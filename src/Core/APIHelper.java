package Core;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.util.*;
import Core.Leitner.*;
import Core.FileSaver;
import java.io.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.*;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;


public class APIHelper {
    private String CreateDeck(String Url,String XML,String DeckName,String Author) throws Exception{
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(Url);
        
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("xml",XML));
        params.add(new BasicNameValuePair("name",DeckName));
        params.add(new BasicNameValuePair("author",Author));
        httpPost.setEntity(new UrlEncodedFormEntity(params));
        
        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        
        if(entity != null){
            InputStream inputStream = entity.getContent();          
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String inputLine;
            StringBuffer responseReader = new StringBuffer();
            
            while((inputLine = in.readLine()) != null){
                responseReader.append(inputLine);
            }
            in.close();
            return responseReader.toString();
        }
        return null;
    }
    
    private String APIRequestGET(String Url,String Method) throws Exception{
        URL obj = new URL(Url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod(Method);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        
        while((inputLine = in.readLine()) != null){
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
    
    public void DownloadDeck(){
    
    }
    
    public boolean PostDeck(String Author,Deck ThisDeck){
        List<String> Lines = FileSaver.CreateFile(ThisDeck, true);
        String XML = "";
        for(String line : Lines){
            XML += line + "\n";
        }
        
        try{
            CreateDeck("http://localhost:8080/api/decks",XML,ThisDeck.DeckName,"Wicked7000");
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }
    
    public List<Map<String,String>> GetDecks(){
        ArrayList<String> DeckNames = new ArrayList<>();
        String response = null;
        try{
            response = APIRequestGET("http://localhost:8080/api/decks","GET");
        }catch(Exception e){
            System.out.println(e);
        }
        JSONParser parser = new JSONParser();
        JSONArray json = null;
        try{
            json = (JSONArray) parser.parse(response);
        }catch(Exception e){
            System.out.println(e);
        }
        
        List<Map<String,String>> DataHolder = new ArrayList<Map<String,String>>();
        for(int X=0;X<json.size();X++){
            try{
             Map<String,String> ThisMap = new HashMap<>();
             JSONObject Obj = (JSONObject) parser.parse(json.get(X).toString());
             ThisMap.put("name", Obj.get("name").toString());
             ThisMap.put("id", Obj.get("_id").toString());
             ThisMap.put("xml", Obj.get("xml").toString());
             DataHolder.add(ThisMap);
            }catch(Exception e){
                System.out.println(e);
            }
        }
        return DataHolder;
    }
}
