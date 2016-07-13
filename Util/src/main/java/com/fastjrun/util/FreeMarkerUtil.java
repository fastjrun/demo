package com.fastjrun.util;

import java.io.BufferedWriter;
import java.io.StringWriter;
import java.util.Locale;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class FreeMarkerUtil {

    private Configuration configuration;

    public FreeMarkerUtil(String ftlDir) {
        configuration = new Configuration(Configuration.VERSION_2_3_22);
        ClassTemplateLoader loader = new ClassTemplateLoader(this.getClass(), ftlDir);
        configuration.setTemplateLoader(loader);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    public String renderFile(Object dataModel, String ftlFile) throws Exception {
        Template template = configuration.getTemplate(ftlFile, Locale.CHINA, "UTF-8");
        StringWriter stringWriter = new StringWriter();
        BufferedWriter writer = new BufferedWriter(stringWriter);
        template.process(dataModel, writer);
        writer.flush();
        return stringWriter.toString();
    }

}
