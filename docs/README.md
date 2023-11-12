# 프로그램 흐름

- [ ]  시작메세지 출력
- [ ]  날짜 입력
   - [ ]  1~31사이의 수 인가
   - [ ]  숫자가 맞는가
- [ ]  할인 여부 확인
- [ ]  주문할 메뉴 입력
   - [ ]  형식이 알맞게 들어 왔는가
   - [ ]  메뉴가 있는 메뉴인가
   - [ ]  같은 메뉴가 중복으로 들어가 있는가
   - [ ]  음료만 있는가
   - [ ]  주문 수가 20이 안넘는가
- [ ]  할인 계산
- [ ]  배지 부여
- [ ]  결과 출력

# **기능 명세서**

## 메세지 **(Message)**

- [x]  예외처리 메세지 (**`ExceptionMessage`**)
- [x]  정상 실행 메세지 (**`RunMessage`**)

## **도메인 (Domain)**

- [x]  **MenuItem(enum)**
   - String category
   - String name
   - int price
   - [x]  메뉴 아이템의 종류 반환 (**`getCategory()`**)
   - [x]  메뉴 아이템의 이름 반환 (**`getName()`**)
   - [x]  메뉴 아이템의 가격 반환 (**`getPrice()`**)
- [x]  **Order**
   - ORDER_PATTERN = "([가-힣]+)-(1[0-9]|20|[1-9])"
   - Map<MenuItem, Integer> items
   - [x]  주문에 메뉴 아이템을 추가 (**`addItem(MenuItem item, int quantity)`**)
   - [x]  할인 전 총주문 금액을 계산 (**`getTotalPrice()`**)
   - [x]  주문된 모든 아이템과 수량을 반환 (**`getItems()`**)
- [x]  **DiscountType**
   - List<Integer> weekend = List.of(1,2,8,9,15,16,22,23,29,30)
   - List<Integer> star = List.of(3,10,17,24,25,31)
   - int day
   - boolean isChristmasDiscount
   - boolean isStarDiscount
   - String weekDiscountType  디저트 or 메인
   - boolean isGiftEvent
   - [x]  날짜가 25일전이면 true반환 (**`christmasDiscountCheck(int day)`**)
   - [x]  날짜가 star에 있으면 true반환 (**`startDiscountCheck(int day)`**)
   - [x]  날짜가 weekend에 있으면 메인 반환없으면 디저트 반환 (**`weekDiscount(int day)`**)
   - [x]  총 금액이 12만원 이상이면 true반환 (**`giftEnent(int totalPrize)`**)
   - [x]  christmasDiscount 반환 (**`getChristmasDiscountStatus()`**)
   - [x]  starDiscount 반환 (**`getStarDiscountStatus()`**)
   - [x]  weekDiscount 반환 (**`**getWeekDiscount()`**)
   - [x]  giftEvent 반환 (**`getGiftEventStatus()`**)
- [x]  **DiscountCalculator**
   - int christmasDiscount
   - int starDiscount
   - int weekDiscount
   - int giftEvent
   - [x]  크리스마스 할인 계산 후 반환 (**`calculateChristmasDiscount(int day, boolean isChristmasDiscount)`**)
   - [x]  별 표시된 날짜의 할인 계산 후 반환 (**`calculateStarDiscount(boolean isStarDiscount)`**)
   - [x]  주말 할인을 계산 후 반환 (**`calculateWeekendDiscount(Order order, String weekDiscountType)`**)
   - [x]  증정품 가격 반환 (**`calculateGiftEvent(boolean isGiftEvent)`**)
   - [x]  총 할인금액 반환 (**`totalDiscount()`**)
   - [x]  날짜 반환 (**`getDay()`**)
   - [x]  크리스마스 할인 금액 반환 (**`getChristmasDiscount()`**)
   - [x]  별 표시된 날짜 할인 금액 반환 (**`getStarDiscount()`**)
   - [x]  주말 할인 금액 반환 (**`getWeekDiscount()`**)
   - [x]  증정 이벤트 금액 반환 (**`getGiftEvent()`**)
- [x]  **EventBadge**
   - String badge
   - [x]  배지 선정(**`eventBadge(int totalDiscount)`**)
   - [x]  배지 반환 (**`getBadge()`**)

## **뷰 (View)**

- [x]  InputView
   - [x]  날짜 입력 (**`inputDay`**)
   - [x]  음식 입력 (**`inputOrder`**)
- [x]  OutputView
   - [x]  시작 메세지
   - [x]  날짜 입력 안내 메세지(**`printDayInputMessage`**)
   - [x]  음식 입력 안내 메세지(**`printOrderInputMessage`**)
   - [x]  날짜 출력 메세지(**`printDayMessage`**)
   - [x]  주문 메뉴 안내 메세지(**`printOrderMenuMessage`**)
   - [x]  할인 전 총주문 금액 메세지 출력 (**`printTotalPriceBeforeDiscount`**)
   - [x]  증정 메뉴 메세지 출력 (**`printGiftMenuMessage`**)
   - [x]  혜택 내역 메세지 출력 (**`printBenefitDetailsMessage`**)
   - [x]  총혜택 금액 메세지 출력 (**`printTotalBenefitAmountMessage`**)
   - [x]  할인 후 예상 결제 금액 메세지 출력 (**`printExpectedPaymentAfterDiscount`**)
   - [x]  12월 이벤트 배지 메세지 출력 (**`printEventBadgeMessage`**)

## **컨트롤러 (Controller)**

- [x]  CristmasController
  - [x] 애플리케이션의 메인 흐름을 제어합니다. (**`start()`**)
  - [x] 사용자로부터 방문 날짜를 입력받습니다. (**`inputDay()`**)
  - [x] 사용자로부터 주문할 메뉴와 수량을 입력받습니다. (**`inputOrder()`**)
  - [x] 입력받은 날짜와 주문 메뉴를 출력합니다. (**`printDayAndOrder()`**)
  - [x] 할인 전 총주문 금액과 증정 메뉴 여부를 출력합니다. (**`printTotalPriceAndGiftMenu()`**)
  - [x] 할인 조건에 따라 할인 금액을 계산합니다. (**`calculateDiscounts()`**)
  - [x] 할인 및 혜택 내역을 출력합니다. (**`printDiscountDetails()`**)
  - [x] 총혜택 금액에 따라 이벤트 배지를 부여합니다. (**`assignBadge()`**)
  - [x] 부여된 배지와 할인 후 예상 결제 금액을 출력합니다. (**`printBadge()`**)