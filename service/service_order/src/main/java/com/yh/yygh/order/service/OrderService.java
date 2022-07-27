package com.yh.yygh.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yh.yygh.common.vo.OrderCountQueryVo;
import com.yh.yygh.order.pojo.OrderInfo;
import com.yh.yygh.order.pojo.vo.OrderQueryVo;

import java.util.Map;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author yh
 * @since 2022-07-25
 */
public interface OrderService extends IService<OrderInfo> {

    Long saveOrder(String scheduleId, Long patientId);

    IPage<OrderInfo> selectPage(Page<OrderInfo> pageParam, OrderQueryVo orderQueryVo);

    OrderInfo getOrder(String orderId);

    Object show(Long id);

    void patientTips();

    Map<String, Object> getCountMap(OrderCountQueryVo orderCountQueryVo);
}
