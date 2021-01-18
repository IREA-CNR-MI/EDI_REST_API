package it.cnr.irea.edi.template_manager.controllers;

import it.cnr.irea.edi.template_manager.JsonLdConfig;
import it.cnr.irea.edi.template_manager.persistence.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/doc")
public class ApiDocController {
    @Autowired
    JsonLdConfig config;
    @Autowired
    TemplateRepository repository;

    String baseUrl = "https://templates.edi.get-it.it/api/v1/doc";

    @GetMapping(value = "/templates", produces = "application/ld+json")
    public String templates() {
        String doc = "{\n" +
                "  \"@context\": \"http://www.w3.org/ns/hydra/context.jsonld\",\n" +
                "  \"@id\": \"" + baseUrl+ "\",\n" +
                "  \"@type\": \"Link\",\n" +
                "  \"supportedOperation\": [\n" +
                "    {\n" +
                "      \"@type\": \"Operation\",\n" +
                "      \"method\": \"POST\",\n" +
                "      \"expects\": \"" + baseUrl+ "/#Template\",\n" +
                "      \"returns\": \"" + baseUrl+ "#Template\",\n" +
                "      \"returnsHeader\": [\n" +
                "        \"Content-Type: application/ld+json\",\n" +
                "        \"Content-Length\"],\n" +
                "      \"expectsHeader\": [\n" +
                "        \"Content-Type: application/xml\"\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        return doc;
    }

}
