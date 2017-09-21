package kmitl.lab04.jittakan58070012.simplemydot;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import com.pes.androidmaterialcolorpickerdialog.ColorPicker;
import com.pes.androidmaterialcolorpickerdialog.ColorPickerCallback;
import java.util.Random;
import kmitl.lab04.jittakan58070012.simplemydot.model.Dot;
import kmitl.lab04.jittakan58070012.simplemydot.model.Dots;
import kmitl.lab04.jittakan58070012.simplemydot.view.DotView;
import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;





/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements Dots.OnDotsChangeListener, DotView.OnDotViewPressListener,View.OnClickListener{


    private Dot dot;
    private Dots dots;

    public Dots getDots() {
        return dots;
    }

    private DotView dotview;
    private View rootView;
    private View view;
    private int intR;
    private int intG;
    private int intB;
    static int saveposx = 0;
    static int saveposy = 0;
    static int Dotx = 0;
    static int Doty = 0;

    static int check = 0;
    static int check2 = 0;

    public MainFragment() {
        // Required empty public constructor
    }



    public void initView(View rootView){
        dotview = rootView.findViewById(R.id.dotviewfragment);
        dotview.setOnDotViewPressListener(this);
        Button cbtn = rootView.findViewById(R.id.button2);
        Button rbtn = rootView.findViewById(R.id.button);
        Button ubtn = rootView.findViewById(R.id.undo);
        ubtn.setOnClickListener(this);
        rbtn.setOnClickListener(this);
        cbtn.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            dotview.setDots(dots);
        }
        dotview.invalidate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        this.rootView = inflater.inflate(R.layout.fragment_main, container,false);
        this.view = inflater.inflate(R.layout.slidebar, container, Boolean.parseBoolean(null));
        initView(rootView);
        dots = new Dots();
        dots.setListener(this);
        //viewgroup = container;

        //container.removeView(this.view);
        return rootView;
    }

    @Override
    public void onDotsChange(Dots dots) {
        dotview.setDots(dots);
        dotview.invalidate();
    }

    void showDialog(Dot dot,Dots dots) {


        DialogFragment newFragment = AlertFragment.newInstance(dot, dots);
        newFragment.show(getFragmentManager(), "dialog");
    }

    @Override
    public void onDotViewPressed(int x, int y){
        Dotx = x;
        Doty = y;

        if (dots.searchDot(x, y) == null && check == 0) {
            Random random = new Random();

            intR = random.nextInt(255);
            intG = random.nextInt(255);
            intB = random.nextInt(255);

            dots.addDot(new Dot(x, y, 50, intR, intG, intB));

        } else if (dots.searchDot(x, y) == null && check == 1 && check2 == 1) {
            dots.changedot(Dotx, Doty, saveposx, saveposy);
            check = 0;
            check2 = 0;
        }

    }
    @Override
    public void onDotViewLongPressed(int x, int y) {
        if (dots.searchDot(x, y) != null) {
            showDialog(dots.searchDot(x, y), dots);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button2:
                onClearDot(view);
                break;
            case R.id.button:
                onRandomDot(view);
                break;
            case R.id.undo:
                onUndoDot(view);
        }
    }

    private void onUndoDot(View view) {
        dots.removeLastDot();
    }

    private void onRandomDot(View view) {
        Random random = new Random();
        int CenterX = random.nextInt(this.dotview.getWidth());
        int CenterY = random.nextInt(this.dotview.getHeight());
        intR = random.nextInt(255);
        intG = random.nextInt(255);
        intB = random.nextInt(255);

        dots.addDot(new Dot(CenterX, CenterY, 50, intR, intG, intB));
    }

    private void onClearDot(View view) {
        dots.clearDot();
    }





}
