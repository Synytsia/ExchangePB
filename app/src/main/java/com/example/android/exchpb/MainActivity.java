package com.example.android.exchpb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    TextView tvUsdKurs;
    TextView tvEurKurs;
    TextView tvRubKurs;
    TextView tvResult;
    EditText edSumEnter;
    Spinner spinner;
    float editNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvUsdKurs = (TextView) findViewById(R.id.idTvKursUSD);
        tvEurKurs = (TextView) findViewById(R.id.idTvKursEUR);
        tvRubKurs = (TextView) findViewById(R.id.idTvKursRUB);
        tvResult = (TextView) findViewById(R.id.idTvResult);
        edSumEnter = (EditText) findViewById(R.id.idEt);
        spinner = (Spinner) findViewById(R.id.idSpinner);
        ParsingPB parsingPB = new ParsingPB(tvUsdKurs,tvEurKurs,tvRubKurs,tvResult,edSumEnter,spinner);
        parsingPB.execute();
    }

    public void onExchange(View view) {
        if (edSumEnter != null) {
            String tmp = edSumEnter.getText().toString();
            if(!(tmp.trim().length()==0))
                editNumber = Float.parseFloat(edSumEnter.getText().toString());
            else
                Toast.makeText(this, R.string.noEditValue, Toast.LENGTH_SHORT).show();
        }

        String selected = spinner.getSelectedItem().toString();
        switch (selected) {
            case "USD":
                result(editNumber, Float.valueOf(tvUsdKurs.getText().toString()));
                break;
            case "EUR":
                result(editNumber, Float.valueOf(tvEurKurs.getText().toString()));
                break;
            case "RUB":
                result(editNumber, Float.valueOf(tvRubKurs.getText().toString()));
                break;
        }
    }

    public void result(float a, float b) {
        tvResult.setText(String.valueOf(a * b));
    }
}
