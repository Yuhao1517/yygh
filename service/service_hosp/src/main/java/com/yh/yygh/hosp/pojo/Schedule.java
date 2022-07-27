package com.yh.yygh.hosp.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ApiModel(description = "Schedule")
@Document("Schedule")
@Data
public class Schedule {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @Id
    private String id;
    @ApiModelProperty(value = "医院编号")
    @Indexed //普通索引
    private String hoscode;

    @ApiModelProperty(value = "科室编号")
    @Indexed //普通索引
    private String depcode;

    @ApiModelProperty(value = "职称")
    private String title;

    @ApiModelProperty(value = "医生名称")
    private String docname;

    @ApiModelProperty(value = "擅长技能")
    private String skill;

    @ApiModelProperty(value = "排班日期")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date workDate;

    @ApiModelProperty(value = "排班时间（0：上午 1：下午）")
    private Integer workTime;

    @ApiModelProperty(value = "可预约数")
    private Integer reservedNumber;

    @ApiModelProperty(value = "剩余预约数")
    private Integer availableNumber;

    @ApiModelProperty(value = "挂号费")
    private BigDecimal amount;

    @ApiModelProperty(value = "排班状态（-1：停诊 0：停约 1：可约）")
    private Integer status;

    @ApiModelProperty(value = "排班编号（医院自己的排班主键）")
    @Indexed //普通索引
    private String hosScheduleId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除(1:已删除，0:未删除)")
    private Integer isDeleted;

    @ApiModelProperty(value = "其他参数")
    @Transient //被该注解标注的，将不会被录入到数据库中。只作为普通的javaBean属性
    private Map<String,Object> param = new HashMap<>();
}
