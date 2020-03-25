package compulsory;

import javafx.util.Pair;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class Canvas extends JPanel {
    private int x,y,radius;
    ConfigurationPanel configPanel;
    BufferedImage image;
    Graphics2D graphics2D;
    Canvas(ConfigurationPanel configurationPanel){
        configPanel = configurationPanel;
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        setBackground(Color.WHITE);
        setDoubleBuffered(false);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                    x = e.getX();
                    y = e.getY();
                    if(configPanel.getRandomColor()){
                        Random rand = new Random();
                        graphics2D.setColor(new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat()));
                    }
                    else
                        graphics2D.setColor(Color.BLACK);
                    graphics2D.fill(new RegularPolygon(x, y, configPanel.getTheSize(), configPanel.getEdges()));
                    repaint();
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(image == null){
            image = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
            graphics2D = image.createGraphics();
        }
        g.drawImage(image,0,0,this);
    }
    public void reset(){
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillPolygon(new RegularPolygon(0,0,5000,4));
        repaint();
    }

    public void save(){
        try{
            ImageIO.write(image,"PNG",new File("test.png"));
        }catch (IOException exceptionx){ System.err.println(exceptionx);}
    }
    public void load(){
        reset();
        try {
            image= ImageIO.read(new File("test.png"));
            graphics2D =  image.createGraphics();
            repaint();
        }catch (IOException exceptionx){ System.err.println(exceptionx);}
    }

}

