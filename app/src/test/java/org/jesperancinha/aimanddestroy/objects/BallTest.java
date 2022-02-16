package org.jesperancinha.aimanddestroy.objects;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.RectF;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class BallTest {

    @Test
    void adjust() {
        final Ball ball = new Ball(123, 10, 20, mock(Bitmap.class), mock(RectF.class), mock(Paint.class));
        assert ball.x == 20.0;
        assert ball.y == 30.0;
    }
}