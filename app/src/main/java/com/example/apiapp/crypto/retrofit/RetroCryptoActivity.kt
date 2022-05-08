package com.example.apiapp.crypto.retrofit

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apiapp.databinding.ActivityRetroCryptoBinding
import kotlinx.android.synthetic.main.crypto_items.*
import retrofit2.HttpException
import java.io.IOException

const val TAG = "RetroCryptoActivity"

class RetroCryptoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRetroCryptoBinding

    private lateinit var retroCryptoAdapter: RetroCryptoAdapter

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetroCryptoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true
            val response = try {
                RetrofitInstance.api.getCryptos()
            } catch (e: IOException) {
                binding.error.isVisible = true
                Log.e(TAG, "IOException, you might not have internet connection")
                binding.error.text = "ERROR\nIOException, you might not have internet connection"
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null) {
                retroCryptoAdapter.cryptos = response.body()!!
            } else {
                Log.e(TAG, "Response not successful")

            }
            binding.progressBar.isVisible = false
        }


    }

    private fun setupRecyclerView() = binding.recyclerviewCryptos.apply {

        retroCryptoAdapter = RetroCryptoAdapter(baseContext)
        adapter = retroCryptoAdapter
        layoutManager = LinearLayoutManager(this@RetroCryptoActivity)
    }
}