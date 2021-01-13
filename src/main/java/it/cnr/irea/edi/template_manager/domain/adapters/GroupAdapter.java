package it.cnr.irea.edi.template_manager.domain.adapters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import it.cnr.irea.edi.template_manager.model.template.generated.Element;
import it.cnr.irea.edi.template_manager.model.template.generated.Help;
import it.cnr.irea.edi.template_manager.model.template.generated.Label;
import it.cnr.irea.edi.template_manager.model.template.generated.Group;

import java.util.ArrayList;
import java.util.List;
@JsonPropertyOrder({
        "@context",
        "@type",
        "id",
        "labels",
        "helps",
        "elements"
})
public class GroupAdapter {
    private Group xmlGroup;

    @JsonProperty("@context")
    public String context;
    @JsonProperty("@type")
    public String type = "Group";

    public GroupAdapter(String context, Group g) {
        this.context = context;
        xmlGroup = g;
    }

    public String getId() {
        return xmlGroup.getId();
    }

    public List<Help> getHelps() {
        List<Help> results = new ArrayList<Help>();
        for (Object child : xmlGroup.getHelpOrLabelOrElement()) {
            if (child instanceof Help) {
                results.add((Help) child);
            }
        }
        return results;
    }

    public List<Label> getLabels() {
        List<Label> results = new ArrayList<Label>();
        for (Object child : xmlGroup.getHelpOrLabelOrElement()) {
            if (child instanceof Label) {
                results.add((Label) child);
            }
        }
        return results;
    }

    public List<ElementAdapter> getElements() {
        List<ElementAdapter> results = new ArrayList<ElementAdapter>();
        for (Object child : xmlGroup.getHelpOrLabelOrElement()) {
            if (child instanceof Element) {
                results.add(new ElementAdapter(context, (Element) child));
            }
        }
        return results;
    }
}
