package ir.moke.javaee.cdi.extension;

import ir.moke.javaee.db.DBClient;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.spi.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Set;

public class DBExtension implements Extension {

    private DBConfiguration dbConfiguration;

    public void annotationDetection(@Observes @WithAnnotations(DBConfiguration.class) ProcessAnnotatedType pat) {
        AnnotatedType at = pat.getAnnotatedType();
        dbConfiguration = at.getAnnotation(DBConfiguration.class);
    }

    public void DatabaseDiscovery(@Observes AfterBeanDiscovery abd, BeanManager bm) {
        if (bm.getBeans(DBConfiguration.class, Default.Literal.INSTANCE).isEmpty()) {
            BeanAttributes<DBClient> beanAttributes = bm.createBeanAttributes(bm.createAnnotatedType(DBClient.class));
            DBClientAttributes dbClientAttributes = new DBClientAttributes(beanAttributes);
            DBClientProducerFactory dbClientProducerFactory = new DBClientProducerFactory(dbConfiguration.hostname(), dbConfiguration.username(), dbConfiguration.password());
            Bean<DBClient> bean = bm.createBean(dbClientAttributes, DBClient.class, dbClientProducerFactory);
            abd.addBean(bean);
        }
    }

    private static class DBClientAttributes implements BeanAttributes<DBClient> {

        private BeanAttributes<DBClient> beanAttributes;

        DBClientAttributes(BeanAttributes<DBClient> beanAttributes) {
            this.beanAttributes = beanAttributes;
        }

        @Override
        public Set<Type> getTypes() {
            return beanAttributes.getTypes();
        }

        @Override
        public Set<Annotation> getQualifiers() {
            return beanAttributes.getQualifiers();
        }

        @Override
        public Class<? extends Annotation> getScope() {
            return beanAttributes.getScope();
        }

        @Override
        public String getName() {
            return beanAttributes.getName();
        }

        @Override
        public Set<Class<? extends Annotation>> getStereotypes() {
            return beanAttributes.getStereotypes();
        }

        @Override
        public boolean isAlternative() {
            return false;
        }
    }

    private static class DBClientProducerFactory implements InjectionTargetFactory<DBClient> {

        private String hostname;
        private String username;
        private String password;

        public DBClientProducerFactory(String hostname, String username, String password) {
            this.hostname = hostname;
            this.username = username;
            this.password = password;
        }

        @Override
        public InjectionTarget<DBClient> createInjectionTarget(Bean<DBClient> bean) {
            return new InjectionTarget<DBClient>() {
                @Override
                public void inject(DBClient instance, CreationalContext<DBClient> ctx) {

                }

                @Override
                public void postConstruct(DBClient instance) {

                }

                @Override
                public void preDestroy(DBClient instance) {

                }

                @Override
                public DBClient produce(CreationalContext<DBClient> ctx) {
                    return new DBClient(hostname, username, password);
                }

                @Override
                public void dispose(DBClient instance) {
                    instance.disconnect();
                }

                @Override
                public Set<InjectionPoint> getInjectionPoints() {
                    return Collections.emptySet();
                }
            };
        }
    }
}