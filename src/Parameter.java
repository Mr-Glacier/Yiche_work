import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

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
            for (int i = 0; i < configData.size(); i++) {
//            Object obj = configData.get(i);
//            JSONObject jsonObject = (JSONObject) obj;
                JSONObject config2 = configData.getJSONObject(i);
                String text1 = config2.getString("name");
                //System.out.println(text1);
                JSONArray config3 = config2.getJSONArray("items");
                //System.out.println(config3.size());
                for (int j = 0; j < config3.size(); j++) {
                    JSONObject config4 = config3.getJSONObject(j);
                    String carname = text1 + "____" + config4.getString("name");
                    System.out.println(carname);
                    JSONArray config5 = config4.getJSONArray("paramValues");
                    //System.out.println(config5.size());
                    for (int k = 0; k < config5.size(); k++) {
                        JSONObject config6 = config5.getJSONObject(k);
                        String VersionID = config5.getJSONObject(k).getString("id");
                        JSONArray config7 = config6.getJSONArray("subList");
//                        System.out.println("Begin____________________");
//                        System.out.println("这是版本车辆ID: " + VersionID);

                        for (int l = 0; l < config7.size(); l++) {
                            if (((JSONObject) config7.get(l)).getString("desc") == null) {
                                String message1 = ((JSONObject) config7.get(l)).getString("value");
//                                System.out.println(carname + "     " + message1);
//                                System.out.println("==================================");
                            } else {
                                String message1 = ((JSONObject) config7.get(l)).getString("value");
                                String message2 = ((JSONObject) config7.get(l)).getString("desc");
//                                System.out.println(message1);
//                                System.out.println(message2);
//                                System.out.println("==================================");
                            }

                        }
                    }
                    //System.out.println(VersionID);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
