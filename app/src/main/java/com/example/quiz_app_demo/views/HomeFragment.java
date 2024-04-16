//package com.example.quiz_app_demo.views;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.navigation.NavController;
//import androidx.navigation.Navigation;
//
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.view.View;
//
//import com.example.quiz_app_demo.R;
//import com.example.quiz_app_demo.views.AccountFragment;
//import com.example.quiz_app_demo.views.ListFragment;
//import com.example.quiz_app_demo.views.RankFragment;
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//import com.google.android.material.navigation.NavigationBarItemView;
//import com.google.android.material.navigation.NavigationBarView;
//
//public class HomeFragment extends AppCompatActivity{
//    private BottomNavigationView bottomNavigationView;
//    private NavController navController;
//    ListFragment listFragment=new ListFragment();
//    RankFragment rankFragment=new RankFragment();
//    AccountFragment accountFragment=new AccountFragment();
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.home_fragment);
//
//        bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        navController= Navigation.findNavController(view);
//        getSupportFragmentManager().beginTransaction().replace(R.id.list_fragment, listFragment).commit();
//
//        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                if (item.getItemId() == R.id.navHome) {
//                    return true;
//                } else if (item.getItemId() == R.id.navRank) {
//                    navController.navigate(R.id.action_listFragment_to_rankFragment);
//                    return true;
//                } else {
//                    navController.navigate(R.id.action_listFragment_to_accountFragment);
//                    return true;
//                }
//
//            }
//        });
//    }
//
//
//}
