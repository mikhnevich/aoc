/*
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

*/
/*

*//*

public class Day5 {

    public static void solve() {
        int n = 0;
        byte[] result;
        boolean hasZeros = false;

        try {
            MessageDigest mDigest = MessageDigest.getInstance("MD5");

            int digitsFound = 0;
            int partAPos = 0;
            char[] pwdPartA = new char[8];
            char[] pwdPartB = "xxxxxxxx".toCharArray();

            while (digitsFound < 8) {
                // Find next five zero hash
                do {
                    mDigest.update((input + n).getBytes());
                    result = mDigest.digest();
                    hasZeros = startsWith5Zeros(result);
                    n++;
                }
                while (!hasZeros);

                // (Part a): Password digits are in sequence.
                if (partAPos < 8) {
                    pwdPartA[partAPos++] = toHex(result).charAt(5);
                }

                // (Part b) Find the first instance of positions between 0-7
                int partBPos = Character.getNumericValue(toHex(result).charAt(5));
                if ((partBPos < 8) && (pwdPartB[partBPos] == 'x')) {
                    pwdPartB[partBPos] = toHex(result).charAt(6);
                    digitsFound++;
                }
            }
            System.out.println("Part A password: " + new String(pwdPartA).toLowerCase());
            System.out.println("Part B password: " + new String(pwdPartB).toLowerCase());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Problem creating MD5: " + e);
        }
    }

    static boolean startsWith5Zeros(byte[] bytes) {
        // To be padded with 5 zeros in hex, the first two bytes must both be zero
        if ((bytes[0] != 0) || (bytes[1] != 0)) {
            return false;
        }
        String hex = toHex(bytes);
        if (hex.charAt(4) == '0') return true;
        return false;
    }

    static String toHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
//        for (int i = 0; i < bytes.length; i++) {
//            int v = bytes[i] & 0xFF;
//            hexChars[i * 2] = HEX_ARRAY[v >>> 4];
//            hexChars[i * 2 + 1] = HEX_ARRAY[v & 0x0F];
//        }
        return new String(hexChars);
    }
}
*/
