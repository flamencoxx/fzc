package com.fzc.fzccommon;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class FzcCommonApplicationTests {


    public int strStr(String haystack, String needle) {
        int len_h = haystack.length();
        int len_n = needle.length();
        char[] h = haystack.toCharArray();
        char[] n = needle.toCharArray();
        int i = 0;
        int j = 0;
        while (i < len_h && j < len_n) {
            if (h[i] == n[j]) {
                i++;
                j++;
                if (j == len_n) {
                    return i - j;
                }
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        return -1;
    }

    public int firstUniqChar(String s) {
        char[] c = s.toCharArray();

        return 0;
    }

    @Test
    public void atoi() {
        String str = "   -12355";
        int result = myAtoi(str);
        System.out.println(result);
        str = str.trim();
        System.out.println(str.charAt(0));
    }

    public int myAtoi(String str) {
        str = str.trim();
        if (str.length() == 0) return 0;
        if (!Character.isDigit(str.charAt(0))
                && str.charAt(0) != '-' && str.charAt(0) != '+')
            return 0;
        long ans = 0L;
        boolean neg = str.charAt(0) == '-';
        int i = !Character.isDigit(str.charAt(0)) ? 1 : 0;
        while (i < str.length() && Character.isDigit(str.charAt(i))) {
            ans = ans * 10 + (str.charAt(i++) - '0');
            if (!neg && ans > Integer.MAX_VALUE) {
                ans = Integer.MAX_VALUE;
                break;
            }
            if (neg && ans > 1L + Integer.MAX_VALUE) {
                ans = 1L + Integer.MAX_VALUE;
                break;
            }
        }
        return neg ? (int) -ans : (int) ans;
    }

    @Test
    void reverseNum() {
        int i = -123;
        int re = reverse(i);
        System.out.println(re);

    }

    public int reverse1(int x) {
        int res = 0;
        while (x != 0) {
            int t = x % 10;
            int newRes = res * 10 + t;

            if ((newRes - t) / 10 != res)
                return 0;

            res = newRes;
            x = x / 10;

        }
        return res;
    }

    public int reverse(int x) {
        String str = String.valueOf(x);
        char[] c = str.toCharArray();
        int len = c.length;
        if (c[0] == '-') {
            if (len == 2) {
                return x;
            } else {
                for (int i = 1; i <= len / 2; i++) {
                    char temp = c[i];
                    c[i] = c[len - i];
                    c[len - i] = temp;
                }
                String res = String.valueOf(c);
                return Integer.parseInt(res);
            }
        } else {
            if (len == 1) {
                return x;
            } else {
                for (int i = 0; i < len / 2; i++) {
                    char temp = c[i];
                    c[i] = c[len - 1 - i];
                    c[len - i - 1] = temp;
                }
                String res = String.valueOf(c);
                return Integer.parseInt(res);
            }
        }
    }

    @Test
    void contextLoads() {
        int[] num1 = {1, 2, 2, 1};
        int[] num2 = {2, 2};
        int[] num3 = intersect(num1, num2);
        System.out.println(Arrays.toString(num3));
    }

    @Test
    void hi() {
        int[][] ma = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        rotate1(ma);
        System.out.println(Arrays.deepToString(ma));

    }

    public void rotate1(int[][] matrix) {
        int len = matrix.length;
        for (int i = 0; i < len / 2; i++) {
            int[] temp = matrix[i];
            matrix[i] = matrix[len - i - 1];
            matrix[len - i - 1] = temp;
        }

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (j > i && i != j) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
        }
    }

    public void rotate2(int[][] matrix) {
        int len = matrix.length;
        for (int i = 0; i < len / 2; i++) {
            int[] temp = matrix[i];
            matrix[i] = matrix[len - i - 1];
            matrix[len - i - 1] = temp;
        }


        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (j > i && i != i) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
        }
    }


    public int[] twoSum(int[] nums, int target) {
        int x = 0;
        int y = 1;
        int len = nums.length;
        while (nums[x] + nums[y] != target) {
            if (x == len - 1) {
                return null;
            }
            if (y == len - 1) {
                x++;
                y = x + 1;
                continue;
            }
            y++;

        }
        int[] result = {x, y};
        return result;
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<Integer>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int x = 0;
        int y = 0;

        while (x < nums1.length && y < nums2.length) {
            if (nums1[x] == nums2[y]) {
                list.add(nums1[x]);
                x++;
                y++;
            } else {
                if (nums1[x] > nums2[y]) {
                    y++;
                } else {
                    x++;
                }
            }
        }

        int index = 0;
        int[] result = new int[list.size()];
        for (int i : list) {
            result[index] = i;
            index++;
        }
        return result;
    }

}
