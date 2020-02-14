gradle clean
groovy copyJars.groovy
gradle shadowJar
mvn install:install-file "-Dfile=build/libs/jodi_odi-12.2.1.3.2.6.jar" "-DgroupId=one.jodi" "-DartifactId=jodi_odi" "-Dversion=12.2.1.3.2.6" "-Dpackaging=jar"