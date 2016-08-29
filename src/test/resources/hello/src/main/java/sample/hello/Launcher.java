package sample.hello;

import java.util.Arrays;

public class Launcher{
    public Launcher(String[] args){
        greeting(new HelloWorld(), args);
    }

    private void greeting(HelloWorld hw, String[] args){
        if(args.length == 0){
            System.out.println(hw.hello());
            return;
        }
        Arrays.stream(args)
        .map(arg -> hw.hello(arg))
        .forEach(System.out::println);
    }

    public static void main(String[] args){
        new Launcher(args);
    }
}
