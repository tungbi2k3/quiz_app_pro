package com.example.quiz_app_demo.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.quiz_app_demo.Adapter.HistoryResultAdapter;
import com.example.quiz_app_demo.Adapter.QuizListAdapter;
import com.example.quiz_app_demo.R;
import com.example.quiz_app_demo.model.QuizListModel;
import com.example.quiz_app_demo.model.ResultModel;
import com.example.quiz_app_demo.viewmodel.QuestionViewModel;
import com.example.quiz_app_demo.viewmodel.QuizListViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

public class HistoryFragment extends Fragment implements HistoryResultAdapter.OnItemClickedListner{

    private NavController navController,navController1;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private QuestionViewModel viewModel1;
    private QuizListViewModel viewModel;
    private HistoryResultAdapter adapter;
    private BottomNavigationView bottomNavigationView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel=new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(QuizListViewModel.class);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.listQuizRecyclerview);
        progressBar = view.findViewById(R.id.quizListProgressbar);
        navController = Navigation.findNavController(view);
        navController1 = Navigation.findNavController(view);
        bottomNavigationView=view.findViewById(R.id.bottomNavigationView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HistoryResultAdapter( this);
        recyclerView.setAdapter(adapter);


        viewModel.getQuizListLiveData().observe(getViewLifecycleOwner(), new Observer<List<QuizListModel>>() {
            @Override
            public void onChanged(List<QuizListModel> quizListModels) {
                progressBar.setVisibility(View.GONE);
                adapter.setQuizListModels(quizListModels);
                adapter.notifyDataSetChanged();
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navRank) {
                    bottomNavigationView.setItemTextColor(ContextCompat.getColorStateList(getContext(), R.color.blue_200));


                    return true;
                } else if (item.getItemId() == R.id.navHome) {
                    navController1.navigate(R.id.action_HistoryFragment_to_listFragment);
                    bottomNavigationView.setItemTextColor(ContextCompat.getColorStateList(getContext(), R.color.grey));
                    return true;
                } else if (item.getItemId() == R.id.navAccount){
                    navController1.navigate(R.id.action_HistoryFragment_to_accountFragment);
                    return true;
                }
                return false;
            }
        });
    }

    public void onItemClick(int position) {
        HistoryFragmentDirections.ActionHistoryFragmentToReviewFragment action=HistoryFragmentDirections.actionHistoryFragmentToReviewFragment();
        action.setPosition(position);
        navController.navigate(action);
    }
}