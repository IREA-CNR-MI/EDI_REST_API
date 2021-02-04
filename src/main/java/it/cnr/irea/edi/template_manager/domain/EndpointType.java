package it.cnr.irea.edi.template_manager.domain;

import it.cnr.irea.edi.template_manager.model.template.generated.Parameter;
import it.cnr.irea.edi.template_manager.model.template.generated.Parameters;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class EndpointType
{
    protected static Map<String, EndpointType> endpointTypes = new HashMap<>();
    protected String id;
    protected String method;
    protected String queryParameter;
    protected List<Parameter> parameters;

    public EndpointType(it.cnr.irea.edi.template_manager.model.template.generated.EndpointType endpointType) {
        id = endpointType.getId();
        method = endpointType.getMethod();
        queryParameter = endpointType.getQueryParameter();
        parameters = endpointType.getParameters().getParameter();
        endpointTypes.put(id, this);
    }

    public static EndpointType getEndpointById(String id) {
        return endpointTypes.get(id);
    }

    public String getQueryString() throws UnsupportedEncodingException {
        StringJoiner joiner = new StringJoiner("&");
        for (Parameter p : parameters) {
            String ascii = p.getName() + "=" + URLEncoder.encode(p.getValue(), "ASCII");
            joiner.add(ascii);
        }
        return joiner.toString() + (queryParameter != null ? "&" + queryParameter + "=" : "");
    }
}
