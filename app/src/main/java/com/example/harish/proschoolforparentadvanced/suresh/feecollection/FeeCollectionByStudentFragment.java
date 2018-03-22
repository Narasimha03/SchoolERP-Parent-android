package com.example.harish.proschoolforparentadvanced.suresh.feecollection;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
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


import static android.content.Context.MODE_PRIVATE;




public class FeeCollectionByStudentFragment extends Fragment implements

    AllFeeCollectionBackTask.AllFeeCollection{
    View examEvalView;
    //
    SlidingUpPanelLayout sliding_layout;
    //Student Layout
    LinearLayout exam_layout;
    TextView textExamCount;
    FrameLayout frameLayout;
    //
    RecyclerView recycler_view;
    ArrayList<FeeCollection> listMarks = new ArrayList<>();
    FeeCollectionAdapter feeCollectionAdapter;
    Context mContext;
    SharedPreferences sharedPreferences2;
    String studentId;
    Toolbar toolbar;


    public FeeCollectionByStudentFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        examEvalView = inflater.inflate(R.layout.all_fee_collection_layout_two, container, false);
        mContext = getActivity();
       // sliding_layout = examEvalView.findViewById(R.id.sliding_layout);
      //  sliding_layout.setParallaxOffset(0);
       // sliding_layout.setPanelHeight(0);
      //  exam_layout = examEvalView.findViewById(R.id.std_layout);
      //  textExamCount = examEvalView.findViewById(R.id.textStdCount);

        toolbar = examEvalView.findViewById(R.id.tab_toolabr);
        toolbar.setTitle("Fee Collection");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

       frameLayout = examEvalView.findViewById(R.id.frameLayout);
        sharedPreferences2 = mContext.getSharedPreferences("studentDetails", MODE_PRIVATE);
        studentId = sharedPreferences2.getString(Constants.studentId, "");
        Log.e("student For Shared Data",studentId);
        new AllFeeCollectionBackTask(getActivity(), FeeCollectionByStudentFragment.this).execute(String.valueOf(studentId));
        recycler_view = examEvalView.findViewById(R.id.recycler_view);
        recycler_view.setNestedScrollingEnabled(false);
        // to set action bar title....
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Fee Collection");
        return examEvalView;
    }


    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frameLayout, fragment)
                //.addToBackStack(null)
                .commit();
    }


    @Override
    public void allFeeCollection(String result) throws JSONException {
        if (result != null && !result.isEmpty()) {
            listMarks.clear();
            JSONObject jsonObject = new JSONObject(result);
            System.out.println("feecollectionobject" + jsonObject);
            JSONArray jsonArray = jsonObject.getJSONArray("student_fee_details");

            //JSONArray jsonArray = jsonObject.getJSONArray(examScheduleKey + "-" + sectionKey);
            System.out.println("alleval" + jsonArray);


            if (jsonArray.length() > 0) {
                int count = 0;
                while (count < jsonArray.length()) {
                    JSONObject markObject = jsonArray.getJSONObject(count);

                    String a;
                    String b;
                    String c;
                    String d;
                    int total;
                    String totalValue;
                   a = markObject.getString("fee_amount");
                    b = markObject.getString("fine");
                    c = markObject.getString("discount");
                    d = markObject.getString("fee_paid");
                    String currentDate = markObject.getString("current_date");

                    String date=currentDate.substring(0,10);

                    int fine = 0;
                    int discount=0;
                    int feePaid=0;
                    if (b != null && !b.isEmpty()) {
                        fine = Integer.parseInt(b);

                    }

                    if (c != null && !c.isEmpty()) {
                        discount = Integer.parseInt(c);

                    }

                    if (d!= null && !d.isEmpty()) {
                        feePaid = Integer.parseInt(d);

                    }
                    total = ((feePaid + fine) - discount);
                     totalValue = String.valueOf(total);
                    System.out.println("total" + totalValue);

                    FeeCollection feeCollectionmodel = new FeeCollection(date, markObject.getString("fee_category"), markObject.getString("payment_mode"), markObject.getString("fee_amount"), markObject.getString("fine"), markObject.getString("discount"), totalValue, markObject.getString("fee_paid"));
                    listMarks.add(feeCollectionmodel);
                    count++;
                }
                //Count....
             //   String taskCount = "FeeCollection (" + String.valueOf(listMarks.size()) + ")";
             //   textExamCount.setText(taskCount);
                //
                recycler_view.setVisibility(View.VISIBLE);
                feeCollectionAdapter = new FeeCollectionAdapter(getActivity(), listMarks);
                recycler_view.setAdapter(feeCollectionAdapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                recycler_view.setLayoutManager(linearLayoutManager);
                feeCollectionAdapter.notifyDataSetChanged();
                //
              //  sliding_layout.setParallaxOffset(0);
              //  sliding_layout.setPanelHeight(120);
            } else {
              //  sliding_layout.setParallaxOffset(0);
             //   sliding_layout.setPanelHeight(0);
                recycler_view.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "No Records Found....!", Toast.LENGTH_LONG).show();
            }
        }

    }

}