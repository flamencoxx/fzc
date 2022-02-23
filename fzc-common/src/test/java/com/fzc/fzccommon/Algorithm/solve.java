//package com.fzc.fzccommon.Algorithm;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
///**
// * @author Flamenco.xxx
// * @date 2021/8/20 18:03
// */
//@SpringBootTest
//public class Solve {
//
//    @Test
//    public void test() {
//        String s = "123";
//        int digits = s.charAt(0) - 1;
//        System.out.println(digits);
//
//    }
//
//    @Test
//    public void test1() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("ks");
//        System.out.println(sb.length());
//        System.out.println(sb.length() - 1);
//        String s = "123456";
//        char a1 = s.charAt(0);
//        char a3 = s.charAt(2);
//        System.out.println(a1);
//        System.out.println(a3);
//
//    }
//
//    @Test
//    public void test2() {
//        boolean[] bool = {true, false};
//        System.out.println(bool[1]);
//    }
//
//
//    private int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
//    private int m, n;
//
//    public void solve(char[][] board) {
//        if (board == null || board.length == 0) {
//            return;
//        }
//
//        m = board.length;
//        n = board[0].length;
//
//        for (int i = 0; i < m; i++) {
//            dfs(board, i, 0);
//            dfs(board, i, n - 1);
//        }
//        for (int i = 0; i < n; i++) {
//            dfs(board, 0, i);
//            dfs(board, m - 1, i);
//        }
//
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (board[i][j] == 'T') {
//                    board[i][j] = 'O';
//                } else if (board[i][j] == 'O') {
//                    board[i][j] = 'X';
//                }
//            }
//        }
//    }
//
//    private void dfs(char[][] board, int r, int c) {
//        if (r < 0 || r >= m || c < 0 || c >= n || board[r][c] != 'O') {
//            return;
//        }
//        board[r][c] = 'T';
//        for (int[] d : direction) {
//            dfs(board, r + d[0], c + d[1]);
//        }
//    }
//
//}
