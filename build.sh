#!/bin/bash
function die() {
  echo "$1"
  exit 1
}

# read version from gradle properties
source "gradle.properties"

# shellcheck disable=SC2154
echo "Building version ${version}"

gradle clean
status=$?
[ ${status} -eq 0 ] || die "clean failed"

groovy copyJars.groovy
status=$?
[ ${status} -eq 0 ] || die "copyJars failed"

gradle shadowJar
status=$?
[ ${status} -eq 0 ] || die "shadowJar failed"

mvn install:install-file -Dfile=./build/libs/jodi_odi-"${version}".jar -DgroupId=one.jodi -DartifactId=jodi_odi -Dversion="${version}" -Dpackaging=jar
status=$?
[ ${status} -eq 0 ] || die "mvn install failed"
