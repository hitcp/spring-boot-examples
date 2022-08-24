package org.microframework.java.stream;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) throws IOException {
        Files.lines(Paths.get(""), Charset.defaultCharset());
//        Char.
        Stream.generate(Random::new).limit(6).forEach(System.out::println);
        System.out.println(Charset.defaultCharset().toString());
    }
}
