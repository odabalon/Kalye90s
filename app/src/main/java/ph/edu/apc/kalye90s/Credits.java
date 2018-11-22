package ph.edu.apc.kalye90s;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class Credits extends AppCompatActivity {

    public Button backbtn;

    public void init(){
        backbtn = (Button)findViewById(R.id.backcredits);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent backcredits = new Intent(Credits.this,MainActivity.class);

                startActivity(backcredits);
                MainActivity.music.reset();

            }
        });
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credits);



        init();





        }
    }













