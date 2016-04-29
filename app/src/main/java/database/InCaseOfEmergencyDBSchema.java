package database;

/**
 * Created by hgx95 on 07/02/2016.
 */
public class InCaseOfEmergencyDBSchema {

    public static final String NAME = "Tbl_InCaseOfEmergency";

    public static final class InCaseOfEmergencyTable {

        public static final String NAME = "Tbl_InCaseOfEmergency";

        public static final class Cols {

            //IN CASE OF EMERGENCY Info

            public static final String ID = "id";
            public static final String CONTACT_NAME = "name";
            public static final String CONTACT_RELATIONSHIP = "relationship";
            public static final String CONTACT_MOBILE = "mobile";
            public static final String CONTACT_PHONE = "phone";

            public static final String[] COL = {"name", "relationship", "mobile", "phone"};


            public static String getCreateQueryCols() {
                String query = "";
                for (int i = 0; i < COL.length; i++) {
                    query += (!COL[i].equals("id")) ? COL[i] + " text " : COL[i] + " INTEGER PRIMARY KEY AUTOINCREMENT ";
                    if (i < COL.length - 1)
                        query = query + ",";
                }
                return query;
            }

        }
    }
}
