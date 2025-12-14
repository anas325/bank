package bank;

import db.SqlEntity;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Livre implements SqlEntity {

    private int id;
    private String titre;
    private String auteur;

    public Livre(int id, String titre, String auteur) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
    }

    @Override
    public String tableName() {
        return "livre";
    }

    @Override
    public String insertSql() {
        return "INSERT INTO livre(id, titre, auteur) VALUES (?, ?, ?)";
    }

    @Override
    public void fillInsert(PreparedStatement ps) throws SQLException {
        ps.setInt(1, id);
        ps.setString(2, titre);
        ps.setString(3, auteur);
    }

    @Override
    public String updateSql() {
        return "UPDATE livre SET titre = ?, auteur = ? WHERE id = ?";
    }

    @Override
    public void fillUpdate(PreparedStatement ps) throws SQLException {
        ps.setString(1, titre);
        ps.setString(2, auteur);
        ps.setInt(3, id);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Livre [id=" + id + ", titre=" + titre + ", auteur=" + auteur + "]";
    }

    // getters / setters si besoin
}
