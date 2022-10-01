package ir.moke.javaee.api;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ConfigInventory {

    @Inject
    @ConfigProperty(name = "name", defaultValue = "Mahdi")
    private String name;

    /**
     * Read config from microprofile-config.properties
     */
    @Inject
    @ConfigProperty(name = "family")
    private String family;

    /**
     * Read config from liberty bootstrap.properties
     */
    @Inject
    @ConfigProperty(name = "age")
    private int age;

    public String getName() {
        return name;
    }

    public String getFamily() {
        return family;
    }

    public int getAge() {
        return age;
    }
}
