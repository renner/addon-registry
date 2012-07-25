# addon-registry

This is a generic registry for add-ons (in the sense of plugins) to be used via JNDI within web applications.

## Declaring the Resource

Declare a `Resource` in your web container's configuration. For Tomcat put this into the `context.xml` 
file (to be found in the tomcat config directory) inside the `Context` element:

    <Resource name="mywebapp/addons" auth="Container"
              type="com.suse.addons.registry.AddonRegistry"
              factory="com.suse.addons.registry.AddonRegistryFactory"
              session-timeout="15" />

## Consuming the Resource

To consume add-ons within a webapp, specify the resource in the webapp configuration. For Tomcat 
add this to `WEB-INF/web.xml`:

    <resource-env-ref>
        <description>Object factory for the AddonRegistry singleton</description>
        <resource-env-ref-name>mywebapp/addons</resource-env-ref-name>
        <resource-env-ref-type>com.suse.addons.registry.AddonRegistry</resource-env-ref-type>
    </resource-env-ref>

In the Java code it is then possible to work with add-ons like this:

    import javax.naming.Context;
    import javax.naming.InitialContext;
    import com.suse.addons.model.Addon;
    import com.suse.addons.registry.AddonRegistry;

    ...

    Context context = (Context) new InitialContext().lookup("java:comp/env");
    AddonRegistry registry = (AddonRegistry) context.lookup("mywebapp/addons");

    ...

    for (Addon addon : registry.getAddons()) {
        print("Name: " + addon.getName());
    }

Eventually add-ons should register themselves to the registry like this:

    registry.register(new Addon("name", "entrypoint"));
