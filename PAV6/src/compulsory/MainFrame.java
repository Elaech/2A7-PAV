package compulsory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {
    private int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int heigth= Toolkit.getDefaultToolkit().getScreenSize().height;
    public void start(){
        setPreferredSize(new Dimension(width,heigth));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        ConfigurationPanel configPanel = new ConfigurationPanel();
        Canvas canvas = new Canvas(configPanel);
        ControlPanel controlPanel = new ControlPanel(canvas,this);

        add(configPanel,BorderLayout.NORTH);
        add(canvas,BorderLayout.CENTER);
        add(controlPanel,BorderLayout.SOUTH);
        setVisible(true);
    }
    public void exit(){
        dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
    }
}
