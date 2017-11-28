package com.app.rakez.winnersprit.selection;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.StackView;

import com.app.rakez.winnersprit.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CourseSelector extends AppCompatActivity {

    @BindView(R.id.course_select_RV)RecyclerView courseSelectRV;
    List<ItemCourse> itemCourses;
    AdapterCourse adapterCourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_course_selector);
        ButterKnife.bind(this);
        initializeRV();

    }

    private void initializeRV() {
        itemCourses = new ArrayList<>();
        itemCourses.add(new ItemCourse("a25s0","Lok Sewa for Kharidar"));
        itemCourses.add(new ItemCourse("a25s0","A. S. P. course Nepal"));
        itemCourses.add(new ItemCourse("a25s0","Short Name"));
        itemCourses.add(new ItemCourse("a25s0","Another name of course"));
        itemCourses.add(new ItemCourse("a25s0","lomg name to illustrate the scrolling effect of textview"));
        itemCourses.add(new ItemCourse("a25s0","A. S. P. course Nepal"));
        itemCourses.add(new ItemCourse("a25s0","Short Name"));
        itemCourses.add(new ItemCourse("a25s0","Another name of course"));
        adapterCourse = new AdapterCourse(this, itemCourses);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        courseSelectRV.setLayoutManager(layoutManager);
        courseSelectRV.setAdapter(adapterCourse);
    }

}
