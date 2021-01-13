package it.cnr.irea.edi.template_manager.domain;

public class SparqlDatasource extends Datasource {
    public SparqlDatasource(String sparql) {
        sparqlQuery = sparql;
    }
}
