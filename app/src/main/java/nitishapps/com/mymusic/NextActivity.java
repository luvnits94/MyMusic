package nitishapps.com.mymusic;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.ArrayList;
public class NextActivity extends AppCompatActivity {
    TextView tv_cd;
    Intent k;
    static SeekBar sb;
    static MediaPlayer mp;
    static int position = 0;
    static ArrayList songPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        sb = (SeekBar) findViewById(R.id.seekBar);
        tv_cd = (TextView) findViewById(R.id.curr_dur);
        songPath = new ArrayList();
        Intent i = getIntent();
        int index = i.getIntExtra("in",-1);




        songPath = i.getIntegerArrayListExtra("songPath");
        position = index;
        k = new Intent(this,MyService.class);
        k.putExtra("options","play");
        startService(k);
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
                    }
                    else{
                        final int curr = mp.getCurrentPosition();
                        sb.setProgress(curr);
                        runOnUiThread(new Runnable(){
                            @Override
                            public void run() {
                                tv_cd.setText(printDuration(curr));
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
        super.onPause();
    }

    @Override
    protected void onDestroy() {

        stopService(k);
        super.onDestroy();
    }

    public void playMusic(View view){
        playMusic();
    }
    public void playMusic(){
        k.putExtra("options","play");
        startService(k);
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
        k = new Intent(this,MyService.class);
        k.putExtra("options","stop");
        startService(k);

    }

    public void nextMusic(View view){
        k = new Intent(this,MyService.class);
        k.putExtra("options","next");
        startService(k);
    }
    public void prevMusic(View view){
        k = new Intent(this,MyService.class);
        k.putExtra("options","prev");
        startService(k);
    }
}
