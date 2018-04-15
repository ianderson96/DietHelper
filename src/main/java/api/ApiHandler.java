package api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;

public class ApiHandler {

    private final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws Exception {
        ApiHandler api = new ApiHandler();
        String food = new String("carrots");
        String badge = new String("sesame_free");
        String ingredient = new String("salt");
//        System.out.println(api.containsBadge(food, badge));
        System.out.println(api.containsIngredient(food, ingredient));

    }

    private String getProductInfo(String productID) {
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

    private String getProductId(String product) {
        String encodedProduct = new String();
        try {
            encodedProduct = URLEncoder.encode(product, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            System.out.println("encoding doesn't work");
        }
        String url = new String("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/food/products/search?query=");
        url = url + encodedProduct;
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

    private ArrayList<String> getBadgeArray(String product) {
        String id = this.getProductId(product);
        String productInfo = this.getProductInfo(id);
        JSONObject productJSON = new JSONObject(productInfo);
        JSONArray badges = productJSON.getJSONArray("badges");
        System.out.println(badges.toString());
        ArrayList<String> result = new ArrayList<String>();
        if (badges != null) {
            for (int i=0;i<badges.length();i++){
                result.add(badges.getString(i));
            }
        }
        return result;
    }

    private ArrayList<String> getIngredientArray(String product) {
        String id = this.getProductId(product);
        String productInfo = this.getProductInfo(id);
        JSONObject productJSON = new JSONObject(productInfo);
        JSONArray ingredients = productJSON.getJSONArray("ingredients");
        ArrayList<String> result = new ArrayList<String>();
        if (ingredients != null) {
            for (int i=0;i<ingredients.length();i++){
                result.add(ingredients.getJSONObject(i).getString("name"));
            }
        }
        return result;
    }

    public Boolean containsBadge(String food, String badge) {
        ArrayList<String> badges = this.getBadgeArray(food);
        if (badge.equals("wheat_free")) {
            return badges.contains("wheat_free") || badges.contains("gluten_free");
        }
        return badges.contains(badge);
    }

    public Boolean containsIngredient(String food, String ingredient) {
        ArrayList<String> ingredients = this.getIngredientArray(food);
        System.out.println(ingredients);
        if (ingredient.equals("wheat_free")) {
            return ingredient.contains("wheat_free") || ingredient.contains("gluten_free");
        }
        return ingredient.contains(ingredient);
    }


        // HTTP GET request
        public String sendGet(String url) throws Exception {

//            String url = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/food/products/33423";

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");

            //add request headers
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

