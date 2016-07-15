package expcalls;

import bdd.Foperat;
import bdd.FoperatDAO;
import java.io.IOException;
import java.sql.SQLException;
import utils.ApplicationProperties;
import utils.DBManager;
import utils.DBServer;
import utils.DBServerException;

/**
 * TstFoperatDAO programme permettant de tester le pattern DAO pour Foperat.
 *
 * @version Juillet 2016
 * @author Thierry Baribaud
 */
public class TstFoperatDAO {

    /**
     * Les arguments en ligne de commande permettent de changer le mode de
     * fonctionnement. Voir GetArgs pour plus de d�tails.
     *
     * @param Args arguments de la ligne de commande.
     * @throws GetArgsException en cas de probl�me sur les param�tres.
     */
    public static void main(String[] Args) throws GetArgsException {
        GetArgs MyArgs;
        ApplicationProperties MyApplicationProperties;
        DBServer MyDBServer;
        DBManager MyDBManager;
        FoperatDAO MyFoperatDAO;
        Foperat MyFoperat1;
        Foperat MyFoperat;
        long i;

        try {
            System.out.println("R�cup�ration des arguments en ligne de commande ...");
            MyArgs = new GetArgs(Args);
            System.out.println(MyArgs);

            System.out.println("Lecture du fichier de param�tres ...");
            MyApplicationProperties = new ApplicationProperties("MyDatabases.prop");

            System.out.println("Lecture des param�tres de base de donn�es ...");
            MyDBServer = new DBServer(MyArgs.getSourceServer(), MyApplicationProperties);
            System.out.println("  " + MyDBServer);

            MyDBManager = new DBManager(MyDBServer);

// Essai d'insertion
            MyFoperatDAO = new FoperatDAO(MyDBManager.getConnection());
            MyFoperatDAO.setInsertPreparedStatement();
            MyFoperat1 = new Foperat();
            MyFoperat1.setOnum(0);
            MyFoperat1.setOname("polo marco");
            MyFoperat1.setOabbname("MPO");
            MyFoperat1.setOnumpabx(99);
            System.out.println("Foperat(avant insertion)=" + MyFoperat1);
            MyFoperatDAO.insert(MyFoperat1);
            MyFoperatDAO.closeInsertPreparedStatement();
            System.out.println("Foperat(apr�s insertion)=" + MyFoperat1);
            System.out.println("Rang�e(s) affect�e(s)=" + MyFoperatDAO.getNbAffectedRow());

// Essai de mise � jour
            MyFoperatDAO.setUpdatePreparedStatement();
            MyFoperat1.setOname(MyFoperat1.getOname() + " del monte");
            MyFoperatDAO.update(MyFoperat1);
            System.out.println("Foperat(apr�s mise-�-jour)=" + MyFoperat1);
            System.out.println("Rang�e(s) affect�e(s)=" + MyFoperatDAO.getNbAffectedRow());
            MyFoperatDAO.closeUpdatePreparedStatement();

// Essai de lecture
            MyFoperatDAO.filterByName("po");
            System.out.println("  SelectStatement=" + MyFoperatDAO.getSelectStatement());
            MyFoperatDAO.setSelectPreparedStatement();
            i = 0;
            while ((MyFoperat = MyFoperatDAO.select()) != null) {
                i++;
                System.out.println("Foperat(" + i + ")=" + MyFoperat);
            }
            MyFoperatDAO.closeSelectPreparedStatement();

// Essai de suppression
            System.out.println("Suppression de : " + MyFoperat1);
            MyFoperatDAO.setDeletePreparedStatement();
            MyFoperatDAO.delete(MyFoperat1.getOnum());
            MyFoperatDAO.closeDeletePreparedStatement();
            System.out.println("Rang�e(s) affect�e(s)=" + MyFoperatDAO.getNbAffectedRow());

        } catch (IOException MyException) {
            System.out.println("Erreur en lecture du fichier des propri�t�s " + MyException);
        } catch (DBServerException MyException) {
            System.out.println("Erreur avec le serveur de base de donn�es " + MyException);
        } catch (ClassNotFoundException MyException) {
            System.out.println("Erreur classe non trouv�e " + MyException);
        } catch (SQLException MyException) {
            System.out.println("Erreur SQL rencontr�e " + MyException);
        }
    }
}
