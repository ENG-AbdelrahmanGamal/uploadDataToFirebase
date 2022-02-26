package com.example.getfromfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    User user;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView editText_email, editText_name;
    private static final String TAG = "MainActivity";
    private Class<? extends User> userClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText_email=findViewById(R.id.tv_profile_email);
        editText_name=findViewById(R.id.tv_profile_name);

        Log.i(TAG, "abdo uid onCreate: "+ constant.getUserId);

        db.collection(constant.Collection).document(constant.getUserId)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete( Task<DocumentSnapshot> task) {
                Log.i(TAG, "abdo task onComplete: "+ Objects.requireNonNull(task.getResult()).toObject(User.class).toString());
            }
        });

        db.collection(constant.Collection).document(constant.getUserId)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete( Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                    Log.i(TAG, "abdo task onComplete: "+task.getResult().toString());
                    User user = task.getResult().toObject(User.class);

                    Log.i(TAG, "abdo user onComplete: "+ user.toString());


                    editText_name.setText(user.getName());
                    editText_email.setText(user.getEmail());
                }
                else {
                    String error = task.getException().getLocalizedMessage();
                    Log.i(TAG, "onComplete: "+error);
                    Toast.makeText(MainActivity.this, ""+error, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}