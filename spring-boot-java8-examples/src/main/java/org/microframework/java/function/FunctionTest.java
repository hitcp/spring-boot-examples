package org.microframework.java.function;

import java.util.function.Function;
import java.util.stream.Stream;

public class FunctionTest {

    public static void main(String[] args) {
        Function<Integer, Integer> A = i -> i * 2;
        Function<Integer, Integer> B = i -> i * i;

        System.out.println("A:" + A.apply(5)); // 10
        System.out.println("B:" + B.apply(5)); // 25
        System.out.println("D:" + A.apply(B.apply(5))); // 50 = 5*5*2
        System.out.println("C:" + B.compose(A).apply(5)); // 50 = 5*2*10 【变：B是在A的基础上修改的】
        System.out.println("E:" + B.andThen(A).apply(5)); // 50 = 5*5*2 【不变：B是在A的基础上修改的】 【等于D的执行顺序】

        Stream<String> stream = Stream.of("aaa", "bbbb", "ccccc");
        stream.map(function).forEach(System.out::println);
        System.out.println(function.apply("5"));

        // 里面的方法优先执行
        test(test2("1"));

    }


    static Function<String, Integer> function = new Function<String, Integer>() {
        @Override
        public Integer apply(String string) {
           // return string.length();
            return 1;
        }
    };

    public static void test(String s) {
        System.out.println("test11111111:" + s);
    }

    public static String test2(String s) {
        System.out.println("test22222222:" + s);
        return s;
    }

}
