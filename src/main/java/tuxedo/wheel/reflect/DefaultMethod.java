package tuxedo.wheel.reflect;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.WrongMethodTypeException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DefaultMethod {
    private final MethodHandle methodHandle;

    public DefaultMethod(Method method) throws ReflectiveOperationException {
        if (!method.isDefault()) {
            throw new WrongMethodTypeException("Should be a default method!");
        }

        final Class<?> declaringClass = method.getDeclaringClass();
        Field field = Lookup.class.getDeclaredField("IMPL_LOOKUP");
        field.setAccessible(true);
        Lookup lookup = ((Lookup) field.get(null)).in(declaringClass);
        methodHandle = lookup.unreflectSpecial(method, declaringClass).bindTo(
                Proxy.newProxyInstance(declaringClass.getClassLoader(), new Class[] { declaringClass }, (_proxy, _method, _args) -> null));
    }

    public Object invoke(Object[] args) throws Throwable {
        return methodHandle.invokeWithArguments(args);
    }
}
