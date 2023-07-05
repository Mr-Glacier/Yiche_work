import Entity.Bean_T_YConfig;
import Entity.Bean_T_YVersion;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

public class Parameter {
    public static void main(String[] args) {
        try {
            Method_web M = new Method_web();
            String ParURL = "https://car.yiche.com/quanxinaodia4l/peizhi/";
            String JsonConent = M.Method_FindYCWB(ParURL);
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
//
//                        for (int l = 0; l < config7.size(); l++) {
//                            if (((JSONObject) config7.get(l)).getString("desc") == null) {
//                                String message1 = ((JSONObject) config7.get(l)).getString("value");
////                                System.out.println(carname + "     " + message1);
////                                System.out.println("==================================");
//                            } else {
//                                String message1 = ((JSONObject) config7.get(l)).getString("value");
//                                String message2 = ((JSONObject) config7.get(l)).getString("desc");
//                                System.out.println(carname + "      " + message1);
//                                System.out.println(carname + "      " + message2);
//                                System.out.println("==================================");
//                            }
//
//                        }
//                    }
//                    //System.out.println(VersionID);
//                }
//            }


            //数据的填充

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
                        String configName = title1 + configIns3.getString("name");
                        JSONArray configIns4 = configIns3.getJSONArray("paramValues");
                        //System.out.println(configIns4.size());
                        String PID = configIns4.getJSONObject(i).getString("id");
                        System.out.println("================================");
                        System.out.println("这里是车型的ID:   " + PID);
                        System.out.println("++++++++++++++++++++++++++++++++");
                        JSONObject configIns5 = configIns4.getJSONObject(i);
                        JSONArray configIns6 = configIns5.getJSONArray("subList");
                        String valueList = "";
                        if (configIns6.size() > 1) {
                            for (int l = 0; l < configIns6.size(); l++) {
                                JSONObject configIns7 = configIns6.getJSONObject(l);
                                String desc = configIns7.getString("desc");
                                if (desc == null) {
                                    String value = configIns7.getString("value");
                                    valueList += value + "\n";
                                } else {
                                    String value = configIns7.getString("value") + desc;
                                    valueList += value + "\n";
                                }
                            }
                            System.out.println(configName);
                            System.out.println(valueList);

                        } else {
                            JSONObject configIns7 = configIns6.getJSONObject(0);
                            String desc = configIns7.getString("desc");
                            if (null == desc) {
                                String value = configIns7.getString("value");
                                System.out.println(configName);
                                System.out.println(value);
                            } else {
                                String value = configIns7.getString("value") + configIns7.getString("desc");
                                System.out.println(configName);
                                System.out.println(value);
                            }
                        }
                    }
                }
            }

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
