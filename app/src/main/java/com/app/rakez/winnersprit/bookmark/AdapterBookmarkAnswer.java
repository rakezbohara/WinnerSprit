package com.app.rakez.winnersprit.bookmark;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.rakez.winnersprit.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by RAKEZ on 12/12/2017.
 */

public class AdapterBookmarkAnswer extends RecyclerView.Adapter<AdapterBookmarkAnswer.MyViewHolder> {

    Context context;
    List<String> answers;
    Integer correct;
    Integer row_index = -1;
    private List<String> options = new ArrayList<>();

    public AdapterBookmarkAnswer(Context context, List<String> answers, Integer correct) {
        this.context = context;
        this.answers = answers;
        this.correct = correct;
        options.add("A");
        options.add("B");
        options.add("C");
        options.add("D");
        options.add("E");
        options.add("F");
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_answer, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String answer = answers.get(position);
        holder.answerOption.setText(answer);
        holder.answerOptionNo.setText(options.get(position));
        if(position==correct){
            holder.answerOptionLayout.setBackgroundColor(context.getResources().getColor(R.color.answer_true_back));
            holder.answerOptionNoCV.setCardBackgroundColor(context.getResources().getColor(R.color.answer_true));
            holder.answerOption.setTextColor(context.getResources().getColor(R.color.answer_true));
            holder.answerOptionNo.setTextColor(context.getResources().getColor(R.color.white));
        }

    }

    @Override
    public int getItemCount() {
        return answers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        View view;
        @BindView(R.id.answer_option_layout) LinearLayout answerOptionLayout;
        @BindView(R.id.answer_option) TextView answerOption;
        @BindView(R.id.answer_option_no) TextView answerOptionNo;
        @BindView(R.id.answer_option_no_CV) CardView answerOptionNoCV;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view = itemView;
        }
    }
}
