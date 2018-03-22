package com.example.harish.proschoolforparentadvanced.suresh.library;

import android.animation.Animator;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.harish.proschoolforparentadvanced.Constants;
import com.example.harish.proschoolforparentadvanced.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by JANI on 03-06-2017.
 */

public class BookListFragment extends Fragment implements AllBookBackTask.AllBooks{
View bookListView;
    RecyclerView recycler_view;
    ArrayList<Books> booksList = new ArrayList<Books>();
    BookAdapter bookAdapter;
    FloatingActionButton fab;
    FrameLayout frameLayout;
    LinearLayout fabLayout1;
    View fabBGLayout;
    boolean isFABOpen=false;
    TextView noText;
    FloatingActionButton floatingActionButton;
Context mContext;
String schoolId;
String role;
Toolbar toolbar;

    public BookListFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bookListView = inflater.inflate(R.layout.books_list_layout_two, container, false);
        mContext=getActivity();

        toolbar = bookListView.findViewById(R.id.tab_toolabr);
        toolbar.setTitle("Books List");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        recycler_view = (RecyclerView) bookListView.findViewById(R.id.recycler_view);
        noText = (TextView) bookListView.findViewById(R.id.noText);


        SharedPreferences sharedPreferences = mContext.getSharedPreferences("AppInfo", MODE_PRIVATE);
        schoolId = sharedPreferences.getString(Constants.schoolIdPref, "");
        role = sharedPreferences.getString(Constants.rolePref, "");
        new AllBookBackTask(getActivity(), BookListFragment.this).execute(schoolId);


        frameLayout=(FrameLayout)bookListView.findViewById(R.id.frameLayout);




        // to set action bar title....
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Books List");



        return bookListView;
    }



    @Override
    public void allBooks(String result) throws JSONException {
        if (result != null && !result.isEmpty()){
            booksList.clear();
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("books");
            if (jsonArray.length() > 0){
                int count = 0;
                while (count < jsonArray.length()){
                    JSONObject bookObject = jsonArray.getJSONObject(count);
                    Books books = new Books(bookObject.getString("_id"), bookObject.getString("book_id"), bookObject.getString("book_title"),
                            bookObject.getString("author_name"), bookObject.getString("book_price"), bookObject.getString("qty"),
                            bookObject.getString("rack_number"), bookObject.getString("inward_date"), bookObject.getString("book_description"),
                            bookObject.getString("subject"));
                    booksList.add(books);
                    count++;
                }
                bookAdapter = new BookAdapter(getActivity(), booksList);
                recycler_view.setAdapter(bookAdapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                recycler_view.setLayoutManager(linearLayoutManager);
                bookAdapter.notifyDataSetChanged();
            } else {
                recycler_view.setVisibility(View.GONE);
                noText.setVisibility(View.VISIBLE);
            }
        }
    }
}
