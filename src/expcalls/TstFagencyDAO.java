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
 * @version 0.33
 * @author Thierry Baribaud
 */
public class TstFagencyDAO {

    /**
     * Les arguments en ligne de commande permettent de changer le mode de
     * fonctionnement. Voir GetArgs pour plus de d�tails.
     *
     * @param args arguments de la ligne de commande.
     * @throws GetArgsException en cas de probl�me sur les param�tres.
     */
    public static void main(String[] args) throws GetArgsException {
        GetArgs getArgs;
        ApplicationProperties applicationProperties;
        DBServer dBServer;
        DBManager dBManager;
        FagencyDAO fagencyDAO;
        Fagency fagency1;
        Fagency fagency;
        long i;

        try {
            System.out.println("R�cup�ration des arguments en ligne de commande ...");
            getArgs = new GetArgs(args);
            System.out.println(getArgs);

            System.out.println("Lecture du fichier de param�tres ...");
            applicationProperties = new ApplicationProperties("ExpCalls.prop");

            System.out.println("Lecture des param�tres de base de donn�es ...");
            dBServer = new DBServer(getArgs.getSourceServer(), applicationProperties);
            System.out.println("  " + dBServer);

            dBManager = new DBManager(dBServer);

// Essai d'insertion
            fagencyDAO = new FagencyDAO(dBManager.getConnection());
            fagencyDAO.setInsertPreparedStatement();
            fagency1 = new Fagency();
            fagency1.setA6unum(getArgs.getUnum());
            fagency1.setA6extname("terra incognita");
            fagency1.setA6name("utopia");
            fagency1.setA6abbname("UTOPIA");
            fagency1.setA6email("utopia@gmail.com");
            fagency1.setA6daddress("12, rue des r�ves");
            fagency1.setA6daddress2("b�timent B");
            fagency1.setA6dposcode("92400");
            fagency1.setA6dcity("UTOPIA CITY");
            fagency1.setA6teloff("01.01.01.01.01");
            fagency1.setA6teldir("02.02.02.02.02");
            fagency1.setA6telfax("03.03.03.03.03");
            fagency1.setA6active(1);
            fagency1.setA6begactive(new Timestamp(new java.util.Date().getTime()));
            fagency1.setA6endactive(Timestamp.valueOf("2050-12-31 23:59:59.0"));
            System.out.println("Fagency(avant insertion)=" + fagency1);
            fagencyDAO.insert(fagency1);
            fagencyDAO.closeInsertPreparedStatement();
            System.out.println("Fagency(apr�s insertion)=" + fagency1);
            System.out.println("Rang�e(s) affect�e(s)=" + fagencyDAO.getNbAffectedRow());

// Essai de mise � jour
            fagencyDAO.setUpdatePreparedStatement();
            fagency1.setA6email(fagency1.getA6email() + ",utopia@free.fr");
            fagencyDAO.update(fagency1);
            System.out.println("Fagency(apr�s mise-�-jour)=" + fagency1);
            System.out.println("Rang�e(s) affect�e(s)=" + fagencyDAO.getNbAffectedRow());
            fagencyDAO.closeUpdatePreparedStatement();

// Essai de lecture
            fagencyDAO.filterByCode(fagency1.getA6unum(), "INCOGNITA");
            fagencyDAO.filterByName(fagency1.getA6unum(), "uto");
            System.out.println("  SelectStatement=" + fagencyDAO.getSelectStatement());
            fagencyDAO.setSelectPreparedStatement();
            i = 0;
            while ((fagency = fagencyDAO.select()) != null) {
                i++;
                System.out.println("Fagency(" + i + ")=" + fagency);
                System.out.println("  getA6num()=" + fagency.getA6num());
                System.out.println("  getA6unum()=" + fagency.getA6unum());
                System.out.println("  getA6extname()=" + fagency.getA6extname());
                System.out.println("  getA6name()=" + fagency.getA6name());
                System.out.println("  getA6abbname()=" + fagency.getA6abbname());
                System.out.println("  getA6email()=" + fagency.getA6email());
                System.out.println("  getA6daddress()=" + fagency.getA6daddress());
                System.out.println("  getA6daddress2()=" + fagency.getA6daddress2());
                System.out.println("  getA6dposcode()=" + fagency.getA6dposcode());
                System.out.println("  getA6dcity()=" + fagency.getA6dcity());
                System.out.println("  getA6teloff()=" + fagency.getA6teloff());
                System.out.println("  getA6teldir()=" + fagency.getA6teldir());
                System.out.println("  getA6telfax()=" + fagency.getA6telfax());
                System.out.println("  getA6active()=" + fagency.getA6active());
                System.out.println("  getA6begactive()=" + fagency.getA6begactive());
                System.out.println("  getA6endactive()=" + fagency.getA6endactive());
            }
            fagencyDAO.closeSelectPreparedStatement();

// Essai de suppression
            System.out.println("Suppression de : " + fagency1);
            fagencyDAO.setDeletePreparedStatement();
            fagencyDAO.delete(fagency1.getA6num());
            fagencyDAO.closeDeletePreparedStatement();
            System.out.println("Rang�e(s) affect�e(s)=" + fagencyDAO.getNbAffectedRow());

        } catch (IOException exception) {
            System.out.println("Erreur en lecture du fichier des propri�t�s " + exception);
        } catch (DBServerException exception) {
            System.out.println("Erreur avec le serveur de base de donn�es " + exception);
        } catch (ClassNotFoundException exception) {
            System.out.println("Erreur classe non trouv�e " + exception);
        } catch (SQLException exception) {
            System.out.println("Erreur SQL rencontr�e " + exception);
        }
    }
}
