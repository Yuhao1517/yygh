package com.yh.yygh.common.exception;

import com.yh.yygh.common.result.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "自定义全局异常类")
public class HospitalException extends RuntimeException {
    @ApiModelProperty(value = "异常状态码")
    private Integer code;

    /**
     * 通过状态码和错误消息创建异常对象
     */
    public HospitalException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public HospitalException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "YyghException{" +
                "code=" + code +
                ",message=" + this.getMessage() +
                '}';
    }
}
