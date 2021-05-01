# jodi_odi

ODI SDK dependencies to build fat jar to develop with ODISDK, no actual jars from Oracle provided, *NOT* an official Oracle repo.

**DO NOT PUBLISH TO MAVENCENTRAL**; Oracle Libraries are not permitted there from non oracle staff.

### Usage
* modify _copyJars.groovy_ to point variable _odiSrcDir_ to your local ODI installation, e.g. \
  `def odiSrcDir = '/u01/app/odi'`
* collect the content jars: run `groovy copyJars.groovy`
* build the fat jar: `gradle shadowJar`
* add the new fat jar to your local Maven repository: \
  `mvn install:install-file -Dfile=build/libs/jodi_odi-12.2.1.4.0.0.jar -DgroupId=one.jodi -DartifactId=jodi_odi -Dversion=12.2.1.4.0.0 -Dpackaging=jar`

