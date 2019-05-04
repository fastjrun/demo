#!/bin/bash

echo "build ..."
if [ "local_bundle_mock" = $1 ] ; then
    mvn clean package -pl demo-bundle-mock -am -Dbdmgc.skip=false
elif [ "mock_server" = $1 ] ; then
    mvn clean package -pl demo-mock-server -am -Dbdmgc.skip=false
    cp demo-mock-server/target/demo-mock-server.war ~/server/tomcat8/webapps/demo-mock-server.war
elif [ "package_api_test" = $1 ] ; then
    mvn clean package -pl demo-api-test -am -Dclientgc.skip=false
elif [ "unitTest" = $1 ] ; then
    mvn clean verify -pl demo-test -PunitTest -am
elif [ "mock_test" = $1 ] ; then
    mvn clean package -pl demo-api-test -Dclientgc.skip=false -Pmock
elif [ "package_provider" = $1 ] ; then
    mvn clean package -pl demo-provider -am -Dbasegc.skip=false -Dbdgc.skip=false -P$2
    cp demo-provider/target/demo-provider.war ~/server/tomcat8/webapps/demo-provider.war
elif [ "api_test" = $1 ] ; then
    mvn clean package -pl demo-api-test -Dclientgc.skip=false -P$2
elif [ "package_task" = $1 ] ; then
    mvn clean package -pl demo-task -am -Dbasegc.skip=false -Dbdgc.skip=false -P$2
    rm -rf ~/app/demo/demo-task
    cp -r demo-task/target/demo-task/demo-task ~/app/demo/demo-task
elif [ "package_ci" = $1 ] ; then
    mvn clean package -pl demo-base -am -Dbasegc.skip=false
    mvn clean package -pl demo-bundle -am -Dbdgc.skip=false
    mvn clean package -pl demo-api-test -am -Dclientgc.skip=false -Dmaven.test.skip=true
    mvn clean package -pl demo-bundle-mock -am -Dbdmgc.skip=false
    mvn clean package -pl demo-api -am -Dapigc.skip=false
elif [ "service_ut" = $1 ] ; then
    mvn clean package -pl demo-test -am -Dbasegc.skip=false -Dbdgc.skip=false -P$2
elif [ "local_install" = $1 ] ; then
    mvn clean verify -pl demo-biz -PunitTest -am
fi
echo "build done."