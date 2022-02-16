package org.jesperancinha.aimanddestroy;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by joao on 3/3/14.
 */

public class AimAndDestroyFinishDialogue extends PopupWindow {
    long timeStarted = 0;

    public AimAndDestroyFinishDialogue(long timeStarted, final AimAndDestroyGameActivity context, View parent) {
        super();
        this.timeStarted = timeStarted;
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mainView = layoutInflater.inflate(R.layout.fragment_aim_and_destroy_end_game, null);
        long timeEnd = new Date().getTime();

        TextView textTime = (TextView) mainView.findViewById(R.id.textTime);
        textTime.setText(String.format("%d seconds", (timeEnd - timeStarted) / 1000));


        Button btnGoBack = (Button) mainView.findViewById(R.id.btnGoBack);

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, AimAndDestroyActivity.class);
                i.putExtra("COMPLETED", true);
                context.startActivity(i);
                context.finish();

            }
        });

        this.showAtLocation(parent, Gravity.NO_GRAVITY, 0, 0);
    }


}
