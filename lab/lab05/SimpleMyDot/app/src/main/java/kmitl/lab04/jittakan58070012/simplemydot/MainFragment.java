package kmitl.lab04.jittakan58070012.simplemydot;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
    private SeekBar seek;
    private SeekBar seek2;
    private AlertDialog.Builder popDialog;
    private int intR;
    private int intG;
    private int intB;
    int saveposx = 0;
    int saveposy = 0;
    private ViewGroup viewgroup;
    private String m_Text = "";
    int check = 0;
    int check2 = 0;
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

        container.removeView(this.view);
        return rootView;
    }

    @Override
    public void onDotsChange(Dots dots) {
        dotview.setDots(dots);
        dotview.invalidate();
    }


    @Override
    public void onDotViewPressed(int x, int y) {
        final int Dotx = x;
        final int Doty = y;

        if(dots.searchDot(x,y) == null && check == 0 ){
            Random random = new Random();

            intR = random.nextInt(255);
            intG = random.nextInt(255);
            intB = random.nextInt(255);

            dots.addDot(new Dot(x, y, 50, intR, intG, intB));

        }else if (dots.searchDot(x,y) == null && check == 1 && check2 == 1 ){
            dots.changedot(Dotx, Doty, saveposx, saveposy);
            check=0;
            check2=0;
        } else{
            final String[] array = {"Delete","EditColor", "EditSize","EditCenter"};
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Select Options");
            builder.setItems(array, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // the user clicked on colors[which]
                    if (which == 1){
                        final ColorPicker cp = new ColorPicker(getActivity(), dots.searchDot(Dotx, Doty).getIntR(), dots.searchDot(Dotx, Doty).getIntG(), dots.searchDot(Dotx, Doty).getIntB());
                        cp.show();
                        cp.setCallback(new ColorPickerCallback() {
                            @Override
                            public void onColorChosen(@ColorInt int color) {
                                // Do whatever you want
                                // Examples
                                dots.searchDot(Dotx, Doty).setIntR(cp.getRed());
                                dots.searchDot(Dotx, Doty).setIntG(cp.getGreen());
                                dots.searchDot(Dotx, Doty).setIntB(cp.getBlue());
                                dots.changeColor();
                                cp.dismiss();

                            }
                        });


                    }else if(which == 2){
                        final AlertDialog.Builder popDialog = new AlertDialog.Builder(getActivity());
                        final SeekBar seek = new SeekBar(getActivity());
                        seek.setMax(255);
                        seek.setKeyProgressIncrement(1);

                        popDialog.setTitle("Change Radius");
                        popDialog.setView(seek);
                        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                                dots.searchDot(Dotx, Doty).setRadius(progress);


                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {


                            }
                        });

                        popDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                dots.changeColor();
                                dialog.dismiss();

                            }
                        });

                    popDialog.show();
                    }else if (which == 3){
//

                        CharSequence text = "Click to select New Dot";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(getActivity(), text, duration);
                        toast.show();
                        check = 1;
                        saveposx = Dotx;
                        saveposy = Doty;

                        if(check2 == 0){

                            check2 =1;
                        }


                    }else{
                        dots.getKeepDot().remove(dots.searchDot(Dotx,Doty));
                        dots.changeColor();
                    }
                }

            });builder.show();
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
