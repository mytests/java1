
#install tomcat server
sudo apt-get update
sudo apt-get -y install tomcat7
sudo apt-get -y install tomcat7-docs tomcat7-admin tomcat7-examples

#Install java
sudo apt-get -y install default-jdk

#Install git and ant (really?)
sudo apt-get -y install ant git

#copy configuration file
sudo cp /vagrant/tomcat-users.xml /etc/tomcat7/tomcat-users.xml
sudo service tomcat7 restart

#install unzip comand
sudo apt-get -y install unzip

#Install activator and play framework
cd ~
wget http://downloads.typesafe.com/typesafe-activator/1.2.12/typesafe-activator-1.2.12.zip
unzip typesafe-activator-1.2.12.zip 
echo 'export PATH=$PATH:/home/vagrant/activator-1.2.12/' >> .bashrc 
