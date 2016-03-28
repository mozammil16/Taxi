package com.example.taxicalling;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends Activity implements OnClickListener {
    Button f2c;
    TextView c1;
    TextView c2;
    TextView c3;
    TextView c4;
    TextView c5;
    EditText ca;
    EditText cb;
    EditText cc;
    EditText cd;
    EditText ce;
    Button f3d;
    TextView d1;
    TextView d2;
    TextView d3;
    TextView d4;
    TextView d5;
    TextView d6;
    EditText da;
    EditText db;
    EditText dc;
    EditText dd;
    EditText de;
    EditText df;
    TabHost th;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0132R.layout.register);
        this.th = (TabHost) findViewById(C0132R.id.tabhost);
        this.th.setup();
        TabSpec specs = this.th.newTabSpec("tag1");
        specs.setContent(C0132R.id.tab1);
        specs.setIndicator("Customer");
        this.th.addTab(specs);
        TabSpec specs1 = this.th.newTabSpec("tag2");
        specs1.setContent(C0132R.id.tab2);
        specs1.setIndicator("Driver");
        this.th.addTab(specs1);
        initialize();
    }

    public void initialize() {
        this.f2c = (Button) findViewById(C0132R.id.bUser);
        this.f3d = (Button) findViewById(C0132R.id.bDriver);
        this.c1 = (TextView) findViewById(C0132R.id.textView1);
        this.c2 = (TextView) findViewById(C0132R.id.textView2);
        this.c3 = (TextView) findViewById(C0132R.id.textView3);
        this.c4 = (TextView) findViewById(C0132R.id.textView4);
        this.c5 = (TextView) findViewById(C0132R.id.textView5);
        this.d1 = (TextView) findViewById(C0132R.id.textView11);
        this.d2 = (TextView) findViewById(C0132R.id.textView12);
        this.d3 = (TextView) findViewById(C0132R.id.textView13);
        this.d4 = (TextView) findViewById(C0132R.id.textView14);
        this.d5 = (TextView) findViewById(C0132R.id.textView15);
        this.d6 = (TextView) findViewById(C0132R.id.textView6);
        this.ca = (EditText) findViewById(C0132R.id.etUsername);
        this.cb = (EditText) findViewById(C0132R.id.etPassword);
        this.cc = (EditText) findViewById(C0132R.id.etName);
        this.cd = (EditText) findViewById(C0132R.id.etEmail);
        this.ce = (EditText) findViewById(C0132R.id.etCity);
        this.da = (EditText) findViewById(C0132R.id.etUsernamed);
        this.db = (EditText) findViewById(C0132R.id.etPasswordd);
        this.dc = (EditText) findViewById(C0132R.id.etNamed);
        this.dd = (EditText) findViewById(C0132R.id.etEmaild);
        this.de = (EditText) findViewById(C0132R.id.etCityd);
        this.df = (EditText) findViewById(C0132R.id.etTaxino);
        this.f2c.setOnClickListener(this);
        this.f3d.setOnClickListener(this);
    }

    public void onClick(View v) {
        Service s = new Service();
        if (v.getId() == C0132R.id.bUser) {
            if (!this.ca.getText().toString().equals("") && !this.cb.getText().toString().equals("") && !this.cc.getText().toString().equals("") && !this.cd.getText().toString().equals("") && !this.ce.getText().toString().equals("")) {
                if (s.get("register.php?username=" + this.ca.getText().toString().trim().replace(' ', '_') + "&password=" + this.cb.getText().toString().trim().replace(' ', '_') + "&email=" + this.cd.getText().toString().trim() + "&name=" + this.cc.getText().toString().trim().replace(' ', '_') + "&city=" + this.ce.getText().toString().trim().replace(' ', '_')).contains("success")) {
                    Toast.makeText(getApplicationContext(), "Successfully Registered", 1).show();
                    startActivity(new Intent(this, Login.class));
                    return;
                }
                Toast.makeText(getApplicationContext(), "Sorry, Some Error Occured", 1).show();
            }
        } else if (v.getId() == C0132R.id.bDriver && !this.da.getText().toString().equals("") && !this.db.getText().toString().equals("") && !this.dc.getText().toString().equals("") && !this.dd.getText().toString().equals("") && !this.de.getText().toString().equals("") && !this.df.getText().toString().equals("")) {
            if (s.get("register.php?username=" + this.da.getText().toString().trim() + "&password=" + this.db.getText().toString().trim() + "&email=" + this.dd.getText().toString().trim() + "&name=" + this.dc.getText().toString().trim().replace(' ', '_') + "&city=" + this.de.getText().toString().trim().replace(' ', '_') + "&taxi=" + this.df.getText().toString().trim()).contains("success")) {
                Toast.makeText(getApplicationContext(), "Successfully Registered", 1).show();
                startActivity(new Intent(this, Login.class));
                return;
            }
            Toast.makeText(getApplicationContext(), "Sorry, Some Error Occured", 1).show();
        }
    }
}
