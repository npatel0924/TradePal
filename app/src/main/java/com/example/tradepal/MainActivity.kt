package com.example.tradepal

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser


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

            val thread = Thread {
                try {
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

                    val data = data(InputText)

                    val DataRS = data.dataRS
                    val DataYF = data.dataYF

                    val jc = jsonClean()

                    Price.text = jc.price(DataRS)
                    PointChange.text = jc.point_change(DataRS)
                    PCTChange.text = jc.pct_change(DataRS)
                    Volume.text = jc.volume(DataRS)

                    Range.text = jc.range(DataYF)
                    PEG.text = jc.PEG(DataYF)
                    MTP.text = jc.MTP(DataYF)
                    Verdict.text = jc.verdict()
                }

                catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            thread.start()
        }
    }
}
