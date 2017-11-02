package com.example.android.exchpb;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Sinitsa on 19.02.2017.
 */

class ParsingPB extends AsyncTask<Void, Void, Void> {

    private TextView tvUsdKurs;
    private TextView tvEurKurs;
    private TextView tvRubKurs;
    private String tempUsd;
    private String tempEur;
    private String tempRub;

    final static String TAG = "Проверка";

    ParsingPB(TextView tvUsdKurs, TextView tvEurKurs, TextView tvRubKurs, TextView tvResult, EditText edSumEnter, Spinner spinner) {
        this.tvUsdKurs = tvUsdKurs;
        this.tvEurKurs = tvEurKurs;
        this.tvRubKurs = tvRubKurs;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            URL url = new URL ("https://api.privatbank.ua/p24api/pubinfo?exchange&coursid=5");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection ( );
            connection.setRequestMethod ("GET");
            connection.setConnectTimeout (10000);
            connection.connect();

            BufferedReader reader = new BufferedReader (new InputStreamReader (connection.getInputStream ( )));
            StringBuilder buffer = new StringBuilder ( );
            String str;
            while ((str = reader.readLine ( )) != null) {
                buffer.append (str);
            }
            Log.e (TAG, buffer.toString ( ));
            String myStr = buffer.toString ( );
            String myUsd = myStr.substring (myStr.indexOf ("ccy=\"USD\""), myStr.indexOf ("ccy=\"USD\"") + 55);
            String myEur = myStr.substring (myStr.indexOf ("ccy=\"EUR\""), myStr.indexOf ("ccy=\"EUR\"") + 55);
            String myRub = myStr.substring (myStr.indexOf ("ccy=\"RUR\""), myStr.indexOf ("ccy=\"RUR\"") + 53);

            tempUsd = myUsd.substring (30, 35);
            tempEur = myEur.substring (30, 35);
            tempRub = myRub.substring (30, 34);

            publishProgress ( );
        }
        catch (MalformedURLException e) {
        }
        catch (ProtocolException e) {
            e.printStackTrace ( );
        }
        catch (IOException e) {
            e.printStackTrace ( );
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate (values);
        tvUsdKurs.setText (tempUsd);
        tvEurKurs.setText (tempEur);
        tvRubKurs.setText (tempRub);
    }
}
