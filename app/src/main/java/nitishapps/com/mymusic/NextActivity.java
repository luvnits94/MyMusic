package nitishapps.com.mymusic;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NextActivity extends AppCompatActivity {
    static ArrayList songPath;
    Intent i;
    Button b_play, b_pause, b_stop;
    TextView tv_cd;
    TextView tv_td;
    static SeekBar sb;
    static MediaPlayer mp;
    static int position = 0;








    //static int position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);


        sb = (SeekBar) findViewById(R.id.seekBar);
        tv_cd = (TextView) findViewById(R.id.curr_dur);
        //position from Main Activity
        songPath = new ArrayList();
        Intent i = getIntent();
        int index = i.getIntExtra("in",-1);
        songPath = i.getIntegerArrayListExtra("songPath");
        position = index;
        //Toast.makeText(this,songPath.get(index).toString(), Toast.LENGTH_SHORT).show();
        playMusic();



        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    if(mp != null){
                        mp.seekTo(progress);
                    }
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }




        });


        Thread th = new Thread(){
            @Override
            public void run() {
                while (true){
                    if(mp == null){
                        sb.setProgress(0);
                        tv_cd.setText("00:00:00");
                    }

                    else{
                        sb.setProgress(mp.getCurrentPosition());
                        runOnUiThread(new Runnable(){
                            @Override
                            public void run() {
                                tv_cd.setText(printDuration(mp.getCurrentPosition()));
                            }
                        });

                    }

                }
            }
        };
        th.start();

    }
    public String printDuration(int d)
    {
        int l = d/1000;
        int min = l/60;
        int sec = l%60;
        String s = ""+min;
        String s1 = ""+sec;
        if(min < 10)
            s = "0" + s;
        if(sec < 10)
            s1 = "0"+s1;
        return s+":"+s1;
    }
    @Override
    protected void onPause() {
        //stopService(i);
        super.onPause();
        mp.pause();
        mp = null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent i = getIntent();
        int index = i.getIntExtra("in",-1);
        songPath = i.getIntegerArrayListExtra("songPath");
        position = index;
        //Toast.makeText(this,songPath.get(index).toString(), Toast.LENGTH_SHORT).show();
        playMusic();
    }


    //Play Working Finee

    public void playMusic(View view){
        playMusic();
        //tmmv_cd.setText(mp.getDuration());
    }
    public void playMusic(){
        if(mp == null){
            mp = MediaPlayer.create(this, Uri.parse(songPath.get(position).toString()));

        }
        else {
        }
        sb.setMax(mp.getDuration());
        mp.start();
    }


    public void pauseMusic(View view){
        if(mp!=null && mp.isPlaying()){
            mp.pause();
        }
    }

    public void stopMusic(View view){
        stopMusic();
    }
    public void stopMusic(){
        stopService(i);
        i.putExtra("option","stop");
        startService(i);
    }

    public void nextMusic(View view){
        stopMusic();
        if(position==(songPath.size()-1)){
            position=0;
        }
        else{
            position++;
        }
        playMusic();
    }
    public void prevMusic(View view){
        stopMusic();
        if(position==0){
            position=songPath.size()-1;
        }
        else{
            position--;
        }
        playMusic();
    }

}
