package database;

/**
 * Created by diego on 25/04/2016.
 */
public class PlacesDBSchema {
    public static final class PlacesDataTable{
        public final static String NAME = "Tbl_Places";

        public static final class Cols {

            public static final String[] COL = {"id", "place_id", "name", "address", "lat", "lng"};

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
