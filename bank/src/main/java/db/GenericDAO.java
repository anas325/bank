package db;

import java.sql.*;
import java.util.*;


public class GenericDAO<T extends SqlEntity> {

    private final rowMapper<T> mapper;

    public GenericDAO(rowMapper<T> mapper) {
        this.mapper = mapper;
    }

    // INSERT
    public void insert(T obj) {
        try (Connection conn = DB.connect();
             PreparedStatement ps = conn.prepareStatement(obj.insertSql())) {

            obj.fillInsert(ps);
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // UPDATE
    public void update(T obj) {
        try (Connection conn = DB.connect();
             PreparedStatement ps = conn.prepareStatement(obj.updateSql())) {

            obj.fillUpdate(ps);
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // DELETE
    public void delete(T obj) {
        String sql = "DELETE FROM " + obj.tableName() + " WHERE id = ?";
        try (Connection conn = DB.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, obj.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // FIND BY ID
    public T findById(String table, int id) {
        String sql = "SELECT * FROM " + table + " WHERE id = ?";
        try (Connection conn = DB.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? mapper.map(rs) : null;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // FIND ALL
    public List<T> findAll(String table) {
        List<T> list = new ArrayList<>();
        String sql = "SELECT * FROM " + table;

        try (Connection conn = DB.connect();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(mapper.map(rs));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }
}
