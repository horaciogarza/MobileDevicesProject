package database;

/**
 * Created by hgx95 on 07/02/2016.
 */
public class EmergencyPhoneNumDBSchema {

    public static final class EmergencyPhoneNumTable {

        public static final String NAME = "Tbl_EmergencyPhoneNumbers";

        public static final class Cols {

            public static final String ID = "id";
            public static final String NAME = "name";
            public static final String PHONE_NUMBER = "number";

            public static final String[] COL = {"name", "number"};


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
