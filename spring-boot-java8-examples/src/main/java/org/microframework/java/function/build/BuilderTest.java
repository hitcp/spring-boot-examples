package org.microframework.java.function.build;

import java.util.Date;

public class BuilderTest {
    public static void main(String[] args) {
        Student student = Builder.of(Student::new)
                .with(Student::setId, "1111")
                .with(Student::setId, "1111")
                .with(Student::setName, "张三")
                .with(Student::setSex, "男")
                .with(Student::setAge, 23)
                .with(Student::setBirthday, new Date())
                .build();
        System.out.println(student);
    }
}
