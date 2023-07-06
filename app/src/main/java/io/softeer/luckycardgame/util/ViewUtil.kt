package io.softeer.luckycardgame.util

import android.util.TypedValue
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import io.softeer.luckycardgame.adapter.CardAdapter
import io.softeer.luckycardgame.adapter.RecyclerItemDecoration

object ViewUtil {

    /**
     * 리사이클러뷰 설정
     */
    fun setRecycler(recyclerView: RecyclerView,layoutManager: LayoutManager, rightSpace : Int, topSpace : Int, adapter: CardAdapter) {

        recyclerView.let {
            it.layoutManager = layoutManager
            if (it.itemDecorationCount != 0) it.removeItemDecoration(it.getItemDecorationAt(0))
            it.addItemDecoration(RecyclerItemDecoration(rightSpace,topSpace))
            it.adapter = adapter
        }

    }

    /**
     * 보드 뷰 변경
     */
    fun changeBoardView(dBoard : ConstraintLayout, eBoard : ConstraintLayout, bottomBoard : ConstraintLayout, playerNumber : Int, play : (Int)->Unit) {
        when(playerNumber) {
            3 -> {
                dBoard.visibility = View.GONE
                eBoard.visibility = View.GONE
                bottomBoard.setHeight(240f)
            }
            4 -> {
                dBoard.visibility = View.VISIBLE
                eBoard.visibility = View.GONE
                bottomBoard.setHeight(240f)
            }
            5 -> {
                dBoard.visibility = View.VISIBLE
                eBoard.visibility = View.VISIBLE
                bottomBoard.setHeight(120f)
            }
        }
        play(playerNumber)
    }

    /**
     * 뷰 높이 설정
     */
    private fun View.setHeight(value: Float) {
        val lp = layoutParams
        lp?.let {
            lp.height =  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, resources.displayMetrics).toInt()
            layoutParams = lp
        }
    }

    /**
     * 간격 설정
     */
    fun setItemSpace(count : Int) : Int {
        if (count == 3) return -42
        if (count == 4) return -24
        return 0
    }
}