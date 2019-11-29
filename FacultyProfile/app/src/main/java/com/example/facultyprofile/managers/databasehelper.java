package com.example.facultyprofile.managers;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class databasehelper {
    private FirebaseFirestore db;
    public databasehelper(){
        db = FirebaseFirestore.getInstance();
    }

    public void fetchallprofessors(){
        db.collection("professors")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("databasehelper", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w("databasehelper", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public void fetchoneprofessors(String name){
        db.collection("professors").document(name)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("databasehelper", "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d("databasehelper", "No such document");
                    }
                } else {
                    Log.d("databasehelper", "get failed with ", task.getException());
                }
            }
        });
    }
}
