package com.example.frogstory;

import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText frog;
    String frogName;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frog = findViewById(R.id.frog);

        // BG MUSIC
        mediaPlayer = new MediaPlayer();
        try {
            AssetFileDescriptor assetFileDescriptor = getAssets().openFd("Audio/bgSound.mp3");
            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            mediaPlayer.prepare();
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToHome(View v) {
        frogName = frog.getText().toString();

        if (frogName.isEmpty()) {
            Toast.makeText(this, "Please enter a frog name", Toast.LENGTH_SHORT).show();

        } else if (!isValidFrogName(frogName)) {
            Toast.makeText(this, "Frog name should not contain numbers or special characters", Toast.LENGTH_SHORT).show();

        } else {
            Intent i = new Intent(this, homePage.class);
            i.putExtra("frogname", frogName);
            startActivity(i);
            finish();
        }
    }

    private boolean isValidFrogName(String name) {
        return name.matches("[a-zA-Z ]+");
    }

    public void exitApp(View v) {
        finish();
    }

    @Override
    public void onBackPressed() {
        // Confirmation dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // If the user clicked Yes, the app will exit
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // If the user clicked No, it won't exit
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
