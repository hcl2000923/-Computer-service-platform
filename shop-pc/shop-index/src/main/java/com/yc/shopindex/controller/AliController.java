package com.yc.shopindex.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.yc.bean.MemberInfo;
import com.yc.exception.BizException;
import com.yc.shopindex.config.AliPayConfig;
import com.yc.shopindex.service.AliPayService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @program: shop-pc
 * @description:
 * @author: 作者
 * @create: 2021-06-19 15:08
 */
@RestController
public class AliController {
    @Resource
    AliPayService aliPayService;

    //    @ApiOperation("支付接口")
    @RequestMapping("alipay/pay/{ono}/{totalPrice}")
    public String alipay(@PathVariable(value = "ono") String ono, @PathVariable(value = "totalPrice") BigDecimal totalPrice, @SessionAttribute MemberInfo loginUser) {
        try {
            return aliPayService.aliPay(ono, totalPrice, loginUser.getMno());
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return null;
        } catch (BizException e) {
            e.printStackTrace();
            return null;
        }
    }

    //    @ApiOperation("支付宝异步通知接口")由于没有暂时使用
    @PostMapping("alipay/notify_url")
    public String notifyAlipay() {
        System.out.println("异步回调");
        return "这是异步通知接口";
    }

    //    @ApiOperation("支付成功之后的回调")
    @GetMapping("alipay/return_url")
    public ModelAndView returnAlipay(ModelAndView mav, HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException, BizException {
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AliPayConfig.alipay_public_key, AliPayConfig.charset, AliPayConfig.sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——
        if (signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

            boolean flag = aliPayService.doOrder(out_trade_no, Double.valueOf(total_amount).intValue());
            if (flag == true) {
                mav.setViewName("redirect:http://localhost");
                return mav;
            } else {
                mav.setViewName("redirect:http://localhost/buyRecord.html");
                return mav;
            }
        } else {
            mav.setViewName("redirect:http://localhost/buyRecord.html");
            return mav;
        }
    }

    //    @ApiOperation("支付成功之后的回调")
    @GetMapping("alipay/outtime")
    public String doOutTime(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(AliPayConfig.gatewayUrl, AliPayConfig.app_id,
                AliPayConfig.merchant_private_key, "json", AliPayConfig.charset, AliPayConfig.alipay_public_key,
                AliPayConfig.sign_type);
        AlipayTradeCloseRequest request1 = new AlipayTradeCloseRequest();
        request1.setBizContent("{" + "    \"trade_no\":\"2013112611001004680073956707\","
                + "    \"out_trade_no\":\"HZ0120131127001\"," + "    \"operator_id\":\"YX01\"" + "  }");
        AlipayTradeCloseResponse response1 = alipayClient.execute(request1);
        if (response1.isSuccess()) {
            return "调用成功";
        } else {
            return "调用失败";
        }
    }

    @GetMapping("alipay/refund")
    public String doRefund(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(AliPayConfig.gatewayUrl, AliPayConfig.app_id,
                AliPayConfig.merchant_private_key, "json", AliPayConfig.charset, AliPayConfig.alipay_public_key,
                AliPayConfig.sign_type);

        // 设置请求参数
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();

        // 商户订单号，商户网站订单系统中唯一订单号
        String out_trade_no = "2020050422001495840501201616";
        // 支付宝交易号
        String trade_no = "";
        // 请二选一设置
        // 需要退款的金额，该金额不能大于订单金额，必填
        String refund_amount = "0.01";
        // 退款的原因说明
        String refund_reason = "";
        // 标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
        String out_request_no = "1";

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"trade_no\":\"" + trade_no + "\","
                + "\"refund_amount\":\"" + refund_amount + "\"," + "\"refund_reason\":\"" + refund_reason + "\","
                + "\"out_request_no\":\"" + out_request_no + "\"}");
        AlipayTradeRefundResponse alipayResponse = alipayClient.execute(alipayRequest);
        if (alipayResponse.isSuccess()) {
            return "调用成功";
        } else {
            return "调用失败";
        }
    }

    @GetMapping("alipay/doset")
    public String doSet(HttpServletRequest request, HttpServletResponse response) {
        return "存储数据库";
    }
}
