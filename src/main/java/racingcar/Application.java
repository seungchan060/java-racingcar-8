package racingcar;

import camp.nextstep.edu.missionutils.*;

public class Application {
    public static void main(String[] args) {
        try {
            System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
            String inputCarName = Console.readLine();
            System.out.println("시도할 횟수는 몇 회인가요?");
            String inputNumber = Console.readLine();
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
            
        }
    }
}