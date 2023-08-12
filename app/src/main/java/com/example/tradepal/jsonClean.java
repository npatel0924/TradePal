package com.example.tradepal;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
public class jsonClean {

    private String price;
    private Double ps;
    private String point_change;
    private String pct_change;
    private String vol;
    private String range;
    private String peg;
    private Double pegs;
    private String MTP;
    private Double MTPS;
    private Double low;
    public jsonClean (){

    }

    public String price(String s){
        JsonObject jsonObject = new Gson().fromJson(s, JsonObject.class);

        price = String.valueOf(jsonObject.get("price").getAsDouble());
        ps = jsonObject.get("price").getAsDouble();
        return price;
    }

    public String point_change (String s){
        JsonObject jsonObject = new Gson().fromJson(s, JsonObject.class);

        point_change = String.valueOf(jsonObject.get("change_point").getAsDouble());
        return point_change;
    }

    public String pct_change (String s){
        JsonObject jsonObject = new Gson().fromJson(s, JsonObject.class);

        pct_change = String.valueOf(jsonObject.get("change_percentage").getAsDouble());
        return pct_change;
    }

    public String volume (String s){
        JsonObject jsonObject = new Gson().fromJson(s, JsonObject.class);

        vol = jsonObject.get("total_vol").getAsString();
        return vol;
    }

    public String range (String s){
        JsonObject jsonObject = new Gson().fromJson(s, JsonObject.class);

        JsonObject dataObject = jsonObject.getAsJsonObject("data");

        range = String.valueOf(dataObject.get("fiftyTwoWeekLow").getAsDouble()) + "-" + dataObject.get("fiftyTwoWeekHigh").getAsDouble();
        low = dataObject.get("fiftyTwoWeekLow").getAsDouble();
        return  range;
    }

    public String PEG (String s){
        JsonObject jsonObject = new Gson().fromJson(s, JsonObject.class);

        JsonObject dataObject = jsonObject.getAsJsonObject("data");

        peg = String.valueOf(dataObject.get("pegRatio").getAsDouble());
        pegs = dataObject.get("pegRatio").getAsDouble();
        return peg;
    }

    public String MTP (String s){
        JsonObject jsonObject = new Gson().fromJson(s, JsonObject.class);

        JsonObject dataObject = jsonObject.getAsJsonObject("data");

        MTP = String.valueOf(dataObject.get("targetMeanPrice").getAsDouble());
        MTPS = dataObject.get("targetMeanPrice").getAsDouble();
        return MTP;
    }

    public String verdict () {

        double pctAboveLow = ((ps - low) / low) * 100;
        double pctBelowTgt = ((MTPS - ps) / MTPS) * 100;

        if ((pctAboveLow >= 33) && pegs <= 1 && (pctBelowTgt <= 33)){
            return "BUY";
        }
        else if (pctAboveLow >= 33 && pegs <= 1){
            return "HOLD";
        }
        else if (pctAboveLow >= 33 && pctBelowTgt <= 33){
            return "HOLD";
        }
        else if (!(pctAboveLow >= 33) && pegs <= 1 && (pctBelowTgt <= 33)){
            return "HOLD";
        }
        else if (!(pctAboveLow >= 33) && pegs <= 1){
            return "WAIT";
        }
        else if (!(pctAboveLow >= 33) && pctBelowTgt <= 33){
            return "WAIT";
        }
        else if (pctAboveLow >= 33){
            return "WAIT";
        }
        else{
            return "SELL";
        }
    }
}
