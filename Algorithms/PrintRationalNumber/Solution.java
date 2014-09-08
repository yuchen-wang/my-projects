import java.util.*;

public class Solution {
    public static String printRationalNumber(int num, int denom) {
        HashMap<Integer, Integer> remainders = new HashMap<Integer, Integer>();
        StringBuffer sb = new StringBuffer();
        int quotient = num > denom ? num % denom * 10 / denom : num * 10 / denom;
        int remainder = num > denom ? num % denom * 10 % denom : num * 10 % denom;
        for (int i = 0; remainder != 0; i++) {
            if (remainders.containsKey(remainder)) {
                break;
            } else {
                remainders.put(remainder, i);
            }
            sb.append(quotient);
            remainder *= 10;
            quotient = remainder / denom;
            remainder %= denom;
        }
        if (remainder == 0) {
            sb.append(quotient);
            sb.append("(0)");
        } else {
            if (sb.charAt(remainders.get(remainder)) - '0' != quotient) {
                sb.append(quotient);
                sb.insert(remainders.get(remainder) + 1, '(');
            } else {
                sb.insert(remainders.get(remainder), '(');
            }
            sb.append(')');
        }
        sb.insert(0, '.');
        sb.insert(0, num > denom ? num / denom : 0);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(printRationalNumber(1,3));
        System.out.println(printRationalNumber(2,4));
        System.out.println(printRationalNumber(22,7));
        System.out.println(printRationalNumber(58,2828));
        System.out.println(printRationalNumber(1,29));
    }
}

