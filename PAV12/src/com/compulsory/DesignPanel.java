package com.compulsory;

import javax.swing.*;
import java.awt.*;

/**
 * Panel that holds the instances
 * In here we do just some initialisation
 */
public class DesignPanel extends JPanel {

    public DesignPanel(Dimension frameDimension){
        setPreferredSize(new Dimension(frameDimension.width,frameDimension.height/10));
        setBackground(Color.WHITE);
    }
}
