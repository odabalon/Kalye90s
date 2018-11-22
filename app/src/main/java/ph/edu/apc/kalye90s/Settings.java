package ph.edu.apc.kalye90s;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Justine Siccion on 08/08/2016.
 */
public class Settings extends AppCompatActivity {

    public Button backbtn,offmusic,onmusic,score;
    static int hello=1;

    public void init(){
        backbtn = (Button)findViewById(R.id.buttonset);
        offmusic=(Button)findViewById(R.id.off) ;
        onmusic=(Button) findViewById(R.id.on);
        score=(Button) findViewById(R.id.hscore);

        backbtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent backset = new Intent(Settings.this,MainActivity.class);
                startActivity(backset);

            }
        });
        offmusic.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                MainActivity.music.start();


            }
        });

        onmusic.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
            if(MainActivity.music.isPlaying()) {
                MainActivity.music.pause();
                MainActivity.music.seekTo(0);
                hello=0;
}
            }
        });
        score.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent score = new Intent(Settings.this,score.class);
                startActivity(score);

            }
        });
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        init();

    }
}






