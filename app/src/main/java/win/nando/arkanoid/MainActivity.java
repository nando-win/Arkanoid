package win.nando.arkanoid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends Activity {

    private ArkanoidView mArkanoidView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mArkanoidView = new ArkanoidView(this);
        setContentView(mArkanoidView);
    }

    protected void onResume(){
        super.onResume();
        mArkanoidView.resume();
    }

    protected void onPause(){
        super.onPause();
        mArkanoidView.pause();
    }

    class ArkanoidView extends SurfaceView implements Runnable{

        private volatile boolean playing;
        private boolean pause;
        private long timeThisFrame;
        private long fps;

        private int screenX;
        private int screenY;

        private Thread gameThread;
        private SurfaceHolder ourHolder;
        private Canvas canvas;
        private Paint paint;
        public ArkanoidView(Context context) {
            super(context);
            this.pause = true;
            this.ourHolder = getHolder();
            this.paint = new Paint();

            //Descobrir o tamanho da tela para desenhar os objetos proporcionais a mesma
            Display display = getWindowManager().getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);

            this.screenX = point.x;
            this.screenY = point.y;
        }

        @Override
        public void run() {

            while (playing){

                long startFrameTime = System.currentTimeMillis();

                if(!pause){
                    update();
                }
                draw();

                timeThisFrame = System.currentTimeMillis() - startFrameTime;
                if(timeThisFrame >= 1){
                    fps = 1000/timeThisFrame;
                }
            }

        }

        private void draw() {

            if(ourHolder.getSurface().isValid()){

                canvas = ourHolder.lockCanvas();

                canvas.drawColor(Color.argb(255,255,255,255));

                paint.setColor(Color.argb(255,255,255,255));

                // Todo: Desenhar o paddle

                // Todo: Desenhar a bola

                // Todo: Desenhar os bricks

                ourHolder.unlockCanvasAndPost(canvas);

            }

        }

        private void update() {
        }

        public void pause(){

            this.playing = false;
            try {
                this.gameThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
         public void resume(){

             this.playing = true;
             gameThread = new Thread(this);
             gameThread.start();

         }
        public boolean onTouchEvent(MotionEvent event){ //Tratar eventos de touch

            switch (event.getAction() & MotionEvent.ACTION_MASK){ //recupera movimento na tela, mas o MASK restringe aos eventos de ação

                case MotionEvent.ACTION_DOWN:

                    break;

                case MotionEvent.ACTION_UP:

                    break;
            }

            return true;
        }
    }

}