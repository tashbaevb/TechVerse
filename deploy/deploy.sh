#!/usr/bin/env bash


 mvn clean package -DskipTests

 echo "Copy files ..."

 scp -i ~/.ssh/makers \
     ./target/*.jar \
     tashbaevb@35.246.189.147:~/deplmakers/hosting.jar

 echo "Restart server ..."

 ssh -i ~/.ssh/makers tashbaevb@35.246.189.147 << EOF

 pgrep java | xargs kill -9
 nohup java -jar deplmakers/hosting.jar &

 EOF

 echo "Bye !"