package it.cnr.irea.edi.template_manager.domain.adapters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import it.cnr.irea.edi.template_manager.model.template.generated.Element;
import it.cnr.irea.edi.template_manager.model.template.generated.Help;
import it.cnr.irea.edi.template_manager.model.template.generated.Item;
import it.cnr.irea.edi.template_manager.model.template.generated.Label;

import java.util.ArrayList;
import java.util.List;
@JsonPropertyOrder({
        "@context",
        "@type",
        "id",
        "multiple",
        "helps",
        "items"
})
public class ElementAdapter {
    private Element element;

    @JsonProperty("@context")
    public String context;
    @JsonProperty("@type")
    public String type = "Element";

    ElementAdapter(String context, Element element) {
        this.element = element;
        this.context = context;
    }

    public String getId() {
        return element.getId();
    }

    public boolean isMultiple() {
        return element.isIsMultiple();
    }
    public boolean isRequired() { return element.isIsMandatory(); }
    public String getRoot() {
        for (Object object : element.getHelpOrLabelOrHasRoot()) {
            if (object instanceof Label || (object instanceof Help)) {
            } else {
                return (String) object;
            }
        }
        return null;
    }
    public List<ItemAdapter> getItems() {
        List<ItemAdapter> results = new ArrayList<ItemAdapter>();

        for ( Item child : element.getProduces().getItem()) {
            results.add(new ItemAdapter(context, this, child));
        }
        return results;
    }
}
