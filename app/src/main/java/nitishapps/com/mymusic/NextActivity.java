package nitishapps.com.mymusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class NextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        //position from Main Activity


        Intent i = getIntent();
        int a = i.getIntExtra("in",-1);
        Toast.makeText(this,(new Integer(a)).toString(), Toast.LENGTH_SHORT).show();
    }
}
