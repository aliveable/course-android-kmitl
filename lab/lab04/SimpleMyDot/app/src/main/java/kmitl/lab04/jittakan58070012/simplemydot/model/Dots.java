package kmitl.lab04.jittakan58070012.simplemydot.model;

import java.util.LinkedList;

public class Dots {

    private OnDotsChangeListener listener;
    private LinkedList<Dot> keepDot = new LinkedList<>();

    public Dots() {
    }

    public interface OnDotsChangeListener{
        void onDotsChange(Dots dots);
    }

    public void clearDot(){
        keepDot.clear();
    }

    public LinkedList<Dot> getKeepDot() {
        return keepDot;
    }

    public void addDot(Dot dot){
        this.keepDot.add(dot);
        this.listener.onDotsChange(this);
    }

    public void deleteDot(Dot dot){
        this.keepDot.remove(dot);
        this.listener.onDotsChange(this);
    }

    public void setListener(OnDotsChangeListener listener) {
        this.listener = listener;
    }
}
