package ssm.bean;

import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(chain = true)
public enum ResultConstant {
    SYSTEM_ERROR(404,"系统错误"),
    USER_PICKNUM_EXTRACTED(1000,"已抽取过号码!"),
    SUCCESS(200,"请求成功");


    /*属性*/
    private int code;
    private String desc;

    ResultConstant(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
