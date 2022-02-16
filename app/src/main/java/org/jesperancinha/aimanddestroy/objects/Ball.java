package org.jesperancinha.aimanddestroy.objects;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import org.jesperancinha.aimanddestroy.R;

public class Ball {


    public float radius = 10;      // Ball's radius
    float x = radius;  // Ball's center (x,y)
    float y = radius;
    public float speedX = 5;       // Ball's speed (x,y)
    public float speedY = 3;
    private final RectF bounds;   // Needed for Canvas.drawOval
    private final Paint paint;    // The paint style, color used for drawing
    Bitmap bitmap = null;

    // Constructor
    public Ball(int color, int xOffset, int yOffset, Bitmap image, RectF rectF, Paint paint) {
        x += xOffset;
        y += yOffset;
        this.bounds = rectF;
        this.paint = paint;
        this.paint.setColor(color);
        this.bitmap = image;
        radius = image.getWidth() / 2;
    }

    public Ball(int color, int xOffset, int yOffset, Bitmap image, int level, RectF rectF, Paint paint) {
        x += xOffset;
        y += yOffset;
        bounds = rectF;
        this.paint = paint;
        this.paint.setColor(color);
        this.bitmap = image;
        radius = image.getWidth() / 2;
        int speedFactor = 0;
        int offsetFactor = 0;
        switch (level) {
            case 1:
                speedFactor = 2;
                offsetFactor = 1;
                break;
            case 2:
                speedFactor = 5;
                offsetFactor = 10;
                break;
            case 3:
                speedFactor = 5;
                offsetFactor = 20;
                break;
        }

        speedX = (float) (offsetFactor + Math.random() * speedFactor);
        speedY = (float) (offsetFactor + Math.random() * speedFactor);
    }

    public void moveWithCollisionDetection(Box box) {
        // Get new (x,y) position
        x += speedX;
        y += speedY;
        // Detect collision and react
        if (x + radius > box.xMax) {
            speedX = -speedX;
            x = box.xMax - radius;
        } else if (x - radius < box.xMin) {
            speedX = -speedX;
            x = box.xMin + radius;
        }
        if (y + radius > box.yMax) {
            speedY = -speedY;
            y = box.yMax - radius;
        } else if (y - radius < box.yMin) {
            speedY = -speedY;
            y = box.yMin + radius;
        }
    }

    public void draw(Canvas canvas) {
        bounds.set(x - radius, y - radius, x + radius, y + radius);
        //canvas.drawOval(bounds, paint);
        canvas.drawBitmap(bitmap, bounds.centerX(), bounds.centerY(), paint);
    }


    public void adjust(int w, int h, int offsetX, int offsetY) {
        if (x > w)
            x = x - w;
        if (y > h)
            y = y - h;
        x += offsetX;
        y += offsetY;
    }

    public static Bitmap getRandomBitmap(Resources res) {
        int result = (int) (Math.random() * 6);
        int testResource = 1;
        switch (result) {
            case 0:
                testResource = R.drawable.ball_blue1;
                break;
            case 1:
                testResource = R.drawable.ball_gray1;
                break;
            case 2:
                testResource = R.drawable.ball_green1;
                break;
            case 3:
                testResource = R.drawable.ball_orange1;
                break;
            case 4:
                testResource = R.drawable.ball_purple1;
                break;
            case 5:
                testResource = R.drawable.ball_red1;
                break;

        }

        return BitmapFactory.decodeResource(res, testResource);
    }


}