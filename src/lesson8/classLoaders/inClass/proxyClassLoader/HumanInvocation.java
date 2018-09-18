package lesson8.classLoaders.inClass.proxyClassLoader;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class HumanInvocation implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        HumanLoader humanLoader = new HumanLoader(proxy.getClass().getClassLoader());
        Class humanClass = humanLoader.loadClass("lesson8.classLoaders.inClass.proxyClassLoader.EuropeanHuman");
        Method method1 = humanClass.getMethod(method.getName(), method.getParameterTypes());
        return method1.invoke(humanClass.newInstance(), args);
    }
}