package it.cnr.irea.edi.template_manager.domain.strategies;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.cnr.irea.edi.template_manager.domain.adapters.ItemAdapter;

/**
 * <p>Documents a generic item</p>
 * <p>Acts as a base class for the specific documentors</p>
 * <p>It is implemented as a strategy design pattern</p>
 */
public class ItemDocumentor {
    @JsonIgnore
    protected ItemAdapter item;

    ItemDocumentor(ItemAdapter item) {
        this.item = item;
    }

    public static ItemDocumentor getItemDocumentor(ItemAdapter item) {
        /// TODO: actually implement documentor selection
        return new GenericItemDocumentor(item);
    }

    protected String makeKeyValue(String key, String value) {
        return "\"" + key + "\": \"" + value + "\"";
    }

    public String getDocumentation() {
        return null;
    }
}
