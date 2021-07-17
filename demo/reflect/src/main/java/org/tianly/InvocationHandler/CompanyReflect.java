package org.tianly.InvocationHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


@Data
@AllArgsConstructor
public class CompanyReflect<T> implements InvocationHandler {
    private T objFactory;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 进行业务增强
        System.out.println("JDK动态代理对业务进行了增强处理");
        // 通过反射调用方法本身
        Object invoke = method.invoke(objFactory, args);
        System.out.println("JDK动态代理对业务进行了增强处理结束");
        return invoke;
    }
}
