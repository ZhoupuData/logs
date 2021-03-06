package com.zhoupu.zplogsbatch.commons;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.zhoupu.zplogsbatch.domain.Actions;
import com.zhoupu.zplogsbatch.domain.Urls;
import com.zhoupu.zplogsbatch.domain.User;
import com.zhoupu.zplogsbatch.repository.ActionsRepository;
import com.zhoupu.zplogsbatch.repository.UrlsRepository;
import com.zhoupu.zplogsbatch.repository.UserRepository;

/**
 * 
 * @author tangdingyi
 *
 */
@Component
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

    @Value("${server.init.data:false}")
    private boolean isInit;

    @Autowired
    private ActionsRepository actionsRepository;

    @Autowired
    private UrlsRepository urlsRepository;


    @Autowired
    private UserRepository userRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // root application context 没有parent，他就是老大.
        if (event.getApplicationContext().getParent() == null) {
            onStartUpLoad();
        }
    }

    private void onStartUpLoad() {

        List<Actions> actions = actionsRepository.findAll();
        actions.forEach(action -> {
            DataCache.actionsMap.put(action.getAction(), action.getName());
        });


        List<Urls> urls = urlsRepository.findAll();
        urls.forEach(url -> {
            DataCache.urlsMap.put(url.getUrl(), url.getName());
        });

        if (isInit) {
            initAdd();
        }

    }

    private void initAdd() {
        Map<String, String> map1 = new LinkedHashMap<>();
        map1.put("22", "ROOT");
        map1.put("tt", "销售");
        map1.put("2", "采购");
        map1.put("44", "仓库");
        map1.put("erp/bill/saleorder/load/add/0", "销售订单");
        map1.put("erp/bill/sale/load/add/0?type=0", "销售单");
        map1.put("erp/bill/sale/load/add/0?type=1", "退货单");
        map1.put("erp/bill/saleorder/list", "查看销售订单");
        map1.put("erp/bill/sale/list?type=0", "查看销售单");
        map1.put("erp/bill/sale/list?type=1", "查看退货单");
        map1.put("erp/doc/salesmanComis/list", "业务员提成");
        map1.put("erp/bill/purchaseorder/load/add/0", "采购订单");
        map1.put("erp/bill/purchase/load/add/0?type=0", "采购单");
        map1.put("erp/bill/purchase/load/add/0?type=1", "采购退货单");
        map1.put("erp/bill/purchaseorder/list", "查看采购订单");
        map1.put("erp/bill/purchase/list?type=0", "查询采购单");
        map1.put("erp/bill/purchase/list?type=1", "查看采购退货单");
        map1.put("erp/bill/move/load/add/0", "调拨单");
        map1.put("erp/bill/stockadjust/load/add/0", "盘点盈亏单");
        map1.put("erp/bill/price/load/add/0", "成本调价单");
        map1.put("erp/bill/loss/load/add/0", "报损单");
        map1.put("erp/bill/check/load/add/1?checkType=1", "盘点任务（整仓）");
        map1.put("erp/bill/check/load/add/1?checkType=0", "盘点任务（部分）");
        map1.put("erp/bill/move/list", "查看调拨单");
        map1.put("erp/bill/stockadjust/list", "查看盘点盈亏单");
        map1.put("erp/bill/price/list", "查看成本调价单");
        map1.put("erp/bill/loss/list", "查看报损单");
        map1.put("erp/bill/check/list/1", "查看盘点任务（整仓）");
        map1.put("erp/bill/check/list/0", "查看盘点任务（部分）");
        map1.put("erp/bill/consumerpaid/load/add/0", "收款单");
        map1.put("erp/bill/supplierpaid/load/add/0", "付款单");
        map1.put("erp/bill/consumerprepay/load/add/0", "预收款单");
        map1.put("erp/bill/supplierprepay/load/add/0", "预付款单");
        map1.put("erp/bill/consumerpaid/list", "查看收款单");
        map1.put("erp/bill/supplierpaid/list", "查看付款单");
        map1.put("erp/bill/consumerprepay/list", "查看预收款单");
        map1.put("erp/bill/supplierprepay/list", "查看预付款单");
        map1.put("erp/doc/goods/list", "商品档案");
        map1.put("erp/doc/brand/list", "品牌档案");
        map1.put("erp/doc/dict/listDocDict", "单位档案");
        map1.put("erp/doc/dict/list/3", "统计类别");
        map1.put("erp/doc/warehouse/list", "仓库档案");
        map1.put("erp/doc/consumer/list", "客户档案");
        map1.put("erp/doc/supplier/list", "供应商档案");
        map1.put("erp/doc/account/list", "支付账户");
        map1.put("system/user/list", "操作员档案");
        map1.put("system/role/list", "操作员角色");
        map1.put("erp/doc/group/list", "分组档案");
        map1.put("erp/doc/area/list", "片区档案");
        map1.put("erp/doc/stockWarning/list", "库存预警设置");
        map1.put("system/role/list", "角色");
        map1.put("system/orga/list", "机构");
        map1.put("system/module/list", "菜单");
        map1.put("system/user/list", "用户");
        map1.put("commons/datasource/list", "数据源");
        map1.put("commons/sysuser/list", "租户管理");
        map1.put("commons/sysorga/list", "分配数据源");
        map1.put("erp/report/sale/reportlist/6", "销售明细表");
        map1.put("erp/report/sale/goodslist", "销售汇总(按商品)");
        map1.put("erp/report/sale/reportlist/1", "销售汇总(按客户)");
        map1.put("erp/report/sale/reportlist/3", "销售汇总(按业务员)");
        map1.put("erp/report/sale/reportlist/2", "销售汇总(按仓库)");
        map1.put("erp/report/sale/reportlist/4", "销售汇总（按供应商）");
        map1.put("erp/report/sale/reportlist/5", "热销排行榜");
        map1.put("erp/report/sale/reportlist/7", "销量走势图");
        map1.put("erp/doc/stock/list", "库存表");
        map1.put("erp/report/stocks/goodstockvarylist", "库存变化表-汇总");
        map1.put("erp/report/stocks/reportlist/4", "库存变化表-按单据");
        map1.put("erp/report/stocks/reportlist/1", "库存滞销报表");
        map1.put("erp/report/stocks/reportlist/2", "库存预警表");
        map1.put("erp/report/purchase/reportlist/1", "采购明细表");
        map1.put("erp/report/purchase/reportlist/2", "采购汇总（按商品）");
        map1.put("erp/report/purchase/reportlist/3", "采购汇总（按供应商）");
        map1.put("erp/report/finance/reportlist/1", "客户往来账");
        map1.put("erp/report/finance/reportlist/3", "客户应收款");
        map1.put("erp/report/finance/reportlist/2", "供应商往来账");
        map1.put("erp/report/finance/reportlist/4", "供应商应付款");
        map1.put("erp/report/salesman/reportlist/4", "业务员业绩");
        map1.put("erp/report/salesman/reportlist/1", "业务员提成汇总表");
        map1.put("erp/report/salesman/reportlist/6", "业务员拜访记录");
        map1.put("data/transfer/list", "数据迁移");
        map1.put("erp/doc/printinfo/load/update", "APP打印设置");
        map1.put("commons/app/list", "app发布");
        map1.put("other/handle/toOpenAccount", "开户");
        map1.put("erp/report/sale/reportlist/9", "销售商品成本利润");
        map1.put("other/bd/list", "商务列表");
        map1.put("erp/doc/config/list?type=PC_PRINT", "电脑打印设置");
        map1.put("erp/report/salesman/check/list", "业务员对账单");
        map1.put("erp/bill/ordertosale/list?page=xs", "订单转销售单");
        map1.put("erp/doc/initialdebt/list", "应收款初始化");
        map1.put("erp/report/finance/reportlist/5", "预收款余额");
        map1.put("erp/report/finance/reportlist/6", "预付款余额");
        map1.put("erp/bill/expenditure/load/add/0", "费用支出");
        map1.put("erp/bill/expenditure/list", "查看费用支出");
        map1.put("commons/newsinfo/list", "新闻资讯");
        map1.put("erp/report/salesman/reportlist/10", "客户活跃度");
        map1.put("commons/experuser/list", "体验用户");
        map1.put("erp/doc/config/list?type=COMPANYCONFIG", "公司设置项");
        map1.put("erp/doc/jointgoods/list", "组合商品档案");
        map1.put("erp/bill/joint/load/add/0?type=0", "组合单");
        map1.put("erp/bill/joint/load/add/0?type=1", "拆分单");
        map1.put("erp/bill/joint/list?type=0", "查看组合单");
        map1.put("erp/bill/joint/list?type=1", "查看拆分单");
        map1.put("erp/other/printtemplate/list", "打印模板");
        map1.put("commons/pay/sub/list", "订阅记录");
        map1.put("erp/bill/preorder/load/add/0", "订货单");
        map1.put("erp/bill/preorder/list", "查看订货单");
        map1.put("erp/report/ordergoods/reportlist/1", "订货汇总");
        map1.put("commons/product/listAll", "SaaS价格");
        map1.put("erp/doc/goodspriceplan/list", "价格方案");
        map1.put("erp/bill/ordertosale/list?page=zc", "打印装车单");
        map1.put("order/merOrderList", "订单管理");
        map1.put("22", "ROOT");
        map1.put("tt", "销售");
        map1.put("2", "采购");
        map1.put("44", "仓库");
        map1.put("erp/bill/saleorder/load/add/0", "销售订单");
        map1.put("erp/bill/sale/load/add/0?type=0", "销售单");
        map1.put("erp/bill/sale/load/add/0?type=1", "退货单");
        map1.put("erp/bill/saleorder/list", "查看销售订单");
        map1.put("erp/bill/sale/list?type=0", "查看销售单");
        map1.put("erp/bill/sale/list?type=1", "查看退货单");
        map1.put("erp/doc/salesmanComis/list", "业务员提成");
        map1.put("erp/bill/purchaseorder/load/add/0", "采购订单");
        map1.put("erp/bill/purchase/load/add/0?type=0", "采购单");
        map1.put("erp/bill/purchase/load/add/0?type=1", "采购退货单");
        map1.put("erp/bill/purchaseorder/list", "查看采购订单");
        map1.put("erp/bill/purchase/list?type=0", "查询采购单");
        map1.put("erp/bill/purchase/list?type=1", "查看采购退货单");
        map1.put("erp/bill/move/load/add/0", "调拨单");
        map1.put("erp/bill/stockadjust/load/add/0", "盘点盈亏单");
        map1.put("erp/bill/price/load/add/0", "成本调价单");
        map1.put("erp/bill/loss/load/add/0", "报损单");
        map1.put("erp/bill/check/load/add/1?checkType=1", "盘点任务（整仓）");
        map1.put("erp/bill/check/load/add/1?checkType=0", "盘点任务（部分）");
        map1.put("erp/bill/move/list", "查看调拨单");
        map1.put("erp/bill/stockadjust/list", "查看盘点盈亏单");
        map1.put("erp/bill/price/list", "查看成本调价单");
        map1.put("erp/bill/loss/list", "查看报损单");
        map1.put("erp/bill/check/list/1", "查看盘点任务（整仓）");
        map1.put("erp/bill/check/list/0", "查看盘点任务（部分）");
        map1.put("erp/bill/consumerpaid/load/add/0", "收款单");
        map1.put("erp/bill/supplierpaid/load/add/0", "付款单");
        map1.put("erp/bill/consumerprepay/load/add/0", "预收款单");
        map1.put("erp/bill/supplierprepay/load/add/0", "预付款单");
        map1.put("erp/bill/consumerpaid/list", "查看收款单");
        map1.put("erp/bill/supplierpaid/list", "查看付款单");
        map1.put("erp/bill/consumerprepay/list", "查看预收款单");
        map1.put("erp/bill/supplierprepay/list", "查看预付款单");
        map1.put("erp/doc/goods/list", "商品档案");
        map1.put("erp/doc/brand/list", "品牌档案");
        map1.put("erp/doc/dict/listDocDict", "单位档案");
        map1.put("erp/doc/dict/list/2", "大包单位");
        map1.put("erp/doc/dict/list/3", "统计类别");
        map1.put("erp/doc/warehouse/list", "仓库档案");
        map1.put("erp/doc/consumer/list", "客户档案");
        map1.put("erp/doc/supplier/list", "供应商档案");
        map1.put("erp/doc/account/list", "支付账户");
        map1.put("system/user/list", "业务员档案");
        map1.put("system/role/list", "业务员角色");
        map1.put("erp/doc/group/list", "分组档案");
        map1.put("erp/doc/area/list", "片区档案");
        map1.put("erp/doc/stockWarning/list", "库存预警设置");
        map1.put("erp/report/sale/reportlist/6", "销售明细表");
        map1.put("erp/report/sale/goodslist", "销售汇总(按商品)");
        map1.put("erp/report/sale/reportlist/1", "销售汇总(按客户)");
        map1.put("erp/report/sale/reportlist/3", "销售汇总(按业务员)");
        map1.put("erp/report/sale/reportlist/2", "销售汇总(按仓库)");
        map1.put("erp/report/sale/reportlist/4", "销售汇总（按供应商）");
        map1.put("erp/report/sale/reportlist/5", "热销排行榜");
        map1.put("erp/report/sale/reportlist/7", "销量走势图");
        map1.put("erp/doc/stock/list", "库存表");
        map1.put("erp/report/stocks/goodstockvarylist", "库存变化表-汇总");
        map1.put("erp/report/stocks/reportlist/4", "库存变化表-按单据");
        map1.put("erp/report/stocks/reportlist/1", "库存滞销报表");
        map1.put("erp/report/stocks/reportlist/2", "库存预警表");
        map1.put("erp/report/purchase/reportlist/1", "采购明细表");
        map1.put("erp/report/purchase/reportlist/2", "采购汇总（按商品）");
        map1.put("erp/report/purchase/reportlist/3", "采购汇总（按供应商）");
        map1.put("erp/report/finance/reportlist/1", "客户往来账");
        map1.put("erp/report/finance/reportlist/3", "客户应收款");
        map1.put("erp/report/finance/reportlist/2", "供应商往来账");
        map1.put("erp/report/finance/reportlist/4", "供应商应付款");
        map1.put("erp/report/salesman/reportlist/4", "业务员业绩");
        map1.put("erp/report/salesman/reportlist/1", "业务员提成汇总表");
        map1.put("erp/report/salesman/reportlist/6", "业务员拜访记录");
        map1.put("erp/doc/printinfo/load/update", "APP打印设置");
        map1.put("erp/report/sale/reportlist/9", "销售商品成本利润");
        map1.put("erp/doc/config/list?type=PC_PRINT", "电脑打印设置");
        map1.put("erp/report/salesman/check/list", "业务员对账单");
        map1.put("erp/bill/ordertosale/list?page=xs", "订单转销售单");
        map1.put("erp/doc/initialdebt/list", "应收款初始化");
        map1.put("erp/report/finance/reportlist/5", "预收款余额");
        map1.put("erp/report/finance/reportlist/6", "预付款余额");
        map1.put("erp/bill/expenditure/load/add/0", "费用支出");
        map1.put("erp/bill/expenditure/list", "查看费用支出");
        map1.put("erp/doc/costtype/list", "费用类别");
        map1.put("erp/report/salesman/reportlist/10", "客户活跃度");
        map1.put("erp/doc/config/list?type=COMPANYCONFIG", "公司设置项");
        map1.put("erp/doc/jointgoods/list", "组合商品档案");
        map1.put("erp/bill/joint/load/add/0?type=0", "组合单");
        map1.put("erp/bill/joint/load/add/0?type=1", "拆分单");
        map1.put("erp/bill/joint/list?type=0", "查看组合单");
        map1.put("erp/bill/joint/list?type=1", "查看拆分单");
        map1.put("erp/other/printtemplate/list", "打印模板");
        map1.put("erp/bill/preorder/load/add/0", "订货单");
        map1.put("erp/bill/preorder/list", "查看订货单");
        map1.put("erp/report/ordergoods/reportlist/1", "订货汇总");
        map1.put("erp/doc/goodspriceplan/list", "价格方案");
        map1.put("erp/bill/ordertosale/list?page=zc", "打印装车单");


        urlsRepository.deleteAll();
        map1.forEach((key, value) -> {
            Urls url = new Urls();
            url.setUrl(key);
            url.setName(value);
            urlsRepository.save(url);
        });


        Map<String, String> map2 = new LinkedHashMap<>();
        map2.put("login", "登录");
        map2.put("getSaleBillList", "获取销售单");
        map2.put("getCustomerList", "获取客户");
        map2.put("getStoreList", "获取仓库");
        map2.put("getGoodsList", "获取商品");
        map2.put("addSaleBill", "新增销售单");
        map2.put("getAccount", "获取账号类型");
        map2.put("addWholesalerUser", "新增租户");
        map2.put("getAreaList", "获取片区列表");
        map2.put("getBrandList", "获取品牌列表");
        map2.put("getGroupList", "获取分组列表");
        map2.put("addCustomer", "添加客户");
        map2.put("getDictList", "获取字典列表");
        map2.put("getGoodsTypeList", "获取商品类型列表");
        map2.put("addGoods", "增加商品");
        map2.put("getSalesmanSaleRankingList", "业务员业绩报表");
        map2.put("getHotSaleGoodsList", "商品热销 报表");
        map2.put("getReportSalesTrendList", "销售趋势报表");
        map2.put("getSalesmanSaleAmountList", "业务员销售金额 报表");
        map2.put("getReportGoodsUnsaledList", "商品滞销 报表");
        map2.put("getBrandSaleAmountList", "商品品牌销量分析 报表");
        map2.put("getSalegoodsCostProfit", "商品销售成本利润 报表");
        map2.put("getNewConsumersCountsByDate", "新增客户统计 报表");
        map2.put("getSalesmanVisitChart", "业务员拜访分析 报表");
        map2.put("getSalesmanVisitRankingChart", "业务员拜访排行榜 报表");
        map2.put("getTodaySummary", "今日汇总 报表");
        map2.put("getFinanceConsumerAccounts", "客户应收款 报表");
        map2.put("getConsumerSaleAmount", "客户排行榜");
        map2.put("getSalesmanList", "业务员列表");
        map2.put("getInfoStockList", "库存查看");
        map2.put("getConsumerSyncUpdateData", "客户档案同步");
        map2.put("getGoodsSyncUpdateData", "商品档案同步");
        map2.put("getWarehouseSyncUpdateData", "仓库档案同步");
        map2.put("addConsumerVisitRecord", "添加客户拜访记录");
        map2.put("getAccountSyncUpdateData", "账户同步");
        map2.put("addConsumerVisitRecord", "添加客户拜访记录");
        map2.put("addOrderSaleBill", "新增销售订单");
        map2.put("addPaidBill", "新增退款单");
        map2.put("updateapp", "更新App");
        map2.put("getPrintInfo", "打印信息");
        map2.put("changepass", "修改密码");
        map2.put("restpass", "重置密码");
        map2.put("uploadHead", "修改头像");
        map2.put("updateConsumerLocation", "更新用户位置信息");
        map2.put("addPhoneDeviceInfo", "获得手机信息");
        map2.put("getTodayYesterdayData", "获取昨天今天数据");
        map2.put("getUserInfo", "获取用户信息");
        map2.put("addMoveBill", "新增调拨单");
        map2.put("getDebtSaleBill", "查询销售单欠款");
        map2.put("getTopNGoodsList", "热销商品");
        map2.put("getStockQuantity", "获取库存数量");
        map2.put("getStockQuantityByOne", "获取库存数量One");
        map2.put("getStockQuantityCount", "获取库存总条数");
        map2.put("getRights", "获取用户手机端权限");
        map2.put("getSalesmanCheck", "今日汇总 new");
        map2.put("getTodaySummaryDetail", "今日汇总详情");
        map2.put("getVercode", "获取短信");
        map2.put("getExperienceAccout", "获取体验账号");
        map2.put("saveExperienceAccout", "保存手机号获得体验账号");
        map2.put("getMoveDetailList", "快速调拨");
        map2.put("getSaleReturnMoveDetailList", "快速调拨退货商品");
        map2.put("getPrecisionConfig", "精度配置");
        map2.put("validateUser", "用户校验");
        map2.put("getConsumerMore", "获取客户更多信息");
        map2.put("getGlobalConfig", "查询全局配置");
        map2.put("getSysConfig", "查询系统级全局配置");
        map2.put("validateSafeToken", "用户token验证");
        map2.put("sendPhoneCode", "发送验证码");
        map2.put("validatePhoneCode", "手机验证码校验");
        map2.put("getSafeToken", "获取手机Token");
        map2.put("getBillListByType", "通过类型获取单据");
        map2.put("getBillInfoById", "通过ID获取单据");
        map2.put("getCostType", "成本类型");
        map2.put("addExpenditureBill", "添加支出方案");
        map2.put("getGoodsPLSyncUpdateData", "商品价格方案同步");
        map2.put("getGoodsById", "加载单个商品信息");
        map2.put("getConsumerById", "加载单个客户信息");
        map2.put("getGoodsPrices", "加载商品价格（记忆价格）");
        map2.put("getAlarmMessage", "获取告警消息");
        map2.put("getNewsInfoMessage", "获取新闻资讯消息");
        map2.put("getMessagestockwarnList", "获取库存告警");

        actionsRepository.deleteAll();
        map2.forEach((key, value) -> {
            Actions actions = new Actions();
            actions.setAction(key);
            actions.setName(value);
            actionsRepository.save(actions);
        });


        userRepository.deleteAll();
        List<String> phones =
                Arrays.asList(new String[] {"13913364179", "18061296485", "13851938254"});
        final Date d = new Date();
        phones.forEach(phonenum -> {
            User user = new User();
            user.setUsername(phonenum);
            user.setCreatetime(d);
            userRepository.save(user);
        });



        // map1.
    }

}
