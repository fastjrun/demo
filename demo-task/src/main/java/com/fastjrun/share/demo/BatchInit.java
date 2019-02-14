/*
 * Copyright (C) 2019 Fastjrun, Inc. All Rights Reserved.
 */
package com.fastjrun.share.demo;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BatchInit {

    private final static Logger log = Logger.getLogger(BatchInit.class);

    public static ApplicationContext appContext = null;

    public static final String SPRING_CONFIG_FILE = "applicationContext.xml";

    public void startup() throws Exception {
        appContext = new ClassPathXmlApplicationContext(SPRING_CONFIG_FILE);
        Scheduler scheduler = (Scheduler) appContext.getBean("scheduler");
        scheduler.start();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                log.info("I am being killed!");
                try {
                    scheduler.shutdown();
                } catch (Exception e) {
                    log.info("I am killed!");
                }
            }
        });
    }

    public static void main(String[] args) {
        try {
            BatchInit batch = new BatchInit();
            batch.startup();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
