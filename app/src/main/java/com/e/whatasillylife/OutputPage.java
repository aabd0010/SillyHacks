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
import android.widget.TextView;

public class OutputPage extends Fragment {

    public String id, answer;
    public String getRandomNumber() {
        int x = (int) (Math.random() * ((18 - 1) + 1)) + 1;
        id = Integer.toString(x);
        return id;
    }
    public OutputPage(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.output_page, container, false);
    }
    public void setAns(String str){
        Log.d("userAns", str);
        this.answer = str;
//        TextView answerBox = myview.findViewById(R.id.your_answer_text);
//        answerBox.setText(answer);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        id = getRandomNumber();
        super.onViewCreated(view, savedInstanceState);
        TextView dbAnswer = view.findViewById(R.id.textView4);
        ImageView avatar = view.findViewById(R.id.avatar);
        TextView dbComment = view.findViewById(R.id.ans_comment);

        ((MainActivity) getActivity()).apiFunction(3, id, dbAnswer);
        ((MainActivity) getActivity()).apiImage(id, avatar);
        ((MainActivity) getActivity()).apiFunction(5, id, dbComment);
        view.findViewById(R.id.restart_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(OutputPage.this)
                        .navigate(R.id.outputPage_mainPage);
            }
        });
    }
}