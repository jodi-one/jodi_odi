import static groovy.io.FileType.FILES

//def odiSrcDir = new File('/u01/app/odi')
def odiSrcDir = new File('src/main/resources/lib')
def filterJarFiles = ~/.*\.jar$/

def printFiles = {
    println "'$it.name',"
}

odiSrcDir.traverse type: FILES, visit: printFiles, nameFilter: filterJarFiles