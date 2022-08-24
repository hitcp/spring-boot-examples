package org.microframework.java.function;

/**
 * @author Shaoyu Liu
 * @date 2021/12/23 11:27
 **/
public class BranchHandleUtil {

    public static BranchHandle isTrueOrFalse(boolean result) {
        return (trueHandle, falseHandle) -> {
            if (result) {
                trueHandle.run();
            } else {
                falseHandle.run();
            }
        };
    }

    private BranchHandleUtil() {
    }

    // 使用用例
    public static void main(String[] args) {
        boolean b = false;
        BranchHandleUtil.isTrueOrFalse(b).trueOrFalseHandle(
                // true do something
                () -> System.out.println(true),
                // false do something
                () -> System.out.println(false));
    }
}
