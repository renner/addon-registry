package com.suse.addons.model;

/**
 * Class representing a single add-on bean.
 */
public class Addon {

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
}
