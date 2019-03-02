def printTestMsg(String input) {
    return input    
}

def getConfigMapParameters(String ns, String cm) {
        def configmapsJson=("oc get cm "+cm+" -o json "+"-n "+ns).execute().text
        
        def jsonSlurper = new JsonSlurper()
        def object = jsonSlurper.parseText(configmapsJson)
        def keyList=[]
        
        object.data.each {
            keyList.add(it.key)
        }
        
        return keyList
}

def getConfigMapsList(String ns) {
        def configmapsJson=("oc get cm -o json "+"-n "+ns).execute().text
        
        def jsonSlurper = new JsonSlurper()
        def object = jsonSlurper.parseText(configmapsJson)
        
        return object.items.metadata.name
}
