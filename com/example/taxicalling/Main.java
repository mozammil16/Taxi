package com.example.taxicalling;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity implements OnClickListener {
    Button login;
    Button register;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0132R.layout.main);
        initialize();
        this.login.setOnClickListener(this);
        this.register.setOnClickListener(this);
    }

    public void initialize() {
        this.login = (Button) findViewById(C0132R.id.bLogin);
        this.register = (Button) findViewById(C0132R.id.bRegister);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case C0132R.id.bLogin:
                startActivity(new Intent(this, Login.class));
            case C0132R.id.bRegister:
                startActivity(new Intent(this, Register.class));
            default:
        }
    }
}
