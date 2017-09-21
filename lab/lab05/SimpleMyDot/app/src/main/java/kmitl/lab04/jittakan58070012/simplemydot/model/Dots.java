package kmitl.lab04.jittakan58070012.simplemydot.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.LinkedList;

public class Dots implements Parcelable{

    private OnDotsChangeListener listener;
    private LinkedList<Dot> keepDot = new LinkedList<>();

    public Dots() {
    }

    protected Dots(Parcel in) {
    }

    public static final Creator<Dots> CREATOR = new Creator<Dots>() {
        @Override
        public Dots createFromParcel(Parcel in) {
            return new Dots(in);
        }

        @Override
        public Dots[] newArray(int size) {
            return new Dots[size];
        }
    };

    public void removeLastDot() {
        if(keepDot.isEmpty()){
            this.listener.onDotsChange(this);
        }else{
            this.keepDot.remove(keepDot.getLast());
            this.listener.onDotsChange(this);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public interface OnDotsChangeListener{
        void onDotsChange(kmitl.lab04.jittakan58070012.simplemydot.model.Dots dots);
    }

    public void clearDot(){
        keepDot.clear();
        this.listener.onDotsChange(this);
    }

    public LinkedList<Dot> getKeepDot() {
        return keepDot;
    }

    public void addDot(Dot dot){
        this.keepDot.add(dot);
        this.listener.onDotsChange(this);
    }

    public void changedot(int x, int y,int savex, int savey){
        for(final Dot point: keepDot) {
            if (dotRangeCheck(point, savex, savey)) {
                point.setCenterX(x);
                point.setCenterY(y);
                this.listener.onDotsChange(this);
                break;
            }
        }
    }
    public void deleteDot(Dot dot){
        this.keepDot.remove(dot);
        this.listener.onDotsChange(this);
    }

    public void changeColor(){
            this.listener.onDotsChange(this);

    }

    public Dot searchDot(int x, int y){
        Dot dotcheck = null;
        for(final Dot point: keepDot) {
            if (dotRangeCheck(point, x, y)) {
                dotcheck = point;
                break;
            }
        }
        return dotcheck;
    }

    public boolean dotRangeCheck(Dot dot, int x, int y){
        double dis2P = Math.pow(Math.pow( (dot.getCenterX()- x) , 2 ) + Math.pow( (dot.getCenterY() - y) , 2) , 0.5);
        if(dot != null){
            if (dis2P <= dot.getRadius()) {
                return true;
            }
        }
        return false;
    }


    public void setListener(OnDotsChangeListener listener) {
        this.listener = listener;
    }
}
