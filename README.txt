Project creation
================

mvn archetype:generate -DgroupId=dk.purplegreen.hello -DartifactId=HelloService -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false

Fix pom.xml (add Java EE 7, fix Junit version, compiler level, etc.) and web.xml (Web app version) in text editor

Import Existing Maven Project (or alternatively mvn eclipse:eclipse and Open Existing Project?)


WLP Server config
=================

<server description="WLP Used From Eclipse">

	<!-- Enable features -->
	<featureManager>
		<feature>javaee-7.0</feature>
		<feature>localConnector-1.0</feature>
	</featureManager>
	
	<httpEndpoint host="*" httpPort="9080" httpsPort="9443" id="defaultHttpEndpoint"/>

	<webApplication id="HelloService" location="HelloService.war" name="HelloService"/>

	<library id="DerbyLib">
		<fileset dir="C:\Development\jdk1.8\db\lib" includes="*.jar"/>
	</library>
	<dataSource jndiName="jdbc/HelloServiceDS">
		<jdbcDriver libraryRef="DerbyLib"/>
		<properties.derby.client createDatabase="create" databaseName="helloservicedb" password="{xor}KzosKw=="/>
	</dataSource>	
</server>


Wildfly config
==============

Deploy derbyclient.jar as application.

<datasource jta="true" jndi-name="java:/jdbc/HelloServiceDS" pool-name="HelloService" enabled="true" use-ccm="true">
	<connection-url>jdbc:derby://localhost:1527/helloservicedb;create=true</connection-url>
    <driver-class>org.apache.derby.jdbc.ClientDriver</driver-class>
    <driver>derbyclient.jar</driver>
</datasource>



URL's
=====

http://localhost:9080/HelloService
http://localhost:9080/HelloService/admin/index.xhtml
http://localhost:9080/HelloService/rest/hello/1
http://localhost:9080/HelloService/HelloService?wsdl
http://localhost:9080/HelloService/HelloServlet?id=2