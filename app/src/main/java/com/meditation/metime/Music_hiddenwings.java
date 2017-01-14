/**
 *  MeDitationTime
 *
 *  Music_hiddenwings.class: Controller class for a media player of the music section
 *
 *  @version    1.0
 *  @author     Meditate to Regenerate (meditatetoregenerate.org)
 */

package com.meditation.metime;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.ToggleButton;

import com.john.waveview.WaveView;


public class Music_hiddenwings extends AppCompatActivity {

    private SeekBar seekBar;
    private WaveView waveView;

    private boolean isPaused = false;

    private long remaining=260000;

    private MediaPlayer Mp;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_hiddenwings);

        final ToggleButton play_btn = (ToggleButton) findViewById(R.id.p_p);

        Mp= MediaPlayer.create(this, R.raw.hiddenwings);


        waveView = (WaveView) findViewById(R.id.wave_view);



        play_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(play_btn.isChecked()){
                    isPaused=false;
                }else{
                    isPaused=true;
                }

                //the length of music
                long mills = remaining;
                if(!isPaused){
                    Mp.start();
                }else{
                    Mp.pause();
                }

                new CountDownTimer(remaining, 1000) { // adjust the milli seconds here

                    public void onTick(long millisUntilFinished) {

                        if(isPaused){
                            cancel();
                        }
                        waveView.setProgress((int)((260-(millisUntilFinished / 1000))*(100/260.0)));
                        remaining = millisUntilFinished;
                        if(remaining<2000){
                            finish();
                        }
                    }

                    public void onFinish() {

                    }
                }.start();


            }
        });



    }

//    public void onDestroy(){
//        super.onDestroy();
//        finish();
//    }

    public void onBackPressed(){
        super.onBackPressed();
        Mp.stop();
    }
}