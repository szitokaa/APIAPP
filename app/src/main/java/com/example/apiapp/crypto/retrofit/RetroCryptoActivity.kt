package com.example.apiapp.crypto.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.apiapp.databinding.ActivityRetroCryptoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
const val TAG = "RetroCryptoActivity"

class RetroCryptoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRetroCryptoBinding

    private lateinit var retroCryptoAdapter: RetroCryptoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetroCryptoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true
            val response = try {
                RetrofitInstance.api.getCryptos()
            } catch(e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body() != null) {
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