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


## 카드 나눠주기

1. 상단 뷰를 segmentButton으로 변경 : Material3의 ToggleButtonGroup의 style을 커스텀하여 사용했다
2. 플레이어 클래스 구현 : 카드 리스트를 가지는 플레이어 클래스를 구현했다
3. 카드 리사이클러뷰 구현 : A~E 영역에 들어갈 카드 리스트를 위해 각각 리사이클러뷰를 달아 연결했다
4. 리팩터링 : 뷰를 제어하는 코드를 ViewUtil로 통일시켜 보일드 코드를 줄였다
5. 카드 나눠주기 과정
   1) 카드 만들기 ~ 섞기
   2) 플레이어 초기화
   3) 플레이어에게 카드 배분
   4) 하단 보드 카드 배분


## 게임로직 구현하기

1. 플레이어를 관리하는 PlayerManager와 카드를 관리하는 CardManager를 Object로 만들었다
2. PlayerManager에선 Player객체를 생성하고 RecyclerView와 Adapter를 연결한다
3. CardManager에선
   1) 사용할 카드를 생성 (한 번만)
   2) 카드를 섞고 게임에 제공
   3) 남은 카드를 하단 보드에 제공
   4) 카드 고르기
   5) 고른 세 카드의 숫자가 모두 동일한지 판별
4. CardAdapter에서 각 카드(아이템)을 클릭하면 3-4의 메서드를 실행한다
5. Card 클래스에서 compareTo를 오버라이딩하여 sort()를 사용하면 숫자 및 타입(개, 고양이, 소 순서)으로 정렬한다