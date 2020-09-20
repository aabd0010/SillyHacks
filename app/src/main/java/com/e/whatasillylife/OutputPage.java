package com.e.whatasillylife;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class OutputPage extends Fragment {

    public String id;
    public String answer = "";
    public String getRandomNumber() {
        int x = (int) (Math.random() * ((18 - 1) + 1)) + 1;
        id = Integer.toString(x);
        return id;
    }
    public int setRating() {
        int x = (int) (Math.random() * ((5 - 1) + 1)) + 1;
        return x;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.output_page, container, false);
    }
    public void setAns(String str){
        Log.e("userstr", str);
        answer = str;

    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        id = getRandomNumber();
        int rate = setRating();
        super.onViewCreated(view, savedInstanceState);
        TextView dbAnswer = view.findViewById(R.id.textView4);
        ImageView avatar = view.findViewById(R.id.avatar);
        TextView dbComment = view.findViewById(R.id.ans_comment);
        RatingBar star = view.findViewById(R.id.ratingBar);
        star.setRating(rate);
        ((MainActivity) getActivity()).apiFunction(3, id, dbAnswer);
        ((MainActivity) getActivity()).apiImage(id, avatar);
        ((MainActivity) getActivity()).apiFunction(5, id, dbComment);
//        TextView answerBox = view.findViewById(R.id.your_answer_text);
//        ((MainActivity) getActivity()).setUser(answerBox);
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
                Log.e("userAns", answer);
//                TextView answerBox = view.findViewById(R.id.your_answer_text);
//                answerBox.setText(answer);
                NavHostFragment.findNavController(OutputPage.this)
                        .navigate(R.id.outputPageSelf);
            }
        });
    }
}