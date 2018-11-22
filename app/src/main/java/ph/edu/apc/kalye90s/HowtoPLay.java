package ph.edu.apc.kalye90s;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Justine Siccion on 02/08/2016.
 */

    public class HowtoPLay extends AppCompatActivity {

        public Button backbtn;

        public void init(){
            backbtn = (Button)findViewById(R.id.Backhow);
            backbtn.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {

                    Intent backhow = new Intent(HowtoPLay.this,MainActivity.class);

                    startActivity(backhow);
                    MainActivity.music.reset();

                }
            });
        }

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.howtoplay);



            init();





        }
    }









