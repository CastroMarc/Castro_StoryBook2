package com.example.frogstory;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;

public class BackgroudMusicPlayer {
    private static MediaPlayer mediaPlayer;

    public static void start(Context context) {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
            try {
                AssetFileDescriptor assetFileDescriptor = context.getAssets().openFd("Audio/forestBG.mp3");
                mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
                mediaPlayer.prepare();
                mediaPlayer.setLooping(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    public static void stop() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public static void release() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
