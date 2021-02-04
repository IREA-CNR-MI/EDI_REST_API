package it.cnr.irea.edi.template_manager.domain.ediml.v2;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

public class Element {
    public enum Requiredness
    {
        FOR_ALL("forAll"),
        NA("NA");
        private String code;

        Requiredness(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }
    private String id;
    private String root;
    private Requiredness mandatory;
    private String representsElement;
    private String alternativeTo;
    private List<Item> items = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public Requiredness getMandatory() {
        return mandatory;
    }

    public void setMandatory(Requiredness mandatory) {
        this.mandatory = mandatory;
    }

    public String getRepresentsElement() {
        return representsElement;
    }

    public void setRepresentsElement(String representsElement) {
        this.representsElement = representsElement;
    }

    public String getAlternativeTo() {
        return alternativeTo;
    }

    public void setAlternativeTo(String alternativeTo) {
        this.alternativeTo = alternativeTo;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(item);
    }
}
