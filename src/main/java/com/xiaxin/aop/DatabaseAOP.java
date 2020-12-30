package com.xiaxin.aop;

import com.xiaxin.annotation.MasterDataSource;
import com.xiaxin.utils.DbUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DatabaseAOP {
    @Pointcut(value = "execution(* com.xiaxin.mapper..*.*(..))")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        boolean isExist = method.isAnnotationPresent(MasterDataSource.class);
        if (!isExist) {
            DbUtil.setDb(DbUtil.slave);
            return;
        }
        DbUtil.setDb(DbUtil.master);
    }
}
