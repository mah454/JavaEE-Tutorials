package ir.moke.javaee.extension;

import ir.moke.javaee.annotation.CustomAPI;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.*;
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
