package ph.edu.apc.kalye90s;

import android.graphics.Bitmap;
import android.graphics.Canvas;



public class Player extends GameObject {

    private Bitmap spritesheet;
    private static int score;

    private boolean up;
    private boolean playing;
    private static Animation animation = new Animation();
    private static long  startTime;


    public Player(Bitmap res, int w, int h, int numFrames)
    {
        x = 160;

        y = GamePanel.HEIGHT / 2;


        score = 0;

        height = h;

        width = w;


        Bitmap[] image = new Bitmap[numFrames];
        spritesheet = res;

        for (int i = 0; i < image.length; i++)
        {
            image[i] = Bitmap.createBitmap(spritesheet, i*width, 0, width, height);
        }

        animation.setFrames(image);
        animation.setDelay(100);
        startTime = System.nanoTime();

    }

    public void setUp(boolean b){up = b;}

    public  void update()
    {
        long elapsed = (System.nanoTime()-startTime)/1000000;
        if(elapsed>100)
        {
            score++;
            startTime = System.nanoTime();
        }
        animation.update();
        if(up){
            dy -=4;
        }
        else{
            dy +=4;
        }

        //speed when ascending and descending
        if(dy>5)dy = 5;
        if(dy<-5)dy = -5;
        y += dy*2;
        if (y>1180) y=1180;
        if (y<400) y=400;



    }


    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(animation.getImage(),x,y,null);
    }
    public int getScore(){return score;}
    public boolean getPlaying(){return playing;}
    public void setPlaying(boolean b){playing = b;}
    public void resetDY (){dy = 0;}
    public void resetScore(){score = 0;}
}








