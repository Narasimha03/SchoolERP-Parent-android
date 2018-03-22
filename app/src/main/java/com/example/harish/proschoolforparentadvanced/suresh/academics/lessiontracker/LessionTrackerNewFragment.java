package com.example.harish.proschoolforparentadvanced.suresh.academics.lessiontracker;

import android.content.Context;
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
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.harish.proschoolforparentadvanced.Constants;
import com.example.harish.proschoolforparentadvanced.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;
import static com.example.harish.proschoolforparentadvanced.Constants.sectionId;

/**
 * Created by JANI on 30-06-2017.
 */

public class LessionTrackerNewFragment extends Fragment implements
        LessionTrackerBackTask.LessionTrackerGraphs {

    View viewTimetable;
    Context mContext;
    TableLayout tableLayout;
    SlidingUpPanelLayout sliding_layout;
    TableRow tr;


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

    /*  //RecyclerView Time Table
      private List<ViewTimeClass> ttSectionsClassList = new ArrayList<>();
      private RecyclerView ttSectionsRecyclerView;
      private ViewTimeAdapter mttSectionAdapter;
      protected RecyclerView.LayoutManager mLayoutManager;
      ViewTimeAdapter viewTimeAdapter;
      ArrayList<ViewTimeClass> timeTableList = new ArrayList<ViewTimeClass>();
      //List<ViewTimeClass> ttSectionsClassList;*/
    String role;

    // Timings
    String timeResponse;
    LinearLayout timingsLayout;
    LinearLayout timeTableLayout;
    LinkedHashMap<String, String> listTime = new LinkedHashMap<String, String>();
    String sectionName,sectionId,studentId;
    SharedPreferences sharedPreferences2;

    String schoolId;
    //TextView chapterText;
    LinearLayout graphView, chapterText, chapterLayout;
    RecyclerView classRecyclerView;

    CardView cardView;

    public LessionTrackerNewFragment() {

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

        viewTimetable = inflater.inflate(R.layout.view_lession_layout_two_section, container, false);
     //   tableLayout = (TableLayout) viewTimetable.findViewById(R.id.tableLayout);
        /*chapterText =  viewTimetable.findViewById(R.id.chapterText);
        graphView = viewTimetable.findViewById(R.id.graphView);*/
      //  chapterLayout = viewTimetable.findViewById(R.id.chapterLayout);
      //  timingsLayout = (LinearLayout) viewTimetable.findViewById(R.id.timingsLayout);
        timeTableLayout = (LinearLayout) viewTimetable.findViewById(R.id.timeTableLayout);
        //cardView = viewTimetable.findViewById(R.id.cardView);

        SharedPreferences sharedPreferences = mContext.getSharedPreferences("AppInfo", MODE_PRIVATE);
        schoolId = sharedPreferences.getString(Constants.schoolIdPref, "");
        role = sharedPreferences.getString(Constants.rolePref, "");
        //  new SessionTimingsBackGroundTask(getActivity(), LessionTrackerNewFragment.this).execute(schoolId);

       // sliding_layout = (SlidingUpPanelLayout) viewTimetable.findViewById(R.id.sliding_layout);
      //  sliding_layout.setParallaxOffset(0);
      //  sliding_layout.setPanelHeight(0);
        sharedPreferences2 = mContext.getSharedPreferences("studentDetails", MODE_PRIVATE);
        studentId = sharedPreferences2.getString(Constants.studentId, "");
        sectionId = sharedPreferences2.getString(Constants.sectionId, "");
        Log.e("section chapters",sectionId);

        if (sectionId != null && !sectionId.isEmpty()) {
            timeTableLayout.removeAllViews();
            new LessionTrackerBackTask(getActivity(), LessionTrackerNewFragment.this).execute(sectionId);
           // sliding_layout.setParallaxOffset(0);
           // sliding_layout.setPanelHeight(120);
        }

      /*  select = (FloatingActionButton) viewTimetable.findViewById(R.id.select);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                *//*String classId = classMap.keySet().toArray()[position].toString();
                System.out.println("classId"+classId);
                Calendar calendar = Calendar.getInstance();
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                System.out.println("day of Week" + dayOfWeek);*//*
                //new TimeTableBackGroundTask(mContext, TimeTableActivity.this).execute(String.valueOf(dayOfWeek), classId);


                //   listEmp.clear();
                //   new TimeTableClassSectionResponse(getActivity(), LessionTrackerNewFragment.this).execute(sectionKey);

            }
        });*/

/*
        fabLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //  showFABMenu();
                *//*AddTimeTableFragment addTimetableFragment = new AddTimeTableFragment();
                setFragment(addTimetableFragment);*//*
                //   closeFABMenu();

                Intent i = new Intent(getActivity(),AddTimeTableFragment.class);
                startActivity(i);

                closeFABMenu();
            }


        });
        if (role.equals("admin")) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    //    animateFAB();
                    if (!isFABOpen) {
                        showFABMenu();
                    } else {
                        closeFABMenu();
                    }
                }
            });
        }
        else  if (role.equals("teacher")) {
            fab.hide();
        }

        fabBGLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeFABMenu();
                //animateFAB();
            }
        });*/
        // select button....
        /*select = viewTimetable.findViewById(R.id.select);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sectionKey != null && !sectionKey.isEmpty()) {
                    new TimeTableBackTask(getActivity(), ViewTimeTableClassFragment.this).execute(sectionKey);
                } else {
                    Toast.makeText(mContext, "Please Select Section...!", Toast.LENGTH_LONG).show();
                }
            }
        });*/
        // Tableview Headers....
        //   addHeaders();
        // Tableview Rows....
        //   addData();
        //
//        noText = (TextView) viewTimetable.findViewById(R.id.noText);
    //    cardView1 = (CardView) viewTimetable.findViewById(R.id.cardView1);


        //Time Table RecyclerView


        /*View view;
        view = inflater.inflate(R.layout.view_time_table_recycler_layout, container, false);
*/
     /*   ttSectionsRecyclerView = viewTimetable.findViewById(R.id.view_time_table_recycler_two);
        ttSectionsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        mttSectionAdapter = new ViewTimeAdapter(ttSectionsClassList);
        //RecyclerView.LayoutManager mttSectionsLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false );
        ttSectionsRecyclerView.setLayoutManager(mLayoutManager);
        ttSectionsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        ttSectionsRecyclerView.setAdapter(mttSectionAdapter);*/
        /*adminSectionsRecyclerView.setAdapter(new AdminSectionsAdapter(adminSectionsClassList, new AdminCurriculumAdapter.OnItemClickListener() {


            @Override
            public void onItemClick(AdminCurriculumClass item) {
                Toast.makeText(getApplicationContext(), item.getAdminCurClasses(), Toast.LENGTH_SHORT).show();
            }

        }));*/


        // preparettSectionsData();

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Timetable");

        return viewTimetable;
    }

    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .addToBackStack(null)
                .commit();
    }




    @Override
    public void lessionTrackerGraphs(String response) throws JSONException {

        if (response != null && !response.isEmpty()) {
            Log.e("Resp", response);
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("subjects");
            for (int i = 0; i < jsonArray.length(); i++) {

                /*CardView cardView = new CardView(mContext);
                LinearLayout.LayoutParams layoutParamsCard = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParamsCard.setMargins(25, 15, 25, 15);
                //linearLayout.setBackgroundResource(R.color.white);
                cardView.setBackgroundResource(R.drawable.lesson_background);
                cardView.setLayoutParams(layoutParamsCard);*/

                // Linear Layout
                LinearLayout linearLayout = new LinearLayout(mContext);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(25, 15, 25, 15);
                //linearLayout.setBackgroundResource(R.color.white);
                linearLayout.setBackgroundResource(R.drawable.lesson_background);
                //linearLayout.setBackground(R.drawable.lesson_background);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.setPadding(10,20,10,20);
                linearLayout.setLayoutParams(layoutParams);


                // JSONObject
                JSONObject sectionObject = jsonArray.getJSONObject(i);
                // Section
                sectionName = sectionObject.getString("subjectName").toUpperCase();
                TextView sectionTV = new TextView(mContext);
                sectionTV.setText(sectionName);
                sectionTV.setTextSize(18);
                sectionTV.setBackgroundResource(R.color.colorPrimary);
                sectionTV.setGravity(1);
                sectionTV.setTextColor(ContextCompat.getColor(mContext, android.R.color.white));
                sectionTV.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fonts/Handlee-Regular.ttf"));
                sectionTV.setPadding(20, 10, 20, 10);
                sectionTV.setTypeface(Typeface.DEFAULT_BOLD);
                // Adding section name to linear layout
                linearLayout.addView(sectionTV);
                // TimeTable
                JSONArray timeTableArray = sectionObject.getJSONArray("chapters");
                for (int j = 0; j < timeTableArray.length(); j++) {

                    JSONObject timeTableObject = timeTableArray.getJSONObject(j);
                    // Subject


                    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);

                    TextView subjectTV = new TextView(mContext);
                    subjectTV.setText(timeTableObject.getString("chapter_code"));
                    subjectTV.setPadding(10, 10, 10, 10);
                    subjectTV.setTextSize(16);
                    subjectTV.setLayoutParams(layoutParams2);
                    subjectTV.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fonts/Handlee-Regular.ttf"));
                    subjectTV.setTextColor(ContextCompat.getColor(mContext, android.R.color.black));
                    // Adding subject name to linear layout
                    //linearLayout.addView(subjectTV);
                    linearLayout.addView(subjectTV);


                    //    holder.mChart.setOnChartValueSelectedListener(mContext);

                    HorizontalBarChart horizontalBarChartTv = new HorizontalBarChart(mContext);
                    horizontalBarChartTv.setPadding(0, 5, 0, 10);
                    horizontalBarChartTv.setDrawGridBackground(false);
                    horizontalBarChartTv.getDescription().setEnabled(false);
                    horizontalBarChartTv.setScaleEnabled(false);

                    horizontalBarChartTv.setPinchZoom(false);
                    horizontalBarChartTv.setTouchEnabled(false);
                    horizontalBarChartTv.setDrawBarShadow(false);
                    horizontalBarChartTv.setDrawValueAboveBar(true);
                    horizontalBarChartTv.setHighlightFullBarEnabled(false);
                    horizontalBarChartTv.setVisibleYRangeMaximum(100, YAxis.AxisDependency.LEFT);
                    //mChart.setVisibleXRangeMaximum(100);
                    horizontalBarChartTv.getAxisLeft().setEnabled(false);
                    horizontalBarChartTv.getAxisRight().setDrawGridLines(false);
                    horizontalBarChartTv.getAxisRight().setDrawZeroLine(true);
                    horizontalBarChartTv.getAxisLeft().setLabelCount(0, false);
                    horizontalBarChartTv.getAxisLeft().setTextSize(20f);
                    horizontalBarChartTv.setDrawGridBackground(false);

                    XAxis xAxis = horizontalBarChartTv.getXAxis();
                    xAxis.setCenterAxisLabels(true);
                    xAxis.setDrawAxisLine(false);
                    xAxis.setGranularity(1f);

                    xAxis.setGranularityEnabled(true);
                    xAxis.setLabelRotationAngle(0f);
                    xAxis.setDrawGridLines(false);
                    xAxis.setEnabled(false);

                    YAxis rightYAxis = horizontalBarChartTv.getAxisRight();
                    rightYAxis.setEnabled(false);

                    horizontalBarChartTv.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
                    horizontalBarChartTv.setDrawValueAboveBar(false);
                    //  new BarEntry(5, new float[]{85, 7, 8});
                    ArrayList<BarEntry> yValues = new ArrayList<>();
                    yValues.add(new BarEntry(5, new float[]{Float.parseFloat(timeTableObject.getString("completed_topics")), Float.parseFloat(timeTableObject.getString("remaining_topics"))}));           /* yValues.add(new BarEntry(5, new float[]{85, 7, 8}));
            yValues.add(new BarEntry(15, new float[]{87, 7, 6}));
            yValues.add(new BarEntry(25, new float[]{80, 10, 10}));
            yValues.add(new BarEntry(35, new float[]{79, 11, 10}));*/

                    BarDataSet set = new BarDataSet(yValues, "");
                    set.setAxisDependency(YAxis.AxisDependency.RIGHT);
                    BarData data = new BarData(set);
                    data.setValueFormatter(new IValueFormatter() {
                        @Override
                        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                            if (value > 0) {
                                return Math.round(value) + "";
                            } else {
                                return "";
                            }
                        }
                    });
                    data.setValueTextSize(17f);
                    set.setColors(Color.rgb(95, 182, 156), Color.rgb(209, 190, 168), Color.rgb(110, 127, 128));
                    set.setStackLabels(new String[]{
                            "Completed", "Remaining"
                    });          //  holder.statusPresent.setText(evalMarks.getStdAttendance());

                    data.setBarWidth(20f); // set custom bar width
                    horizontalBarChartTv.setData(data);
                    horizontalBarChartTv.setFitBars(true); // make the x-axis fit exactly all bars
                    horizontalBarChartTv.invalidate(); // refresh

                    horizontalBarChartTv.animateY(1400, Easing.EasingOption.EaseInOutQuad);
                    horizontalBarChartTv.setScaleXEnabled(false);

                    Legend legend = horizontalBarChartTv.getLegend();
                    legend.setEnabled(true);
                    legend.setTextSize(10);
                    legend.setFormSize(10);
                    legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
                    legend.setStackSpace(10);
                    linearLayout.addView(horizontalBarChartTv);

                    // View
                 /*   View view = new View(mContext);
                    LinearLayout.LayoutParams viewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
                    view.setLayoutParams(viewParams);
                    view.setBackgroundColor(ContextCompat.getColor(mContext, android.R.color.darker_gray));
                    // Adding view to linear layout
                    linearLayout.addView(view);*/
                }

                //((ViewGroup)timeTableLayout.getParent()).removeView(timeTableLayout);
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
