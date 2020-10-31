package client_sharenow;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class HashGenerator {
    public static String hash(String s){
        String ans=null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] str=s.getBytes();
            byte[] hashstr=messageDigest.digest(str);
//            ans = new String(hashstr);
            StringBuffer sb = new StringBuffer();
            for(int i=0;i<str.length;i++){
                sb.append(Integer.toString((str[i] & 0xff)+0x100,16)).substring(1);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return ans;
    }
}
