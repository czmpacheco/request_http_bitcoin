package com.czmp.http_request_bitcoin;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView descriptionusd;
    private TextView codeusd;
    private TextView symbolusd;
    private TextView rateusd;
    private TextView descriptiongbp;
    private TextView codegbp;
    private TextView symbolgbp;
    private TextView rategbp;
    private TextView descriptioneur;
    private TextView codeeur;
    private TextView symboleur;
    private TextView rateeur;
    private TextView updated;
    private TextView disclaimer;
    private ProgressDialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadBits download = new DownloadBits();

        descriptionusd = (TextView)findViewById(R.id.usdDesc);
        codeusd = (TextView)findViewById(R.id.usdCode);
        symbolusd = (TextView)findViewById(R.id.usdSymbol);
        rateusd = (TextView)findViewById(R.id.usdRate);
        descriptiongbp = (TextView)findViewById(R.id.gbpDesc);
        codegbp = (TextView)findViewById(R.id.gbpCode);
        symbolgbp = (TextView)findViewById(R.id.gbpSymbol);
        rategbp = (TextView)findViewById(R.id.gbpRate);
        descriptioneur = (TextView)findViewById(R.id.eurDesc);
        codeeur = (TextView)findViewById(R.id.eurCode);
        symboleur = (TextView)findViewById(R.id.eurSymbol);
        rateeur = (TextView)findViewById(R.id.eurRate);
        updated = (TextView)findViewById(R.id.updated);
        disclaimer = (TextView)findViewById(R.id.disclaimer);

        download.execute();
    }

    private class DownloadBits extends AsyncTask<Void, Void, Bitcoin> {

        @Override
        protected void onPreExecute(){
            load = ProgressDialog.show(MainActivity.this,
                    "Loading ...", "Gathering information...");
        }

        @Override
        protected Bitcoin doInBackground(Void... params) {
            Controller util = new Controller();
            return util.getInfo("https://api.coindesk.com/v1/bpi/currentprice.json");
        }

        @Override
        protected void onPostExecute(Bitcoin bits){
            descriptionusd.setText(bits.getDescriptionusd());
            codeusd.setText(bits.getCodeusd());
            symbolusd.setText(Html.fromHtml(bits.getSymbolusd()));
            rateusd.setText(bits.getRateusd());
            descriptiongbp.setText(bits.getDescriptiongbp());
            codegbp.setText(bits.getCodegbp());
            symbolgbp.setText(Html.fromHtml(bits.getSymbolgbp())); ;
            rategbp.setText(bits.getRategbp()); ;
            descriptioneur.setText(bits.getDescriptioneur()); ;
            codeeur.setText(bits.getCodeeur()); ;
            symboleur.setText(Html.fromHtml(bits.getSymboleur())); ;
            rateeur.setText(bits.getRateeur()); ;
            updated.setText(bits.getUpdated()); ;
            disclaimer.setText(bits.getDisclaimer());
            load.dismiss();
        }
    }
}
