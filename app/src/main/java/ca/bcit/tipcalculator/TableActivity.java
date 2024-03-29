package ca.bcit.tipcalculator;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class TableActivity extends Activity implements TextView.OnEditorActionListener, View.OnClickListener {

    // define variables for the widgets
    private EditText billAmountEditText;
    private TextView percentTextView;
    private Button percentUpButton;
    private Button percentDownButton;
    private TextView tipTextView;
    private TextView totalTextView;

    // define the SharedPreferences object
    private SharedPreferences savedValues;

    // define instance variables that should be saved
    private String billAmountString = "";
    private float tipPercent = .15f;

    private static final String TAG = "TipCalculatorActivity";

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_table );

        // get references to the widgets
        billAmountEditText = (EditText) findViewById( R.id.billAmountEditText5 );
        percentTextView = (TextView) findViewById( R.id.percentTextView5 );
        percentUpButton = (Button) findViewById( R.id.percentUpButton5 );
        percentDownButton = (Button) findViewById( R.id.percentDownButton5 );
        tipTextView = (TextView) findViewById( R.id.tipTextView5 );
        totalTextView = (TextView) findViewById( R.id.totalTextView5 );

        // set the listeners
        billAmountEditText.setOnEditorActionListener( this );
        percentUpButton.setOnClickListener( this );
        percentDownButton.setOnClickListener( this );

        // get SharedPreferences object
        savedValues = getSharedPreferences( "SavedValues", MODE_PRIVATE );
    }

    @Override
    public void onPause() {
        // save the instance variables
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putString( "billAmountString", billAmountString );
        editor.putFloat( "tipPercent", tipPercent );
        editor.commit();

        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

        // get the instance variables
        billAmountString = savedValues.getString( "billAmountString", "" );
        tipPercent = savedValues.getFloat( "tipPercent", 0.15f );

        // set the bill amount on its widget
        billAmountEditText.setText( billAmountString );

        // calculate and display
        calculateAndDisplay();
    }

    public void calculateAndDisplay() {

        // get the bill amount
        billAmountString = billAmountEditText.getText().toString();
        float billAmount;
        try {
            billAmount = Float.parseFloat( billAmountString );
        } catch ( NumberFormatException e ) {
            billAmount = 0;
        }

        // calculate tip and total
        float tipAmount = billAmount * tipPercent;
        float totalAmount = billAmount + tipAmount;

        // display the other results with formatting
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        tipTextView.setText( currency.format( tipAmount ) );
        totalTextView.setText( currency.format( totalAmount ) );

        NumberFormat percent = NumberFormat.getPercentInstance();
        percentTextView.setText( percent.format( tipPercent ) );
    }

    @Override
    public boolean onEditorAction( TextView v, int actionId, KeyEvent event ) {
        if ( actionId == EditorInfo.IME_ACTION_DONE ||
                actionId == EditorInfo.IME_ACTION_UNSPECIFIED ) {
            calculateAndDisplay();
        }
        return false;
    }

    @Override
    public void onClick( View v ) {
        switch ( v.getId() ) {
            case R.id.percentDownButton:
                tipPercent = tipPercent - .01f;
                calculateAndDisplay();
                break;
            case R.id.percentUpButton:
                tipPercent = tipPercent + .01f;
                calculateAndDisplay();
                break;
        }
    }

}
