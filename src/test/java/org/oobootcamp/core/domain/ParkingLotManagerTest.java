package org.oobootcamp.core.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.oobootcamp.core.exception.InvalidTicketException;
import org.oobootcamp.core.exception.ParkingLotFullException;

import io.vavr.collection.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ParkingLotManagerTest {

    // Given <经理管理1个停车场、1个毕业小弟、1个聪明小弟，经理停车场有1个空位，毕业小弟管理1个停车场，停车场有1个空位，聪明小弟有1个停车场，停车场有1个空位>, When <停车>, Then <停入停车经理的停车场> 取得tiecket
    @Test
    void should_return_ticket_when_manager_park_car_given_manager_manage_one_parking_lot_and_one_graduate_boy_and_one_smart_boy_and_all_of_them_has_remaining() {

        ParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(new ParkingLot(1)));
        ParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(new ParkingLot(1)));
        ParkingLot parkingLot = new ParkingLot(1);

        ParkingManager ParkingManager = new ParkingManager(
                List.of(parkingLot, graduateParkingBoy, smartParkingBoy));
        Car car = new Car();
        Ticket ticket = ParkingManager.parkingCar(car);
        assertThat(ticket).isNotNull();
        assertThat(parkingLot.pickUpCar(ticket)).isEqualTo(car);
    }
    // Given <经理管理1个停车场、1个毕业小弟、1个聪明小弟，经理停车场有0个空位，毕业小弟管理1个停车场，停车场有1个空位，聪明小弟有1个停车场，停车场有1个空位>, When <停车>, Then <停入毕业小弟的停车场> 取得tiecket
    @Test
    void should_return_ticket_when_manager_park_car_given_manager_manage_one_parking_lot_and_one_graduate_boy_and_one_smart_boy_and_manager_parking_lot_no_remaining() {

        ParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(new ParkingLot(1)));
        ParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(new ParkingLot(1)));
        ParkingLot parkingLot = new ParkingLot(0);

        ParkingManager ParkingManager = new ParkingManager(
                List.of(parkingLot, graduateParkingBoy, smartParkingBoy));
        Car car = new Car();
        Ticket ticket = ParkingManager.parkingCar(car);
        assertThat(ticket).isNotNull();
        assertThat(graduateParkingBoy.pickUpCar(ticket)).isEqualTo(car);
    }

    // Given <经理管理1个停车场、1个毕业小弟、1个聪明小弟，经理停车场有0个空位，毕业小弟管理1个停车场，停车场有0个空位，聪明小弟有1个停车场，停车场有1个空位>, When <停车>, Then <停入聪明小弟的停车场> 取得tiecket
    @Test
    void should_return_ticket_when_manager_park_car_given_manager_manage_one_parking_lot_and_one_graduate_boy_and_one_smart_boy_and_manager_parking_lot_no_remaining_and_graduate_boy_no_remaining() {

        ParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(new ParkingLot(1)));
        ParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(new ParkingLot(0)));
        ParkingLot parkingLot = new ParkingLot(0);

        ParkingManager ParkingManager = new ParkingManager(
                List.of(parkingLot, graduateParkingBoy, smartParkingBoy));
        Car car = new Car();
        Ticket ticket = ParkingManager.parkingCar(car);
        assertThat(ticket).isNotNull();
        assertThat(smartParkingBoy.pickUpCar(ticket)).isEqualTo(car);
    }

    // Given <经理管理1个毕业小弟、1个停车场、1个聪明小弟，毕业小弟管理1个停车场，停车场有1个空位，经理停车场有1个空位，聪明小弟有1个停车场，停车场有1个空位>, When <停车>, Then <停入毕业小弟的停车场> 取得tiecket
    @Test
    void should_return_ticket_when_manager_park_car_given_manager_manage_one_graduate_boy_and_one_parking_lot_and_one_smart_boy_and_all_of_them_has_remaining() {

        ParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(new ParkingLot(1)));
        ParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(new ParkingLot(1)));
        ParkingLot parkingLot = new ParkingLot(1);

        ParkingManager ParkingManager = new ParkingManager(
                List.of(graduateParkingBoy, parkingLot, smartParkingBoy));
        Car car = new Car();
        Ticket ticket = ParkingManager.parkingCar(car);
        assertThat(ticket).isNotNull();
        assertThat(graduateParkingBoy.pickUpCar(ticket)).isEqualTo(car);
    }

    // Given <经理管理1个聪明小弟、1个毕业小弟、1个停车场，毕业小弟管理1个停车场，停车场有1个空位，经理停车场有1个空位，聪明小弟有1个停车场，停车场有1个空位>, When <停车>, Then <停入聰明小弟的停车场> 取得tiecket
    @Test
    void should_return_ticket_when_manager_park_car_given_manager_manage_one_smart_boy_and_one_parking_lot_and_one_graduate_boy_and_smart_boy_is_full() {

        ParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(new ParkingLot(1)));
        ParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(new ParkingLot(1)));
        ParkingLot parkingLot = new ParkingLot(1);
        smartParkingBoy.parkingCar(new Car());
        ParkingManager ParkingManager = new ParkingManager(
                List.of(smartParkingBoy, graduateParkingBoy, parkingLot));
        Car car = new Car();
        Ticket ticket = ParkingManager.parkingCar(car);
        assertThat(ticket).isNotNull();
        assertThat(graduateParkingBoy.pickUpCar(ticket)).isEqualTo(car);
    }

    // Given <经理管理1个毕业小弟、1个停车场、1个聪明小弟，毕业小弟管理1个停车场，停车场有0个空位，经理停车场有0个空位，聪明小弟有1个停车场，停车场有0个空位>, When <停车>, Then <停车失败> 提示停车失败
    @Test
    void should_park_failed_when_manager_park_car_given_manager_manage_one_graduate_boy_and_one_parking_lot_and_one_smart_boy_and_all_of_them_full() {

        ParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(new ParkingLot(1)));
        ParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(new ParkingLot(1)));
        ParkingLot parkingLot = new ParkingLot(1);
        smartParkingBoy.parkingCar(new Car());
        graduateParkingBoy.parkingCar(new Car());
        parkingLot.parkingCar(new Car());
        ParkingManager ParkingManager = new ParkingManager(
                List.of(graduateParkingBoy, parkingLot, smartParkingBoy));

        assertThat(assertThrows(ParkingLotFullException.class, () -> ParkingManager.parkingCar(new Car())).getLocalizedMessage()).isEqualTo("停车场已满");
    }

    // Given 停车经理管理1个毕业小弟、1个停车场、1个聪明小弟，毕业小弟管理1个停车场，聪明小弟有1个停车场, 停车经理有一张有效票, 票是毕业小弟停车场的 When 停车经理 取车, Then 取车成功
    @Test
    void should_return_car_when_manager_pick_car_given_manager_manage_one_graduate_boy_and_one_parking_lot_and_one_smart_boy_and_manager_have_one_valid_ticket_and_this_ticket_belong_to_graduate_boy() {

        ParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(new ParkingLot(1)));
        ParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(new ParkingLot(1)));
        ParkingLot parkingLot = new ParkingLot(1);

        Car car = new Car();
        Ticket ticket = graduateParkingBoy.parkingCar(car);
        ParkingManager ParkingManager = new ParkingManager(
                List.of(graduateParkingBoy, parkingLot, smartParkingBoy));

        assertThat(ParkingManager.pickUpCar(ticket)).isEqualTo(car);
    }

    // Given 停车经理管理1个毕业小弟、1个停车场、1个聪明小弟，毕业小弟管理1个停车场，聪明小弟有1个停车场, 停车经理有一张有效票, 票是停车经理停车场的 When 停车经理 取车, Then 取车成功
    @Test
    void should_return_car_when_manager_pick_car_given_manager_manage_one_graduate_boy_and_one_parking_lot_and_one_smart_boy_and_manager_have_one_valid_ticket_and_this_ticket_belong_to_manager() {

        ParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(new ParkingLot(1)));
        ParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(new ParkingLot(1)));
        ParkingLot parkingLot = new ParkingLot(1);

        Car car = new Car();
        Ticket ticket = parkingLot.parkingCar(car);
        ParkingManager ParkingManager = new ParkingManager(
                List.of(graduateParkingBoy, parkingLot, smartParkingBoy));

        assertThat(ParkingManager.pickUpCar(ticket)).isEqualTo(car);
    }

    // Given 停车经理管理1个毕业小弟、1个停车场、1个聪明小弟，毕业小弟管理1个停车场，聪明小弟有1个停车场, 停车经理有一张有效票, 票是聪明小弟停车场的 When 停车经理 取车, Then 取车成功
    @Test
    void should_return_car_when_manager_pick_car_given_manager_manage_one_graduate_boy_and_one_parking_lot_and_one_smart_boy_and_manager_have_one_valid_ticket_and_this_ticket_belong_to_smart_boy() {

        ParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(new ParkingLot(1)));
        ParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(new ParkingLot(1)));
        ParkingLot parkingLot = new ParkingLot(1);

        Car car = new Car();
        Ticket ticket = smartParkingBoy.parkingCar(car);
        ParkingManager ParkingManager = new ParkingManager(
                List.of(graduateParkingBoy, parkingLot, smartParkingBoy));

        assertThat(ParkingManager.pickUpCar(ticket)).isEqualTo(car);
    }

    // Given 停车经理管理1个毕业小弟、1个停车场、1个聪明小弟，毕业小弟管理1个停车场，聪明小弟有1个停车场, 停车经理有一张已经使用的票 When 停车经理 取车, Then 取车失败，提示：票无效
    @Test
    void should_pick_failed_when_manager_pick_car_given_manager_manage_one_graduate_boy_and_one_parking_lot_and_one_smart_boy_and_manager_have_one_used_ticket() {

        ParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(new ParkingLot(1)));
        ParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(new ParkingLot(1)));
        ParkingLot parkingLot = new ParkingLot(1);

        Car car = new Car();
        Ticket ticket = smartParkingBoy.parkingCar(car);
        ParkingManager ParkingManager = new ParkingManager(
                List.of(graduateParkingBoy, parkingLot, smartParkingBoy));

        assertThat(ParkingManager.pickUpCar(ticket)).isEqualTo(car);
        assertThat(assertThrows(InvalidTicketException.class, () -> ParkingManager.pickUpCar(ticket)).getLocalizedMessage()).isEqualTo("票无效");
    }

    // Given 停车经理管理1个毕业小弟、1个停车场、1个聪明小弟，毕业小弟管理1个停车场，聪明小弟有1个停车场,  停车经理有一张第三方的停车场的票 When 停车经理 取车, Then 取车失败，提示：票无效
    @Test
    void should_pick_failed_when_manager_pick_car_given_manager_manage_one_graduate_boy_and_one_parking_lot_and_one_smart_boy_and_manager_have_one_third_ticket() {

        ParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(new ParkingLot(1)));
        ParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(new ParkingLot(1)));
        ParkingLot parkingLot = new ParkingLot(1);

        Car car = new Car();
        smartParkingBoy.parkingCar(car);
        ParkingManager ParkingManager = new ParkingManager(
                List.of(graduateParkingBoy, parkingLot, smartParkingBoy));
        ParkingLot thirdParkingLot = new ParkingLot(1);
        Ticket thirdTicket = thirdParkingLot.parkingCar(new Car());
        assertThat(assertThrows(InvalidTicketException.class, () -> ParkingManager.pickUpCar(thirdTicket)).getLocalizedMessage()).isEqualTo("票无效");
    }

    // Given Given 停车经理管理1个毕业小弟、1个停车场、1个聪明小弟，毕业小弟管理1个停车场，聪明小弟有1个停车场, 停车经理没有票 When 停车经理取车, Then 取车失败，提示：票无效
    @Test
    void should_pick_failed_when_manager_pick_car_given_manager_manage_one_graduate_boy_and_one_parking_lot_and_one_smart_boy_and_manager_no_ticket() {

        ParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(new ParkingLot(1)));
        ParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(new ParkingLot(1)));
        ParkingLot parkingLot = new ParkingLot(1);

        Car car = new Car();
        smartParkingBoy.parkingCar(car);
        ParkingManager ParkingManager = new ParkingManager(
                List.of(graduateParkingBoy, parkingLot, smartParkingBoy));
        assertThat(assertThrows(InvalidTicketException.class, () -> ParkingManager.pickUpCar(null)).getLocalizedMessage()).isEqualTo("票无效");
    }
}
