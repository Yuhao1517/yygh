package com.yh.yygh.common.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 就诊人表
 * </p>
 *
 * @author yh
 * @since 2022-07-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Patient implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 证件类型
     */
    private String certificatesType;

    /**
     * 证件编号
     */
    private String certificatesNo;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 出生年月
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;

    /**
     * 手机
     */
    private String phone;

    /**
     * 是否结婚
     */
    private Integer isMarry;

    /**
     * 省code
     */
    private String provinceCode;

    /**
     * 市code
     */
    private String cityCode;

    /**
     * 区code
     */
    private String districtCode;

    /**
     * 详情地址
     */
    private String address;

    /**
     * 联系人姓名
     */
    private String contactsName;

    /**
     * 联系人证件类型
     */
    private String contactsCertificatesType;

    /**
     * 联系人证件号
     */
    private String contactsCertificatesNo;

    /**
     * 联系人手机
     */
    private String contactsPhone;

    /**
     * 就诊卡号
     */
    private String cardNo;

    /**
     * 是否有医保
     */
    private Integer isInsure;

    /**
     * 状态（0：默认 1：已认证）
     */
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 逻辑删除(1:已删除，0:未删除)
     */
    private Integer isDeleted;

    @ApiModelProperty(value = "其他参数")
    @TableField(exist = false)
    private Map<String,Object> param = new HashMap<>();
}
