package com.zyx.io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * テキストファイルの読み込み
 */
public class TextFileInputNioPart2 {

    /**
     * @param args
     */
    public static void main(String[] args) {

        // 読み込みファイルの名前
        Path path = Paths.get("C:/dk", "input.txt");

        // Streamの生成
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {

            // 標準出力に出力
            lines.forEach(line -> System.out.println(line));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}