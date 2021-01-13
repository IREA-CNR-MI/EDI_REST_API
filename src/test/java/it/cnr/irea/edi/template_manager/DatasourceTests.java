package it.cnr.irea.edi.template_manager;

import it.cnr.irea.edi.template_manager.domain.Codelist;
import it.cnr.irea.edi.template_manager.domain.SparqlDatasource;
import it.cnr.irea.edi.template_manager.domain.adapters.ParametersWrapper;
import it.cnr.irea.edi.template_manager.model.template.generated.Parameter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class DatasourceTests {
    @Test
    public void codelistWorks() throws IOException {
        it.cnr.irea.edi.template_manager.model.template.generated.EndpointType e = new it.cnr.irea.edi.template_manager.model.template.generated.EndpointType();
        e.setId("test");
        e.setMethod("GET");
        e.setQueryParameter("query");

        Parameter p1 = new Parameter();
        p1.setName("format");
        p1.setValue("application/sparql-results+json");
        Parameter p2 = new Parameter();
        p2.setName("save");
        p2.setValue("display");

        List<Parameter> parameters = new ArrayList<Parameter>() {
            {
                add(p1);
                add(p2);
            }
        };

        ParametersWrapper para = new ParametersWrapper();
        para.setParameter(parameters);
        e.setParameters(para);
        it.cnr.irea.edi.template_manager.domain.EndpointType endpointType = new it.cnr.irea.edi.template_manager.domain.EndpointType(e);
        Codelist codelist = new Codelist();
        codelist.setUrl("http://sp7.irea.cnr.it:8891/sparql");
        codelist.setUri("http://inspire-registry.jrc.ec.europa.eu/registers/Languages/items");
        codelist.setEndpointType(endpointType);
        SparqlResult results = codelist.query("");
        System.out.println("results" + results);
    }

    @Test
    public void specificSparqlWorks() throws IOException {
        it.cnr.irea.edi.template_manager.model.template.generated.EndpointType e = new it.cnr.irea.edi.template_manager.model.template.generated.EndpointType();
        e.setId("test");
        e.setMethod("GET");
        e.setQueryParameter("query");

        Parameter p1 = new Parameter();
        p1.setName("format");
        p1.setValue("application/sparql-results+json");
        Parameter p2 = new Parameter();
        p2.setName("save");
        p2.setValue("display");

        List<Parameter> parameters = new ArrayList<Parameter>() {
            {
                add(p1);
                add(p2);
            }
        };

        ParametersWrapper para = new ParametersWrapper();
        para.setParameter(parameters);
        e.setParameters(para);
        it.cnr.irea.edi.template_manager.domain.EndpointType endpointType = new it.cnr.irea.edi.template_manager.domain.EndpointType(e);
        SparqlDatasource codelist = new SparqlDatasource("PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n" +
                "                        SELECT ?c ?l ?urn\n" +
                "                        FROM <http://ritmare.it/rdfdata/units>\n" +
                "                        WHERE {\n" +
                "                          ?c rdf:type skos:Concept.\n" +
                "                          OPTIONAL {\n" +
                "                            ?c skos:prefLabel ?l.\n" +
                "                            FILTER( LANG(?l) = \"en\")\n" +
                "                          }\n" +
                "                          OPTIONAL {\n" +
                "                            ?c skos:prefLabel ?a.\n" +
                "                            FILTER( LANG(?a) = \"en\")\n" +
                "                          }\n" +
                "                          OPTIONAL {\n" +
                "                            ?c skos:altLabel ?urn.\n" +
                "                            FILTER( LANG(?urn) = \"en\")\n" +
                "                          }\n" +
                "                          FILTER( REGEX( STR(?l), \"$search_param\", \"i\") || REGEX( STR(?a), \"$search_param\", \"i\") )\n" +
                "                        }\n" +
                "                        ORDER BY ASC(?l) ASC(?a)");
        codelist.setUrl("http://sparql.get-it.it");
        codelist.setEndpointType(endpointType);
        SparqlResult results = codelist.query("Milligrams per square metre per incubation duration");
        System.out.println("results" + results);
        System.out.println("first row" + results.firstRow());
    }
}
