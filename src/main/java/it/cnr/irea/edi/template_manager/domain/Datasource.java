package it.cnr.irea.edi.template_manager.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.cnr.irea.edi.template_manager.SparqlResult;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class Datasource {
    public enum DatasourceType {
        SPARQL,
        CODELIST,
        SINGLETON
    }
    protected static Map<String, Datasource> datasources = new HashMap<>();

    protected String id;
    protected String sparqlQuery;
    protected DatasourceType type;
    protected String url;
    protected EndpointType endpointType;
    protected String accepts = "application/sparql-results+json";
    protected SparqlResult results;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public EndpointType getEndpointType() {
        return endpointType;
    }

    public void setEndpointType(EndpointType endpointType) {
        this.endpointType = endpointType;
    }

    public SparqlResult query(String searchString) throws IOException {
        String sparqlQuery = this.sparqlQuery;
        if (searchString != null) {
            sparqlQuery = sparqlQuery.replaceAll("\\$search_param", searchString);
        }
        HttpClient client = new HttpClient();
        System.out.println("SPARQL: " + sparqlQuery);
        String resultString = client.get(url + "?" + endpointType.getQueryString() + URLEncoder.encode(sparqlQuery, "ASCII"),  null);
        System.out.println("url + endpointType.getQueryString() + sparql -> " + url + "?" + endpointType.getQueryString() + URLEncoder.encode(sparqlQuery, "ASCII") + "\n" + resultString);
        results = new SparqlResult();
        ObjectMapper mapper = new ObjectMapper();
        results = mapper.readValue(resultString, SparqlResult.class);
        return results;
    }

/*
    public SparqlResult firstRow() {

        if (results != null && results.get("results") != null) {
            Map<String, Map<String, List>> res = (Map<String, Map<String, List>>) this.results.get("results");
            List bindings = (List) res.get("bindings");
            System.out.println(bindings);
        }
        return null;
    }
*/

}
