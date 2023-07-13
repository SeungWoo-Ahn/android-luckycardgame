package io.softeer.luckycardgame.util

import io.softeer.luckycardgame.adapter.CardAdapter
import io.softeer.luckycardgame.card.Card
import io.softeer.luckycardgame.player.Player
import java.util.Queue
import kotlin.math.abs

object GameManager {

    val playerList = mutableListOf<Player>()
    val matchPool = mutableListOf<Int>()

    fun selectCardByPlayer(
        playerQueue : Queue<Player>,
        card : Card,
        adapterId : Int,
        selectPool: MutableMap<Int, MutableList<Card>>,
        matchPool : MutableList<Int>,
        endGame : () -> Unit
    ) : Boolean {
        var currentPlayer = playerQueue.peek()!!
        if (!currentPlayer.canSelectCard()) {
            currentPlayer = changeCurrentPlayer(playerQueue)
        }
        val playerCardMatch = currentPlayer.selectCard(card)
        updateSelectPool(card, adapterId, selectPool)
        if (playerCardMatch && checkGameNeedEnd(card.getCardNumber(), matchPool, false)) {
            endGame()
        }
        return playerCardMatch
    }

    private fun updateSelectPool(card: Card, adapterId: Int, selectPool: MutableMap<Int, MutableList<Card>>) {
        val selectList = selectPool[adapterId] ?: mutableListOf()
        selectList.add(card)
        selectPool[adapterId] = selectList
    }

    private fun changeCurrentPlayer(playerQueue: Queue<Player>) : Player {
        playerQueue.offer(playerQueue.poll())
        return playerQueue.peek()!!
    }

    fun checkSelectedCardsSame(cards : List<Card>) : Boolean {
        return cards.all { it.getCardNumber() == cards[0].getCardNumber() }
    }

    fun checkGameNeedEnd(
        cardNumber : Int,
        matchPool : MutableList<Int>,
        onlyCheck : Boolean
    ) : Boolean {
        if (cardNumber == 7) {
            matchPool.add(7)
            return true
        }
        val needEnd = matchPool.any { it + cardNumber == 7 || abs(it - cardNumber) == 7 }
        if (onlyCheck) matchPool.add(cardNumber)
        return needEnd
    }

    fun updateGameUI(
        selectPool: MutableMap<Int, MutableList<Card>>,
        adapterList: MutableList<CardAdapter>
    ) {
        selectPool.forEach{ (id, selectList) ->
            adapterList[id].updateAdapter(selectList)
        }
        selectPool.clear()
    }

    fun findWinner() : List<Int> {
        val winNumbers = mutableListOf<Int>()
        for (matchNumber in matchPool) {
            matchPool.remove(matchNumber)
            if (checkGameNeedEnd(matchNumber, matchPool, true)) {
                winNumbers.add(matchNumber)
            }
            matchPool.add(matchNumber)
        }
        val winnerList = mutableListOf<Int>()
        for (player in playerList) {
            for (matchNumber in player.matchList) {
                if (winNumbers.contains(matchNumber)) winnerList.add(player.playerId)
            }
        }
        return winnerList
    }

    fun setGameResult(playerList : MutableList<Player>) {
        playerList.addAll(playerList)
    }

    fun resetGameResult() {
        playerList.clear()
        matchPool.clear()
    }
}