package com.example.tradepal

import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.google.gson.JsonParser
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody


class MainActivity : ComponentActivity() {

    private lateinit var TikcerInput: EditText
    private lateinit var EnterButton: Button
    private lateinit var Price: TextView
    private lateinit var PointChange: TextView
    private lateinit var PCTChange: TextView
    private lateinit var Volume: TextView
    private lateinit var Range: TextView
    private lateinit var PEG: TextView
    private lateinit var MTP: TextView
    private lateinit var Verdict: TextView

    override fun onCreate(savedInstanceState: Bundle?){

        super.onCreate(savedInstanceState)
        setContentView (R.layout.activity_main)

        EnterButton = findViewById(R.id.EnterButton)

        EnterButton.setOnClickListener {

            TikcerInput = findViewById(R.id.TickerInput)
            Price = findViewById(R.id.Price)
            PointChange = findViewById(R.id.PointChange)
            PCTChange = findViewById(R.id.PCTChange)
            Volume = findViewById(R.id.Volume)
            Range = findViewById(R.id.Range)
            PEG = findViewById(R.id.PEG)
            MTP = findViewById(R.id.MTP)
            Verdict = findViewById(R.id.Verdict)

            val InputText = TikcerInput.text.toString()

            val client = OkHttpClient()

            val request = Request.Builder()
                .url("https://realstonks.p.rapidapi.com/" + InputText)
                .get()
                .addHeader("X-RapidAPI-Key", "d001be903bmsh72c76a45143a83fp1d2e0djsn66706bb1fe21")
                .addHeader("X-RapidAPI-Host", "realstonks.p.rapidapi.com")
                .build()

            val responseRS = client.newCall(request).execute().toString()

            val client2 = OkHttpClient()

            val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
            val body = RequestBody.create(mediaType, "symbol=" + InputText)
            val request2 = Request.Builder()
                .url("https://yfinance-stock-market-data.p.rapidapi.com/stock-info")
                .post(body)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .addHeader("X-RapidAPI-Key", "d001be903bmsh72c76a45143a83fp1d2e0djsn66706bb1fe21")
                .addHeader("X-RapidAPI-Host", "yfinance-stock-market-data.p.rapidapi.com")
                .build()

            val responseYF = client2.newCall(request2).execute().toString()

            val parser = JsonParser()
            val jsonElement = parser.parse(responseYF)

            val fiftyTwoWeekHigh = jsonElement.asJsonObject.get("fiftyTwoWeekHigh").asDouble
            val fiftyTwoWeekLow = jsonElement.asJsonObject.get("fiftyTwoWeekLow").asDouble
            val pegRatio = jsonElement.asJsonObject.get("pegRatio").asDouble
            val targetMeanPrice = jsonElement.asJsonObject.get("targetMeanPrice").asDouble

            val weekRange = fiftyTwoWeekLow.toString() + "-" + fiftyTwoWeekHigh.toString()

            val jsonElement2 = parser.parse(responseRS)

            val p = jsonElement2.asJsonObject.get("price").asDouble
            val pct = jsonElement2.asJsonObject.get("change_percentage").asDouble
            val pint = jsonElement2.asJsonObject.get("change_point").asDouble
            val vol = jsonElement2.asJsonObject.get("total_vol").asString

            Price.text = p.toString()
            PointChange.text = pint.toString()
            PCTChange.text = pct.toString()
            Volume.text = vol.toString()
            Range.text = weekRange
            MTP.text = targetMeanPrice.toString()
            PEG.text = pegRatio.toString()

            val pctAboveLow = ((p - fiftyTwoWeekLow) / fiftyTwoWeekLow) * 100
            val pctBelowTgt = ((targetMeanPrice - p) / targetMeanPrice) * 100

            when {
                pctAboveLow >= 33 && pegRatio <= 1 && pctBelowTgt <= 33 -> {
                    Verdict.text = "BUY"
                }
                pctAboveLow >= 33 && pegRatio <= 1 -> {
                    Verdict.text = "HOLD"
                }
                pctAboveLow >= 33 && pctBelowTgt <= 33 -> {
                    Verdict.text = "HOLD"
                }
                !(pctAboveLow >= 33) && pegRatio <= 1 && pctBelowTgt <= 33 -> {
                    Verdict.text = "HOLD"
                }
                !(pctAboveLow >= 33) && pegRatio <= 1 -> {
                    Verdict.text = "WAIT"
                }
                !(pctAboveLow >= 33) && pctBelowTgt <= 33 -> {
                    Verdict.text = "WAIT"
                }
                pctAboveLow >= 33 -> {
                    Verdict.text = "WAIT"
                }
                else -> {
                    Verdict.text = "SHORT"
                }
            }
        }
    }
}
