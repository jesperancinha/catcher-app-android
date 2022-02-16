package org.jesperancinha.aimanddestroy;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AimAndDestroyGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        int level = 0;
        if (extras != null) {
            level = extras.getInt("LEVEL");

        }
        this.setTitle(getString(R.string.aim_and_destroy_playing));
        View bouncingBallView = new AimAndDestroyView(this, 5, level);
        setContentView(bouncingBallView);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.aim_and_destroy_game, menu);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(AimAndDestroyGameActivity.this, AimAndDestroyActivity.class);
        i.putExtra("COMPLETED", true);
        startActivity(i);
        finish();

    }


}
