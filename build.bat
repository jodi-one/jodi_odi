@SET odiVersion=12.2.1.4.0.1
gradle clean
groovy copyJars.groovy
gradle shadowJar
mvn install:install-file "-Dfile=build/libs/jodi_odi-%odiVersion%.jar" "-DgroupId=one.jodi" "-DartifactId=jodi_odi" "-Dversion=%odiVersion%" "-Dpackaging=jar"