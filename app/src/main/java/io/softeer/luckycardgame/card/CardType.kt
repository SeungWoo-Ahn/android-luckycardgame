package io.softeer.luckycardgame.card

// 카드 종류를 enum class로 사용하면 직관적이고 유지보수가 편리해 따로 만들었다.
// 해당 유니코드도 속성을 통해 쉽게 접근할 수 있다.
enum class CardType(val typeUnicode : String) {
    Dog(typeUnicode = "\uD83D\uDC36"),
    Cat(typeUnicode = "\uD83D\uDC31"),
    Cow(typeUnicode = "\uD83D\uDC2E")
}