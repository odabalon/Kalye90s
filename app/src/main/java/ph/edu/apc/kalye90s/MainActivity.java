package ph.edu.apc.kalye90s;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.*;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {
    public Button cbutton, howtobutton, setbutton, exbutton, pbutton;
    static MediaPlayer music;


    public void init() {

        cbutton = (Button) findViewById(R.id.credits);
        howtobutton = (Button) findViewById(R.id.how);
        setbutton = (Button) findViewById(R.id.settings);

        pbutton = (Button) findViewById(R.id.play);


        cbutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent credits = new Intent(MainActivity.this, Credits.class);
                startActivity(credits);

            }

        });
        pbutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent game = new Intent(MainActivity.this, Game.class);
                startActivity(game);


            }
        });

        howtobutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent how = new Intent(MainActivity.this, HowtoPLay.class);
                startActivity(how);

            }

        });


        setbutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent settings = new Intent(MainActivity.this, Settings.class);
                startActivity(settings);


            }

        });


    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        music = MediaPlayer.create(MainActivity.this, R.raw.bornt);

        if (Settings.hello==1) {
            music.setLooping(true);
            music.start();
        }


        //turn title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //set to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        init();
    }


}






