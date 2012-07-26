package com.suse.addons.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.suse.addons.exceptions.RegistrationException;
import com.suse.addons.registry.AddonRegistry;

/**
 * Class representing a single add-on bean.
 */
public class Addon {

    // JNDI name used to lookup the registry
    // TODO: Read this from a config file
    private static final String ADDONS = "suse/addons";

    private String name;
    private String group;
    private String description;
    private String entry;

    /**
     * Default constructor.
     */
    public Addon() {
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public Addon setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @return the group
     */
    public String getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public Addon setGroup(String group) {
        this.group = group;
        return this;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public Addon setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * @return the entry
     */
    public String getEntry() {
        return entry;
    }

    /**
     * @param entry the entry to set
     */
    public Addon setEntry(String entry) {
        this.entry = entry;
        return this;
    }

    /**
     * Register this add-on.
     */
    public void register() throws RegistrationException {
        try {
            Context context = (Context) new InitialContext().lookup("java:comp/env");
            AddonRegistry registry = (AddonRegistry) context.lookup(ADDONS);
            registry.register(this);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Unregister this add-on.
     */
    public void unregister() {
        try {
            Context context = (Context) new InitialContext().lookup("java:comp/env");
            AddonRegistry registry = (AddonRegistry) context.lookup(ADDONS);
            registry.unregister(this);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
