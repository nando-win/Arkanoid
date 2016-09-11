package win.nando.arkanoid;

import android.graphics.RectF;

/**
 * Created by nando on 08/09/2016.
 */
public class Paddle {

    public final int STOPPED = 0;
    public final int LEFT = 1;
    public final int RIGHT = 2;

    private RectF rect;

    private float x;
    private float y;
    private float width;
    private float height;

    private float paddleSpeed;

    private int paddleMoving;

    public Paddle(int screenX, int screenY){

        this.paddleMoving = STOPPED;

        this.x = (screenX - width) / 2;
        this.y = screenY - 50;

        rect = new RectF(x, y, x+width, y+height);

    }

    public RectF getRect(){
        return rect;
    }

}
