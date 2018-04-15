package api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ApiHandler {

    private final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws Exception {
        ApiHandler api = new ApiHandler();
        String id = api.getProductId("twizzlers");
        System.out.println(api.getProductInfo(id));
    }

    public String getProductInfo(String productID) {
        String url = new String("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/food/products/");
        url = url + productID;
        String result = new String();
        try {
            result = this.sendGet(url);

        }
        catch (Exception e) {
            System.out.println("Error with connection");
        }
        return result;
    }

    public String getProductId(String product) {
        String url = new String("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/food/products/search?query=");
        url = url + product;
        String response = new String();
        String result = new String();
        try {
            response = this.sendGet(url);
            JSONObject wholeResponse = new JSONObject(response);
            JSONArray productList = wholeResponse.getJSONArray("products");
            JSONObject firstItem = productList.getJSONObject(1);
            result = firstItem.get("id").toString();
        }
        catch (Exception e) {
            System.out.println("Error with connection");
        }
        return result;
    }


        // HTTP GET request
        public String sendGet(String url) throws Exception {

//            String url = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/food/products/33423";

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("X-Mashape-Key", "sR6MoRG1yDmsh2PVbmG1Sh4AhMtUp1hOTjBjsnVZ7lj4iDB5ER");

            int responseCode = con.getResponseCode();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            return response.toString();

        }

    }

