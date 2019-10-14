# jodi_odi

ODI SDK dependencies to build fat jar to develop with ODISDK, no actual jars from Oracle provided, NOT an official Oracle repo.
DO NOT PUBLISH TO MAVENCENTRAL; Oracle Libraries are not permitted there from non oracle staff.

`Usage:`
<ul>
<li>modify copyJars.groovy e.g. def odiSrcDir = new File('/u01/app/odi')</li>
<li>run copyJars.groovy e.g. groovy copyJars.groovy</li>
<li>gradle shadowJar</li>
<li>mvn install:install-file -Dfile=build/libs/jodi_odi-12.2.1.3.2.jar -DgroupId=one.jodi -DartifactId=jodi_odi -Dversion=12.2.1.3.2 -Dpackaging=jar</li>
<li> In your project use: this as gradle dependency:	provided group: 'one.jodi', name: 'jodi_odi', version: '12.2.1.3.2'</li>
</ul>
