cd "$(dirname "$0")"

lib=./lib
classpath=.

for file in ${lib}/*.jar; 
    do classpath=${classpath}:$file; 
done

java -Xms${jvm.ms} -Xmx${jvm.mx} -Dfile.encoding=utf-8 -Dorg.apache.activemq.SERIALIZABLE_PACKAGES="*" -classpath ${classpath} com.fastjrun.demospring4.BatchInit startup 