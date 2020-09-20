package com.e.whatasillylife;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class OutputPage extends Fragment {

    public String id;

    public String getRandomNumber() {
        int num = (int) (Math.random() * ((18 - 1) + 1)) + 1;
        id = Integer.toString(num);
        return id;
    }

    public int setRating() {
        int rate = (int) (Math.random() * ((5 - 1) + 1)) + 1;
        return rate;
    }

    public int setAge() {
        int age = (int) (Math.random() * ((100 + 1) + 1)) - 1;
        return age;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.output_page, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        id = getRandomNumber();
        int rate = setRating();
        int age = setAge();
        super.onViewCreated(view, savedInstanceState);
        TextView dbAnswer = view.findViewById(R.id.textView4);
        ImageView avatar = view.findViewById(R.id.avatar);
        TextView dbComment = view.findViewById(R.id.ans_comment);
        RatingBar star = view.findViewById(R.id.ratingBar);
        star.setRating(rate);
        TextView ageView = view.findViewById(R.id.estimated_age);
        ageView.append(" " + Integer.toString(age) + " years old !");
        ((MainActivity) getActivity()).apiOutput(id, dbAnswer, avatar, dbComment);

        TextView answerBox = view.findViewById(R.id.your_answer_text);
        answerBox.setText(((MainActivity) getActivity()).getAnswer());

        view.findViewById(R.id.restart_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(OutputPage.this)
                        .navigate(R.id.outputPage_mainPage);
            }
        });
        view.findViewById(R.id.button_answer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                TextView answerBox = view.findViewById(R.id.your_answer_text);
//                answerBox.setText(answer);
                NavHostFragment.findNavController(OutputPage.this)
                        .navigate(R.id.outputPageSelf);
            }
        });
    }
}