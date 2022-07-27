package com.yh.yygh.hosp.receiver;

import com.rabbitmq.client.Channel;
import com.yh.yygh.common.vo.MsmVo;
import com.yh.yygh.hosp.pojo.Schedule;
import com.yh.yygh.hosp.pojo.vo.OrderMqVo;
import com.yh.yygh.hosp.service.ScheduleService;
import com.yh.yygh.rabbit.constant.MqConst;
import com.yh.yygh.rabbit.service.RabbitService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HospitalReceiver {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private RabbitService rabbitService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = MqConst.QUEUE_ORDER,durable = "true"),
            exchange = @Exchange(value = MqConst.EXCHANGE_DIRECT_ORDER),
            key = {MqConst.ROUTING_ORDER}
    ))
    public void receiver(OrderMqVo orderMqVo, Message message, Channel channel){
        //下单成功更新预约数
        Schedule schedule = scheduleService.getScheduleId(orderMqVo.getScheduleId());
        schedule.setReservedNumber(orderMqVo.getReservedNumber());
        schedule.setAvailableNumber(orderMqVo.getAvailableNumber());
        scheduleService.update(schedule);
        //发送短信
        MsmVo msmvo = orderMqVo.getMsmVo();
        if(null!=msmvo){
            rabbitService.sendMessage(MqConst.EXCHANGE_DIRECT_MSM,MqConst.ROUTING_MSM_ITEM,msmvo);
        }
    }

}
