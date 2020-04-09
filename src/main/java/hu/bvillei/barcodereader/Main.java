package hu.bvillei.barcodereader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

class Main {
  public static void main(String[] args) {
       try (Stream<Path> walk = Files.walk(Paths.get("src/main/resources"))) {
            walk.filter(Files::isRegularFile)
                    .forEach(filePath -> {
                        BarcodeReader barcodeReader = new BarcodeReader(filePath);
                        try {
                            barcodeReader.collectEndpoints();
                            System.out.println(barcodeReader.toString());
                        } catch (IOException e) {
                            System.out.println("Failed to process file: " + filePath.getFileName());
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            System.out.println("Error happened during accessing resource files.");
            e.printStackTrace();
        }
  }
}
