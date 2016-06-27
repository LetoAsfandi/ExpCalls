package expcalls;

import bdd.Fagency;
import bdd.FagencyDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import utils.ApplicationProperties;
import utils.DBManager;
import utils.DBServer;
import utils.DBServerException;

/**
 * TstFagencyDAO programme permettant de tester le pattern DAO pour Fagency.
 *
 * @version Juin 2016
 * @author Thierry Baribaud
 */
public class TstFagencyDAO {

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
        FagencyDAO MyFagencyDAO;
        Fagency MyFagency1;
        Fagency MyFagency;
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
            MyFagencyDAO = new FagencyDAO(MyDBManager.getConnection());
            MyFagencyDAO.setInsertPreparedStatement();
            MyFagency1 = new Fagency();
            MyFagency1.setA6unum(MyArgs.getUnum());
            MyFagency1.setA6extname("terra incognita");
            MyFagency1.setA6name("utopia");
            MyFagency1.setA6abbname("UTOPIA");
            MyFagency1.setA6email("utopia@gmail.com");
            MyFagency1.setA6daddress("12, rue des r�ves");
            MyFagency1.setA6daddress2("b�timent B");
            MyFagency1.setA6dposcode("92400");
            MyFagency1.setA6dcity("UTOPIA CITY");
            MyFagency1.setA6teloff("01.01.01.01.01");
            MyFagency1.setA6teldir("02.02.02.02.02");
            MyFagency1.setA6telfax("03.03.03.03.03");
            MyFagency1.setA6active(1);
            MyFagency1.setA6begactive(new Timestamp(new java.util.Date().getTime()));
            MyFagency1.setA6endactive(Timestamp.valueOf("2050-12-31 23:59:59.0"));
            System.out.println("Fagency(avant insertion)=" + MyFagency1);
            MyFagencyDAO.insert(MyFagency1);
            MyFagencyDAO.closeInsertPreparedStatement();
            System.out.println("Fagency(apr�s insertion)=" + MyFagency1);
            System.out.println("Rang�e(s) affect�e(s)=" + MyFagencyDAO.getNbAffectedRow());

// Essai de mise � jour
            MyFagencyDAO.setUpdatePreparedStatement();
            MyFagency1.setA6email(MyFagency1.getA6email() + ",utopia@free.fr");
            MyFagencyDAO.update(MyFagency1);
            System.out.println("Fagency(apr�s mise-�-jour)=" + MyFagency1);
            System.out.println("Rang�e(s) affect�e(s)=" + MyFagencyDAO.getNbAffectedRow());
            MyFagencyDAO.closeUpdatePreparedStatement();

// Essai de lecture
            MyFagencyDAO.filterByCode(MyFagency1.getA6unum(), "INCOGNITA");
            MyFagencyDAO.filterByName(MyFagency1.getA6unum(), "uto");
            System.out.println("  SelectStatement=" + MyFagencyDAO.getSelectStatement());
            MyFagencyDAO.setSelectPreparedStatement();
            i = 0;
            while ((MyFagency = MyFagencyDAO.select()) != null) {
                i++;
                System.out.println("Fagency(" + i + ")=" + MyFagency);
                System.out.println("  getA6num()=" + MyFagency.getA6num());
                System.out.println("  getA6unum()=" + MyFagency.getA6unum());
                System.out.println("  getA6extname()=" + MyFagency.getA6extname());
                System.out.println("  getA6name()=" + MyFagency.getA6name());
                System.out.println("  getA6abbname()=" + MyFagency.getA6abbname());
                System.out.println("  getA6email()=" + MyFagency.getA6email());
                System.out.println("  getA6daddress()=" + MyFagency.getA6daddress());
                System.out.println("  getA6daddress2()=" + MyFagency.getA6daddress2());
                System.out.println("  getA6dposcode()=" + MyFagency.getA6dposcode());
                System.out.println("  getA6dcity()=" + MyFagency.getA6dcity());
                System.out.println("  getA6teloff()=" + MyFagency.getA6teloff());
                System.out.println("  getA6teldir()=" + MyFagency.getA6teldir());
                System.out.println("  getA6telfax()=" + MyFagency.getA6telfax());
                System.out.println("  getA6active()=" + MyFagency.getA6active());
                System.out.println("  getA6begactive()=" + MyFagency.getA6begactive());
                System.out.println("  getA6endactive()=" + MyFagency.getA6endactive());
            }
            MyFagencyDAO.closeSelectPreparedStatement();

// Essai de suppression
            System.out.println("Suppression de : " + MyFagency1);
            MyFagencyDAO.setDeletePreparedStatement();
            MyFagencyDAO.delete(MyFagency1.getA6num());
            MyFagencyDAO.closeDeletePreparedStatement();
            System.out.println("Rang�e(s) affect�e(s)=" + MyFagencyDAO.getNbAffectedRow());

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
