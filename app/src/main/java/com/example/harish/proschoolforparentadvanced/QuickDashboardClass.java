package com.example.harish.proschoolforparentadvanced;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.harish.proschoolforparentadvanced.suresh.LoginActivity;
import com.example.harish.proschoolforparentadvanced.suresh.academics.AcademicsTabsFragment;
import com.example.harish.proschoolforparentadvanced.suresh.academics.Subject;
import com.example.harish.proschoolforparentadvanced.suresh.academics.SubjectAdapter;
import com.example.harish.proschoolforparentadvanced.suresh.assignments.AllAssignmentFragment;
import com.example.harish.proschoolforparentadvanced.suresh.dashboard.TimeTableDashBoardAdapter;
import com.example.harish.proschoolforparentadvanced.suresh.dashboard.TimeTableDashBoardModel;
import com.example.harish.proschoolforparentadvanced.suresh.dashboard.TimeTablePresentDayBackTask;
import com.example.harish.proschoolforparentadvanced.suresh.dashboard.presentdaystatus.StudentPresentDayBackTask;
import com.example.harish.proschoolforparentadvanced.suresh.examinations.ExaminationTabsFragment;
import com.example.harish.proschoolforparentadvanced.suresh.feecollection.FeeCollectionByStudentFragment;
import com.example.harish.proschoolforparentadvanced.suresh.library.BookListFragment;
import com.example.harish.proschoolforparentadvanced.suresh.noticeboard.AdminDashboardEventAdapter;
import com.example.harish.proschoolforparentadvanced.suresh.noticeboard.AdminDashboardEventClass;
import com.example.harish.proschoolforparentadvanced.suresh.noticeboard.AdminDashboardNoticeBoardAdapter;
import com.example.harish.proschoolforparentadvanced.suresh.noticeboard.AdminDashboardNoticeBoardClass;
import com.example.harish.proschoolforparentadvanced.suresh.studentprofile.AllAttendanceReportsMonthBackTaskForGraph;
import com.example.harish.proschoolforparentadvanced.suresh.studentprofile.ProfileModuleTabsFragment;
import com.example.harish.proschoolforparentadvanced.suresh.timetable.TimeTableClassSection;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.example.harish.proschoolforparentadvanced.MultiSelectionSpinner.getKeyFromValue;
import static com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState.COLLAPSED;


/**
 * Created by Harish on 08-02-2018.
 */

public class QuickDashboardClass extends AppCompatActivity implements QuickDashboardRecyclerViewAdapter.ItemListener,AsyncResponse,StudentProfileBackTask.StudentProfile,WordQuoteBackgroundTask.WordQuoteResponse,TextToSpeech.OnInitListener ,SchoolEventsBackTask.SchoolEvents,TimeTablePresentDayBackTask.TimeTablePresentDay,
        NoticeBoardBackTask.NoticeBoard,StudentPresentDayBackTask.StudentPresentDay,AllAttendanceReportsMonthBackTaskForGraph.AllFeeCollection {

    ArrayList<QuickDashboardDataModel> allItems = new ArrayList<>();
    private SlidingUpPanelLayout mLayout;
    RecyclerView recyclerView;
    private static final int TIME_DELAY = 2000;
    private static long back_pressed;
    CircleImageView stdProfileImage;
    // student Name Spinner....
    String studentNameKey, selectedStudent;
    Spinner stdNameSpinner;
    Map<String, String> studentMap = new LinkedHashMap<String, String>();
    ArrayList<String> studentList;
    ArrayAdapter<String> studentAdapter;

    String loggedEmail;
    String loggedPwd;

    String studentId;
    String classId;
    String sectionId;
    SharedPreferences sharedPreferences, sharedPreferences1;
    TextView textQuote, textWord, wordMeaningText;
    ImageView imgSound;


    ReusableBackGoundTask reusableBackGoundTask;


    Context mContext = this;
    String schoolId;
    FrameLayout frameLayout;


    private TextToSpeech tts;

    //noticeboard

    private List<AdminDashboardNoticeBoardClass> adminNoticeList = new ArrayList<>();
    private AdminDashboardNoticeBoardAdapter mAdminNoticeAdapter;
    RecyclerView noticeRecyclerView;

    // events
    RecyclerView eventRecyclerView;
    private List<AdminDashboardEventClass> adminEventList = new ArrayList<>();
    private AdminDashboardEventAdapter mAdminEventAdapter;

    //TimeTable
    int dayOfWeek;
    String date;
    RecyclerView curriculum_recycler_view;
    TextView tvClassName;
    private ArrayList<TimeTableDashBoardModel> timeTableList = new ArrayList<>();
    private TimeTableDashBoardAdapter timeTableDashBoardAdapter;


    //Profile
    TextView tvAdmissionNo, tvClass, tvSection, tvRollNo, tvTotalPresent, tvTotalAbsent, tvTotalLeave, tvTodayStatus, tvStdName;
    CircleImageView studentImgProfile;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_dashboard_layout);

        SharedPreferences sharedPreferences1 = mContext.getSharedPreferences("AppInfo", MODE_PRIVATE);
        schoolId = sharedPreferences1.getString(Constants.schoolIdPref, "");
        //role = sharedPreferences.getString(Constants.rolePref, "");
        new WordQuoteBackgroundTask(this, QuickDashboardClass.this).execute(schoolId);
        new SchoolEventsBackTask(getApplicationContext(), this).execute(schoolId);
        new NoticeBoardBackTask(getApplicationContext(), this).execute(schoolId);
        textQuote = findViewById(R.id.textQuote);
        wordMeaningText = findViewById(R.id.wordMeaning);
        textWord = findViewById(R.id.textWord);
        imgSound = findViewById(R.id.imgSound);

        imgSound.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                tts = new TextToSpeech(mContext, QuickDashboardClass.this);

                speakOut();
            }

        });


        date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        System.out.println("Today Date" + date);

        Calendar calendar = Calendar.getInstance();
        dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println("day of Week" + dayOfWeek);


// profile student
        tvAdmissionNo = findViewById(R.id.tvAdmissionNo);
        tvClass = findViewById(R.id.tvClass);
        tvSection = findViewById(R.id.tvSection);
        tvRollNo = findViewById(R.id.tvRollNo);
        tvTotalPresent = findViewById(R.id.tvTotalPresent);
        tvTotalAbsent = findViewById(R.id.tvTotalAbsent);
        tvTotalLeave = findViewById(R.id.tvTotalLeave);
        tvAdmissionNo = findViewById(R.id.tvAdmissionNo);
        tvStdName = findViewById(R.id.tvStdName);
        tvTodayStatus = findViewById(R.id.tvTodayStatus);

        studentImgProfile = findViewById(R.id.cirImageProfile);


        SharedPreferences studentPref = mContext.getSharedPreferences("studentDetails", MODE_PRIVATE);
        studentId = studentPref.getString(Constants.studentId, "");
        sectionId = studentPref.getString(Constants.sectionId, "");
        Log.e("student Data in Dash", sectionId);


        frameLayout = findViewById(R.id.frameLayout);
        sharedPreferences = this.getSharedPreferences(getString(R.string.appinfo), MODE_PRIVATE);
        loggedEmail = sharedPreferences.getString(getString(R.string.useridInfo), "");
        loggedPwd = sharedPreferences.getString(getString(R.string.pwdInfo), "");
        Log.e("email", loggedEmail + " -- " + loggedPwd);


        JSONObject loginObject = new JSONObject();
        try {
            loginObject.put("email", loggedEmail);
            loginObject.put("password", loggedPwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (loginObject.length() > 0) {
            // Validating userid, pwd in server....
            reusableBackGoundTask = new ReusableBackGoundTask(mContext, this);
            reusableBackGoundTask.execute(String.valueOf(loginObject));

        }
        // stdProfileImage = findViewById(R.id.profile_image);
        // stdProfileImage.setVisibility(View.GONE);

        stdNameSpinner = findViewById(R.id.stdNameSpinner);

        stdNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedStudent = stdNameSpinner.getSelectedItem().toString();
                studentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                studentNameKey = (String) getKeyFromValue(studentMap, selectedStudent);
                //  name[0] =studentNameKey;
                // studentNameKey=stdId;

              /*  int userChoice = stdNameSpinner.getSelectedItemPosition();
                Log.e("position", String.valueOf(userChoice));
                SharedPreferences sharedPref = getSharedPreferences("FileName",0);
                SharedPreferences.Editor prefEditor = sharedPref.edit();
                prefEditor.putInt("userChoiceSpinner",userChoice);
                prefEditor.commit();*/

                if (studentNameKey != null && !studentNameKey.isEmpty()) {
                    new StudentProfileBackTask(mContext, QuickDashboardClass.this).execute(studentNameKey);
                }


/*
             *//**//*   Glide.with(mContext)
                        .load(studentsDetails.getImageDisplay())
                        .into(holder.imgUser);
                holder.imgUser.setVisibility(View.VISIBLE);*//**//*
               *//**//* Log.e("studentNamKey",studentNameKey);
                Glide.with(mContext)
                        .load(studentsDetails.getImageDisplay())
                        .into(holder.imgUser);
                holder.imgUser.setVisibility(View.VISIBLE);*//**//**/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //noticeboard

        noticeRecyclerView = (RecyclerView) findViewById(R.id.notice_board_recycler_view);

        mAdminNoticeAdapter = new AdminDashboardNoticeBoardAdapter(adminNoticeList);
        RecyclerView.LayoutManager mAdminNoticeLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        noticeRecyclerView.setLayoutManager(mAdminNoticeLayoutManager);
        noticeRecyclerView.setItemAnimator(new DefaultItemAnimator());
        noticeRecyclerView.setAdapter(mAdminNoticeAdapter);

        //events
        eventRecyclerView = (RecyclerView) findViewById(R.id.events_recycler_view);
        mAdminEventAdapter = new AdminDashboardEventAdapter(adminEventList);
        RecyclerView.LayoutManager mAdminEventLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        eventRecyclerView.setLayoutManager(mAdminEventLayoutManager);
        eventRecyclerView.setItemAnimator(new DefaultItemAnimator());
        eventRecyclerView.setAdapter(mAdminEventAdapter);

        //timetable
        curriculum_recycler_view = (RecyclerView) findViewById(R.id.curriculum_recycler_view);
        tvClassName = (TextView) findViewById(R.id.tvClassName);


//toolbar
        Toolbar toolbar = findViewById(R.id.test_toolabr);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Dashboard");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = findViewById(R.id.recycler_view_sliding);
        mLayout = findViewById(R.id.sliding_layout);
        allItems.add(new QuickDashboardDataModel("Profile", R.drawable.attendance, R.mipmap
                .icon_one));
        allItems.add(new QuickDashboardDataModel("Academics", R.drawable.academic, R.mipmap
                .icon_two));
        allItems.add(new QuickDashboardDataModel("Assignments", R.drawable.assignment, R.drawable.icon_eleven));
        allItems.add(new QuickDashboardDataModel("Examinations", R.drawable.examination, R.drawable.icon_examinations));
        allItems.add(new QuickDashboardDataModel("Time Table", R.drawable.scheduler, R.drawable.icon_six));
        allItems.add(new QuickDashboardDataModel("Fee", R.drawable.fee, R.drawable.icon_fee));
        allItems.add(new QuickDashboardDataModel("Conduct Card", R.drawable.conduct_card, R
                .mipmap.icon_seven));
        allItems.add(new QuickDashboardDataModel("Bus Locator", R.drawable.bus_locator, R.mipmap
                .icon_eight));
        allItems.add(new QuickDashboardDataModel("IQ Plus", R.drawable.iq_plus, R.mipmap
                .icon_nine));
        allItems.add(new QuickDashboardDataModel("Library", R.drawable.library, R.mipmap.icon_ten));
        allItems.add(new QuickDashboardDataModel("Quiz", R.drawable.quiz, R.mipmap.icon_eleven));
        allItems.add(new QuickDashboardDataModel("E Connect", R.drawable.e_connect, R.mipmap
                .icon_twelve));

        QuickDashboardRecyclerViewAdapter adapter = new QuickDashboardRecyclerViewAdapter(this, allItems, this);
        recyclerView.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);


    }

    @Override
    public void onItemClick(QuickDashboardDataModel item) {

        if (item.text == "Profile") {

            mLayout.setPanelState(COLLAPSED);
//            stdProfileImage.setVisibility(View.GONE);
            stdNameSpinner.setVisibility(View.GONE);
            ProfileModuleTabsFragment profileModuleTabsFragment = new ProfileModuleTabsFragment();
            setFragment(profileModuleTabsFragment);
            // Intent profileModuleTabsFragment = new Intent(this,MainActivity.class);
            // startActivity(profileModuleTabsFragment);
        }

        if (item.text == "Academics") {

            mLayout.setPanelState(COLLAPSED);
            //   stdProfileImage.setVisibility(View.GONE);
            stdNameSpinner.setVisibility(View.GONE);

            AcademicsTabsFragment academicsTabsFragment = new AcademicsTabsFragment();
            setFragment(academicsTabsFragment);

        }
        if (item.text == "Assignments") {

            mLayout.setPanelState(COLLAPSED);

            //   stdProfileImage.setVisibility(View.GONE);
            stdNameSpinner.setVisibility(View.GONE);

            AllAssignmentFragment allAssignmentFragment = new AllAssignmentFragment();
            setFragment(allAssignmentFragment);

        }

        if (item.text == "Examinations") {

            mLayout.setPanelState(COLLAPSED);

            ExaminationTabsFragment examinationTabsFragment = new ExaminationTabsFragment();
            setFragment(examinationTabsFragment);

        }

        if (item.text == "Time Table") {

            mLayout.setPanelState(COLLAPSED);
            //  stdProfileImage.setVisibility(View.GONE);
            stdNameSpinner.setVisibility(View.GONE);

            TimeTableClassSection examinationTabsFragment = new TimeTableClassSection();
            setFragment(examinationTabsFragment);

        }

        if (item.text == "Fee") {

            mLayout.setPanelState(COLLAPSED);
            //     stdProfileImage.setVisibility(View.GONE);
            stdNameSpinner.setVisibility(View.GONE);
            FeeCollectionByStudentFragment feeCollectionByStudentFragment = new FeeCollectionByStudentFragment();
            setFragment(feeCollectionByStudentFragment);

        }

        if (item.text == "Library") {

            mLayout.setPanelState(COLLAPSED);
            //  stdProfileImage.setVisibility(View.GONE);
            stdNameSpinner.setVisibility(View.GONE);

            BookListFragment bookListFragment = new BookListFragment();
            setFragment(bookListFragment);

        }

    }

    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .addToBackStack(null)
                .commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        /*if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        } else*/

        if (id == R.id.logout) {
            //Context mContext = getActivity() ;
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle("Log Out");
            builder.setMessage("Are you sure want to Log out?");
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    final ProgressDialog progressDialog = ProgressDialog.show(mContext, "Please wait...", "logging out...", true);
                    Thread thread = new Thread() {
                        public void run() {
                            try {
                                Thread.sleep(1000);
                                SharedPreferences sharedPreferences = mContext.getSharedPreferences(getString(R.string.appinfo), MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.clear();
                                editor.apply();
                                Intent intent = new Intent(mContext, LoginActivity.class);
                                startActivity(intent);
                                progressDialog.dismiss();
                                finish();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    thread.start();
                }
            });
            builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void allPreferedDetails(String output) {
        if (output != null && !output.isEmpty()) {
            String imageName = "";

            try {
                JSONObject classObject = new JSONObject(output);
                JSONArray classArray = classObject.getJSONArray("users");
                int count = 0;
                while (count < classArray.length()) {
                    JSONObject cObject = classArray.getJSONObject(count);
                    studentMap.put(cObject.getString("student_id"), cObject.getString("first_name"));

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
            // Creating adapter for spinner
            studentAdapter = new ArrayAdapter<String>(mContext, R.layout.spinner_item, studentList);
            studentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            stdNameSpinner.setAdapter(studentAdapter);
            studentAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void studentProfile(String result) throws JSONException {

        if (result != null && !result.isEmpty()) {
            String imageName = "";
            String imageurl = null;
            JSONObject student = new JSONObject(result);
            JSONArray jsonArray = student.getJSONArray("students");
            System.out.println("Data profile" + jsonArray);
            if (jsonArray.length() > 0) {
                int count = 0;
                while (count < jsonArray.length()) {
                    JSONObject jsonObject = jsonArray.getJSONObject(count);
                    studentId = jsonObject.getString("student_id");
                    schoolId = jsonObject.getString("school_id");
                    classId = jsonObject.getString("class_id");
                    sectionId = jsonObject.getString("section_id");


                    new TimeTablePresentDayBackTask(getApplicationContext(), this).execute(String.valueOf(dayOfWeek), sectionId);
                    new StudentPresentDayBackTask(getApplicationContext(), this).execute(date, studentId);

                    new AllAttendanceReportsMonthBackTaskForGraph(getApplicationContext(), this).execute(studentId);

                    sharedPreferences1 = mContext.getSharedPreferences("studentDetails", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences1.edit();
                    editor.putString(Constants.studentId, studentId);
                    editor.putString(Constants.schoolId1, schoolId);
                    editor.putString(Constants.classId, classId);
                    editor.putString(Constants.sectionId, sectionId);

                    editor.apply();

                    Log.e("student_id dashboard", studentId);
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
             /*   Glide.with(mContext)
                        .load(imageurl)
                        .into(stdProfileImage);
                stdProfileImage.setVisibility(View.VISIBLE);*/
            }

        }
    }

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                imgSound.setEnabled(true);
                speakOut();
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }


    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }


    private void speakOut() {

        String text = textWord.getText().toString();
        Log.e("word check", text);

        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        tts.setPitch(0.6f);

    }


    @Override
    public void onWordQuoteResponse(String response) throws JSONException {

        if (response != null && !response.isEmpty()) {
            //   listPaper.clear();
            JSONObject jsonObject = new JSONObject(response);
            System.out.println("Quote" + jsonObject);
            //   JSONArray jsonArray = jsonObject.getJSONArray(examScheduleKey + "-" + subjectkey);


            JSONArray jsonArray = jsonObject.getJSONArray("Quotes");
            System.out.println("exam paper" + jsonArray);
            if (jsonArray.length() > 0) {
                int count = 0;
                while (count < jsonArray.length()) {
                    JSONObject examObject = jsonArray.getJSONObject(count);
                    String quote = examObject.getString("quote") + " ~ " + examObject.getString("quoteWritten");
                    String word = examObject.getString("word");
                    String wordMeaning = examObject.getString("wordWritten");
                    textQuote.setText(quote);
                    textWord.setText(word);
                    wordMeaningText.setText(wordMeaning);
                    count++;
                }
                //Count....


            }
        }
    }


    @Override
    public void noticeBoard(String noticeBoard) throws JSONException {

        if (noticeBoard != null && !noticeBoard.isEmpty()) {
            String stdName;
            adminNoticeList.clear();
            JSONObject jsonObject = new JSONObject(noticeBoard);
            System.out.println("Schools" + jsonObject);
            JSONArray jsonArray = jsonObject.getJSONArray("messages");

            //JSONArray jsonArray = jsonObject.getJSONArray(examScheduleKey + "-" + sectionKey);
            System.out.println("noticeboard data:" + jsonArray);


            if (jsonArray.length() > 0) {
                int count = 0;
                while (count < jsonArray.length()) {
                    JSONObject markObject = jsonArray.getJSONObject(count);

                    AdminDashboardNoticeBoardClass adminNoticeText = new AdminDashboardNoticeBoardClass(markObject.getString("date"), markObject.getString("time"), markObject.getString("subject"), markObject.getString("messages"));
                    adminNoticeList.add(adminNoticeText);


                    count++;
                }
                mAdminNoticeAdapter.notifyDataSetChanged();

            } else {
                // sliding_layout.setParallaxOffset(0);
                //  sliding_layout.setPanelHeight(0);
                //  recycler_view.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "No Records In NoticeBoard....!", Toast.LENGTH_SHORT).show();
            }


        }

    }

    @Override
    public void schoolevents(String schoolevents) throws JSONException {

        if (schoolevents != null && !schoolevents.isEmpty()) {
            adminEventList.clear();
            JSONObject jsonObject = new JSONObject(schoolevents);
            System.out.println("Schools" + jsonObject);
            JSONArray jsonArray = jsonObject.getJSONArray("school_events");

            //JSONArray jsonArray = jsonObject.getJSONArray(examScheduleKey + "-" + sectionKey);
            System.out.println("school events:" + jsonArray);


            if (jsonArray.length() > 0) {
                int count = 0;
                while (count < jsonArray.length()) {
                    JSONObject markObject = jsonArray.getJSONObject(count);

                    AdminDashboardEventClass adminEventTotal = new AdminDashboardEventClass(markObject.getString("date"), markObject.getString("event_title"));
                    adminEventList.add(adminEventTotal);

                    count++;
                }
                mAdminEventAdapter.notifyDataSetChanged();

            } else {

                Toast.makeText(getApplicationContext(), "No Records In Events....!", Toast.LENGTH_SHORT).show();
            }

        }


    }

    @Override
    public void timeTablePresentDay(String result) throws JSONException {

        if (result != null && !result.isEmpty()) {
            timeTableList.clear();
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("timetable");
            System.out.println("Subject Data" + jsonArray);
            if (jsonArray.length() > 0) {
                int count = 0;
                while (count < jsonArray.length()) {
                    JSONObject subObject = jsonArray.getJSONObject(count);
                    TimeTableDashBoardModel timeTableDashBoardModel = new TimeTableDashBoardModel(subObject.getString("start_time"), subObject.getString("subject_name"));
                    timeTableList.add(timeTableDashBoardModel);
                    count++;
                }


                //
                //Count....
                /*String taskCount = "Subjects (" + String.valueOf(subjectList.size()) + ")";
                textSubCount.setText(taskCount);*/
                //
                curriculum_recycler_view.setVisibility(View.VISIBLE);

                timeTableDashBoardAdapter = new TimeTableDashBoardAdapter(mContext, timeTableList);
                curriculum_recycler_view.setAdapter(timeTableDashBoardAdapter);
                //    timeTableDashBoardAdapter.setClickListener(this);

                //noRecordsFound.setVisibility(View.GONE);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
                curriculum_recycler_view.setLayoutManager(linearLayoutManager);
                timeTableDashBoardAdapter.notifyDataSetChanged();
                //Sliding Panel
                /*sliding_layout.setParallaxOffset(0);
                sliding_layout.setPanelHeight(120);*/
            } else {
                /*sliding_layout.setParallaxOffset(0);
                sliding_layout.setPanelHeight(0);*/
                // recycler_view.setVisibility(View.GONE);
                // String taskCount = "Subjects";
                // textSubCount.setText(taskCount);
                curriculum_recycler_view.setVisibility(View.GONE);
                // noRecordsFound.setVisibility(View.VISIBLE);
                Toast.makeText(mContext, "No Records Found....!", Toast.LENGTH_SHORT).show();
                //noRecordsFound.setText("No Records Found");

            }

        }
    }

    @Override
    public void studentPresentDay(String result) throws JSONException {


        if (result != null && !result.isEmpty()) {
            String imageNameData = "";
            String admissionNo;
            String className = null;
            String sectionName;
            String rollNo;
            String studentName;
            String status;

            String imageurlStudent = null;
            JSONObject student = new JSONObject(result);
            JSONArray jsonArray = student.getJSONArray("studentAttendence");
            System.out.println("present Day" + jsonArray);
            if (jsonArray.length() > 0) {
                int count = 0;
                while (count < jsonArray.length()) {
                    JSONObject jsonObject = jsonArray.getJSONObject(count);

                    admissionNo = jsonObject.getString("Admission_no");
                    // className = jsonObject.getString("school_id");
                    sectionName = jsonObject.getString("section_name");
                    rollNo = jsonObject.getString("roll_no");
                    studentName = jsonObject.getString("student_name").toUpperCase();
                    status = jsonObject.getString("status");
                    tvAdmissionNo.setText(admissionNo);
                    tvClass.setText(className);
                    tvSection.setText(sectionName);
                    tvRollNo.setText(rollNo);
                    tvStdName.setText(studentName);
                    tvTodayStatus.setText(status);


                    Log.e("student_id dashboard", studentId);
                    if (!jsonObject.isNull("studentImage")) {
                        JSONArray imageArray = jsonObject.getJSONArray("studentImage");
                        int p = 0;
                        while (p < imageArray.length()) {
                            JSONObject parentObject = imageArray.getJSONObject(p);
                            imageNameData = parentObject.getString("filename");

                            p++;
                        }


                    }

                    imageurlStudent = Constants.singleImage + imageNameData;
                    count++;

                }
                Glide.with(mContext)
                        .load(imageurlStudent)
                        .into(studentImgProfile);
                studentImgProfile.setVisibility(View.VISIBLE);
            }

        }


    }

    @Override
    public void allFeeCollection(String result) throws JSONException {
        if (result != null && !result.isEmpty()) {

            String totalAttCount;
            String totalPresent;
            String totalAbsent;
            String totalLeave;

            String imageurlStudent = null;
            JSONObject student = new JSONObject(result);
            totalAttCount = student.getString("totalDays");
            totalPresent = student.getString("totalPresent");
            totalAbsent = student.getString("totalAbsent");
            totalLeave = student.getString("totalOnLeave");
            tvTotalPresent.setText(totalPresent + "/" + totalAttCount);
            tvTotalAbsent.setText(totalAbsent + "/" + totalAttCount);
            tvTotalLeave.setText(totalLeave + "/" + totalAttCount);

            //JSONArray jsonArray = student.getJSONArray("studentAttendence");
        }
    }


    @Override
    public void onBackPressed() {

        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {

            super.onBackPressed();
            return;


        }
        /*else {

            *//*Intent intent = new Intent(QuickDashboardClass.this, QuickDashboardClass.class);

            intent.addCategory(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);*//*

        }*/
        Intent intent = new Intent(QuickDashboardClass.this, QuickDashboardClass.class);

        intent.addCategory(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        Toast.makeText(getBaseContext(), "Press Once Again to Exit!",
                Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
        finish();

    }
}
