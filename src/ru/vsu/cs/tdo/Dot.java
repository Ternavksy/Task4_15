package Program;

public class Dot implements Comparable<Dot> {
    private int x;
    private int y;

    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public static Dot[] intMatrixToDotArray(int[][] matrix) {
        Dot[] dots = new Dot[matrix.length];
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new Dot(matrix[i][0], matrix[i][1]);
        }
        return dots;
    }

    public static int[][] dotArrayToIntMatrix(Dot[] array) {
        int[][] matrix = new int[array.length][2];
        for (int i = 0; i < array.length; i++) {
            matrix[i][0] = array[i].x();
            matrix[i][1] = array[i].y();
        }
        return matrix;
    }

    public double getDistance() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    @Override
    public int compareTo(Dot d) {
        double dist1 = this.getDistance();
        double dist2 = d.getDistance();

        //String i = String.format("%.0f", dist1 - dist2);
        //return Integer.parseInt(i);
        return (int) Math.round(dist1 - dist2);
    }
}
