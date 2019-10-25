package expcalls;

import bdd.Furgent;
import bdd.FurgentDAO;
import java.io.IOException;
import java.sql.SQLException;
import utils.ApplicationProperties;
import utils.DBManager;
import utils.DBServer;
import utils.DBServerException;
import utils.GetArgsException;

/**
 * TstFurgentDAO programme permettant de tester le pattern DAO pour Furgent.
 *
 * @version 0.51
 * @author Thierry Baribaud
 */
public class TstFurgentDAO {

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
        FurgentDAO furgentDAO;
        Furgent furgent1;
        Furgent furgent;
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
            furgentDAO = new FurgentDAO(dBManager.getConnection());
            furgentDAO.setInsertPreparedStatement();
            furgent1 = new Furgent();
            furgent1.setUnum(0);
            furgent1.setUname("terra incognita");
            furgent1.setUabbname("INCOGNIT");
            furgent1.setUnewurg(5);
            System.out.println("Furgent(avant insertion)=" + furgent1);
            furgentDAO.insert(furgent1);
            furgentDAO.closeInsertPreparedStatement();
            System.out.println("Furgent(apr�s insertion)=" + furgent1);
            System.out.println("Rang�e(s) affect�e(s)=" + furgentDAO.getNbAffectedRow());

// Essai de mise � jour
            furgentDAO.setUpdatePreparedStatement();
            furgent1.setUname(furgent1.getUname() + " at Atlantis");
            furgentDAO.update(furgent1);
            System.out.println("Furgent(apr�s mise-�-jour)=" + furgent1);
            System.out.println("Rang�e(s) affect�e(s)=" + furgentDAO.getNbAffectedRow());
            furgentDAO.closeUpdatePreparedStatement();

// Essai de lecture
            furgentDAO.filterByCode("INCOGNIT");
            furgentDAO.filterByName(furgent1.getUnum(), "terra");
            System.out.println("  SelectStatement=" + furgentDAO.getSelectStatement());
            furgentDAO.setSelectPreparedStatement();
            i = 0;
            while ((furgent = furgentDAO.select()) != null) {
                i++;
                System.out.println("Furgent(" + i + ")=" + furgent);
            }
            furgentDAO.closeSelectPreparedStatement();

// Essai de suppression
            System.out.println("Suppression de : " + furgent1);
            furgentDAO.setDeletePreparedStatement();
            furgentDAO.delete(furgent1.getUnum());
            furgentDAO.closeDeletePreparedStatement();
            System.out.println("Rang�e(s) affect�e(s)=" + furgentDAO.getNbAffectedRow());

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
