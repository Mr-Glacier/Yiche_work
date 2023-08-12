import Dao.son_sqlserver;
import Entity.Bean_T_YConfig;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Parameter {
    public static void main(String[] args) {
        try {
            Method_web M = new Method_web();
            //https://car.yiche.com/xinaodia6l/
            String ParURL = "https://car.yiche.com/aodia8l/peizhi/";
            String JsonConent = M.Method_FindYCWB(ParURL);


            System.out.println(JsonConent);
//            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File("F:/A_URL网页保存/s7.txt"), true), 165537);
//            bufferedOutputStream.write(JsonConent.getBytes());

            JSONObject config1 = JSONObject.parseObject(JsonConent);
            //System.out.println(config1.size());
            JSONArray configData = config1.getJSONArray("data");
            //System.out.println(configData.size());
//            for (int i = 0; i < configData.size(); i++) {
////            Object obj = configData.get(i);
////            JSONObject jsonObject = (JSONObject) obj;
//                JSONObject config2 = configData.getJSONObject(i);
//                String text1 = config2.getString("name");
//                //System.out.println(text1);
//                JSONArray config3 = config2.getJSONArray("items");
//                //System.out.println(config3.size());
//                for (int j = 0; j < config3.size(); j++) {
//                    JSONObject config4 = config3.getJSONObject(j);
//                    String carname = text1 + "____" + config4.getString("name");
//                    System.out.println(carname);
//                    JSONArray config5 = config4.getJSONArray("paramValues");
//                    //System.out.println(config5.size());
//                    for (int k = 0; k < config5.size(); k++) {
//                        JSONObject config6 = config5.getJSONObject(k);
//                        String VersionID = config5.getJSONObject(k).getString("id");
//                        JSONArray config7 = config6.getJSONArray("subList");
////                        System.out.println("Begin____________________");
////                        System.out.println("这是版本车辆ID: " + VersionID);
//                        for (int l = 0; l < config7.size(); l++) {
//                            if (((JSONObject) config7.get(l)).getString("desc") == null) {
//                                String message1 = ((JSONObject) config7.get(l)).getString("value");
////                                System.out.println(carname + "     " + message1);
////                                System.out.println("==================================");
//                            } else {
//                                String message1 = ((JSONObject) config7.get(l)).getString("value");
//                                String message2 = ((JSONObject) config7.get(l)).getString("desc");
////                                System.out.println(carname + "      " + message1);
////                                System.out.println(carname + "      " + message2);
////                                System.out.println("==================================");
//                            }
//                        }
//                    }
//                }
//            }


            //数据的填充
            Map<String, String> columnMap = new HashMap<>();
            columnMap.put("基本信息____车款名称", "C_基本信息____车款名称");
            columnMap.put("基本信息____厂商指导价", "C_基本信息____厂商指导价");
            columnMap.put("基本信息____城市参考价", "C_基本信息____城市参考价");
            columnMap.put("基本信息____厂商", "C_基本信息____厂商");
            columnMap.put("基本信息____级别", "C_基本信息____级别");
            columnMap.put("基本信息____能源类型", "C_基本信息____能源类型");
            columnMap.put("基本信息____环保标准", "C_基本信息____环保标准");
            columnMap.put("基本信息____上市时间", "C_基本信息____上市时间");
            columnMap.put("基本信息____发动机", "C_基本信息____发动机");
            columnMap.put("基本信息____最大功率/最大扭矩", "C_基本信息____最大功率_最大扭矩");
            columnMap.put("基本信息____变速箱", "C_基本信息____变速箱");
            columnMap.put("基本信息____长*宽*高[mm]", "C_基本信息____长_宽_高_mm");
            columnMap.put("基本信息____车身结构", "C_基本信息____车身结构");
            columnMap.put("基本信息____最高车速[km/h]", "C_基本信息____最高车速_km_h");
            columnMap.put("基本信息____官方0-100km/h加速[s]", "C_基本信息____官方0_100km_h加速_s");
            columnMap.put("基本信息____WLTC综合油耗[L/100km]", "C_基本信息____WLTC综合油耗_L_100km");
            columnMap.put("基本信息____NEDC综合油耗[L/100km]", "C_基本信息____NEDC综合油耗_L_100km");
            columnMap.put("基本信息____整车质保", "C_基本信息____整车质保");
            columnMap.put("基本信息____外观颜色", "C_基本信息____外观颜色");
            columnMap.put("基本信息____内饰颜色", "C_基本信息____内饰颜色");
            columnMap.put("车身____长度[mm]", "C_车身____长度_mm");
            columnMap.put("车身____宽度[mm]", "C_车身____宽度_mm");
            columnMap.put("车身____高度[mm]", "C_车身____高度_mm");
            columnMap.put("车身____轴距[mm]", "C_车身____轴距_mm");
            columnMap.put("车身____前轮距[mm]", "C_车身____前轮距_mm");
            columnMap.put("车身____后轮距[mm]", "C_车身____后轮距_mm");
            columnMap.put("车身____车门开启方式", "C_车身____车门开启方式");
            columnMap.put("车身____车门数", "C_车身____车门数");
            columnMap.put("车身____座位数", "C_车身____座位数");
            columnMap.put("车身____接近角[°]", "C_车身____接近角_");
            columnMap.put("车身____离去角[°]", "C_车身____离去角_");
            columnMap.put("车身____满载最小离地间隙[mm]", "C_车身____满载最小离地间隙_mm");
            columnMap.put("车身____最小转弯半径[m]", "C_车身____最小转弯半径_m");
            columnMap.put("车身____油箱容积[L]", "C_车身____油箱容积_L");
            columnMap.put("车身____官方后备厢容积[L]", "C_车身____官方后备厢容积_L");
            columnMap.put("车身____整备质量[kg]", "C_车身____整备质量_kg");
            columnMap.put("车身____满载质量[kg]", "C_车身____满载质量_kg");
            columnMap.put("发动机____发动机型号", "C_发动机____发动机型号");
            columnMap.put("发动机____排量[mL]", "C_发动机____排量_mL");
            columnMap.put("发动机____排量[L]", "C_发动机____排量_L");
            columnMap.put("发动机____进气形式", "C_发动机____进气形式");
            columnMap.put("发动机____发动机布局", "C_发动机____发动机布局");
            columnMap.put("发动机____气缸排列形式", "C_发动机____气缸排列形式");
            columnMap.put("发动机____气缸数[个]", "C_发动机____气缸数_个");
            columnMap.put("发动机____每缸气门数[个]", "C_发动机____每缸气门数_个");
            columnMap.put("发动机____配气机构", "C_发动机____配气机构");
            columnMap.put("发动机____最大功率[kW]", "C_发动机____最大功率_kW");
            columnMap.put("发动机____最大马力[Ps]", "C_发动机____最大马力_Ps");
            columnMap.put("发动机____最大扭矩[N·m]", "C_发动机____最大扭矩_N_m");
            columnMap.put("发动机____最大净功率[kW]", "C_发动机____最大净功率_kW");
            columnMap.put("发动机____燃油标号", "C_发动机____燃油标号");
            columnMap.put("发动机____供油方式", "C_发动机____供油方式");
            columnMap.put("发动机____缸盖材料", "C_发动机____缸盖材料");
            columnMap.put("发动机____缸体材料", "C_发动机____缸体材料");
            columnMap.put("发动机____环保标准", "C_发动机____环保标准");
            columnMap.put("变速箱____变速箱描述", "C_变速箱____变速箱描述");
            columnMap.put("变速箱____变速箱类型", "C_变速箱____变速箱类型");
            columnMap.put("变速箱____挡位数", "C_变速箱____挡位数");
            columnMap.put("底盘转向____驱动形式", "C_底盘转向____驱动形式");
            columnMap.put("底盘转向____四驱形式", "C_底盘转向____四驱形式");
            columnMap.put("底盘转向____中央差速器结构", "C_底盘转向____中央差速器结构");
            columnMap.put("底盘转向____前悬架类型", "C_底盘转向____前悬架类型");
            columnMap.put("底盘转向____后悬架类型", "C_底盘转向____后悬架类型");
            columnMap.put("底盘转向____助力类型", "C_底盘转向____助力类型");
            columnMap.put("底盘转向____可调悬架功能", "C_底盘转向____可调悬架功能");
            columnMap.put("底盘转向____可调悬架种类", "C_底盘转向____可调悬架种类");
            columnMap.put("底盘转向____车体结构", "C_底盘转向____车体结构");
            columnMap.put("底盘转向____限滑差速器", "C_底盘转向____限滑差速器");
            columnMap.put("底盘转向____差速锁", "C_底盘转向____差速锁");
            columnMap.put("车轮制动____前制动器类型", "C_车轮制动____前制动器类型");
            columnMap.put("车轮制动____后制动器类型", "C_车轮制动____后制动器类型");
            columnMap.put("车轮制动____驻车制动类型", "C_车轮制动____驻车制动类型");
            columnMap.put("车轮制动____前轮胎规格", "C_车轮制动____前轮胎规格");
            columnMap.put("车轮制动____后轮胎规格", "C_车轮制动____后轮胎规格");
            columnMap.put("车轮制动____备胎", "C_车轮制动____备胎");
            columnMap.put("车轮制动____备胎放置方式", "C_车轮制动____备胎放置方式");
            columnMap.put("主动安全____ABS防抱死", "C_主动安全____ABS防抱死");
            columnMap.put("主动安全____制动力分配(EBD/CBC等)", "C_主动安全____制动力分配_EBD_CBC等");
            columnMap.put("主动安全____制动辅助(BA/EBA/BAS等)", "C_主动安全____制动辅助_BA_EBA_BAS等");
            columnMap.put("主动安全____牵引力控制(ASR/TCS/TRC等)", "C_主动安全____牵引力控制_ASR_TCS_TRC等");
            columnMap.put("主动安全____车身稳定控制(ESP/DSC/VSC等)", "C_主动安全____车身稳定控制_ESP_DSC_VSC等");
            columnMap.put("主动安全____安全带未系提醒", "C_主动安全____安全带未系提醒");
            columnMap.put("主动安全____胎压监测", "C_主动安全____胎压监测");
            columnMap.put("主动安全____车内生物监测", "C_主动安全____车内生物监测");
            columnMap.put("被动安全____主驾驶安全气囊", "C_被动安全____主驾驶安全气囊");
            columnMap.put("被动安全____副驾驶安全气囊", "C_被动安全____副驾驶安全气囊");
            columnMap.put("被动安全____第一排侧气囊", "C_被动安全____第一排侧气囊");
            columnMap.put("被动安全____第二排侧气囊", "C_被动安全____第二排侧气囊");
            columnMap.put("被动安全____侧安全气帘", "C_被动安全____侧安全气帘");
            columnMap.put("被动安全____膝部气囊", "C_被动安全____膝部气囊");
            columnMap.put("被动安全____副驾驶座垫式气囊", "C_被动安全____副驾驶座垫式气囊");
            columnMap.put("被动安全____后排安全带式气囊", "C_被动安全____后排安全带式气囊");
            columnMap.put("被动安全____后排座椅防下滑气囊", "C_被动安全____后排座椅防下滑气囊");
            columnMap.put("被动安全____中央安全气囊", "C_被动安全____中央安全气囊");
            columnMap.put("被动安全____第二排正向气囊", "C_被动安全____第二排正向气囊");
            columnMap.put("被动安全____儿童座椅接口", "C_被动安全____儿童座椅接口");
            columnMap.put("被动安全____被动行人保护", "C_被动安全____被动行人保护");
            columnMap.put("被动安全____缺气保用轮胎", "C_被动安全____缺气保用轮胎");
            columnMap.put("辅助/操控配置____前驻车雷达", "C_辅助_操控配置____前驻车雷达");
            columnMap.put("辅助/操控配置____后驻车雷达", "C_辅助_操控配置____后驻车雷达");
            columnMap.put("辅助/操控配置____倒车车侧预警系统", "C_辅助_操控配置____倒车车侧预警系统");
            columnMap.put("辅助/操控配置____开门碰撞预警", "C_辅助_操控配置____开门碰撞预警");
            columnMap.put("辅助/操控配置____驾驶辅助影像", "C_辅助_操控配置____驾驶辅助影像");
            columnMap.put("辅助/操控配置____底盘透视", "C_辅助_操控配置____底盘透视");
            columnMap.put("辅助/操控配置____巡航系统", "C_辅助_操控配置____巡航系统");
            columnMap.put("辅助/操控配置____驾驶模式选择", "C_辅助_操控配置____驾驶模式选择");
            columnMap.put("辅助/操控配置____疲劳提醒", "C_辅助_操控配置____疲劳提醒");
            columnMap.put("辅助/操控配置____自动泊车", "C_辅助_操控配置____自动泊车");
            columnMap.put("辅助/操控配置____遥控泊车", "C_辅助_操控配置____遥控泊车");
            columnMap.put("辅助/操控配置____远程召唤", "C_辅助_操控配置____远程召唤");
            columnMap.put("辅助/操控配置____发动机启停", "C_辅助_操控配置____发动机启停");
            columnMap.put("辅助/操控配置____自动驻车", "C_辅助_操控配置____自动驻车");
            columnMap.put("辅助/操控配置____上坡辅助", "C_辅助_操控配置____上坡辅助");
            columnMap.put("辅助/操控配置____陡坡缓降", "C_辅助_操控配置____陡坡缓降");
            columnMap.put("辅助/操控配置____夜视系统", "C_辅助_操控配置____夜视系统");
            columnMap.put("辅助/操控配置____可变转向比", "C_辅助_操控配置____可变转向比");
            columnMap.put("辅助/操控配置____整体主动转向系统", "C_辅助_操控配置____整体主动转向系统");
            columnMap.put("辅助/操控配置____涉水感应系统", "C_辅助_操控配置____涉水感应系统");
            columnMap.put("辅助驾驶功能____并线辅助(BSM/BSD)", "C_辅助驾驶功能____并线辅助_BSM_BSD");
            columnMap.put("辅助驾驶功能____车道偏离预警(LDWS)", "C_辅助驾驶功能____车道偏离预警_LDWS");
            columnMap.put("辅助驾驶功能____车道保持(LKAS)", "C_辅助驾驶功能____车道保持_LKAS");
            columnMap.put("辅助驾驶功能____车道居中保持", "C_辅助驾驶功能____车道居中保持");
            columnMap.put("辅助驾驶功能____道路交通标识识别", "C_辅助驾驶功能____道路交通标识识别");
            columnMap.put("辅助驾驶功能____前方碰撞预警", "C_辅助驾驶功能____前方碰撞预警");
            columnMap.put("辅助驾驶功能____后方碰撞预警", "C_辅助驾驶功能____后方碰撞预警");
            columnMap.put("辅助驾驶功能____主动刹车", "C_辅助驾驶功能____主动刹车");
            columnMap.put("辅助驾驶功能____自动变道辅助", "C_辅助驾驶功能____自动变道辅助");
            columnMap.put("辅助驾驶功能____匝道自动驶出/入", "C_辅助驾驶功能____匝道自动驶出_入");
            columnMap.put("辅助驾驶功能____倒车循迹", "C_辅助驾驶功能____倒车循迹");
            columnMap.put("辅助驾驶硬件____驾驶辅助级别", "C_辅助驾驶硬件____驾驶辅助级别");
            columnMap.put("辅助驾驶硬件____超声波雷达", "C_辅助驾驶硬件____超声波雷达");
            columnMap.put("辅助驾驶硬件____环视摄像头", "C_辅助驾驶硬件____环视摄像头");
            columnMap.put("辅助驾驶硬件____车内摄像头", "C_辅助驾驶硬件____车内摄像头");
            columnMap.put("外部配置____天窗类型", "C_外部配置____天窗类型");
            columnMap.put("外部配置____光感天幕", "C_外部配置____光感天幕");
            columnMap.put("外部配置____运动外观套件", "C_外部配置____运动外观套件");
            columnMap.put("外部配置____电动扰流板", "C_外部配置____电动扰流板");
            columnMap.put("外部配置____轮圈材质", "C_外部配置____轮圈材质");
            columnMap.put("外部配置____电吸门", "C_外部配置____电吸门");
            columnMap.put("外部配置____自动开合车门", "C_外部配置____自动开合车门");
            columnMap.put("外部配置____无框车门", "C_外部配置____无框车门");
            columnMap.put("外部配置____侧滑门", "C_外部配置____侧滑门");
            columnMap.put("外部配置____车顶行李架", "C_外部配置____车顶行李架");
            columnMap.put("外部配置____钥匙类型", "C_外部配置____钥匙类型");
            columnMap.put("外部配置____无钥匙启动", "C_外部配置____无钥匙启动");
            columnMap.put("外部配置____无钥匙进入", "C_外部配置____无钥匙进入");
            columnMap.put("外部配置____隐藏电动门把手", "C_外部配置____隐藏电动门把手");
            columnMap.put("外部配置____主动闭合式进气格栅", "C_外部配置____主动闭合式进气格栅");
            columnMap.put("外部配置____电动尾门", "C_外部配置____电动尾门");
            columnMap.put("外部配置____尾门位置记忆", "C_外部配置____尾门位置记忆");
            columnMap.put("外部配置____感应尾门", "C_外部配置____感应尾门");
            columnMap.put("外部配置____尾门玻璃独立开启", "C_外部配置____尾门玻璃独立开启");
            columnMap.put("外部配置____车侧脚踏板", "C_外部配置____车侧脚踏板");
            columnMap.put("外部配置____低速行车警示音", "C_外部配置____低速行车警示音");
            columnMap.put("外部配置____哨兵(千里眼)模式", "C_外部配置____哨兵_千里眼模式");
            columnMap.put("内部配置____方向盘材质", "C_内部配置____方向盘材质");
            columnMap.put("内部配置____方向盘调节", "C_内部配置____方向盘调节");
            columnMap.put("内部配置____换挡形式", "C_内部配置____换挡形式");
            columnMap.put("内部配置____多功能方向盘", "C_内部配置____多功能方向盘");
            columnMap.put("内部配置____方向盘换挡", "C_内部配置____方向盘换挡");
            columnMap.put("内部配置____方向盘加热", "C_内部配置____方向盘加热");
            columnMap.put("内部配置____行车电脑显示屏", "C_内部配置____行车电脑显示屏");
            columnMap.put("内部配置____全液晶仪表盘", "C_内部配置____全液晶仪表盘");
            columnMap.put("内部配置____仪表屏幕尺寸[英寸]", "C_内部配置____仪表屏幕尺寸_英寸");
            columnMap.put("内部配置____HUD抬头显示", "C_内部配置____HUD抬头显示");
            columnMap.put("内部配置____内置行车记录仪", "C_内部配置____内置行车记录仪");
            columnMap.put("内部配置____手机无线充电", "C_内部配置____手机无线充电");
            columnMap.put("内部配置____ETC装置", "C_内部配置____ETC装置");
            columnMap.put("内部配置____主动降噪", "C_内部配置____主动降噪");
            columnMap.put("内部配置____电动可调踏板", "C_内部配置____电动可调踏板");
            columnMap.put("内部配置____单踏板模式", "C_内部配置____单踏板模式");
            columnMap.put("座椅配置____座椅材质", "C_座椅配置____座椅材质");
            columnMap.put("座椅配置____运动风格座椅", "C_座椅配置____运动风格座椅");
            columnMap.put("座椅配置____主驾座椅电动调节", "C_座椅配置____主驾座椅电动调节");
            columnMap.put("座椅配置____副驾座椅电动调节", "C_座椅配置____副驾座椅电动调节");
            columnMap.put("座椅配置____主驾座椅调节方式", "C_座椅配置____主驾座椅调节方式");
            columnMap.put("座椅配置____副驾座椅调节方式", "C_座椅配置____副驾座椅调节方式");
            columnMap.put("座椅配置____第一排座椅功能", "C_座椅配置____第一排座椅功能");
            columnMap.put("座椅配置____老板键", "C_座椅配置____老板键");
            columnMap.put("座椅配置____第二排座椅电动调节", "C_座椅配置____第二排座椅电动调节");
            columnMap.put("座椅配置____第二排座椅调节方式", "C_座椅配置____第二排座椅调节方式");
            columnMap.put("座椅配置____第二排座椅功能", "C_座椅配置____第二排座椅功能");
            columnMap.put("座椅配置____中央扶手", "C_座椅配置____中央扶手");
            columnMap.put("座椅配置____座椅布局", "C_座椅配置____座椅布局");
            columnMap.put("座椅配置____后排座椅放倒方式", "C_座椅配置____后排座椅放倒方式");
            columnMap.put("座椅配置____后排座椅电动放倒", "C_座椅配置____后排座椅电动放倒");
            columnMap.put("座椅配置____后排杯架", "C_座椅配置____后排杯架");
            columnMap.put("座椅配置____加热/制冷杯架", "C_座椅配置____加热_制冷杯架");
            columnMap.put("座椅配置____后排折叠桌板", "C_座椅配置____后排折叠桌板");
            columnMap.put("车机/互联____中控彩色屏幕", "C_车机_互联____中控彩色屏幕");
            columnMap.put("车机/互联____中控屏幕尺寸[英寸]", "C_车机_互联____中控屏幕尺寸_英寸");
            columnMap.put("车机/互联____副驾屏幕", "C_车机_互联____副驾屏幕");
            columnMap.put("车机/互联____车机系统", "C_车机_互联____车机系统");
            columnMap.put("车机/互联____车机网络", "C_车机_互联____车机网络");
            columnMap.put("车机/互联____OTA升级", "C_车机_互联____OTA升级");
            columnMap.put("车机/互联____车机WiFi功能", "C_车机_互联____车机WiFi功能");
            columnMap.put("车机/互联____车载导航地图", "C_车机_互联____车载导航地图");
            columnMap.put("车机/互联____V2X通讯", "C_车机_互联____V2X通讯");
            columnMap.put("车机/互联____道路救援呼叫", "C_车机_互联____道路救援呼叫");
            columnMap.put("车机/互联____蓝牙/车载电话", "C_车机_互联____蓝牙_车载电话");
            columnMap.put("车机/互联____手机互联/映射", "C_车机_互联____手机互联_映射");
            columnMap.put("车机/互联____语音分区唤醒", "C_车机_互联____语音分区唤醒");
            columnMap.put("车机/互联____语音免唤醒", "C_车机_互联____语音免唤醒");
            columnMap.put("车机/互联____语音连续识别", "C_车机_互联____语音连续识别");
            columnMap.put("车机/互联____语音识别控制功能", "C_车机_互联____语音识别控制功能");
            columnMap.put("车机/互联____手势控制", "C_车机_互联____手势控制");
            columnMap.put("车机/互联____面部识别", "C_车机_互联____面部识别");
            columnMap.put("车机/互联____远程控制功能", "C_车机_互联____远程控制功能");
            columnMap.put("影音娱乐____车载APP应用市场", "C_影音娱乐____车载APP应用市场");
            columnMap.put("影音娱乐____车载CD/DVD", "C_影音娱乐____车载CD_DVD");
            columnMap.put("影音娱乐____多媒体/充电接口", "C_影音娱乐____多媒体_充电接口");
            columnMap.put("影音娱乐____USB/Type-C接口数量", "C_影音娱乐____USB_Type_C接口数量");
            columnMap.put("影音娱乐____后排控制多媒体", "C_影音娱乐____后排控制多媒体");
            columnMap.put("影音娱乐____座舱220V/230V电源", "C_影音娱乐____座舱220V_230V电源");
            columnMap.put("影音娱乐____行李厢电源接口", "C_影音娱乐____行李厢电源接口");
            columnMap.put("影音娱乐____音响品牌", "C_影音娱乐____音响品牌");
            columnMap.put("影音娱乐____扬声器数量", "C_影音娱乐____扬声器数量");
            columnMap.put("影音娱乐____音响试听", "C_影音娱乐____音响试听");
            columnMap.put("灯光功能____近光灯光源", "C_灯光功能____近光灯光源");
            columnMap.put("灯光功能____远光灯光源", "C_灯光功能____远光灯光源");
            columnMap.put("灯光功能____灯光特色功能", "C_灯光功能____灯光特色功能");
            columnMap.put("灯光功能____LED日间行车灯", "C_灯光功能____LED日间行车灯");
            columnMap.put("灯光功能____大灯功能", "C_灯光功能____大灯功能");
            columnMap.put("灯光功能____转向辅助灯", "C_灯光功能____转向辅助灯");
            columnMap.put("灯光功能____前雾灯", "C_灯光功能____前雾灯");
            columnMap.put("灯光功能____大灯清洗装置", "C_灯光功能____大灯清洗装置");
            columnMap.put("灯光功能____车内氛围灯", "C_灯光功能____车内氛围灯");
            columnMap.put("玻璃/后视镜____第一排电动车窗", "C_玻璃_后视镜____第一排电动车窗");
            columnMap.put("玻璃/后视镜____第二排电动车窗", "C_玻璃_后视镜____第二排电动车窗");
            columnMap.put("玻璃/后视镜____车窗一键升降", "C_玻璃_后视镜____车窗一键升降");
            columnMap.put("玻璃/后视镜____车窗防夹手功能", "C_玻璃_后视镜____车窗防夹手功能");
            columnMap.put("玻璃/后视镜____多层隔音玻璃", "C_玻璃_后视镜____多层隔音玻璃");
            columnMap.put("玻璃/后视镜____外后视镜功能", "C_玻璃_后视镜____外后视镜功能");
            columnMap.put("玻璃/后视镜____内后视镜功能", "C_玻璃_后视镜____内后视镜功能");
            columnMap.put("玻璃/后视镜____后排侧隐私玻璃", "C_玻璃_后视镜____后排侧隐私玻璃");
            columnMap.put("玻璃/后视镜____后排侧遮阳帘", "C_玻璃_后视镜____后排侧遮阳帘");
            columnMap.put("玻璃/后视镜____后风挡遮阳帘", "C_玻璃_后视镜____后风挡遮阳帘");
            columnMap.put("玻璃/后视镜____车内化妆镜", "C_玻璃_后视镜____车内化妆镜");
            columnMap.put("玻璃/后视镜____感应雨刷功能", "C_玻璃_后视镜____感应雨刷功能");
            columnMap.put("玻璃/后视镜____后雨刷", "C_玻璃_后视镜____后雨刷");
            columnMap.put("玻璃/后视镜____可加热喷水嘴", "C_玻璃_后视镜____可加热喷水嘴");
            columnMap.put("空调/制冷____第一排空调", "C_空调_制冷____第一排空调");
            columnMap.put("空调/制冷____第二排空调", "C_空调_制冷____第二排空调");
            columnMap.put("空调/制冷____第三排空调", "C_空调_制冷____第三排空调");
            columnMap.put("空调/制冷____空气质量监测", "C_空调_制冷____空气质量监测");
            columnMap.put("空调/制冷____车内PM2.5过滤装置", "C_空调_制冷____车内PM2_5过滤装置");
            columnMap.put("空调/制冷____车载空气净化器", "C_空调_制冷____车载空气净化器");
            columnMap.put("空调/制冷____负离子发生器", "C_空调_制冷____负离子发生器");
            columnMap.put("空调/制冷____香氛系统", "C_空调_制冷____香氛系统");
            columnMap.put("空调/制冷____车载冰箱", "C_空调_制冷____车载冰箱");


            ArrayList<Bean_T_YConfig> resultList = new ArrayList<Bean_T_YConfig>();
            JSONArray configB = configData.getJSONObject(0).getJSONArray("items");
            JSONArray configD = configB.getJSONObject(0).getJSONArray("paramValues");
            System.out.println(configD.size());
            for (int r = 0; r < configD.size(); r++) {
                String PID = configD.getJSONObject(r).getString("id");
                Bean_T_YConfig bean = new Bean_T_YConfig();
                bean.set_C_PID(PID);
                resultList.add(bean);
            }
            //System.out.println(resultList.size());
            //这里是获取奥迪A4L下的12个车型的配置信息
            for (int i = 0; i < resultList.size(); i++) {

                for (int j = 0; j < configData.size(); j++) {
                    JSONObject configIns1 = configData.getJSONObject(j);
                    String title1 = configIns1.getString("name");
                    System.out.println(title1);
                    JSONArray configIns2 = configIns1.getJSONArray("items");
                    System.out.println(configIns2.size());
                    for (int k = 0; k < configIns2.size(); k++) {
                        JSONObject configIns3 = configIns2.getJSONObject(k);
                        String configName = title1 + "____" + configIns3.getString("name");
                        JSONArray configIns4 = configIns3.getJSONArray("paramValues");
                        //System.out.println(configIns4.size());
                        String PID = configIns4.getJSONObject(i).getString("id");
                        System.out.println("================================");
                        System.out.println("这里是车型的ID:   " + PID);
                        System.out.println("++++++++++++++++++++++++++++++++");
                        JSONObject configIns5 = configIns4.getJSONObject(i);
                        JSONArray configIns6 = configIns5.getJSONArray("subList");

                        if (configIns6.size() >= 1) {
                            String valueList = "";
                            for (int l = 0; l < configIns6.size(); l++) {
                                JSONObject configIns7 = configIns6.getJSONObject(l);
                                String desc = configIns7.getString("desc");
                                if (desc == null) {
                                    String value = configIns7.getString("value");
                                    valueList += value;
                                } else {
                                    String value = configIns7.getString("value") + desc;
                                    valueList += value;
                                }
                            }
                            System.out.println(configName);
                            System.out.println(valueList);
                            if (resultList.get(i).get_C_PID().equals(PID)) {
                                Class c = resultList.get(i).getClass();
                                Field field = c.getDeclaredField(columnMap.get(configName));
                                field.setAccessible(true);
                                field.set(resultList.get(i), valueList);
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
//
//            System.out.println(resultList.get(10).get_C_基本信息____内饰颜色() + "\n");
//            for (int i = 0; i < resultList.size(); i++) {
//                System.out.print(resultList.get(i).get_C_PID() + "\n");
//                System.out.println(resultList.get(i).get_C_基本信息____车款名称() + "\n");
//                System.out.println(resultList.get(i).get_C_空调_制冷____车载冰箱() + "\n");
//                son_sqlserver daoconfig = new son_sqlserver("T_YConfig", 3);
//                //daoconfig.Method_Insert(resultList.get(i));
//            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
