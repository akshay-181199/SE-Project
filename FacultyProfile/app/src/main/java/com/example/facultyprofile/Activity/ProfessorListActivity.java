package com.example.facultyprofile.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facultyprofile.Adapters.professorlistadapter;
import com.example.facultyprofile.Listeners.OnObjectFetchListener;
import com.example.facultyprofile.Listeners.OnObjectListFetchListener;
import com.example.facultyprofile.Models.Professors;
import com.example.facultyprofile.R;
import com.example.facultyprofile.managers.databasehelper;

import java.util.ArrayList;

public class ProfessorListActivity extends BaseActivity {
    databasehelper db = new databasehelper();
    professorlistadapter Professorlistadapter;
    RecyclerView professorrecylerview;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String str = intent.getStringExtra("flag");
        setContentView(R.layout.professors_show_list);
        professorrecylerview = findViewById(R.id.professorlistRecyclerView);
        Professorlistadapter = new professorlistadapter(this);
        Professorlistadapter.setCallBack(new professorlistadapter.CallBack() {
            @Override
            public void onItemClick(int position, View view) {
                Professors professors = Professorlistadapter.getItemByPosition(position);
            }
        });
        professorrecylerview.setAdapter(Professorlistadapter);
        layoutManager=new LinearLayoutManager(ProfessorListActivity.this);
        professorrecylerview.setHasFixedSize(true);
        professorrecylerview.setLayoutManager(layoutManager);
        showProgress();
        if(str.equals("0")){
            db.fetchoneprofessors(intent.getStringExtra("name"), new OnObjectFetchListener() {
                @Override
                public void onDataFetched(Object object) {
                    ArrayList<Professors> list=new ArrayList<Professors>();
                    list.add((Professors) object);
                    hideProgress();
                    Professorlistadapter.setProfessorsArrayList(list);
                }
            });
        }
        else{
            db.fetchallprofessors(new OnObjectListFetchListener() {
                @Override
                public void onListChanged(ArrayList list, boolean isEmpty) {
                    if (isEmpty){
                        hideProgress();
                        setContentView(R.layout.activity_main);
                    }
                    else {
                        hideProgress();
                        Professorlistadapter.setProfessorsArrayList(list);

                    }
                }
            });
        }

    }
}
