package io.softeer.luckycardgame.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.softeer.luckycardgame.card.Card
import io.softeer.luckycardgame.databinding.HolderCardBinding

class CardAdapter(
    private val cardList: MutableList<Card>,
    private val showFront : Boolean,
    private val onCardClick : ()->Unit
) : RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    private var ableMinIndex = 0
    private var ableMaxIndex = itemCount-1
    private val selectedPosition = mutableListOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = HolderCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cardList[position])
    }

    inner class ViewHolder(private val bind : HolderCardBinding) : RecyclerView.ViewHolder(bind.root) {
        fun bind(card : Card) {
            card.getCardNumber().toString().let {
                bind.cardNumberTop.text = it
                bind.cardNumberBottom.text = it
            }
            bind.cardType.text = card.getCardType().typeUnicode
            bind.groupBack.visibility = if(showFront) View.GONE else View.VISIBLE
            bind.root.setOnClickListener {
                updateAbleIndex(adapterPosition) {
                    flipCard(bind)
                }
            }
        }
    }

    private fun updateAbleIndex(position : Int, selectItem : ()->Unit) {
        if (selectedPosition.contains(ableMinIndex)) ableMinIndex++
        if (selectedPosition.contains(ableMaxIndex)) ableMaxIndex--
        if (position == ableMinIndex || position == ableMaxIndex) {
            selectedPosition.add(position)
            selectItem()
        }
    }

    /**
     * 카드 뒤집기
     */
    private fun flipCard(bind : HolderCardBinding) {
        bind.groupFront.visibility = View.VISIBLE
        bind.groupBack.visibility = View.GONE
    }

}