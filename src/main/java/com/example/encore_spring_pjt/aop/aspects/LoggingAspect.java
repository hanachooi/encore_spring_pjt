package com.example.encore_spring_pjt.aop.aspects;

/*
AOP 용어 정리 ( 횡단 관심 모듈(cross cutting)을 이용한 공통의 사항을 처리하는 것이 AOP)

Aspect : 공통의 기능을 가지고 있는 모듈
Target : Aspect가 적용된 대상(클래스, 메서드 등이 해당)
JoinPoint : Aspect가 적용될 시점(메서드 실행 전, 후 )
Advice : 시점에 실행할 코드
PointCut : Advice를 적용할 메서드의 범위를 지정

시점에 관련된 어노테이션으로
@Before : 대상의 메서드가 실행되기 전에 호출
@After  : 대상의 메서드가 실행된 후에 호출
@AfterReturning : 대상의 메서드가 정상으로 실행되고 반환된 후에 실행
@AfterThrowing : 대상의 메서드가 예외가 발생되었을 때 실행
@Around : 대상의 메서드가 실행 전, 후, 예외발생 시에 실행      --> before 와 after 을 한꺼번에 같이 해줌. Before와 After 대체.

*/

import lombok.extern.slf4j.Slf4j;
import org.aspectj.apache.bcel.classfile.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.mapping.Join;
import org.springframework.stereotype.Component;

import java.util.concurrent.ForkJoinPool;


@Aspect
@Component
@Slf4j
public class LoggingAspect {

    // AOP 에서, 인터셉트의 역할을 시행하도록 하면 안됌.

    // 동일 경로를 cut 함수로 만들어 놓은 상태임.
    // 이걸 안쓰면, @Before, @After 마다 경로를 계속 설정해주어야 함.
    // point cut pattern [접근지정][패키지][클래스][메서드()]
   @Pointcut("execution(* com.example.encore_spring_pjt.ctrl.*.*(..))")
   //@Pointcut("execution(* com.example.encore_spring_pjt.aop.*Controller.test*(..))")
    private void cut(){

    }


    // test01 메서드가 실행하기 전에, cut() 메서드가 실행되게 됌.
    @Before("cut()")
    public void BeforeLog(JoinPoint point){
        String name = getMethod(point);
        System.out.println("LoggingAspect.BeforeLog");
        System.out.println("LoggingAspect.BeforeLog name : " + name);

        // 파라미터 정보를 얻어와서 파라미터 값을 로그로 출력
        Object[] args = point.getArgs();
        if(args.length <= 0){
            // @Slf4j로 로그를 찍는 것임.
            log.info(" >>>> no parameter ");
        }else{
            for(Object arg : args){
                log.info("parameter type = {}", arg.getClass().getSimpleName());
                log.info("parameter value = {}", arg);
            }
        }

    }

    // test01 이 실행된 후, afterLog 가 찍히게 됌.
    @After("cut()")
    public void afterLog(JoinPoint point){
        System.out.println("LoggingAspect.afterLog");
    }

    @AfterReturning(pointcut = "cut()", returning = "param")
    public void returningLog(JoinPoint point, Object param){
       String name = getMethod(point);
        System.out.println("debug >>>> LoggingAspect returnngLog ");
        System.out.println("debug >>> name , " + name);

        // 리턴타입과 리턴되는 값을 확인 할 수 있다.
        System.out.println("debug >>> return type , " + param.getClass().getSimpleName());
        System.out.println("debug >>> return value , " + param);
    }


    // join point 관련된 정보를 가져 옴.
    public String getMethod(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getName();
    }


    /*
    // 메서드 실행 전, 후 예외 발생 시 Advice 실행
    @Around("cut()")
    public Object aroundLog(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("debug >>> around before, " + joinPoint.getSinature.getName();
        Object result = joinPoint.proceed();
        System.out.println("debug >>> around after, " + joinPoint.getSinature.getName();
        return result;
    }
    */

    // 예외가 발생을 했을 때, 이게 실행이 되어야 함.
    @AfterThrowing(pointcut = "cut()", throwing = "e")
    public void throwingLog(JoinPoint joinPoint, Throwable e){
        // throwing e 와 Throwable e 의 이름이 동일해야 함.
        System.out.println("LoggingAspect.throwingLog method , " + joinPoint.getSignature().getName());
        System.out.println("LoggingAspect.throwingLog msg , " + e.getMessage());
    }

}
