package it.cnr.irea.edi.template_manager.domain.ediml.v2;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class Item {
    String id;
    String elementId;
    String path;
    String dataType;
    boolean fixed;
    boolean useURN;
    int outIndex;
    String value;
    String labelValue;
    String codeValue;
    String urnValue;
    boolean languageNeutral;
    String listeningFor;
    boolean isLanguageNeutral;
    String datasource;
    String hasIndex;
    String field;
    String itemId;
    boolean show;
    String defaultValue;
    String query;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public boolean isUseURN() {
        return useURN;
    }

    public void setUseURN(boolean useURN) {
        this.useURN = useURN;
    }

    public int getOutIndex() {
        return outIndex;
    }

    public void setOutIndex(int outIndex) {
        this.outIndex = outIndex;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabelValue() {
        return labelValue;
    }

    public void setLabelValue(String labelValue) {
        this.labelValue = labelValue;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public String getUrnValue() {
        return urnValue;
    }

    public void setUrnValue(String urnValue) {
        this.urnValue = urnValue;
    }

    public boolean isLanguageNeutral() {
        return languageNeutral;
    }

    public void setLanguageNeutral(boolean languageNeutral) {
        this.languageNeutral = languageNeutral;
    }

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }

    public String getHasIndex() {
        return hasIndex;
    }

    public void setHasIndex(String hasIndex) {
        this.hasIndex = hasIndex;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getListeningFor() {
        return listeningFor;
    }

    public void setListeningFor(String listeningFor) {
        this.listeningFor = listeningFor;
    }
}
