package com.e.whatasillylife;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class OutputPage extends Fragment {

    String answer;

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
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.restart_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(OutputPage.this)
                        .navigate(R.id.outputPage_mainPage);
            }
        });
    }
}