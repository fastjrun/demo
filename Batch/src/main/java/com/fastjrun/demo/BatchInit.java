package com.fastjrun.demo;

import java.io.OutputStream;
import java.net.Socket;

import org.quartz.Scheduler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fastjrun.helper.StringHelper;
import com.fastjrun.system.ShutdownService;

public class BatchInit {

    public static ApplicationContext appContext=null;

    public static final String SPRING_CONFIG_FILE = "applicationContext.xml";

    public static final String COMMAND_START_DEFAULT = "startup";

    public static final String COMMAND_SHUTDOWN_DEFAULT = "shutdown";

    public void startup(String command) throws Exception {
        appContext = new ClassPathXmlApplicationContext(SPRING_CONFIG_FILE);
        Scheduler scheduler= (Scheduler) appContext
                .getBean("scheduler");
        ShutdownService shutdownService = (ShutdownService) appContext
                .getBean("shutdownService");

        if (COMMAND_START_DEFAULT.equalsIgnoreCase(command)) {
            new Thread(shutdownService).start();
            scheduler.start();
        } else if (COMMAND_SHUTDOWN_DEFAULT.equalsIgnoreCase(command)) {
            scheduler.shutdown();
            shutdown(command, shutdownService.getPort());
        }
        // ((ClassPathXmlApplicationContext)appContext).close();
    }

    public void shutdown(String command, int shutdownPort) throws Exception {

        Socket socket = new Socket("127.0.0.1", shutdownPort);

        OutputStream outputStream = socket.getOutputStream();

        outputStream.write(command.getBytes(StringHelper.DEFAULT_ENCODING));
        outputStream.flush();
        socket.shutdownOutput();

        outputStream.close();
        socket.close();
        System.exit(0);
    }

    public static void main(String[] args) {
        try {
            String command = args[0];
            BatchInit batch = new BatchInit();
            batch.startup(command);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
