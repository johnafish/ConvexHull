package convexhull3d;

/**
 * @author John
 */
public class DeterminantTools {
    /**
     * Calculate and return the determinant of a 4x4 matrix
     * Takes advantage of method in http://nebula.deanza.edu/~bloom/math43/Determinant4x4Matrix.pdf
     * @param m a 4x4 matrix of doubles
     * @return det, the determinant of m
     */
    public static double get4x4Determinant(double[][] m){
        double det = 0;
        for (int i = 0; i < 4; i++) { //top column
            double [][] minor = new double[3][3];
            for (int j = 1; j < 4; j++) { //Rows
                int column = 0;
                for (int k = 0; k < 4; k++) { //Columns
                    if(k==i){
                        continue;
                    }
                    else {
                        minor[j-1][column]=m[j][k];
                        column++;
                    }
                }
                
            }
            if(i%2==0){
                det+=m[0][i]*DeterminantTools.get3x3Determinant(minor);
            }
            else {
                det-=m[0][i]*DeterminantTools.get3x3Determinant(minor);
            }
        }
        return det;
    }
    /**
     * Calculate and return the determinant of a 3x3 matrix
     * Takes advantage of method in http://www.purplemath.com/modules/determs2.htm
     * @param m a 3x3 matrix of doubles
     * @return det, the determinant of m
     */
    public static double get3x3Determinant(double[][] m){
        double det = 0;
        double[][] extendedDet = new double[3][5];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                extendedDet[i][j]=m[i][j%3];
            }
        }
        
        for (int i = 0; i < 3; i++) {
            double downDiagonalProduct = 1;
            double upDiagonalProduct = 1;
            for (int j = 0; j < 3; j++) {
                downDiagonalProduct *= extendedDet[j][j+i];
                upDiagonalProduct *= extendedDet[j][4-j-i]; //4-j-i is (5-1)-(j+i) simplified.
            }
            det+=downDiagonalProduct-upDiagonalProduct;
        }
        return det;
    }
}
