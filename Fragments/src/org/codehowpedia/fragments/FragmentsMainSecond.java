package org.codehowpedia.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentsMainSecond extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main_second, container, false);
    }

    // @Override
    // public void onStart() {
    // super.onStart();
    // Button btnText = (Button) getActivity().findViewById(R.id.btn_frag2);
    // btnText.setOnClickListener(new View.OnClickListener() {
    // @Override
    // public void onClick(View v) {
    // // getting the data from fragment #1
    // TextView txtView = (TextView) getActivity().findViewById(R.id.text_frag1);
    // Toast.makeText(getActivity(), txtView.getText(), Toast.LENGTH_SHORT).show();
    // }
    // });
    // }
}
