import Entity.Bean_T_YConfig;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;

public class InsertAll {
    public static void main(String[] args) {
        try{
            String Yicheurl = "https://car.yiche.com/";
            Document document = Jsoup.parse(new URL(Yicheurl).openStream(), "UTF-8", Yicheurl);
            Elements Items1 = document.select(".brand-list-content");
            Elements Items2 = Items1.select(".brand-list");
            Elements Items3 = Items2.select(".brand-list-item");
            Elements Items4 = Items3.select(".item-brand");
            for (int i = 0; i < Items4.size(); i++) {

               String BrandName = Items4.get(i).attr("data-name");
                //System.out.println(BrandName);
               String BrandID = Items4.get(i).attr("data-id");
                //System.out.println(BrandID);
               String BrandURL = "https://car.yiche.com/xuanchegongju/?mid=" + BrandID;

                //进入车型查询
                Thread.sleep(50);
                String Yicheurl2 = BrandURL;
                Document document2 = Jsoup.parse(new URL(Yicheurl2).openStream(), "UTF-8", Yicheurl2);
                Element idEL = document2.getElementById("pagination-list");
                Elements ItemModel = idEL.select(".pagenation-box.ssr-box");
                Elements ItemModel1 = ItemModel.select(".link-list.pg-item");
                Elements ItemModel2 = ItemModel1.select("a");

                for (int j = 0; j < ItemModel2.size(); j++) {
                    String Url3 = "https://car.yiche.com"+ItemModel2.get(j).select("a").attr("href");
                    Document document3 = Jsoup.parse(new URL(Url3).openStream(),"UTF-8", Url3);
                    Elements Item1 = document3.select(".search-result-wrapper");
                    Elements Item2 = Item1.select(".search-result-list");
                    Elements Item3 = Item2.select(".search-result-list-item");

                    for (int k = 0; k < Item3.size(); k++) {
                        Thread.sleep(20);
                        String ModelID = Item3.get(k).attr("data-id");
                        Elements Item4 = Item3.get(k).select(".cx-name.text-hover");
                        String ModelName = Item4.text();
                        String ModelURL = "https://car.yiche.com"+Item3.get(k).select("a").attr("href");

                        Method_web M = new Method_web();
                        String ParURL = ModelURL+"peizhi/";
                        String JsonConent = M.Method_FindYCWB(ParURL);
                        JSONObject config1 = JSONObject.parseObject(JsonConent);
                        JSONArray configData = config1.getJSONArray("data");

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

                        for (int s = 0; s < resultList.size(); s++) {

                            for (int t = 0; t < configData.size(); t++) {
                                JSONObject configIns1 = configData.getJSONObject(t);
                                String title1 = configIns1.getString("name");
                                System.out.println(title1);
                                if (title1.equals("选配包")||title1.equals("特色配置")){

                                }else {

                                }

                                JSONArray configIns2 = configIns1.getJSONArray("items");
                                System.out.println(configIns2.size());
                                for (int u = 0; u < configIns2.size(); u++) {
                                    JSONObject configIns3 = configIns2.getJSONObject(u);
                                    String configName = title1 + "____" + configIns3.getString("name");
                                    JSONArray configIns4 = configIns3.getJSONArray("paramValues");
                                    //System.out.println(configIns4.size());
                                    String PID = configIns4.getJSONObject(s).getString("id");
                                    System.out.println("================================");
                                    System.out.println("这里是车型的ID:   " + PID);
                                    System.out.println("++++++++++++++++++++++++++++++++");
                                    JSONObject configIns5 = configIns4.getJSONObject(s);
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
                                        if (resultList.get(s).get_C_PID().equals(PID)) {
                                            Class c = resultList.get(s).getClass();
                                            Field field = c.getDeclaredField(columnMap.get(configName));
                                            field.setAccessible(true);
                                            field.set(resultList.get(s), valueList);
                                        } else {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }catch (Exception ex){
            System.out.println(ex.toString());
        }
    }
}
