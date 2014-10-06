/**
 *
 * @author Vishv Brahmbhatt
 */

import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class Archer extends Applet implements Runnable,KeyListener{

    Thread t=null;
    int maxX=800, maxY=600;
    boolean dir=false, arrowMove=false, verMove=false;
    boolean stopFlag=false;
    int posX=60, posY=50, diffY=0;
    int speedX=30, speedY=10;   //speedX=arrow speed; speedY=target speed; Direct variation
    int programSpeed=50;    //Inverse Variation
    int dimX=20, dimY=100, adimX=50;
    int count=0;


    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER)
            if (!verMove) arrowMove=true;
    }

    public void keyReleased(KeyEvent e) {
    }

    public void start(){
       resize(maxX,maxY);
       addKeyListener(this);
       requestFocus();
       setForeground(Color.RED);
       setBackground(Color.lightGray);
       t=new Thread(this);
       stopFlag=false;
       t.start();
    }

    public void run() {
        for(;;)
        try
        {
            if (stopFlag) break;
            if (posY>=maxY-dimY||posY<=0)
                dir=!dir;
            if (dir)
                posY=posY+speedY;
            else
                posY=posY-speedY;
            if (posX>=maxX)
            {
                arrowMove=false;
                posX=60;
            }
            if (arrowMove)
            if (posX>=maxX-60&&posX<=maxX-30)
                if (posY<=maxY/2&&posY+dimY>=maxY/2)
                {
                    arrowMove=false;
                    posX=maxX-60;
                    verMove=true;
                    diffY=maxY/2-posY;
                    count=0;
                }
            if (arrowMove)
                posX=posX+speedX;
            Thread.sleep(programSpeed);
            repaint();
        }
        catch(InterruptedException ex) {}
    }

    public void stop()
    {
        stopFlag=true;
        t=null;
    }

    public void paint(Graphics g)
    {
        g.fillRect(maxX-60,posY,30,dimY);
        {
            int my=maxY/2;
            if (verMove)
                my = posY + diffY;
            g.drawLine(posX, my, posX-5, my-5);
            g.drawLine(posX, my, posX-5, my+5);
            g.drawLine(posX,my,posX-adimX,my);
            g.drawLine(posX-adimX, my, posX-5-adimX, my-5);
            g.drawLine(posX-adimX, my, posX-5-adimX, my+5);
            if (verMove)
            {
                count++;
                if (count>50)
                {
                        verMove=false;
                        posX=60;
                }
            }
        }
    }

}



