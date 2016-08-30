package sample.hello;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class GodObject{
    public static final int DEFAULT_LIMIT_SIZE = 50;
    private Map<Integer, Long> fibonacci = new HashMap<>();
    private int limit = DEFAULT_LIMIT_SIZE;
    private String dummyString = "dummy";
    private Date dummyDate = new Date();

    public GodObject(int limit){
        this();
        this.limit = limit;
    }
    public GodObject(){
        fibonacci.put(1, 1L);
        fibonacci.put(2, 1L);
    }

    public void run(){
        fibonacci()
            .limit(limit)
            .forEach(value -> System.out.println(value));
        fizzbuzz()
            .limit(limit)
            .forEach(value -> System.out.println(value));
    }

    public LongStream fibonacci(){
        return LongStream.iterate(1L, i -> i + 1).
            map(index -> fibonacci(index));
    }

    private long fibonacci(long index){
        return fibonacci.computeIfAbsent(
            new Integer((int)index),
            i -> fibonacci(i - 1) + fibonacci(i - 2)
        );
    }

    public Stream<String> fizzbuzz(){
        return IntStream.iterate(1, i -> i + 1)
            .mapToObj(index -> {
                String value;
                if(index % 3 == 0 && index % 5 == 0){
                    value = "FizzBuzz";
                }
                else if(index % 3 == 0){
                    value = "Fizz";
                }
                else if(index % 5 == 0){
                    value = "Buzz";
                }
                else{
                    value = String.valueOf(index);
                }
                return value;
            });
    }

    public static void main(String[] args){
        GodObject object = new GodObject();
        object.run();
    }
}
