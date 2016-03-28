package com.example.taxicalling;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

public class JourneyDetails extends ActionBarActivity implements OnClickListener {
    Button f8b;
    int id;
    int id_journey;
    TextView tv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0132R.layout.journeydetails);
        this.tv = (TextView) findViewById(C0132R.id.tv1);
        this.f8b = (Button) findViewById(C0132R.id.b1);
        this.f8b.setOnClickListener(this);
        abc();
    }

    public void abc() {
        SharedPreferences settings = getSharedPreferences("MyPreferencesFileName", 0);
        this.id_journey = settings.getInt("id_journey", 7);
        this.id = settings.getInt("id", 1);
        try {
            JSONObject j = new JSONObject(new Service().get("journeyOnId.php?id=" + this.id_journey));
            this.tv.append("Source-" + j.getString("src"));
            this.tv.append("\nDest-" + j.getString("dest"));
            this.tv.append("\nDate-" + j.getString("date"));
            this.tv.append("\ntime-" + j.getString("time"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onClick(View v) {
        if (new Service().get("bookJourney.php?id_journey=" + this.id_journey + "&id=" + this.id).contains("success")) {
            Toast.makeText(getBaseContext(), "Booked", 1);
            startActivity(new Intent(this, MyJourneyDriver.class));
            return;
        }
        Toast.makeText(getBaseContext(), "Some Error Occured", 1);
    }
}
