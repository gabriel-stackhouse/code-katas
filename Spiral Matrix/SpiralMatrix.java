class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int rowIndex = 0;
        int columnIndex = 0;
        int verticalDirection = 0;
        int horizontalDirection = 1; // starting right
        int verticalBoundary = 0;
        int horizontalBoundary = 0;
        for (int i = 0; i < n * n; i++) {
            matrix[rowIndex][columnIndex] = i + 1;
            
            // Does direction change?
            if (columnIndex == n - horizontalBoundary - 1 && horizontalDirection == 1) {    // Right -> Down
                verticalDirection = 1;
                horizontalDirection = 0;
            } else if (rowIndex == n - verticalBoundary - 1 && verticalDirection == 1) {    // Down -> Left
                verticalDirection = 0;
                horizontalDirection = -1;
            } else if (columnIndex == horizontalBoundary && horizontalDirection == -1) {    // Left -> Up
                verticalDirection = -1;
                horizontalDirection = 0;
                verticalBoundary++;
            } else if (rowIndex == verticalBoundary && verticalDirection == -1) {           // Up -> Right
                verticalDirection = 0;
                horizontalDirection = 1;
                horizontalBoundary++;
            }
            
            // Increment indexes
            rowIndex += verticalDirection;
            columnIndex += horizontalDirection;
        }
        return matrix;
    }
}