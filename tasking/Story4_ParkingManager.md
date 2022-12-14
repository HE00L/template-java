# Story（Calarification Needed）

**作为** 钻石卡VIP用户
**我想要** 委托经理来帮我停车和取车
**从而** 节省我的时间，提升体验

---

# Tasking（Business-Oriented Tasking）
### AC1：停车经理管理多个毕业小弟、聪明小弟、停车场，按停车经理管理的停车场、毕业小弟、聪明小弟的顺序停车，经理或小弟管理的停车场有空车位，开始停车，停车成功，取得tiecket

- Example
 -  Given <经理管理1个停车场、1个毕业小弟、1个聪明小弟，经理停车场有1个空位，毕业小弟管理1个停车场，停车场有1个空位，聪明小弟有1个停车场，停车场有1个空位>, When <停车>, Then <停入停车经理的停车场> 取得tiecket
   
 -  Given <经理管理1个停车场、1个毕业小弟、1个聪明小弟，经理停车场有0个空位，毕业小弟管理1个停车场，停车场有1个空位，聪明小弟有1个停车场，停车场有1个空位>, When <停车>, Then <停入毕业小弟的停车场> 取得tiecket
   
 -  Given <经理管理1个停车场、1个毕业小弟、1个聪明小弟，经理停车场有0个空位，毕业小弟管理1个停车场，停车场有0个空位，聪明小弟有1个停车场，停车场有1个空位>, When <停车>, Then <停入聪明小弟的停车场> 取得tiecket
  
 -  Given <经理管理1个毕业小弟、1个停车场、1个聪明小弟，毕业小弟管理1个停车场，停车场有1个空位，经理停车场有1个空位，聪明小弟有1个停车场，停车场有1个空位>, When <停车>, Then <停入毕业小弟的停车场> 取得tiecket


### AC2：停车经理管理多个毕业小弟、聪明小弟、停车场，按停车经理管理的停车场、毕业小弟、聪明小弟的顺序停车，经理和小弟管理的停车场均已满，开始停车，停车失败，提示停车失败

- Example

 -  Given <经理管理1个毕业小弟、1个停车场、1个聪明小弟，毕业小弟管理1个停车场，停车场有0个空位，经理停车场有0个空位，聪明小弟有1个停车场，停车场有0个空位>, When <停车>, Then <停车失败> 提示停车失败


### AC3：停车经理管理多个毕业小弟、聪明小弟、停车场，停车经理有一张有效Ticket，停车经理取车成功

- Example

    - Given 停车经理管理1个毕业小弟、1个停车场、1个聪明小弟，毕业小弟管理1个停车场，聪明小弟有1个停车场, 停车经理有一张有效票, 票是毕业小弟停车场的 When 停车经理 取车, Then 取车成功

    - Given 停车经理管理1个毕业小弟、1个停车场、1个聪明小弟，毕业小弟管理1个停车场，聪明小弟有1个停车场, 停车经理有一张有效票, 票是停车经理停车场的 When 停车经理 取车, Then 取车成功

    - Given 停车经理管理1个毕业小弟、1个停车场、1个聪明小弟，毕业小弟管理1个停车场，聪明小弟有1个停车场, 停车经理有一张有效票, 票是聪明小弟停车场的 When 停车经理 取车, Then 取车成功

### AC4：停车经理管理多个毕业小弟、聪明小弟、停车场，停车经理有一张无效Ticket，停车经理取车失败，提示：票无效

- Example

    - Given 停车经理管理1个毕业小弟、1个停车场、1个聪明小弟，毕业小弟管理1个停车场，聪明小弟有1个停车场, 停车经理有一张已经使用的票 When 停车经理 取车, Then 取车失败，提示：票无效

    - Given 停车经理管理1个毕业小弟、1个停车场、1个聪明小弟，毕业小弟管理1个停车场，聪明小弟有1个停车场,  停车经理有一张第三方的停车场的票 When 停车经理 取车, Then 取车失败，提示：票无效

### AC5：停车经理没有Ticket，停车经理取车失败，提示：票无效

- Example

    - Given Given 停车经理管理1个毕业小弟、1个停车场、1个聪明小弟，毕业小弟管理1个停车场，聪明小弟有1个停车场, 停车经理没有票 When 停车经理取车, Then 取车失败，提示：票无效