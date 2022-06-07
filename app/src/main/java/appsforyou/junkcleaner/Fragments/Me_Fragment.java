package appsforyou.junkcleaner.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import appsforyou.junkcleaner.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Me_Fragment extends Fragment {


    public Me_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_me_, container, false);
    }

}
