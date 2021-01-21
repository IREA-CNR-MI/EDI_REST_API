package it.cnr.irea.edi.template_manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.cnr.irea.edi.template_manager.domain.adapters.TemplateAdapter;
import it.cnr.irea.edi.template_manager.domain.ediml.v2.EDIML;
import it.cnr.irea.edi.template_manager.model.template.generated.Template;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class EDIMLTests {
    static String templateString;
    static Template template;

    public static void readTemplate() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        Process process = Runtime.getRuntime().exec("ls -al src/main/resources");
        printResults(process);
        Path filePath = Paths.get("src/main/resources/test_template.xml");
        Charset charset = StandardCharsets.UTF_8;
        try {
            List<String> lines = Files.readAllLines(filePath, charset);
            for (String line : lines) {
                sb.append(line);
            }
        } catch (IOException ex) {
            System.out.format("I/O Exception", ex);
        }
        templateString = sb.toString();
        JAXBContext context = JAXBContext.newInstance(Template.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringReader reader = new StringReader(templateString);

        System.out.println("Received template " + templateString);
        template = (Template) unmarshaller.unmarshal(reader);

    }

    public static void printResults(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }
    @Test
    public void create() throws IOException, JAXBException {
        readTemplate();
        JsonLdConfig config = new JsonLdConfig();

        TemplateAdapter templateAdapter = new TemplateAdapter(config, template);
        EDIML ediml = new EDIML(templateAdapter);

        JAXBContext contextObj = JAXBContext.newInstance(EDIML.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshallerObj.marshal(ediml, new FileOutputStream("src/main/resources/ediml_output.xml"));
    }
}
