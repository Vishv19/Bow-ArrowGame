/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.applet.Applet;

/**
 *
 * @author Vishv Brahmbhatt
 */
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class Archer extends Applet implements Runnable,KeyListener{

    Thread t=null;
    int maxx=800, maxy=600;
    boolean dir=false,arrowmove=false,vermove=false;
    boolean stopFlag=false;
    int posx=60,posy=50,diffy=0;
    int speedx=30,speedy=10;   //speedx=arrow speed; speedy=target speed; Direct variation
    int programspeed=50;    //Inverse Variation
    int dimx=20,dimy=100,adimx=50;
    int count=0;


    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER)
            if (!vermove) arrowmove=true;
    }

    public void keyReleased(KeyEvent e) {
    }

    public void start(){
       resize(maxx,maxy);
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
            if (posy>=maxy-dimy||posy<=0)
                dir=!dir;
            if (dir)
                posy=posy+speedy;
            else
                posy=posy-speedy;
            if (posx>=maxx)
            {
                arrowmove=false;
                posx=60;
            }
            if (arrowmove)
            if (posx>=maxx-60&&posx<=maxx-30)
                if (posy<=maxy/2&&posy+dimy>=maxy/2)
                {
                    arrowmove=false;
                    posx=maxx-60;
                    vermove=true;
                    diffy=maxy/2-posy;
                    count=0;
                }
            if (arrowmove)
                posx=posx+speedx;
            Thread.sleep(programspeed);
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
        g.fillRect(maxx-60,posy,30,dimy);
        {
            int my=maxy/2;
            if (vermove)
                my = posy + diffy;
            g.drawLine(posx, my, posx-5, my-5);
            g.drawLine(posx, my, posx-5, my+5);
            g.drawLine(posx,my,posx-adimx,my);
            g.drawLine(posx-adimx, my, posx-5-adimx, my-5);
            g.drawLine(posx-adimx, my, posx-5-adimx, my+5);
            if (vermove)
            {
                count++;
                if (count>50)
                {
                        vermove=false;
                        posx=60;
                }
            }
        }
    }

}



