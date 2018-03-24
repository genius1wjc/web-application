package org.wjc.maven.logging;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public final class LogUtil {

    private static final String LOGGING_FOLDER = "application-logs";

    private LogUtil() {
        // Disable instantiation
    }

    @NotNull
    public static Logger getLogger(@NotNull final String className) {
        new File(LOGGING_FOLDER).mkdir();
        Logger logger = Logger.getLogger(className);
        try {
            FileHandler handler = new FileHandler(LOGGING_FOLDER + "/" + className + ".txt");
            handler.setFormatter(new LogFormatter());
            logger.addHandler(handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return logger;
    }
}
