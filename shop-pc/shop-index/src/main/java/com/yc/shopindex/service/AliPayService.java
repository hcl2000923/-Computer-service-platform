package com.yc.shopindex.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.yc.exception.BizException;
import com.yc.shopindex.config.AliPayConfig;
import com.yc.shopindex.controller.MemberAction;
import com.yc.shopindex.controller.OrderAction;
import com.yc.vo.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @Description：支付宝业务逻辑接口
 */
@Service
@Transactional(rollbackFor = {Exception.class, BizException.class})
public class AliPayService {
    @Resource
    private OrderAction orderAction;
    @Resource
    private MemberAction memberAction;

    /**
     * 支付服务(推荐传入订单号和金额,其余的从其他地方获取)
     */
    public String aliPay(String ono, BigDecimal totalPrice, Integer mno) throws AlipayApiException, BizException {

        //1、构建支付数据信息
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        Result res = orderAction.checkExist(ono, mno);
        if (res.getCode() == 0) {
            throw new BizException(res.getMsg());
        }
        //订单标题
        model.setSubject("大学城电脑配件销售服务");
        //订单号
        model.setOutTradeNo(ono);
        //订单付款金额
        model.setTotalAmount(totalPrice.toString());
        //订单付款时间
        model.setTimeoutExpress("");
        //销售产品码(必填)
        model.setProductCode("FAST_INSTANT_TRADE_PAY");
        //2、构建客户端
        DefaultAlipayClient defaultAlipayClient = new DefaultAlipayClient(AliPayConfig.gatewayUrl,
                AliPayConfig.app_id, AliPayConfig.merchant_private_key, "json",
                AliPayConfig.charset, AliPayConfig.alipay_public_key, AliPayConfig.sign_type);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //异步通知后回调
        request.setNotifyUrl(AliPayConfig.notify_url);
        //支付成功后回调
        request.setReturnUrl(AliPayConfig.return_url);
        request.setBizModel(model);
        String response = defaultAlipayClient.pageExecute(request).getBody();
        return response;
    }

    public boolean doOrder(String out_trade_no, Integer total_amount) throws BizException {
        Result r1 = orderAction.confirmOrder(out_trade_no);
        Result r2 = memberAction.addPoint((Integer) r1.getData(), total_amount);
        if (r1.getCode() != 1 || r2.getCode() != 1) {
            throw new BizException("购买出现异常");
        }
        return true;
    }
}
