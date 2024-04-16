//package com.example.quiz_app_demo;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.navigation.NavController;
//
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.view.View;
//
//import com.example.quiz_app_demo.views.AccountFragment;
//import com.example.quiz_app_demo.views.ListFragment;
//import com.example.quiz_app_demo.views.RankFragment;
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//import com.google.android.material.navigation.NavigationBarItemView;
//import com.google.android.material.navigation.NavigationBarView;
//
//public class bottom_nav_menu extends AppCompatActivity{
//    private BottomNavigationView bottomNavigationView;
//    ListFragment listFragment=new ListFragment();
//    RankFragment rankFragment=new RankFragment();
//    AccountFragment accountFragment=new AccountFragment();
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.home_fragment);
//
//        bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        getSupportFragmentManager().beginTransaction().replace(R.id.list_fragment, listFragment).commit();
//
//        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                if (item.getItemId() == R.id.navHome) {
//                    getSupportFragmentManager().beginTransaction().replace(R.id.list_fragment, listFragment).commit();
//                    return true;
//                } else if (item.getItemId() == R.id.navRank) {
//                    getSupportFragmentManager().beginTransaction().replace(R.id.rank_fragment, rankFragment).commit();
//                    return true;
//                } else {
//                    getSupportFragmentManager().beginTransaction().replace(R.id.account_fragment, accountFragment).commit();
//                    return true;
//                }
//
//            }
//        });
//    }
//
//
//}
