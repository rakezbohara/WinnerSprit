package com.app.rakez.winnersprit.selection;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.rakez.winnersprit.R;
import com.app.rakez.winnersprit.data.SharedPref;
import com.app.rakez.winnersprit.quiz.MainContainer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by RAKEZ on 11/27/2017.
 */

public class AdapterCourse extends RecyclerView.Adapter<AdapterCourse.MyViewHolder> {

    private Context context;
    private List<ItemCourse> itemCourses;
    SharedPref sharedPref;

    public AdapterCourse(Context context, List<ItemCourse> itemCourses) {
        this.context = context;
        this.itemCourses = itemCourses;
        sharedPref = new SharedPref(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ItemCourse itemCourse  =itemCourses.get(position);
        holder.bigLetterTV.setText(itemCourse.getCourseName().substring(0,1));
        holder.courseName.setText(itemCourse.getCourseName());
        holder.courseName.setSelected(true);
        if (position%5==0){
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.rv1));
        }else if(position%5==1){
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.rv2));
        }else if(position%5==2){
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.rv3));
        }else if(position%5==4){
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.rv4));
        }else{
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.rv5));
        }
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPref.saveData("course_id",itemCourse.getCourseId());
                sharedPref.saveData("course_name",itemCourse.getCourseName());
                Intent in = new Intent(context, MainContainer.class);
                context.startActivity(in);
                ((CourseSelector)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemCourses.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public View view;
        @BindView(R.id.item_course_CV) CardView  cardView;
        @BindView(R.id.item_course_big_letter) TextView bigLetterTV;
        @BindView(R.id.item_course_course_name) TextView courseName;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            view = itemView;
        }
    }
}
