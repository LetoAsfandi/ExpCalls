package expcalls;

import bdd.Ftype;
import bdd.FtypeDAO;
import java.io.IOException;
import java.sql.SQLException;
import utils.ApplicationProperties;
import utils.DBManager;
import utils.DBServer;
import utils.DBServerException;

/**
 * TstFtypeDAO programme permettant de tester le pattern DAO pour Ftype.
 *
 * @version 0.33
 * @author Thierry Baribaud
 */
public class TstFtypeDAO {

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
        FtypeDAO ftypeDAO;
        Ftype ftype1;
        Ftype ftype;
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
            ftypeDAO = new FtypeDAO(dBManager.getConnection());
            ftypeDAO.setInsertPreparedStatement();
            ftype1 = new Ftype();
            ftype1.setTtnum(0);
            ftype1.setTtunum(getArgs.getUnum());
            ftype1.setTtextname("message sans suite");
            ftype1.setTtypename("message");
            ftype1.setTtype(99);
            System.out.println("Ftype(avant insertion)=" + ftype1);
            ftypeDAO.insert(ftype1);
            ftypeDAO.closeInsertPreparedStatement();
            System.out.println("Ftype(apr�s insertion)=" + ftype1);
            System.out.println("Rang�e(s) affect�e(s)=" + ftypeDAO.getNbAffectedRow());

// Essai de mise � jour
            ftypeDAO.setUpdatePreparedStatement();
            ftype1.setTtypename(ftype1.getTtypename() + " simple");
            ftypeDAO.update(ftype1);
            System.out.println("Ftype(apr�s mise-�-jour)=" + ftype1);
            System.out.println("Rang�e(s) affect�e(s)=" + ftypeDAO.getNbAffectedRow());
            ftypeDAO.closeUpdatePreparedStatement();

// Essai de lecture
            ftypeDAO.filterByName(getArgs.getUnum(), "m");
            System.out.println("  SelectStatement=" + ftypeDAO.getSelectStatement());
            ftypeDAO.setSelectPreparedStatement();
            i = 0;
            while ((ftype = ftypeDAO.select()) != null) {
                i++;
                System.out.println("Ftype(" + i + ")=" + ftype);
            }
            ftypeDAO.closeSelectPreparedStatement();

// Essai de suppression
            System.out.println("Suppression de : " + ftype1);
            ftypeDAO.setDeletePreparedStatement();
            ftypeDAO.delete(ftype1.getTtnum());
            ftypeDAO.closeDeletePreparedStatement();
            System.out.println("Rang�e(s) affect�e(s)=" + ftypeDAO.getNbAffectedRow());

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
