package com.example.frogstory;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.MotionEvent;
import android.widget.Toast;


public class page4 extends AppCompatActivity {
    String frogName;
    private static final int SWIPE_THRESHOLD = 100;
    private float x1, x2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page4);

        Intent i = getIntent();
        frogName = i.getStringExtra("frogname");

        findViewById(android.R.id.content).setOnTouchListener(this::handleSwipeEvent);

        BackgroudMusicPlayer.start(this);

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
    private void goBackToPage5() {
        Intent i = new Intent(this, page5.class);
        startActivity(i);
        finish();
    }
    public void goToPage5(View view) {
        Intent i = new Intent(this, page5.class);
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
                        Toast.makeText(this, "Back to Page 3", Toast.LENGTH_SHORT).show();
                        goBackToPage3();
                    } else {
                        Toast.makeText(this, "Page 5", Toast.LENGTH_SHORT).show();
                        goBackToPage5();
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
