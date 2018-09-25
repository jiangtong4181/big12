import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * App
 * 
 */
public class App {
	public static void main(String[] args) {

		//目标对象
		final WelcomeService2 target = new WelcomeServiceImpl();
		//类加载器
		ClassLoader loader = ClassLoader.getSystemClassLoader();

		//接口集合
		Class[] interfaces = {WelcomeService.class,WelcomeService2.class};

		//处理器
		InvocationHandler h = new InvocationHandler() {
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("hello world");
				System.out.println("how are you");
				Object ret = method.invoke(target , args) ;
				return ret;
			}
		} ;


		//创建代理对象
		WelcomeService2 o1 = (WelcomeService2) Proxy.newProxyInstance(loader, interfaces, h);
		//访问代理对象的方法
		o1.sayHello2("c");
		((WelcomeService)o1).sayHello("kkk");
	}
}
