package kmitl.lab05.jittakan58070012.a01_lab05_workshop.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kmitl.lab05.jittakan58070012.a01_lab05_workshop.R;

public class MainFragment extends Fragment {

    public MainFragment() {
        // Required empty public constructor
    }
    String message = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_main, container, false);
        TextView fragmentTextview = rootView.findViewById(R.id.textView);
        if (!message.isEmpty()){
            fragmentTextview.setText(message);
        }

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        message = getArguments().getString("message");
    }

    public static MainFragment newInstance(String message) {

        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        args.putString("message", message);
        fragment.setArguments(args);
        return fragment;

    }

}
