package com.example.facultyprofile.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.facultyprofile.R;
import com.google.firebase.FirebaseApp;



public class SearchActivity extends BaseActivity {
    EditText univ,stream;
    AutoCompleteTextView dep,name;
    Spinner s1;
    Button but;
    String[] dept ={"CSE"};
    String[] names ={"Anisha Radhakrishnan","Anupa Vijai","Archanaa rajendran","Bagavathi Sivakumar P.","Baskar A","Bindu K R","C.Arunkumar","D Venkataraman","Dhanya M Dhanalakshmy","Dhanya N.M.","Dr. Senthilkumar Mathi","GOVINDARAJAN JAYAPRAKASH","Ganesh Neelakanta Iyer","Gowtham Ramesh","Jevitha KP","Jeyakumar","Latha Parameswaran","M Sethumadhavan","M.Prathilothamai","Malathi P","N Harini","N Radhika","Padmavathi S","Prakash Periyasamy","Prashant R. Nair","Priyanka Kumar","RR sathiya","Raghesh Krishnan K","Rajathilagam B","Ramya gr","Ritwik Murali","Saba Rish","Senthilkumar Thangavel","Shanmuga Priya S","Shriram K Vasudevan","Shunmuga Velayutham","Sikha o.k","Sini Raj Pulari","Swapna T R","Vamsee Krishna Kiran M","Vidhya Balasubramanian","gayathri v"};

    ArrayAdapter<String> adapter,adapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(getApplicationContext());

        adapter = new ArrayAdapter<String>(SearchActivity.this,android.R.layout.simple_selectable_list_item,names);
        adapter1 = new ArrayAdapter<String>(SearchActivity.this,android.R.layout.simple_selectable_list_item,dept);
        univ= findViewById(R.id.UNIVERSITYNAME);
        dep= findViewById(R.id.DEPARTMENT);
        name= findViewById(R.id.FACULTYNAME);
        stream=findViewById(R.id.STREAM);
        but=findViewById(R.id.search);
        dep.setVisibility(View.GONE);
        univ.setEnabled(false);
        stream.setEnabled(false);
        s1=findViewById(R.id.course_name);
        dep.setThreshold(1);
        name.setThreshold(1);
        dep.setAdapter(adapter1);
        name.setAdapter(adapter);
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.showDropDown();
            }
        });
        dep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dep.showDropDown();
            }
        });


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.course, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    dep.setText("");
                    name.setVisibility(View.VISIBLE);
                    dep.setVisibility(View.GONE);
                }
                else{
                    name.setText("");
                    dep.setVisibility(View.VISIBLE);
                    name.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hasInternetConnection()){
                    if(name.getText().toString().matches("")&&dep.getText().toString().matches("")){
                        Toast.makeText(SearchActivity.this,"Please Enter Some Value",Toast.LENGTH_LONG).show();
                    }
                    else if(dep.getText().toString().matches("")){
                        Intent intent = new Intent(SearchActivity.this,ProfessorListActivity.class);
                        intent.putExtra("flag","0");
                        intent.putExtra("name",name.getText().toString());
                        startActivity(intent);
                    }
                    else{
                        Intent intent = new Intent(SearchActivity.this,ProfessorListActivity.class);
                        intent.putExtra("flag","1");
                        intent.putExtra("dep",dep.getText());
                        startActivity(intent);
                    }
                }
                else{
                    Toast.makeText(SearchActivity.this,"No Internet!!!",Toast.LENGTH_LONG).show();

                }

            }
        });


    }

}
