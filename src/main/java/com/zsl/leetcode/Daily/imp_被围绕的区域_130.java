package com.zsl.leetcode.Daily;

/**
 * 剑锋曾经遇到的题目
 * 有两种解法：
 * 1、深度优先算法
 * 对于每一个边界上的 O，我们以它为起点，标记所有与它直接或间接相连的字母 O；
 * 最后我们遍历这个矩阵，对于每一个字母：
 * 如果该字母被标记过，则该字母为没有被字母 X 包围的字母 O，我们将其还原为字母 O；
 * 如果该字母没有被标记过，则该字母为被字母 X 包围的字母 O，我们将其修改为字母 X。
 * <p>
 * <p>
 * 2、广度优先算法
 * 我们可以使用广度优先搜索实现标记操作。在下面的代码中，我们把标记过的字母 O 修改为字母 A。
 * 从边界开始，所有连着边界的X都修改成B 然后把剩余的 O 改成 X B 改成回 O
 * <p>
 * XXXX    XXXX    XXXX    XXXX
 * XOOX    XOOX    XXXX    XXXX
 * XXOX    XXOX    XXXX    XXXX
 * XOXX    XBXX    XBXX    XOXX
 *
 * @author ZengShiLin
 * @version 1.0
 * @date 2020/8/18 22:32
 */
public class imp_被围绕的区域_130 {
    int n, m;

    public void solve(char[][] board) {
        n = board.length;
        if (n == 0) {
            return;
        }
        m = board[0].length;
        for (int i = 0; i < n; i++) {
            dfs(board, i, 0);
            dfs(board, i, m - 1);
        }
        for (int i = 1; i < m - 1; i++) {
            dfs(board, 0, i);
            dfs(board, n - 1, i);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void dfs(char[][] board, int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= m || board[x][y] != 'O') {
            return;
        }
        board[x][y] = 'A';
        dfs(board, x + 1, y);
        dfs(board, x - 1, y);
        dfs(board, x, y + 1);
        dfs(board, x, y - 1);
    }

}
