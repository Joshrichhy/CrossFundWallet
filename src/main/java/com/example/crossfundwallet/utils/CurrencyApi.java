package com.example.crossfundwallet.utils;

import com.example.crossfundwallet.Data.Models.CurrencyType;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;




public class CurrencyApi {
//     JSONObject data;

    public CurrencyApi(CurrencyType currencyType){
//        String endpoint = "https://api.exchangerate-api.com/v4/latest/"+currencyType;
//
//        try {
//            URL url = new URL(endpoint);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            conn.connect();
//            Scanner scanner = new Scanner(url.openStream());
//            String response = scanner.useDelimiter("\\Z").next();
//            scanner.close();
//            data = new JSONObject(response);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
}

    public BigDecimal nairaRate(){
        return BigDecimal.ZERO;
       // return data.getJSONObject("rates").getBigDecimal("NGN");
    }

    public BigDecimal dollarRate(){
        return BigDecimal.ZERO;
        //return  data.getJSONObject("rates").getBigDecimal("USD");
    }

    public BigDecimal eurosRate() {
        return BigDecimal.ZERO;
        //return  data.getJSONObject("rates").getBigDecimal("EUR");
    }

    public BigDecimal poundsRate() {
        return BigDecimal.ZERO;
        //return  data.getJSONObject("rates").getBigDecimal("GBP");
    }
    public BigDecimal canadianDollarRate(){
        return BigDecimal.ZERO;
        //return  data.getJSONObject("rates").getBigDecimal("CAD");
    }





}