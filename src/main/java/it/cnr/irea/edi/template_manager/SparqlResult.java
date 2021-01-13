package it.cnr.irea.edi.template_manager;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class SparqlResult {
    public static class Head {
        @JsonProperty("link")
        List<String> link;
        @JsonProperty("vars")
        List<String> vars;
    }

    public static class Field {
        @JsonProperty("type")
        String type;
        @JsonProperty("xml:lang")
        String xml_lang;
        @JsonProperty("value")
        String value;

        @Override
        public String toString() {
            return "Field{" +
                    "type='" + type + '\'' +
                    ", xml_lang='" + xml_lang + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    public static class Results {
        @JsonProperty("distinct")
        boolean distinct;
        @JsonProperty("ordered")
        boolean ordered;
        @JsonProperty("bindings")
        List<Map<String, Field>> bindings;
    }

    @JsonProperty("head")
    Head head;
    @JsonProperty("results")
    Results results;

    public Map<String, Field> firstRow() {
        if (!results.bindings.isEmpty()) {
            return results.bindings.get(0);
        }
        return null;
    }

    public long size() {
        return results.bindings.size();
    }
}
