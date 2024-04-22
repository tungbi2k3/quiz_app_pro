package com.example.quiz_app_demo.viewmodel;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quiz_app_demo.model.QuestionModel;
import com.example.quiz_app_demo.repository.QuestionRepository;

import java.util.HashMap;
import java.util.List;

public class QuestionViewModel extends ViewModel implements QuestionRepository.OnQuestionLoad, QuestionRepository.OnResultAdded, QuestionRepository.OnResultLoad {
    private MutableLiveData<List<QuestionModel>> questionMutableLiveData;
    private QuestionRepository repository;
    private MutableLiveData<HashMap<String , Long>> resultMutableLiveData;

    public MutableLiveData<HashMap<String, Long>> getResultMutableLiveData() {
        return resultMutableLiveData;
    }

    public void getResults(){
        repository.getResults();
    }

    public MutableLiveData<List<QuestionModel>> getQuestionMutableLiveData() {
        return questionMutableLiveData;
    }

    public QuestionViewModel(){
        questionMutableLiveData = new MutableLiveData<>();
        resultMutableLiveData = new MutableLiveData<>();
        repository = new QuestionRepository(this,this,this);
    }
    public void addResults(HashMap<String , Object> resultMap){
        repository.addResults(resultMap);
    }

    public void setQuizId(String quizId){
        repository.setQuizId(quizId);

    }
    public void setCurrentUserId(String currentUserId){
        repository.setCurrentUserId(currentUserId);

    }
    public void getQuestions (){
        repository.getQuestions();
    }

    @Override
    public void onLoad(List<QuestionModel> questionModels) {
        questionMutableLiveData.setValue(questionModels);
    }


    @Override
    public void onResultLoad(HashMap<String, Long> resultMap) {
        resultMutableLiveData.setValue(resultMap);
    }

    @Override
    public boolean onSubmit() {
        return true;
    }

    @Override
    public void onError(Exception e) {
        Log.d("QuizError", "onError: " + e.getMessage());
    }

}
