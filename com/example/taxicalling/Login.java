package com.example.taxicalling;

import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.internal.view.SupportMenu;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends ActionBarActivity implements OnClickListener {
    TextView f1;
    TextView f2;
    Button login;
    EditText password;
    EditText username;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0132R.layout.login);
        initialize();
        this.login.setOnClickListener(this);
    }

    public void initialize() {
        this.login = (Button) findViewById(C0132R.id.bLogin1);
        this.f1 = (TextView) findViewById(C0132R.id.tvFeedback1);
        this.f2 = (TextView) findViewById(C0132R.id.tvFeedback2);
        this.username = (EditText) findViewById(C0132R.id.etUsername);
        this.password = (EditText) findViewById(C0132R.id.etPassword);
    }

    public void onClick(View v) {
        if (v.getId() == C0132R.id.bLogin1) {
            this.f1.setText("");
            this.f2.setText("");
            if (this.username.getText().toString().equals("")) {
                this.f1.setText("username can't be blank");
                this.f1.setTextColor(SupportMenu.CATEGORY_MASK);
            } else if (this.password.getText().toString().equals("")) {
                this.f2.setText("password can't be blank");
                this.f2.setTextColor(SupportMenu.CATEGORY_MASK);
            } else {
                String result = "";
                result = new Service().get("login.php?username=" + this.username.getText().toString().trim() + "&password=" + this.password.getText().toString().trim());
                if (result.contains("true")) {
                    JSONObject j;
                    Editor p;
                    if (result.contains("customer")) {
                        try {
                            j = new JSONObject(result);
                            p = getSharedPreferences("MyPreferencesFileName", 0).edit();
                            p.putInt("id", Integer.parseInt(j.getString("id")));
                            p.putBoolean("login", true);
                            p.commit();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(getApplicationContext(), "logged in as customer", 1).show();
                        startActivity(new Intent(this, MyJourneyCustomer.class));
                    } else if (result.contains("driver")) {
                        try {
                            j = new JSONObject(result);
                            p = getSharedPreferences("MyPreferencesFileName", 0).edit();
                            p.putInt("id", Integer.parseInt(j.getString("id")));
                            p.putBoolean("login", true);
                            p.putString("city", j.getString("city"));
                            p.commit();
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                        Toast.makeText(getApplicationContext(), "logged in as driver", 1).show();
                        startActivity(new Intent(this, MyJourneyDriver.class));
                    }
                } else if (result.contains("false")) {
                    this.f1.setText("username/password incorrect");
                    this.f1.setTextColor(SupportMenu.CATEGORY_MASK);
                }
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0132R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == C0132R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
