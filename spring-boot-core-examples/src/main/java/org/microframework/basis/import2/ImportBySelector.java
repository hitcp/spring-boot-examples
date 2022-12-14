package org.microframework.basis.import2;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author Shaoyu Liu
 * @date 2022-08-23
 */
public class ImportBySelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        String[] mataData = new String[3];
        // 指定所在类的全路径
        mataData[0] = "org.microframework.annotation.import2.ImportByClass";
        mataData[1] = "org.microframework.annotation.import2.ImportBySelector";
        mataData[2] = "org.microframework.annotation.import2.ImportByRegistrar";
        return mataData;
    }

    static {
        System.out.println("ImportBySelector init...");
    }

    public void foo() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getClassName());
    }
}
