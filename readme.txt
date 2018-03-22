Put This into server.xml

<Context docBase="WebApplication with MySQL DB" path="/WebApplication_with_MySQL_DB" reloadable="true" source="org.eclipse.jst.jee.server:WebApplication with MySQL DB">
  	<Resource auth="Container" driverClassName="com.mysql.jdbc.Driver" logAbandoned="true" maxActive="30" maxIdle="15" maxWait="10000" name="jdbc/mssql" password="" removeAbandoned="true" removeAbandonedTimeout="60" type="javax.sql.DataSource" url="jdbc:mysql://localhost:3306/TestDB" username="root"/>
</Context>