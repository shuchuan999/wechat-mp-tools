package wechat.core.constant;

public enum Color {

    BLACK(" #000000"),//纯黑
    BLUE_DEFAULT("#173177"),//微信公众号默认蓝色
    RED(" #FF0000"),//纯红
    YELLOW(" #FFFF00"),//纯黄
    ORANGE(" #FFA500"),//橙色
    GREEN(" #008000"),//纯绿
    BLUE(" #0000FF"),//纯蓝
    PURPLE(" #800080"),//紫色
    WHITE(" #FFFFFF"),//纯白
    BROWN("##804000"),//棕色
    SKYBLUE(" #87CEEB"),//天蓝色

    ;

    private String code;

    Color(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
