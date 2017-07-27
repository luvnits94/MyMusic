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

        //position from Main Activity
        songPath = new ArrayList();
        Intent i = getIntent();
        int index = i.getIntExtra("in",-1);
        songPath = i.getIntegerArrayListExtra("songPath");
        position = index;
        //Toast.makeText(this,songPath.get(index).toString(), Toast.LENGTH_SHORT).show();
        playMusic();
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
        mp.start();
    }
}
