package it.cnr.irea.edi.template_manager.domain.ediml.v2;

import it.cnr.irea.edi.template_manager.domain.adapters.TemplateAdapter;
import it.cnr.irea.edi.template_manager.model.template.generated.XsltChain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "elements")
@XmlAccessorType(XmlAccessType.FIELD)
public class EDIML {
    private String ediVersion = "2";
    private String version = "1.00";
    private String template;
    private String templateDocument;
    private String starterKit = "noSK";
    private String starterKitUri;
    private String fileId;
    private String fileUri;
    private String user;
    private String baseDocument;
    private it.cnr.irea.edi.template_manager.model.template.generated.XsltChain xsltChain;
    @XmlElement(name = "element")
    private List<Element> elements = null;

    public EDIML() {
    }

    public EDIML(TemplateAdapter template) {
        baseDocument = template.getSettings().getBaseDocument();
        this.template = template.urn;
//        templateDocument = template.xmlString;
        this.xsltChain = template.getSettings().getXsltChain();
    }

    public String getEdiVersion() {
        return ediVersion;
    }

    public void setEdiVersion(String ediVersion) {
        this.ediVersion = ediVersion;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getTemplateDocument() {
        return templateDocument;
    }

    public void setTemplateDocument(String templateDocument) {
        this.templateDocument = templateDocument;
    }

    public String getStarterKit() {
        return starterKit;
    }

    public void setStarterKit(String starterKit) {
        this.starterKit = starterKit;
    }

    public String getStarterKitUri() {
        return starterKitUri;
    }

    public void setStarterKitUri(String starterKitUri) {
        this.starterKitUri = starterKitUri;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileUri() {
        return fileUri;
    }

    public void setFileUri(String fileUri) {
        this.fileUri = fileUri;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBaseDocument() {
        return baseDocument;
    }

    public void setBaseDocument(String baseDocument) {
        this.baseDocument = baseDocument;
    }

    public XsltChain getXsltChain() {
        return xsltChain;
    }

    public void setXsltChain(XsltChain xsltChain) {
        this.xsltChain = xsltChain;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public Element elementById(String id) {
        for (Element element : elements) {
            if (element.getId().equalsIgnoreCase(id)) {
                return element;
            }
        }
        return null;
    }

    public void addElement(Element element) {
        if (elements == null) {
            elements = new ArrayList<>();
        }
        elements.add(element);
    }
}
