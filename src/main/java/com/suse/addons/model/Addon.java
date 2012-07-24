package com.suse.addons.model;

/**
 * Class representing a single add-on bean.
 */
public class Addon {

    private String name;
    private String entryPoint;

    /**
     * Constructor.
     * @param name
     * @param entryPoint
     */
    public Addon(String name, String entryPoint) {
        this.name = name;
        this.entryPoint = entryPoint;
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
     * @return the entryPoint
     */
    public String getEntryPoint() {
        return entryPoint;
    }
    /**
     * @param entryPoint the entryPoint to set
     */
    public void setEntryPoint(String entryPoint) {
        this.entryPoint = entryPoint;
    }
}
