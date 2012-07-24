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
        this.init();
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

    /**
     * Put some example data.
     */
    private void init() {
        this.register(new Addon("foobar1", "entrypoint1"));
        this.register(new Addon("foobar2", "entrypoint2"));
        this.register(new Addon("foobar3", "entrypoint3"));
        this.register(new Addon("foobar4", "entrypoint4"));
    }
}
