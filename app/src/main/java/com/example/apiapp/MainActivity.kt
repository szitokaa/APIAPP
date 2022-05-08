package com.example.apiapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.apiapp.crypto.retrofit.RetroCryptoActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        udvozlo.text = "Az alkalmazás célja az API használatának bemutatása."
        udvozlo2.text = "Kattintson a BITCOIN Logójára, ha szeretné látni a kriptovaluták aktuális árfolyamát!"

        toRetroCryptoButton.setOnClickListener({
            startActivity(
                Intent(
                    this,
                    RetroCryptoActivity::class.java
                )
            )
        })
    }
}