package com.suse.addons.registry;

import java.util.ArrayList;
import java.util.List;

import com.suse.addons.model.Addon;

/**
 * Singleton add-on registry class.
 */
public class AddonRegistry {

    private static AddonRegistry instance;
    private List<Addon> addons;

    private AddonRegistry() {
        this.addons = new ArrayList<Addon>();
    }

    public static AddonRegistry getInstance() {
        if (instance == null) {
            instance = new AddonRegistry();
        }
        return instance;
    }

    public List<Addon> getAddons() {
        return this.addons;
    }

    public void register(Addon addon) {
        this.addons.add(addon);
    }

    public void unregister(Addon addon) {
        this.addons.remove(addon);
    }
}
