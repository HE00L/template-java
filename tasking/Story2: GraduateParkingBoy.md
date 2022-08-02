# Story（Calarification Needed）

**作为** 银卡VIP用户
**我想要** 一个初入职场的停车小弟来帮我停车和取车
**从而** 节省我的时间

---

# Tasking（Business-Oriented Tasking）

### AC：停车小弟的停车场们有空位，小弟顺序停车成功

- Example
    - Given 停车小弟 管理2个停车场,两个停车场都有剩余停车位 When 停车小弟 停车, Then 停入第一个停车场，停车成功后拿到票
    - Given 停车小弟 管理2个停车场,只有第二个停车场有剩余停车位 When 停车小弟 停车, Then 停入第二个停车场，停车成功后拿到票

### AC：停车小弟的停车场们无空位，小弟停车失败

- Example
    - Given 停车小弟 管理2个停车场,两个停车场皆无剩余停车位 When 停车小弟 停车, Then 停车失败，提示：停车失败

### AC：停车小弟有一张有效Ticket，小弟取车成功

- Example
    - Given 停车小弟 管理2个停车场,小弟有一张有效票,票是第一个停车场的 When 停车小弟 取车, Then 取车成功
    - Given 停车小弟 管理2个停车场,小弟有一张有效票,票是第二个停车场的 When 停车小弟 取车, Then 取车成功

### AC：停车小弟有一张无效Ticket，小弟取车失败，提示：票无效

- Example
    - Given 停车小弟 管理2个停车场,小弟有一张已经使用的票 When 停车小弟 取车, Then 取车失败，提示：取车失败
    - Given 停车小弟 管理2个停车场,小弟有一张第三方的停车场的票 When 停车小弟 取车, Then 取车失败，提示：票无效

### AC：停车小弟没有Ticket，小弟取车失败，提示：票无效

- Example
    - Given 停车小弟 管理2个停车场,小弟没有票 When 停车小弟 取车, Then 取车失败，提示：票无效

# implementation

## Class

- Domain
    - ParkingBoy
        - parkingLots<ParkingLot> -> Array
        - method: `parkingCar(Car car) ` -> return Ticket
        - method: `pickUpCar(Ticket ticket) ` -> return Car