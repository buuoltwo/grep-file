package com.github.hcsp.io;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class FileSearch {
    // 找到第一个包含text的行的行号，行号从1开始计算。若没找到，则返回-1。
    // 如果指定的文件不存在或者无法被读取，抛出一个IllegalArgumentException。
    // 请不要让这个方法抛出checked exception
    public static int grep(File target, String text) {
        try {
            List<String> parsedLines = Files.readAllLines(target.toPath());
            System.out.println("parsedLines = " + parsedLines);
            for (int i = 0; i < parsedLines.size(); i++) {
                if (parsedLines.get(i).equals(text)) {
                    return i + 1;
                }
            }
            return -1;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
//        try (Stream<String> lines = Files.lines(target.toPath(), StandardCharsets.UTF_8)) {
//            final AtomicInteger lineNum = new AtomicInteger(1);
//            return lines
//                    .sequential() // 确保行号顺序
//                    .filter(line -> {
//                        boolean match = line.equals(text);
//                        if (!match) {
//                            lineNum.incrementAndGet();
//                        }
//                        return match;
//                    })
//                    .mapToInt(line -> lineNum.get())
//                    .findFirst()
//                    .orElse(-1);
//        } catch (IOException e) {
//            throw new IllegalArgumentException("读取文件失败: " + e.getMessage(), e);
//        }

    }

    public static void main(String[] args) {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        System.out.println("结果行号：" + grep(new File(projectDir, "log.txt"), "BBBB"));
    }
}
