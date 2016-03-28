package com.example.taxicalling;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AskJourney extends ActionBarActivity {

    /* renamed from: com.example.taxicalling.AskJourney.1 */
    class C01311 implements OnItemClickListener {
        C01311() {
        }

        public void onItemClick(AdapterView<?> adapterView, View container, int position, long id) {
            TextView tvCountry = (TextView) ((RelativeLayout) container).getChildAt(0);
            Editor p = AskJourney.this.getSharedPreferences("MyPreferencesFileName", 0).edit();
            p.putInt("id_journey", Integer.parseInt(tvCountry.getText().toString()));
            p.commit();
            AskJourney.this.startActivity(new Intent("com.example.taxicalling.JOURNEYDETAILS"));
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0132R.layout.listview);
        Toast.makeText(getApplicationContext(), "AskJourney", 1);
        abc();
    }

    public void abc() {
        List<HashMap<String, String>> aList = new ArrayList();
        SharedPreferences settings = getSharedPreferences("MyPreferencesFileName", 0);
        int id = settings.getInt("id", 1);
        String result = new Service().get("AskJourney.php?city=" + settings.getString("city", "bilaspur"));
        if (result.contains("failure")) {
            Toast.makeText(getBaseContext(), "No Request Found", 1).show();
            return;
        }
        try {
            JSONArray j = new JSONArray(result);
            for (int i = 0; i < j.length(); i++) {
                HashMap<String, String> hm = new HashMap();
                JSONObject j1 = j.getJSONObject(i);
                hm.put("src", j1.getString("src"));
                hm.put("dest", j1.getString("dest"));
                hm.put("time", j1.getString("time"));
                hm.put("date", j1.getString("date"));
                hm.put("id", j1.getString("id"));
                aList.add(hm);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListView listView = (ListView) findViewById(C0132R.id.listview);
        listView.setAdapter(new SimpleAdapter(getBaseContext(), aList, C0132R.layout.listformat, new String[]{"src", "dest", "time", "date", "id"}, new int[]{C0132R.id.textView2, C0132R.id.textView3, C0132R.id.textView4, C0132R.id.textView5, C0132R.id.textView1}));
        listView.setOnItemClickListener(new C01311());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0132R.menu.drivermenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == C0132R.id.item1) {
            abc();
        } else if (item.getItemId() == C0132R.id.item2) {
            startActivity(new Intent(this, Login.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
