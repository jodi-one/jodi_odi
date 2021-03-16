#!/bin/bash
version="12.2.1.4.0.0"
gradle clean
status=$?
if [ $status -gt 0 ]
then
  echo "clean failed";
  exit 1;
fi
groovy copyJars.groovy
status=$?
if [ $status  -gt 0 ]
then
  echo "copyJars failed";
  exit 1;
fi
gradle shadowJar
status=$?
if [ $status -gt 0 ]
 then
  echo "shadowJar failed";
  exit 1;
fi
mvn install:install-file -Dfile=./build/libs/jodi_odi-$version.jar -DgroupId=one.jodi -DartifactId=jodi_odi -Dversion=$version -Dpackaging=jar
status=$?
if [ $status -gt 0 ]
then
  echo "mvn install failed";
  exit 1;
fi
gradle clean