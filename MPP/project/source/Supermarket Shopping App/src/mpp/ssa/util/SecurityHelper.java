package mpp.ssa.util;

import javax.xml.bind.DatatypeConverter;
import  java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityHelper {

    public static String hashMD5String(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] digest = md.digest();
            return DatatypeConverter.printHexBinary(digest).toUpperCase();
        } catch(NoSuchAlgorithmException ex) {
        }

        return "";
    }
}