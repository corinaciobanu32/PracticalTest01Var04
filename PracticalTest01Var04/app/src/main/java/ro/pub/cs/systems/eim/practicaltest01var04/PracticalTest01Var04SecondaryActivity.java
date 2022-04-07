package ro.pub.cs.systems.eim.practicaltest01var04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PracticalTest01Var04SecondaryActivity  extends AppCompatActivity {

    Button ok_button;
    Button cancel_button;

    TextView text1;
    TextView text2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var04_secondary);

        ok_button = (Button) findViewById(R.id.ok_button);
        ok_button.setOnClickListener(okButtonClickListener);

        cancel_button = (Button) findViewById(R.id.cancel_button);
        cancel_button.setOnClickListener(cancelButtonClickListener);

        text1 = (TextView) findViewById(R.id.name_text);
        text2 = (TextView) findViewById(R.id.grupa_text);

        Intent intent = getIntent();
        if (intent!= null){
            if (intent.getExtras().containsKey("Nume")) {
                String s1 = intent.getStringExtra("Nume");
                text1.setText(s1);
            }
            if (intent.getExtras().containsKey("Grupa")) {
                String s2 = intent.getStringExtra("Grupa");
                text2.setText(s2);
            }
        }

    }

    private PracticalTest01Var04SecondaryActivity.OkButtonClickListener okButtonClickListener = new PracticalTest01Var04SecondaryActivity.OkButtonClickListener();
    private class OkButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            setResult(RESULT_OK, null);
            finish();
        }
    }

    private PracticalTest01Var04SecondaryActivity.CancelButtonClickListener cancelButtonClickListener = new PracticalTest01Var04SecondaryActivity.CancelButtonClickListener();
    private class CancelButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            setResult(RESULT_CANCELED, null);
            finish();
        }
    }
}
