package com.example.facultyprofile.Activity;

import android.content.Intent;
import android.os.Bundle;
import com.example.facultyprofile.R;
import com.google.firebase.FirebaseApp;



public class SearchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(getApplicationContext());
        Intent intent = new Intent(this,ProfessorListActivity.class);
        startActivity(intent);
    }

}
