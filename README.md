# addon-registry

`addon-registry` is a generic registry for add-ons to be used via JNDI within web applications.

## Declaring the Resource

Declare a `Resource` in your app server's configuration. For Tomcat you would put this into the `context.xml` file 
(to be found in the tomcat config directory) inside the `Context` element:

    <Resource name="mywebapp/addons" auth="Container"
              type="com.suse.addons.registry.AddonRegistry"
              factory="com.suse.addons.registry.AddonRegistryFactory"
              session-timeout="15" />

## Consuming the Resource

Your webapp is the one to make use of this resource, so add this to `WEB-INF/web.xml`:

    <resource-env-ref>
        <description>Object factory for the AddonRegistry singleton</description>
        <resource-env-ref-name>mywebapp/addons</resource-env-ref-name>
        <resource-env-ref-type>com.suse.addons.registry.AddonRegistry</resource-env-ref-type>
    </resource-env-ref>

In your Java webapp code you can then work with add-ons like this:

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

Further you will be able to register your add-on like this:

    registry.register(new Addon("name", "entrypoint"));
