# Story（Calarification Needed）

**作为** 普通停车用户

**我想要** 自助停车和取车

**从而** 节省我停车和取车的时间

# Tasking（Business-Oriented Tasking）

## AC：停车场有空位，普通停车用户可以自助停车成功

### Example

- Given 一个空位, When 普通停车用户自助停车, Then 普通停车用户 停车成功后拿到票
- Given 两个空位, When 普通停车用户自助停车, Then 普通停车用户 停车成功后拿到票

## AC：停车场没有空位，普通停车用户自助停车失败

### Example

- Given 没有空位, When 普通停车用户自助停车, Then 普通停车用户 停车失败

## AC：有票，普通停车用户 取车成功

### Example

- Given 有票，号码为一号, When 普通停车用户自助取车, Then 普通停车用户 取一号车位的车成功
- Given 有票，号码为二号, When 普通停车用户自助取车, Then 普通停车用户 取二号车位的车成功

## AC：无票，普通停车用户 取车失败

### Example

- Given 无票, When 普通停车用户自助取车, Then 普通停车用户 取车失败

# implementation

## Package

- entity
- service
- util*

## Class

- Entity
    - Car
        - `id` -> int
    - ParkingLot
        - `id` -> int
        - `remainingCount` -> int
        - `parkingSpaces<ParkingSpace>` -> Array
    - Ticket
        - `id` -> int
    - ParkingSpace
        - `id` -> int
        - `ticket_id`  -> int
        - `car`  -> car
- Service
    - ParkingLotService
        - `parking()`, return type is Ticket. -> input param is Car.
        - `pickUpCar()` ,return type is Car. -> input param is Ticket.