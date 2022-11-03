package ir.moke.javaee.extension;

import ir.moke.javaee.annotation.CustomAPI;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.spi.*;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;

public class CustomApiExtension implements Extension {

    private Class<?> clazz;

    public <T> void annotationDetection(@Observes @WithAnnotations(CustomAPI.class) ProcessAnnotatedType<T> pat) {
        AnnotatedType<T> at = pat.getAnnotatedType();
        clazz = at.getJavaClass();
    }

    public void registerCustomApi(@Observes AfterBeanDiscovery abd, BeanManager bm) {
        abd.addBean()
                .types(Object.class)
                .scope(RequestScoped.class)
                .qualifiers(Default.Literal.INSTANCE)
                .alternative(true)
                .produceWith(createNewInstance());
    }

    private Function<Instance<Object>, Object> createNewInstance() {
        return objects -> {
            try {
                return clazz.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
            return null;
        };
    }

}
