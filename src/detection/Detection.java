package detection;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Detection {

    public static void main(String[] args)  {
                MongoClient mongoClient = new MongoClient();
                MongoDatabase database = mongoClient.getDatabase("rig_witsml");
                               
                Scanner s = new Scanner(System.in);
     int count = 0;
     System.out.println("Enter the WellBore,logID You need");
 
    /*String WellB = s.nextLine();*/
 /*   String LogId = s.nextLine();
     System.out.println("Now Enter the Mnemonic You need");
     String Mnem = s.nextLine();
    String ss = null;
    int matchcount = 0;
    	MongoCollection<Document> collection = database.getCollection("groupLog");
        DistinctIterable<String> mdocuments = collection.distinct("curvInfoList.mnemonic", String.class ).filter(eq("uidLog",LogId));
        for (String document : mdocuments) {
         
         System.out.println(document);
        if(document.equals(Mnem)){
         ss = document ; 
        }
     }System.out.println("WEW");
        System.out.println(ss);
        
         DistinctIterable<String> documents = collection.distinct("curvInfoList.mnemonic", String.class );
      for (String ndocument : mdocuments) {
         
         count++;
        if(ss.equals(ndocument)){
            matchcount  = count;
        }
     }
      
      System.out.println(matchcount);
  */     
        String[] mnems = new String[200];
	       		String WellB = s.nextLine();
                        String LogId = s.nextLine();	
                        
	        DB db_log = mongoClient.getDB("rig_witsml");
	        System.out.println("Connect to database successfully");
	    	DBCollection coll = db_log.getCollection("groupLog");
			      
	        DBObject keys_log = new BasicDBObject();
	        DBObject key_log = new BasicDBObject();
	 		
	 		keys_log.put("curvInfoList.mnemonic", "");
                        key_log.put("uidLog", LogId);
                        key_log.put("uidWell", WellB);
			DBCursor log = coll.find(key_log, keys_log);
	 		int counter = 0;
	 		while(log.hasNext())
	 		{
                                final DBObject result = log.next();
	 			BasicDBList values = (BasicDBList) result.get("curvInfoList");
                                    
                                    for (Object value : values) {
                                        Pattern p = Pattern.compile("\"([^\"]*)\"");
                                    
                                           String ss = value.toString();
                                           Matcher m = p.matcher(ss);
                                           while (m.find()) {
                                               
                                               mnems[counter] = m.group(1);
                                               if (!mnems[counter].equals("mnemonic")){
                                               System.out.println(mnems[counter] + " " + counter);
                                               counter++;
                                               }
                                               }
                                            }
                                        }
                        
                       System.out.println("Select The Mnemonic U need");
                       
                       String Mnem = s.nextLine();
                       int MatchM = 0;
                       for (int k=0;k<counter;k++){
                           if(mnems[k].equals(Mnem)){
                               MatchM = k;
                           }
                       }
                           System.out.println(MatchM);
                           
                           
                           
                                    }
    public String CSV(int j)throws FileNotFoundException, IOException{

    BufferedReader br = new BufferedReader(new FileReader("D:/Varun/Pattern_Detection/1.csv"));

                    String str;
                   List<String> list = new ArrayList<>();
                    while((str = br.readLine()) != null){
                                list.add(str);
                        }
                    String[] a = list.toArray(new String[list.size()]); 
                    String[] ss = new String[5];
                     for (int i = 0; i < a.length; i++){   
                         if((a[i]) != null) {
                             
                             ss[i] = a[i];
                                 }
                    }
                     return ss[j]; 
    }

     
}


    


