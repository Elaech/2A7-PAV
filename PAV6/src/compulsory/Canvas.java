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

/**
 * Clasa ce actioneaza ca un canvas pentru desene si deseneaza figuri dupa configuratiile
 * unei instante de ConfigurationPanel atunci cand se apasa pe mouse
 */
public class Canvas extends JPanel {
    private int x,y,radius;
    ConfigurationPanel configPanel;
    BufferedImage image;
    Graphics2D graphics2D;

    /**
     * init function for the canvas setting brackground border and a listener
     * A buffered image will be used for the drawings
     * @param configurationPanel used to specify parameters for the shape being drawn
     */
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

    /**
     * overrided the paintComponent such that the behaviour will be interpreted through our image
     * so we can add multiple shapes without refreshing
     * @param g graphics of this pane
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(image == null){
            image = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
            graphics2D = image.createGraphics();
        }
        g.drawImage(image,0,0,this);
    }

    /**
     * Puts a large white rectangle over the image
     */
    public void reset(){
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillPolygon(new RegularPolygon(0,0,5000,4));
        repaint();
    }

    /**
     * we simply save the image that we have drawn into onto the disk
     */
    public void save(){
        try{
            ImageIO.write(image,"PNG",new File("test.png"));
        }catch (IOException exceptionx){ System.err.println(exceptionx);}
    }

    /**
     * In order to load an image we just get the image from the path
     * and create its graphics that will replace our existing ones for this pane
     */
    public void load(){
        reset();
        try {
            image= ImageIO.read(new File("test.png"));
            graphics2D =  image.createGraphics();
            repaint();
        }catch (IOException exceptionx){ System.err.println(exceptionx);}
    }

}

