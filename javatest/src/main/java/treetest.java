import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;

public class treetest {
    @Test
    public void testtree1() throws Exception {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(1,"tom1");
        map.put(2,"tom2");
        map.put(3,"tom3");
        map.put(4,"tom4");
        map.put(5,"tom5");
        preordertraval(getroot(map));

    }

    //通过反射拿到根节点
    public static Map.Entry getroot(TreeMap map) throws Exception {
        //拿到字段
        Field f = TreeMap.class.getDeclaredField("root");
        //设置可访问性
        f.setAccessible(true);
        //取出这个字段在某个对象上面的值
        Object o = f.get(map);
        return (Map.Entry) o;
    }

    //通过反射拿到节点的key
    public static Object getkey(Map.Entry e) throws Exception {
        //拿到字段
        Field f = e.getClass().getDeclaredField("key");
        //设置可访问性
        f.setAccessible(true);
        Object o = f.get(e);
        return o;
    }

    //通过反射拿到左节点
    public static Map.Entry getleft(Map.Entry e) throws Exception {
        //拿到字段
        Field f = e.getClass().getDeclaredField("left");
        //设置可访问性
        f.setAccessible(true);
        Object o = f.get(e);
        return (Map.Entry)o;
    }

    //通过两次反射拿到左节点的key
    public static Object getleftkey(Map.Entry e) throws Exception {
        //拿到字段
        Field f = e.getClass().getDeclaredField("left");
        //设置可访问性
        f.setAccessible(true);
        Object o = f.get(e);
        if(o!=null){
            Field key = o.getClass().getDeclaredField("key");
            key.setAccessible(true);
            return key.get(o);
        }
        return null;
    }
    public static Object getrightkey(Map.Entry e) throws Exception {
        //拿到字段
        Field f = e.getClass().getDeclaredField("right");
        //设置可访问性
        f.setAccessible(true);
        Object o = f.get(e);
        if(o!=null){
            Field key = o.getClass().getDeclaredField("key");
            key.setAccessible(true);
            return key.get(o);
        }
        return null;
    }

    public static Map.Entry getright(Map.Entry e) throws Exception {
        //拿到字段
        Field f = e.getClass().getDeclaredField("right");
        //设置可访问性
        f.setAccessible(true);
        Object o = f.get(e);
        return (Map.Entry)o;
    }

    //通过递归实现前序遍历
    public static void preordertraval(Map.Entry e) throws Exception {
        if(e!=null){
            System.out.println(getkey(e));
            preordertraval(getleft(e));
            preordertraval(getright(e));
        }
    }
}
