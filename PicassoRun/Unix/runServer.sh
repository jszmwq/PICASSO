#!/bin/bash
cd ../../
d=`pwd`

export CLASSPATH=.:$d/PicassoServer:$d/PicassoClient:$d/PicassoCommon:$d/Libraries/db2jcc.jar:$d/Libraries/Jama-1.0.2.jar:$d/Libraries/flanagan.jar:$d/Libraries/db2jcc_license_cu.jar:$d/Libraries/jconn3.jar:$d/Libraries/jgraph.jar:$d/Libraries/jgraphlayout.jar:$d/Libraries/vecmath.jar:$d/Libraries/l2fprod-common-all.jar:$d/Libraries/sqljdbc4.jar:$d/Libraries/ojdbc14.jar:$d/Libraries/postgresql-8.0-311.jdbc3.jar:$d/Libraries/mysql-connector-java-5.1.8-bin.jar:$d/Libraries/ojdbc14.jar:$d/Libraries/pic_linearalgebra.jar

java -Xmx256m -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 iisc.dsl.picasso.server.Picasso_Server > PicassoRun/Logs/server.log

cd PicassoRun/Unix
