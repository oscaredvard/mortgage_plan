FROM openjdk:17

WORKDIR /app

COPY ./src/main/java/org /app/org

RUN javac ./org/example/Main.java

CMD ["java", "org.example.Main"]
