FROM openjdk:17

WORKDIR /app

COPY ./src /app/src

RUN javac -d . /app/src/main/java/org/example/Main.java

CMD ["java", "-cp", "/app", "org.example.Main"]