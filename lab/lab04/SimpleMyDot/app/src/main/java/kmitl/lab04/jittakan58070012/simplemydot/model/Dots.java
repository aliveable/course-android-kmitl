package kmitl.lab04.jittakan58070012.simplemydot.model;

import java.util.LinkedList;

public class Dots {

    private OnDotsChangeListener listener;
    private LinkedList<Dot> keepDot = new LinkedList<>();

    public Dots() {
    }

    public void removeLastDot() {
        if(keepDot.isEmpty()){
            this.listener.onDotsChange(this);
        }else{
            this.keepDot.remove(keepDot.getLast());
            this.listener.onDotsChange(this);
        }
    }

    public interface OnDotsChangeListener{
        void onDotsChange(kmitl.lab04.jittakan58070012.simplemydot.model.Dots dots);
    }

    public void clearDot(){
        keepDot.clear();
    }

    public LinkedList<Dot> getKeepDot() {
        return keepDot;
    }

    public void addDot(Dot dot){
        this.keepDot.add(dot);
        System.out.print("sssss");
        this.listener.onDotsChange(this);
    }

    public void deleteDot(Dot dot){
        this.keepDot.remove(dot);
        this.listener.onDotsChange(this);
    }

    public void changeColor(){
            this.listener.onDotsChange(this);

    }



    public void setListener(OnDotsChangeListener listener) {
        this.listener = listener;
    }
}
