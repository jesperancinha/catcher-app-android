package org.jesperancinha.aimanddestroy;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.jesperancinha.aimanddestroy.objects.Ball;
import org.jesperancinha.aimanddestroy.objects.Box;
import org.jesperancinha.aimanddestroy.objects.GameBall;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AimAndDestroyView extends View {

    private final Box box;
    List<GameBall> gameBalls = new ArrayList<GameBall>();
    long timeStart = 0;

    public AimAndDestroyView(Context context, int nBalls, int level) {
        super(context);

        box = new Box(0xff00003f);
        Resources res = getResources();

        for (int i = 0; i < nBalls; i++) {
            gameBalls.add(new GameBall(box, Color.RED, Ball.getRandomBitmap(res), level));
        }


        this.setFocusable(true);
        this.requestFocus();

        this.setFocusableInTouchMode(true);
        timeStart = new Date().getTime();
    }


    @Override
    protected void onDraw(Canvas canvas) {

        box.draw(canvas);
        for (GameBall gameBall : gameBalls) {
            Box box = gameBall.getBox();
            Ball ball = gameBall.getBall();

            ball.draw(canvas);

            ball.moveWithCollisionDetection(box);


            try {
                Thread.sleep(1);
            } catch (InterruptedException ignored) {
            }
        }
        invalidate();  // Force a re-draw
    }

    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {

        box.set(0, 0, w, h);
        for (GameBall gameBall : gameBalls) {


            gameBall.adjust(w, h);
        }
    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        for (GameBall gameBall : gameBalls) {
            Ball ball = gameBall.getBall();
            switch (keyCode) {
                case KeyEvent.KEYCODE_DPAD_RIGHT:
                    ball.speedX++;
                    break;
                case KeyEvent.KEYCODE_DPAD_LEFT:
                    ball.speedX--;
                    break;
                case KeyEvent.KEYCODE_DPAD_UP:
                    ball.speedY--;
                    break;
                case KeyEvent.KEYCODE_DPAD_DOWN:
                    ball.speedY++;
                    break;
                case KeyEvent.KEYCODE_DPAD_CENTER:
                    ball.speedX = 0;
                    ball.speedY = 0;
                    break;
                case KeyEvent.KEYCODE_A:
                    float maxRadius = (box.xMax > box.yMax) ? box.yMax / 2 * 0.9f : box.xMax / 2 * 0.9f;
                    if (ball.radius < maxRadius) {
                        ball.radius *= 1.05;
                    }
                    break;
                case KeyEvent.KEYCODE_Z:
                    if (ball.radius > 20) {
                        ball.radius *= 0.95;
                    }
                    break;
                case KeyEvent.KEYCODE_BACK: {
                    ((AimAndDestroyGameActivity) getContext()).onBackPressed();
                    break;
                }
            }
        }
        return true;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        GameBall ballToRemove = null;
        for (GameBall gameBall : gameBalls) {
            Ball ball = gameBall.getBall();

            float currentX = event.getX();
            float currentY = event.getY();
            float deltaX, deltaY;
            float scalingFactor = 5.0f / ((box.xMax > box.yMax) ? box.yMax : box.xMax);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (ballToRemove == null) {
                        ballToRemove = gameBall.removeBall(event.getX(), event.getY());
                    }
                    break;
                case MotionEvent.ACTION_MOVE:

                    deltaX = currentX - gameBall.getPreviousX();
                    deltaY = currentY - gameBall.getPreviousY();
                    ball.speedX += deltaX * scalingFactor;
                    ball.speedY += deltaY * scalingFactor;
            }

            gameBall.setPreviousX(currentX);
            gameBall.setPreviousY(currentY);
        }
        if (null != ballToRemove) {
            gameBalls.remove(ballToRemove);
        }
        if (gameBalls.size() == 0) {

           showPopup((Activity) getContext());

        }
        return true;
    }


    private void showPopup(final Activity context) {
        int popupWidth = 400;
        int popupHeight = 200;

        // Inflate the popup_layout.xml
        LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.popup);
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mainView = layoutInflater.inflate(R.layout.fragment_aim_and_destroy_end_game, viewGroup);

        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(mainView);
        popup.setWidth(popupWidth);
        popup.setHeight(popupHeight);
        popup.setFocusable(true);

        // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
        int OFFSET_X = 30;
        int OFFSET_Y = 30;

        // Clear the default translucent background
        popup.setBackgroundDrawable(new BitmapDrawable());

        // Displaying the popup at the specified location, + offsets.
        popup.showAtLocation(mainView, Gravity.NO_GRAVITY,0 + OFFSET_X, 200 + OFFSET_Y);

        long timeEnd = new Date().getTime();

        TextView textTime = (TextView) mainView.findViewById(R.id.textTime);
        textTime.setText(String.format("%d seconds", (timeEnd - timeStart) / 1000));
        Button btnGoBack = (Button) mainView.findViewById(R.id.btnGoBack);
        btnGoBack.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, AimAndDestroyActivity.class);
                i.putExtra("COMPLETED", true);
                context.startActivity(i);
                context.finish();
                popup.dismiss();
            }
        });
    }

}
