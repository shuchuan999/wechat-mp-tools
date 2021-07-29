package wechat.core.util;

public class StringUtil {
    public static boolean isEmpty(Object str) {
        return (str == null || "".equals(str));
    }

    public static boolean isErrorMsg(String json){
        if(json==null) return false;
        return json.matches("\\{ *\\\"errcode\\\" *: *-?\\d+ *, *\\\"errmsg\\\" *: *\\\".*?\\\" *\\} *");
    }
}
