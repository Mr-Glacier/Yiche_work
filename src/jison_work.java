import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.net.URLDecoder;

public class jison_work {
    public static void main(String[] args) {
        try {
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
            for (int i = 0; i <jsonArr.size() ; i++) {
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
                        //https://car.yiche.com/aodia3-3999/m152951/
                        String VersionURL = stopurl+"m"+five.getString("id")+"/";
                        System.out.println(VersionID);
                        System.out.println(VersionName);
                        System.out.println(VersionURL);
                        System.out.println("============");
                    }
                }
            }

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }


    }
}
