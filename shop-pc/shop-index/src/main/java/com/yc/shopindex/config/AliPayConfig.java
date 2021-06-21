package com.yc.shopindex.config;

import org.springframework.context.annotation.Configuration;

import java.io.FileWriter;
import java.io.IOException;

@Configuration
public class AliPayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016102900775666";//例：2016082600317257

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCTY6IjrKXMHguqDIPoWXOFjZBt9XaHBvIIRCvTKqDJ3slADrbwtR5WDTQVp5mH8KytosPAODE8NkNkDqaL3MplnZj3kRJ/n6oax83lrLIWcXUazSwNjHKVAsD96qoVraLhqDUNT8UnNoHkbJxy+qVq9iUASkfJj29AfvDDWR3aJq2hLW6TDnXS5nYZxVsBxkedFpRhofc5WddzDV+OgZC/vyrBj+1Exj/kqSSbYbz9XnqGrlxxq/X7QmTg8MqPJseNiNrF1FsJEIthcJ7BLDHvwiY/IB3hHVbshd9inxZP8es71YO81ncnQ4P3hKfu2MKDYPZVvgVlFnpTNuA8V179AgMBAAECggEAZHQknwHdoofb1VNgV+0Xki/Q/1CsLw1pyn6EDBFisxO3wIQG5XncKjQnipbHSmKbnmBv4EOsMuic98YQdwTNoYJPLnRe0kMFEemaCG6ylh9P/CtYWgeeVLciyCy3Lb1BZWFafy8eJUK+NuxbmPYB5ivLPi5iTRsIbUHXYJ0Nfo+K4AnSv0NaanAw9iFgAe183ymnX+HyR6kc2sLB+MWbR7g9dNgdpe1dT91Wbe+vK0sk2ITpgfMdxYhqGUOu5u60a2Tr1Rnz6sTZENAAXhi/dF+s6ri1u7kftDp5A7x8VkcJNqM9Ww1nEMgbrwVAF6tnynKMAXsrQnr99eiMxnrK1QKBgQD5pUH/V2h27XFkZtAripok3TBQE97b4jgNJ3O09WRn4Ou5LXg0Li7dWKtoSV93J1zStA24FHiCvutQol7gJd/4FiVNEK2g238ABHvScq1VQtlTaNBrYmagzxWXlHQA5661vElS3ZHgHwjzcg0dKxmkrSfKIp8YSVravPDXfGAELwKBgQCXJA1NTxaigNLalcQtIBr6NVkXIGcc3fy8AgYn3TW8M/127ODyNj1Q8dgXPAF5vR6Oxe8e3xSDoRV5mTwTuAtLaTDEZrsq90KUWJO/udG41MgIfE8iCnGSf6kdzKh4+/esYPUCvT4Z+V9LNM4Vg581L91ayJYdphLW/H/2VcSIkwKBgQDEBeNgoD3CxIb5E1H6HocS0yuQdUnU1ji0+3l7agRBHPd7HBz6vBjIbUuKQ7LWWO4l9IOhO1G1ODyFwDlHP/YxUxJPBP2RBUkftmWTxcAc5rTHLdIZv9ZeBr0pnMPJwiIJSaM5kgltNnFM1w09Dg7Ao8ZOjgjmhRL2Fs32Jg8uuQKBgCLOFpIbCGdbqzLTEl4h6pR7/Iwq1hP7utvAy5IaiulQP6pHWnXsr6oYR17yXpSeTI8sXaiKTrsROVTLSN5xn4g6LRTNY4S9dAOdckjQjc82kOoDVmXMaeMGqvdFzfYucBauLT34PnjUyHEVM/AYY21oHF1vm8IwIF6Jvx4HGj8pAoGAQ4qI9prUKVc7L7+se5rJreg4jbzabhfoee/vGLxAFVhn+QEUfdh8C7A9LHrSX8MQYrIW2GOK8CoEPpVOOUkLDhsTMnQHFAH8Nu7Yf5PD6mspTkPxaVxFeJ4i2Q4t+eegHtAsihm7H/lfFEzgFs7ktVhBt4QvZcHJArZyC0bLGZs=";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm
    // 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzI/beR6Wgfy44Uw5MJ6UBKn0Kc5lFI6mr7XsBdv3knMu407eMqCR/XMoHU7BJmjWI9qL+Ge3SZFw65PpR2Fd0MoPiB8zTsIEG9M88a+h9lE8p8KtNtxnGVnbbQsga9fk3YWhY31vS4mwqxBnpB2xHZ2jeOXKq3F9N4am2Hsqc6j5NP/LtUgyn3TNKS16v9UgO3BumggCxRjXLsvp2yh7u/URz1LP63mXJ7R0xw5gy/9+kKExThGRqDWTK2mfWknK5onnLniarknVxSkY3GpaquOHPtXtVWO+LoXoh52go5svSzgIy5TNh6VaWRA/WGMKaJRxH8P8OKxkWGd1AHJjyQIDAQAB/CsraLDwDgxPDZDZA6mi9zKZZ2Y95ESf5+qGsfN5ayyFnF1Gs0sDYxylQLA/eqqFa2i4ag1DU/FJzaB5GyccvqlavYlAEpHyY9vQH7ww1kd2iatoS1ukw510uZ2GcVbAcZHnRaUYaH3OVnXcw1fjoGQv78qwY/tRMY/5Kkkm2G8/V56hq5ccav1+0Jk4PDKjybHjYjaxdRbCRCLYXCewSwx78ImPyAd4R1W7IXfYp8WT/HrO9WDvNZ3J0OD94Sn7tjCg2D2Vb4FZRZ6UzbgPFde/QIDAQAB";
    // 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    /**
     * 返回的时候此页面不会返回到用户页面，只会执行你写到控制器里的地址
     */
    public static String notify_url = "http://127.0.0.1/shop-index/alipay/notify_url";
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问/alipay/notify_url
    /**
     * 此页面是同步返回用户页面，也就是用户支付后看到的页面，上面的notify_url是异步返回商家操作，谢谢
     * 要是看不懂就找度娘，或者多读几遍，或者去看支付宝第三方接口API，不看API直接拿去就用，遇坑不怪别人
     */
    public static String return_url = "http://127.0.0.1/shop-index/alipay/return_url";
    // 签名方式
    public static String sign_type = "RSA2";
    // 字符编码格式
    public static String charset = "utf-8";
    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
    // 日志地址
    public static String log_path = "D:/logs/";

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_"
                    + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}