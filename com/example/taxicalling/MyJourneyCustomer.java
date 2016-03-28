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

public class MyJourneyCustomer extends ActionBarActivity implements OnClickListener {
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
        String result = new Service().get("myJourneyCustomer.php?id=" + this.id);
        if (result.contains("failure")) {
            Toast.makeText(getApplicationContext(), "NO Journey Registered", 1).show();
            startActivity(new Intent(this, InsertJourney.class));
        }
        try {
            JSONObject j1 = new JSONObject(result);
            this.id_journey = Integer.parseInt(j1.getString("id"));
            this.tv.append("Source-" + j1.getString("src"));
            this.tv.append("\nDest-" + j1.getString("dest"));
            this.tv.append("\nDate-" + j1.getString("date"));
            this.tv.append("\ntime-" + j1.getString("time"));
            if (j1.getString("id_driver").contains("null")) {
                this.tv.append("\n NO DRIVER ALLOTED");
                this.b1 = 0;
                return;
            }
            this.b1 = 1;
            JSONObject j2 = new JSONObject(new Service().get("driverInfo.php?id=" + j1.getString("id_driver")));
            this.tv.append("\n\nDriver phone-" + j2.getString("phone"));
            this.tv.append("\nDriver name-" + j2.getString("name"));
            this.tv.append("\nDriver taxi no-" + j2.getString("taxino"));
            if (j1.getString("complete_driver").equals("1")) {
                this.tv.append("\n Driver Says Journey is over Block/Okay");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onClick(View v) {
        String a;
        if (v.getId() == C0132R.id.bBlock) {
            if (this.b1 != 0) {
                a = new Service().get("customerBlock.php?id=" + this.id);
                if (a.contains("success")) {
                    Toast.makeText(getApplicationContext(), "Successfully Blocked", 1).show();
                    Intent intent = new Intent("com.example.taxi1.PROXY1");
                } else if (a.contains("failure")) {
                    Toast.makeText(getApplicationContext(), "Some Error  Occured", 1).show();
                }
            }
        } else if (v.getId() == C0132R.id.bJourneyOver) {
            a = new Service().get("customerOkay.php?id=" + this.id);
            Toast.makeText(getApplicationContext(), "Successfully Done", 1).show();
            startActivity(new Intent(this, InsertJourney.class));
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0132R.menu.customer, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == C0132R.id.item1) {
            startActivity(new Intent(this, EditJourney.class));
        } else if (item.getItemId() == C0132R.id.item3) {
            startActivity(new Intent(this, Login.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
