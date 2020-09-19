package com.e.whatasillylife;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.gson.Gson;

public class MainPage extends Fragment {
    public String id;

    public String getRandomNumber() {
        int x = (int) (Math.random() * ((20 - 1) + 1)) + 1;
        id = Integer.toString(x);
        return id;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.main_page, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        id = getRandomNumber();
        super.onViewCreated(view, savedInstanceState);
        String question = ((MainActivity) getActivity()).apiFunction(1, "1");
//
//        Gson gson = new Gson();
//        String json = gson.toJson(question);
//        TextView t = view.findViewById(R.id.question);
//        t.setText(question);

        view.findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MainPage.this)
                        .navigate(R.id.mainPage_outputPage);
            }
        });
    }
}