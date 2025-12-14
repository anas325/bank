package db;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface rowMapper<T> {
     T map(ResultSet rs) throws SQLException;
}
