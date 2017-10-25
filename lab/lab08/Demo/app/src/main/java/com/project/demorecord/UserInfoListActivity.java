package com.project.demorecord;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserInfoListActivity extends AppCompatActivity {

    public static final String EXTTRA_LIST = "EXTTRA_LIST";

    @BindView(R.id.list)
    public RecyclerView list;

    @BindView(R.id.textNotFound)
    public TextView textNotFound;

    private MyAdapter adapter;
    private CommonSharePreference preference;
    private UserInfoList suggestSearchList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_list);
        ButterKnife.bind(this);
        Button clearButton = (Button) findViewById(R.id.CLbutton);
        preference = new CommonSharePreference(this);

        adapter = new MyAdapter();
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        suggestSearchList = (UserInfoList) preference.read(UserInfoListActivity.EXTTRA_LIST, UserInfoList.class);
        if (suggestSearchList != null) {
            displaySuggestsList(suggestSearchList.getUserInfoList());
        } else {
            displaySuggestsList(new ArrayList<UserInfo>());
        }

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cleardata(suggestSearchList, adapter, preference);

            }
        });
    }

    public void cleardata(UserInfoList suggest, MyAdapter notifycall, CommonSharePreference sharePreference){
        suggest.getUserInfoList().clear();
        notifycall.notifyDataSetChanged();
        sharePreference.save(UserInfoListActivity.EXTTRA_LIST, suggest);
        textNotFound.setVisibility(View.VISIBLE);
        list.setVisibility(View.GONE);
    }

    public void displaySuggestsList(List<UserInfo> suggestsList) {
        if (suggestsList.size() <= 0) {
            textNotFound.setVisibility(View.VISIBLE);
            list.setVisibility(View.GONE);
        } else {
            textNotFound.setVisibility(View.GONE);
            list.setVisibility(View.VISIBLE);
            adapter.setData(suggestsList);
            adapter.notifyDataSetChanged();
        }

    }

}
