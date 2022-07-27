package org.oobootcamp.warmup;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FizzBuzzTest {

//    - 当数字是三的倍数且不是五的倍数时
//  - Example:
//            - Given 3 When SayCount Then SayFizz
//    - Given 6 When SayCount Then SayFizz
//    - Given 9 When SayCount Then SayFizz

    @Test
    public void should_say_fizz_when_say_count_given_3() {
        assertThat(FizzBuzz.sayCount(3)).isEqualTo("Fizz");
    }

    @Test
    public void should_say_fizz_when_say_count_given_6() {
        assertThat(FizzBuzz.sayCount(6)).isEqualTo("Fizz");
    }

    @Test
    public void should_say_fizz_when_say_count_given_9() {
        assertThat(FizzBuzz.sayCount(9)).isEqualTo("Fizz");
    }

//    - 当数字是五的倍数且不是三的倍数时
//  - Example
//    - Given 5 When SayCount Then SayBuzz
//    - Given 10 When SayCount Then SayBuzz
//    - Given 20 When SayCount Then SayBuzz

    @Test
    public void should_say_buzz_when_say_count_given_5() {
        assertThat(FizzBuzz.sayCount(5)).isEqualTo("Buzz");
    }

    @Test
    public void should_say_buzz_when_say_count_given_10() {
        assertThat(FizzBuzz.sayCount(10)).isEqualTo("Buzz");
    }

    @Test
    public void should_say_buzz_when_say_count_given_20() {
        assertThat(FizzBuzz.sayCount(20)).isEqualTo("Buzz");
    }

    //    当数字是五和三的倍数时
//  - Example
//    - Given 15 When SayCount Then SayFizzBuzz
//    - Given 30 When SayCount Then SayFizzBuzz
//    - Given 45 When SayCount Then SayFizzBuzz
    @Test
    public void should_say_fizzbuzz_when_say_count_given_15() {
        assertThat(FizzBuzz.sayCount(15)).isEqualTo("FizzBuzz");
    }

    @Test
    public void should_say_fizzbuzz_when_say_count_given_30() {
        assertThat(FizzBuzz.sayCount(30)).isEqualTo("FizzBuzz");
    }

    @Test
    public void should_say_fizzbuzz_when_say_count_given_45() {
        assertThat(FizzBuzz.sayCount(45)).isEqualTo("FizzBuzz");
    }

//    - 既不是三也不是五的倍数时
//  - Example
//    - Given 1 When SayCount Then 1
//            - Given 2 When SayCount Then 2

    @Test
    public void should_say_1_when_say_count_given_1() {
        assertThat(FizzBuzz.sayCount(1)).isEqualTo("1");
    }

    @Test
    public void should_say_2_when_say_count_given_2() {
        assertThat(FizzBuzz.sayCount(2)).isEqualTo("2");
    }
}
