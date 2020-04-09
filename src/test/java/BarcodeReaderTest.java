import hu.bvillei.barcodereader.BarcodeReader;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BarcodeReaderTest {
    @Test
    public void testOneHorizontalLine() throws IOException {
        Path filePath = Paths.get("src/test/resources/one_horizontal_line.txt");
        BarcodeReader barcodeReader = new BarcodeReader(filePath);
        barcodeReader.collectEndpoints();
        Assert.assertThat(barcodeReader.getEndpointList().size(), CoreMatchers.equalTo(2));
        Assert.assertEquals("one_horizontal_line.txt (5,7) (58,7)", barcodeReader.toString());
    }

    @Test
    public void testOneVerticalLine() throws IOException {
        Path filePath = Paths.get("src/test/resources/one_vertical_line.txt");
        BarcodeReader barcodeReader = new BarcodeReader(filePath);
        barcodeReader.collectEndpoints();
        Assert.assertThat(barcodeReader.getEndpointList().size(), CoreMatchers.equalTo(2));
        Assert.assertEquals("one_vertical_line.txt (30,27) (30,37)", barcodeReader.toString());
    }

    @Test
    public void testOneDiagonalLine() throws IOException {
        Path filePath = Paths.get("src/test/resources/one_diagonal_line.txt");
        BarcodeReader barcodeReader = new BarcodeReader(filePath);
        barcodeReader.collectEndpoints();
        Assert.assertThat(barcodeReader.getEndpointList().size(), CoreMatchers.equalTo(2));
        Assert.assertEquals("one_diagonal_line.txt (3,1) (0,4)", barcodeReader.toString());
    }

    @Test
    public void testOneAntiDiagonalLine() throws IOException {
        Path filePath = Paths.get("src/test/resources/one_anti_diagonal_line.txt");
        BarcodeReader barcodeReader = new BarcodeReader(filePath);
        barcodeReader.collectEndpoints();
        Assert.assertThat(barcodeReader.getEndpointList().size(), CoreMatchers.equalTo(2));
        Assert.assertEquals("one_anti_diagonal_line.txt (15,16) (39,40)", barcodeReader.toString());
    }

    @Test
    public void testOneStaircaseLine() throws IOException {
        Path filePath = Paths.get("src/test/resources/one_staircase_line.txt");
        BarcodeReader barcodeReader = new BarcodeReader(filePath);
        barcodeReader.collectEndpoints();
        Assert.assertThat(barcodeReader.getEndpointList().size(), CoreMatchers.equalTo(2));
        Assert.assertEquals("one_staircase_line.txt (41,0) (0,7)", barcodeReader.toString());
    }

    @Test
    public void testOnePoint() throws IOException {
        Path filePath = Paths.get("src/test/resources/one_point.txt");
        BarcodeReader barcodeReader = new BarcodeReader(filePath);
        barcodeReader.collectEndpoints();
        Assert.assertThat(barcodeReader.getEndpointList().size(), CoreMatchers.equalTo(0));
        Assert.assertEquals("one_point.txt", barcodeReader.toString());
    }

    @Test
    public void testZeroBlackPixel() throws IOException {
        Path filePath = Paths.get("src/test/resources/zero_black_pixel.txt");
        BarcodeReader barcodeReader = new BarcodeReader(filePath);
        barcodeReader.collectEndpoints();
        Assert.assertThat(barcodeReader.getEndpointList().size(), CoreMatchers.equalTo(0));
        Assert.assertEquals("zero_black_pixel.txt", barcodeReader.toString());
    }

    @Test
    public void testTwoShortLinesInOneRow() throws IOException {
        Path filePath = Paths.get("src/test/resources/two_short_lines_in_one_row.txt");
        BarcodeReader barcodeReader = new BarcodeReader(filePath);
        barcodeReader.collectEndpoints();
        Assert.assertThat(barcodeReader.getEndpointList().size(), CoreMatchers.equalTo(4));
        Assert.assertEquals("two_short_lines_in_one_row.txt (4,6) (5,6) (16,6) (17,6)", barcodeReader.toString());
    }

    @Test
    public void testTwoLines() throws IOException {
        Path filePath = Paths.get("src/test/resources/two_lines.txt");
        BarcodeReader barcodeReader = new BarcodeReader(filePath);
        barcodeReader.collectEndpoints();
        Assert.assertThat(barcodeReader.getEndpointList().size(), CoreMatchers.equalTo(4));
        Assert.assertEquals("two_lines.txt (6,5) (17,5) (14,9) (10,13)", barcodeReader.toString());
    }
}
