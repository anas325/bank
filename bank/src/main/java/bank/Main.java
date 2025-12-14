package bank;

import db.GenericDAO;

public class Main {
    public static void main(String[] args) {
           GenericDAO<Livre> dao = new GenericDAO<>(
    rs -> new Livre(
        rs.getInt("id"),
        rs.getString("titre"),
        rs.getString("auteur")
    )
);

// INSERT
dao.insert(new Livre(1, "insert", "works"));

// UPDATE
Livre l = dao.findById("livre", 1);
l = new Livre(1, "Le Petit Prince (Révisé)", "A. de Saint-Exupéry");
dao.update(l);

// DELETE
dao.delete(l);

// FIND ALL
dao.findAll("livre").forEach(System.out::println);


    }
    
}