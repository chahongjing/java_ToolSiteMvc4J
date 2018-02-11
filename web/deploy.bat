rem 先在tomcat-users.xml文件中添加 <role rolename="manager-gui"/><role rolename="manager-script" /><user username="admin" password="admin" roles="manager-script,admin-gui" />
rem 再到mvn settings.xml文件中配置<server><id>tomcat7</id><username>admin</username><password>admin</password></server>
mvn tomcat7:deploy