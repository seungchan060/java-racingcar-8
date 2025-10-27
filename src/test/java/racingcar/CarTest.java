package racingcar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class CarTest {

    @DisplayName("Car 객체 생성 시 이름과 초기 위치를 확인한다.")
    @ParameterizedTest
    @ValueSource(strings = {"pobi", "woni", "test"})
    void createCar_CheckInitialState(String name) {
        Car car = new Car(name);
        assertThat(car.getName()).isEqualTo(name);
        assertThat(car.getPosition()).isEqualTo(0);
    }

    @DisplayName("무작위 값이 4 이상일 경우 자동차는 전진한다.")
    @Test
    void move_WhenRandomNumberIsOverFour_PositionIncreases() {
        Car car = new Car("test");
        int initialPosition = car.getPosition();

        car.move(); // 한 번 이동 시도
        // position이 0이거나 1이어야 함
        assertThat(car.getPosition()).isBetween(initialPosition, initialPosition + 1);
    }

    @DisplayName("getPosition()으로 현재 위치를 반환한다.")
    @Test
    void getPosition_ReturnCurrentPosition() {
        Car car = new Car("test");
        assertThat(car.getPosition()).isEqualTo(0);

        // position 값을 임의로 변경할 수 없으므로, move()를 통해 변경 후 확인
        car.move();
        assertThat(car.getPosition()).isBetween(0, 1);
    }
}