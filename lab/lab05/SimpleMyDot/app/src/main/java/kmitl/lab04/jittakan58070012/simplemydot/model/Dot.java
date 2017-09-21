package kmitl.lab04.jittakan58070012.simplemydot.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Dot implements Parcelable{
    private int centerX;
    private int centerY;
    private int radius;
    private int intR;
    private int intG;
    private int intB;



    private OnDotChangeListener listener;

    public Dot(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;

    }

    public Dot(int centerX, int centerY, int radius, int intR, int intG, int intB) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.intR = intR;
        this.intG = intG;
        this.intB = intB;
    }

    public Dot(OnDotChangeListener listener,int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.listener = listener;

    }

    public Dot(OnDotChangeListener listener, int centerX, int centerY, int radius, int intR, int intG, int intB) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.intR = intR;
        this.intG = intG;
        this.intB = intB;
        this.listener = listener;
        this.listener.onDotChanged(this);
    }

    protected Dot(Parcel in) {
        centerX = in.readInt();
        centerY = in.readInt();
        radius = in.readInt();
        intR = in.readInt();
        intG = in.readInt();
        intB = in.readInt();
    }

    public static final Creator<Dot> CREATOR = new Creator<Dot>() {
        @Override
        public Dot createFromParcel(Parcel in) {
            return new Dot(in);
        }

        @Override
        public Dot[] newArray(int size) {
            return new Dot[size];
        }
    };

    public void setListener(OnDotChangeListener listener) {
        this.listener = listener;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
        //this.listener.onDotChanged(this);
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
        //this.listener.onDotChanged(this);
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getCenterX() {

        return centerX;
    }
    public void setIntR(int intR) {
        this.intR = intR;
    }

    public void setIntG(int intG) {
        this.intG = intG;
    }

    public void setIntB(int intB) {
        this.intB = intB;
    }

    public int getIntR() {
        return intR;
    }

    public int getIntG() {
        return intG;
    }

    public int getIntB() {
        return intB;
    }

    public int getCenterY() {
        return centerY;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(centerX);
        parcel.writeInt(centerY);
        parcel.writeInt(radius);
        parcel.writeInt(intR);
        parcel.writeInt(intG);
        parcel.writeInt(intB);
    }

    public interface OnDotChangeListener{
        void onDotChanged(Dot dot);
    }

}
