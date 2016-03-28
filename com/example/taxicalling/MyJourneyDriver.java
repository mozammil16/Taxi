package com.example.taxicalling;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

public class MyJourneyDriver extends ActionBarActivity implements OnClickListener {
    int b1;
    Button block;
    Button done;
    int id;
    int id_journey;
    TextView tv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0132R.layout.myjourney);
        this.block = (Button) findViewById(C0132R.id.bBlock);
        this.done = (Button) findViewById(C0132R.id.bJourneyOver);
        this.done.setOnClickListener(this);
        this.block.setOnClickListener(this);
        this.tv = (TextView) findViewById(C0132R.id.tvMyJourney);
        this.id = getSharedPreferences("MyPreferencesFileName", 0).getInt("id", 1);
        String result = new Service().get("myJourneyDriver.php?id=" + this.id);
        if (result.contains("failure")) {
            Toast.makeText(getApplicationContext(), "NO Journey Registered", 1).show();
            startActivity(new Intent(this, AskJourney.class));
        }
        try {
            JSONObject j1 = new JSONObject(result);
            this.id_journey = Integer.parseInt(j1.getString("id"));
            this.tv.append("Source-" + j1.getString("src"));
            this.tv.append("\nDest-" + j1.getString("dest"));
            this.tv.append("\nDate-" + j1.getString("date"));
            this.tv.append("\ntime-" + j1.getString("time"));
            this.b1 = 1;
            JSONObject j2 = new JSONObject(new Service().get("customerInfo.php?id=" + j1.getString("id_customer")));
            this.tv.append("\n\nCustomer phone-" + j2.getString("phone"));
            this.tv.append("\nCustomer name-" + j2.getString("name"));
            if (j1.getString("complete_customer").equals("1")) {
                this.tv.append("\n JOURNEY OVER Okay if yes or Block if no");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onClick(View v) {
        String a;
        if (v.getId() == C0132R.id.bBlock) {
            if (this.b1 != 0) {
                a = new Service().get("driverBlock.php?id=" + this.id);
                if (a.contains("success")) {
                    Toast.makeText(getApplicationContext(), "Successfully Blocked", 1).show();
                    startActivity(new Intent(this, AskJourney.class));
                } else if (a.contains("failure")) {
                    Toast.makeText(getApplicationContext(), "Some Error  Occured", 1).show();
                }
            }
        } else if (v.getId() == C0132R.id.bJourneyOver) {
            a = new Service().get("driverOkay.php?id=" + this.id);
            Toast.makeText(getApplicationContext(), "Successfully Done", 1).show();
            startActivity(new Intent(this, AskJourney.class));
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0132R.menu.driver, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C0132R.id.item1 && item.getItemId() == C0132R.id.item3) {
            startActivity(new Intent(this, Login.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
