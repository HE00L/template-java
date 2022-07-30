package org.oobootcamp.core.domain;

import io.vavr.collection.List;
import org.junit.jupiter.api.Test;
import org.oobootcamp.core.exception.ParkCarException;
import org.oobootcamp.core.exception.PickUpCarException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingBoyTest {

    //    Given 停车小弟 管理2个停车场,两个停车场都有剩余停车位 When 停车小弟 停车, Then 停入第一个停车场，停车成功后拿到票
    //    Given 停车小弟 管理2个停车场,只有第二个停车场有剩余停车位 When 停车小弟 停车, Then 停入第二个停车场，停车成功后拿到票
    @Test
    public void should_return_ticket_when_parking_boy_park_given_manage_two_parking_lot_both_has_remainingCount() {
        Car car = new Car(1);

        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(2);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        assertThat(parkingBoy.parkingCar(car)).isEqualTo(new Ticket(car.id()));
        assertThat(firstParkingLot.hasTiles()).isEqualTo(1);
    }

    @Test
    public void should_return_ticket_when_parking_boy_park_given_manage_two_parking_lot_and_second_has_capacity() {
        Car car1 = new Car(1);
        Car car2 = new Car(2);

        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(2);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        firstParkingLot.parkingCar(car1);
        assertThat(parkingBoy.parkingCar(car2)).isEqualTo(new Ticket(car2.id()));
        assertThat(secondParkingLot.hasTiles()).isEqualTo(1);
    }
}
