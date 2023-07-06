# android-luckycardgame
Android 학습 프로젝트 #1

## 게임보드 만들기

1. 높이가 작은 화면을 생각해 스크롤 될 수 있게 NestedScrollView를 부모 레이아웃으로 설정
2. 하위에 ConstraintLayout을 만들고 padding 대신 GuideLine을 사용해 바깥쪽 간격 설정
3. ConstraintLayout으로 각 카드를 만들고 backgound에 해당하는 xml을 만들어 배치
4. 글자 투명도는 검색을 통해 color.xml에 투명도+색 코드를 추가
