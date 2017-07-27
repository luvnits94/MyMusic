package nitishapps.com.mymusic;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList songName = new ArrayList();
    ArrayList songPath = new ArrayList();
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSongList();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this,(new Integer(position)).toString(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this,NextActivity.class);
                i.putExtra("in",position);
                startActivity(i);
            }
        });
    }
    private void getSongList(){
        lv = (ListView) findViewById(R.id.listSong);
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC+"!=0";
        String projection[] = {MediaStore.Audio.Media.DISPLAY_NAME,MediaStore.Audio.Media.DURATION,MediaStore.Audio.Media.DATA};
        Cursor c = getContentResolver().query(uri,projection,selection,null,null);
        try {
            while (c.moveToNext()) {
                songName.add(c.getString(0) + "\n" + c.getString(1) + "\n" + c.getString(2));
                songPath.add(c.getString(2));
            }
        }
        catch (NullPointerException ex){
            System.out.println(ex);
        }
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,songName);
        lv.setAdapter(adapter);
        c.close();
    }
}



