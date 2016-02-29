package database;

/**
 * Created by hgx95 on 07/02/2016.
 */
public class PersonalInfoDBSchema {

    public static final class UserDataTable {

        public static final String NAME = "Tbl_PersonalInfo";

        public static final class Cols {

            //Personal Info


            public static final String NAME = "name"; //ready
            public static final String ADDRESS = "address"; //ready
            public static final String BLOOD_TYPE = "bloodType"; //ready
            public static final String CITY = "city"; //ready
            public static final String EMAIL = "email"; //ready
            public static final String ZIP_CODE = "zipCode"; //ready
            public static final String MOBILE = "mobile"; //ready
            public static final String HOME_PHONE = "homePhone"; //ready
            public static final String MARITAL_STATUS = "maritalStatus"; //ready
            public static final String WEIGHT = "weight";
            public static final String HEIGHT = "height";
            public static final String BIRTHDAY = "birthday";

            public static final String[] COL = {"name", "address", "bloodType", "city",  "email", "zipCode", "mobile", "homePhone", "maritalStatus", "birthday"};

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
