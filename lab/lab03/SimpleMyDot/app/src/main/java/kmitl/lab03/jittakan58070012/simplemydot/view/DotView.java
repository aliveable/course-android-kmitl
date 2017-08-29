package kmitl.lab03.jittakan58070012.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Iterator;
import java.util.LinkedList;

import kmitl.lab03.jittakan58070012.simplemydot.model.Dot;


public class DotView extends View {

    private Paint paint;
    private Dot dot;
    private LinkedList<Dot> keepDot = new LinkedList<>();

    public void setDot(Dot dot) {
        this.dot = dot;
    }

    public LinkedList<Dot> getKeepDot() {
        return keepDot;
    }

    public void clearDot(){
        dot = null;
        keepDot.clear();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        keepDot.add(dot);
        for (Dot point: keepDot) {
            if (point != null) {
                paint.setARGB(150,point.getIntR(), point.getIntG(), point.getIntB());
                canvas.drawCircle(point.getCenterX(), point.getCenterY(), point.getRadius(), paint);
            }
        }

    }


    public DotView(Context context) {
        super(context);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();

    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }
}
