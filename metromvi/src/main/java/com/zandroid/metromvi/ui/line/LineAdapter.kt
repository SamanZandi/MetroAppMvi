package com.zandroid.metromvi.ui.line

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zandroid.metromvi.R
import com.zandroid.metromvi.data.model.Line.ResponseLinesItem
import com.zandroid.metromvi.databinding.ItemLinesBinding
import javax.inject.Inject


class LineAdapter @Inject constructor():RecyclerView.Adapter<LineAdapter.ViewHolder>() {

    private lateinit var binding: ItemLinesBinding
    private var lineList= emptyList<ResponseLinesItem>()
    private lateinit var context:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context=parent.context
        binding=ItemLinesBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(lineList[position])
        holder.setIsRecyclable(false)

    }

    override fun getItemCount(): Int {
       return lineList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder:RecyclerView.ViewHolder(binding.root){
        @SuppressLint("ResourceAsColor")

        fun bind(item: ResponseLinesItem){
            binding.apply {
                txtTitleLine.text=item.title
                txtTitleLineEn.text=item.englishTitle
                lineId.text=item.id

                when(item.id){
                    1.toString() ->cardViewLines.setCardBackgroundColor(ContextCompat.getColor(context, R.color.bg_tajrish))
                    2.toString() ->cardViewLines.setCardBackgroundColor(ContextCompat.getColor(context,R.color.Tehranpars))
                    3.toString() ->cardViewLines.setCardBackgroundColor(ContextCompat.getColor(context,R.color.bg_qaem))
                    4.toString() ->cardViewLines.setCardBackgroundColor(ContextCompat.getColor(context,R.color.bg_eram))
                    5.toString() ->cardViewLines.setCardBackgroundColor(ContextCompat.getColor(context,R.color.bg_sadeghiye))
                    7.toString() ->cardViewLines.setCardBackgroundColor(ContextCompat.getColor(context,R.color.bg_basij))
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

    private var onItemClickListener: ((ResponseLinesItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (ResponseLinesItem) -> Unit) {
        onItemClickListener = listener
    }

    fun setData(data:List<ResponseLinesItem>){
        val lineDiffUtil=LineDiffUtils(data,lineList)
        val diffutils=DiffUtil.calculateDiff(lineDiffUtil)
       lineList=data
        diffutils.dispatchUpdatesTo(this)
    }


    class LineDiffUtils(private val oldItem:List<ResponseLinesItem>,private val newItem:List<ResponseLinesItem>)
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