package org.jesperancinha.aimanddestroy;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AimAndDestroyActivity extends AppCompatActivity {


    Button btnBegginer = null;
    Button btnIntermediate = null;
    Button btnAdvanced = null;
    Button btnExit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_aim_and_destroy);

        btnBegginer = (Button) findViewById(R.id.btnBegginer);
        btnBegginer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame(1);
            }
        });
        btnIntermediate = (Button) findViewById(R.id.btnIntermediate);
        btnIntermediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame(2);
            }
        });
        btnAdvanced = (Button) findViewById(R.id.btnAdvanced);
        btnAdvanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame(3);
            }
        });
        btnExit = (Button) findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.aim_and_destroy, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void startGame(int level) {
        Intent i = new Intent(AimAndDestroyActivity.this, AimAndDestroyGameActivity.class);
        i.putExtra("LEVEL", level);
        finish();
        startActivity(i);
    }


}
