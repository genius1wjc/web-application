package org.wjc.maven.constant;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class CommonConstants {

    // "" is required to make LoginView as the starting view
    public static final String LOGIN_VIEW = "";
    public static final String LOCATION_VIEW = "location_view";
    public static final String UPLOAD_VIEW = "upload_view";

    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("BookShare");

    private CommonConstants() {
        // Prevent instantiation of this class
    }
}
