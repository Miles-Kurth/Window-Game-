import java.awt.*;
import java.awt.event.*;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import javax.swing.*;

public class Main extends JFrame{
    ActionListener taskPerformer = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            mX = (int) mousePos.getX();
            mY = (int) mousePos.getY();
        }
    };
    Timer timer = new Timer(50, taskPerformer);
//    timer

    static Toolkit toolkit = Toolkit.getDefaultToolkit();
    static int screenWidth = toolkit.getScreenSize().width;
    static int screenHeight = toolkit.getScreenSize().height;

    static Point mousePos = MouseInfo.getPointerInfo().getLocation();
    static int mX; static int mY;

    static JFrame window = new JFrame("You can't close me!");
    static int windowWidth = 400; static int windowHeight = 300;
    static int windowX = 500; static int windowY = 500;

    static JButton closeButton = new JButton();

    public static void main(String[] args) {
        mX = (int) mousePos.getX();
        mY = (int) mousePos.getY();


        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.setBounds(windowX,windowY,windowWidth,windowHeight);

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("test");
                moveWindow();
            }
        });
        window.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int winMX = e.getX() + windowX;
                int winMY = e.getY() + windowY;
                mX = (int) mousePos.getX();
                mY = (int) mousePos.getY();

                System.out.println(mX);
//                System.out.println(windowX + ",  " + winMX + " " + winMY);
                if (winMX >= windowX + 5 && winMX <= windowX + 22){
                    System.out.println("aaaaaa");
                }

            }
        });

        window.setVisible(true);

        while (true){
            mX = (int) mousePos.getX();
            mY = (int) mousePos.getY();
            System.out.println(mX);
//            mX = (int) mousePos.getX();
//            mY = (int) mousePos.getY();
//
//            if (mX >= windowX && mX <= windowX + 80){
//                System.out.println("aaaaaa");
//                System.exit(0);
//            }
        }
    }

    public static void checkMousePosition(int x, int y){
        System.out.println(x + " " + y);
    }

    public static void moveWindow(){
        int newX = (int)(Math.random() * (screenWidth - windowWidth) );
        int newY = (int)(Math.random() * (screenHeight - windowHeight) );
        window.setBounds(newX,newY,windowWidth,windowHeight);
        windowX = window.getX();
        windowY = window.getY();
    }




}
