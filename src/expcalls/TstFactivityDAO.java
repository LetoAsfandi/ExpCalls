package expcalls;

import bdd.Factivity;
import bdd.FactivityDAO;
import java.io.IOException;
import java.sql.SQLException;
import utils.ApplicationProperties;
import utils.DBManager;
import utils.DBServer;
import utils.DBServerException;

/**
 * TstFactivityDAO programme permettant de tester le pattern DAO pour Factivity.
 *
 * @version 0.33
 * @author Thierry Baribaud
 */
public class TstFactivityDAO {

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
        FactivityDAO factivityDAO;
        Factivity factivity1;
        Factivity factivity;
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
            factivityDAO = new FactivityDAO(dBManager.getConnection());
            factivityDAO.setInsertPreparedStatement();
            factivity1 = new Factivity();
            factivity1.setA4abbname("PLBZNG");
            factivity1.setA4name("plombier zingueur");
            System.out.println("Factivity(avant insertion)=" + factivity1);
            factivityDAO.insert(factivity1);
            factivityDAO.closeInsertPreparedStatement();
            System.out.println("Factivity(apr�s insertion)=" + factivity1);
            System.out.println("Rang�e(s) affect�e(s)=" + factivityDAO.getNbAffectedRow());

// Essai de mise � jour
            factivityDAO.setUpdatePreparedStatement();
            factivity1.setA4name(factivity1.getA4name() + " couvreur");
            factivityDAO.update(factivity1);
            System.out.println("Factivity(apr�s mise � jour)=" + factivity1);
            System.out.println("Rang�e(s) affect�e(s)=" + factivityDAO.getNbAffectedRow());
            factivityDAO.closeUpdatePreparedStatement();

// Essai de lecture
            factivityDAO.filterById(factivity1.getA4num());
            System.out.println("  SelectStatement=" + factivityDAO.getSelectStatement());
            factivityDAO.setSelectPreparedStatement();
            i = 0;
            while ((factivity = factivityDAO.select()) != null) {
                i++;
                System.out.println("Factivity(" + i + ")=" + factivity);
                System.out.println("  getA4num()=" + factivity.getA4num());
                System.out.println("  getA4abbname()=" + factivity.getA4abbname());
                System.out.println("  getA4name()=" + factivity.getA4name());
            }
            factivityDAO.closeSelectPreparedStatement();

// Essai de suppression
            System.out.println("Suppression de : " + factivity1);
            factivityDAO.setDeletePreparedStatement();
            factivityDAO.delete(factivity1.getA4num());
            factivityDAO.closeDeletePreparedStatement();
            System.out.println("Rang�e(s) affect�e(s)=" + factivityDAO.getNbAffectedRow());

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
