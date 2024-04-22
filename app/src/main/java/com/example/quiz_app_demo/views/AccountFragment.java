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

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.quiz_app_demo.R;

import com.example.quiz_app_demo.viewmodel.QuestionViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.HashMap;

public class AccountFragment extends Fragment {
    private QuestionViewModel viewModel;
    private NavController navController1;
    private BottomNavigationView bottomNavigationView;
    private Button btnLogout;
    private TextView score;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel=new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(QuestionViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController1 = Navigation.findNavController(view);
        bottomNavigationView=view.findViewById(R.id.bottomNavigationView);
        btnLogout=view.findViewById(R.id.BthLogout);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navAccount) {
                    bottomNavigationView.setItemTextColor(ContextCompat.getColorStateList(getContext(), R.color.blue_200));
                    return true;
                } else if (item.getItemId() == R.id.navRank) {
                    navController1.navigate(R.id.action_accountFragment_to_HistoryFragment);
                    return true;
                } else if (item.getItemId() == R.id.navHome){
                    navController1.navigate(R.id.action_accountFragment_to_listFragment);
                    bottomNavigationView.setItemTextColor(ContextCompat.getColorStateList(getContext(), R.color.grey));
                    return true;
                }
                return false;
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController1.navigate(R.id.action_accountFragment_to_splashFragment);
            }
        });
    }
}