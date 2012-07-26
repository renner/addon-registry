package com.suse.addons.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.suse.addons.registry.AddonRegistry;


/**
 * Class representing a single add-on bean.
 */
public class Addon {

    private static final String ADDONS = "susemanager/addons";
    private String name;
    private String entry;

    /**
     * Constructor.
     * @param name
     * @param entry
     */
    public Addon(String name, String entry) {
        this.name = name;
        this.entry = entry;
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
    public void setName(String name) {
        this.name = name;
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
    public void setEntry(String entry) {
        this.entry = entry;
    }

    /**
     * Register itself.
     */
    public void register() throws Exception {
        try {
            Context context = (Context) new InitialContext().lookup("java:comp/env");
            AddonRegistry registry = (AddonRegistry) context.lookup(ADDONS);
            registry.register(this);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
