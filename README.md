1. Description

GenericDAO est une classe générique pour gérer facilement les opérations CRUD (Create, Read, Update, Delete) sur n’importe quelle entité Java.

Pré-requis :
Toutes les entités doivent implémenter l’interface SqlEntity et fournir :

le nom de la table (tableName)

les requêtes SQL d’insertion et de mise à jour (insertSql, updateSql)

les méthodes pour remplir les PreparedStatement (fillInsert, fillUpdate)

l’identifiant (getId)

2. Exemple avec la classe Livre
Livre.java
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
    public String tableName() { return "livre"; }

    @Override
    public String insertSql() { return "INSERT INTO livre(id, titre, auteur) VALUES (?, ?, ?)"; }

    @Override
    public void fillInsert(PreparedStatement ps) throws SQLException {
        ps.setInt(1, id);
        ps.setString(2, titre);
        ps.setString(3, auteur);
    }

    @Override
    public String updateSql() { return "UPDATE livre SET titre = ?, auteur = ? WHERE id = ?"; }

    @Override
    public void fillUpdate(PreparedStatement ps) throws SQLException {
        ps.setString(1, titre);
        ps.setString(2, auteur);
        ps.setInt(3, id);
    }

    @Override
    public int getId() { return id; }

    @Override
    public String toString() {
        return "Livre [id=" + id + ", titre=" + titre + ", auteur=" + auteur + "]";
    }
}

3. Utilisation dans Main.java
Étapes

Créer le DAO pour votre entité :

GenericDAO<Livre> dao = new GenericDAO<>();


Insérer un objet dans la base :

dao.insert(new Livre(1, "Le Petit Prince", "Antoine de Saint-Exupéry"));


Lire un objet par son ID :

Livre l = dao.findById("livre", 1);
System.out.println(l);


Mettre à jour un objet :

l = new Livre(1, "Le Petit Prince (Révisé)", "A. de Saint-Exupéry");
dao.update(l);


Supprimer un objet :

dao.delete(l);


Lister tous les objets d’une table :

dao.findAll("livre").forEach(System.out::println);

Exemple complet :
public class Main {
    public static void main(String[] args) {
        GenericDAO<Livre> dao = new GenericDAO<>();

        dao.insert(new Livre(1, "Le Petit Prince", "Antoine de Saint-Exupéry"));

        Livre l = dao.findById("livre", 1);
        System.out.println(l);

        l = new Livre(1, "Le Petit Prince (Révisé)", "A. de Saint-Exupéry");
        dao.update(l);

        dao.delete(l);

        dao.findAll("livre").forEach(System.out::println);
    }
}

4. Résumé

Créer votre classe et implémenter SqlEntity.

Instancier GenericDAO<T>.

Utiliser les méthodes CRUD : insert, update, delete, findById, findAll.

Réutilisable pour toutes vos entités SQL, sécurisé grâce aux PreparedStatement.