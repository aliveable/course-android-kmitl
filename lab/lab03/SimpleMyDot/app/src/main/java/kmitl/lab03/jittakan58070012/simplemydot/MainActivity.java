package kmitl.lab03.jittakan58070012.simplemydot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.Random;

import kmitl.lab03.jittakan58070012.simplemydot.model.Dot;
import kmitl.lab03.jittakan58070012.simplemydot.view.DotView;

import static kmitl.lab03.jittakan58070012.simplemydot.R.id.dotView;
import static kmitl.lab03.jittakan58070012.simplemydot.R.layout.activity_main;

public class MainActivity extends AppCompatActivity implements Dot.OnDotChangeListener, View.OnClickListener{

    private Dot dot;
    private DotView dotview;
    Button clearbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        clearbutton = (Button) findViewById(R.id.button2);
        clearbutton.setOnClickListener(this);
        dotview = (DotView) findViewById(R.id.dotView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // you may need the x/y location
        boolean check = true;
        int Coordinate[] = new int[2];
        dotview.getLocationOnScreen(Coordinate);
        if(event.getAction() == MotionEvent.ACTION_UP){
            int CenterX = (int) event.getX();
            int CenterY = (int) event.getY();
            CenterX = CenterX - Coordinate[0];
            CenterY = CenterY - Coordinate[1];

            /*if (dotview.getKeepDot()!= null){
                for(Dot point: dotview.getKeepDot()) {
                    if (dotRangeCheck(point, CenterX, CenterY)) {
                        dotview.getKeepDot().remove(point);
                        check = false;
                    }
                }
            }*/

            if (check){
                Random random = new Random();

                int intR = random.nextInt(255);
                int intG = random.nextInt(255);
                int intB = random.nextInt(255);

                Dot dot = new Dot(this, CenterX, CenterY, 50, intR, intG, intB);
            }

        }
            return true;
    }

    public void onRandomDot(View view) {
        Random random = new Random();
        int CenterX = random.nextInt(this.dotview.getWidth());
        int CenterY = random.nextInt(this.dotview.getHeight());
        int intR = random.nextInt(255);
        int intG = random.nextInt(255);
        int intB = random.nextInt(255);

        Dot dot = new Dot(this, CenterX, CenterY, 50, intR, intG, intB);
    }

    @Override
    public void onDotChanged(Dot dot) {
        dotview.setDot(dot);
        dotview.invalidate();
    }


    @Override
    public void onClick(View v) {
        dotview.clearDot();
        dotview.invalidate();
    }

    public boolean dotRangeCheck(Dot dot, int x, int y){
        double dis2P = Math.pow(Math.pow( (dot.getCenterX()- x) , 2 ) + Math.pow( (dot.getCenterY() - y) , 2) , 0.5);
        if (dis2P<=dot.getRadius()){
            return true;
        }else{
            return false;
        }
    }
}
