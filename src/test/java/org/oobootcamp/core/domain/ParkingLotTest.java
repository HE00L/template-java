package org.oobootcamp.core.domain;

import org.junit.jupiter.api.Test;
import org.oobootcamp.core.exception.ParkingLotFullException;
import org.oobootcamp.core.exception.InvalidTicketException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingLotTest {

    //- Given 停车场有一个空位, When 普通停车用户自助停车, Then 普通停车用户 停车成功后拿到票
    //- Given 停车场有两个空位, When 普通停车用户自助停车, Then 普通停车用户 停车成功后拿到票
    @Test
    public void should_return_ticket_when_parking_car_given_parking_lot_has_one_space() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        assertThat(parkingLot.parkingCar(car)).isNotNull();
    }

    @Test
    public void should_return_ticket_when_parking_car_given_parking_lot_has_two_space() {
        ParkingLot parkingLot = new ParkingLot(2);
        Car car = new Car();
        assertThat(parkingLot.parkingCar(car)).isNotNull();
    }

    //- Given 停车场没有空位, When 普通停车用户自助停车, Then 普通停车用户 停车失败，提示停车失败
    @Test
    public void should_park_failed_when_parking_car_given_parking_lot_no_space() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car1 = new Car();
        parkingLot.parkingCar(car1);
        Car car2 = new Car();
        assertThat(assertThrows(ParkingLotFullException.class, () -> parkingLot.parkingCar(car2)).getLocalizedMessage()).isEqualTo("停车场已满");
    }

    //- Given 有票，号码为一号, When 普通停车用户自助取车, Then 普通停车用户 取一号车位的车成功
    //- Given 有票，号码为二号, When 普通停车用户自助取车, Then 普通停车用户 取二号车位的车成功
    @Test
    public void should_pick_up_car_1_when_pick_up_car_given_ticket_1() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        Ticket ticket = parkingLot.parkingCar(car);
        assertThat(parkingLot.pickUpCar(ticket)).isEqualTo(car);
    }

    @Test
    public void should_pick_up_car_2_when_pick_up_car_given_ticket_2() {
        ParkingLot parkingLot = new ParkingLot(2);
        Car car = new Car();
        Ticket ticket = parkingLot.parkingCar(car);
        assertThat(parkingLot.pickUpCar(ticket)).isEqualTo(car);
    }

    //- Given 有票,票对应的车已经被取 When 普通停车用户自助取车, Then 普通停车用户 取车失败，提示票无效
    @Test
    public void should_pick_up_failed_when_pick_up_car_given_used_ticket() {
        ParkingLot parkingLot = new ParkingLot(2);
        Car car = new Car();
        Car car2 = new Car();
        Ticket ticket = parkingLot.parkingCar(car);
        parkingLot.parkingCar(car2);
        assertThat(parkingLot.pickUpCar(ticket)).isEqualTo(car);
        assertThat(assertThrows(InvalidTicketException.class, () -> parkingLot.pickUpCar(ticket)).getLocalizedMessage()).isEqualTo("票无效");
    }

    // - Given 无票, When 普通停车用户自助取车, Then 普通停车用户 取车失败
    @Test
    public void should_pick_up_failed_when_pick_up_car_given_null_ticket() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        parkingLot.parkingCar(car);
        assertThat(assertThrows(InvalidTicketException.class, () -> parkingLot.pickUpCar(null)).getLocalizedMessage()).isEqualTo("票无效");
    }

}
