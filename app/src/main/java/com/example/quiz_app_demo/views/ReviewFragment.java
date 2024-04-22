package com.example.quiz_app_demo.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.quiz_app_demo.R;
import com.example.quiz_app_demo.viewmodel.QuestionViewModel;

import java.util.HashMap;

public class ReviewFragment extends Fragment {
    private NavController navController;
    private QuestionViewModel viewModel;
    private TextView correctAnswer , wrongAnswer , notAnswered;
    private TextView scoreTv,judgement;
    private ProgressBar scoreProgressbar;
    private String quizId, currentUserId;
    private Button backBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this , ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(QuestionViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        correctAnswer = view.findViewById(R.id.correctAnswerTv);
        wrongAnswer = view.findViewById(R.id.wrongAnswersTv);
        notAnswered = view.findViewById(R.id.notAnsweredTv);
        scoreTv = view.findViewById(R.id.scoreTv);
        scoreProgressbar = view.findViewById(R.id.resultCoutProgressBar);
        backBtn = view.findViewById(R.id.back_btn);

        quizId = ReviewFragmentArgs.fromBundle(getArguments()).getQuizId();

        viewModel.setQuizId(quizId);
        viewModel.getResults();
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_reviewFragment_to_HistoryFragment);
            }
        });



        viewModel.getResultMutableLiveData().observe(getViewLifecycleOwner(), new Observer<HashMap<String, Long>>() {
            @Override
            public void onChanged(HashMap<String, Long> stringLongHashMap) {
                Long correct = stringLongHashMap.get("correct");
                Long wrong = stringLongHashMap.get("wrong");
                Long noAnswer = stringLongHashMap.get("notAnswered");
                Long score = stringLongHashMap.get("Score");

                correctAnswer.setText(correct != null ? correct.toString() : "0");
                wrongAnswer.setText(wrong != null ? wrong.toString() : "0");
                notAnswered.setText(noAnswer != null ? noAnswer.toString() : "0");
                scoreTv.setText(score != null ? score.toString() : "0");
            }
        });
    }
}