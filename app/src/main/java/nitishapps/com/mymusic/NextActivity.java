package nitishapps.com.mymusic;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class NextActivity extends AppCompatActivity {
    ArrayList songPath;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);


        //position from Main Activity
        songPath = new ArrayList();
        Intent i = getIntent();
        int index = i.getIntExtra("in",-1);
        songPath = i.getIntegerArrayListExtra("songPath");
        Toast.makeText(this,songPath.get(index).toString(), Toast.LENGTH_SHORT).show();
        mp= MediaPlayer.create(this, Uri.parse(songPath.get(index).toString()));
        mp.start();


    }
}
