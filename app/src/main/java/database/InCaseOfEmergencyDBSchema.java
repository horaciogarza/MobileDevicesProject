package database;

/**
 * Created by hgx95 on 07/02/2016.
 */
public class InCaseOfEmergencyDBSchema {

    public static final String NAME = "Tbl_InCaseOfEmergency";

    public static final class InCaseOfEmergencyTable{

        public static final String NAME = "Tbl_InCaseOfEmergency";

        public static final class Cols{

            //IN CASE OF EMERGENCY Info

            public static final String CONTACT_NAME = "name";
            public static final String CONTACT_RELATIONSHIP = "relationship";
            public static final String CONTACT_MOBILE = "mobile";
            public static final String CONTACT_PHONE = "phone";

            public static final String[] COL = {"name", "relationship", "mobile", "phone"}; 
28 
 
29             public static String getCreateQueryCols(){ 
30                 String query =""; 
31                 for (int i=0;i<COL.length;i++){ 
32                     query = query + COL[i] + " text "; 
33                     if(i<COL.length-1) 
34                         query = query+","; 
35                 } 
36                 return query; 
37             } 

        }
    }
}
