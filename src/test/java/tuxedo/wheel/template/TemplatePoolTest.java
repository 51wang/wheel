package tuxedo.wheel.template;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import freemarker.template.TemplateException;

public class TemplatePoolTest {
    private final TemplatePool templatePool = new TemplatePool();
    private final Map<String, Object> dataModel = new HashMap<>();

    @BeforeTest
    public void before() throws IOException {
        templatePool.importText("text", "text=${a}")//
                .importFile("file", "src/test/resources/template/file.ftl")//
                .importResource("resource", "template/resource.ftl");
        dataModel.put("a", "xyz");
    }

    @Test
    public void testText() throws IOException, TemplateException {
        Assert.assertEquals(templatePool.execute("text", dataModel), "text=xyz");
    }

    @Test
    public void testFile() throws IOException, TemplateException {
        Assert.assertEquals(templatePool.execute("file", dataModel), "file=xyz");
    }

    @Test
    public void testResource() throws IOException, TemplateException {
        Assert.assertEquals(templatePool.execute("resource", dataModel), "resource=xyz");
    }
}
