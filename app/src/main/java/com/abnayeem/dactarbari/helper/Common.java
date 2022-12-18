package com.abnayeem.dactarbari.helper;

import android.app.Activity;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class Common {
    public static void transferActivityFinished(Activity activity, String auth, String s) {
    }

    public static void setInputError(EditText editText, String data) {
        editText.requestFocus();
        editText.setError(data);
    }

    public static void makeText(Activity activity, String data){
        Toast.makeText(activity, data, Toast.LENGTH_SHORT).show();
    }

    public static void setDList(Activity activity, String name, String phone, String email, String address, String imageUrl) {

//        DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference();
//
//        HashMap<String, String> map = new HashMap<>();
//
//        String rootKey = Session.randomAlphaNumeric(10);
//
//        map.put("name", name);
//        map.put("phone", phone);
//        map.put("email", email);
//        map.put("address", address);
//        map.put("imageUrl", imageUrl);
//        map.put("root_key", rootKey);
//
//        dataRef.child(Constant.DB_NODE).child(rootKey).setValue(map).addOnCompleteListener(task -> {
//            if (task.isSuccessful()) {
//                makeText(activity, "Data added successful");
//            }
//        }).addOnFailureListener(e -> {
//            makeText(activity, e.getMessage());
//        });

    }
}
