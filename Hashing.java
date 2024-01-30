/**
* This class drives the SHA512 class to generate digest values for AuthDLList.
*/
public class Hashing extends SHA512 {
    /**
    * Generates hexadecimal representation of the cryptographic hash of a given string.
    *
    * @param s the string to hash.
    * @return digest.substring(0,128) the resulting digest.
    */
    public static String cryptHash(String s) {
        String digest = hashSHA512(s);
        return digest.substring(0,128);
    }
}
