package kmitl.lab04.jittakan58070012.simplemydot;


import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pes.androidmaterialcolorpickerdialog.ColorPicker;
import com.pes.androidmaterialcolorpickerdialog.ColorPickerCallback;

import kmitl.lab04.jittakan58070012.simplemydot.model.Dots;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorPickerfragment extends DialogFragment {


    private Dots dots;

    public ColorPickerfragment() {
        // Required empty public constructor
    }

    public static ColorPickerfragment newInstance() {
        
        Bundle args = new Bundle();
        
        ColorPickerfragment fragment = new ColorPickerfragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final ColorPicker cp = new ColorPicker(getActivity(), 100, 100, 100);
                        cp.show();
                        cp.setCallback(new ColorPickerCallback() {
                            @Override
                            public void onColorChosen(@ColorInt int color) {
                                // Do whatever you want
                                // Examples
                                //dots.searchDot(Dotx, Doty).setIntR(cp.getRed());
                                //dots.searchDot(Dotx, Doty).setIntG(cp.getGreen());
                                //dots.searchDot(Dotx, Doty).setIntB(cp.getBlue());
                                //dots.changeColor();
                                cp.dismiss();

                            }
                        });
        return super.onCreateDialog(savedInstanceState);
    }
}
