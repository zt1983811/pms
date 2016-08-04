#!/usr/bin/env bash

echo "Provisioning virtual machine..."
apt-get install software-properties-common python-software-properties debconf-utils -y
add-apt-repository ppa:webupd8team/java -y
apt-get update -yy
echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 select true" | debconf-set-selections 
apt-get install oracle-java8-installer -y
apt-get install oracle-java8-set-default -y
apt install tmux -y
apt install git -y
apt install tar -y
apt install maven -y
debconf-set-selections <<< 'mysql-server mysql-server/root_password password root'
debconf-set-selections <<< 'mysql-server mysql-server/root_password_again password root'

apt install mysql-server -y
mysql -uroot -proot -e "CREATE DATABASE pms_test CHARACTER SET ucs2 COLLATE ucs2_general_ci";

wget http://apache.mirrors.ionfish.org/tomcat/tomcat-8/v8.5.4/bin/apache-tomcat-8.5.4.tar.gz 
mkdir /opt/tomcat
tar xzvf apache-tomcat-8*tar.gz -C /opt/tomcat --strip-components=1
chown -R ubuntu:ubuntu /opt/tomcat
# chmod -R 777 /opt/tomcat

echo '
[Unit]
Description=Apache Tomcat Web Application Container
After=network.target

[Service]
Type=forking

Environment=JAVA_HOME=/usr/lib/jvm/java-8-oracle/jre
Environment=CATALINA_PID=/opt/tomcat/temp/tomcat.pid
Environment=CATALINA_HOME=/opt/tomcat
Environment=CATALINA_BASE=/opt/tomcat
Environment='CATALINA_OPTS=-Xms512M -Xmx1024M -server -XX:+UseParallelGC'
Environment='JAVA_OPTS=-Djava.awt.headless=true -Djava.security.egd=file:/dev/./urandom'

ExecStart=/opt/tomcat/bin/startup.sh
ExecStop=/opt/tomcat/bin/shutdown.sh

User=ubuntu
Group=ubuntu
RestartSec=10
Restart=always

[Install]
WantedBy=multi-user.target
' >> /etc/systemd/system/tomcat.service

systemctl daemon-reload
systemctl start tomcat


git clone https://github.com/ericzhu/pms.git
cd pms
git checkout -b dev origin/dev
mvn clean install -Pparent
mvn clean install
cp ~/pms/iqware-platform-app/iqware-platform-app-restful/target/app.war /opt/tomcat/webapps/app.war
