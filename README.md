openshift-configmap-extractor

Extractor for config maps and their parameters in provided namespace can be used for comparing two namespaces.

# Example use case:
  - Say that we have DEV, INT, UAT and PROD stages.
  - Each namespaces should have the same config map pattern, except their pointed values.
  - You have to detect config map changes(i.e. new configmap could be created or new parameters could be added to existing ones)
  - You can detect these differences by using this simple code.

# Example:
  ```
  import ConfigMapExtractor

  ConfigMapExtractor cme1=new ConfigMapExtractor("test-dev")

  def cm1=cme1.getConfigMapsNames()

  cm1.each {
      println(it+": "+cme1.getConfigMapsParameters(it))
  }
  ```
