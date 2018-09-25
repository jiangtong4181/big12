package cmm.xiu.myudf;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;

public class Util {
    //定义返回值类型为object数组，方便forward处理
    public  static  ArrayList<Object[]>  parsejson(String json){
        ArrayList<Object[]> list = new ArrayList<Object[]>();
        String newjson = json.replaceAll("\\\\", "");
        JSONObject js1 = JSON.parseObject(newjson);
        Object deviceId = js1.get("deviceId");
        JSONArray arr = js1.getJSONArray("appEventLogs");
        if(arr!=null){
            for (Object o : arr) {
                Object[] obj = new Object[6];
                JSONObject js2 = JSON.parseObject(o.toString());
                obj[0]=deviceId;
                obj[1]=js2.get("logType");
                obj[2]=js2.get("eventId");
                obj[3]=js2.get("musicID");
                obj[4]=js2.get("createdAtMs");
                obj[5]=js2.get("mark");
                list.add(obj);
            }
        }
        return list;
    }
}
