package kmitl.lab03.jittakan58070012.simplemydot;

import android.os.Parcel;
import android.os.Parcelable;



public class DotParcelable implements Parcelable {
    private int centerX;
    private int centerY;
    private int radius;
    private String Color;

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

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    protected DotParcelable(Parcel in) {
        centerX = in.readInt();
        centerY = in.readInt();
        radius = in.readInt();
        Color = in.readString();
    }

    public DotParcelable(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(centerX);
        dest.writeInt(centerY);
        dest.writeInt(radius);
        dest.writeString(Color);
    }

    public static final Creator<DotParcelable> CREATOR = new Creator<DotParcelable>() {
        @Override
        public DotParcelable createFromParcel(Parcel in) {
            return new DotParcelable(in);
        }

        @Override
        public DotParcelable[] newArray(int size) {
            return new DotParcelable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


}
