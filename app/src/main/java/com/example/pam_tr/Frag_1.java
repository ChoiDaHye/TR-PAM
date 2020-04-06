package com.example.pam_tr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class Frag_1 extends Fragment {

    public Frag_1(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //MapActivity ma = (MapActivity) getActivity();
        //String data = ma.getmydata();
        return inflater.inflate(R.layout.frag_maps, container, false);
    }

}
