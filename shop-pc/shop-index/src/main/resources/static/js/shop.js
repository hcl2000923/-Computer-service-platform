Vue.component("shop-bottom", {
    template: `
<div class="bottom">
    <a href="#">关于我们</a>|<a href="#">帮助中心</a>|<a href="legal.html">法律声明</a>|<a href="#">用户协议</a>|<a href="#">联系我们</a>|<a
        href="#">人才招聘</a>|<a href="#">站点地图</a>
        
    <p>网络文化经营许可证：粤网文[2015]0295-065号<br/>© 2015 深圳易易城科技网络有限公司. 粤ICP备15042543号</p><p class="p02"><img src="images/home/ico25.jpg"/><img src="images/home/ico26.jpg"/>
            <img src="images/home/ico27.jpg"/><img src="images/home/ico36.jpg"/><img src="images/home/ico37.jpg"/>
        </p>
</div>
`
});
Vue.component("shop-cart", {
    template: `
<div class="w1200">
    <div class="logo"><a href="index.html"></a></div>
    <div class="s_r">
        <dl>
            <dt><a href="shopcar.html">购物车</a></dt>
            <dd>客服电话：<b>400-0139-038</b></dd>
        </dl>
    </div>
    <div class="clear"></div>
</div>
`
});
Vue.component("welcome", {
    template: `
<div class="w1185">
    <div class="logo"><a href="index.html"></a></div>
    <div class="font-d">欢迎登陆</div>
    <div class="s_r"><img src="images/tel.jpg"/></div>
    <div class="clear"></div>
</div>
`
});
Vue.component("login-body", {
    template: `
<div class="top">
    <div class="w1200">
        <div class="left" v-if="loginUser!=undefined && loginUser!=null"><em>[{{loginUser.nickName}}]&nbsp;&nbsp;&nbsp;</em>您好，欢迎光临易易城！<a
                href="shop-memberinfo/logout">[注销]</a> 
        </div>
        <div class="left" v-else>易易城！<a href="login.html">[登录]</a> <a href="reg.html">[注册]</a>
        </div>
        <div class="right"><a href="member.html">我的会员中心</a>|<a href="#">服务中心</a>|<a href="#">在线客服</a>|<a
                href="shopcar.html">购物车<b>{{loginUser!= null && loginUser.cartInfoList!= undefined &&loginUser!= undefined && loginUser.cartInfoList!= null? loginUser.cartInfoList.length:0}}</b>件</a></div>
        <div class="clear"></div>
    </div>
</div>
`,
    data() {
        return {loginUser: {}};
    },
    created() {
        //定义函数读取用户信息
        axios.get("shop-memberinfo/getLoginUser").then(res => {
            if (res.data.code == 1) {
                this.loginUser = res.data.data;
            }
        });
    }
});
Vue.component("footer-body", {
    template: `
<div class="footer">
    <div class="f_bz">
        <div class="w1200">
            <dl class="dl01">
                <dt>正品保证</dt>
                <dd>正品护航 购物无忧</dd>
            </dl>
            <dl class="dl02">
                <dt>你消费 我买单</dt>
                <dd>返现购物商城</dd>
            </dl>
            <dl class="dl03">
                <dt>品类丰富</dt>
                <dd>品类齐全 轻松购物</dd>
            </dl>
            <dl class="dl04">
                <dt>特色服务体验</dt>
                <dd>为您呈现不一样的服务</dd>
            </dl>
            <div class="clear"></div>
        </div>
    </div>
    <div class="f_nav">
        <div class="w1200">
            <dl>
                <dt>新手指南</dt>
                <dd>
                    <a href="#">注册新用户</a>
                    <a href="#">商品订购流程</a>
                    <a href="#">会员注册协议</a>
                </dd>
            </dl>
            <dl>
                <dt>付款方式</dt>
                <dd>
                    <a href="#">支付宝支付</a>
                    <a href="#">网上银行支付</a>
                    <a href="#">货到付款</a>
                </dd>
            </dl>
            <dl>
                <dt>常见问题</dt>
                <dd>
                    <a href="#">订单状态</a>
                    <a href="#">发票说明</a>
                </dd>
            </dl>
            <dl>
                <dt>售后服务</dt>
                <dd>
                    <a href="#">退换货政策</a>
                    <a href="#">退换货流程</a>
                    <a href="#">退款说明</a>
                    <a href="#">退换货申请</a>
                </dd>
            </dl>
            <dl>
                <dt>客服中心</dt>
                <dd>
                    <a href="#">常见问题</a>
                    <a href="#">联系客服</a>
                    <a href="#">投诉与建议</a>
                </dd>
            </dl>
            <div class="ewm"><img src="images/home/ico35.png"/></div>
            <div class="ewm"><img src="images/home/ico38.png"/></div>
            <div class="clear"></div>
        </div>
    </div>
    <shop-bottom></shop-bottom>
</div>
`
});


