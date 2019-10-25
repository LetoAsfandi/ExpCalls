package expcalls;

import bdd.Fmenuit;
import bdd.FmenuitDAO;
import java.io.IOException;
import java.sql.SQLException;
import utils.ApplicationProperties;
import utils.DBManager;
import utils.DBServer;
import utils.DBServerException;
import utils.GetArgsException;

/**
 * TstFmenuitDAO programme permettant de tester le pattern DAO pour Fmenuit.
 *
 * @version 0.51
 * @author Thierry Baribaud
 */
public class TstFmenuitDAO {

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
        FmenuitDAO fmenuitDAO;
        Fmenuit fmenuit1;
        Fmenuit fmenuit;
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
            fmenuitDAO = new FmenuitDAO(dBManager.getConnection());
            fmenuitDAO.setInsertPreparedStatement();
            fmenuit1 = new Fmenuit();
            fmenuit1.setM6extname("message client");
            fmenuit1.setM6name("message");
            System.out.println("Fmenuit(avant insertion)=" + fmenuit1);
            fmenuitDAO.insert(fmenuit1);
            fmenuitDAO.closeInsertPreparedStatement();
            System.out.println("Fmenuit(apr�s insertion)=" + fmenuit1);
            System.out.println("Rang�e(s) affect�e(s)=" + fmenuitDAO.getNbAffectedRow());

// Essai de mise � jour
            fmenuitDAO.setUpdatePreparedStatement();
            fmenuit1.setM6extname(fmenuit1.getM6extname() + " totolito");
            fmenuitDAO.update(fmenuit1);
            System.out.println("Fmenuit(apr�s mise � jour)=" + fmenuit1);
            System.out.println("Rang�e(s) affect�e(s)=" + fmenuitDAO.getNbAffectedRow());
            fmenuitDAO.closeUpdatePreparedStatement();

// Essai de lecture
            fmenuitDAO.filterById(fmenuit1.getM6num());
            System.out.println("  SelectStatement=" + fmenuitDAO.getSelectStatement());
            fmenuitDAO.setSelectPreparedStatement();
            i = 0;
            while ((fmenuit = fmenuitDAO.select()) != null) {
                i++;
                System.out.println("Fmenuit(" + i + ")=" + fmenuit);
                System.out.println("  getM6num()=" + fmenuit.getM6num());
                System.out.println("  getM6extname()=" + fmenuit.getM6extname());
                System.out.println("  getM6name()=" + fmenuit.getM6name());
            }
            fmenuitDAO.closeSelectPreparedStatement();

// Essai de suppression
            System.out.println("Suppression de : " + fmenuit1);
            fmenuitDAO.setDeletePreparedStatement();
            fmenuitDAO.delete(fmenuit1.getM6num());
            fmenuitDAO.closeDeletePreparedStatement();
            System.out.println("Rang�e(s) affect�e(s)=" + fmenuitDAO.getNbAffectedRow());

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
