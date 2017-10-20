package kmitl.lab04.jittakan58070012.simplemydot;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.pes.androidmaterialcolorpickerdialog.ColorPicker;
import com.pes.androidmaterialcolorpickerdialog.ColorPickerCallback;

import java.util.Random;

import kmitl.lab04.jittakan58070012.simplemydot.model.Dot;
import kmitl.lab04.jittakan58070012.simplemydot.model.Dots;
import kmitl.lab04.jittakan58070012.simplemydot.view.DotView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScreenFragment extends Fragment implements Dots.OnDotsChangeListener, View.OnClickListener{

    private Dots dots = new Dots();
    private DotView dotview;
    private View fragview;
    private int intR;
    private int intG;
    private int intB;


    public ScreenFragment() {
        // Required empty public constructor
    }

    public boolean dotRangeCheck(Dot dot, int x, int y){
        double dis2P = Math.pow(Math.pow( (dot.getCenterX()- x) , 2 ) + Math.pow( (dot.getCenterY() - y) , 2) , 0.5);
        if(dot != null){
            if (dis2P<=dot.getRadius()) {
                return true;
            }
        }
        return false;
    }

   /* @Override
    public boolean onTouchEvent(MotionEvent event) {

        // you may need the x/y location
        boolean check = true;
        int Coordinate[] = new int[2];
        fragview.getLocationOnScreen(Coordinate);
        if(event.getAction() == MotionEvent.ACTION_UP){
            int CenterX = (int) event.getX();
            int CenterY = (int) event.getY();
            CenterX = CenterX - Coordinate[0];
            CenterY = CenterY - Coordinate[1];

            final String[] array = {"Delete","Edit"};

            if (dots.getKeepDot() != null){

                for(final Dot point: dots.getKeepDot()) {

                    if (dotRangeCheck(point, CenterX, CenterY)) {
                       / check = false;
                        final ColorPicker cp = new ColorPicker(MainActivity.this, point.getIntR(), point.getIntG(), point.getIntB());
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Pick a color");
                        builder.setItems(array, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // the user clicked on colors[which]
                                if(which == 0){
                                    dots.deleteDot(point);

                                }else if(which ==1 ){


                                    cp.show();

                                    cp.setCallback(new ColorPickerCallback() {
                                        @Override
                                        public void onColorChosen(@ColorInt int color) {
                                            // Do whatever you want
                                            // Examples
                                            point.setIntR(cp.getRed());
                                            point.setIntG(cp.getGreen());
                                            point.setIntB(cp.getBlue());
                                            cp.dismiss();

                                            dots.changeColor();

                                        }
                                    });
                                }
                            }

                        });
                        builder.show();

                        break;
                    }

                }
            }

            if (check){
                Random random = new Random();

                intR = random.nextInt(255);
                intG = random.nextInt(255);
                intB = random.nextInt(255);

                Dot dot = new Dot(CenterX, CenterY, 50, intR, intG, intB);
                dots.addDot(dot);
            }

        }
        return super.onTouchEvent(event);
    }*/



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v = inflater.inflate(R.layout.activity_main, container, false);

        Button rant = v.findViewById(R.id.button);
        rant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Random random = new Random();
                    int CenterX = random.nextInt(fragview.getWidth());
                    int CenterY = random.nextInt(fragview.getHeight());
                    intR = random.nextInt(255);
                    intG = random.nextInt(255);
                    intB = random.nextInt(255);
                    dots.addDot(new Dot(CenterX, CenterY, 50, intR, intG, intB));
                dots.changeColor();

            }
        });

        View view = inflater.inflate(R.layout.fragment_screen,container,false);
        view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                boolean check = true;
                int Coordinate[] = new int[2];
                v.getLocationOnScreen(Coordinate);
                if(event.getAction() == MotionEvent.ACTION_UP){
                    //do somethingint
                    int CenterX = (int) event.getX();
                    int CenterY = (int) event.getY();
                    CenterX = CenterX - Coordinate[0];
                    CenterY = CenterY - Coordinate[1];
                    if (dots.getKeepDot() != null){
                        for(final Dot point: dots.getKeepDot()) {
                            if (dotRangeCheck(point, CenterX, CenterY)) {
                                dots.deleteDot(point);
                            }
                        }
                    }
                }
                return true;
            }
        });

//here the rest of your code

        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static ScreenFragment newInstance() {

        Bundle args = new Bundle();

        ScreenFragment fragment = new ScreenFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onDotsChange(Dots dots) {
        dotview.setDots(dots);
        dotview.invalidate();
    }
}
