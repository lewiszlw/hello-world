package lewiszlw.java8.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class LambdaDemo {
    public static void main(String[] args){

        IFlower flower = (String name) -> {if(name == "rose") {return "red";} else {return "unkonwn";}};
        System.out.println(flower.getColor("rose"));

        Thread t = new Thread(() -> System.out.println("hello"));
        t.start();

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        for(Integer i: list) {
            System.out.println(i);
        }
        list.forEach(new Consumer<Integer>() {
            @Override public void accept(Integer i) {
                System.out.println(i);
            }
        });
        list.forEach(i -> System.out.println(i));
        list.forEach(System.out :: println);
    }
}
