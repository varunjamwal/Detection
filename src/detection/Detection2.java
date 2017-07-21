/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package detection;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;



public class Detection2 {
    public static void main(String[] args) throws IOException {
        Correlation c = new Correlation();
        Detection d = new Detection();
        MongoClient mongoClient = new MongoClient();
                MongoDatabase database = mongoClient.getDatabase("rig_witsml");
                FindIterable<Document> thedatabaserecords = database.getCollection("data").find();
                MongoCursor<Document> Theiterator = thedatabaserecords.iterator();
                DBObject myData = new BasicDBObject();
                myData.put("data","");
                int Counter=0;
                String[] val = new String[10000];
                List<String> x = new ArrayList<>();
                while (Theiterator.hasNext()) {
                    Document doc = Theiterator.next();
                x = (List<String>) doc.get("data");
                String key = doc.getString("keyData");
               String s = x.toString();
               String[] dataList = s.split(",");
               val[Counter]= dataList[5];
               Counter++;
                }
                int counterLoop = 0;
                System.out.println(val[1]);
                double[] a = new double[5];
                double[] b = new double[5];
                for(int i=0;i<5;i++){
                a[i] = Double.parseDouble(d.CSV(i));
                System.out.println(a[i]);
                }
                for(int j=counterLoop;j<counterLoop+5;j++){
                for(int m=0;m<5;m++){
                b[m] = Double.parseDouble(val[m+counterLoop]);
                }
                counterLoop++;
                
                System.out.println(c.corr(a,b));
                if(counterLoop == 9715){
                    break;
                }
                if(c.corr(a, b) >0.97 && c.corr(a, b)< 1){
                    System.out.println("matchfound !");
                }
                }
    }
    }
    
    
