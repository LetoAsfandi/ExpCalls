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
 * @version 0.33
 * @author Thierry Baribaud
 */
public class TstFoperatDAO {

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
        FoperatDAO foperatDAO;
        Foperat foperat1;
        Foperat foperat;
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
            foperatDAO = new FoperatDAO(dBManager.getConnection());
            foperatDAO.setInsertPreparedStatement();
            foperat1 = new Foperat();
            foperat1.setOnum(0);
            foperat1.setOname("polo marco");
            foperat1.setOabbname("MPO");
            foperat1.setOnumpabx(99);
            System.out.println("Foperat(avant insertion)=" + foperat1);
            foperatDAO.insert(foperat1);
            foperatDAO.closeInsertPreparedStatement();
            System.out.println("Foperat(apr�s insertion)=" + foperat1);
            System.out.println("Rang�e(s) affect�e(s)=" + foperatDAO.getNbAffectedRow());

// Essai de mise � jour
            foperatDAO.setUpdatePreparedStatement();
            foperat1.setOname(foperat1.getOname() + " del monte");
            foperatDAO.update(foperat1);
            System.out.println("Foperat(apr�s mise-�-jour)=" + foperat1);
            System.out.println("Rang�e(s) affect�e(s)=" + foperatDAO.getNbAffectedRow());
            foperatDAO.closeUpdatePreparedStatement();

// Essai de lecture
            foperatDAO.filterByName("po");
            System.out.println("  SelectStatement=" + foperatDAO.getSelectStatement());
            foperatDAO.setSelectPreparedStatement();
            i = 0;
            while ((foperat = foperatDAO.select()) != null) {
                i++;
                System.out.println("Foperat(" + i + ")=" + foperat);
            }
            foperatDAO.closeSelectPreparedStatement();

// Essai de suppression
            System.out.println("Suppression de : " + foperat1);
            foperatDAO.setDeletePreparedStatement();
            foperatDAO.delete(foperat1.getOnum());
            foperatDAO.closeDeletePreparedStatement();
            System.out.println("Rang�e(s) affect�e(s)=" + foperatDAO.getNbAffectedRow());

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
