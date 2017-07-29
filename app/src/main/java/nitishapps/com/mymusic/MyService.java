package nitishapps.com.mymusic;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;
import static nitishapps.com.mymusic.NextActivity.mp;
import static nitishapps.com.mymusic.NextActivity.sb;
import static nitishapps.com.mymusic.NextActivity.position;
import static nitishapps.com.mymusic.NextActivity.songPath;
public class MyService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String opt = intent.getStringExtra("options");
        switch (opt){
            case "play":
                playMusic();
                break;
            case "pause":
                pauseMusic();
                break;
            case "stop":
                stopMusic();
                break;
            case "prev":
                prevMusic();
                break;
            case "next":
                nextMusic();
                break;
            default:
                Toast.makeText(this, "Undefined Operation", Toast.LENGTH_SHORT).show();
        }
        return super.onStartCommand(intent, flags, startId);
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public void playMusic(){
        if(mp == null){
            mp = MediaPlayer.create(this, Uri.parse(songPath.get(position).toString()));
        }
        sb.setMax(mp.getDuration());
        mp.start();
        Toast.makeText(this, "Song No :"+position, Toast.LENGTH_SHORT).show();
    }
    public void pauseMusic(){
        mp.pause();
    }
    public void stopMusic(){
        if(mp != null){
            mp.stop();
            mp = null;
        }
    }
    public void prevMusic(){
        stopMusic();
        if(position==0){
            position=songPath.size()-1;
        }
        else{
            position--;
        }
        playMusic();
    }

    public void nextMusic(){
        stopMusic();
        if(position==(songPath.size()-1)){
            position=0;
        }
        else{
            position++;
        }
        playMusic();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
