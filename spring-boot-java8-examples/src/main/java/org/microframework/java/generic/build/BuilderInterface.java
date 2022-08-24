package org.microframework.java.generic.build;

import java.lang.annotation.Annotation;

public interface BuilderInterface<T extends Annotation> {
    T build();
}
