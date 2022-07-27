package com.yh.yygh.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yh.yygh.common.vo.OrderCountQueryVo;
import com.yh.yygh.common.vo.OrderCountVo;
import com.yh.yygh.order.pojo.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author yh
 * @since 2022-07-25
 */
@Mapper
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {
    List<OrderCountVo> selectOrderCount(@Param("orderCountQueryVo") OrderCountQueryVo orderCountQueryVo);

}
