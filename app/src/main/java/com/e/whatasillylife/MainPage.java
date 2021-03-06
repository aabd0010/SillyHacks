package com.e.whatasillylife;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

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
        TextView que = view.findViewById(R.id.question);
        TextView hint = view.findViewById(R.id.hint);
        ((MainActivity) getActivity()).apiFunction(id, que, hint);
        final View ansView = view;
        view.findViewById(R.id.surrender).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MainPage.this)
                        .navigate(R.id.mainPageSelf);
            }
        });
        view.findViewById(R.id.imageView4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MainPage.this)
                        .navigate(R.id. mainPage_startPage);
            }
        });

        FloatingActionButton fab = view.findViewById(R.id.search_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Are you looking for something ? :P", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        view.findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText userText = ansView.findViewById(R.id.answer);
                String user_answer = userText.getText().toString();
                ((MainActivity) getActivity()).retrieveData(user_answer);
                NavHostFragment.findNavController(MainPage.this)
                        .navigate(R.id.mainPage_outputPage);
            }
        });
    }
}