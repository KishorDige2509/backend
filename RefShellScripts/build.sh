#!/bin/bash
#!/bin/sh
echo -----------------Building dependencies------------------------
mvn -f ./cravx-dependencies/pom.xml clean install 
if [ $? != 0 ]
then	echo "Build failure while running the cravx-dependencies"
	read -p "Press any key"
	exit 25
fi
echo -----------------Building parent------------------------
mvn -f ./cravx-parent/pom.xml clean install 
if [ $? != 0 ]
then	echo "Build failure while running the cravx-parent"
	read -p "Press any key"
	exit 25
fi

echo -----------------Building services------------------------
mvn clean install
if [ $? != 0 ]
then	echo "Build failure while running the cravx-services"
	read -p "Press any key"
	exit 25
fi

echo -----------------Completed building all--------------------------
