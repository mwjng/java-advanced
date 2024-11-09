package io.file.copy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileCopyMainV3 {

    public static void main(String[] args) throws IOException {
        final long startTime = System.currentTimeMillis();

        final Path source = Path.of("temp/copy.dat");
        final Path target = Path.of("temp/copy_new.dat");
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

        final long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }
}
