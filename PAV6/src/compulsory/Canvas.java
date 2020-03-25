package compulsory;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Clasa ce actioneaza ca un canvas pentru desene si deseneaza figuri dupa configuratiile
 * unei instante de ConfigurationPanel atunci cand se apasa pe mouse
 */
public class Canvas extends JPanel {
    private int x, y, radius;
    ConfigurationPanel configPanel;
    BufferedImage image;
    Graphics2D graphics2D;

    /**
     * init function for the canvas setting brackground border and a listener
     * A buffered image will be used for the drawings
     *
     * @param configurationPanel used to specify parameters for the shape being drawn
     */
    Canvas(ConfigurationPanel configurationPanel) {
        configPanel = configurationPanel;
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        setBackground(Color.WHITE);
        setDoubleBuffered(false);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
                /**
                 * option to rub with the rubber
                 */
                if (!configPanel.isRubberset()) {
                    if (configPanel.getRandomColor()) {
                        Random rand = new Random();
                        graphics2D.setColor(new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));
                    } else
                        graphics2D.setColor(Color.BLACK);
                    /**
                     * Optional added the option to switch between figures in the config panel
                     * we will use that value to draw either a circle or a polygon
                     */
                    switch (configPanel.getShape()) {
                        default: {
                            graphics2D.fill(new RegularPolygon(x, y, configPanel.getTheSize(), configPanel.getEdges()));
                            break;
                        }
                        case 1: {
                            graphics2D.fill(new Circle(x, y, configPanel.getTheSize()));
                            break;
                        }
                    }

                    repaint();
                }
                /**
                 * Filling the rubber space with a square of the size of the input
                 * (clicking intensifies)
                 */
                else {
                    graphics2D.setColor(configPanel.getRubberColor());
                    graphics2D.fill(new RegularPolygon(x,y,configPanel.getTheSize(),4));
                    repaint();
                }
            }

        });

    }

    /**
     * overrided the paintComponent such that the behaviour will be interpreted through our image
     * so we can add multiple shapes without refreshing
     *
     * @param g graphics of this pane
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image == null) {
            image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            graphics2D = image.createGraphics();
        }
        g.drawImage(image, 0, 0, this);
    }

    /**
     * Puts a large white rectangle over the image
     */
    public void reset() {
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillPolygon(new RegularPolygon(0, 0, 5000, 4));
        repaint();
    }

    /**
     * we simply save the image that we have drawn into onto the disk
     */
    public void save() {
        try {
            ImageIO.write(image, "PNG", new File("test.png"));
        } catch (IOException exceptionx) {
            System.err.println(exceptionx);
        }
    }

    /**
     * Optional component - saving using a file chooser
     * I implemented a file chooser to choose a directory
     * and an option pane to input the name of the file
     * The termination will implicitly be .png
     * We build the path from the file chooser + \ + name + .png
     */
    public void saveChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose a directory");
        fileChooser.setCurrentDirectory(new File("."));
        //only directories
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.showOpenDialog(this);

        JOptionPane optionPane = new JOptionPane();
        String fileName = JOptionPane.showInputDialog("Enter the name of the file:");
        File location = new File(fileChooser.getSelectedFile().getAbsolutePath() + "\\" + fileName + ".png");
        try {
            ImageIO.write(image, "PNG", location);
        } catch (IOException exceptionx) {
            System.err.println(exceptionx);
        }
    }

    /**
     * Optional - load a file using file chooser
     */
    public void loadChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose a png file:");
        fileChooser.setCurrentDirectory(new File("."));
        //only files
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.showOpenDialog(this);
        reset();
        try {
            image = ImageIO.read(new File(fileChooser.getSelectedFile().getAbsolutePath()));
            graphics2D = image.createGraphics();
            repaint();
        } catch (IOException exceptionx) {
            System.err.println(exceptionx);
        }
    }

    /**
     * In order to load an image we just get the image from the path
     * and create its graphics that will replace our existing ones for this pane
     */
    public void load() {
        reset();
        try {
            image = ImageIO.read(new File("test.png"));
            graphics2D = image.createGraphics();
            repaint();
        } catch (IOException exceptionx) {
            System.err.println(exceptionx);
        }
    }

}

