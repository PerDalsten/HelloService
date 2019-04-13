#/bin/bash

DERBY_DATA=$PWD

JAVA=$JAVA_HOME/bin/java

# Client connect:
# Embedded: CONNECT 'jdbc:derby:helloservicedb;create=true';
# Network Client: CONNECT 'jdbc:derby://localhost:1527/helloservicedb;create=true';

$JAVA -Dderby.system.home=$DERBY_DATA -jar $DERBY_HOME/lib/derbyrun.jar ij