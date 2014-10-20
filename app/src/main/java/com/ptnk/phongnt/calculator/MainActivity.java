package com.ptnk.phongnt.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ptnk.phongnt.calculator.R;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;


public class MainActivity extends Activity {
    String ans;
    String current;
    Evaluator evaluator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        evaluator = new Evaluator();
        ans = "0";
        setContentView(R.layout.activity_main);
        clearDisplay();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initialize(){
        float ans = 0;
        float current = 0;
    }

    private void setDisplayText(String text){
        TextView display = (TextView) findViewById(R.id.display_input);
        display.setText(text);
    }

    private String getDisplayText(){
        TextView display = (TextView) findViewById(R.id.display_input);
        return display.getText().toString();
    }

    private void clearDisplay(){
        setDisplayText("0");
    }

    private float getNumeric(String string){
        return Float.parseFloat(string);
    }

    public void onSymbolClicked(View view){
        String currentText = getDisplayText();
        Button b = (Button)view;
        String buttonText = b.getText().toString();
        if(! currentText.equals("0"))
            setDisplayText(currentText+buttonText);
        else
            setDisplayText(buttonText);
    }

    public void onOperatorClicked(View view){
        String currentText = getDisplayText();
        Button b = (Button)view;
        String buttonText = b.getText().toString();
        if(! currentText.equals("0"))
            setDisplayText(currentText+buttonText);
        else
            setDisplayText("ans"+buttonText);
    }

    public void onEvaluated(View view){
        String expression = getDisplayText().replace("ans",ans);
        try{
            ans = evaluator.evaluate(expression);
            showResult(ans);
            clearDisplay();
        }
        catch (EvaluationException e){
            showResult("Error");
        }

    }

    public void showResult(String result){
        TextView display = (TextView) findViewById(R.id.display_result);
        display.setText(result);
    }

    public void onCleared(View view){
        clearDisplay();
    }
}
