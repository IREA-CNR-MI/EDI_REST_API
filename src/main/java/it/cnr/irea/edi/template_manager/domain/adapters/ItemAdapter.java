package it.cnr.irea.edi.template_manager.domain.adapters;

import it.cnr.irea.edi.template_manager.model.template.generated.Item;
import it.cnr.irea.edi.template_manager.model.template.generated.Label;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ItemAdapter {
    private Item item;

    ItemAdapter(Item item) {
        this.item = item;
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


}
