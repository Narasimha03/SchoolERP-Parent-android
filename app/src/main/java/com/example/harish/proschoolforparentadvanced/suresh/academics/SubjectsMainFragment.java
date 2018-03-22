package com.example.harish.proschoolforparentadvanced.suresh.academics;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
 * Created by suresh on 11/10/2017.
 */



public class SubjectsMainFragment extends Fragment implements
        AllSubjectBackTask.OnSubjectResponse,SubjectAdapter.ItemClickSubjectListener {

    View subjectView;
    //
    SlidingUpPanelLayout sliding_layout;
    //Student Layout
    LinearLayout sub_layout;
    TextView textSubCount;
    //
    EditText editSubName, editSubCode;

    RecyclerView recycler_view;
    SubjectAdapter subjectAdapter;
    //StationAdapter stationAdapter;
    ArrayList<Subject> subjectList = new ArrayList<Subject>();
    //
    Button addSubject;

    FloatingActionButton select;
    TextView noRecordsFound;

    FloatingActionButton fab, fab1, fab2, fab3;
    LinearLayout fabLayout1, fabLayout2, fabLayout3;
    View fabBGLayout;
    FrameLayout frameLayout;
    boolean isFABOpen = false;
    Context mContext;
    String schoolId;
    SharedPreferences sharedPreferences;
    String studentId;
    String classId;
    String sectionId;
    String sectionIdStudent;

    String schoolId1;
    SharedPreferences sharedPreferences2;
    CircleImageView stdProfileImage;


    //student spinner
    String studentNameKey, selectedStudent;
    private Spinner stdNameSpinner;
    Map<String, String> studentMap = new LinkedHashMap<String, String>();
    Map<String, String> studentMap1 = new LinkedHashMap<String, String>();

    ArrayList<String> studentList;
    ArrayAdapter<String> studentAdapter1;


    SharedPreferences sharedPreferences1;
    String loggedEmail;
    String loggedPwd;
    String studentSubjectKey;

    public SubjectsMainFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        subjectView = inflater.inflate(R.layout.show_all_subject_layout_two, container, false);
        mContext = getActivity();
        /*sliding_layout = (SlidingUpPanelLayout) subjectView.findViewById(R.id.sliding_layout);
        sliding_layout.setParallaxOffset(0);
        sliding_layout.setPanelHeight(0);
        sub_layout = (LinearLayout) subjectView.findViewById(R.id.emp_layout);
        textSubCount = (TextView) subjectView.findViewById(R.id.textEmpCount);*/
       // stdProfileImage = ((QuickDashboardClass) getActivity()).findViewById(R.id.profile_image);


        noRecordsFound = (TextView) subjectView.findViewById(R.id.no_records_found);
        sharedPreferences2 = mContext.getSharedPreferences("studentDetails", MODE_PRIVATE);
        studentId = sharedPreferences2.getString(Constants.studentId, "");
        sectionId = sharedPreferences2.getString(Constants.sectionId, "");
        Log.e("section in subjects",sectionId);


        new AllSubjectBackTask(getActivity(), SubjectsMainFragment.this).execute(sectionId);

      /*  schoolId1 = sharedPreferences2.getString(Constants.schoolId1, "");
        classId = sharedPreferences2.getString(Constants.classId, "");

/*


        sharedPreferences1 = mContext.getSharedPreferences(getString(R.string.appinfo), MODE_PRIVATE);
        loggedEmail = sharedPreferences1.getString(getString(R.string.useridInfo), "");
        loggedPwd = sharedPreferences1.getString(getString(R.string.pwdInfo), "");
        Log.e("username", loggedEmail);
        JSONObject loginObject = new JSONObject();
        try {
            loginObject.put("email", loggedEmail);
            loginObject.put("password", loggedPwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (loginObject.length() > 0) {
            // Validating userid, pwd in server....
            loginBackGroundTask = new ReusableBackGoundTask(this);
            loginBackGroundTask.execute(String.valueOf(loginObject));

        }
*/

/*

        stdNameSpinner = ((QuickDashboardClass) getActivity()).findViewById(R.id.stdNameSpinner);
        studentMap.put(studentId, studentId);

        //    new AllFeeCollectionBackTask(getActivity(), FeeCollectionByStudentFragment.this).execute(String.valueOf(stdNameSpinner));


        stdNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedStudent = stdNameSpinner.getSelectedItem().toString();
              //  studentAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                studentNameKey = (String) getKeyFromValue(studentMap, selectedStudent);
                System.out.println("academy"+studentNameKey);
             //   studentSubjectKey = (String) getKeyFromValue(studentMap1, selectedStudent);


               // Log.e("student spinner data", studentNameKey);
               */
/* int userChoice = stdNameSpinner.getSelectedItemPosition();
                SharedPreferences sharedPref = mContext.getSharedPreferences("FileName",0);
                SharedPreferences.Editor prefEditor = sharedPref.edit();
                prefEditor.putInt("userChoiceSpinner",userChoice);
                prefEditor.commit();
*//*

                if (studentNameKey != null && !studentNameKey.isEmpty()) {
                    new StudentProfileBackTask(mContext, SubjectsMainFragment.this).execute(studentNameKey);
                    new AllSubjectBackTask(getActivity(), SubjectsMainFragment.this).execute(studentSubjectKey);

                    //    Log.e("selected section id",sectionIdStudent);

                    //   new AllFeeCollectionBackTask(mContext, SubjectsMainFragment.this).execute(studentNameKey);
                    //  new StudentProfileBackTask(mContext, SubjectsMainFragment.this).execute(studentNameKey);
                }

             */
/*   Glide.with(mContext)
                        .load(studentsDetails.getImageDisplay())
                        .into(holder.imgUser);
                holder.imgUser.setVisibility(View.VISIBLE);*//*

               */
/* Log.e("studentNamKey",studentNameKey);
                Glide.with(mContext)
                        .load(studentsDetails.getImageDisplay())
                        .into(holder.imgUser);
                holder.imgUser.setVisibility(View.VISIBLE);*//*

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
*/


        //
        recycler_view = (RecyclerView) subjectView.findViewById(R.id.recycler_view);

        recycler_view.setNestedScrollingEnabled(false);


        // to set action bar title....
        //   ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Subjects");

        return subjectView;
    }

    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void OnSubjectResponse(String response) throws JSONException {
        if (response != null && !response.isEmpty()) {
            subjectList.clear();
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("subjects");
            System.out.println("Subject Data" + jsonArray);
            if (jsonArray.length() > 0) {
                int count = 0;
                while (count < jsonArray.length()) {
                    JSONObject subObject = jsonArray.getJSONObject(count);
                    Subject subject = new Subject(subObject.getString("name"), subObject.getString("subject_id"));
                    subjectList.add(subject);
                    count++;
                }


                //
                //Count....
                /*String taskCount = "Subjects (" + String.valueOf(subjectList.size()) + ")";
                textSubCount.setText(taskCount);*/
                //
                recycler_view.setVisibility(View.VISIBLE);

                subjectAdapter = new SubjectAdapter(getActivity(), subjectList);
                recycler_view.setAdapter(subjectAdapter);
                subjectAdapter.setClickListener(this);

                //noRecordsFound.setVisibility(View.GONE);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                recycler_view.setLayoutManager(linearLayoutManager);
                subjectAdapter.notifyDataSetChanged();
                //Sliding Panel
                /*sliding_layout.setParallaxOffset(0);
                sliding_layout.setPanelHeight(120);*/
            } else {
                /*sliding_layout.setParallaxOffset(0);
                sliding_layout.setPanelHeight(0);*/
                // recycler_view.setVisibility(View.GONE);
                // String taskCount = "Subjects";
                // textSubCount.setText(taskCount);
                recycler_view.setVisibility(View.GONE);
                // noRecordsFound.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "No Records Found....!", Toast.LENGTH_SHORT).show();
                //noRecordsFound.setText("No Records Found");

            }
        }


    }

    @Override
    public void onClickSubjectName(View view, int position) {
        final Subject subject = subjectList.get(position);
        String subjectCode = subject.getCode();

        sharedPreferences = mContext.getSharedPreferences("subjectId", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.subjectId, subjectCode);


        editor.commit();
        startActivity(new Intent(mContext, ChaptersActivity.class));

       /* Intent intent = new Intent(mContext, StudentProfileTabsFragment.class);
        mContext.startActivity(intent);*/
        //   setFragment(studentProfileTabsFragment);
        //sendStudentId.sendData(studentName);

        Log.e("subject click", subjectCode);

    }
}


   /* @Override
    public void allPreferedDetails(String output) {
        if (output != null && !output.isEmpty()) {
            String imageName = "";
            //  studentList.clear();
            // studentAdapter1.clear();


            try {
                JSONObject classObject = new JSONObject(output);
                JSONArray classArray = classObject.getJSONArray("users");
                int count = 0;
                while (count < classArray.length()) {
                    JSONObject cObject = classArray.getJSONObject(count);
                    //  if(value1.equals("Depok") && value2.equals("Jakarta"))
                    //
                    studentMap.put(cObject.getString("student_id"), cObject.getString("first_name"));
                   // studentMap1.put(cObject.getString("section_id"), cObject.getString("student_id"));

                    //studentMap.put(cObject.getString("section_id"),cObject.getString("first_name"));

                    //  studentMap.put(studentId, cObject.getString("first_name"));
                    //studentImage
                    if (!cObject.isNull("studentImage")) {
                        JSONArray imageArray = cObject.getJSONArray("studentImage");
                        int p = 0;
                        while (p < imageArray.length()) {
                            JSONObject parentObject = imageArray.getJSONObject(p);
                            imageName = parentObject.getString("filename");
                            p++;
                        }


                    }

                    count++;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            studentList = new ArrayList<String>(studentMap.values());
         //   studentList = new ArrayList<String>(studentMap1.values());

            // Creating adapter for spinner
            studentAdapter1 = new ArrayAdapter<String>(mContext, R.layout.spinner_item, studentList);
            studentAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            stdNameSpinner.setAdapter(studentAdapter1);
               studentAdapter1.notifyDataSetChanged();
        }

    }

    @Override
    public void studentProfile(String result) throws JSONException {
        if (result != null && !result.isEmpty()) {
            String imageName = "";
            String imageurl = null;
            JSONObject student = new JSONObject(result);
            JSONArray jsonArray = student.getJSONArray("students");
            System.out.println("Data" + jsonArray);
            if (jsonArray.length() > 0) {
                int count = 0;
                while (count < jsonArray.length()) {
                    JSONObject jsonObject = jsonArray.getJSONObject(count);
                    studentId = jsonObject.getString("student_id");
                    schoolId = jsonObject.getString("school_id");
                    classId = jsonObject.getString("class_id");
                    sectionIdStudent = jsonObject.getString("section_id");
                    if (sectionIdStudent != null && !sectionIdStudent.isEmpty()) {
                        new AllSubjectBackTask(getActivity(), SubjectsMainFragment.this).execute(sectionIdStudent);
                    }
                    else
                    {

                    }
                  //  Log.e("section id student",selectedStudent);

                    sharedPreferences = mContext.getSharedPreferences("studentDetails", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(Constants.studentId, studentId);
                    editor.putString(Constants.schoolId1, schoolId);
                    editor.putString(Constants.classId, classId);
                    editor.putString(Constants.sectionId, sectionId);

                    editor.apply();

                    Log.e("subjects send data", studentId);
                    if (!jsonObject.isNull("studentImage")) {
                        JSONArray imageArray = jsonObject.getJSONArray("studentImage");
                        int p = 0;
                        while (p < imageArray.length()) {
                            JSONObject parentObject = imageArray.getJSONObject(p);
                            imageName = parentObject.getString("filename");

                            p++;
                        }


                    }

                    imageurl = Constants.singleImage + imageName;
                    count++;

                }
                Glide.with(mContext)
                        .load(imageurl)
                        .into(stdProfileImage);
                stdProfileImage.setVisibility(View.VISIBLE);
            }
        }*/

    /*
    "{
  ""subjects"": [
    {
      ""_id"": ""5926ada2f78c0904ccd5736f"",
      ""subject_id"": ""SCH-9273-CL-2-SEC-1-SUB-1"",
      ""section_id"": ""SCH-9273-CL-2-SEC-1"",
      ""name"": ""Drawing""
    }
  ]
}"
     */
    /*@Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(this).attach(this).commit();
        }

    }*/

