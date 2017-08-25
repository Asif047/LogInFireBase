package com.example.asif.firebaseauthdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{


    private TextView profileTV;
    private Button logoutBtn;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth=FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()==null)
        {
            finish();
            startActivity(new Intent(this,LogInActivity.class));
        }

        FirebaseUser user=firebaseAuth.getCurrentUser();


        profileTV= (TextView) findViewById(R.id.profile_textview);
        logoutBtn= (Button) findViewById(R.id.log_out_button);


        //String temp_name=getIntent().getStringExtra("user_name");
        //Toast.makeText(ProfileActivity.this,temp_name,Toast.LENGTH_LONG).show();
        if(user.getDisplayName()!=null)
        profileTV.setText("Welcome "+user.getDisplayName());
        if(user.getDisplayName()==null)
            profileTV.setText("Welcomeee "+getIntent().getStringExtra("user_name"));



        logoutBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(this,LogInActivity.class));
    }
}
