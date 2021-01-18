package it.cnr.irea.edi.template_manager.domain.strategies;

import it.cnr.irea.edi.template_manager.domain.adapters.ItemAdapter;
import it.cnr.irea.edi.template_manager.model.template.generated.Label;

import java.util.*;

public class GenericItemDocumentor extends ItemDocumentor {
    private Map<String, String> datatypeDefinitions = new HashMap<String, String>() {{
        put("string", "https://schema.org/Text");
        put("text", "https://schema.org/Text");
        put("autoCompletion", "https://schema.org/Text");
        put("date", "https://schema.org/Date");
        put("boolean", "https://schema.org/Boolean");
        put("URL", "https://schema.org/url");
    }};
    private List<String> excludedDatatypes = Arrays.asList(
            "select",
            "function"
    );

    GenericItemDocumentor(ItemAdapter item) {
        super(item);
    }

    @Override
    public String getDocumentation() {
        if (item.isFixed()) return null;
        if (excludedDatatypes.contains(item.getHasDatatype())) return null;

        List<String> results = new ArrayList<>();
        results.add(makeKeyValue("@id", item.getId()));
        if (datatypeDefinitions.get(item.getHasDatatype()) != null) {
            results.add(makeKeyValue("@type", datatypeDefinitions.get(item.getHasDatatype())));
        } else {
            results.add(makeKeyValue("@type", item.getHasDatatype()));
        }
        results.add(makeKeyValue("required", String.valueOf(item.getElement().isRequired())));
        if (item.getDatasource() != null) results.add(makeKeyValue("datasource", item.getDatasource()));
        if (item.getField() != null) results.add(makeKeyValue("field", item.getField()));
        if (item.getLabels() != null && item.getLabels().size() > 0) {
            List<String> labels = new ArrayList<>();
            for (Label label : item.getLabels()) {
                labels.add("{" + makeKeyValue("content", label.getContent()) + ", " + makeKeyValue("lang", label.getLang()) + "}");
            }
            results.add("\"labels\": [" + String.join(", ", labels) + "]");
        }
        return "{" + String.join(", ", results) + "}";
    }

}
