package com.qatix.base.lang.string;

public class StringMultiply {
    public static void main(String[] args) {
        String a = "721";
        String b = "0";
        System.out.println(multiply(a, b));
    }

    public static String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        String[] parts = new String[num2.length()];
        for (int j = num2.length() - 1; j >= 0; j--) {
            parts[num2.length() - 1 - j] = mult(num1, num2.charAt(j), num2.length() - 1 - j);
        }
        int[] pointer = new int[num2.length()];

        int pre = 0;
        while (true) {
            int t = 0;
            boolean flag = false;
            for (int i = 0; i < num2.length(); i++) {
                if (pointer[i] < parts[i].length()) {
                    t = t + (parts[i].charAt(pointer[i]) - '0');
                    flag = true;
                }
                pointer[i]++;
            }
            t = t + pre;
            if (t > 0 || flag) {
                sb.append((char) (t % 10 + '0'));
                pre = t / 10;
            }
            if (!flag) {
                break;
            }
        }
        if (pre > 0 || sb.length() < 1) {
            sb.append((char) (pre + '0'));
        }
        return reserve(sb.toString());
    }

    private static String mult(String num, char ch, int paddingLen) {
        StringBuilder sb = new StringBuilder();
        int t = ch - '0';
        if (t < 1) {
            return "0";
        }
        while (paddingLen-- > 0) {
            sb.append('0');
        }
        int pre = 0;
        for (int i = num.length() - 1; i >= 0; i--) {
            int ct = num.charAt(i) - '0';
            int cm = t * ct + pre;
            sb.append((char) (cm % 10 + '0'));
            pre = cm / 10;
        }
        if (pre > 0) {
            sb.append((char) (pre + '0'));
        }
        return sb.toString();
    }

    public static String reserve(String str) {
        if (str == null) {
            return null;
        }
        int len = str.length();
        char[] chars = str.toCharArray();
        for (int i = 0; i < len / 2; i++) {
            char tmp = chars[i];
            chars[i] = chars[len - 1 - i];
            chars[len - 1 - i] = tmp;
        }
        return String.valueOf(chars);
    }
}
