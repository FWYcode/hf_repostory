package com.hf.game;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Created by 123 on 2019-6-23.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Test1 {
    @Autowired
    RestTemplate restTemplate;

    @Test
    public void testStatic() throws Exception {
        ResponseEntity<Map> forEntity = restTemplate.getForEntity("http://localhost:8083/item/category/allbyserver", Map.class);
        if (forEntity == null) {
            return;
        }
        Map body = forEntity.getBody();
        Configuration configuration = new Configuration(Configuration.getVersion());
        FileTemplateLoader fileTemplateLoader = new FileTemplateLoader(new File("D:/dve_set/hf-parent/hf-manager-page-static/src/main/resources/templates/"));
        configuration.setTemplateLoader(fileTemplateLoader);
        Template index_category = configuration.getTemplate("index_category.ftl", "utf-8");
        FileWriter fileWriter = new FileWriter(new File("D:/dve_set/statics/xc-ui-pc-static-portal/include/index_category1.html"));
        System.out.println(body.toString());
        index_category.process(body,fileWriter);
        fileWriter.flush();
        fileWriter.close();
    }

}
