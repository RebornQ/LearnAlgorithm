package util.aspects;

//import com.alibaba.fastjson.JSONObject;
import util.aspects.annotations.RuntimeLogAnnotation;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * Log 注解类实现
 */
@Aspect
public class RuntimeLogAspect {

    public static final Logger logger =  LoggerFactory.getLogger(RuntimeLogAspect.class);
    /**
     * 换行符
     */
    private static final String LINE_SEPARATOR = System.lineSeparator();

    //execution the scan of pakage 切点package
//    @Pointcut("execution( * pw.gike.dddas..*(..))")
    @Pointcut("execution(* *.*(..)) && @annotation(util.aspects.annotations.RuntimeLogAnnotation)")
    public void serviceLog(){
    }

    @Before("serviceLog()")
    public void before(JoinPoint joinPoint) {
        logger.info("========================================== Start ==========================================");
        try {
            // 获取方法签名
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //java reflect相关类，通过反射得到注解
            Method method = signature.getMethod();
//            Class<?> targetClass = method.getDeclaringClass();
            // 打印调用 controller 的全路径以及执行方法
            logger.info("Class.Method\t: {}", signature);
            // 获取注解信息
            RuntimeLogAnnotation annotation = method.getAnnotation(RuntimeLogAnnotation.class);
            String methodDescription = annotation.description();
            if (!methodDescription.equals("")) {
                // 打印方法描述
                logger.info("Description\t: {}", methodDescription);
            }
            // 打印请求入参
            logger.info("Param args\t\t: {}", JSONObject.toJSON(joinPoint.getArgs()));
        }catch (Throwable throwable) {
            logger.error(throwable.getMessage(), throwable);
        }
    }
    @Around("serviceLog()")
    public Object around(ProceedingJoinPoint joinPoint) {
        try {
            long startTime = System.currentTimeMillis();
            long startNanoTime = System.nanoTime();
            Object result = joinPoint.proceed();
            // 打印出参
            logger.info("Result args\t: {}", JSONObject.toJSON(result));
            // 执行耗时
            long totalTime = System.currentTimeMillis() - startTime;
            if (totalTime > 0) {
                logger.info("Total time\t\t: {} ms", totalTime);
            } else {
                logger.info("Total time\t\t: {} ns", System.nanoTime() - startNanoTime);
            }
            // 结束时间
            logger.info("Finished at\t: {}", new Date());
            return result;
        }catch (Throwable throwable) {
            logger.error(throwable.getMessage(), throwable);
        }
        return null;
    }

    @After("serviceLog()")
    public void after() {
        // 接口结束后换行，方便分割查看
        logger.info("=========================================== End ===========================================" + LINE_SEPARATOR);
    }
}