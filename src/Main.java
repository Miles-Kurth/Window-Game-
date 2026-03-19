import java.awt.Frame;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Toolkit;

import java.awt.event.WindowStateListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JButton;


public class Main{

    //System
    static Toolkit toolkit = Toolkit.getDefaultToolkit();
    static int screenWidth = toolkit.getScreenSize().width;
    static int screenHeight = toolkit.getScreenSize().height;

    static String operatingSystem = System.getProperty("os.name");


    //Mouse
    public static int mX; public static int mY;


    //Graphics
    static JFrame window = new JFrame("You can't close me!");
    static int windowWidth = 400; static int windowHeight = 300;
    static int windowX = 250; static int windowY = 200;

    //Button
    static JButton closeButton = new JButton("CLOSE");
    static int closeButtonWidth = 90;
    static int closeButtonHeight = 35;


    public static void main(String[] args) {
        setOperatingSystemName();


        Timer timer = new Timer();
        TimerTask periodic = new TimerTask() {
            @Override
            public void run() {
                PointerInfo pointerInfo = MouseInfo.getPointerInfo();
                Point mousePos = pointerInfo.getLocation();
                mX = (int) mousePos.getX();
                mY = (int) mousePos.getY();
//                System.out.println(mX + " " + mY);

                windowX = window.getX();
                windowY = window.getY();
//                System.out.println(windowX + " " + windowY);
                checkWindow();

            }
        };
        timer.schedule(periodic,0,50);


        //Window
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.setBounds(windowX,windowY,windowWidth,windowHeight);
        window.add(closeButton);

        window.addWindowStateListener(new WindowStateListener() {
            public void windowStateChanged(WindowEvent e) {
                int newState = e.getNewState();

                if ((newState & Frame.MAXIMIZED_BOTH) != 0 || (e.getNewState() & Frame.ICONIFIED) == Frame.ICONIFIED) {
                    System.out.println("Edit detected!");
                    window.setState(Frame.NORMAL);
                    moveWindow();
                } else if ((newState & Frame.NORMAL) != 0) {
                    ;
                }
            }
        });

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Close detected!");
                moveWindow();
            }
        });
//        window.addMouseMotionListener(new MouseMotionAdapter() {
//            @Override
//            public void mouseMoved(MouseEvent e) {
//                int winMX = e.getX() + windowX;
//                int winMY = e.getY() + windowY;
////                mX = (int) mousePos.getX();
////                mY = (int) mousePos.getY();
//
////                System.out.println(mX);
////                System.out.println(windowX + "," + windowY + " ;  " + winMX + "," + winMY);
////                if (winMX >= windowX + 5 && winMX <= windowX + 22){
////                    System.out.println("aaaaaa");
////                }
//
//            }
//        });

        //Button


        window.setVisible(true);

        while (true){

        }

    } //End main


    public static void moveWindow(){
        int newX = (int)(Math.random() * (screenWidth - windowWidth) );
        int newY = (int)(Math.random() * (screenHeight - windowHeight) );
        window.setBounds(newX,newY,windowWidth,windowHeight);
        windowX = window.getX();
        windowY = window.getY();
        closeButton.setBounds(windowX - closeButtonWidth,windowY - closeButtonHeight,closeButtonWidth,closeButtonHeight);
    }

    public static void setOperatingSystemName(){
        if (operatingSystem.toLowerCase().contains("windows")){
            operatingSystem = "windows";
        }
        else if (operatingSystem.toLowerCase().contains("mac")){
            operatingSystem = "mac";
        }
        else{
            operatingSystem = "other";
        }
        System.out.println("Found operating system: " + operatingSystem);
    }

    public static void checkWindow(){
        window.setBounds(windowX,windowY,windowWidth,windowHeight);
        closeButton.setBounds(windowWidth - 90-2,windowHeight - 63-2,closeButtonWidth,closeButtonHeight);
    }

}
