# android-luckycardgame
Android 학습 프로젝트 #1

## 게임보드 만들기

1. 높이가 작은 화면을 생각해 스크롤 될 수 있게 NestedScrollView를 부모 레이아웃으로 설정
2. 하위에 ConstraintLayout을 만들고 padding 대신 GuideLine을 사용해 바깥쪽 간격 설정
3. ConstraintLayout으로 각 카드를 만들고 backgound에 해당하는 xml을 만들어 배치
4. 글자 투명도는 검색을 통해 color.xml에 투명도+색 코드를 추가


## 럭키카드 클래스 만들기

1. 카드 종류는 enum class로 구현, 각 enum에 해당 유니코드 속성으로 입력
2. 카드 클래스 구현, 카드 필드 & 카드 정보 메서드로 구성
3. 카드 출력 과정
   1) 카드 타입의 빈 배열 생성
   2) 카드 종류의 수만큼, 카드 번호 (1~12) 만큼 순회하며 카드 인스턴스 생성 -> 배열에 삽입
   3) 배열 섞기
   4) 배열 순회하며 카드 정보 출력
