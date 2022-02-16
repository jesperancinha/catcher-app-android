package org.jesperancinha.aimanddestroy.objects;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Box {
    public int xMin, xMax, yMin, yMax;
    private Paint paint;
    private Rect bounds;

    public Box(int color) {
        paint = new Paint();
        paint.setColor(color);
        bounds = new Rect();
    }

    public void set(int x, int y, int width, int height) {
        xMin = x;
        xMax = x + width - 1;
        yMin = y;
        yMax = y + height - 1;

        bounds.set(xMin, yMin, xMax, yMax);
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(bounds, paint);
    }
}