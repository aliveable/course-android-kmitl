package kmitl.lab03.jittakan58070012.simplemydot.model;

import java.util.LinkedList;

/**
 * Created by student on 9/8/2017 AD.
 */

public class Dots {
    public interface OnDotsChangeListener{
        void onDotChange(Dots dots);
    }
    private OnDotsChangeListener listener;

    private LinkedList<Dot> keepDot = new LinkedList<>();

    public LinkedList<Dot> getKeepDot() {
        return keepDot;
    }

    public void addDot(Dot dot){
        this.keepDot.add(dot);
        this.listener.onDotChange(this);
    }

    public void setListener(OnDotsChangeListener listener) {
        this.listener = listener;
    }
}
