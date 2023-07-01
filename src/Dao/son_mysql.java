package Dao;

import java.util.ArrayList;

public class son_mysql extends FatherClass {


    public son_mysql(String tableName, int chose) {
        super(tableName, chose);
    }

    @Override
    public ArrayList<Object> Method_Find10(String tableName) {
        String query = "select * from " + tableName + " limit 10";
        return super.Method_Find10(query);
    }

}
