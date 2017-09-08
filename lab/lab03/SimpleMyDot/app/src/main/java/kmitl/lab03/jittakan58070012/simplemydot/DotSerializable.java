package kmitl.lab03.jittakan58070012.simplemydot;

import java.io.Serializable;

/**
 * Created by student on 9/8/2017 AD.
 */

public class DotSerializable implements Serializable{
    private int centerX;
    private int centerY;
    private int radius;
    private String Color;

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
