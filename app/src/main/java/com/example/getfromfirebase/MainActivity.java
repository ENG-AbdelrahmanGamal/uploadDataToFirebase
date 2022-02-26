package com.example.getfromfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
User user;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText editText_email,editText_phone,editText_name;
    private static final String TAG = "MainActivity";
    private Class<? extends User> userClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
editText_email=findViewById(R.id.signin_et_email);
editText_name=findViewById(R.id.signin_et_name);
editText_phone=findViewById(R.id.signin_et_phone);

        db.collection(constant.Collection).document(constant.getUserId).get( ).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
    @Override
    public void onComplete( Task<DocumentSnapshot> task) {

    }
});
db.collection(constant.Collection).document(constant.getUserId)
        .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
    @Override
    public void onComplete( Task<DocumentSnapshot> task) {
        if(task.isSuccessful())
        {
            Log.i(TAG, "onComplete: "+task.getResult().getName());
          //  user.setName(task.getResult().getClass().getName());


//editText_name.setText(user.name);
//editText_phone.setText(user.phone);
        }
        else {
            String error =task.getException().getLocalizedMessage();
            Log.i(TAG, "onComplete: "+error);
            Toast.makeText(MainActivity.this, ""+error, Toast.LENGTH_SHORT).show();
        }
    }
});

    }
}