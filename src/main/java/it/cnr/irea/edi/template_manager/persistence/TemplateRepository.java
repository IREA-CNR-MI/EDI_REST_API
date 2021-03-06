package it.cnr.irea.edi.template_manager.persistence;

import it.cnr.irea.edi.template_manager.domain.adapters.TemplateAdapter;
import org.springframework.data.repository.CrudRepository;

public interface TemplateRepository extends CrudRepository<TemplateAdapter, String> {
}
