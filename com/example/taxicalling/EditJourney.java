package com.example.taxicalling;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONObject;

public class EditJourney extends ActionBarActivity implements OnClickListener {
    EditText city;
    EditText date;
    EditText dest;
    int id;
    int id_journey;
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
        this.id = getSharedPreferences("MyPreferencesFileName", 0).getInt("id", 1);
        String result = new Service().get("myJourneyCustomer.php?id=" + this.id);
        if (result.contains("failure")) {
            Toast.makeText(getApplicationContext(), "NO Journey Registered", 1).show();
            startActivity(new Intent(this, InsertJourney.class));
        }
        try {
            JSONObject j1 = new JSONObject(result);
            this.id_journey = Integer.parseInt(j1.getString("id"));
            this.source.setHint(j1.getString("src"));
            this.dest.setHint(j1.getString("dest"));
            this.date.setHint(j1.getString("date"));
            this.time.setHint(j1.getString("time"));
            this.city.setHint(j1.getString("city"));
        } catch (Exception e) {
        }
    }

    public void onClick(View v) {
        if (!this.source.getText().toString().equals("") && !this.dest.getText().toString().equals("") && !this.time.getText().toString().equals("") && !this.date.getText().toString().equals("") && !this.city.getText().toString().equals("")) {
            if (new Service().get("update.php?id=" + this.id_journey + "source=" + this.source.getText().toString().replace(' ', '_') + "&dest=" + this.dest.getText().toString().replace(' ', '_') + "&city=" + this.city.getText().toString().replace(' ', '_') + "&time=" + this.time.getText().toString().replace(' ', '_') + "&date=" + this.date.getText().toString().replace(' ', '_')).contains("success")) {
                Toast.makeText(getApplicationContext(), "Successfully Booked", 1).show();
                startActivity(new Intent(this, MyJourneyCustomer.class));
                return;
            }
            Toast.makeText(getApplicationContext(), "Some Error Occured", 1).show();
        }
    }
}
