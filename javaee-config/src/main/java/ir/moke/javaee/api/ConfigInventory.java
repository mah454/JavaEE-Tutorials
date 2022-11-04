package ir.moke.javaee.api;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

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
     * Read config from bootstrap.properties (Liberty application server)
     */
    @Inject
    @ConfigProperty(name = "age" , defaultValue = "12")
    private Integer age;

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
