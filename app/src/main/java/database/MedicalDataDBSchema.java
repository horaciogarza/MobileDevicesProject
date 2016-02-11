package database;

/**
 * Created by hgx95 on 07/02/2016.
 */
public class MedicalDataDBSchema {

    public static final class MedicalDataTable {

        public static final String NAME = "Tbl_MedicalInfo";

        public static final class Cols {

            //Medical Info

            public static final String INSURANCE_PROVIDER = "insuranceProvider";
            public static final String LOCATION = "location";
            public static final String POLICY_NUMBER = "policyNumber";
            public static final String DOCTORS_NAME = "doctorsName";
            public static final String OFFICE_NUMBER = "officeNumber";
            
            public static final String[] COL = {"insuranceProvider", "location", "policyNumber", "doctorsName", "officeNumber"}; 
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
