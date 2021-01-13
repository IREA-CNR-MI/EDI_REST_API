package it.cnr.irea.edi.template_manager.domain;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class Codelist extends Datasource {
    protected String uri;
    protected final String sparql = "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#> " +
            "PREFIX dct:<http://purl.org/dc/terms/> " +
            "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
            "PREFIX skos:<http://www.w3.org/2004/02/skos/core#> " +

            "SELECT DISTINCT <" + uri + "> AS ?uri ?c ?l ?a ?z " +
            "WHERE { " +
            "	{ " +
            "	  ?c rdf:type skos:Concept. " +
            "	  ?c skos:inScheme <" + uri + ">. " +
            "	  OPTIONAL { " +
            "	      ?c skos:prefLabel ?l. " +
            "	      FILTER ( LANG(?l) = \"en\" ) " +
            "	  } " +
            "	} " +

            "	OPTIONAL { " +
            "	    ?c skos:prefLabel ?z. " +
            "	    FILTER ( LANG(?z) = \"zxx\" ) " +
            "	} " +
            "	OPTIONAL { " +
            "	    ?c skos:prefLabel ?a. " +
            "	    FILTER ( LANG(?a) = \"" + "it" + "\" ) " +
            "	} " +
            "	" +
            "} " +
            "ORDER BY ASC(?a) ASC(?l)";

    public Codelist() {
        sparqlQuery = sparql;
        System.out.println("Query is " + sparqlQuery);
    }

    public Codelist(it.cnr.irea.edi.template_manager.model.template.generated.Codelist codelist) {
        type = DatasourceType.CODELIST;
        id = codelist.getId();
        uri = codelist.getUri();
        url = codelist.getUrl();
        endpointType = EndpointType.getEndpointById(codelist.getEndpointType());
        if (endpointType == null) {
            throw new IllegalArgumentException("EndpointType " + codelist.getEndpointType() + " not declared");
        }
        datasources.put(id, this);
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
