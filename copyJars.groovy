import static groovy.io.FileType.*
import java.nio.file.*

// def odiSrcDir = new File('/u01/app/odi')
def odiSrcDir = new File('D:/products/12.2.1.4.0/odihome_1/')
def libFolder = 'src/main/resources/lib'
def trgtDir = new File(libFolder)
def odiJarNames = [
        'eclipselink.jar',
        'activation.jar',
        'jython.jar',
        'spring-expression.jar',
        'aopalliance.jar',
        'odi-core.jar',
        'spring-instrument.jar',
        'bsf.jar',
        'odihapp_common.jar',
        'spring-instrument-tomcat.jar',
        'odihapp_essbase.jar',
        'spring-jdbc.jar',
        'commons-codec-1.3.jar',
        'odihapp_planning.jar',
        'spring-jms.jar',
        'commons-codec-1.9.jar',
        'odi-sap.jar',
        'spring-orm.jar',
        'commons-discovery-0.4.jar',
        'odi-wls-template-gen.jar',
        'spring-oxm.jar',
        'commons-lang-2.2.jar',
        'ogg_jmx_interface.jar',
        'spring-struts.jar',
        'oracle.odi-jaxrsri.jar',
        'spring-test.jar',
        'spring-tx.jar',
        'commons-net-3.3.jar',
        'org.eclipse.jgit_3.6.2.201501210735-r.jar',
        'spring-web.jar',
        'pop3.jar',
        'spring-webmvc.jar',
        'connector.jar',
        'sapjco3.jar',
        'spring-webmvc-portlet.jar',
        'cpld.jar',
        'sapjco.jar',
        'svnkit-1.8.3.jar',
        'enterprise_data_quality.jar',
        'spring-aop.jar',
        'trilead-ssh2-1.0.0-build217.jar',
        'hsqldb.jar',
        'spring-beans.jar',
        'trove.jar',
        'jakarta-oro-2.0.8.jar',
        'spring-context.jar',
        'XmlSchema-1.4.2.jar',
        'javolution.jar',
        'spring-context-support.jar',
        'jms.jar',
        'spring-core.jar',
        'javax.validation.jar',
        'com.oracle.http_client.http_client.jar',
        'com.oracle.webservices.orawsdl-api.jar',
        'com.sun.mail.javax.mail.jar',
        'org.codehaus.jackson.jackson-core-asl.jar',
        'org.glassfish.javax.json.jar',
        'javax.management.j2ee.jar',
        //   'oracle.ucp.jar', // this should come from patch 26261906 otherwise we get connection closed.
        'javax.persistence.jar',
        'dms.jar',
        'ojdl.jar',
        'jackson-core-2.2.0.jar',
        'jackson-core-2.7.9.jar',
        'jackson-core-asl-1.9.11.jar',
        'jackson-core-asl-1.9.13.jar',
        'bpm-infra.jar',
        'wlthint3client.jar',
        'javax.validation.jar',
        'SchemaVersion.jar',
        'coherence.jar',
        'identitystore.jar',
        'jps-api.jar',
        'jmxframework.jar',
        'jmxspi.jar',
        'orai18n-mapping.jar',
        'httpclient-4.5.1.jar',
        'httpclient-cache-4.5.1.jar',
        'httpcore-4.4.3.jar',
        'httpmime-4.5.1.jar',
        'bsh-2.0b4.jar',
        'commons-io-1.2.jar',
        'ant-antlr.jar',
        'ant-apache-oro.jar',
        'ant-commons-logging.jar',
        'ant-javamail.jar',
        'ant-junit4.jar',
        'ant-swing.jar',
        'ant-apache-bcel.jar',
        'ant-apache-regexp.jar',
        'ant-commons-net.jar',
        'ant-jdepend.jar',
        'ant-junit.jar',
        'ant-testutil.jar',
        'ant-apache-bsf.jar',
        'ant-apache-resolver.jar',
        'ant-jai.jar',
        'ant-jmf.jar',
        'ant-launcher.jar',
        'ant-apache-log4j.jar',
        'ant-apache-xalan2.jar',
        'ant.jar',
        'ant-jsch.jar',
        'ant-netrexx.jar',
        'jsch-0.1.53.jar',
        'enterprise_data_quality.jar',
        'oracle.odi-sdk-jse.jar',
        'ojdbc8.jar',
        'ojdbc8dms.jar',
        'commons-collections-3.2.2.jar',
        'oraclepki.jar',
        'oamcfgtool.jar',
        'oci-java-sdk-full-shaded-1.2.33.jar',
        'oracle.odi.tp.clientLib.jar',
        'oraclepki.jar'
]


def copyFiles = {
    if( ((File) it).getAbsolutePath().contains(".patch_storage")){return}
    if(!odiJarNames.contains(it.name) ){
        return
    }
    println "Copying: '$it.absolutePath' to $trgtDir"
    Path copied = Paths.get("$trgtDir/$it.name");
    Path originalPath = Paths.get(it.absolutePath);
    Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
}
File  f = new File(libFolder)
f.delete()
f.mkdirs()

odiSrcDir.traverse type: FILES, visit: copyFiles