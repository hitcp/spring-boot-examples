package org.microframework.java.function;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * @author Shaoyu Liu
 * @date 2022/3/22 16:26
 */
public class FunctionChainTest {
    public static void main(String[] args) {
        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckerProcessing();
        p1.setSuccessor(p2);
        String result1 = p1.handle("Aren't labdas really sexy?!!");
        System.out.println(result1);

        // 先执行andThen前面再执行里面，compose则相反，看不懂就看下面的三个 process
        UnaryOperator<String> headerProcessing =
                (String text) -> "From Raoul, Mario and Alan: " + text;
        UnaryOperator<String> spellCheckerProcessing =
                (String text) -> text.replaceAll("labda", "lambda");
        UnaryOperator<String> spellCheckerProcessing222 =
                (String text) -> text + "11111111111111111";
        // 先执行 andThen前面的，然后执行apply调用的地方，
//        Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing).andThen(spellCheckerProcessing222);
//        String result2 = pipeline.apply("Aren't labdas really sexy?!!");
        String result2 =  headerProcessing.andThen(spellCheckerProcessing).andThen(spellCheckerProcessing222).apply("Aren't labdas really sexy?!!");
        System.out.println(result2);


        UnaryOperator<String> process1 = (String text) -> {
            System.out.println(" execute process1");
            return "process1";
        };
        UnaryOperator<String> process2 = (String text) -> {
            System.out.println(" execute process2");
            return "process2";
        };
        UnaryOperator<String> process3 = (String text) -> {
            System.out.println(" execute process3");
            return "process3";
        };
        String apply = process1.andThen(process2).andThen(process3).apply("apply");
        System.out.println(apply);
//        输出结果：
//        execute process1
//        execute process2
//        execute process3
//        process3

    }

    private abstract static class ProcessingObject<T> {
        protected ProcessingObject<T> successor;

        public void setSuccessor(ProcessingObject<T> successor) {
            this.successor = successor;
        }

        public T handle(T input) {
            T r = handleWork(input);
            if (successor != null) {
                return successor.handle(r);
            }
            return r;
        }

        /**
         * @param input
         * @return
         */
        protected abstract T handleWork(T input);
    }

    static private class HeaderTextProcessing
            extends ProcessingObject<String> {
        @Override
        public String handleWork(String text) {
            return "From Raoul, Mario and Alan: " + text;
        }
    }

    static private class SpellCheckerProcessing
            extends ProcessingObject<String> {
        @Override
        public String handleWork(String text) {
            return text.replaceAll("labda", "lambda");
        }
    }
}
