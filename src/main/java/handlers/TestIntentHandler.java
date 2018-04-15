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
  
    static void authImplicit() {
      // If you don't specify credentials when constructing the client, the client library will
      // look for credentials via the environment variable GOOGLE_APPLICATION_CREDENTIALS.
      Storage storage = StorageOptions.getDefaultInstance().getService();

      System.out.println("Buckets:");
      Page<Bucket> buckets = storage.list();
      for (Bucket bucket : buckets.iterateAll()) {
        System.out.println(bucket.toString());
      }
    }
  
    static void authExplicit(String jsonPath) throws IOException {
      // You can specify a credential file by providing a path to GoogleCredentials.
      // Otherwise credentials are read from the GOOGLE_APPLICATION_CREDENTIALS environment variable.
      GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(jsonPath))
            .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
      Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

      System.out.println("Buckets:");
      Page<Bucket> buckets = storage.list();
      for (Bucket bucket : buckets.iterateAll()) {
        System.out.println(bucket.toString());
      }
    }
  
    static void authCompute() {
      // Explicitly request service account credentials from the compute engine instance.
      GoogleCredentials credentials = ComputeEngineCredentials.create();
      Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

      System.out.println("Buckets:");
      Page<Bucket> buckets = storage.list();
      for (Bucket bucket : buckets.iterateAll()) {
        System.out.println(bucket.toString());
      }
    }
  
    static void authAppEngineStandard() throws IOException {
      // Explicitly request service account credentials from the app engine standard instance.
      GoogleCredentials credentials = AppEngineCredentials.getApplicationDefault();
      Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

      System.out.println("Buckets:");
      Page<Bucket> buckets = storage.list();
      for (Bucket bucket : buckets.iterateAll()) {
        System.out.println(bucket.toString());
      }
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
          String jdbcUrl = "jdbc:mysql://google/Users?cloudSqlInstance=diethelper-201120:us-east1:users&socketFactory=com.google.cloud.sql.mysql.SocketFactory";
//           Properties connectionProps = new Properties();
//           connectionProps.put("user", "root");
//           Connection con = DriverManager.getConnection(jdbcUrl,"root", "");
//           try (Statement statement = connection.createStatement()) {
//             ResultSet resultSet = statement.executeQuery("SHOW TABLES");
//             while (resultSet.next()) {
//               System.out.println(resultSet.getString(1));
//             }
//           }
          Storage storage = StorageOptions.getDefaultInstance().getService();
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