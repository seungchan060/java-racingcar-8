package racingcar;

import camp.nextstep.edu.missionutils.*;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        try {
            System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
            String inputCarName = Console.readLine();
            System.out.println("시도할 횟수는 몇 회인가요?");
            String inputNumber = Console.readLine();

            RacingCalculator.race(inputCarName, inputNumber);
        } finally {
            Console.close();
        }
    }
}

class RacingCalculator {
    public static void race(String inputCarName, String inputNumber) {
        if (inputCarName == null || inputCarName.isEmpty()) throw new IllegalArgumentException("경주할 자동차 이름을 입력해주세요");
        if (!inputNumber.matches("^(100|[1-9][0-9]?)$")) throw new IllegalArgumentException("1~100 사이의 숫자를 입력해주세요");

        String[] carNames = inputCarName.split(",");
        int tryCount = Integer.parseInt(inputNumber);

        List<Car> cars = new ArrayList<>();
        for (String name : carNames) { // 자동차 검증
            validateCarName(name);
            cars.add(new Car(name));
        }

        System.out.println("\n실행 결과");

        for (int i = 0; i < tryCount; i++) { // 레이싱 시작
            for (Car car : cars) {
                car.move();
                car.printStatus();
            }
            System.out.println();
        }
    }

    private static void validateCarName(String carName) {
        if (carName.isEmpty()) {
            throw new IllegalArgumentException("자동차 이름은 비어 있을 수 없습니다.");
        }

        if (!carName.matches("[a-zA-Z0-9가-힣]+")) {
            throw new IllegalArgumentException("자동차 이름은 영어, 숫자, 한글만 가능합니다: " + carName);
        }

        if (carName.length() > 5) {
            throw new IllegalArgumentException("자동차 이름은 5자 이하만 가능합니다: " + carName);
        }
    }
}

class Car {
    private final String name;
    private int position = 0;

    public Car(String name) {
        this.name = name;
    }

    // 전진 로직
    public void move() {
        int random = Randoms.pickNumberInRange(0, 9);
        if (random >= 4) {
            this.position++;
        }
    }

    // 현재 상태 출력
    public void printStatus() {
        System.out.println(name + " : " + "-".repeat(position));
    }
}