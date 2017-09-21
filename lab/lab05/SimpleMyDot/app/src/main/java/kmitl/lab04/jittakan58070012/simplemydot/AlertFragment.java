package kmitl.lab04.jittakan58070012.simplemydot;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import com.pes.androidmaterialcolorpickerdialog.ColorPicker;
import com.pes.androidmaterialcolorpickerdialog.ColorPickerCallback;

import kmitl.lab04.jittakan58070012.simplemydot.model.Dot;
import kmitl.lab04.jittakan58070012.simplemydot.model.Dots;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlertFragment extends DialogFragment {
    Dot dot;
    Dots dots;
    int check1;
    int check2;

    public AlertFragment() {
        // Required empty public constructor

    }

    public static AlertFragment newInstance(Dot dot, Dots dots) {
        Bundle args = new Bundle();
        args.putParcelable("Dot", dot);
        args.putParcelable("Dots", dots);
        args.putParcelable("Check1", dot);
        AlertFragment fragment = new AlertFragment();
        fragment.setArguments(args);
        return fragment;
    }




    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

       if (getArguments() != null){
           dot = getArguments().getParcelable("Dot");
           dots = getArguments().getParcelable("Dots");
           CharSequence text = "Can getArguments";
       }

        final String[] array = {"Delete","EditColor", "EditSize","EditCenter"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select Options");
        builder.setItems(array, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 1){
                    final ColorPicker cp = new ColorPicker(getActivity(), dot.getIntR(),dot.getIntG(),dot.getIntB());
                    cp.show();
                        cp.setCallback(new ColorPickerCallback() {
                            @Override
                            public void onColorChosen(@ColorInt int color) {
                                // Do whatever you want
                                // Examples
                                dot.setIntR(cp.getRed());
                                dot.setIntG(cp.getGreen());
                                dot.setIntB(cp.getBlue());
                                dots.changeColor();
                                cp.dismiss();

                            }
                        });

                }else if (which == 2){
                    final AlertDialog.Builder popDialog = new AlertDialog.Builder(getActivity());
                        final SeekBar seek = new SeekBar(getActivity());
                        seek.setMax(255);
                        seek.setKeyProgressIncrement(1);
                        popDialog.setMessage("Radius 0 - 255");
                        popDialog.setTitle("Change Radius");
                        popDialog.setView(seek);
                        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                dot.setRadius(progress);

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

                        CharSequence text = "Click to select New Dot";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(getActivity(), text, duration);
                        toast.show();
                        MainFragment.check = 1;

                        MainFragment.saveposx = MainFragment.Dotx;
                        MainFragment.saveposy = MainFragment.Doty;

                        if(MainFragment.check2 == 0){

                            MainFragment.check2 =1;
                        }


                    }else{
                        dots.getKeepDot().remove(dot);
                        dots.changeColor();
                    }

            }
        });

        return builder.create();
    }


}
