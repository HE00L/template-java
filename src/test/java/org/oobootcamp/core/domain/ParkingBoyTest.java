package org.oobootcamp.core.domain;

import io.vavr.collection.List;
import org.junit.jupiter.api.Test;
import org.oobootcamp.core.exception.ParkCarException;
import org.oobootcamp.core.exception.PickUpCarException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingBoyTest {

    //    Given 停车小弟 管理2个停车场,两个停车场都有剩余停车位 When 停车小弟 停车, Then 停入第一个停车场，停车成功后拿到票
    @Test
    public void should_return_ticket_when_parking_boy_park_given_manage_two_parking_lot_both_has_remainingCount() {
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(2);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        Car car = new Car(1);
        Ticket expectedTicket = new Ticket(car.id());

        assertThat(parkingBoy.parkingCar(car)).isEqualTo(expectedTicket);
        assertThat(parkingBoy.pickUpCar(expectedTicket)).isEqualTo(car);
    }

    //    Given 停车小弟 管理2个停车场,只有第二个停车场有剩余停车位 When 停车小弟 停车, Then 停入第二个停车场，停车成功后拿到票
    @Test
    public void should_return_ticket_when_parking_boy_park_given_manage_two_parking_lot_and_second_has_remainingCount() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(2);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        Car car1 = new Car(1);
        firstParkingLot.parkingCar(car1);

        Car car2 = new Car(2);
        Ticket expectedTicket = new Ticket(car2.id());
        assertThat(parkingBoy.parkingCar(car2)).isEqualTo(expectedTicket);
        assertThat(secondParkingLot.pickUpCar(expectedTicket)).isEqualTo(car2);
    }

    //    Given 停车小弟 管理2个停车场,两个停车场皆无剩余停车位 When 停车小弟 停车, Then 停车失败，提示：停车失败
    @Test
    public void should_park_failed_and_alert_error_message_when_parking_boy_park_given_manage_two_parking_lot_and_both_full() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        Car car1 = new Car(1);
        Car car2 = new Car(2);
        firstParkingLot.parkingCar(car1);
        secondParkingLot.parkingCar(car2);

        Car car3 = new Car(3);
        assertThat(assertThrows(ParkCarException.class, () -> parkingBoy.parkingCar(car3)).getLocalizedMessage()).isEqualTo("停车场已满");
    }

    //    - Given 停车小弟 管理2个停车场,小弟有一张有效票,票是第一个停车场的 When 停车小弟 取车, Then 取车成功
    @Test
    public void should_pick_up_car_when_parking_boy_pick_up_car_given_manage_two_parking_lot_and_have_one_first_lot_ticket() {
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(2);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        Car car = new Car(1);
        Ticket firstParkingLotTicket = parkingBoy.parkingCar(car);

        assertThat(parkingBoy.pickUpCar(firstParkingLotTicket)).isEqualTo(car);
    }

    //    - Given 停车小弟 管理2个停车场,小弟有一张有效票,票是第二个停车场的 When 停车小弟 取车, Then 取车成功
    @Test
    public void should_pick_up_car_when_parking_boy_pick_up_car_given_manage_two_parking_lot_and_have_a_second_lot_ticket() {
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(2);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        Car car = new Car(1);
        Ticket secondParkingLotTicket = secondParkingLot.parkingCar(car);

        assertThat(parkingBoy.pickUpCar(secondParkingLotTicket)).isEqualTo(car);
    }

    //    - Given 停车小弟 管理2个停车场,小弟有一张第三方的停车场的票 When 停车小弟 取车, Then 取车失败，提示：票无效
    @Test
    public void should_pick_up_car_failed_when_parking_boy_pick_up_car_given_manage_two_parking_lot_and_ticket_is_not_belong_to_these_parking_lot() {
        Car car = new Car(1);
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(2);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);

        ParkingLot thirdParkingLot = new ParkingLot(1);
        Ticket thirdTicket = thirdParkingLot.parkingCar(car);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        assertThat(assertThrows(PickUpCarException.class, () -> parkingBoy.pickUpCar(thirdTicket)).getLocalizedMessage()).isEqualTo("票无效");
    }

    //    - Given 停车小弟 管理2个停车场,小弟有一张已经使用的票 When 停车小弟 取车, Then 取车失败，提示：取车失败
    @Test
    public void should_pick_up_car_failed_when_parking_boy_pick_up_car_given_manage_two_parking_lot_and_have_a_used_ticket() {
        Car car = new Car(1);
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(2);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Ticket firstParkingLotTicket = parkingBoy.parkingCar(car);

        assertThat(parkingBoy.pickUpCar(firstParkingLotTicket)).isEqualTo(car);
        assertThat(assertThrows(PickUpCarException.class, () -> parkingBoy.pickUpCar(firstParkingLotTicket)).getLocalizedMessage()).isEqualTo("票无效");
    }

    //  - Given 停车小弟 管理2个停车场,小弟没有票 When 停车小弟 取车, Then 取车失败，提示：票无效
    @Test
    public void should_pick_up_car_failed_when_parking_boy_pick_up_car_given_manage_two_parking_lot_and_no_ticket() {
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(2);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        assertThat(assertThrows(PickUpCarException.class, () -> parkingBoy.pickUpCar(null)).getLocalizedMessage()).isEqualTo("票无效");
    }
}
