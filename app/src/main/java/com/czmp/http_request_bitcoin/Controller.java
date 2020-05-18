package com.czmp.http_request_bitcoin;

import org.json.JSONException;
import org.json.JSONObject;


public class Controller {


    public Bitcoin getInfo(String end){
        String json = Connection.getJsonFromApi(end);
        Bitcoin back = parseJson(json);
        return back;
    }

    private Bitcoin parseJson(String json){
        try {
            Bitcoin bits = new Bitcoin();

            JSONObject jsonObj = new JSONObject(json);

            bits.setDisclaimer(jsonObj.getString("disclaimer"));

            //time
            JSONObject time = jsonObj.getJSONObject("time");
            bits.setUpdated(time.getString("updated"));

            //bpi
            JSONObject bpi = jsonObj.getJSONObject("bpi");

            //usd
            JSONObject usd = bpi.getJSONObject("USD");
            bits.setDescriptionusd(usd.getString("description"));
            bits.setCodeusd(usd.getString("code"));
            bits.setSymbolusd(usd.getString("symbol"));
            bits.setRateusd(usd.getString("rate"));

            //gbp
            JSONObject gbp = bpi.getJSONObject("GBP");
            bits.setDescriptiongbp(gbp.getString("description"));
            bits.setCodegbp(gbp.getString("code"));
            bits.setSymbolgbp(gbp.getString("symbol"));
            bits.setRategbp(gbp.getString("rate"));

            //eur
            JSONObject eur = bpi.getJSONObject("EUR");
            bits.setDescriptioneur(eur.getString("description"));
            bits.setCodeeur(eur.getString("code"));
            bits.setSymboleur(eur.getString("symbol"));
            bits.setRateeur(eur.getString("rate"));

            return bits;
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }
}
