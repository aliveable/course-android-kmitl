package kmitl.lab03.jittakan58070012.simplemydot.model;

public class Dot {
    private int centerX;
    private int centerY;
    private int radius;
    private OnDotChangeListener listener;

    public Dot(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;


    }

    public Dot(OnDotChangeListener listener,int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.listener = listener;
    }


    public void setListener(OnDotChangeListener listener) {
        this.listener = listener;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
        this.listener.onDotChanged(this);
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
        this.listener.onDotChanged(this);
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getCenterX() {

        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public int getRadius() {
        return radius;
    }

    public interface OnDotChangeListener{
        void onDotChanged(Dot dot);
    }

}
