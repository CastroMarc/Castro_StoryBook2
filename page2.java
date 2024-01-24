package com.example.frogstory;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.MotionEvent;
import android.widget.Toast;


public class page2 extends AppCompatActivity {

    String frogName;

    private static final int SWIPE_THRESHOLD = 100;
    private float x1, x2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        Intent i = getIntent();
        frogName = i.getStringExtra("frogname");

        BackgroudMusicPlayer.start(this);

        findViewById(android.R.id.content).setOnTouchListener(this::handleSwipeEvent);

    }
    private void goBackToPage1() {
        Intent i = new Intent(this, page1.class);
        i.putExtra("frogname", frogName);
        startActivity(i);
        finish();
    }
    public void goToPage1(View view) {
        Intent i = new Intent(this, page1.class);
        i.putExtra("frogname", frogName);
        startActivity(i);
        finish();
    }
    private void goBackToPage3() {
        Intent i = new Intent(this, page3.class);
        i.putExtra("frogname", frogName);
        startActivity(i);
        finish();
    }
    public void goToPage3(View view) {
        Intent i = new Intent(this, page3.class);
        i.putExtra("frogname", frogName);
        startActivity(i);
        finish();
    }

    private boolean handleSwipeEvent(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;

                if (Math.abs(deltaX) > SWIPE_THRESHOLD) {
                    if (deltaX > 0) {
                        Toast.makeText(this, "Back to Page 1", Toast.LENGTH_SHORT).show();
                        goBackToPage1();
                    } else {
                        Toast.makeText(this, "Page 3", Toast.LENGTH_SHORT).show();
                        goBackToPage3();
                    }
                }
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //Confirmation dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if the user clicked Yes the app will exit
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if the user clicked No it won't exit
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
