package com.example.apiapp.crypto.retrofit

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import com.example.apiapp.crypto.api.CryptoListItem
import com.example.apiapp.databinding.CryptoItemsBinding
import java.math.RoundingMode

class RetroCryptoAdapter(val context: Context) :
    RecyclerView.Adapter<RetroCryptoAdapter.RetroCryptoHolder>() {

    inner class RetroCryptoHolder(val binding: CryptoItemsBinding) :
        RecyclerView.ViewHolder(binding.root)


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
        set(value) {
            differ.submitList(value)
        }


    override fun getItemCount() = cryptos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetroCryptoHolder {
        return RetroCryptoHolder(
            CryptoItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RetroCryptoHolder, position: Int) {
        fun Context.svgLoader(): ImageLoader {
            return ImageLoader.Builder(this).components { add(SvgDecoder.Factory()) }.build()
        }


        holder.binding.apply {
            val crypto = cryptos[position]

            logoUrl.load(crypto.logo_url, context.svgLoader())

            rank.text = "Rank:" + crypto.rank
            name.text = crypto.name
            symbol.text = crypto.symbol
            price.text = crypto.price.toBigDecimal().setScale(4, RoundingMode.UP).toString()


            var dayChange = crypto.day.price_change_pct.toDouble()
            dayChange *= 100
            day.text = dayChange.toBigDecimal().setScale(2, RoundingMode.UP).toString() +"%"
            if (dayChange < 0) day.setTextColor(Color.parseColor("red"))
            else if (dayChange > 0) {
                day.setTextColor(Color.parseColor("green"))
                day.text = "+" + dayChange.toBigDecimal().setScale(2, RoundingMode.UP).toString() +"%"
            }

            var weekChange = crypto.week.price_change_pct.toDouble()
            weekChange *= 100
            week.text = weekChange.toBigDecimal().setScale(2, RoundingMode.UP).toString() +"%"
            if (weekChange < 0) week.setTextColor(Color.parseColor("red"))
            else if (weekChange > 0) {
                week.setTextColor(Color.parseColor("green"))
                week.text = "+" + weekChange.toBigDecimal().setScale(2, RoundingMode.UP).toString() +"%"
            }

            var monthChange = crypto.month.price_change_pct.toDouble()
            monthChange *= 100
            month.text = monthChange.toBigDecimal().setScale(2, RoundingMode.UP).toString()+"%"
            if (monthChange < 0) month.setTextColor(Color.parseColor("red"))
            else if (monthChange > 0) {
                month.setTextColor(Color.parseColor("green"))
                month.text = "+" + monthChange.toBigDecimal().setScale(2, RoundingMode.UP).toString() +"%"
            }
        }


        }
    }


