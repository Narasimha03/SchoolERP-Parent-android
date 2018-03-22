package com.example.harish.proschoolforparentadvanced.suresh.examinations;

import android.animation.Animator;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.harish.proschoolforparentadvanced.Constants;
import com.example.harish.proschoolforparentadvanced.R;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by JANI on 07-06-2017.
 */

public class ExamScheduleFragment extends Fragment implements AllExamSchBackTask.ExamResponse{
    View examScheduleView;
    //
    //Student Layout

    // Class Spinner....

    // Section Spinner....

    // EditText...
    EditText  editStartDate, editEndDate;
    // Button...
    Button addSchedule;
    //
    ArrayList<ExamSchedule> listExamSchedule = new ArrayList<ExamSchedule>();
    String schoolId;

    SharedPreferences sharedPreferences1;
    String loggedEmail;
    String loggedPwd;
    SharedPreferences.OnSharedPreferenceChangeListener listener;
    CircleImageView stdProfileImage;
    String selectedSection;


    //student spinner
    String studentNameKey, selectedStudent;
    private Spinner stdNameSpinner;
    Map<String, String> studentMap = new LinkedHashMap<String, String>();
    ArrayList<String> studentList;
    ArrayAdapter<String> studentAdapter1;

    View examPaperView;
    //
    SlidingUpPanelLayout sliding_layout;
    //Student Layout
    LinearLayout exam_layout;
    TextView textExamCount;
    // Class Spinner....
    String classKey, selectedClass;
    Spinner classSpinner;
    Map<String, String> classMap = new LinkedHashMap<String, String>();
    ArrayList<String> classList;
    ArrayAdapter<String> classAdapter;
    // Section Spinner....
    String sectionKey;
    Spinner sectionSpinner;
    Map<String, String> sectionMap = new LinkedHashMap<String, String>();
    ArrayList<String> sectionList;
    ArrayAdapter<String> sectionAdapter;
    // Exam Schedule Spinner...
    String examScheduleKey, selectedExamSchedule;
    Spinner examScheduleSpinner;
    Map<String, String> examScheduleMap = new LinkedHashMap<String, String>();
    ArrayList<String> examScheduleList;
   // ArrayAdapter<String> examScheduleAdapter;
    ExamScheduleAdapter examScheduleAdapter;
    // Subject Spinner...
    String subjectkey, selectedSubject;
    Spinner subjectSpinner;
    Map<String, String> subjectMap = new LinkedHashMap<String, String>();
    ArrayList<String> subjectList;
    ArrayAdapter<String> subjectAdapter;
    // EditText....
    EditText editTitle, editMarks, editDate, editStartTime, editEndTime;
    //
    //Button select, addPaper;
    FloatingActionButton select;
    //
    RecyclerView recycler_view;
    ArrayList<ExamPaper> listPaper = new ArrayList<ExamPaper>();
    ExamPaperAdapter examPaperAdapter;
    Context mContext;
    FloatingActionButton fab;
    LinearLayout fabLayout1;
    View fabBGLayout;
    FrameLayout frameLayout;
    // FrameLayout fabBGLayout;
    boolean isFABOpen = false;
    String role;



    String schoolId1;
    SharedPreferences sharedPreferences2,sharedPreferences3;
    SharedPreferences sharedPreferences;

    String studentId;
    String classId;
    String sectionId;


    public ExamScheduleFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        examScheduleView = inflater.inflate(R.layout.exam_schedule_layout, container, false);
     mContext=getActivity();

        SharedPreferences sharedPreferences = mContext.getSharedPreferences("AppInfo", MODE_PRIVATE);
        schoolId = sharedPreferences.getString(Constants.schoolIdPref, "");
        role = sharedPreferences.getString(Constants.rolePref, "");
        //new ClassBackGroundTask(getActivity(), ExamScheduleFragment.this).execute(schoolId);
       // sliding_layout = (SlidingUpPanelLayout) examScheduleView.findViewById(R.id.sliding_layout);
       // sliding_layout.setParallaxOffset(0);
       // sliding_layout.setPanelHeight(0);
      //  exam_layout = (LinearLayout) examScheduleView.findViewById(R.id.exam_layout);
       // textExamCount = (TextView) examScheduleView.findViewById(R.id.textExamCount);

        new AllExamSchBackTask(getActivity(), ExamScheduleFragment.this).execute(schoolId);


        recycler_view = (RecyclerView) examScheduleView.findViewById(R.id.recycler_view);
        recycler_view.setNestedScrollingEnabled(false);
        /*fabLayout1= (LinearLayout) examScheduleView.findViewById(R.id.fabLayout1);
        fab = (FloatingActionButton) examScheduleView.findViewById(R.id.fab);
        frameLayout=(FrameLayout)examScheduleView.findViewById(R.id.frameLayout);
        fabBGLayout=examScheduleView.findViewById(R.id.fabBGLayout);
        // class spinner....
        classSpinner = (Spinner) examScheduleView.findViewById(R.id.classSpinner);
        classMap.put("", "-- Select --");
        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedClass = classSpinner.getSelectedItem().toString();
                classKey = (String) getKeyFromValue(classMap, selectedClass);
                // Getting Sections From Server....
                if (classKey != null && !classKey.isEmpty()) {
                    new SectionBackGroundTask(getActivity(), ExamScheduleFragment.this).execute(classKey);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // section spinner....
        sectionSpinner = (Spinner) examScheduleView.findViewById(R.id.sectionSpinner);
        sectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSection = sectionSpinner.getSelectedItem().toString();
                sectionKey = (String) getKeyFromValue(sectionMap, selectedSection);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fabLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //  showFABMenu();
                AddScheduleFragment examScheduleFragment = new AddScheduleFragment();
                setFragment(examScheduleFragment);
                //   closeFABMenu();
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
        });

        recycler_view = (RecyclerView) examScheduleView.findViewById(R.id.recycler_view);
        recycler_view.setNestedScrollingEnabled(false);*/
        // to set action bar title....
     /*((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Exam Schedule");*/

        return examScheduleView;
    }
/*
    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .addToBackStack(null)
                .commit();
    }*/



    @Override
    public void ExamResponse(String result) throws JSONException {
        if (result != null && !result.isEmpty()) {
            listExamSchedule.clear();
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("exam_schedules");

            System.out.println("getexam schedule"+jsonArray);

            if (jsonArray.length() > 0) {
                int count = 0;
                while (count < jsonArray.length()) {
                    JSONObject examObject = jsonArray.getJSONObject(count);
                    ExamSchedule examSchedule = new ExamSchedule(examObject.getString("_id"), examObject.getString("exam_sch_id"),
                            examObject.getString("school_id"), examObject.getString("exam_title"), examObject.getString("exam_title"),
                            examObject.getString("from_date"), examObject.getString("status"));
                    listExamSchedule.add(examSchedule);
                    count++;
                }
                //
                //Count....
             //   String taskCount = "Exams (" + String.valueOf(listExamSchedule.size()) + ")";
              //  textExamCount.setText(taskCount);
                //
                examScheduleAdapter = new ExamScheduleAdapter(getActivity(), listExamSchedule);
                recycler_view.setAdapter(examScheduleAdapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                recycler_view.setLayoutManager(linearLayoutManager);
                examScheduleAdapter.notifyDataSetChanged();
                //
             //   sliding_layout.setParallaxOffset(0);
             //   sliding_layout.setPanelHeight(120);
            } else {
              //  sliding_layout.setParallaxOffset(0);
              //  sliding_layout.setPanelHeight(0);
                recycler_view.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "No Records Found....!", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }



}
