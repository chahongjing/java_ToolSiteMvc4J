rem ����tomcat-users.xml�ļ������ <role rolename="manager-gui"/><role rolename="manager-script" /><user username="admin" password="admin" roles="manager-script,admin-gui" />
rem �ٵ�mvn settings.xml�ļ�������<server><id>tomcat7</id><username>admin</username><password>admin</password></server>
mvn tomcat7:deploy