package org.oobootcamp.core.domain;

import io.vavr.collection.List;
import org.junit.jupiter.api.Test;
import org.oobootcamp.core.exception.ParkingLotFullException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class SmartParkingBoyTest {

    // Given 停车小弟 管理2个停车场,两个停车场都有剩余停车位，第一个停车场剩余1个空位，第二个停车场剩余2个车位 When 停车小弟 停车, Then 停入第二个停车场，停车成功后拿到票
    @Test
    public void should_return_ticket_when_smart_parking_boy_park_given_manage_two_parking_lot_and_first_parking_lot_remaining_1_and_second_parking_lot_remaining_2() {
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(2);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        ParkingBoy smartParkingBoy  = new SmartParkingBoy(parkingLots);

        firstParkingLot.parkingCar(new Car());
        Car car = new Car();
        Ticket expectedTicket = smartParkingBoy.parkingCar(car);

        assertThat(expectedTicket).isNotNull();
        assertThat(secondParkingLot.pickUpCar(expectedTicket)).isEqualTo(car);
    }

    // Given 停车小弟 管理2个停车场,两个停车场都有剩余停车位，第一个停车场剩余2个空位，第二个停车场剩余1个车位 When 停车小弟 停车, Then 停入第一个停车场，停车成功后拿到票
    @Test
    public void should_return_ticket_when_smart_parking_boy_park_given_manage_two_parking_lot_and_first_parking_lot_remaining_2_and_second_parking_lot_remaining_1() {
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(2);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        ParkingBoy smartParkingBoy  = new SmartParkingBoy(parkingLots);

        secondParkingLot.parkingCar(new Car());
        Car car = new Car();
        Ticket expectedTicket = smartParkingBoy.parkingCar(car);

        assertThat(expectedTicket).isNotNull();
        assertThat(firstParkingLot.pickUpCar(expectedTicket)).isEqualTo(car);
    }

    // Given 停车小弟 管理3个停车场,三个停车场都有剩余停车位，第一个停车场剩余1个空位，第二个停车场剩余1个车位，第三个停车场剩余2个车位 When 停车小弟 停车, Then 停入第三个停车场，停车成功后拿到票
    @Test
    public void should_return_ticket_when_smart_parking_boy_park_given_manage_3_parking_lot_and_first_parking_lot_remaining_1_and_second_parking_lot_remaining_1_and_third_parking_lot_remaining_2() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        ParkingLot thirdParkingLot = new ParkingLot(2);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot, thirdParkingLot);
        ParkingBoy smartParkingBoy  = new SmartParkingBoy(parkingLots);

        Car car = new Car();
        Ticket expectedTicket = smartParkingBoy.parkingCar(car);

        assertThat(expectedTicket).isNotNull();
        assertThat(thirdParkingLot.pickUpCar(expectedTicket)).isEqualTo(car);
    }

    // Given 停车小弟 管理3个停车场,三个停车场都有剩余停车位，第一个停车场剩余2个空位，第二个停车场剩余2个车位，第三个停车场剩余1个车位 When 停车小弟 停车, Then 停入第一个停车场，停车成功后拿到票
    @Test
    public void should_return_ticket_when_smart_parking_boy_park_given_manage_3_parking_lot_and_first_parking_lot_remaining_2_and_second_parking_lot_remaining_2_and_third_parking_lot_remaining_1() {
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(2);
        ParkingLot thirdParkingLot = new ParkingLot(2);
        thirdParkingLot.parkingCar(new Car());
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot, thirdParkingLot);
        ParkingBoy smartParkingBoy  = new SmartParkingBoy(parkingLots);

        Car car = new Car();
        Ticket expectedTicket = smartParkingBoy.parkingCar(car);

        assertThat(expectedTicket).isNotNull();
        assertThat(firstParkingLot.pickUpCar(expectedTicket)).isEqualTo(car);
    }

    // Given 停车小弟 管理3个停车场,三个停车场都有剩余停车位，第一个停车场剩余1个空位，第二个停车场剩余2个车位，第三个停车场剩余2个车位 When 停车小弟 停车, Then 停入第二个停车场，停车成功后拿到票
    @Test
    public void should_return_ticket_when_smart_parking_boy_park_given_manage_3_parking_lot_and_first_parking_lot_remaining_1_and_second_parking_lot_remaining_2_and_third_parking_lot_remaining_2() {
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(2);
        ParkingLot thirdParkingLot = new ParkingLot(2);
        firstParkingLot.parkingCar(new Car());
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot, thirdParkingLot);
        ParkingBoy smartParkingBoy  = new SmartParkingBoy(parkingLots);

        Car car = new Car();
        Ticket expectedTicket = smartParkingBoy.parkingCar(car);

        assertThat(expectedTicket).isNotNull();
        assertThat(secondParkingLot.pickUpCar(expectedTicket)).isEqualTo(car);
    }

    // Given 聪明的停车小弟 管理2个停车场,两个停车场皆无剩余停车位 When 停车小弟 停车, Then 停车失败，提示：停车场已满
    @Test
    public void should_park_failed_and_alert_error_message_when_smart_parking_boy_park_given_manage_two_parking_lot_and_both_full() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        ParkingBoy smartParkingBoy  = new SmartParkingBoy(parkingLots);

        firstParkingLot.parkingCar(new Car());
        secondParkingLot.parkingCar(new Car());

        assertThat(assertThrows(ParkingLotFullException.class, () -> smartParkingBoy.parkingCar(new Car())).getLocalizedMessage()).isEqualTo("停车场已满");
    }
}
