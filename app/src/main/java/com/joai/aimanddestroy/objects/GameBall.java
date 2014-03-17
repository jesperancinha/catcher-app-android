package com.joai.aimanddestroy.objects;

import android.graphics.Bitmap;

/**
 * Created by joao on 3/3/14.
 */
public class GameBall {

    private Box box = null;
    private Ball ball = null;

    private float previousX;
    private float previousY;


    public float getPreviousX() {
        return previousX;
    }

    public void setPreviousX(float previousX) {
        this.previousX = previousX;
    }

    public float getPreviousY() {
        return previousY;
    }

    public void setPreviousY(float previousY) {
        this.previousY = previousY;
    }

    public GameBall(Box box, int ballColor, int xOffset, int yOffset, Bitmap image) {
        setBox(box);
        setBall(new Ball(ballColor, xOffset, yOffset, image));
    }

    public GameBall(Box box, int ballColor, Bitmap image, int level) {
        setBox(box);
        setBall(new Ball(ballColor, 0, 0, image, level));
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }


    public void adjust(int w, int h) {
        int offSetX = (int) Math.random() * box.xMax;
        int offSetY = (int) (Math.random() * box.yMax);
        this.ball.adjust(w, h, offSetX, offSetY);
    }

    public GameBall removeBall(float xPoint, float yPoint) {
        GameBall removedBall = null;
        if (Math.abs(xPoint - ball.x) < ball.radius * 2 && Math.abs(yPoint - ball.y) < ball.radius * 2) {
            removedBall = this;
        }

        return removedBall;
    }
}