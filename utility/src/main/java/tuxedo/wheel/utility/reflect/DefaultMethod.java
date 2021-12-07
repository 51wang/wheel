package tuxedo.wheel.utility.reflect;

import lombok.NonNull;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.WrongMethodTypeException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DefaultMethod {
    private static Lookup implLookup;
    private final MethodHandle methodHandle;

    private DefaultMethod(Method method) throws IllegalAccessException {
        Class<?> declaringClass = method.getDeclaringClass();
        methodHandle = implLookup.in(declaringClass).unreflectSpecial(method, declaringClass).bindTo(Proxy
                .newProxyInstance(declaringClass.getClassLoader(), new Class[]{declaringClass},
                        (_proxy, _method, _args) -> null));
    }

    public static DefaultMethod newInstance(@NonNull Method method)
            throws WrongMethodTypeException, ReflectiveOperationException {
        if (!method.isDefault()) {
            throw new WrongMethodTypeException("Method should be default!");
        }

        if (implLookup == null) {
            Field field = Lookup.class.getDeclaredField("IMPL_LOOKUP");
            field.setAccessible(true);
            implLookup = ((Lookup) field.get(null));
        }

        return new DefaultMethod(method);
    }

    public Object invoke(Object[] args) throws Throwable {
        return methodHandle.invokeWithArguments(args);
    }
}
