package com.example.moneyrate.adapters


import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moneyrate.Model.MoneyRate
import com.example.moneyrate.databinding.RecRowBinding

class RecAdapter(private val MoneyList : ArrayList<MoneyRate>, private val listener : Listener) : RecyclerView.Adapter<RecAdapter.RowHolder>() {

    interface Listener {
        fun onItemClick(MoneyRate: MoneyRate)
    }

    private val colors: Array<String> = arrayOf("#13bd27","#29c1e1","#b129e1","#d3df13","#f6bd0c","#a1fb93","#0d9de3","#ffe48f")

    class RowHolder(val binding: RecRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val binding = RecRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RowHolder(binding)
    }

    override fun getItemCount(): Int {
        return MoneyList.count()
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.itemView.setOnClickListener {
            listener.onItemClick(MoneyList.get(position))
        }
        holder.itemView.setBackgroundColor(Color.parseColor(colors[position % 8]))

        holder.binding.textName.text = MoneyList.get(position).currency
        holder.binding.textPrice.text = MoneyList.get(position).price

    }


}