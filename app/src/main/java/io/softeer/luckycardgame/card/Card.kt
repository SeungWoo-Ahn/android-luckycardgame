package io.softeer.luckycardgame.card

// 캡슐화를 위해 모든 필드를 private 처리했다.
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

    // 해당 카드의 최대/최소 숫자를 설정하면 나중에 유지보수에 편리했다.
    companion object {
        const val MAX_NUMBER = 12
        const val MIN_NUMBER = 1
    }
}