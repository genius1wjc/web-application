package org.wjc.maven.util;

import static org.wjc.maven.constant.CommonConstants.ENTITY_MANAGER_FACTORY;

public final class DbUtil {

    private DbUtil() {
        // Prevent instantiation of this class
    }

    public static void closeDb() {
        ENTITY_MANAGER_FACTORY.close();
    }
}
