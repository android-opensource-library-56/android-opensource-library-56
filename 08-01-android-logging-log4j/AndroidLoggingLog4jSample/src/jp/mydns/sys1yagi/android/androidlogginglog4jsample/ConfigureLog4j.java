package jp.mydns.sys1yagi.android.androidlogginglog4jsample;

import java.io.File;

import org.apache.log4j.Level;

import android.content.Context;
import de.mindpipe.android.logging.log4j.LogConfigurator;

public class ConfigureLog4j {
    public static void configure(Context context) {
        final LogConfigurator logConfigurator = new LogConfigurator();
        logConfigurator
                .setLogCatPattern("%d{yyyy-MM-dd HH:mm:ss.SSS} %p:%r:%c:%m%n");
        logConfigurator.setRootLevel(Level.DEBUG);

        // logConfigurator.setUseFileAppender(false);
        // logConfigurator.setUseLogCatAppender(false);
        File logFileDir = context.getExternalFilesDir(null);
        String logFilePath = new File(logFileDir, "myapp.log")
                .getAbsolutePath();
        logConfigurator.setFileName(logFilePath);

        logConfigurator.configure();
    }
}
