package com.zandroid.metromvi.ui.station

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zandroid.metromvi.R
import com.zandroid.metromvi.data.model.ResponseStation.ResponseStationItem
import com.zandroid.metromvi.databinding.StationRowBinding


import javax.inject.Inject


class StationAdapter @Inject constructor():RecyclerView.Adapter<StationAdapter.ViewHolder>() {

    private lateinit var binding: StationRowBinding
    private var stationList= emptyList<ResponseStationItem>()
    private lateinit var context:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context=parent.context
        binding=StationRowBinding.inflate(LayoutInflater.from(context),parent,false)

        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(stationList[position])
        holder.setIsRecyclable(false)

    }

    override fun getItemCount(): Int {
       return stationList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder:RecyclerView.ViewHolder(binding.root){
        @SuppressLint("ResourceAsColor")

        fun bind(item: ResponseStationItem){
            binding.apply {
                txtTitleStation.text=item.title
                txtTitleStationEn.text=item.titleEnglish

                when(item.lineId){
                    1.toString() ->cardViewStation.setCardBackgroundColor(ContextCompat.getColor(context,
                        R.color.bg_tajrish))
                    2.toString() ->cardViewStation.setCardBackgroundColor(ContextCompat.getColor(context,R.color.Tehranpars))
                    3.toString() ->cardViewStation.setCardBackgroundColor(ContextCompat.getColor(context,R.color.bg_qaem))
                    4.toString() ->cardViewStation.setCardBackgroundColor(ContextCompat.getColor(context,R.color.bg_eram))
                    5.toString() ->cardViewStation.setCardBackgroundColor(ContextCompat.getColor(context,R.color.bg_sadeghiye))
                    7.toString() ->cardViewStation.setCardBackgroundColor(ContextCompat.getColor(context,R.color.bg_basij))
                }

                //onCLick
                root.setOnClickListener {
                     onItemClickListener?.let {
                         it(item)
                     }
                }
            }
        }
    }

    private var onItemClickListener: ((ResponseStationItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (ResponseStationItem) -> Unit) {
        onItemClickListener = listener
    }

    fun setData(data:List<ResponseStationItem>){
        val lineDiffUtil=LineDiffUtils(data,stationList)
        val diffutils=DiffUtil.calculateDiff(lineDiffUtil)
        stationList=data
        diffutils.dispatchUpdatesTo(this)
    }


    class LineDiffUtils(private val oldItem:List<ResponseStationItem>,private val newItem:List<ResponseStationItem>)
        :DiffUtil.Callback(){
        override fun getOldListSize(): Int {
            return oldItem.size
        }

        override fun getNewListSize(): Int {
            return newItem.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition]===newItem[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition]===newItem[newItemPosition]
        }

    }


}