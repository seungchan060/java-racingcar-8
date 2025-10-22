package racingcar;

import camp.nextstep.edu.missionutils.*;

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
        if (!inputNumber.matches("[0-9]")) throw new IllegalArgumentException("0~9 사이의 숫자를 입력해주세요");

        String[] cars = inputCarName.split(",");
        int tryCount = Integer.parseInt(inputNumber);

        for (String car : cars) { // 자동차 검증
            validateCarName(car);
        }

        for (int i = 0; i < tryCount; i++) { // 레이싱 시작
            for (String car : cars) {
                int random = Randoms.pickNumberInRange(0, 9);

                if (random >= 4) {
                    System.out.println(car + " : 전진!");
                } else {
                    System.out.println(car + " : 멈춤!");
                }
            }
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