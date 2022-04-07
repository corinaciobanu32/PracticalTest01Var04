package ro.pub.cs.systems.eim.practicaltest01var04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var04MainActivity extends AppCompatActivity {

    CheckBox check1;
    CheckBox check2;
    Button navigate;
    Button display;

    EditText text1;
    EditText text2;

    TextView completeText;

    String mergedText;

    Boolean serviceActive =false;

    private PracticalTest01Var04ServiceReceiver practicalTest01ServiceReceiver;
    private IntentFilter startedServiceIntentFilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var04_main);


        practicalTest01ServiceReceiver = new PracticalTest01Var04ServiceReceiver();
        startedServiceIntentFilter = new IntentFilter();
        startedServiceIntentFilter.addAction(Constants.NUME_ACTION);
        startedServiceIntentFilter.addAction(Constants.GRUPA_ACTION);

        navigate = (Button) findViewById(R.id.navigate_button);
        navigate.setOnClickListener(navigateClickListener);

        display = (Button) findViewById(R.id.display_button);
        display.setOnClickListener(displayClickListener);

        text1 = (EditText) findViewById(R.id.editText1);
        text2 = (EditText) findViewById(R.id.editText2);

        check1 = (CheckBox) findViewById(R.id.checkBox1);
        check2 = (CheckBox) findViewById(R.id.checkBox2);

        completeText = (TextView) findViewById(R.id.text_simple);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("text1")) {
                text1.setText(savedInstanceState.getString("text1"));
            }
            if (savedInstanceState.containsKey("text2")) {
                text2.setText(savedInstanceState.getString("text2"));
            }
            if (savedInstanceState.containsKey("completeText")) {
                completeText.setText(savedInstanceState.getString("completeText"));
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(practicalTest01ServiceReceiver, startedServiceIntentFilter);

    }

    @Override
    protected void onPause() {
        unregisterReceiver(practicalTest01ServiceReceiver);

        super.onPause();
    }

    private NavigateClickListener navigateClickListener = new NavigateClickListener();
    private class NavigateClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), PracticalTest01Var04SecondaryActivity.class);
            intent.putExtra("Nume", text1.getText().toString());
            intent.putExtra("Grupa", text2.getText().toString());
            startActivityForResult(intent, 2017);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 2017) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }


    private DisplayClickListener displayClickListener = new DisplayClickListener();
    private class DisplayClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            mergedText = "";
            if (check1.isChecked()) {
                if (!text1.getText().toString().equals("")){
                    mergedText = text1.getText().toString();
                }
                else{
                    Toast.makeText(getApplication(), getResources().getString(R.string.field_incomplete), Toast.LENGTH_LONG).show();
                }
            }
            if (check2.isChecked()) {
                if (!text2.getText().toString().equals("")){
                    mergedText += text2.getText().toString();
                }
                else{
                    Toast.makeText(getApplication(), getResources().getString(R.string.field_incomplete), Toast.LENGTH_LONG).show();
                }
            }
            if (!text1.getText().toString().equals("") && !text2.getText().toString().equals("")){
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var04SecondaryActivity.class);
                intent.putExtra("Nume", text1.getText().toString());
                intent.putExtra("Grupa", text2.getText().toString());
                startService(intent);
                serviceActive = true;
            }

            completeText.setText(mergedText);
            completeText.setVisibility(View.VISIBLE);
        }
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        text1 = (EditText) findViewById(R.id.editText1);
        text2 = (EditText) findViewById(R.id.editText2);
        completeText = (TextView) findViewById(R.id.text_simple);

        if (savedInstanceState.containsKey("text1")) {
            text1.setText(savedInstanceState.getString("text1"));
        }
        if (savedInstanceState.containsKey("text2")) {
            text2.setText(savedInstanceState.getString("text2"));
        }
        if (savedInstanceState.containsKey("completeText")) {
            completeText.setText(savedInstanceState.getString("completeText"));
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        String s1, s2, s3;
        s1 = text1.getText().toString();
        s2 = text2.getText().toString();
        s3 = completeText.getText().toString();


        savedInstanceState.putString("text1", String.valueOf(s1));
        savedInstanceState.putString("text2", String.valueOf(s2));
        savedInstanceState.putString("completeText", String.valueOf(s3));
    }
}