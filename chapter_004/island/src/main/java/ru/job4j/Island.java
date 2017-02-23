package ru.job4j;

/**
 * Island class.
 *
 * @author Denis
 * @since 23.02.2017
 */
class Island {
    /**
     * Find bigger island.
     *
     * @param map map.
     * @return bigger island size.
     */
    int findBiggerIsland(int[][] map) {
        int biggerIsland = 0;
        if (map != null && map.length > 0 && checkSquare(map)) {
            int size = map[0].length;
            int newIsland;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (map[i][j] == 1) {
                        newIsland = calculate(map, i, j, size);
                        biggerIsland = biggerIsland > newIsland ? biggerIsland : newIsland;
                    }
                }
            }
        }
        return biggerIsland;
    }

    /**
     * Calculate island size.
     *
     * @param map    map.
     * @param row    current row.
     * @param column current column.
     * @param size   size of map.
     * @return size of island.
     */
    private int calculate(int[][] map, int row, int column, int size) {
        int result = 0;
        if (map[row][column] == 1) {
            map[row][column] = 0;
            result++;
            for (int i = (row - 1 < 0 ? 0 : row - 1); i <= (row + 1 == size ? row : row + 1); i++) {
                for (int j = column - 1 < 0 ? 0 : column - 1; j <= (column + 1 == size ? column : column + 1); j++) {
                    result += calculate(map, i, j, size);
                }
            }
        }
        return result;
    }

    /**
     * Check if map is square.
     *
     * @param map map
     * @return true if map is square.
     */
    private boolean checkSquare(int[][] map) {
        boolean result = true;
        int length = map.length;
        for (int i = 0; i < length; i++) {
            if (map[i].length != length) {
                result = false;
                break;
            }
        }
        return result;
    }
}
