package jp.mydns.sys1yagi.android.androidlogginglog4jsample;

import org.apache.log4j.Logger;

public class SampleClass {
    private final static Logger log = Logger.getLogger(SampleClass.class);

    void doSomething() {
        log.debug("logging");
    }
}
