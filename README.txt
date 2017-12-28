Project creation
================

mvn archetype:generate -DgroupId=dk.purplegreen.hello -DartifactId=HelloService -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false

Fix pom.xml (add Java EE 7, fix Junit version, compiler level, etc.) and web.xml (Web app version) in text editor


WLP Server config
=================

./installUtility install jaxws-2.2 if running web profile instead of full WLP.


<server description="WLP Used From Eclipse">

	<!-- Enable features -->
	<featureManager>
        <feature>webProfile-7.0</feature>
        <feature>jaxws-2.2</feature>
        <feature>localConnector-1.0</feature>
    </featureManager>
	
	<httpEndpoint host="*" httpPort="9080" httpsPort="9443" id="defaultHttpEndpoint"/>

	<webApplication id="HelloService" location="HelloService.war" name="HelloService"/>
	
	<jndiEntry value="http://localhost:9080/HelloService/HelloService" jndiName="helloservice/endpoint"></jndiEntry>

	<library id="DerbyLib">
    	<fileset dir="lib/derby" includes="*.jar"/>
    </library>
    <dataSource jndiName="jdbc/HelloServiceDS">
        <jdbcDriver libraryRef="DerbyLib"/>
        <properties.derby.client createDatabase="create" databaseName="helloservicedb" user="helloservice" password="{xor}NzozMzAsOi0pNjw6"/>
    </dataSource>
</server>


Wildfly config
==============

Deploy derbyclient.jar as application.

<datasource jta="true" jndi-name="java:/jdbc/HelloServiceDS" pool-name="HelloService" enabled="true" use-ccm="true">
	<connection-url>jdbc:derby://localhost:1527/helloservicedb;create=true</connection-url>
    <driver-class>org.apache.derby.jdbc.ClientDriver</driver-class>
    <driver>derbyclient.jar</driver>
    <security>
         <user-name>helloservice</user-name>
         <password>helloservice</password>
    </security>
</datasource>


Add WS URL:

<subsystem xmlns="urn:jboss:domain:naming:2.0">
  <bindings>
     <simple name="java:/helloservice/endpoint" value="http://localhost:8080/HelloService/HelloService" type="java.lang.String" />
  </bindings>
</subsystem>

or run scripts in src/main/scripts/wildfly as needed e.g.


cd $JAVA_HOME/db/lib; /path/to/jboss-cli.sh --file=/path/to/create-derby-database-driver.cli 

/path/to/jboss-cli.sh --file=/path/to/create-derby-datasource.cli

/path/to/jboss-cli.sh --file=/path/to/create-jndi-entry.cli



URL's
=====

http://localhost:9080/HelloService
http://localhost:9080/HelloService/admin/index.xhtml
http://localhost:9080/HelloService/rest/hello/1
http://localhost:9080/HelloService/HelloService?wsdl
http://localhost:9080/HelloService/HelloServlet?id=2