package io.softeer.luckycardgame.card

// 캡슐화를 위해 모든 필드를 private 처리했다.
class Card(
    private val number : Int,
    private val type: CardType,
) {

    fun getCardNumber() : String = number.toString()

    fun getCardType() : String = type.typeUnicode

    // 필요한 카드 정보만 볼 수 있게 메서드를 만들었다.
    fun cardInfo() : String {
        return "${type.typeUnicode}${String.format("%02d",number)}"
    }

    // 해당 카드의 최대/최소 숫자를 설정하면 나중에 유지보수에 편리했다.
    companion object {
        const val MAX_NUMBER = 12
        const val MIN_NUMBER = 1
    }
}