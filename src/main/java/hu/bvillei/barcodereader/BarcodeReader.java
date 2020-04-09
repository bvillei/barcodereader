package hu.bvillei.barcodereader;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.*;

public class BarcodeReader {
    private static final EnumSet<Direction> DIRECTIONS =
            EnumSet.of(Direction.HORIZONTAL, Direction.VERTICAL, Direction.DIAGONAL, Direction.ANTIDIAGONAL);
    private Path path;
    private List<Point> endpointList = new ArrayList<>();

    public Path getPath() {
        return path;
    }

    public List<Point> getEndpointList() {
        return endpointList;
    }

    public BarcodeReader(Path path) {
        this.path = path;
    }

    public void collectEndpoints() throws IOException {
        List<Point> pixels = new ArrayList<>(getBlackPoints());

        for (int i = 0; i < pixels.size(); i++) {
            for (Direction direction : DIRECTIONS)
                if (pixels.contains(getNextPoint(pixels.get(i), direction)))
                    addEndpoint(pixels, pixels.get(i), direction);
        }
        removeMiddleEndpoints();
    }

    private List<Point> getBlackPoints() throws IOException {
        List<Point> pixelSet = new ArrayList<>();
        try (Scanner sc = new Scanner(path)) {
            int y = 0;
            while (sc.hasNextLine()) {
                char[] line = sc.nextLine().trim().toCharArray();
                for (int x = 0; x < line.length; x++) {
                    if (line[x] == '1') {
                        pixelSet.add(new Point(x, y));
                    }
                }
                y++;
            }
        }
        return pixelSet;
    }

    private Point getNextPoint(Point point, Direction direction) {
        int x = (int) point.getX();
        int y = (int) point.getY();
        switch (direction) {
            case HORIZONTAL:
                return new Point(x + 1, y);
            case VERTICAL:
                return new Point(x, y + 1);
            case DIAGONAL:
                return new Point(x - 1, y + 1);
            case ANTIDIAGONAL:
                return new Point(x + 1, y + 1);
            default:
                throw new IllegalStateException("Invalid direction: " + direction);
        }
    }

    private void addEndpoint(List<Point> pixels, Point pixel, Direction direction) {
        endpointList.add(pixel);
        Point next = getNextPoint(pixel, direction);
        Point nextNext = getNextPoint(next, direction);
        Point end = next;
        while (pixels.contains(nextNext)) {
            end = nextNext;
            pixels.remove(next);
            next = nextNext;
            nextNext = getNextPoint(nextNext, direction);
        }
        endpointList.add(end);
    }

    public void removeMiddleEndpoints() {
        endpointList.removeIf(pixel -> Collections.frequency(endpointList, pixel) > 1);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(path.getFileName().toString());
        for (Point point : endpointList) {
            stringBuilder.append(" (");
            stringBuilder.append((int) point.getX());
            stringBuilder.append(",");
            stringBuilder.append((int) point.getY());
            stringBuilder.append(")");
        }
        return stringBuilder.toString();
    }
}
