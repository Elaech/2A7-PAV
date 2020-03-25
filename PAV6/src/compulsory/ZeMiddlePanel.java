//package compulsory;
//
//import javax.imageio.ImageIO;
//import javax.swing.*;
//import javax.swing.border.BevelBorder;
//import java.awt.*;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.awt.image.BufferedImage;
//import java.io.File;
//
//
///**
// * class that implements the functionality of the middle panel
// */
//
//public class ZeMiddlePanel extends JPanel {
//    private int width;
//    private int height;
//    private BufferedImage image;
//    private ZeTopPanel options;
//    private JPanel canvas;
//
//    public ZeMiddlePanel(int width,int height,java.awt.Color color,ZeTopPanel zeTopPanel) {
//        this.width =width;
//        this.height=height;
//        setMinimumSize(new Dimension(width,height));
//        options = zeTopPanel;
//        canvas = new JPanel();
//        canvas.setBackground(Color.white);
//        canvas.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
//        canvas.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                drawShape(e);
//            }
//        });
//        canvas.setMinimumSize(new Dimension(width,height));
//        canvas.setPreferredSize(new Dimension(width,height));
//        Graphics graphics = canvas.getGraphics();
//        add(canvas);
//    }
//
//    private void drawShape(MouseEvent event){
//        Graphics2D graphics = (Graphics2D) canvas.getGraphics();
//        graphics.setColor(new Color(0,0,0));
//        if(options.isRandomColor()){
//            graphics.setColor(new Color(
//                    (int)(Math.random()*255),
//                    (int)(Math.random()*255),
//                    (int)(Math.random()*255)
//            ));
//        }
//
//        graphics.fillPolygon(new RegularPolygon(event.getX(),event.getY(),options.getDrawSize(),options.getDrawEdges()));
//
//    }
//    public void clear(){
//        repaint();
//    }
//
//    public void save(){
//        try{
//        ImageIO.write(image,"png",new File("drawing.png"));
//        }catch (Exception ignored){}
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        if(image == null) {
//            image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
//            Graphics2D graphics = image.createGraphics();
//            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        }
//        g.drawImage(image, 0, 0, null);
//    }
//
//    public void load(){
//
//    }
//}
