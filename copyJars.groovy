import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

// def odiSrcDir = '/u01/app/odi'
def odiSrcDir = 'C:/Oracle/Oracle_Home/'
def libFolder = 'src/main/resources/lib'
def odiJarNames = [
        'activation.jar',
        'ant-antlr.jar',
        'ant-apache-bcel.jar',
        'ant-apache-bsf.jar',
        'ant-apache-log4j.jar',
        'ant-apache-oro.jar',
        'ant-apache-regexp.jar',
        'ant-apache-resolver.jar',
        'ant-apache-xalan2.jar',
        'ant-commons-logging.jar',
        'ant-commons-net.jar',
        'ant-jai.jar',
        'ant-javamail.jar',
        'ant-jdepend.jar',
        'ant-jmf.jar',
        'ant-jsch.jar',
        'ant-junit.jar',
        'ant-junit4.jar',
        'ant-launcher.jar',
        'ant-netrexx.jar',
        'ant-swing.jar',
        'ant-testutil.jar',
        'ant.jar',
        'aopalliance.jar',
        'bpm-infra.jar',
        'bsf-2.4.0.jar',
        'bsh-2.0b6.jar',
        'coherence.jar',
        'com.oracle.http_client.http_client.jar',
        'com.oracle.webservices.orawsdl-api.jar',
        'com.sun.mail.javax.mail.jar',
        'commons-codec-1.11.jar',
        'commons-collections-3.2.2.jar',
        'commons-discovery-0.5.jar',
        'commons-io-2.6.jar',
        'commons-lang-2.6.jar',
        'commons-net-3.5.jar',
        'connector.jar',
        'cpld.jar',
        'dms.jar',
        'eclipselink.jar',
        'enterprise_data_quality.jar',
        'hsqldb.jar',
        'httpclient-4.5.6.jar',
        'httpclient-cache-4.5.1.jar',
        'httpcore-4.4.10.jar',
        'httpmime-4.5.1.jar',
        'identitystore.jar',
        'jackson-core-2.9.9.jar',
        'jackson-core-asl-1.9.11.jar',
        'jackson-core-asl-1.9.13.jar',
        'jackson-databind-2.9.9.jar',
        'jakarta-oro-2.0.8.jar',
        'javax.management.j2ee.jar',
        'javax.persistence.jar',
        'javax.validation.jar',
        'javax.validation.jar',
        'javolution.jar',
        'jms.jar',
        'jmxframework.jar',
        'jmxspi.jar',
        'jps-api.jar',
        'jsch-0.1.54.jar',
        'jython.jar',
        'oamcfgtool.jar',
        'oci-java-sdk-full-shaded-1.2.33.jar',
        'odi-core.jar',
        'odi-sap.jar',
        'odi-wls-template-gen.jar',
        'odihapp_common.jar',
        'odihapp_essbase.jar',
        'odihapp_planning.jar',
        'ogg_jmx_interface.jar',
        'ojdl.jar',
        'oracle.odi-jaxrsri.jar',
        'oracle.odi-sdk-jse.jar',
        'oracle.odi.tp.clientLib.jar',
        'oraclepki.jar',
        'orai18n-mapping.jar',
        'org.codehaus.jackson.jackson-core-asl.jar',
        'org.eclipse.jgit-5.2.0.201812061821-r.jar',
        'org.glassfish.javax.json.jar',
        'pop3.jar',
        'sapjco.jar',
        'sapjco3.jar',
        'SchemaVersion.jar',
        'spring-aop-5.1.3.RELEASE.jar',
        'spring-beans-5.1.3.RELEASE.jar',
        'spring-context-support-5.1.3.RELEASE.jar',
        'spring-context-5.1.3.RELEASE.jar',
        'spring-core-5.1.3.RELEASE.jar',
        'spring-expression-5.1.3.RELEASE.jar',
        'spring-instrument-5.1.3.RELEASE.jar',
        'spring-jdbc-5.1.3.RELEASE.jar',
        'spring-jms-5.1.3.RELEASE.jar',
        'spring-orm-5.1.3.RELEASE.jar',
        'spring-oxm-5.1.3.RELEASE.jar',
        'spring-test-5.1.3.RELEASE.jar',
        'spring-tx-5.1.3.RELEASE.jar',
        'spring-web-5.1.3.RELEASE.jar',
        'spring-webmvc-5.1.3.RELEASE.jar',
        'svnkit-1.10.0.jar',
        'thirdPartyMain_comFasterxmlJacksonCoreJacksonDatabind_2.9.9.0.0.jar',
        'trilead-ssh2-1.0.0-build221.jar',
        'trove.jar',
        'wagon-http-2.8-shaded.jar',
        'wlthint3client.jar',
        'XmlSchema-1.4.7.jar'
]

def srcPath = Paths.get(odiSrcDir)
def trgtDir = Paths.get(libFolder)

if (!Files.exists(srcPath as Path))
    throw new IllegalArgumentException("Path $srcPath not found")
if (!Files.isDirectory(srcPath))
    throw new IllegalArgumentException("Path $srcPath is not a directory")
if (!Files.isReadable(srcPath))
    throw new IllegalArgumentException("Not allowed to read from path $srcPath")

// clear target dir first
if (Files.exists(trgtDir)) {
    if (!Files.isWritable(trgtDir))
        throw new IllegalArgumentException("Not allowed to write to path $trgtDir")
    Files.find(trgtDir, 10, (_, a) -> a.isRegularFile())
            .forEach(Files::delete)
}
// (re)create the target path if necessary
Files.createDirectories(trgtDir)

// find matching files and copy them to the output directory
Files.find(srcPath, 999, (_, a) -> a.isRegularFile())
        .filter(p -> !p.toString().contains(".patch_storage"))
        .filter(p -> odiJarNames.contains(p.getFileName().toString()))
        .peek(p -> println "Found: $p")
        .forEach(p -> Files.copy(p, trgtDir.resolve(p.fileName), StandardCopyOption.REPLACE_EXISTING))
