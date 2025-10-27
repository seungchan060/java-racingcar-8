# 2주차 미션 - 자동차 경주

## 기능 목록
### 입력
- **경주할 자동차 이름** 입력 받기
- **시도 횟수 입력** 받기
### 유효성 검사
- **자동차 이름**이 5자 이하인지 검증
- **자동차 이름**이 빈 값인지(`,` 포함) 검증
- **시도 횟수**가 1~100 사이의 유효한 숫자인지 검증
- 잘못된 입력 시 `IllegalArgumentException` 발생 후 종료
### 자동차 객체
- **Car 객체**를 생성하고 이름과 위치를 관리
- `Car` 객체의 **전진 로직** 구현 (랜덤 값 4 이상)
### 게임 실행
- **주어진 횟수**만큼 경주를 반복 실행
- 각 차수별로 실행 결과 (이름: `-` 수) 출력
### 우승자 판별
- 경주 완료 후 **가장 멀리 이동**한 최고 위치를 판별
- 최고 위치와 **동일한 위치**를 가진 자동차를 우승자로 선정
- **우승자 이름**을 쉼표로 구분하여 최종 출력

## 구현 순서
- `Application.main`에서 자동차 이름과 시도 횟수를 입력받는 기본 구조 구현
- 경주 로직을 담을 `race` 메소드 틀 생성
- 구현할 기능 목록 등 요구사항 정리
- `inputCarName`의 `null/empty` 검증 및 `inputNumber`의 정규식 검증 추가
- 입력받은 자동차 이름을 쉼표 기준으로 분리
- 문자열 횟수를 `int`로 변환
- `Car` 클래스(이름, 위치)의 기본 틀 구현 및 생성자 추가
- `Car.move()`에 랜덤 값 기반 전진 로직 추가
- `Car` 객체 리스트 생성 및 시도 횟수(정수형) 관련 로직을 사용하도록 구조 변경
- `validateCarName` 호출을 위한 반복문 틀 추가
- `validateCarName` 메소드에 5자 제한, 빈 값, 허용 문자 검증 로직 구현
- 전체 경주 횟수(`tryCount`)에 따른 반복문(`for`) 틀 추가
- `Car` 객체에 `getName()`, `getPosition()` 추가 및 레이스 반복문 안에서 `car.move()` 실행
- `Car.printStatus()` 메소드 (이름: `-` 수) 구현
- 우승자 출력 메소드 틀 추가
- `printWinners` 내부에서 `maxPosition`을 찾는 로직 구현
- `maxPosition`과 같은 위치의 자동차 이름을 모으는 로직 및 최종 출력 구현
- `validateCarName` 메소드 내 스타일 개선
- 최종 기능에 대한 테스트 코드 작성 및 실행

## 주요 구현
`Car` 클래스는 `position` 상태와 `move()` 행위를 가집니다.

- **전진 조건**: `camp.nextstep.edu.missionutils.Randoms.pickNumberInRange(0, 9)`를 통해 무작위 숫자를 생성하고, 이 숫자가 **4 이상**일 경우에만 내부 `position` 값을 1 증가시킵니다.

### 최종 우승자 판별 및 출력

`RacingCalculator.printWinners(List<Car> cars)` 메소드가 최종 우승자를 판별하고 출력합니다.

1. **최고 위치 탐색**: 모든 `Car` 객체를 순회하며 **가장 큰 `position` 값**(`maxPosition`)을 찾습니다.
2. **우승자 필터링**: `maxPosition`과 동일한 `position`을 가진 모든 `Car` 객체의 `name`을 수집합니다.
3. **결과 출력**: 수집된 우승자 이름을 `String.join(", ", winners)`를 사용하여 쉼표(`,` )로 연결하여 최종 출력합니다. 이는 단독 우승이든 공동 우승이든 하나의 출력 로직으로 처리합니다

## 실행 결과
<img width="398" height="600" alt="스크린샷 2025-10-22 오후 4 04 35" src="https://github.com/user-attachments/assets/8ec2ed01-a597-455f-bd3f-1d08c6a1b273" />
<img width="398" height="600" alt="스크린샷 2025-10-27 오후 1 35 29" src="https://github.com/user-attachments/assets/0e4080f3-6813-4e69-8bbb-510aeaaa5092" />
<img width="398" height="670" alt="스크린샷 2025-10-27 오후 1 35 36" src="https://github.com/user-attachments/assets/896432f8-525d-4a86-bb31-6624f8ba8c93" />

## 단위 테스트 리스트
### CarTest
- `getPosition()`으로 현재 **위치를 반환**
- 무작위 값이 **4이상**일 경우 자동차는 **전진**
- `Car 객체` 생성 시 **이름**과 **초기 위치**를 확인

### ApplicationTest
- **모두 전진**하여 **공동 우승**하는 경우 테스트
- 콤마로 분리 시 **비어있는 자동차 이름이 포함**된 경우 예외 발생
- **전진하는 경우**와 **멈추는 경우**를 포함하는 기능 테스트 및 **우승자 확인**
- 자동차 이름이 **5자를 초과**하는 경우 예외 발생
- 시도 횟수가 **1~100 범위를 벗어**나는 경우 예외 발생

### RacingCalculatorTest
- 자동차 이름이 `Null`이거나 `비어있는` 경우 `IllegalArgumentException`을 던진다.
- 콤마로 분리 시 **비어있는 자동차 이름이 포함**된 경우 `IllegalArgumentException`을 던진다.
- 자동차 이름에 **허용되지 않는 문자**가 포함된 경우 `IllegalArgumentException`을 던진다.
- 자동차 이름이 **5자 이하**인 경우 통과한다.
- 최고 위치가 동일한 **우승자가 여러 명일 경우 쉼표로 분리**하여 출력한다.
- 시도 횟수가 **1~100 범위 내의 숫자인 경우** 통과한다.
- 시도 횟수가 **1~100 범위를 벗어나거나 숫자가 아닌 경우** `IllegalArgumentException`을 던진다.
- 자동차 이름이 **6자 이상**인 경우 `IllegalArgumentException`을 던진다.

## 단위 테스트 결과
<img width="599" height="388" alt="스크린샷 2025-10-22 오후 4 02 19" src="https://github.com/user-attachments/assets/adc59322-8533-4150-a221-0ebef99bcc3a" />

## 과제 진행 요구 사항
- 미션은 [자동차 경주](https://github.com/woowacourse-precourse/java-racingcar-8) 저장소를 포크하고 클론하는 것으로 시작한다.
- **기능을 구현하기 전 `README.md`에 구현할 기능 목록을 정리**해 추가한다.
- Git의 커밋 단위는 앞 단계에서 `README.md`에 정리한 기능 목록 단위로 추가한다.
    - [AngularJS Git Commit Message Conventions](https://gist.github.com/stephenparish/9941e89d80e2bc58a153)을 참고해 커밋 메시지를 작성한다.
- 자세한 과제 진행 방법은 프리코스 진행 가이드 문서를 참고한다.

## 기능 요구 사항
초간단 자동차 경주 게임을 구현한다.

- 주어진 횟수 동안 n대의 자동차는 전진 또는 멈출 수 있다.
- 각 자동차에 이름을 부여할 수 있다. 전진하는 자동차를 출력할 때 자동차 이름을 같이 출력한다.
- 자동차 이름은 쉼표(,)를 기준으로 구분하며 이름은 5자 이하만 가능하다.
- 사용자는 몇 번의 이동을 할 것인지를 입력할 수 있어야 한다.
- 전진하는 조건은 0에서 9 사이에서 무작위 값을 구한 후 무작위 값이 4 이상일 경우이다.
- 자동차 경주 게임을 완료한 후 누가 우승했는지를 알려준다. 우승자는 한 명 이상일 수 있다.
- 우승자가 여러 명일 경우 쉼표(,)를 이용하여 구분한다.
- 사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException`을 발생시킨 후 애플리케이션은 종료되어야 한다.

### **입출력 요구 사항**

### **입력**

- 경주할 자동차 이름(이름은 쉼표(,) 기준으로 구분)

```
pobi,woni,jun
```

- 시도할 횟수

```
5
```

### **출력**

- 차수별 실행 결과

```
pobi : --
woni : ----
jun : ---
```

- 단독 우승자 안내 문구

```
최종 우승자 : pobi
```

- 공동 우승자 안내 문구

```
최종 우승자 : pobi, jun
```

### **실행 결과 예시**

```
경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)
pobi,woni,jun
시도할 횟수는 몇 회인가요?
5

실행 결과
pobi : -
woni :
jun : -

pobi : --
woni : -
jun : --

pobi : ---
woni : --
jun : ---

pobi : ----
woni : ---
jun : ----

pobi : -----
woni : ----
jun : -----

최종 우승자 : pobi, jun
```

## 프로그래밍 요구 사항 1
- JDK 21 버전에서 실행 가능해야 한다.
- 프로그램 실행의 시작점은 `Application`의 `main()`이다.
- `build.gradle` 파일은 변경할 수 없으며, **제공된 라이브러리 이외의 외부 라이브러리는 사용하지 않는다.**
- 프로그램 종료 시 `System.exit()`를 호출하지 않는다.
- 프로그래밍 요구 사항에서 달리 명시하지 않는 한 파일, 패키지 등의 이름을 바꾸거나 이동하지 않는다.
- 자바 코드 컨벤션을 지키면서 프로그래밍한다.
    - 기본적으로 [Java Style Guide](https://github.com/woowacourse/woowacourse-docs/blob/main/styleguide/java)를 원칙으로 한다.

## 프로그래밍 요구 사항 2
- indent(인덴트, 들여쓰기) depth를 3이 넘지 않도록 구현한다. 2까지만 허용한다.
    - 예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
    - 힌트: indent(인덴트, 들여쓰기) depth를 줄이는 좋은 방법은 함수(또는 메서드)를 분리하면 된다.
- 3항 연산자를 쓰지 않는다.
- 함수(또는 메서드)가 한 가지 일만 하도록 최대한 작게 만들어라.
- JUnit 5와 AssertJ를 이용하여 정리한 기능 목록이 정상적으로 작동하는지 테스트 코드로 확인한다.
    - 테스트 도구 사용법이 익숙하지 않다면 아래 문서를 참고하여 학습한 후 테스트를 구현한다.
        - [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide)
        - [AssertJ User Guide](https://assertj.github.io/doc)
        - [AssertJ Exception Assertions](https://www.baeldung.com/assertj-exception-assertion)
        - [Guide to JUnit 5 Parameterized Tests](https://www.baeldung.com/parameterized-tests-junit-5)

### **라이브러리**

- `camp.nextstep.edu.missionutils`에서 제공하는 `Randoms` 및 `Console` API를 사용하여 구현해야 한다.
    - Random 값 추출은 `camp.nextstep.edu.missionutils.Randoms`의 `pickNumberInRange()`를 활용한다.
    - 사용자가 입력하는 값은 `camp.nextstep.edu.missionutils.Console`의 `readLine()`을 활용한다.

### **사용 예시**

- 0에서 9까지의 정수 중 한 개의 정수 반환

```java
Randoms.pickNumberInRange(0, 9);
```