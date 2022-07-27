package org.oobootcamp.warmup;

public class FizzBuzz {
    public static String sayCount(int count) {
        if (count % 3 == 0 && count % 5 == 0) {
            return "FizzBuzz";
        }
        if (count % 3 == 0) {
            return "Fizz";
        }
        if (count % 5 == 0) {
            return "Buzz";
        }
        return String.valueOf(count);
    }
}
