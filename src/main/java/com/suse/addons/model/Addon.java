package com.suse.addons.model;

import com.suse.addons.exceptions.RegistrationException;
import com.suse.addons.registry.AddonRegistry;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Class representing a single add-on bean.
 */
public class Addon {

    // JNDI name used to lookup the registry
    // TODO: Read this from a config file
    private static final String ADDONS = "suse/addons";

    private String id;
    private String name;
    private String group;
    private String description;
    private String entry;
    private String iconURI;

    /**
     * Default constructor.
     */
    public Addon() {
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public Addon setId(String id) {
        this.id = id;
        return this;
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
     * Set entry point from the resource template.
     * 
     * @param resource
     * @param base
     * @param params  Params to substitute in the template.
     * @return
     * @throws IOException 
     */
    public Addon setEntryFromResource(String resource, Object base, Map<String, String> params) throws IOException {
        this.setEntryFromStream(base.getClass().getResourceAsStream(resource), params);
        return this;
    }


    /**
     * Set entry point from the stream template.
     * 
     * @param stream
     * @param params
     * @return
     * @throws IOException
     */
    public Addon setEntryFromStream(InputStream stream, Map<String, String> params) throws IOException {
        StringBuilder out = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));
        String read = br.readLine();
        while(read != null) {
            out.append(read).append("\n");
            read = br.readLine();
        }

        String entryValue = out.toString();
        for (String key : params.keySet()) {
            entryValue = entryValue.replace(key, params.get(key));
        }

        this.setEntry(entryValue);
        return this;
    }


    /**
     * @return the iconURI
     */
    public String getIconURI() {
        return iconURI;
    }

    /**
     * @param iconURI the iconURI to set
     */
    public Addon setIconURI(String iconURI) {
        this.iconURI = iconURI;
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
        } catch (NamingException ex) {
            Logger.getLogger(Addon.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (NamingException ex) {
            Logger.getLogger(Addon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
