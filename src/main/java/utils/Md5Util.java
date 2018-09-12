package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
	public static final String KEY_MD5 = "MD5";
	/**
     * MD5加密算法
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String encryptMD5(byte[] data) throws NoSuchAlgorithmException {
        byte[] inputData = data;
        if (data == null){
            return null;
        }else {
            MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
            md5.update(inputData);
            return new String(md5.digest());
        }
    }
}
