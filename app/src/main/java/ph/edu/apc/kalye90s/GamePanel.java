package ph.edu.apc.kalye90s;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    public static final int  WIDTH = 2280;
    public static final int HEIGHT = 1440;
    public static final int MOVESPEED = -4;
    private MainThread thread;
    private long missileStartTime;
    private Background bg;
    private Player player;
    private ArrayList<Missile> missiles;
    private Random rand = new Random();
    private boolean newGameCreated;
    public static int score;
    static int best;
    private long startReset;
    private boolean reset;
    private boolean dissapear;
    private boolean started;




    public GamePanel(Context context)
    {
        super(context);



        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);



        //make gamePanel focusable so it can handle  events
        setFocusable(true);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        boolean retry = true;
        int counter = 0;
        while (retry && counter<1000)
        {

            counter++;
            try {
                thread.setRunning(false);
                thread.join();
                retry = false;


            } catch (InterruptedException e) {e.printStackTrace();}




        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.environment));
        player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.running), 188 , 273, 4);
        missiles = new ArrayList<Missile>();
        missileStartTime = System.nanoTime();

        thread = new MainThread(getHolder(), this);
        //we can safely start the game loop
        thread.setRunning(true);
        thread.start();


    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {

        if(event.getAction()==MotionEvent.ACTION_DOWN){
            if(!player.getPlaying() && newGameCreated && reset)
            {
                player.setPlaying(true);
                player.setUp(true);
            }
            if(player.getPlaying())
            {

                if(!started)started = true;
                reset = false;
                player.setUp(true);
            }
            return true;
        }
        if(event.getAction()==MotionEvent.ACTION_UP)
        {
            player.setUp(false);
            return true;
        }

        return super.onTouchEvent(event);



    }

    public void update() {
        if (player.getPlaying()) {

            bg.update();
            player.update();
            //add missiles on timer
            long missileElapsed = (System.nanoTime() - missileStartTime) / 1000000;
            if (missileElapsed > (1000 - player.getScore() / 4)) {

                System.out.println("making missile");
                //first missile always goes down the middle
                if (missiles.size() == 0) {
                    missiles.add(new Missile(BitmapFactory.decodeResource(getResources(), R.drawable.
                            obstacle), WIDTH + 10, (int) (rand.nextDouble() * (HEIGHT))+60, 118, 190, player.getScore(), 1));
                } else {

                    missiles.add(new Missile(BitmapFactory.decodeResource(getResources(), R.drawable.obstacle),
                            WIDTH + 40,(int) (rand.nextDouble() * (HEIGHT))+60, 118, 190, player.getScore(), 1));


                }

                //reset timer
                missileStartTime = System.nanoTime();
            }
            //loop through every missile and check collision and remove
            for (int i = 0; i < missiles.size(); i++) {
                //update missile
                missiles.get(i).update();

                if (collision(missiles.get(i), player)) {
                    missiles.remove(i);
                    player.setPlaying(false);
                    break;
                }
                //remove missile if it is way off the screen
                if (missiles.get(i).getX() < -100) {
                    missiles.remove(i);
                    break;
                }
               if (missiles.get(i).getY() > 1250) {
                   System.out.println("y="+missiles.get(i).getY());
                   missiles.remove(i);

                   break;
                }
                if (i>=3&&missiles.get(i-3).getX() > 200){
                    System.out.println("obstacles are chuchu");
                    missiles.remove(i);
                }
               // if (i>3&&missiles.get(i-3).getY()<=600&&missiles.get(i-2).getY()<=600){
                 //   y=missiles.get(i).getY()+ 600;
                //}


                //remove missile if missile>3
                //if (missiles.get(i)==missiles.get(i-1)){
                //  missiles.remove(i);
                //

            }

        } else {
            player.resetDY();
            if (!reset) {
                newGameCreated = false;
                startReset = System.nanoTime();
                reset = true;
                dissapear = true;
                score=player.getScore();


            }
            if (score>best){
                best=score;
            }
            long resetElapsed = (System.nanoTime() - startReset) / 1000000;

            if (resetElapsed > 2500 && !newGameCreated)
            {
                newGame();
            }
        }
    }

    public void newGame() {
        dissapear = false;
        missiles.clear();

        player.resetDY();
        player.resetScore();
        player.setY(HEIGHT/2);

        newGameCreated = true;


    }





    public boolean collision(GameObject a, GameObject b)
    {
        if(Rect.intersects(a.getRectangle(),b.getRectangle()))
        {
            return true;
        }
        return false;
    }



    @SuppressLint("MissingSuperCall")
    @Override
    public void draw(Canvas canvas)

    {
        final float scaleFactorX = getWidth()/(WIDTH*1.f);
        final float scaleFacotrY = getHeight()/(HEIGHT*1.f);
        if (canvas!=null) {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFacotrY);
            bg.draw(canvas);
            player.draw(canvas);
            if(!dissapear) {
                player.draw(canvas);

            }
            //draw missiles
            for(Missile m: missiles)
            {
                m.draw(canvas);
            }
            drawText(canvas);
            canvas.restoreToCount(savedState);
        }
    }





    public void drawText(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(50);

        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("DISTANCE: " + (player.getScore()), 120, 170, paint);
        if (best==0){
            canvas.drawText("BEST: " + (player.getScore()), 120, 290, paint);
        }
        else
            canvas.drawText("BEST: " + best, 120,290, paint);

        if(!player.getPlaying()&&newGameCreated&&reset)
        {
            Paint paint1 = new Paint();
            paint1.setTextSize(90);
            paint1.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            canvas.drawText("PRESS TO START", WIDTH/2-20, HEIGHT/2, paint1);

            paint1.setTextSize(40);
            canvas.drawText("PRESS AND HOLD TO GO UP", WIDTH/2-20, HEIGHT/2 + 100, paint1);
            canvas.drawText("RELEASE TO GO DOWN", WIDTH/2-20, HEIGHT/2 + 200, paint1);

        }

    }



}