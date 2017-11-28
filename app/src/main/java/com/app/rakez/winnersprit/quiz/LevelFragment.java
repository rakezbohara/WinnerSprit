package com.app.rakez.winnersprit.quiz;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.app.rakez.winnersprit.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by RAKEZ on 11/28/2017.
 */

public class LevelFragment extends Fragment {

    @BindView(R.id.level_RV) RecyclerView levelRV;

    AdapterLevel adapterLevel;
    List<ItemLevel> itemLevels;

    ToogleProfile toogleProfile;

    public LevelFragment() {

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_level, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initializeRV();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            toogleProfile = (ToogleProfile) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    private void initializeRV() {
        itemLevels = new ArrayList<>();
        itemLevels.add(new ItemLevel("edfr4",true));
        itemLevels.add(new ItemLevel("edfr4",true));
        itemLevels.add(new ItemLevel("edfr4",true));
        itemLevels.add(new ItemLevel("edfr4",true));
        itemLevels.add(new ItemLevel("edfr4",true));
        itemLevels.add(new ItemLevel("edfr4",true));
        itemLevels.add(new ItemLevel("edfr4",true));
        itemLevels.add(new ItemLevel("edfr4",true));
        itemLevels.add(new ItemLevel("edfr4",true));
        itemLevels.add(new ItemLevel("edfr4",true));
        itemLevels.add(new ItemLevel("edfr4",true));
        itemLevels.add(new ItemLevel("edfr4",true));
        itemLevels.add(new ItemLevel("edfr4",false));
        itemLevels.add(new ItemLevel("edfr4",false));
        itemLevels.add(new ItemLevel("edfr4",false));
        itemLevels.add(new ItemLevel("edfr4",false));
        itemLevels.add(new ItemLevel("edfr4",false));
        adapterLevel = new AdapterLevel(getActivity(), itemLevels);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        levelRV.setLayoutManager(layoutManager);
        levelRV.setAdapter(adapterLevel);
        levelRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager llm = (LinearLayoutManager) recyclerView.getLayoutManager();
                int firstVisible = llm.findFirstCompletelyVisibleItemPosition();
                if(firstVisible!=0){
                    toogleProfile.hideProfile();
                }else{
                    toogleProfile.showProfile();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
}
