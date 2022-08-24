package org.microframework.java.operator;

/**
 * @author Shaoyu Liu
 * @date 2022/3/31 17:58
 */
public class T {
    public static void main(String[] args) {
        // 一、算术运算符  + - * / %

        int x = 2;
        int y = 3;
        // 1- = x++中的x为2，++x中为3
        // 答案：-2： (x++)会先运算所以运算的时候括号里面是2，然后后面会先2++、+1所以括号里是4
        System.out.println((x++)-(++x));

        // 二、赋值运算符  = += -= *= /= %=
        int a = 23;
        //a += 2; 相当于a = a + 2;
        System.out.println(a += 2);
        //a -= 10; 相当于a = a - 2;
        System.out.println(a -= 10);
        //a *= 2; 相当于a = a * 2;
        System.out.println(a *= 2);
        //a /= 2;  相当于a = a / 2;
        System.out.println(a /= 2);
        //a %= 12 相当于 a = a % 2;
        System.out.println(a %= 12);

        // 三、比较运算符 > < = <= >= == !=

        // 四、逻辑运算符（重点）
        boolean b1 = false;
        boolean b2 = false;
        boolean b3 = false;

        // 1.&:全真才真，一假则假（&3三个表达式都会执行，&&如果一个返回false就不执行后面的表达式）
        boolean r1 = b1 & b2 & b3;
        boolean r2 = b1 && b2 && b3;
        System.out.println(r1);
        // 2.|：一真就真，全假才假（|3三个表达式都会执行，||如果一个返回ture就不执行后面的表达式）
        boolean r3 = b1 | b2 | b3;
        boolean r4 = b1 || b2 || b3;
        System.out.println(r2);
    }
}
