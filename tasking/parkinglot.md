# Story（Calarification Needed）

**作为** 普通停车用户

**我想要** 自助停车和取车

**从而** 节省我停车和取车的时间

# Tasking（Business-Oriented Tasking）

## AC：停车场有空位，普通停车用户可以自助停车成功

### Example

- Given 停车场有一个空位, When 普通停车用户自助停车, Then 普通停车用户 停车成功后拿到票
- Given 停车场有两个空位, When 普通停车用户自助停车, Then 普通停车用户 停车成功后拿到票

## AC：停车场没有空位，普通停车用户自助停车失败

### Example

- Given 停车场没有空位, When 普通停车用户自助停车, Then 普通停车用户 停车失败，提示停车失败

## AC：有票且票有效，普通停车用户 取车成功

### Example

- Given 用户有票，票是1号车停车获取的, When 普通停车用户自助取车, Then 普通停车用户 取一号车成功
- Given 用户有票，票是2号车停车获取的, When 普通停车用户自助取车, Then 普通停车用户 取二号车成功

## AC：有票但票无效，普通停车用户 取车失败

### Example

- Given 有票,票对应的车已经被取 When 普通停车用户自助取车, Then 普通停车用户 取车失败，提示票无效

## AC：无票，普通停车用户 取车失败

### Example

- Given 无票, When 普通停车用户自助取车, Then 普通停车用户 取车失败

# implementation

## Package

- domain
- exception
- util*

## Class

- Domain
    - Car
        - `id` -> int
    - ParkingLot
        - `remainingCount` -> int
        - `capacity`-> int
        - `parkingTiles<ParkingTile>` -> Array
    - Ticket
        - `id` -> int
    - ParkingTile
        - `ticket`  -> ticket
        - `car`  -> car
- Exception
    - Constant
    - PickUpCarException -> extends RuntimeException
    - ParkCarException -> extends RuntimeException