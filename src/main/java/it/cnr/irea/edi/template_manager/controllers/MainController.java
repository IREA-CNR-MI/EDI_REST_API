package it.cnr.irea.edi.template_manager.controllers;

import it.cnr.irea.edi.template_manager.JsonLdConfig;
import it.cnr.irea.edi.template_manager.domain.adapters.ElementAdapter;
import it.cnr.irea.edi.template_manager.domain.adapters.GroupAdapter;
import it.cnr.irea.edi.template_manager.domain.adapters.ItemAdapter;
import it.cnr.irea.edi.template_manager.domain.adapters.TemplateAdapter;
import it.cnr.irea.edi.template_manager.model.template.generated.Template;
import it.cnr.irea.edi.template_manager.persistence.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class MainController {
    @Autowired
    JsonLdConfig config;
    @Autowired
    TemplateRepository repository;

    @PostMapping(path = "/templates", consumes = MediaType.APPLICATION_XML_VALUE, produces = "application/ld+json")
    public TemplateAdapter testTemplate(@RequestBody String templateString) throws JAXBException {
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
        TemplateAdapter adapter = repository.findById(urn).get();
        return adapter.xmlString;
    }
    @GetMapping(path = "/templates/{id}/structure", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getTemplateStructure(@PathVariable("id") String urn) throws JAXBException {
        TemplateAdapter adapter = TemplateAdapter.fromURN(config, repository, urn);
        List<String> items = new ArrayList<>();
        for (GroupAdapter group : adapter.getGroups()) {
            for (ElementAdapter element : group.getElements()) {
                for (ItemAdapter item : element.getItems()) {
                    String doc = item.getDocumentation();

                    if (doc != null) items.add(doc);
                }
            }
        }
        return "\"@context\": \"/templates/" + urn + "/structure\", \"@type\": \"ItemCollection\", \"members\": [" + String.join(", ", items) + "]";
    }
}
