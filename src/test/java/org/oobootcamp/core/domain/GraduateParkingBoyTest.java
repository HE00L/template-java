package org.oobootcamp.core.domain;

import io.vavr.collection.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.oobootcamp.core.exception.ParkingLotFullException;
import org.oobootcamp.core.exception.InvalidTicketException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GraduateParkingBoyTest {

    //    Given 停车小弟 管理2个停车场,两个停车场都有剩余停车位 When 停车小弟 停车, Then 停入第一个停车场，停车成功后拿到票
    @Test

    public void should_return_ticket_when_parking_boy_park_given_manage_two_parking_lot_both_has_remainingCount() {
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(2);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        AbstractParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);

        Car car = new Car();
        Ticket expectedTicket = graduateParkingBoy.parkingCar(car);

        assertThat(expectedTicket).isNotNull();
        assertThat(graduateParkingBoy.pickUpCar(expectedTicket)).isEqualTo(car);
    }

    //    Given 停车小弟 管理2个停车场,只有第二个停车场有剩余停车位 When 停车小弟 停车, Then 停入第二个停车场，停车成功后拿到票
    @Test
    public void should_return_ticket_when_parking_boy_park_given_manage_two_parking_lot_and_second_has_remainingCount() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(2);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        AbstractParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);

        Car car1 = new Car();
        firstParkingLot.parkingCar(car1);

        Car car2 = new Car();
        Ticket expectedTicket = graduateParkingBoy.parkingCar(car2);
        assertThat(graduateParkingBoy.parkingCar(car2)).isNotNull();
        assertThat(secondParkingLot.pickUpCar(expectedTicket)).isEqualTo(car2);
    }

    //    Given 停车小弟 管理2个停车场,两个停车场皆无剩余停车位 When 停车小弟 停车, Then 停车失败，提示：停车失败
    @Test
    public void should_park_failed_and_alert_error_message_when_parking_boy_park_given_manage_two_parking_lot_and_both_full() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        AbstractParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);

        Car car1 = new Car();
        Car car2 = new Car();
        firstParkingLot.parkingCar(car1);
        secondParkingLot.parkingCar(car2);

        Car car3 = new Car();
        assertThat(assertThrows(ParkingLotFullException.class, () -> graduateParkingBoy.parkingCar(car3)).getLocalizedMessage()).isEqualTo("停车场已满");
    }

    //    - Given 停车小弟 管理2个停车场,小弟有一张有效票,票是第一个停车场的 When 停车小弟 取车, Then 取车成功
    @Test
    public void should_pick_up_car_when_parking_boy_pick_up_car_given_manage_two_parking_lot_and_have_one_first_lot_ticket() {
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(2);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        AbstractParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);

        Car car = new Car();
        Ticket firstParkingLotTicket = graduateParkingBoy.parkingCar(car);

        assertThat(graduateParkingBoy.pickUpCar(firstParkingLotTicket)).isEqualTo(car);
    }

    //    - Given 停车小弟 管理2个停车场,小弟有一张有效票,票是第二个停车场的 When 停车小弟 取车, Then 取车成功
    @Test
    public void should_pick_up_car_when_parking_boy_pick_up_car_given_manage_two_parking_lot_and_have_a_second_lot_ticket() {
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(2);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        AbstractParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);

        Car car = new Car();
        Ticket secondParkingLotTicket = secondParkingLot.parkingCar(car);

        assertThat(graduateParkingBoy.pickUpCar(secondParkingLotTicket)).isEqualTo(car);
    }

    //    - Given 停车小弟 管理2个停车场,小弟有一张第三方的停车场的票 When 停车小弟 取车, Then 取车失败，提示：票无效
    @Test
    public void should_pick_up_car_failed_when_parking_boy_pick_up_car_given_manage_two_parking_lot_and_ticket_is_not_belong_to_these_parking_lot() {
        Car car = new Car();
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(2);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);

        ParkingLot thirdParkingLot = new ParkingLot(1);
        Ticket thirdTicket = thirdParkingLot.parkingCar(car);
        AbstractParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);

        assertThat(assertThrows(InvalidTicketException.class, () -> graduateParkingBoy.pickUpCar(thirdTicket)).getLocalizedMessage()).isEqualTo("票无效");
    }

    //    - Given 停车小弟 管理2个停车场,小弟有一张已经使用的票 When 停车小弟 取车, Then 取车失败，提示：取车失败
    @Test
    public void should_pick_up_car_failed_when_parking_boy_pick_up_car_given_manage_two_parking_lot_and_have_a_used_ticket() {
        Car car = new Car();
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(2);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);

        AbstractParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        Ticket firstParkingLotTicket = graduateParkingBoy.parkingCar(car);

        assertThat(graduateParkingBoy.pickUpCar(firstParkingLotTicket)).isEqualTo(car);
        assertThat(assertThrows(InvalidTicketException.class, () -> graduateParkingBoy.pickUpCar(firstParkingLotTicket)).getLocalizedMessage()).isEqualTo("票无效");
    }

    //  - Given 停车小弟 管理2个停车场,小弟没有票 When 停车小弟 取车, Then 取车失败，提示：票无效
    @Test
    public void should_pick_up_car_failed_when_parking_boy_pick_up_car_given_manage_two_parking_lot_and_no_ticket() {
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(2);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        AbstractParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        assertThat(assertThrows(InvalidTicketException.class, () -> graduateParkingBoy.pickUpCar(null)).getLocalizedMessage()).isEqualTo("票无效");
    }

    @Nested
    class AC1{
        @Test
        void AC_Example(){
            assertTrue(true);
        }
    }

}
