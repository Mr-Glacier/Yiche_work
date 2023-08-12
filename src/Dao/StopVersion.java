package Dao;

import Entity.Bean_T_YVersion;
import Entity.Bean_YiCheConfig;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import Dao.son_sqlserver;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;

public class StopVersion {

    public void MyStopFind(String URL, String ModelID, String ModelName) {
        try {
            String stopUrl = URL;
            Document documentstop = Jsoup.parse(new URL(stopUrl).openStream(), "UTF-8", stopUrl);

            Elements stopItems1 = documentstop.select("script[type=text/javascript]");
            String text = stopItems1.get(7).toString();
            if (!text.equals("")){
                String keyWord = URLDecoder.decode(text, "utf-8");
                String startString = keyWord.substring(keyWord.indexOf("\"stopSaleCarList\":"));
                if (!startString.equals("")){
                    String fintext = startString.substring(18, startString.indexOf("}]}]}]"));
                    String maintext = fintext + "}]}]}]";
                    //System.out.println(maintext);
//
//                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("F:/A_Stop/" + ModelName.replace(":", "_") + ".text", true), 165537);//165537
//                    bufferedOutputStream.write(maintext.getBytes());
//                    bufferedOutputStream.close();
                    JSONArray jsonArr = JSONArray.parseArray(maintext);
                    System.out.println(jsonArr.size());
                    for (int i = 0; i < jsonArr.size(); i++) {
                        JSONObject First1 = jsonArr.getJSONObject(i);
                        JSONArray second = First1.getJSONArray("powerList");
                        System.out.println(second.size());
                        for (int j = 0; j < second.size(); j++) {
                            JSONObject tride = second.getJSONObject(j);
                            JSONArray four = tride.getJSONArray("carList");
                            //System.out.println("carList里面所含对象"+four.size());
                            for (int k = 0; k < four.size(); k++) {
                                JSONObject five = four.getJSONObject(k);
                                String VersionID = five.getString("id");
                                String VersionName = five.getString("name");
                                String State = "停售";
                                //https://car.yiche.com/aodia3-3999/m152951/
                                String VersionURL = stopUrl + "m" + five.getString("id") + "/";
//                                System.out.println(VersionID);
//                                System.out.println(VersionName);
//                                System.out.println(VersionURL);
//                                System.out.println(State);
                                System.out.println("============");
                                Bean_T_YVersion beanTYVersion = new Bean_T_YVersion();
                                beanTYVersion.set_C_VersionID(VersionID);
                                beanTYVersion.set_C_VersionName(VersionName);
                                beanTYVersion.set_C_VersionURL(VersionURL);
                                beanTYVersion.set_C_ModelID(ModelID);
                                beanTYVersion.set_C_State(State);
                                //son_sqlserver daoStopVersion = new son_sqlserver("T_YVersion", 2);
                                //daoStopVersion.Method_Insert2(beanTYVersion);
                                Method_web_Stop M = new Method_web_Stop();
                                String DownURL = VersionURL+"peizhi/";
                                System.out.println(DownURL);
                                String JsonConent = M.Method_FindYCWB(DownURL);
                               // System.out.println(JsonConent);

                                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("F:/A_StopConfig/"+VersionName.replace(":", "_")+"_Config.text", true), 662148);//165537
                                bufferedOutputStream.write(JsonConent.getBytes());
                                bufferedOutputStream.close();
                                Thread.sleep(100);
//                                JSONObject config1 = JSONObject.parseObject(JsonConent);
//                                JSONArray configData = config1.getJSONArray("data");

//                                if (!(configData.size() == 0)) {
//                                    ArrayList<Bean_YiCheConfig> resultList = new ArrayList<Bean_YiCheConfig>();
//                                    JSONArray configB = configData.getJSONObject(0).getJSONArray("items");
//                                    JSONArray configD = configB.getJSONObject(0).getJSONArray("paramValues");
//                                    System.out.println(configD.size());
//                                    for (int r = 0; r < configD.size(); r++) {
//                                        String PID = configD.getJSONObject(r).getString("id");
//                                        Bean_YiCheConfig bean = new Bean_YiCheConfig();
//                                        bean.set_C_PID(PID);
//                                        resultList.add(bean);
//                                    }
//                                    //第一个for循环是循环车辆个数
//                                    for (int s = 0; s < resultList.size(); s++) {
//                                        //第二个循环是循环data里面的配置大项
//                                        for (int t = 0; t < configData.size(); t++) {
//                                            JSONObject configIns1 = configData.getJSONObject(t);
//                                            String title1 = configIns1.getString("name");
//                                            System.out.println(title1);
//                                            if (title1.equals("选配包") || title1.equals("特色配置")) {
//                                                if (title1.equals("选配包")) {
//                                                    //里面有多少子项
//                                                    JSONArray configIns2 = configIns1.getJSONArray("items");
//                                                    String specilNameList = "";
//                                                    String connectList = "";
//                                                    String priceList = "";
//                                                    for (int l = 0; l < configIns2.size(); l++) {
//                                                        JSONObject configIns3 = configIns2.getJSONObject(l);
//                                                        String specificName = configIns3.getString("name");
//
//                                                        specilNameList += specificName+"__";
//                                                        //specificName是选装包具体名称
//                                                        String neirong = configIns3.getString("desc");
//                                                        connectList += neirong+"__";
//                                                        JSONArray configIns4 = configIns3.getJSONArray("paramValues");
//                                                        JSONObject configIns5 = configIns4.getJSONObject(s);
//                                                        String PID = configIns5.getString("id");
//                                                        JSONArray configIns6 = configIns5.getJSONArray("subList");
//
//                                                        for (int m = 0; m < configIns6.size(); m++) {
//                                                            JSONObject configIns7 = configIns6.getJSONObject(m);
//                                                            String value = configIns7.getString("value");
//                                                            String price = configIns7.getString("price");
//                                                            String desc = configIns7.getString("desc");
//                                                            if (desc == null || value.equals("-")) {
//                                                                priceList = "-" + "\n";
//                                                            } else {
//                                                                priceList += value + desc + price + "\n";
//                                                            }
//                                                        }
//
//                                                        if (resultList.get(s).get_C_PID().equals(PID)) {
//                                                            Class c = resultList.get(s).getClass();
//                                                            Field field = c.getDeclaredField(columnMap.get("选配包名称"));
//                                                            field.setAccessible(true);
//                                                            field.set(resultList.get(s), specilNameList);
//
//                                                            Field field1 = c.getDeclaredField(columnMap.get("选配包内容"));
//                                                            field1.setAccessible(true);
//                                                            field1.set(resultList.get(s), connectList);
//
//                                                            Field field2 = c.getDeclaredField(columnMap.get("选配包价格"));
//                                                            field2.setAccessible(true);
//                                                            field2.set(resultList.get(s), priceList);
//                                                        }
//                                                    }
//
//                                                } else {
//                                                    //进入特色配置
//                                                    JSONArray configIns2 = configIns1.getJSONArray("items");
//                                                    String specilNameList = "";
//                                                    String valueList = "";
//                                                    //特色配置数量
//
//                                                    for (int l = 0; l < configIns2.size(); l++) {
//                                                        JSONObject configIns3 = configIns2.getJSONObject(l);
//                                                        String specificName = configIns3.getString("name");
//                                                        specilNameList += specificName+"__";
//                                                        JSONArray configIns4 = configIns3.getJSONArray("paramValues");
//                                                        JSONObject configIns5 = configIns4.getJSONObject(s);
//                                                        String PID = configIns5.getString("id");
//                                                        JSONArray configIns6 = configIns5.getJSONArray("subList");
//                                                        // specilNameList += specificName + "\n";
//                                                        //String value = configIns6.getJSONObject(0).getString("value");
//                                                        for (int m = 0; m < configIns6.size(); m++) {
//                                                            JSONObject configIns7 = configIns6.getJSONObject(m);
//                                                            String value = configIns7.getString("value");
//                                                            String price = configIns7.getString("price");
//                                                            String desc = configIns7.getString("desc");
//                                                            if (desc == null || value.equals("-")) {
//                                                                valueList = value + "\n";
//                                                            } else {
//                                                                valueList += value + desc + price + "\n";
//                                                            }
//                                                        }
//
//                                                        if (resultList.get(s).get_C_PID().equals(PID)) {
//                                                            Class c = resultList.get(s).getClass();
//                                                            Field field = c.getDeclaredField(columnMap.get("特色配置名称"));
//                                                            field.setAccessible(true);
//                                                            field.set(resultList.get(s), specilNameList);
//
//                                                            Field field1 = c.getDeclaredField(columnMap.get("特色配置状态"));
//                                                            field1.setAccessible(true);
//                                                            field1.set(resultList.get(s), valueList);
//                                                        }
////                                            else {
////                                                break;
////                                            }
//                                                    }
//                                                }
//                                            } else {
//                                                JSONArray configIns2 = configIns1.getJSONArray("items");
//                                                System.out.println(configIns2.size());
//                                                for (int u = 0; u < configIns2.size(); u++) {
//                                                    JSONObject configIns3 = configIns2.getJSONObject(u);
//                                                    String configName = title1 + "____" + configIns3.getString("name");
//                                                    JSONArray configIns4 = configIns3.getJSONArray("paramValues");
//                                                    //System.out.println(configIns4.size());
//                                                    String PID = configIns4.getJSONObject(s).getString("id");
//                                                    System.out.println("================================");
//                                                    System.out.println("这里是车型的ID:   " + PID);
//                                                    System.out.println("++++++++++++++++++++++++++++++++");
//                                                    JSONObject configIns5 = configIns4.getJSONObject(s);
//                                                    JSONArray configIns6 = configIns5.getJSONArray("subList");
//                                                    if (configIns6.size() >= 1) {
//                                                        String valueList = "";
//                                                        for (int l = 0; l < configIns6.size(); l++) {
//                                                            JSONObject configIns7 = configIns6.getJSONObject(l);
//                                                            String desc = configIns7.getString("desc");
//                                                            if (desc == null) {
//                                                                String value = configIns7.getString("value");
//                                                                valueList += value;
//                                                            } else {
//                                                                String value = configIns7.getString("value") + desc;
//                                                                valueList += value;
//                                                            }
//                                                        }
//                                                        System.out.println(configName);
//                                                        System.out.println(valueList);
//                                                        if (resultList.get(s).get_C_PID().equals(PID)) {
//                                                            Class c = resultList.get(s).getClass();
//                                                            Field field = c.getDeclaredField(columnMap.get(configName));
//                                                            field.setAccessible(true);
//                                                            field.set(resultList.get(s), valueList);
//                                                        } else {
//                                                            break;
//                                                        }
//                                                    }
//                                                }
//                                            }
//                                        }
//                                    }
//                                    for (int o = 0; o < resultList.size(); o++) {
//                                        System.out.print(resultList.get(o).get_C_PID() + "\n");
//                                        System.out.println(resultList.get(o).get_C_基本信息____车款名称() + "\n");
//                                        System.out.println(resultList.get(o).get_C_选配包名称() + "\n");
//                                        System.out.println(resultList.get(o).get_C_选配包内容() + "\n");
//                                        System.out.println(resultList.get(o).get_C_选配包价格() + "\n");
//
//
//
//                                        son_sqlserver daoconfig = new son_sqlserver("T_YiCheConfig", 4);
//                                        daoconfig.Method_Insert(resultList.get(o));
//                                        System.out.println("数据入库成功");
//                                    }
//
//
//                                } else {
//                                    System.out.println("!!!!!!!!网址里面没有东西!!!!!!!!");
//                                }


                            }
                        }
                    }
                }else {
                    System.out.println("内容为空 :"+stopUrl);
                }
            }else {
                System.out.println("内容为空: "+stopUrl);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
