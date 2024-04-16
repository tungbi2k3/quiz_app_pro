package com.example.quiz_app_demo.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.example.quiz_app_demo.Adapter.QuizListAdapter;
import com.example.quiz_app_demo.R;
import com.example.quiz_app_demo.model.QuizListModel;
import com.example.quiz_app_demo.viewmodel.AuthViewModel;
import com.example.quiz_app_demo.viewmodel.QuizListViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;


public class ListFragment extends Fragment implements QuizListAdapter.OnItemClickedListner{

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private NavController navController,navController1;
    private QuizListViewModel viewModel;
    private QuizListAdapter adapter;
    private BottomNavigationView bottomNavigationView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
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

//        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        adapter = new QuizListAdapter(this);

        bottomNavigationView=view.findViewById(R.id.bottomNavigationView);

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
                if (item.getItemId() == R.id.navHome) {
                    return true;
                } else if (item.getItemId() == R.id.navRank) {
                    navController1.navigate(R.id.action_listFragment_to_rankFragment);
                    return true;
                } else if (item.getItemId() == R.id.navAccount){
                    navController1.navigate(R.id.action_listFragment_to_accountFragment);
                    return true;
                }
                return false;
            }
        });
    }
    public void onItemClick(int position) {
        ListFragmentDirections.ActionListFragmentToDetailFragment action =
                ListFragmentDirections.actionListFragmentToDetailFragment();
        action.setPosition(position);
        navController.navigate(action);
    }
}