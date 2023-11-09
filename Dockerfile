FROM openjdk:11
EXPOSE 8080
ADD target/Devops_project.jar Devops_project.jar
ENTRYPOINT ["java","-jar","Devops_project.jar"]