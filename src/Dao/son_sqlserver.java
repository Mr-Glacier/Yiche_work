package Dao;


import java.util.ArrayList;

public class son_sqlserver extends FatherClass {

    public son_sqlserver(String tableName, int chose) {
        super(tableName, chose);
    }

    //sqlserver 查询前10条 select top 10 * from table_name
    //MySQL 查询前10条select * from table_name limit 10
    //String query1 = "select top 10 * from T_DModel";
    @Override
    public ArrayList<Object> Method_Find10(String tableName) {
        String query = "select top 10 * from  " + tableName + "";
        return super.Method_Find10(query);
    }
}
