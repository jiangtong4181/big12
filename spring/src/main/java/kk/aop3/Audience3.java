package kk.aop3;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 *
 */
public class Audience3 {
	/**
	 * 观看 , 环绕通知
	 */
	public Object watch(ProceedingJoinPoint pjp){
		try {
			System.out.println("sitdown");
			System.out.println("trunoff");
			Object obj = pjp.proceed();
			System.out.println("applaud");
			return obj;
		} catch (Throwable e) {
			System.out.println("payoff");
			e.printStackTrace();
		}
		finally {
			System.out.println("gohome");
		}
		return null;
	}

}
