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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quiz_app_demo.R;
import com.example.quiz_app_demo.viewmodel.AuthViewModel;
import com.google.firebase.auth.FirebaseUser;


public class SignUpFragment extends Fragment {

    private AuthViewModel viewModel;
    private NavController navController;
    private EditText editEmail, editPass, editUsername;
    private TextView signInText;
    private Button signUpBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController= Navigation.findNavController(view);
        editEmail=view.findViewById(R.id.editEmailSignUp);
        editUsername=view.findViewById(R.id.editUsername);
        editPass=view.findViewById(R.id.editPassSignUp);
        signInText=view.findViewById(R.id.signInText);
        signUpBtn=view.findViewById(R.id.signUpBtn);

        signInText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_signUpFragment_to_signinFragment);
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email =editEmail.getText().toString();
                String pass=editPass.getText().toString();
                String username=editUsername.getText().toString();
                if (!email.isEmpty()&& !pass.isEmpty()&& !username.isEmpty()){
                    viewModel.signUp(email,pass,username);
                    Toast.makeText(getContext(),"Registered Successfully",Toast.LENGTH_SHORT).show();
                    viewModel.getFirebaseUserMutableLiveData().observe(getViewLifecycleOwner(), new Observer<FirebaseUser>() {
                        @Override
                        public void onChanged(FirebaseUser firebaseUser) {
                            if (firebaseUser !=null){
                                navController.navigate(R.id.action_signUpFragment_to_signinFragment);
                            }
                        }
                    });
                }else{
                    Toast.makeText(getContext(),"Please Enter your Email and Password!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel=new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(AuthViewModel.class);
    }
}