package compulsory;

import java.awt.*;

/**
 * class implemented by myself for drawing a circle
 */
public class Circle extends Polygon {
    public Circle(int x0, int y0, int radius) {
        double x;
        double y;
        double angle = 0;
        double angleAddition = 2 * Math.PI / (100 * radius);
        while (angle < 2 * Math.PI) {
            x = x0 + radius * Math.sin(angle);
            y = y0 + radius * Math.cos(angle);
            angle += angleAddition;
            this.addPoint((int) x, (int) y);
        }
    }
}
