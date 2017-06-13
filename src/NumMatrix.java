public class NumMatrix {

    private int[][] matrix;
    private int[][] accum;
    private int row;
    private int col;
    
    private int getAccum(int i, int j) {
        if(i<0||j<0) return 0;
        return accum[i][j];
    }
    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;
        row = matrix.length;
        if(row>0)      
        {
            col = matrix[0].length;
            accum = new int[row][col];
        }
        for(int i=0;i<row;++i){
            for(int j=0;j<col;++j) {
                accum[i][j] = getAccum(i-1,j) + getAccum(i, j-1) + matrix[i][j] - getAccum(i-1,j-1);
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        --row1; --col;
        int res = getAccum(row2, col2) - getAccum(row2,col1) - getAccum(row1,col2) + getAccum(row1,col1);
        return res;        
    }
    
    public static void main(String[] args) {
    }
}


// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.sumRegion(1, 2, 3, 4);