package ssm.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
public class Result implements Serializable {
    private static final long serialVersionUID = -5407227492239816992L;

    private int code;
    private String desc;
    public Result() {
    }
    public Result(int code) {
        this.code = code;
    }

    public Result(int code,String desc) {
        this.code = code;
        this.desc=desc;
    }

    public void initMe(int code,String desc) {
        this.code = code;
        this.desc=desc;
    }
    public void initMe(ResultConstant resultConstant) {
        this.code = resultConstant.getCode();
        this.desc = resultConstant.getDesc();
    }


}
