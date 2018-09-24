package ca.bcit.tipcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void onConstraintClick(View v) {
        Intent i = new Intent(this, ConstraintActivity.class);
        startActivity(i);
    }
}
