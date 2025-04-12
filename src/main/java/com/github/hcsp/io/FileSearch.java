package com.github.hcsp.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class FileSearch {
    // 找到第一个包含text的行的行号，行号从1开始计算。若没找到，则返回-1。
    // 如果指定的文件不存在或者无法被读取，抛出一个IllegalArgumentException。
    // 请不要让这个方法抛出checked exception
    public static int grep(File target, String text) {
//        if (!target.exists() || !target.canRead()) {
//            throw new IllegalArgumentException();
//        }
        AtomicInteger myIndex = new AtomicInteger(0);
        try {
            Stream<String> lines = null;
            lines = Files.lines(target.toPath());
//            lines.forEach(System.out::println);
            Optional<Integer> integerStream = lines
                    .map(line -> {
                        int curIndex = myIndex.incrementAndGet();
                        if (line.contains(text)) {
                            return myIndex.get();
                        }
                        return -1;
                    })
                    .filter(num -> num != -1)
                    .findFirst();
            return integerStream.orElse(-1);
        } catch (IOException e) {
//            throw new RuntimeException(e);
            throw new IllegalArgumentException();
        }

//        return 0;
    }

    public static void main(String[] args) {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        System.out.println("结果行号：" + grep(new File(projectDir, "log.txt"), "BBB"));
    }
}
