

&lt;bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close"&gt;


> 

&lt;property name="driverClassName" value="com.mysql.jdbc.Driver"/&gt;


> 

&lt;property name="URL" value="jdbc:mysql://localhost:port/DB-name" /&gt;


> 

&lt;property name="user" value="user" /&gt;


> 

&lt;property name="password" value="pass" /&gt;




&lt;/bean&gt;



Or, you can use MysqlDataSource explicitly instead of generic BasicDataSource, in that case you do not want to set property driverClassName.

Also do not forget to change org.hibernate.dialect.Oracle10gDialect to org.hibernate.dialect.MySQLDialect in entityManagerFactory.

You also can use more precise dialect depending on MySQL engines:
MySQL with InnoDB -	org.hibernate.dialect.MySQLInnoDBDialect
MySQL with MyISAM -	org.hibernate.dialect.MySQLMyISAMDialect