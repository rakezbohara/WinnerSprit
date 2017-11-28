package com.app.rakez.winnersprit.quiz;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.app.rakez.winnersprit.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by RAKEZ on 11/28/2017.
 */

public class AdapterLevel extends RecyclerView.Adapter<AdapterLevel.ViewHolder> {

    private Context context;
    private List<ItemLevel> itemLevels;

    public AdapterLevel(Context context, List<ItemLevel> itemLevels) {
        this.context = context;
        this.itemLevels = itemLevels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_level, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ItemLevel itemLevel = itemLevels.get(position);
        if(itemLevel.isActive()){
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.level_active));
        }else{
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.level_inactive));
        }
        switch (position % 7){
            case 0:
                holder.imageView.setImageResource(R.drawable.ic_level_1);
                break;
            case 1:
                holder.imageView.setImageResource(R.drawable.ic_level_2);
                break;
            case 2:
                holder.imageView.setImageResource(R.drawable.ic_level_3);
                break;
            case 3:
                holder.imageView.setImageResource(R.drawable.ic_level_4);
                break;
            case 4:
                holder.imageView.setImageResource(R.drawable.ic_level_5);
                break;
            case 5:
                holder.imageView.setImageResource(R.drawable.ic_level_6);
                break;
            case 6:
                holder.imageView.setImageResource(R.drawable.ic_level_7);
                break;
            default:
                holder.imageView.setImageResource(R.drawable.ic_level_8);
                break;
        }
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if(position%2==0){
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        }else{
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        }
        holder.cardView.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return itemLevels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        @BindView(R.id.level_CV) CardView cardView;
        @BindView(R.id.level_IV) ImageView imageView;
        @BindView(R.id.level_item_layout) RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view = itemView;
        }
    }
}
