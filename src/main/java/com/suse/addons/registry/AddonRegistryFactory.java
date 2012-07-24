package com.suse.addons.registry;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;

/**
 * Implementation of {@link ObjectFactory} to return the singleton instance of
 * the {@link AddonRegistry}.
 */
public class AddonRegistryFactory implements ObjectFactory {

    @Override
    public Object getObjectInstance(Object arg0, Name arg1, Context arg2,
            Hashtable<?, ?> arg3) throws Exception {
        return AddonRegistry.getInstance();
    }
}
