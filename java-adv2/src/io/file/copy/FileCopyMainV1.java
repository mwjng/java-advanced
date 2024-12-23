package io.file.copy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyMainV1 {

    public static void main(String[] args) throws IOException {
        final long startTime = System.currentTimeMillis();

        final FileInputStream fis = new FileInputStream("temp/copy.dat");
        final FileOutputStream fos = new FileOutputStream("temp/copy_new.dat");

        final byte[] bytes = fis.readAllBytes();
        fos.write(bytes);

        fis.close();
        fos.close();

        final long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }
}
