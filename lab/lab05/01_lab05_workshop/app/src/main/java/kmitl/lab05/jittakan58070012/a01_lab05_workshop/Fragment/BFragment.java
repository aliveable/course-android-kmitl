package kmitl.lab05.jittakan58070012.a01_lab05_workshop.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kmitl.lab05.jittakan58070012.a01_lab05_workshop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BFragment extends Fragment {


    public BFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b, container, false);
    }

    public static BFragment newInstance() {
        
        Bundle args = new Bundle();
        
        BFragment fragment = new BFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
