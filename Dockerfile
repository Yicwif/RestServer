FROM freedomkk/tomcat-maven:latest

ENV TIME_ZONE=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TIME_ZONE /etc/localtime && echo $TIME_ZONE > /etc/timezone

ENV TOMCAT_HOME /usr/local/tomcat

ADD . /app

WORKDIR /app

RUN mvn install  -D maven.test.skip=true

RUN mv ./ocmanager.war  $TOMCAT_HOME/webapps

#TODO :init mysql
#TODO :configure  <TOMCAT_HOME>/webapps/ocmanager/WEB-INF/conf

EXPOSE 8080
CMD ["catalina.sh", "run"]

