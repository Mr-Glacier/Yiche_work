import Dao.son_sqlserver;
import Entity.Bean_T_YBrand;
import Entity.Bean_T_YModel;
import Entity.Bean_T_YVersion;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;

public class YicheFirst {
    public static void main(String[] args) {
        try {
            String BrandID = "";
            String BrandName = "";
            String BrandURL = "";
            String Yicheurl = "https://car.yiche.com/";
            Document document = Jsoup.parse(new URL(Yicheurl).openStream(), "UTF-8", Yicheurl);
            //System.out.println(document.toString());
            Elements Items1 = document.select(".brand-list-content");
            Elements Items2 = Items1.select(".brand-list");
            Elements Items3 = Items2.select(".brand-list-item");
            //System.out.println(Items3.size());
            Elements Items4 = Items3.select(".item-brand");
            //System.out.println(Items4.size());
            //son_sqlserver sqlserverdao = new son_sqlserver("T_YBrand", 0);
            //son_sqlserver sqlserverdao2 = new son_sqlserver("T_YModel", 1);
                son_sqlserver sqlserverdao3 = new son_sqlserver("T_YVersion",2);
            for (int i = 0; i < Items4.size(); i++) {

                BrandName = Items4.get(i).attr("data-name");
                //System.out.println(BrandName);
                BrandID = Items4.get(i).attr("data-id");
                //System.out.println(BrandID);
                BrandURL = "https://car.yiche.com/xuanchegongju/?mid=" + BrandID;

                Bean_T_YBrand beanTYBrand = new Bean_T_YBrand();
                beanTYBrand.set_C_BrandID(BrandID);
                beanTYBrand.set_C_BrandName(BrandName);
                beanTYBrand.set_C_BrandURL(BrandURL);
                //System.out.println(BrandID);
                //System.out.println(BrandName);
                //System.out.println(BrandURL);
                //System.out.println("+++++++++++++++");
                //sqlserverdao.Method_Insert(beanTYBrand);
                //进入车型查询
                Thread.sleep(800);
                String Yicheurl2 = BrandURL;
                Document document2 = Jsoup.parse(new URL(Yicheurl2).openStream(), "UTF-8", Yicheurl2);
                //System.out.println(document2.toString());
                Element idEL = document2.getElementById("pagination-list");
                Elements ItemModel = idEL.select(".pagenation-box.ssr-box");
                Elements ItemModel1 = ItemModel.select(".link-list.pg-item");
                Elements ItemModel2 = ItemModel1.select("a");

                for (int j = 0; j < ItemModel2.size(); j++) {
                    Thread.sleep(500);
                    String Url3 = "https://car.yiche.com"+ItemModel2.get(j).select("a").attr("href");
                    System.out.println("品牌链接:"+Url3);
                    System.out.println();
                    Document document3 = Jsoup.parse(new URL(Url3).openStream(),"UTF-8", Url3);
                    //System.out.println(document3.toString());
                    Elements Item1 = document3.select(".search-result-wrapper");
                    Elements Item2 = Item1.select(".search-result-list");
                    Elements Item3 = Item2.select(".search-result-list-item");

                    for (int k = 0; k < Item3.size(); k++) {
                        Thread.sleep(500);
                        String ModelID = Item3.get(k).attr("data-id");
                        Elements Item4 = Item3.get(k).select(".cx-name.text-hover");
                        String ModelName = Item4.text();
                        String ModelURL = "https://car.yiche.com"+Item3.get(k).select("a").attr("href");

                        Bean_T_YModel beanTYModel = new Bean_T_YModel();
                        beanTYModel.set_C_ModelID(ModelID);
                        beanTYModel.set_C_ModelName(ModelName);
                        beanTYModel.set_C_ModelURL(ModelURL);
                        beanTYModel.set_C_BrandID(BrandID);
                        ///sqlserverdao2.Method_Insert(beanTYModel);
                        System.out.println("----------------");
                        System.out.println("品牌名称: "+BrandID);
                        System.out.println("车型名称: "+ModelName);
                        System.out.println("车型ID: "+ModelID);
                        System.out.println("车型URL: "+ModelURL);
                        System.out.println("=================");

                        //进入版本查询
                        String VerUrl = ModelURL;
                        String ModelIDV =ModelID;
                        Document documentV = Jsoup.parse(new URL(VerUrl).openStream(), "UTF-8", VerUrl);
                        //System.out.println(documentV.toString());
                        Elements VItem1 = documentV.select(".car-model");
                        //System.out.println(VItem1.size());//1
                        Elements VItem2 = VItem1.select(".model-detail");
                        //System.out.println(VItem2.size());//1
                        Elements VItem3 = VItem2.select(".power-name");
                        System.out.println(VItem3.size());//2
                        for (int l = 0; l < VItem3.size(); l++) {
                            Elements VItem4 = VItem3.get(l).select("table");
                            System.out.println(VItem4.size());
                            System.out.println("=============");
                            for (int m = 0; m < VItem4.size(); m++) {
                                Elements VItem5 = VItem4.get(m).select(".list-info");
                                for (int n = 0; n < VItem5.size(); n++) {
                                    String VersionID = VItem5.get(n).select("a").attr("data-id");
                                    String VersionURL = "https://car.yiche.com"+VItem5.get(n).select("a").attr("href");
                                    String VersionName = VItem5.get(n).select(".first").select("a").text();
                                    System.out.println("车型信息: "+ModelID);
                                    System.out.println("版本URL: "+VersionURL);
                                    System.out.println("版本名称: "+VersionName);
                                    System.out.println("版本ID: "+VersionID);

                                    Bean_T_YVersion beanTYVersion = new Bean_T_YVersion();
                                    beanTYVersion.set_C_VersionID(VersionID);
                                    beanTYVersion.set_C_VersionURL(VersionURL);
                                    beanTYVersion.set_C_VersionName(VersionName);
                                    beanTYVersion.set_C_ModelID(ModelID);
                                    beanTYVersion.set_C_State("在售");
                                    sqlserverdao3.Method_Insert(beanTYVersion);

                                }
                                System.out.println("0000000000000");
                                System.out.println(VItem5.size());
                                //System.out.println(VItem5.toString());
                                System.out.println("++++++++++++++");
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
