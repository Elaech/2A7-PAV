package com.compulsory;

import javax.swing.*;
import java.awt.*;

/**
 * The panel that holds the settings
 * In here we do some init
 */
public class ControlPanel extends JPanel {
    public ControlPanel(Dimension frameDimension){
        setBorder(BorderFactory.createEmptyBorder(2, 50, 2, 50));
        GridLayout gridLayout = new GridLayout(2,3);
        gridLayout.setHgap(20);
        setLayout(gridLayout);
        setBackground(Color.gray);
        setPreferredSize(new Dimension(frameDimension.width,frameDimension.height/10));
    }

}
