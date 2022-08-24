package org.microframework.java.function;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;

/**
 * 只提供一个返回值
 */
public class SupplierTest {
    public static void main(String[] args) {
        IntSupplier intSupplier = new IntSupplier() {
            @Override
            public int getAsInt() {
                return 0;
            }
        };

        LongSupplier longSupplier = new LongSupplier() {
            @Override
            public long getAsLong() {
                return 0;
            }
        };

        DoubleSupplier doubleSupplier = new DoubleSupplier() {
            @Override
            public double getAsDouble() {
                return 0;
            }
        };

        BooleanSupplier booleanSupplier = new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return false;
            }
        };

        System.out.println(intSupplier.getAsInt());
        System.out.println(doubleSupplier.getAsDouble());
        System.out.println(longSupplier.getAsLong());
        System.out.println(booleanSupplier.getAsBoolean());
    }
}
