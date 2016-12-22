package logistics.system.project.utility;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class LoggingAspect {

	public void doBefore(JoinPoint jp) {
		LogRecord.info(jp.getTarget().getClass().getName() + "." + jp.getSignature().getName() + " start");
	}

	public void doAfter(JoinPoint jp) {
		LogRecord.info(jp.getTarget().getClass().getName() + "." + jp.getSignature().getName()+ " end");
	}

	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		long time = System.currentTimeMillis();
		LogRecord.info("args : " + Arrays.toString(pjp.getArgs()));
		Object retVal = pjp.proceed();
		time = System.currentTimeMillis() - time;
		LogRecord.info("process time: " + time + " ms");
		return retVal;
	}

	public void doThrowing(JoinPoint jp, Throwable ex) {
		LogRecord.error(jp.getTarget().getClass().getName() + "." + jp.getSignature().getName() + " throw exception");
		LogRecord.error(ex.getMessage());
	}


}
