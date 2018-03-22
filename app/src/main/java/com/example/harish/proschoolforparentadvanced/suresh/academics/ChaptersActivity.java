package com.example.harish.proschoolforparentadvanced.suresh.academics;

import android.animation.Animator;
import android.content.Context;

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
import android.view.View;

import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.harish.proschoolforparentadvanced.Constants;
import com.example.harish.proschoolforparentadvanced.R;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;



public class ChaptersActivity extends AppCompatActivity  implements  AllChapterBackTask.ChapterResponse {

    Toolbar toolbar;

    //
    SlidingUpPanelLayout sliding_layout;
    //Student Layout
    LinearLayout chapter_layout;
    TextView textChapterCount;

    // Subject Spinner...
    String selectedSubject;

    RecyclerView recycler_view;
    ArrayList<Chapters> listChapters = new ArrayList<Chapters>();
    ChapterAdapter chapterAdapter;
    FrameLayout frameLayout;
    LinearLayout fabLayout1;
    FloatingActionButton fab;
    View fabBGLayout;
    Context mContext;
    String schoolId;
    boolean isFABOpen = false;
    String role;
    String subjectId;
    SharedPreferences sharedPreferences1;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.all_chapter_layout_two_three);
        // toolbar = (Toolbar) findViewById(R.id.test_toolbar);
        // setSupportActionBar(toolbar);

        Toolbar toolbar = findViewById(R.id.test_toolabr);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Chapters");


        mContext = this;
        /*sliding_layout = (SlidingUpPanelLayout)findViewById(R.id.sliding_layout);
        sliding_layout.setParallaxOffset(0);
        sliding_layout.setPanelHeight(0);*/


        sharedPreferences1 = mContext.getSharedPreferences("subjectId", MODE_PRIVATE);
        subjectId = sharedPreferences1.getString(Constants.subjectId, "");
        new AllChapterBackTask(mContext, ChaptersActivity.this).execute(subjectId);

        frameLayout = (FrameLayout)findViewById(R.id.frameLayout);




        //noRecordsFound=(TextView) findViewById(R.id.no_records_found);

        /*chapter_layout = (LinearLayout)findViewById(R.id.chapter_layout);
        textChapterCount = (TextView)findViewById(R.id.textChapterCount);*/
        // class spinner....

        //

        recycler_view = (RecyclerView)findViewById(R.id.recycler_view);
        recycler_view.setNestedScrollingEnabled(false);
    }



    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void ChapterResponse(String response) throws JSONException {
        if (response != null && !response.isEmpty()) {
            listChapters.clear();
            JSONObject jsonObject = new JSONObject(response);
            System.out.println("chapters jsonObject" + jsonObject);

            JSONArray jsonArray = jsonObject.getJSONArray("chapters");
            System.out.println("chapters" + jsonArray);
            if (jsonArray.length() > 0) {

                int count = 0;
                while (count < jsonArray.length()) {
                    JSONObject chapObject = jsonArray.getJSONObject(count);
                    Chapters chapters = new Chapters(chapObject.getString("_id"), chapObject.getString("lession_id"), selectedSubject,
                            chapObject.getString("title"), chapObject.getString("chapter_code"), chapObject.getString("no_of_topics"),
                            chapObject.getString("description"), chapObject.getString("status"));
                    listChapters.add(chapters);
                    count++;
                }
                //Count....
                /*String taskCount = "Chapters (" + String.valueOf(listChapters.size()) + ")";
                textChapterCount.setText(taskCount);*/
                // textChapterCount.setVisibility(View.VISIBLE);
                recycler_view.setVisibility(View.VISIBLE);

                //
                chapterAdapter = new ChapterAdapter(mContext, listChapters);
                recycler_view.setAdapter(chapterAdapter);
                //noRecordsFound.setVisibility(View.GONE);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                recycler_view.setLayoutManager(linearLayoutManager);
                chapterAdapter.notifyDataSetChanged();
                //
                /*sliding_layout.setParallaxOffset(0);
                sliding_layout.setPanelHeight(120);*/
            } else {
                /*sliding_layout.setParallaxOffset(0);
                sliding_layout.setPanelHeight(0);*/
                // String taskCount = "Chapters";
                //  textChapterCount.setText(taskCount);
                //  recycler_view.setVisibility(View.GONE);
                // noRecordsFound.setVisibility(View.VISIBLE);
                // noRecordsFound.setText("No Records Found");
                Toast.makeText(mContext, "No Records Found....!", Toast.LENGTH_SHORT).show();
                //recycler_view.setVisibility(View.GONE);
            }
        }
    }
}