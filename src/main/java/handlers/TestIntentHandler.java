package handlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;
import java.util.Properties;

public class TestIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("TestIntentHandler"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
      String speechText = "Hello world: you are in the test intent";
        try {
          Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException err){
          speechText = err.getMessage( );
        }
        try {
          String databaseName = "users";
          String instanceConnectionName = "diethelper-201120:us-east1:users";
          //String jdbcUrl = "jdbc:mysql://google/%s?cloudSqlInstance=%s&socketFactory=com.google.cloud.sql.mysql.SocketFactory:"+instanceConnectionName+"/"+databaseName+"?user=root";
          //Connection con = DriverManager.getConnection("jdbc:mysql://35.185.24.120:diethelper-201120:us-east1:users?user=root");
          //String jdbcUrl = String.format("jdbc:mysql://google/%s?cloudSqlInstance=%s&socketFactory=com.google.cloud.sql.mysql.SocketFactory",databaseName,instanceConnectionName);
          String jdbcUrl = "jdbc:mysql://google/users?socketFactory=com.google.cloud.sql.mysql.SocketFactory&cloudSqlInstance="+instanceConnectionName;
          Properties connectionProps = new Properties();
          connectionProps.put("user", "root");
          Connection con = DriverManager.getConnection(jdbcUrl,connectionProps);
          System.out.println("Pass");
          //speechText = "Hello world: you are in the test intent";
        }
          catch ( SQLException err ) {
          speechText = err.getMessage( );
          System.out.println("Fail");
        }
        

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .build();
    }

}