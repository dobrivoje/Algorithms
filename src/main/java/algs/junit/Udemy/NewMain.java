package algs.junit.Udemy;

/**
 *
 * @author root
 */
public class NewMain {

    public static void main(String[] args) {

        int[][] n = new int[2][3];

        int[][] m = new int[][]{
            {1, 3, 5, 7},
            {2, 4, 6, 8},
            {11, 12, 13, 14},
            {21, 22, 23, 24}
        };

        System.err.println("m.length() : " + m.length);
        
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.err.print("(" + i + ", " + j + ") -> " + m[i][j] + "  ");
            }

            System.err.println();
        }

        /*
        n[0][0] = 1;
        n[0][1] = 3;
        n[0][2] = 5;
        
        n[1][0] = 2;
        n[1][1] = 4;
        n[1][2] = 6;
        
        for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 2; i++) {
        System.err.println("#2 :" + n[i][j]);
        }
        }
         */
    }

}
