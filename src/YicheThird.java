import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URL;

public class YicheThird {
    public static void main(String[] args) {
        try {
            String VerUrl = "https://car.yiche.com/quanxinaodia4l/";
            Document documentV = Jsoup.parse(new URL(VerUrl).openStream(), "UTF-8", VerUrl);
            //System.out.println(documentV.toString());
            Elements VItem1 = documentV.select(".car-model");
            //System.out.println(VItem1.size());//1
            Elements VItem2 = VItem1.select(".model-detail");
            //System.out.println(VItem2.size());//1
            Elements VItem3 = VItem2.select(".power-name");
            System.out.println(VItem3.size());//2
            for (int i = 0; i < VItem3.size(); i++) {
                Elements VItem4 = VItem3.get(i).select("table");
                System.out.println(VItem4.size());
                System.out.println("=============");
                for (int j = 0; j < VItem4.size(); j++) {
                    Elements VItem5 = VItem4.get(j).select(".list-info");
                    for (int k = 0; k < VItem5.size(); k++) {
                        String VersionID = VItem5.get(k).select("a").attr("data-id");
                        String VersionURL = VItem5.get(k).select("a").attr("href");
                        String VersionName = VItem5.get(k).select(".first").select("a").text();
                        String State = "在售";
                        System.out.println(VersionURL);
                        System.out.println(VersionName);
                        System.out.println(VersionID);
                    }
                    System.out.println("0000000000000");
                    System.out.println(VItem5.size());
                    //System.out.println(VItem5.toString());
                    System.out.println("++++++++++++++");
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }


    }
}
