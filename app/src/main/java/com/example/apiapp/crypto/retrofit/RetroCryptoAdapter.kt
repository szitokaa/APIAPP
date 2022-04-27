package com.example.apiapp.crypto.retrofit

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import com.example.apiapp.crypto.api.CryptoListItem
import com.example.apiapp.crypto.api.cryptoList
import com.example.apiapp.databinding.CryptoItemsBinding
import kotlin.coroutines.coroutineContext

class RetroCryptoAdapter(val context: Context) : RecyclerView.Adapter<RetroCryptoAdapter.RetroCryptoHolder>(){

inner class  RetroCryptoHolder(val binding: CryptoItemsBinding) : RecyclerView.ViewHolder(binding.root) {


}

    private val diffCallback = object : DiffUtil.ItemCallback<CryptoListItem>() {
        override fun areItemsTheSame(oldItem: CryptoListItem, newItem: CryptoListItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CryptoListItem, newItem: CryptoListItem): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, diffCallback)
    var cryptos: List<CryptoListItem>
        get() = differ.currentList
        set(value) { differ.submitList(value) }



    override fun getItemCount() = cryptos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetroCryptoHolder {
        return RetroCryptoHolder(CryptoItemsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RetroCryptoHolder, position: Int) {
        fun Context.svgLoader(): ImageLoader {
            return ImageLoader.Builder(this).components { add(SvgDecoder.Factory()) }.build()
        }


        holder.binding.apply{
            val crypto = cryptos[position]

            logoUrl.load(crypto.logo_url, context.svgLoader())

                rank.text = "Rank:" + crypto.rank
                name.text = crypto.name
                symbol.text = crypto.symbol




        }
    }


}