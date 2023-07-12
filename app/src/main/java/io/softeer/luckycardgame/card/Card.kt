package io.softeer.luckycardgame.card

class Card(
    private val number : Int,
    private val type: CardType,
) : Comparable<Card> {

    fun getCardNumber() : Int = number

    fun getCardType() : CardType = type

    // 카드 정보 출력
    override fun toString(): String {
        return "${type.typeUnicode}${String.format("%02d",number)}"
    }

    override fun compareTo(other: Card): Int {
        return compareValuesBy(this, other, { it.number }, { it.type })
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Card) return false
        return number == other.getCardNumber() && type == other.getCardType()
    }

    companion object {
        const val MAX_NUMBER = 12
        const val MIN_NUMBER = 1
    }
}