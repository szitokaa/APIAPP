package com.example.apiapp
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apiapp.crypto.retrofit.RetroCryptoActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toRetroCryptoButton.setOnClickListener({ startActivity(Intent(this, RetroCryptoActivity::class.java)) })
    }
}