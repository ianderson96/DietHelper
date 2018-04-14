package api;

import javax.net.ssl.HttpsURLConnection;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

public class ApiHandler {

    private final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws Exception {



    }

    public String getProductInfo (int productID) {
        System.out.println("Testing 1 - Send Http GET request");
        this.sendGet("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com" +
                "/food/products/22347");
    }


        // HTTP GET request
        public void sendGet(String url) throws Exception {

//            String url = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/food/products/33423";

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("X-Mashape-Key", "sR6MoRG1yDmsh2PVbmG1Sh4AhMtUp1hOTjBjsnVZ7lj4iDB5ER");


            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            return response.toString());

        }

    }

