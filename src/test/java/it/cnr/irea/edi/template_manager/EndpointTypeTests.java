package it.cnr.irea.edi.template_manager;

import it.cnr.irea.edi.template_manager.domain.EndpointType;
import it.cnr.irea.edi.template_manager.domain.adapters.ParametersWrapper;
import it.cnr.irea.edi.template_manager.model.template.generated.Parameter;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class EndpointTypeTests {
    @Test
    void queryStringWorks() throws UnsupportedEncodingException {
        it.cnr.irea.edi.template_manager.model.template.generated.EndpointType e = new it.cnr.irea.edi.template_manager.model.template.generated.EndpointType();
        e.setId("test");
        e.setMethod("GET");

        Parameter p1 = new Parameter();
        p1.setName("pippo");
        p1.setValue("2");
        Parameter p2 = new Parameter();
        p2.setName("pluto");
        p2.setValue("paperino");

        List<Parameter> parameters = new ArrayList<Parameter>() {
            {
                add(p1);
                add(p2);
            }
        };
        ParametersWrapper para = new ParametersWrapper();
        para.setParameter(parameters);
        e.setParameters(para);
        EndpointType endpointType = new EndpointType(e);
        System.out.println(endpointType.getQueryString());
        assert endpointType.getQueryString().equalsIgnoreCase("pippo=2&pluto=paperino");
    }
}
