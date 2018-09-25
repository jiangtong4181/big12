import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;

public class reflect1 {
    @Test
    public  void testIntrospect2() throws Exception{
        Person1 c1 = new Person1();
        c1.setAge(32);
        c1.setName("kk");
        person2 c2 = new person2();
        copyPropertiesInIntrospector(c1, c2);
        System.out.println();
    }

    private static void copyPropertiesInIntrospector(Person1 c1, person2 c2) throws Exception {
        //bean信息
        BeanInfo bi1 = Introspector.getBeanInfo(c1.getClass()) ;
        PropertyDescriptor[] pps1 = bi1.getPropertyDescriptors();
        HashMap<Class, Object> map = new HashMap<>();
        BeanInfo bi2 = Introspector.getBeanInfo(c2.getClass());
        PropertyDescriptor[] pps2 = bi2.getPropertyDescriptors();

        for(PropertyDescriptor pp : pps1){
            Method getter = pp.getReadMethod();
            Method setter = pp.getWriteMethod() ;
            if(getter != null){
                Class retType = getter.getReturnType();
                Object retValue = getter.invoke(c1) ;
                map.put(retType,retValue);
            }
        }
        for(PropertyDescriptor pp2 : pps2){
            Method getter2 = pp2.getReadMethod();
            Method setter2 = pp2.getWriteMethod();
            if(setter2 != null){
                Class retType = getter2.getReturnType();
                Object retValue = getter2.invoke(c1) ;
                if(map.containsKey("retType")){
                    setter2.invoke(c2,map.get("retType"));
                }
            }
        }
    }
}
