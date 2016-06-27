package expcalls;

import bdd.Furgent;
import bdd.FurgentDAO;
import java.io.IOException;
import java.sql.SQLException;
import utils.ApplicationProperties;
import utils.DBManager;
import utils.DBServer;
import utils.DBServerException;

/**
 * TstFurgentDAO programme permettant de tester le pattern DAO pour Furgent.
 *
 * @version Juin 2016
 * @author Thierry Baribaud
 */
public class TstFurgentDAO {

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
        FurgentDAO MyFurgentDAO;
        Furgent MyFurgent1;
        Furgent MyFurgent;
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
            MyFurgentDAO = new FurgentDAO(MyDBManager.getConnection());
            MyFurgentDAO.setInsertPreparedStatement();
            MyFurgent1 = new Furgent();
            MyFurgent1.setUnum(0);
            MyFurgent1.setUname("terra incognita");
            MyFurgent1.setUabbname("INCOGNIT");
            MyFurgent1.setUnewurg(5);
            System.out.println("Furgent(avant insertion)=" + MyFurgent1);
            MyFurgentDAO.insert(MyFurgent1);
            MyFurgentDAO.closeInsertPreparedStatement();
            System.out.println("Furgent(apr�s insertion)=" + MyFurgent1);
            System.out.println("Rang�e(s) affect�e(s)=" + MyFurgentDAO.getNbAffectedRow());

// Essai de mise � jour
            MyFurgentDAO.setUpdatePreparedStatement();
            MyFurgent1.setUname(MyFurgent1.getUname() + " at Atlantis");
            MyFurgentDAO.update(MyFurgent1);
            System.out.println("Furgent(apr�s mise-�-jour)=" + MyFurgent1);
            System.out.println("Rang�e(s) affect�e(s)=" + MyFurgentDAO.getNbAffectedRow());
            MyFurgentDAO.closeUpdatePreparedStatement();

// Essai de lecture
            MyFurgentDAO.filterByCode("INCOGNIT");
            MyFurgentDAO.filterByName(MyFurgent1.getUnum(), "terra");
            System.out.println("  SelectStatement=" + MyFurgentDAO.getSelectStatement());
            MyFurgentDAO.setSelectPreparedStatement();
            i = 0;
            while ((MyFurgent = MyFurgentDAO.select()) != null) {
                i++;
                System.out.println("Furgent(" + i + ")=" + MyFurgent);
            }
            MyFurgentDAO.closeSelectPreparedStatement();

// Essai de suppression
            System.out.println("Suppression de : " + MyFurgent1);
            MyFurgentDAO.setDeletePreparedStatement();
            MyFurgentDAO.delete(MyFurgent1.getUnum());
            MyFurgentDAO.closeDeletePreparedStatement();
            System.out.println("Rang�e(s) affect�e(s)=" + MyFurgentDAO.getNbAffectedRow());

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
