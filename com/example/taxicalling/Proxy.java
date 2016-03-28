package com.example.taxicalling;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Proxy extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, MyJourneyCustomer.class));
    }
}
