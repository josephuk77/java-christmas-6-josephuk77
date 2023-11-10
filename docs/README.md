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

- [ ]  예외처리 메세지 (**`ExceptionMessage`**)
- [ ]  정상 실행 메세지 (**`RunMessage`**)

## **도메인 (Domain)**

- [ ]  **MenuItem(enum)**
   - String category
   - String name
   - int price
   - [ ]  메뉴 아이템의 종류 반환 (**`getCategory()`**)
   - [ ]  메뉴 아이템의 이름 반환 (**`getName()`**)
   - [ ]  메뉴 아이템의 가격 반환 (**`getPrice()`**)
- [ ]  **Order**
   - ORDER_PATTERN = "([가-힣]+)-(1[0-9]|20|[1-9])"
   - Map<MenuItem, Integer> items
   - [ ]  주문에 메뉴 아이템을 추가 (**`addItem(MenuItem item, int quantity)`**)
   - [ ]  할인 전 총주문 금액을 계산 (**`getTotalPrice()`**)
   - [ ]  주문된 모든 아이템과 수량을 반환 (**`getItems()`**)
- [ ]  **DiscountType**
   - List<Integer> weekend = List.of(1,2,8,9,15,16,22,23,29,30)
   - List<Integer> star = List.of(3,10,17,24,25,31)
   - boolean isChristmasDiscount
   - boolean isStarDiscount
   - String weekDiscountType  디저트 or 메인
   - boolean isGiftEvent
   - [ ]  날짜가 25일전이면 true반환 (**`christmasDiscountCheck(int day)`**)
   - [ ]  날짜가 star에 있으면 true반환 (**`startDiscountCheck(int day)`**)
   - [ ]  날짜가 weekend에 있으면 메인 반환없으면 디저트 반환 (**`weekDiscount(int day)`**)
   - [ ]  총 금액이 12만원 이상이면 true반환 (**`giftEnent(int totalPrize)`**)
   - [ ]  christmasDiscount 반환 (**`getChristmasDiscountStatus()`**)
   - [ ]  starDiscount 반환 (**`getStarDiscountStatus()`**)
   - [ ]  weekDiscount 반환 (**`**getWeekDiscount()`**)
   - [ ]  giftEvent 반환 (**`getGiftEventStatus()`**)
- [ ]  **DiscountCalculator**
   - int christmasDiscount
   - int starDiscount
   - int weekDiscount
   - int giftEvent
   - [ ]  크리스마스 할인 계산 후 반환 (**`calculateChristmasDiscount(int day, boolean isChristmasDiscount)`**)
   - [ ]  별 표시된 날짜의 할인 계산 후 반환 (**`calculateStarDiscount(boolean isStarDiscount)`**)
   - [ ]  주말 할인을 계산 후 반환 (**`calculateWeekendDiscount(Order order, String weekDiscountType)`**)
   - [ ]  증정품 가격 반환 (**`calculateGiftEvent(boolean isGiftEvent)`**)
   - [ ]  크리스마스 할인 금액 반환 (**`getChristmasDiscount()`**)
   - [ ]  별 표시된 날짜 할인 금액 반환 (**`getStarDiscount()`**)
   - [ ]  주말 할인 금액 반환 (**`getWeekDiscount()`**)
   - [ ]  증정 이벤트 금액 반환 (**`getGiftEvent()`**)
- [ ]  **EventBadge**
   - String badge
   - int totalDiscount
   - [ ]  총할인 금액 반환(**`calculateTotalDiscount(DiscountCalculator discountCalculator)`**)
   - [ ]  배지 선정(**`eventBadge(int totalDiscount)`**)
   - [ ]  배지 반환 (**`getBadge()`**)
   - [ ]  총혜택 금액 반환 (**`getTotalDiscount()`**)

## **뷰 (View)**

- [ ]  InputView
   - [ ]  날짜 입력 (**`inputDay`**)
   - [ ]  음식 입력 (**`inputOrder`**)
- [ ]  OutputView
   - [ ]  시작 메세지
   - [ ]  날짜 입력 안내 메세지(**`printDayInputMessage`**)
   - [ ]  음식 입력 안내 메세지(**`printOrderInputMessage`**)
   - [ ]  날짜 출력 메세지(**`printDayMessage`**)
   - [ ]  주문 메뉴 안내 메세지(**`printOrderMenuMessage`**)
   - [ ]  할인 전 총주문 금액 메세지 출력 (**`printTotalPriceBeforeDiscount`**)
   - [ ]  증정 메뉴 메세지 출력 (**`printGiftMenuMessage`**)
   - [ ]  혜택 내역 메세지 출력 (**`printBenefitDetailsMessage`**)
   - [ ]  총혜택 금액 메세지 출력 (**`printTotalBenefitAmountMessage`**)
   - [ ]  할인 후 예상 결제 금액 메세지 출력 (**`printExpectedPaymentAfterDiscount`**)
   - [ ]  12월 이벤트 배지 메세지 출력 (**`printEventBadgeMessage`**)

## **컨트롤러 (Controller)**

- [ ]  CristmasController
   - [ ]  애플리케이션 실행 (**`run()`**)
   - [ ]  사용자 입력 처리 (**`processUserInput()`**)
   - [ ]  주문 처리 (**`processOrder()`**)
   - [ ]  할인 계산 및 적용 (**`calculateDiscounts()`**)
   - [ ]  배지 부여(**`giveBadge()`**)
   - [ ]  결과 출력 (**`displayResults()`**)