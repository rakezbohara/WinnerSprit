package com.app.rakez.winnersprit.selection;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.StackView;
import android.widget.Toast;

import com.app.rakez.winnersprit.FirebaseHandler.FirebaseHelper;
import com.app.rakez.winnersprit.R;
import com.app.rakez.winnersprit.data.DataRepository;
import com.app.rakez.winnersprit.model.Course;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CourseSelector extends AppCompatActivity {

    @BindView(R.id.course_select_RV)RecyclerView courseSelectRV;
    List<Course> itemCourses;
    AdapterCourse adapterCourse;
    ProgressDialog progressDialog;

    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_course_selector);
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Courses");
        initializeRV();
        getCourses();

    }

    private void initializeRV() {
        itemCourses = new ArrayList<>();
        adapterCourse = new AdapterCourse(this, itemCourses);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        courseSelectRV.setLayoutManager(layoutManager);
        courseSelectRV.setAdapter(adapterCourse);
    }

    void showProgressDialog(){
        if(progressDialog!=null){
            progressDialog.show();
        }
    }
    void hideProgressDialog(){
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
    //Firebase task
    public void getCourses(){
        showProgressDialog();
        databaseReference = FirebaseHelper.getDatabase().getReference("courses");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemCourses.clear();
                for(DataSnapshot contactSnapshot : dataSnapshot.getChildren()){
                    Course course = contactSnapshot.getValue(Course.class);
                    itemCourses.add(course);
                }
                for(int i = 0 ; i <itemCourses.size() ; i++) {
                    Log.d("tag", "Response from firebase " + itemCourses.get(i).getId());
                    Log.d("tag", "Response from firebase " + itemCourses.get(i).getName());
                    Log.d("tag", "Response from firebase " + itemCourses.get(i).getId());
                    Log.d("tag", "Response from firebase " + itemCourses.get(i).getMax_level());
                }
                adapterCourse.notifyDataSetChanged();
                hideProgressDialog();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                hideProgressDialog();
                Toast.makeText(CourseSelector.this, "Error : "+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
