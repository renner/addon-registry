# addon-registry

Generic plugin registry to be used via JNDI within application servers or web applications.

## Declaring the Registry as a JNDI Resource

To make use of the plugin registry from different applications, it is necessary to define a global JNDI resource 
in the application server's configuration. For Tomcat this needs to be done in the `server.xml` file:

    <GlobalNamingResources>
        <Resource name="mywebapp/addons" auth="Container"
                  type="com.suse.addons.registry.AddonRegistry"
                  factory="com.suse.addons.registry.AddonRegistryFactory" />
    </GlobalNamingResources>

Additionally a reference needs to be placed inside each application's `Context` element. For Tomcat this can be
done globally for all applications in the `context.xml` file:

    <Context>
        <ResourceLink global="mywebapp/addons" name="mywebapp/addons" />
    </Context>

## Consuming the Registry

Add-ons should register themselves like this:

    import javax.naming.Context;
    import javax.naming.InitialContext;
    import com.suse.addons.model.Addon;
    import com.suse.addons.registry.AddonRegistry;
    ...
    Context context = (Context) new InitialContext().lookup("java:comp/env");
    AddonRegistry registry = (AddonRegistry) context.lookup("mywebapp/addons");
    ...
    Addon addon = new Addon();
    addon.setName("Foobar");
    registry.register(addon);

In the application code it is possible to work with registered add-ons like this:

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
