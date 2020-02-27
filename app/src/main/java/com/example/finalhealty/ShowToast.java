package com.example.finalhealty;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ShowToast {
    public ShowToast(Context context, String info) {
        Toast toast = Toast.makeText(context, info, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,0);
        View view = toast.getView();
        view.setBackgroundColor(Color.parseColor("#F5BCA9"));
        TextView text = view.findViewById(android.R.id.message);
        text.setTextColor(Color.DKGRAY);
        text.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        toast.show();
    }
}