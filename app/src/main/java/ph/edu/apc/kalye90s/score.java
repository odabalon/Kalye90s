package ph.edu.apc.kalye90s;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class score extends AppCompatActivity {
    public TextView scoreview;
    public Button back;
int best= GamePanel.best;
    public void init(){
    back=(Button)findViewById(R.id.back);
        scoreview=(TextView)findViewById(R.id.scoreview);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent backset = new Intent(score.this,Settings.class);

                startActivity(backset);
                MainActivity.music.reset();

            }
        });

    }
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scores);
        scoreview = (TextView)findViewById(R.id.scoreview);

        SharedPreferences sp = getSharedPreferences("spfile.txt",MODE_PRIVATE);
        String vcontent = sp.getString("Result", String.valueOf(best));
        scoreview.setText(vcontent);

        init();





    }

}