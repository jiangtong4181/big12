/**
 * 接口实现类
 */
public class WelcomeServiceImpl implements WelcomeService, WelcomeService2{
	public void sayHello(String msg) {
		System.out.println("hello : " + msg);
	}

	@Override
	public void sayHello2(String msg) {
		System.out.println("hello222 : " + msg);
	}
}
