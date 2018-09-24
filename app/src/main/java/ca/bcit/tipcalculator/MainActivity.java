package ca.bcit.tipcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
    }

    protected void onConstraintClick( View v ) {
        Intent i = new Intent( this, ConstraintActivity.class );
        startActivity( i );
    }

    protected void onLinearClick( View v ) {
        Intent i = new Intent( this, LinearActivity.class );
        startActivity( i );
    }

    protected void onRelativeClick( View v ) {
        Intent i = new Intent( this, RelativeActivity.class );
        startActivity( i );
    }

    protected void onGridClick( View v ) {
        Intent i = new Intent( this, GridActivity.class );
        startActivity( i );
    }

    protected void onTableClick( View v ) {
        Intent i = new Intent( this, TableActivity.class );
        startActivity( i );
    }

}
