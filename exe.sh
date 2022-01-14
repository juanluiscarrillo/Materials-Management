#!/bin/bash

export CLASSPATH="./classes:./jars/AbsoluteLayout.jar:./jars/ucanaccess-4.0.2.jar:./jars/commons-lang-2.6.jar:./jars/commons-logging-1.2.jar:./jars/hsqldb-2.4.0.jar:./jars/jackcess-2.1.8.jar"

/usr/lib/jvm/java-11-openjdk-amd64/bin/java -mx1000m vgestion.JFramePrincipal ./gestionDB.mdb
