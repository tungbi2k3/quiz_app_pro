package com.example.quiz_app_demo.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.quiz_app_demo.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AccountFragment extends Fragment {
    private NavController navController1;
    private BottomNavigationView bottomNavigationView;

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
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navAccount) {
                    bottomNavigationView.setItemTextColor(ContextCompat.getColorStateList(getContext(), R.color.blue_200));
                    return true;
                } else if (item.getItemId() == R.id.navRank) {
                    navController1.navigate(R.id.action_accountFragment_to_rankFragment);
                    return true;
                } else if (item.getItemId() == R.id.navHome){
                    navController1.navigate(R.id.action_accountFragment_to_listFragment);
                    bottomNavigationView.setItemTextColor(ContextCompat.getColorStateList(getContext(), R.color.grey));
                    return true;
                }
                return false;
            }
        });
    }
}