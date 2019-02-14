#!/bin/bash

echo "clean ..."
# rm demo-api
rm -rf demo-api/src/

# rm demo-api-test
rm -rf demo-api-test/src/main/
rm -rf demo-api-test/src/test/java/
rm -rf demo-api-test/src/test/resources/testng.xml
rm -rf demo-api-test/src/test/data/

# rm demo-base
rm -rf demo-base/src/

# rm demo-api
rm -rf demo-api/src/

# rm demo-bundle
rm -rf demo-bundle/src/

# rm demo-bundle-mock
rm -rf demo-bundle-mock/src/

mvn clean
echo "clean done."