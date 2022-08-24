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


        UnaryOperator<String> headerProcessing =
                (String text) -> "From Raoul, Mario and Alan: " + text;
        UnaryOperator<String> spellCheckerProcessing =
                (String text) -> text.replaceAll("labda", "lambda");
        UnaryOperator<String> spellCheckerProcessing222 =
                (String text) -> text + "11111111111111111";
        Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing).andThen(spellCheckerProcessing222);
        String result2 = pipeline.apply("Aren't labdas really sexy?!!");

        System.out.println(result2);
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
