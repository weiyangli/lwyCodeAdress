package ssm.bean;

import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(chain = true)
public enum ResultConstant {
    SYSTEM_ERROR(500,"系统错误"),
    USER_PICKNUM_EXTRACTED(1000,"已抽取过号码!"),
    SUCCESS(200,"请求成功"),
    IMPORT_ERROR(1001,"excel 导入失败");


    /*属性*/
    private int code;
    private String desc;

    ResultConstant(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static void main(String[] args) {
        ResultConstant.USER_PICKNUM_EXTRACTED.getCode();
    }
}
