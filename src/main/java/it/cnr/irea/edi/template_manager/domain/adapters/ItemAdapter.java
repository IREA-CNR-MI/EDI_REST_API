package it.cnr.irea.edi.template_manager.domain.adapters;

import it.cnr.irea.edi.template_manager.domain.strategies.ItemDocumentor;
import it.cnr.irea.edi.template_manager.model.template.generated.Item;
import it.cnr.irea.edi.template_manager.model.template.generated.Label;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ItemAdapter {
    private Item item;
    private ItemDocumentor documentor = ItemDocumentor.getItemDocumentor(this);
    private String context;
    private ElementAdapter element;

    ItemAdapter(String context, ElementAdapter elementAdapter, Item item) {
        this.item = item;
        this.context = context;
        element = elementAdapter;
    }

    public String getContext() {
        return context;
    }

    public String getId() {
        return item.getId();
    }

    public boolean isFixed() {
        return item.isIsFixed();
    }

    public String getHasDatatype() {
        return item.getHasDatatype();
    }

    public BigInteger getHasIndex() {
        return item.getHasIndex();
    }

    public String getDatasource() {
        return item.getDatasource();
    }

    public String getField() {
        return item.getField();
    }

    public ElementAdapter getElement() {
        return element;
    }

    public boolean isLanguageNeutral() {
        return item.getIsLanguageNeutral() != null && item.getIsLanguageNeutral().equalsIgnoreCase("true");
    }

    public List<Label> getLabels() {
        List<Label> labels = new ArrayList<Label>();
        for (Object child : item.getHelpOrLabel()) {
            if (child instanceof Label) {
                labels.add((Label) child);
            }
        }
        return labels;
    }

    public String getDocumentation() {
        if (documentor != null) return documentor.getDocumentation();
        return null;
    }

}
