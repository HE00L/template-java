package org.oobootcamp.core.service;

import org.junit.jupiter.api.Test;
import org.oobootcamp.core.entity.Car;
import org.oobootcamp.core.entity.ParkingLot;
import org.oobootcamp.core.entity.Ticket;

import static org.assertj.core.api.Assertions.assertThat;

public class ParkingLotServiceTest {

    // - Given 一个空位, When 普通停车用户自助停车, Then 普通停车用户 停车成功后拿到票
    // - Given 两个空位, When 普通停车用户自助停车, Then 普通停车用户 停车成功后拿到票
    @Test
    public void should_return_ticket_1_when_parking_car_given_parking_lot_has_one_space() {
        ParkingLotService parkingLotService = new ParkingLotService(new ParkingLot(1));
        Car car = new Car(1);
        assertThat(parkingLotService.parkCar(car)).isEqualTo(new Ticket(car.hashCode()));
    }

    @Test
    public void should_return_ticket_2_when_parking_car_given_parking_lot_has_two_space() {
        ParkingLotService parkingLotService = new ParkingLotService(new ParkingLot(2));
        Car car = new Car(1);
        assertThat(parkingLotService.parkCar(car)).isEqualTo(new Ticket(car.hashCode()));
    }

    // - Given 没有空位, When 普通停车用户自助停车, Then 普通停车用户 停车失败
    @Test
    public void should_park_failed_when_parking_car_given_parking_lot_no_space() {
        ParkingLotService parkingLotService = new ParkingLotService(new ParkingLot(1));
        Car car1 = new Car(1);
        parkingLotService.parkCar(car1);
        Car car2 = new Car(2);
        assertThat(parkingLotService.parkCar(car2)).isNull();
    }

    //- Given 有票，号码为一号, When 普通停车用户自助取车, Then 普通停车用户 取一号车位的车成功
    //- Given 有票，号码为二号, When 普通停车用户自助取车, Then 普通停车用户 取二号车位的车成功
    @Test
    public void should_pick_up_car_1_when_pick_up_car_given_ticket_1() {
        ParkingLotService parkingLotService = new ParkingLotService(new ParkingLot(1));
        Car car = new Car(1);
        Ticket ticket = parkingLotService.parkCar(car);
        assertThat(parkingLotService.pickUpCar(ticket)).isEqualTo(car);
        assertThat(car.id()).isEqualTo(ticket.id());
    }

    @Test
    public void should_pick_up_car_2_when_pick_up_car_given_ticket_2() {
        ParkingLotService parkingLotService = new ParkingLotService(new ParkingLot(1));
        Car car = new Car(2);
        Ticket ticket = parkingLotService.parkCar(car);
        assertThat(parkingLotService.pickUpCar(ticket)).isEqualTo(car);
        assertThat(car.id()).isEqualTo(ticket.id());
    }

    // - Given 无票, When 普通停车用户自助取车, Then 普通停车用户 取车失败
    @Test
    public void should_pick_up_failed_when_pick_up_car_given_null_ticket() {
        ParkingLotService parkingLotService = new ParkingLotService(new ParkingLot(1));
        assertThat(parkingLotService.pickUpCar(null)).isNull();
    }

}
