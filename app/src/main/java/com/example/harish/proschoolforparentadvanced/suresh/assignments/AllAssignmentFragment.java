package com.example.harish.proschoolforparentadvanced.suresh.assignments;

import android.animation.Animator;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.harish.proschoolforparentadvanced.Constants;
import com.example.harish.proschoolforparentadvanced.R;
import com.example.harish.proschoolforparentadvanced.suresh.academics.AllChapterBackTask;
import com.example.harish.proschoolforparentadvanced.suresh.academics.AllSubjectBackTask;
import com.example.harish.proschoolforparentadvanced.suresh.assignments.AllAssignmentBackTask;
import com.example.harish.proschoolforparentadvanced.suresh.assignments.AssignmentAdapter;
import com.example.harish.proschoolforparentadvanced.suresh.assignments.Assignments;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;
import static com.example.harish.proschoolforparentadvanced.MultiSelectionSpinner.getKeyFromValue;

/**
 * Created by JANI on 03-06-2017.
 */

public class AllAssignmentFragment extends Fragment implements
        AllSubjectBackTask.OnSubjectResponse, AllChapterBackTask.ChapterResponse, AllAssignmentBackTask.AssignResponse {

    View assignmentView;
    //
    SlidingUpPanelLayout sliding_layout;
    //Student Layout
    LinearLayout assignment_layout;
    TextView textAssignCount;
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
    // Subject Spinner...
    String subjectkey, selectedSubject;
    Spinner subjectSpinner;
    Map<String, String> subjectMap = new LinkedHashMap<String, String>();
    ArrayList<String> subjectList;
    ArrayAdapter<String> subjectAdapter;
    //
    // Chapter Spinner....
    String chapterKey, selectedChapter;
    Spinner chapterSpinner;
    Map<String, String> chapterMap = new LinkedHashMap<String, String>();
    ArrayList<String> chapterList;
    ArrayAdapter<String> chapterAdapter;
    //
    //
    FloatingActionButton select;
    //
    RecyclerView recycler_view;
    AssignmentAdapter assignmentAdapter;
    ArrayList<Assignments> listAssignment = new ArrayList<Assignments>();
    TextView noRecordsFound;
    Context mContext;
    String schoolId;
    FloatingActionButton fab;
    LinearLayout fabLayout1;
    View fabBGLayout;
    FrameLayout frameLayout;
    boolean isFABOpen = false;
    String role;
    String studentId,sectionId;
    SharedPreferences sharedPreferences2;
    Toolbar toolbar;

    public AllAssignmentFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        assignmentView = inflater.inflate(R.layout.all_assignments_layout_two, container, false);
        mContext=getActivity();

        toolbar = assignmentView.findViewById(R.id.tab_toolabr);
        toolbar.setTitle("Assignments");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));


        sliding_layout = (SlidingUpPanelLayout) assignmentView.findViewById(R.id.sliding_layout);
        sliding_layout.setParallaxOffset(0);
        sliding_layout.setPanelHeight(0);
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("AppInfo", MODE_PRIVATE);
        schoolId = sharedPreferences.getString(Constants.schoolIdPref, "");
        role = sharedPreferences.getString(Constants.rolePref, "");
        Log.e("schoolId dashBaord", schoolId);
        sharedPreferences2 = mContext.getSharedPreferences("studentDetails", MODE_PRIVATE);
        studentId = sharedPreferences2.getString(Constants.studentId, "");
        sectionId = sharedPreferences2.getString(Constants.sectionId, "");
        Log.e("section in subjects",sectionId);




        assignment_layout = (LinearLayout) assignmentView.findViewById(R.id.assignment_layout);
        textAssignCount = (TextView) assignmentView.findViewById(R.id.textAssignCount);
        noRecordsFound=(TextView)assignmentView.findViewById(R.id.no_records_found);



        if (sectionId != null && !sectionId.isEmpty()) {
            new AllSubjectBackTask(getActivity(), AllAssignmentFragment.this).execute(sectionId);
        }



        // subject spinner...
        subjectSpinner = (Spinner) assignmentView.findViewById(R.id.subjectSpinner);
        subjectMap.put("", "-- select --");
        subjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSubject = subjectSpinner.getSelectedItem().toString();
                subjectkey = (String) getKeyFromValue(subjectMap, selectedSubject);
                if (subjectkey != null && !subjectkey.isEmpty()) {
                    new AllChapterBackTask(getActivity(), AllAssignmentFragment.this).execute(subjectkey);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // chapter spinner;
        chapterSpinner = (Spinner) assignmentView.findViewById(R.id.chapterSpinner);
        chapterMap.put("", "-- select --");
        chapterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedChapter = chapterSpinner.getSelectedItem().toString();
                chapterKey = (String) getKeyFromValue(chapterMap, selectedChapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //
        select = assignmentView.findViewById(R.id.select);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listAssignment.clear();
                if (chapterKey != null && !chapterKey.isEmpty()) {
                    new AllAssignmentBackTask(getActivity(), AllAssignmentFragment.this).execute(sectionKey, chapterKey);
                } else {
                    Toast.makeText(getActivity(), "Please select Chapter..!", Toast.LENGTH_LONG).show();
                }

            }
        });



        recycler_view = (RecyclerView) assignmentView.findViewById(R.id.recycler_view);
        recycler_view.setNestedScrollingEnabled(false);
        // to set action bar title....
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Assignments");

        return assignmentView;
    }




    @Override
    public void OnSubjectResponse(String response) throws JSONException {
        if (response != null && !response.isEmpty()) {
            subjectMap.clear();
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("subjects");
            System.out.println("subject response"+jsonArray);

            if (jsonArray.length() > 0) {
                int count = 0;
                while (count < jsonArray.length()) {
                    JSONObject subObject = jsonArray.getJSONObject(count);
                    subjectMap.put(subObject.getString("subject_id"), subObject.getString("name"));
                    count++;
                }
                subjectList = new ArrayList<>(subjectMap.values());
                //
                subjectAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, subjectList);
                subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                subjectSpinner.setAdapter(subjectAdapter);
                subjectAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void ChapterResponse(String response) throws JSONException {
        if (response != null && !response.isEmpty()) {
            chapterMap.clear();
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("chapters");
            System.out.println("chapter response"+jsonArray);

            if (jsonArray.length() > 0) {
                int count = 0;
                while (count < jsonArray.length()) {
                    JSONObject chapObject = jsonArray.getJSONObject(count);
                    chapterMap.put(chapObject.getString("lession_id"), chapObject.getString("title"));
                    count++;
                }
            }
            chapterList = new ArrayList<>(chapterMap.values());
            //
            chapterAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, chapterList);
            chapterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            chapterSpinner.setAdapter(chapterAdapter);
            chapterAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void AssignResponse(String result) throws JSONException {
        if (result != null && !result.isEmpty()) {
            listAssignment.clear();
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("assignments");

            System.out.println("Assignment response"+jsonArray);

            if (jsonArray.length() > 0) {
                int count = 0;
                while (count < jsonArray.length()) {
                    JSONObject assignObject = jsonArray.getJSONObject(count);
                    Assignments assignments = new Assignments(assignObject.getString("_id"), assignObject.getString("assignment_id"),
                            assignObject.getString("assignment_title"), assignObject.getString("section_id"), selectedChapter,
                            assignObject.getString("due_date"), assignObject.getString("description"));
                    listAssignment.add(assignments);
                    count++;
                }
                //
                //Count....
                String taskCount = "Assignments (" + String.valueOf(listAssignment.size()) + ")";
                textAssignCount.setText(taskCount);
                //
                assignmentAdapter = new AssignmentAdapter(getActivity(), listAssignment);
                recycler_view.setAdapter(assignmentAdapter);
                recycler_view.setVisibility(View.VISIBLE);
                noRecordsFound.setVisibility(View.GONE);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                recycler_view.setLayoutManager(linearLayoutManager);
                assignmentAdapter.notifyDataSetChanged();
                sliding_layout.setParallaxOffset(0);
                sliding_layout.setPanelHeight(120);

            } else {
                sliding_layout.setParallaxOffset(0);
                sliding_layout.setPanelHeight(0);
                String taskCount = "Chapters";
                textAssignCount.setText(taskCount);
                recycler_view.setVisibility(View.GONE);
                noRecordsFound.setVisibility(View.VISIBLE);
                noRecordsFound.setText("No Records Found");
                Toast.makeText(getActivity(), "No Records Found....!", Toast.LENGTH_SHORT).show();

              /*  recycler_view.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "No Records Found....!", Toast.LENGTH_LONG).show();*/
            }
        }
    }



    /*
    "{
  ""assignments"": [
    {
      ""_id"": ""592d21dd16b61d077cc5351e"",
      ""assignment_id"": ""ASMT-1"",
      ""assignment_title"": ""Test assignment title"",
      ""section_id"": ""SCH-9273-CL-2-SEC-1"",
      ""lesson_id"": ""SCH-9273-CL-2-SEC-1-SUB-2-LES-3"",
      ""due_date"": ""26-05-2017"",
      ""description"": ""this is test description for assignment""
    }
  ]
}"
     */


}
