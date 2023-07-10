package io.softeer.luckycardgame.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.softeer.luckycardgame.card.Card
import io.softeer.luckycardgame.databinding.HolderCardBinding

class CardAdapter(private val cardList: MutableList<Card>,private val isMine : Boolean) : RecyclerView.Adapter<CardAdapter.ViewHolder>() {

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
            selectCardSide(bind.cardNumberTop, card.getCardNumber().toString(), isMine)
            selectCardSide(bind.cardNumberBottom, card.getCardNumber().toString(), isMine)
            selectCardSide(bind.cardType, card.getCardType().typeUnicode, isMine)
            bind.cardBack.visibility = if(!isMine) View.VISIBLE else View.INVISIBLE
        }
    }

    /**
     * isMine 속성이 true 일 때, 카드 정보가 보인다
     * false 라면, 카드 뒷면이 보인다
     */
    private fun selectCardSide(textView : TextView, text : String, isMine: Boolean) {
        textView.let {
            it.text = text
            it.visibility = if(isMine) View.VISIBLE else View.INVISIBLE
        }
    }


}