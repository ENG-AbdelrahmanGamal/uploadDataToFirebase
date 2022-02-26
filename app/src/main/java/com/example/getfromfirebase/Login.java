package com.example.getfromfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {
    EditText  login_et_email, login_et_password,login_et_name,login_et_phone;
Button login_bt_login;
FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

User user=new User("","","","");
    private static final String TAG = "Login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       login_et_email=findViewById(R.id.signin_et_email) ;
       login_et_password=findViewById(R.id.signin_et_password);
       login_et_name=findViewById(R.id.signin_et_name);
       login_et_phone=findViewById(R.id.signin_et_phone);
   login_bt_login=findViewById(R.id.login_Button_register);

        login_bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user.setEmail(login_et_email.getText().toString().trim());
              user.setPassword(login_et_password.getText().toString().trim());
              user.setName(login_et_name.getText().toString().trim());
              user.setPhone(login_et_phone.getText().toString().trim());
                if(user.getEmail().isEmpty()||user.getPassword().isEmpty()||user.getPhone().isEmpty()||
                user.getName().isEmpty())
                {
                    Toast.makeText(Login.this, "please fill your data require", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {

                }
                firebaseAuth.signInWithEmailAndPassword(user.email,user.password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Login.this, "login success", Toast.LENGTH_SHORT).show();
                         db.collection(constant.Collection).document(constant.getUserId).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                             @Override
                             public void onComplete( Task<Void> task) {

                             }
                         });
                            Intent intent=new Intent(Login.this,MainActivity.class);
                                   startActivity(intent);
                        }
                        else {
                            String error=task.getException().getLocalizedMessage();
                            Toast.makeText(Login.this, ""+error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }


}