package com.suse.addons.registry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.suse.addons.model.Addon;

/**
 * Singleton add-on registry class.
 */
public class AddonRegistry {

    private static AddonRegistry instance;
    private List<Addon> addons;

    /**
     * Private constructor.
     */
    private AddonRegistry() {
        this.addons = new ArrayList<Addon>();
    }

    /**
     * Get the singleton instance of this class.
     * @return instance
     */
    public static AddonRegistry getInstance() {
        if (instance == null) {
            instance = new AddonRegistry();
        }
        return instance;
    }

    /**
     * Register a given add-on.
     * @param addon
     */
    public synchronized void register(Addon addon) throws Exception {
        if (addons.contains(addon)) {
            throw new Exception("Addon " + addon.getName() + " already exists!");
        } else {
            this.addons.add(addon);   
        }
    }

    /**
     * Unregister a given add-on.
     * @param addon
     */
    public synchronized void unregister(Addon addon) {
        this.addons.remove(addon);
    }

    /**
     * Return add-ons as an unmodifiable list to make it "read-only" for
     * consumers.
     */
    public List<Addon> getAddons() {
        return Collections.unmodifiableList(this.addons);
    }
}
