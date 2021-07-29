package wechat.core.util;

import java.security.MessageDigest;

public class SecurityUtil {

    public static String sha1Encode(String inStr){
        try {
            MessageDigest sha = null;
            try {
                sha = MessageDigest.getInstance("SHA");
            } catch (Exception e) {
                System.out.println(e.toString());
                e.printStackTrace();
                return "";
            }

            byte[] byteArray = inStr.getBytes("UTF-8");
            byte[] md5Bytes = sha.digest(byteArray);
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        }catch (Exception e){
            return "";
        }
    }
}
