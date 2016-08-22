package tuxedo.wheel.template;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import lombok.NonNull;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.Resources;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

public class TemplatePool {
    protected final Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
    protected final StringTemplateLoader templateLoader = new StringTemplateLoader();

    public TemplatePool() {
        configuration.setTemplateLoader(templateLoader);
    }

    public TemplatePool importText(@NonNull String templateName, @NonNull String templateText) {
        templateLoader.putTemplate(templateName, templateText);
        return this;
    }

    public TemplatePool importFile(String templateName, @NonNull String templateFile) throws IOException {
        return importText(templateName, Files.toString(new File(templateFile), Charsets.UTF_8));
    }

    public TemplatePool importResource(String templateName, @NonNull String templateResource) throws IOException {
        return importText(templateName, Resources.toString(Resources.getResource(templateResource), Charsets.UTF_8));
    }

    public String execute(@NonNull String templateName, Object dataModel) throws IOException, TemplateException {
        try (OutputStream os = new ByteArrayOutputStream(); OutputStreamWriter osw = new OutputStreamWriter(os);) {
            configuration.getTemplate(templateName).process(dataModel, osw);
            return os.toString();
        }
    }
}
