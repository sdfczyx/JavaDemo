package com.zyx.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * バイナリーファイルを書き込む
 */
public class BinaryFileOutputNio {

    /**
     * @param args
     */
    public static void main(String[] args) {

        // 書き込みファイルの名前
        Path path = Paths.get("C:/dk", "output.dat");

        // バイナリデータ
        byte[] data = { (byte)0xe3, (byte)0x83, (byte)0x86, (byte)0xe3, (byte)0x82, (byte)0xad, (byte)0xe3, (byte)0x82, (byte)0xb9, (byte)0xe3, (byte)0x83, (byte)0x88, (byte)0xe3, (byte)0x83, (byte)0x95, (byte)0xe3, (byte)0x82, (byte)0xa1, (byte)0xe3, (byte)0x82, (byte)0xa4, (byte)0xe3, (byte)0x83, (byte)0xab, (byte)0xe5, (byte)0x87, (byte)0xba, (byte)0xe5, (byte)0x8a, (byte)0x9b, (byte)0x30, (byte)0x31, (byte)0x0d, (byte)0x0a, (byte)0xe3, (byte)0x83, (byte)0x86, (byte)0xe3, (byte)0x82, (byte)0xad, (byte)0xe3, (byte)0x82, (byte)0xb9, (byte)0xe3, (byte)0x83, (byte)0x88, (byte)0xe3, (byte)0x83, (byte)0x95, (byte)0xe3, (byte)0x82, (byte)0xa1, (byte)0xe3, (byte)0x82, (byte)0xa4, (byte)0xe3, (byte)0x83, (byte)0xab, (byte)0xe5, (byte)0x87, (byte)0xba, (byte)0xe5, (byte)0x8a, (byte)0x9b, (byte)0x30, (byte)0x32, (byte)0x0d, (byte)0x0a};

        try {
            // バイナリデータ一括書き込み
            Files.write(path, data);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}