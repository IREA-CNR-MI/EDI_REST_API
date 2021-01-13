package it.cnr.irea.edi.template_manager.domain.adapters;

import it.cnr.irea.edi.template_manager.model.template.generated.Parameter;
import it.cnr.irea.edi.template_manager.model.template.generated.Parameters;

import java.util.List;

public class ParametersWrapper extends Parameters {
    public void setParameter(List<Parameter> parameterList) {
        parameter = parameterList;
    }
}
