import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;

public class YicheSecond {
    public static void main(String[] args) {
        try {
            String Yicheurl2 = "https://car.yiche.com/xuanchegongju/?mid=9";
            Document document2 = Jsoup.parse(new URL(Yicheurl2).openStream(), "UTF-8", Yicheurl2);
            Element idEL = document2.getElementById("pagination-list");
            Elements ItemModel = idEL.select(".pagenation-box.ssr-box");
            Elements ItemModel1 = ItemModel.select(".link-list.pg-item");
            //System.out.println(ItemModel1);
            Elements ItemModel2 = ItemModel1.select("a");
            if (ItemModel2.size() >= 1) {
                for (int i = 0; i < ItemModel2.size(); i++) {
                    String Url3 = "https://car.yiche.com"+ItemModel2.get(i).select("a").attr("href");
                    System.out.println(Url3);
                    Document document3 = Jsoup.parse(new URL(Url3).openStream(),"UTF-8", Url3);
                    Elements Item1 = document3.select(".search-result-wrapper");
                    Elements Item2 = Item1.select(".search-result-list");
                    Elements Item3 = Item2.select(".search-result-list-item");

                    System.out.println(Item2.size());
                    System.out.println(Item3.size());
                    for (int j = 0; j < Item3.size(); j++) {
                        String ModelID = Item3.get(j).attr("data-id");
                        Elements Item4 = Item3.get(j).select(".cx-name.text-hover");
                        String ModelName = Item4.text();
                        System.out.println(ModelName);
                        System.out.println(ModelID);
                        //https://car.yiche.com/quanxinaodia4l/
                        String ModelURL = "https://car.yiche.com"+Item3.get(j).select("a").attr("href");
                        System.out.println(ModelURL);
                        System.out.println("+++++++++++");
                    }
                }
                //https://car.yiche.com/xuanchegongju/?mid=9&page=2
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

}
