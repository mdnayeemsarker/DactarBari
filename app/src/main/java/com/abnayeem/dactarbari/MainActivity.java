package com.abnayeem.dactarbari;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.abnayeem.dactarbari.helper.Common;
import com.abnayeem.dactarbari.helper.Session;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private Activity activity;
    private Session session;

    private MaterialButton submitButton;
    private EditText nameInputEdt, phoneInputEdt, emailInputEdt, addressInputEdt, imageUrlInputEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        define();
        work();
    }

    private void define() {

        nameInputEdt = findViewById(R.id.nameInputEdt);
        phoneInputEdt = findViewById(R.id.phoneInputEdt);
        emailInputEdt = findViewById(R.id.emailInputEdt);
        addressInputEdt = findViewById(R.id.addressInputEdt);
        imageUrlInputEdt = findViewById(R.id.imageUrlInputEdt);
        submitButton = findViewById(R.id.submitButton);
    }

    private void work() {

        activity = this;
        session = new Session(activity);

        submitButton.setOnClickListener(this::DList);

    }

    private void DList(View view) {

        String name = nameInputEdt.getText().toString();
        String phone = phoneInputEdt.getText().toString();
        String email = emailInputEdt.getText().toString();
        String address = addressInputEdt.getText().toString();
        String imageUrl = imageUrlInputEdt.getText().toString();

        if (TextUtils.isEmpty(name)){
            Common.setInputError(nameInputEdt, "Field is Mandatory");
        }else if (TextUtils.isEmpty(phone)){
            Common.setInputError(nameInputEdt, "Field is Mandatory");
        }else if (TextUtils.isEmpty(email)){
            Common.setInputError(nameInputEdt, "Field is Mandatory");
        }else if (TextUtils.isEmpty(address)){
            Common.setInputError(nameInputEdt, "Field is Mandatory");
        }else {
            if (imageUrl.equals(""))
                imageUrl = "imageUrl";
            Common.setDList(activity, name, phone, email, address, imageUrl);
        }


    }
}