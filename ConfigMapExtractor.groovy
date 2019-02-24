import groovy.json.JsonSlurper 

class ConfigMapExtractor {
    public String namespace;
    public String configmapName;
    public String configmapsJson; // in json format
    public def jsonSlurper;
    public def object;

    ConfigMapExtractor(namespace) {
        this.setNamespace(namespace)
    }

    void setConfigmapName(String cmn) {
        configmapName=cmn
    }

    void setNamespace(String ns) {
        namespace=ns
    }

    void getConfigMapsJSON() {
        configmapsJson=("oc get cm -o json "+"-n "+namespace).execute().text
    }

    void getConfigMapsJSON(String cmname) {
        configmapsJson=("oc get cm "+cmname+" -o json "+"-n "+namespace).execute().text
    }

    void parseConfigmapsJSON() {
        jsonSlurper = new JsonSlurper()
        object = jsonSlurper.parseText(configmapsJson)
    }

    def getConfigMapsNames() {
        getConfigMapsJSON()
        parseConfigmapsJSON()
        return object.items.metadata.name
    }

    def getConfigMapsParameters(String cm) {
        getConfigMapsJSON(cm)
        parseConfigmapsJSON()
        return object.data
    }
}