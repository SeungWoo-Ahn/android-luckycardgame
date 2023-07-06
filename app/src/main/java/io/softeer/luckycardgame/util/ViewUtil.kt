package io.softeer.luckycardgame.util

import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import io.softeer.luckycardgame.adapter.CardAdapter
import io.softeer.luckycardgame.adapter.RecyclerItemDecoration

object ViewUtil {

    /**
     * 리사이클러뷰 설정
     */
    fun setRecycler(
        recyclerView: RecyclerView,
        layoutManager: LayoutManager,
        rightSpace: Int,
        topSpace: Int,
        adapter: CardAdapter
    ) {
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
    fun changeBoardView(
        dGroup: Group,
        eGroup: Group,
        bottomBoard: TextView,
        bottomRecyclerView: RecyclerView,
        playerNumber: Int,
        play: (Int) -> Unit
    ) {
        when(playerNumber) {
            3 -> {
                dGroup.visibility = View.GONE
                eGroup.visibility = View.GONE
                bottomRecyclerView.setHeight(240f)
                bottomBoard.setHeight(240f)
            }
            4 -> {
                dGroup.visibility = View.VISIBLE
                eGroup.visibility = View.GONE
                bottomRecyclerView.setHeight(240f)
                bottomBoard.setHeight(240f)
            }
            5 -> {
                dGroup.visibility = View.VISIBLE
                eGroup.visibility = View.VISIBLE
                bottomRecyclerView.setHeight(120f)
                bottomBoard.setHeight(120f)
            }
        }
        play(playerNumber)
    }

    /**
     * 뷰 높이 설정
     */
    fun View.setHeight(value: Float) {
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