
import java.util.Random;

public class Shape {
    public enum Tetrominoes { NoShape, ZShape, SShape, LineShape, TShape, SquareShape, LShape, MirroredLShape }

    private Tetrominoes pieceShape;
    private int[][] coords;
    private static final int[][][] shapesTable = new int[][][] {
        {{0,0}, {0,0}, {0,0}, {0,0}},
        {{0,-1}, {0,0}, {-1,0}, {-1,1}},
        {{0,-1}, {0,0}, {1,0}, {1,1}},
        {{0,-1}, {0,0}, {0,1}, {0,2}},
        {{-1,0}, {0,0}, {1,0}, {0,1}},
        {{0,0}, {1,0}, {0,1}, {1,1}},
        {{-1,-1}, {0,-1}, {0,0}, {0,1}},
        {{1,-1}, {0,-1}, {0,0}, {0,1}}
    };

    public Shape() {
        coords = new int[4][2];
        setShape(Tetrominoes.NoShape);
    }

    public void setShape(Tetrominoes shape) {
        for (int i = 0; i < 4; i++) {
            coords[i][0] = shapesTable[shape.ordinal()][i][0];
            coords[i][1] = shapesTable[shape.ordinal()][i][1];
        }
        pieceShape = shape;
    }

    public void setRandomShape() {
        Random r = new Random();
        int x = Math.abs(r.nextInt()) % 7 + 1;
        Tetrominoes[] values = Tetrominoes.values();
        setShape(values[x]);
    }

    public Tetrominoes getShape() {
        return pieceShape;
    }

    public int x(int index) {
        return coords[index][0];
    }

    public int y(int index) {
        return coords[index][1];
    }

    public void rotateRight() {
        for (int i = 0; i < 4; i++) {
            int temp = coords[i][0];
            coords[i][0] = -coords[i][1];
            coords[i][1] = temp;
        }
    }
}
