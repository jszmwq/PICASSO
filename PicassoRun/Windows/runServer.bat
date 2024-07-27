@ECHO OFF

title Picasso Server
cd ..\..\
java -Xmx256m -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -classpath .;./PicassoServer;./PicassoCommon;./Libraries/pic_linearalgebra.jar;./Libraries/db2jcc.jar;./Libraries/db2jcc_license_cu.jar;./Libraries/ojdbc14.jar;./Libraries/sqljdbc4.jar;./Libraries/postgresql-8.0-311.jdbc3.jar;./Libraries/mysql-connector-java-5.1.8-bin.jar;./Libraries/jconn3.jar iisc.dsl.picasso.server.Picasso_Server > ./PicassoRun/Logs/server.log

title DOS
pause