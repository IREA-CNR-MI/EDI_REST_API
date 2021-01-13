package it.cnr.irea.edi.template_manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.cnr.irea.edi.template_manager.domain.TemplateRepository;
import it.cnr.irea.edi.template_manager.domain.adapters.TemplateAdapter;
import it.cnr.irea.edi.template_manager.model.template.generated.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

@RestController
@RequestMapping("v1")
public class MainController {
    @Autowired
    JsonLdConfig config;
    @Autowired
    TemplateRepository repository;

    @PostMapping(path = "/templates", consumes = MediaType.APPLICATION_XML_VALUE, produces = "application/ld+json")
    public TemplateAdapter testTemplate(@RequestBody String templateString) throws JAXBException, JsonProcessingException {
        JAXBContext context = JAXBContext.newInstance(Template.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringReader reader = new StringReader(templateString);

        System.out.println("Received template " + templateString);
        Template t = (Template) unmarshaller.unmarshal(reader);

        TemplateAdapter template = new TemplateAdapter(config, t);
        repository.save(template);
        return template;
    }

    @GetMapping(path = "/templates/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public String getTemplate(@PathVariable("id") String urn) {
        System.out.println("Getting template " + urn);
        TemplateAdapter adapter = repository.findById(urn).get();
        System.out.println("Template adapter " + adapter);
        System.out.println("Template " + adapter.xmlString);
        return adapter.xmlString;
    }
}
