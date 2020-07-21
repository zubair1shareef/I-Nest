package com.example.recylerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class Myviewholder extends RecyclerView.ViewHolder {
    TextView name,rollno,desp;
    ImageView imageView;
    public Myviewholder(@NonNull View itemView) {

        super(itemView);
        name=itemView.findViewById(R.id.namee);
        rollno=itemView.findViewById(R.id.rollnoo);
        desp=itemView.findViewById(R.id.ddddee);
        imageView=itemView.findViewById(R.id.imageView);

    }
}
