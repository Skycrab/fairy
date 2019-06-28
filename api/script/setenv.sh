#!/bin/bash

# Service name
SERVICE_NAME=cloud-api
#Service path
SERVICE_HOME=/home/xiaoju/${SERVICE_NAME}
# JDK path
JAR_PACKAGE="lib/cloud-api.jar"

if [ `uname` = "Darwin" ]; then
    JAVA_HOME=`/usr/libexec/java_home -v 1.8`
#elif [ -d /usr/java/default ]; then
#    JAVA_HOME=/usr/java/default
else
    #JAVA_HOME=/usr/local/jdk1.8.0_65
    JAVA_HOME="/usr/java/jdk1.8.0_144/"
fi

export JAVA_HOME=$JAVA_HOME

# JVM options
XMS="-Xms4096M"
XMX="-Xmx4096M"
XMN="-Xmn512M"
X_PERM_SIZE="-XX:PermSize=128M"
X_MAX_PERM_SIZE="-XX:MaxPermSize=128M"
X_SERVIVOR_RATIO="-XX:SurvivorRatio=8"