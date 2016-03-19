clear

lib=./lib
classpath=.

for file in ${lib}/*.jar; 
    do classpath=${classpath}:$file; 
done

$JAVA_HOME/bin/java -classpath ${classpath} com.fastjrun.demospring4.BatchInit shutdown 