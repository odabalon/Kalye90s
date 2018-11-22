package ph.edu.apc.kalye90s;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by nanat on 8/29/2016.
 */
public class fetchscore extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp= getSharedPreferences("spfile.txt",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("Result", String.valueOf(GamePanel.best));
        editor.commit();
        startActivity(new Intent(fetchscore.this,score.class));
    }


}
