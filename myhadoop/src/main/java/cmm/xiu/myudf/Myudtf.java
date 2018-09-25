package cmm.xiu.myudf;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

import java.util.ArrayList;

public class Myudtf extends GenericUDTF {
    PrimitiveObjectInspector poi;
    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {
        //判断参数个数，如果不为1，则抛异常
        if(argOIs.getAllStructFieldRefs().size()!=1){
            throw new UDFArgumentException("参数个数只能为1");
        }
       //通过对象检查器判断是否为基本类型，如果不是则抛出异常
        ObjectInspector oi = argOIs.getAllStructFieldRefs().get(0).getFieldObjectInspector();
        if(oi.getCategory()!=ObjectInspector.Category.PRIMITIVE){
            throw new UDFArgumentException("参数非基本类型,需要基本类型string");
        }
        //通过第一步的判断，已经得到基本类型，这时候强转为基本类型
        poi= (PrimitiveObjectInspector) oi;
        //进一步判断是否为string类型，如果不是则抛异常
        if(poi.getPrimitiveCategory()!=PrimitiveObjectInspector.PrimitiveCategory.STRING){
            throw new UDFArgumentException("参数非string类型,需要基本类型string");
        }
        //构造字段名，通过list
        ArrayList<String> fieldNames = new ArrayList<String>();
        fieldNames.add("deviceId");
        fieldNames.add("logType");
        fieldNames.add("eventId");
        fieldNames.add("musicID");
        fieldNames.add("createdAtMs");
        fieldNames.add("mark");
        //构造字段类型,string类型
        ArrayList<ObjectInspector> fieldOIs = new ArrayList<ObjectInspector>();
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        //构造对象检查器，把字段装进去返回
        return ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames,fieldOIs);
    }

    @Override
    public void process(Object[] args) throws HiveException {
        //得到一行数据
        String json = (String) poi.getPrimitiveJavaObject(args[0]);
        ArrayList<Object[]> list = Util.parsejson(json);
        for (Object[] objects : list) {
            forward(objects);
        }
    }

    @Override
    public void close() throws HiveException {

    }
}
