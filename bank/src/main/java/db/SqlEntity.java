package db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlEntity {

    String tableName();

    String insertSql();
    void fillInsert(PreparedStatement ps) throws SQLException;

    String updateSql();
    void fillUpdate(PreparedStatement ps) throws SQLException;

    int getId();
}
