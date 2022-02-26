package com.example.getfromfirebase;

import com.google.firebase.auth.FirebaseAuth;

public interface constant {
    final  String Collection="users";
    final  String userId=null;
final String getUserId= FirebaseAuth.getInstance().getCurrentUser().getUid();

}
