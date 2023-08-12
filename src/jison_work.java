import Entity.Bean_T_YBrand;
import Entity.Bean_T_YModel;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.net.URL;
import java.net.URLDecoder;

//这个函数用于解析停售款车型.
public class jison_work {

    public void Method_Json(String stopURL) throws Exception {
        Document documentstop = Jsoup.parse(new URL(stopURL).openStream(), "UTF-8", stopURL);
        Elements stopItems1 = documentstop.select("script[type=text/javascript]");
        String text1 = String.valueOf(stopItems1.get(7));
        //System.out.println(text1);
        String keyWord = URLDecoder.decode(text1, "utf-8");
        //System.out.println(keyWord);
        String maintext = keyWord.substring(26539, 146838);
        //System.out.println(miantext);
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
                    String VersionURL = stopURL + "m" + five.getString("id") + "/";
                    System.out.println(VersionID);
                    System.out.println(VersionName);
                    System.out.println(VersionURL);
                    System.out.println(State);
                    System.out.println("============");
                }
            }
        }

    }
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
                String BrandID = Items4.get(i).attr("data-id");
                String BrandURL = "https://car.yiche.com/xuanchegongju/?mid=" + BrandID;
                Bean_T_YBrand beanTYBrand = new Bean_T_YBrand();
                beanTYBrand.set_C_BrandID(BrandID);
                beanTYBrand.set_C_BrandName(BrandName);
                beanTYBrand.set_C_BrandURL(BrandURL);

                //进入车型查询
                Thread.sleep(500);
                String Yicheurl2 = BrandURL;
                Document document2 = Jsoup.parse(new URL(Yicheurl2).openStream(), "UTF-8", Yicheurl2);
                //System.out.println(document2.toString());
                Element idEL = document2.getElementById("pagination-list");
                Elements ItemModel = idEL.select(".pagenation-box.ssr-box");
                Elements ItemModel1 = ItemModel.select(".link-list.pg-item");
                Elements ItemModel2 = ItemModel1.select("a");

                for (int j = 0; j < ItemModel2.size(); j++) {
                    Thread.sleep(200);
                    String Url3 = "https://car.yiche.com" + ItemModel2.get(j).select("a").attr("href");
                    System.out.println("品牌链接:" + Url3);
                    System.out.println();
                    Document document3 = Jsoup.parse(new URL(Url3).openStream(), "UTF-8", Url3);
                    //System.out.println(document3.toString());
                    Elements Item1 = document3.select(".search-result-wrapper");
                    Elements Item2 = Item1.select(".search-result-list");
                    Elements Item3 = Item2.select(".search-result-list-item");

                    for (int k = 0; k < Item3.size(); k++) {
                        Thread.sleep(200);
                        String ModelID = Item3.get(k).attr("data-id");
                        Elements Item4 = Item3.get(k).select(".cx-name.text-hover");
                        String ModelName = Item4.text();
                        String ModelURL = "https://car.yiche.com" + Item3.get(k).select("a").attr("href");
                        Bean_T_YModel beanTYModel = new Bean_T_YModel();
                        beanTYModel.set_C_ModelID(ModelID);
                        beanTYModel.set_C_ModelName(ModelName);
                        beanTYModel.set_C_ModelURL(ModelURL);
                        beanTYModel.set_C_BrandID(BrandID);
                        ///sqlserverdao2.Method_Insert(beanTYModel);
                        System.out.println("----------------");
                        System.out.println("\t" + "品牌名称: " + BrandName);
                        System.out.println("\t" + "车型名称: " + ModelName);
                        System.out.println("\t" + "车型ID: " + ModelID);
                        System.out.println("\t" + "车型URL: " + ModelURL);
                        System.out.println("=================");
                        //进入版本查询

                    }
                }
            }




            String stopurl = "https://car.yiche.com/aodia3-3999/";
            Document documentstop = Jsoup.parse(new URL(stopurl).openStream(), "UTF-8", stopurl);

            //Elements elements = document.select("span[class=class1]span[id=id1]")
            Elements stopItems1 = documentstop.select("script[type=text/javascript]");
            //System.out.println(stopItems1);
            //System.out.println(stopItems1.get(7));
            String text1 = String.valueOf(stopItems1.get(7));
            //System.out.println(text1);
            String keyWord = URLDecoder.decode(text1, "utf-8");
            //System.out.println(keyWord);
            String maintext = keyWord.substring(26539, 146838);
            //System.out.println(miantext);
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
                        String VersionURL = stopurl + "m" + five.getString("id") + "/";
                        System.out.println(VersionID);
                        System.out.println(VersionName);
                        System.out.println(VersionURL);
                        System.out.println(State);
                        System.out.println("============");
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }




    }
}
