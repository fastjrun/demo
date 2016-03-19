clear

lib=./lib
classpath=.

for file in ${lib}/*.jar; 
    do classpath=${classpath}:$file; 
done

$JAVA_HOME/bin/java -Xms256M -Xmx512M -Dfile.encoding=utf-8 -Dorg.apache.activemq.SERIALIZABLE_PACKAGES="*" -classpath ${classpath} com.fastjrun.demospring4.BatchInit startup 