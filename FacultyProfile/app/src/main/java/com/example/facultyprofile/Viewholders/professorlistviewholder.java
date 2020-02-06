package com.example.facultyprofile.Viewholders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facultyprofile.Adapters.professorlistadapter;
import com.example.facultyprofile.Models.Professors;
import com.example.facultyprofile.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class professorlistviewholder extends RecyclerView.ViewHolder {

    private TextView professorname,NOCcount,Hindexcount;
    private ImageView professorImageView;
    private ProgressBar progressBar;

    public professorlistviewholder(@NonNull View itemView, final professorlistadapter.CallBack callBack, final Context context) {
        super(itemView);
        professorname = itemView.findViewById(R.id.professorname);
        NOCcount = itemView.findViewById(R.id.NOCcount);
        Hindexcount = itemView.findViewById(R.id.hindexcount);
        professorImageView = itemView.findViewById(R.id.professorimage);
        progressBar = itemView.findViewById(R.id.progressofimage);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                if (callBack != null && position != RecyclerView.NO_POSITION) {
                    callBack.onItemClick(position, v);
                }
            }
        });

    }

    public void bindData(Professors professors) {
        professorname.setText(professors.getname());
        NOCcount.setText(String.valueOf(professors.getCitedby()));
        Hindexcount.setText(String.valueOf(professors.getHindex()));
        setImageOfUser(professors.getUrl_picture());
    }

    private void setImageOfUser(String imageUrl) {
        if (imageUrl == null) {
            progressBar.setVisibility(View.INVISIBLE);
            professorImageView.setImageResource(R.drawable.circular_person_image_background);
        } else {
            Picasso.get()
                    .load(imageUrl)
                    .fit()
                    .into(professorImageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            progressBar.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onError(Exception e) {
                            progressBar.setVisibility(View.INVISIBLE);
                            professorImageView.setImageResource(R.drawable.circular_person_image_background);
                        }
                    });

        }
    }
}
