package database;

/**
 * Created by hgx95 on 07/02/2016.
 */
public class EmergencyPhoneNumDBSchema {

    public static final class EmergencyPhoneNumTable {

        public static final String NAME = "Tbl_EmergencyPhoneNumbers";

        public static final class Cols {

            public static final String NAME = "name";
            public static final String PHONE_NUMBER = "number";
            
            public static final String[] COL = {"name", "number"}; 
28 
 
29             public static String getCreateQueryCols(){ 
30                 String query =""; 
31                 for (int i=0 ; i<COL.length ; i++){ 
32                     query = query + COL[i] + " text "; 
33                     if(i<COL.length-1) 
34                         query = query+","; 
35                 } 
36                 return query; 
37             } 

        }
    }
}
