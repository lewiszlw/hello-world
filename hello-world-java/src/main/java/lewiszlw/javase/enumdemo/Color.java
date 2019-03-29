package lewiszlw.javase.enumdemo;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei
 * Date:2018/7/18
 * Time:12:24
 */
public enum Color {
    RED(1, "红") {
        public String getComments() {
            return "国旗的主体颜色是红色";
        }
    },
    YELLOW(2, "黄") {
        public String getComments() {
            return "向日葵是黄色的";
        }
    },
    BULE(3, "蓝") {
        public String getComments() {
            return "晴空是蓝色的";
        }
    };

    private int code;
    private String value;

    Color(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public String toString() {
        return "code: " + this.code + ", value: " + this.value;
    }

    public int getCode() {
        return this.code;
    }

    public String getValue() {
        return this.value;
    }

    public abstract String getComments();
}
