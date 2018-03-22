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
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.example.harish.proschoolforparentadvanced.Constants;
import com.example.harish.proschoolforparentadvanced.R;
import com.example.harish.proschoolforparentadvanced.StudentProfileBackTask;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;
import static com.example.harish.proschoolforparentadvanced.MultiSelectionSpinner.getKeyFromValue;

/**
 * Created by JANI on 16-06-2017.
 */

public class ExaminationMarksFragment extends Fragment implements AllExamSchBackTask.ExamResponse,
    AllEvalMarkBackTask.EvalMarks{

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
    String sectionKey, selectedSection;
    Spinner sectionSpinner;
    Map<String, String> sectionMap = new LinkedHashMap<String, String>();
    ArrayList<String> sectionList;
    ArrayAdapter<String> sectionAdapter;
    // Exam Schedule Spinner...
    String examScheduleKey, selectedExamSchedule;
    Spinner examScheduleSpinner;
    Map<String, String> examScheduleMap = new LinkedHashMap<String, String>();
    ArrayList<String> examScheduleList;
    ArrayAdapter<String> examScheduleAdapter;
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
    ArrayList<EvalMarks> listPaper = new ArrayList<EvalMarks>();
    EvalMarkAdapter evalMarkAdapter;
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
    String schoolId;

    //student spinner
    String studentNameKey, selectedStudent;
    private Spinner stdNameSpinner;
    Map<String, String> studentMap = new LinkedHashMap<String, String>();
    ArrayList<String> studentList;
    ArrayAdapter<String> studentAdapter1;

    SharedPreferences sharedPreferences1;
    String loggedEmail;
    String loggedPwd;
    SharedPreferences.OnSharedPreferenceChangeListener listener;
    CircleImageView stdProfileImage;


    String paperkey, selectedPaper;



    public ExaminationMarksFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        examPaperView = inflater.inflate(R.layout.exam_marks_main_layout_two, container, false);
        mContext=getActivity();

        SharedPreferences sharedPreferences = mContext.getSharedPreferences("AppInfo", MODE_PRIVATE);
        schoolId = sharedPreferences.getString(Constants.schoolIdPref, "");
        role = sharedPreferences.getString(Constants.rolePref, "");

        //new ClassBackGroundTask(getActivity(), ExamPaperFragment.this).execute(schoolId);

        sliding_layout = (SlidingUpPanelLayout) examPaperView.findViewById(R.id.sliding_layout);
        sliding_layout.setParallaxOffset(0);
        sliding_layout.setPanelHeight(0);
        exam_layout = (LinearLayout) examPaperView.findViewById(R.id.exam_layout);
        textExamCount = (TextView) examPaperView.findViewById(R.id.textExamCount);




        sharedPreferences2 = mContext.getSharedPreferences("studentDetails", MODE_PRIVATE);
        studentId = sharedPreferences2.getString(Constants.studentId, "");
        schoolId1 = sharedPreferences2.getString(Constants.schoolId1, "");
        classId = sharedPreferences2.getString(Constants.classId, "");
        sectionId = sharedPreferences2.getString(Constants.sectionId, "");
        new AllExamSchBackTask(getActivity(), ExaminationMarksFragment.this).execute(schoolId1);






        examScheduleSpinner = (Spinner) examPaperView.findViewById(R.id.examScheduleSpinner);
        examScheduleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedExamSchedule = examScheduleSpinner.getSelectedItem().toString();
                examScheduleKey = (String) getKeyFromValue(examScheduleMap, selectedExamSchedule);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        select = examPaperView.findViewById(R.id.select);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listPaper.clear();
                if (examScheduleKey != null && !examScheduleKey.isEmpty()) {
                    new AllEvalMarkBackTask(getActivity(), ExaminationMarksFragment.this).execute(studentId, examScheduleKey);
                } else {
                    Toast.makeText(getActivity(), "Please select Schedule..!", Toast.LENGTH_LONG).show();
                }

            }
        });


        //
        recycler_view = (RecyclerView) examPaperView.findViewById(R.id.recycler_view);
        recycler_view.setNestedScrollingEnabled(false);
        // to set action bar title....
        /*((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Exam Paper");*/

        return examPaperView;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    @Override
    public void ExamResponse(String result) throws JSONException {
        if (result != null && !result.isEmpty()) {
            examScheduleMap.clear();
            // examScheduleMap.put("", "-- select --");
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("exam_schedules");
            int count = 0;
            while (count < jsonArray.length()) {
                JSONObject examObject = jsonArray.getJSONObject(count);
                examScheduleMap.put(examObject.getString("exam_sch_id"), examObject.getString("exam_title"));
                count++;
            }
            examScheduleList = new ArrayList<>(examScheduleMap.values());

            examScheduleAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, examScheduleList);
            examScheduleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            examScheduleSpinner.setAdapter(examScheduleAdapter);
            examScheduleAdapter.notifyDataSetChanged();
        }
    }

    /*@Override
    public void examPaper(String result) throws JSONException {
        if (result != null && !result.isEmpty()) {

        }
    }*/





    @Override
    public void evalMarks(String result) throws JSONException {
        if (result != null && !result.isEmpty()) {
            listPaper.clear();
            JSONObject jsonObject = new JSONObject(result);
            System.out.println("evalobject"+jsonObject);
            JSONArray jsonArray = jsonObject.getJSONArray("resultArray");

            //JSONArray jsonArray = jsonObject.getJSONArray(examScheduleKey + "-" + sectionKey);
            System.out.println("alleval"+jsonArray);


            if (jsonArray.length() > 0) {
                int count = 0;
                while (count < jsonArray.length()) {
                    JSONObject markObject = jsonArray.getJSONObject(count);
                    //paper_result_id
                    EvalMarks evalMarks = new EvalMarks(markObject.getString("_id"), markObject.getString("exam_paper_id"),
                            selectedPaper, selectedStudent, markObject.getString("marks"),
                            markObject.getString("percentage"), markObject.getString("conduct"), markObject.getString("paper_name"),
                            markObject.getString("first_name"), markObject.getString("examschedule_name"), selectedExamSchedule, selectedSubject);
                    listPaper.add(evalMarks);
                    count++;
                }
                //Count....
                String taskCount = "ExamEvaluations (" + String.valueOf(listPaper.size()) + ")";
                textExamCount.setText(taskCount);
                //
                recycler_view.setVisibility(View.VISIBLE);

                evalMarkAdapter = new EvalMarkAdapter(getActivity(), listPaper);
                recycler_view.setAdapter(evalMarkAdapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                recycler_view.setLayoutManager(linearLayoutManager);
                evalMarkAdapter.notifyDataSetChanged();
                //
                sliding_layout.setParallaxOffset(0);
                sliding_layout.setPanelHeight(120);
            } else {
                sliding_layout.setParallaxOffset(0);
                sliding_layout.setPanelHeight(0);
                recycler_view.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "No Records Found....!", Toast.LENGTH_LONG).show();
            }
        }

    }


    /*
    "{
  ""SCH-9273-EXM_SCH-3-SCH-9273-CL-2-SEC-1-SUB-1"": [
    {
      ""_id"": ""592bef0b501c1018082fd58d"",
      ""exam_paper_id"": ""SCH-9273-EXM_SCH-3-SCH-9273-CL-2-SEC-1-SUB-1-EXM-5"",
      ""subject_id"": ""SCH-9273-CL-2-SEC-1-SUB-1"",
      ""exam_sch_id"": ""SCH-9273-EXM_SCH-3"",
      ""exam_paper_title"": ""Test Exam"",
      ""date"": ""30-05-2017"",
      ""start_time"": ""10:30 AM"",
      ""end_time"": ""11.30 AM"",
      ""max_marks"": ""100"",
      ""status"": 1
    }
  ]
}"
     */

    /*public class DatePickerFragement implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            int years = year;
            int month = monthOfYear;
            int day = dayOfMonth;
            editDate.setText(new StringBuilder().append(day).append("-").append(month + 1).append("-").append(years).append(""));
        }
    }*/
}
