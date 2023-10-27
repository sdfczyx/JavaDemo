package com.zyx.io;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

public class Nio2Test {

    // 1行ずつ読み込む
    //1行ずつ読み込む場合はBufferedReaderクラスのreadline()メソッドを利用します
    @Test
    public void test1() {
        Path targetFile = Path.of("/tmp/test/sample.txt");
        // 処理終了後に自動的にBufferedReaderがclose()されるようにtry-with-resourcesを利用
        try (BufferedReader reader = Files.newBufferedReader(targetFile, StandardCharsets.UTF_8)) {
            // reader.readLine()で次の1行を読み込む（ファイルの末尾に到達した場合はnullを返す）
            for (String line; (line = reader.readLine()) != null;) {
                // 読み込んだ行に対して何らかの処理を行う
                // 今回はサンプルなので表示するだけ
                System.out.println(line);
            }
        } catch (IOException e) {
            // 業務コードでe.printStackTrace()を使うことはめったにありません。
            // 業務では各プロジェクトのルールに合わせて、例外の再スローやログの出力などを行ってください。
            e.printStackTrace();
        }
    }

    //全行に同じ加工処理をする
    //この場合はStream APIが便利です。Files.lines()メソッドでStream<String>を取得できます。

    @Test
    public void test2() {
        Path targetFile = Path.of("/tmp/test/sample.txt");
        try (Stream<String> stream = Files.lines(targetFile, StandardCharsets.UTF_8)) {
            List<String> list = stream.map(line -> "[" + line + "]")
                    .toList();
            for (String line : list) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //全行を保持するListを生成する
    //Files.readAllLines()を利用しま

    @Test
    public void test3() {
        Path targetFile = Path.of("/tmp/test/sample.txt");
        try {
            // 1要素＝1行であるListを生成する
            List<String> lines = Files.readAllLines(targetFile, StandardCharsets.UTF_8);
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //ファイルの全内容を1つの文字列として取得する
    //Files.readString()を利用しま

    @Test
    public void test4() {
        Path targetFile = Path.of("/tmp/test/sample.txt");
        try {
            String content = Files.readString(targetFile, StandardCharsets.UTF_8);
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //1行だけ書き込む（追記）
    //Files.writeString()を利用し

    @Test
    public void test5() {
        Path targetFile = Path.of("/tmp/test/sample.txt");
        try {
            Files.writeString(targetFile, "たちつてと", StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //1行だけ書き込む（上書き）
    //Files.writeString()メソッドの第4引数にStandardOpenOption.TRUNCATE_EXISTINGを指定すると、上書きになります。つまり、以前のファイルの内容は削除されます
    @Test
    public void test6() {
        Path targetFile = Path.of("/tmp/test/sample.txt");
        try {
            Files.writeString(targetFile, "たちつてと", StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //複数行を一気に書き込む（追記）
    //Files.write()を利用します。
    @Test
    public void test7() {
        Path targetFile = Path.of("/tmp/test/sample.txt");
        try {
            List<String> lines = List.of("たちつてと", "なにぬねの", "はひふへほ");
            Files.write(targetFile, lines, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //複数行を一気に書き込む（上書き）
    //第4引数にStandardOpenOption.TRUNCATE_EXISTINGを指定すると、上書きになり
    @Test
    public void test8() {
        Path targetFile = Path.of("/tmp/test/sample.txt");
        try {
            List<String> lines = List.of("たちつてと", "なにぬねの", "はひふへほ");
            Files.write(targetFile, lines, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
  

