package com.example.taxicalling;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertJourney extends ActionBarActivity implements OnClickListener {
    EditText city;
    EditText date;
    EditText dest;
    EditText source;
    Button submit;
    EditText time;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0132R.layout.insertjourney);
        initialize();
    }

    void initialize() {
        this.source = (EditText) findViewById(C0132R.id.etSource);
        this.dest = (EditText) findViewById(C0132R.id.etDest);
        this.city = (EditText) findViewById(C0132R.id.etCity);
        this.date = (EditText) findViewById(C0132R.id.etDate);
        this.time = (EditText) findViewById(C0132R.id.time);
        this.submit = (Button) findViewById(C0132R.id.bAskTaxi);
        this.submit.setOnClickListener(this);
    }

    public void onClick(View v) {
        int id = getSharedPreferences("MyPreferencesFileName", 0).getInt("id", 1);
        if (!this.source.getText().toString().equals("") && !this.dest.getText().toString().equals("") && !this.time.getText().toString().equals("") && !this.date.getText().toString().equals("") && !this.city.getText().toString().equals("")) {
            if (new Service().get("askTaxi.php?source=" + this.source.getText().toString().replace(' ', '_') + "&dest=" + this.dest.getText().toString().replace(' ', '_') + "&city=" + this.city.getText().toString().replace(' ', '_') + "&time=" + this.time.getText().toString().replace(' ', '_') + "&date=" + this.date.getText().toString().replace(' ', '_') + "&id=" + id).contains("success")) {
                Toast.makeText(getApplicationContext(), "Successfully Booked", 1).show();
                startActivity(new Intent(this, MyJourneyCustomer.class));
                return;
            }
            Toast.makeText(getApplicationContext(), "Some Error Occured", 1).show();
        }
    }
}
