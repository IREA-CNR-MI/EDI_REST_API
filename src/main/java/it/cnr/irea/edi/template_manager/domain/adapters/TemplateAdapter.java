package it.cnr.irea.edi.template_manager.domain.adapters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import it.cnr.irea.edi.template_manager.JsonLdConfig;
import it.cnr.irea.edi.template_manager.model.template.generated.Datasources;
import it.cnr.irea.edi.template_manager.model.template.generated.EndpointTypes;
import it.cnr.irea.edi.template_manager.model.template.generated.Settings;
import it.cnr.irea.edi.template_manager.model.template.generated.Template;
import it.cnr.irea.edi.template_manager.persistence.TemplateRepository;

import javax.persistence.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * <p>The TemplateAdapter wraps the @see <a href="Template">Template</a></p>
 *
 */
@JsonPropertyOrder({
        "@context",
        "@type",
        "@id",
        "settings",
        "groups"
})
@Entity(name = "stored_template")
public class TemplateAdapter {
    @Id()
    @JsonProperty("@id")
    public String urn;

    @Transient
    private Template template;
    @Transient
    private JsonLdConfig config;
    @Transient
    @JsonProperty("@context")
    public String context;
    @Transient
    @JsonProperty("@type")
    public String type = "Template";
    @Column(name = "xml", columnDefinition = "text")
    @Lob
    public String xmlString;

    public static TemplateAdapter fromURN(JsonLdConfig config, TemplateRepository repository, String urn) throws JAXBException {
        TemplateAdapter adapter = repository.findById(urn).get();
        String templateString = adapter.xmlString;
        JAXBContext context = JAXBContext.newInstance(Template.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringReader reader = new StringReader(templateString);

        Template t = (Template) unmarshaller.unmarshal(reader);

        TemplateAdapter template = new TemplateAdapter(config, t);
        return template;
    }

    public TemplateAdapter() {
    }

    public TemplateAdapter(final JsonLdConfig config, Template template) throws JAXBException {
        this.template = template;
        this.context = config.jsonLdContext;
        urn = "urn:edi:schemas:template:" + UUID.randomUUID();
        setXmlString();
    }

    public Settings getSettings() {
        return template.getSettings();
    }

    public EndpointTypes getEndpointTypes() {
        return template.getEndpointTypes();
    }

    public Datasources getDatasources() {
        return template.getDatasources();
    }

    public List<GroupAdapter> getGroups() {
        return template.getGroup().stream().map(g -> new GroupAdapter(context, g)).collect(Collectors.toList());
    }

    public Template getTemplate() {
        return template;
    }

    public void setXmlString() throws JAXBException {
        try {
            // create an instance of `JAXBContext`
            JAXBContext context = JAXBContext.newInstance(Template.class);

            // create an instance of `Marshaller`
            Marshaller marshaller = context.createMarshaller();

            // enable pretty-print XML output
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            // write XML to `StringWriter`
            StringWriter sw = new StringWriter();

            // convert book object to XML
            marshaller.marshal(template, sw);

            // print the XML
            xmlString = sw.toString();
        } catch (JAXBException ex) {
            xmlString = null;
            ex.printStackTrace();
            throw ex;
        }
    }

}
