package cn.aop;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyMethodInterceptor implements MethodInterceptor {
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long start = System.nanoTime();
        //调用目标对象的方法
        System.out.println("环绕前");
        Object obj = invocation.proceed();
        System.out.println("环绕后");
        System.out.println(System.nanoTime()-start);
        return obj;
    }
}
