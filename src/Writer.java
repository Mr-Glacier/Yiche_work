import Dao.son_sqlserver;
import Entity.Bean_T_YBrand;
import Entity.Bean_T_YConfig;
import Entity.Bean_T_YModel;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Writer {
    public static void main(String[] args) {
        try {
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
                Thread.sleep(100);
                String Yicheurl2 = BrandURL;
                Document document2 = Jsoup.parse(new URL(Yicheurl2).openStream(), "UTF-8", Yicheurl2);
                //System.out.println(document2.toString());
                Element idEL = document2.getElementById("pagination-list");
                Elements ItemModel = idEL.select(".pagenation-box.ssr-box");
                Elements ItemModel1 = ItemModel.select(".link-list.pg-item");
                Elements ItemModel2 = ItemModel1.select("a");

                for (int j = 0; j < ItemModel2.size(); j++) {
                    Thread.sleep(100);
                    String Url3 = "https://car.yiche.com" + ItemModel2.get(j).select("a").attr("href");
                    System.out.println("品牌的分页链接:" + Url3);
                    System.out.println();
                    Document document3 = Jsoup.parse(new URL(Url3).openStream(), "UTF-8", Url3);
                    Elements Item1 = document3.select(".search-result-wrapper");
                    Elements Item2 = Item1.select(".search-result-list");
                    Elements Item3 = Item2.select(".search-result-list-item");

                    for (int k = 0; k < Item3.size(); k++) {
                        Thread.sleep(100);
                        String ModelID = Item3.get(k).attr("data-id");
                        Elements Item4 = Item3.get(k).select(".cx-name.text-hover");
                        String ModelName = Item4.text();
                        String ModelURL = "https://car.yiche.com" + Item3.get(k).select("a").attr("href");
                        System.out.println("----------------");
                        System.out.println("\t" + "品牌名称: " + BrandName);
                        System.out.println("\t" + "车型名称: " + ModelName);
                        System.out.println("\t" + "车型ID: " + ModelID);
                        System.out.println("\t" + "车型URL: " + ModelURL);
                        System.out.println("=================");

                        Method_web M = new Method_web();
                        String DownURL = ModelURL+"peizhi/";
                        System.out.println(DownURL);
                        String JsonConent = M.Method_FindYCWB(DownURL);
                        System.out.println(JsonConent);
                        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("F:/A_JSON_File/"+ModelName.replace(":", "_")+"_Config.text", true), 662148);//165537
                        bufferedOutputStream.write(JsonConent.getBytes());
                        bufferedOutputStream.close();
                        Thread.sleep(300);
                        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}