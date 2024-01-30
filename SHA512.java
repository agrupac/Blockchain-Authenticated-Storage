import java.security.*;
import java.nio.charset.*;
/**
* This class generates SHA512 hashcode from strings.
*/
public class SHA512 {
    /**
    * Generates hexadecimal hashcode from a given string.
    *
    * @param message the string to hash.
    * @return sha512ValueHexa the hashcode.
    */
    protected static String hashSHA512(String message) {
        String sha512ValueHexa = "";
        try {
            MessageDigest digest512 = MessageDigest.getInstance("SHA-512");
            sha512ValueHexa = byteToHex(digest512.digest(message.getBytes(StandardCharsets.UTF_8)));
        }
        catch(NoSuchAlgorithmException exp) {
            exp.getMessage();
        }
        return sha512ValueHexa;
    }
    /**
    * Converts bytes to hexadecimal.
    *
    * @param digest the bytes to convert.
    * @return output the hexadecimal string.
    */
    public static String byteToHex(byte[] digest) {
        StringBuilder vector = new StringBuilder();
        for (byte c : digest) {
            vector.append(String.format("%02X", c));
        }
        String output = vector.toString();
        return output;
    }
}
