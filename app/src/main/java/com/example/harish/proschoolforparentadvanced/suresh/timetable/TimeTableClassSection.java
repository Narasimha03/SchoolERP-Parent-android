package com.example.harish.proschoolforparentadvanced.suresh.timetable;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


import com.example.harish.proschoolforparentadvanced.Constants;
import com.example.harish.proschoolforparentadvanced.R;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;
import static com.example.harish.proschoolforparentadvanced.MultiSelectionSpinner.getKeyFromValue;

/**
 * Created by JANI on 30-06-2017.
 */

public class TimeTableClassSection extends Fragment implements
       TimeTableClassSectionResponse.OnTimeTableResponse,SessionTimingsBackGroundTask.OnTimeResponse {

    View viewTimetable;
    Context mContext;
    TableLayout tableLayout;
    SlidingUpPanelLayout sliding_layout;
    TableRow tr;
    TextView timeTv, sub1Tv, sub2Tv, sub3Tv, sub4Tv, sub5Tv, sub6Tv;

    String day[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    String time[] = {"9:00-10:00", "10:00-11:00", "11:00-12:00", "12:00-1:00", "1:00-2:00", "3:00-4:00"};
    String subject[] = {"subject-1", "subject-2", "subject-3", "subject-4", "subject-5", "subject-6"};
    //
    ArrayList<String> timeList = new ArrayList<String>();
    ArrayList<String> subjectList = new ArrayList<String>();
    // Class Spinner....
    String classKey, selectedClass;
    Spinner classSpinner;
    Map<String, String> classMap = new LinkedHashMap<String, String>();
    ArrayList<String> classList;
    ArrayAdapter<String> classAdapter;
    // Section Spinner....
    String sectionKey, selectedSection;
    Spinner sectionSpinner;
    Map<String, String> sectionMap = new LinkedHashMap<String, String>();
    ArrayList<String> sectionList;
    ArrayAdapter<String> sectionAdapter;
    // Button...
    FloatingActionButton select;
    //
    CardView cardView1;
    TextView noText;
    FloatingActionButton fab;
    LinearLayout fabLayout1;
    View fabBGLayout;
    FrameLayout frameLayout;
    // FrameLayout fabBGLayout;
    boolean isFABOpen = false;

    //RecyclerView Time Table
    private RecyclerView ttSectionsRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;
    //List<ViewTimeClass> ttSectionsClassList;
    String role;

    // Timings
    String timeResponse;
    LinearLayout timingsLayout;
    LinearLayout timeTableLayout;
    LinkedHashMap<String, String> listTime = new LinkedHashMap<String, String>();
    String sectionName;
    String schoolId;
    RecyclerView classRecyclerView;
    SharedPreferences sharedPreferences2;
    String sectionId,studentId;
    Toolbar toolbar;

    public TimeTableClassSection() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
    //    classRecyclerView = (RecyclerView)viewTimetable. findViewById(R.id.classRecyclerView);
        // Timings Layout

        viewTimetable = inflater.inflate(R.layout.view_timetable_layout_two_section, container, false);
      //  tableLayout = (TableLayout) viewTimetable.findViewById(R.id.tableLayout);
        timingsLayout = (LinearLayout)viewTimetable. findViewById(R.id.timingsLayout);
        timeTableLayout = (LinearLayout)viewTimetable. findViewById(R.id.timeTableLayout);
       // sliding_layout = (SlidingUpPanelLayout) viewTimetable.findViewById(R.id.sliding_layout);

        toolbar = viewTimetable.findViewById(R.id.tab_toolabr);
        toolbar.setTitle("Timetable");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        SharedPreferences sharedPreferences = mContext.getSharedPreferences("AppInfo", MODE_PRIVATE);
        schoolId = sharedPreferences.getString(Constants.schoolIdPref, "");
        role = sharedPreferences.getString(Constants.rolePref, "");
        sharedPreferences2 = mContext.getSharedPreferences("studentDetails", MODE_PRIVATE);
        studentId = sharedPreferences2.getString(Constants.studentId, "");
        sectionId = sharedPreferences2.getString(Constants.sectionId, "");
        Log.e("section for time table",sectionId);
       // schoolId = sharedPreferences.getString(Constants.schoolId1, "");

        new SessionTimingsBackGroundTask(getActivity(), TimeTableClassSection.this).execute(schoolId);


        if (sectionId != null && !sectionId.isEmpty()) {
            timeTableLayout.removeAllViews();
            new TimeTableClassSectionResponse(getActivity(), TimeTableClassSection.this).execute(sectionId);
           // sliding_layout.setParallaxOffset(0);
           // sliding_layout.setPanelHeight(120);

        }

      //  sliding_layout.setParallaxOffset(0);
      //  sliding_layout.setPanelHeight(0);



        //
     //   noText = (TextView) viewTimetable.findViewById(R.id.noText);
     //   cardView1 = (CardView) viewTimetable.findViewById(R.id.cardView1);



        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Timetable");

        return viewTimetable;
    }




    @Override
    public void onTimeResponse(String response) throws JSONException {
        if (response != null && !response.isEmpty()) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("session_timings");
                Log.e("session timings", String.valueOf(jsonArray));
                if (jsonArray.length() > 0) {
                    // TimeTable
                    TextView heading = new TextView(mContext);
                    heading.setText("");
                    heading.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fonts/Handlee-Regular.ttf"));
                    heading.setTextSize(18);
                    //heading.setTypeface(Typeface.DEFAULT_BOLD);
                    timingsLayout.addView(heading);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject timeObject = jsonArray.getJSONObject(i);
                        // Timings
                        TextView timeView = new TextView(mContext);
                        String text = timeObject.getString("start_time") + " - " + timeObject.getString("end_time");
                        timeView.setText(text);
                        timeView.setPadding(10, 20, 10, 20);
                        timeView.setTextSize(15);
                        timeView.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fonts/Handlee-Regular.ttf"));
                        timeView.setTextColor(ContextCompat.getColor(mContext, android.R.color.black));
                        timingsLayout.addView(timeView);
                        listTime.put(timeObject.getString("start_time"), timeObject.getString("end_time"));
                        // View
                        View view = new View(mContext);
                        LinearLayout.LayoutParams viewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
                        view.setLayoutParams(viewParams);
                        view.setBackgroundColor(ContextCompat.getColor(mContext, android.R.color.darker_gray));
                        timingsLayout.addView(view);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTimeTableResponse(String response) throws JSONException {
        if (response != null && !response.isEmpty()) {
            Log.e("Resp", response);
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("timetable");
            for (int i = 0; i < jsonArray.length(); i++) {
                // Linear Layout
                LinearLayout linearLayout = new LinearLayout(mContext);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(15, 0, 15, 0);
                linearLayout.setBackgroundResource(R.color.white);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.setLayoutParams(layoutParams);
                // JSONObject
                JSONObject sectionObject = jsonArray.getJSONObject(i);
                // Section
                sectionName = sectionObject.getString("day").toUpperCase();
                TextView sectionTV = new TextView(mContext);
                sectionTV.setText(sectionName);
                sectionTV.setTextSize(18);
                sectionTV.setGravity(1);
                sectionTV.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fonts/Handlee-Regular.ttf"));
                sectionTV.setPadding(20,0,20,0);
                sectionTV.setTypeface(Typeface.DEFAULT_BOLD);
                // Adding section name to linear layout
                linearLayout.addView(sectionTV);
                // TimeTable
                JSONArray timeTableArray = sectionObject.getJSONArray("timetableData");
                for (int j = 0; j < timeTableArray.length(); j++) {
                    JSONObject timeTableObject = timeTableArray.getJSONObject(j);
                    // Subject
                    TextView subjectTV = new TextView(mContext);
                    subjectTV.setText(timeTableObject.getString("subject"));
                    subjectTV.setPadding(10, 20, 10, 20);
                    subjectTV.setTextSize(15);
                    subjectTV.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fonts/Handlee-Regular.ttf"));
                    subjectTV.setTextColor(ContextCompat.getColor(mContext, android.R.color.black));
                    // Adding subject name to linear layout
                    linearLayout.addView(subjectTV);
                    // View
                    View view = new View(mContext);
                    LinearLayout.LayoutParams viewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
                    view.setLayoutParams(viewParams);
                    view.setBackgroundColor(ContextCompat.getColor(mContext, android.R.color.darker_gray));
                    // Adding view to linear layout
                    linearLayout.addView(view);
                }
                timeTableLayout.addView(linearLayout);
            }
        }
    }



    /*
    "{
    ""timetable"": [
        {
            ""_id"": ""5928895127110e1fdc846a42"",
            ""timetable_id"": ""SCH-9273-CL-2-SEC-1-SEC-1"",
            ""section_id"": ""SCH-9273-CL-2-SEC-1"",
            ""day"": ""Monday"",
            ""start_time"": ""09:00"",
            ""end_time"": ""10:00"",
            ""room_no"": ""ROOM-1"",
            ""subject_id"": ""SCH-9273-CL-2-SEC-1-SUB-1"",
            ""status"": 1
        },
        {
            ""_id"": ""593e33ff8f6f4e0d04fc4350"",
            ""timetable_id"": ""SCH-9273-CL-2-SEC-1-SEC-2"",
            ""section_id"": ""SCH-9273-CL-2-SEC-1"",
            ""day"": ""Monday"",
            ""start_time"": ""09:00"",
            ""end_time"": ""10:00"",
            ""room_no"": ""ROOM-1"",
            ""subject_id"": ""SCH-9273-CL-2-SEC-1-SUB-2"",
            ""status"": 1
        }
    ]
}"
     */
}
