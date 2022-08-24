package org.microframework.algorithm.array;

/**
 * 二维数组
 *
 * @author Shaoyu Liu
 * @date 2022/4/11 23:12
 */
public class Array2D {
    public static void main(String[] args) {
        // 数组中的没填写的数字用"."代替
        char[][] chars = {
                "59867.1..".toCharArray()
                , "6........".toCharArray()
                , "3........".toCharArray()
                , "8..9..7..".toCharArray()
                , "4........".toCharArray()
                , "7........".toCharArray()
                , "1..8..2..".toCharArray()
                , ".........".toCharArray()
                , ".........".toCharArray()};
        System.out.println(isValidSudoku2(chars));

        int[][] matrix = {
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}};
        rotate(matrix);
    }


    public static boolean isValidSudoku(char board[][]) {
        int length = board.length;
        //二维数组line表示的是对应的行中是否有对应的数字，比如line[0][3]
        //表示的是第0行（实际上是第1行，因为数组的下标是从0开始的）是否有数字3
        int line[][] = new int[length][length];
        int column[][] = new int[length][length];
        int cell[][] = new int[length][length];
        for (int i = 0; i < length; ++i)
            for (int j = 0; j < length; ++j) {
                //如果还没有填数字，直接跳过
                if (board[i][j] == '.')
                    continue;
                //num是当前格子的数字
                int a = board[i][j] - '0';
                int b = board[i][j];
                int c = board[i][j] - 1;
                int num = board[i][j] - '0' - 1;
                // TODO
                //k是第几个单元格，9宫格数独横着和竖着都是3个单元格
                int k = i / 3 * 3 + j / 3;
                //如果当前数字对应的行和列以及单元格，只要一个由数字，说明冲突了，直接返回false。
                //举个例子，如果line[i][num]不等于0，说明第i（i从0开始）行有num这个数字。
                int i1 = line[i][num];
                int i2 = column[j][num];
                int i3 = cell[k][num];
                if (line[i][num] != 0 || column[j][num] != 0 || cell[k][num] != 0)
                    return false;
                //表示第i行有num这个数字，第j列有num这个数字，对应的单元格内也有num这个数字
                line[i][num] = column[j][num] = cell[k][num] = 1;
            }
        return true;
    }

    /**
     * 判断数独有效性（中已有数字是否已经冲突）
     * @param board
     * @return
     */
    public static boolean isValidSudoku2(char board[][]) {
        // 步骤一：因为只有1-9这9个数字，第一个[]表示第几行||第几列||哪个9宫格；第二个[]表示1-9这个数字是否已存在：0表示不存在，1表示已存在。
        int[][] row = new int[9][9]; // 1）第几行是否存在这个元素
        int[][] col = new int[9][9]; // 2）第几列是否存在这个元素
        int[][] cell = new int[9][9]; // 3）9宫格是否存在这个元素
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if(board[i][j] == '.'){
                    continue;
                }
                int num = board[i][j] - '0' - 1; // TODO 哈希值 ：直接board[i][j]取值会有问题，取的不是应该有的值
                int cellIndex = i / 3 *3  + j / 3; // TODO 第几个九宫格 ：(row/3)*3+(line/3)为方块的标号
                System.out.println(num);
                // 步骤二：判断行、列、九宫格是否存在这个数字，存在就false，不存在就改为1表示已存在。
                if (row[i][num] != 0 || col[j][num] != 0 || cell[cellIndex][num] != 0) {
                    return false;
                }
                row[i][num] = col[j][num] = cell[cellIndex][num] = 1;
            }
        }
        return true;
    }


    /**
     * 旋转图像（顺时针旋转二维数组）
     *
     * @param matrix
     */
    public static void rotate(int[][] matrix) {
        /*  1.第一个for循环：先旋转最外层，然后往里面一层一层旋转
         *  因为是对称的，只需要计算循环前半行即可
         * （i=0：也就是第一行的时候横着的第一行会放到右边第一列，最后一行会放到左边第一列）
         * （i=1：也就是第二行的时候横着的第二行会放到右边第二列，倒数第二行也右边第二列）
         */
        for (int i = 0; i < matrix.length / 2; i++) {
            /* j=i：第一次循环最外成一圈，也就是i=0，第一次循环里面一圈也就是i=1，以此类推
             * j< matrix.length - i - 1:确保每圈尾部
             * j++：列往后移动
             */
            for (int j = i; j < matrix.length - i - 1; j++) {
                //3.腾出来第一个要【被替换元素】
                int temp = matrix[i][j];
                int x = matrix.length - 1 - i;  // 3.2【替换元素】纵坐标（y）
                int y = matrix.length - 1 - j; // 3.1【替换元素】横坐标（x）

                // 测试：先定位好四个元素：右上角、右下角、左下角、左上角
//                System.out.println(matrix[j][x]); // 右上角
//                System.out.println(matrix[x][y]); // 右下角
//                System.out.println(matrix[y][i]); // 左下角
//                System.out.println(matrix[i][j]); // 左上角

                // 4.从左下角开始，用后面的来替换自己（顺时针）
                matrix[i][j] = matrix[y][i]; //4.1左下角赋值给左上角
                matrix[y][i] = matrix[x][y]; //4.2右下角赋值给左下角
                matrix[x][y] = matrix[j][x]; //4.3右上角赋值给右下角
                matrix[j][x] = temp;         //4.4左上角赋值给右上角
            }
        }


    }

}
