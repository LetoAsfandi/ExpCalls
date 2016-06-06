package expcalls;

import bdd.EtatTicket;
import bdd.Fessais;
import bdd.FessaisDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import utils.ApplicationProperties;
import utils.DBManager;
import utils.DBServer;
import utils.DBServerException;

/**
 *
 * @author Thierry Baribaud
 */
public class tstFessaisDAO {

    /**
     * Les arguments en ligne de commande permettent de changer le mode de
     * fonctionnement. Voir GetArgs pour plus de d�tails.
     *
     * @param Args arguments de la ligne de commande.
     * @throws expcalls.GetArgsException en cas de probl�me sur les param�tres.
     */
    public static void main(String[] Args) throws GetArgsException {
        GetArgs MyArgs;
        ApplicationProperties MyApplicationProperties;
        DBServer MyDBServer;
        DBManager MyDBManager;
        FessaisDAO MyFessaisDAO;
        Fessais MyFessais1;
        Fessais MyFessais;
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

// Essai insertion
            MyFessaisDAO = new FessaisDAO(MyDBManager.getConnection(), 0, 0, 0, EtatTicket.EN_COURS);
            MyFessais1 = new Fessais();
            MyFessais1.setEcnum(6192014);
            MyFessais1.setEptr(0);
            MyFessais1.setEunum(572);
            MyFessais1.setEdate(new Timestamp(new java.util.Date().getTime()));
            MyFessais1.setEtime("14:00:00");
            MyFessais1.setEmessage("bonjour");
            MyFessais1.setEtnum(9123);
            MyFessais1.setEonum(36);
            MyFessais1.setEresult(-1);
            MyFessais1.setEduration(60);
            MyFessais1.setEtest(0);
            MyFessais1.setEinternal(0);
            MyFessais1.setEm3num(0);
            MyFessais1.setEgid(3333);
            System.out.println("Fessais(avant insertion)=" + MyFessais1);
            MyFessaisDAO.insert(MyFessais1);
            System.out.println("Fessais(apr�s insertion)=" + MyFessais1);
            System.out.println("Rang�e(s) affect�e(s)=" + MyFessaisDAO.getNbAffectedRow());

// Essai mise � jour
            MyFessais1.setEduration(MyFessais1.getEduration() + 30);
            MyFessaisDAO.update(MyFessais1);
            System.out.println("Fessais(apr�s mise � jour)=" + MyFessais1);
            System.out.println("Rang�e(s) affect�e(s)=" + MyFessaisDAO.getNbAffectedRow());
            MyFessaisDAO.close();

// Essai lecture
            MyFessaisDAO = new FessaisDAO(MyDBManager.getConnection(), 0, MyFessais1.getEcnum(), 0, EtatTicket.EN_COURS);
            i = 0;
            while ((MyFessais = MyFessaisDAO.select()) != null) {
                i++;
                System.out.println("Fessais(" + i + ")=" + MyFessais);
                System.out.println("  getEcnum()=" + MyFessais.getEcnum());
                System.out.println("  getEptr()=" + MyFessais.getEptr());
                System.out.println("  getEunum()=" + MyFessais.getEunum());
                System.out.println("  getEdate()=" + MyFessais.getEdate());
                System.out.println("  getEtime()=" + MyFessais.getEtime());
                System.out.println("  getEmessage()=" + MyFessais.getEmessage());
                System.out.println("  getEtnum()=" + MyFessais.getEtnum());
                System.out.println("  getEonum()=" + MyFessais.getEonum());
                System.out.println("  getEresult()=" + MyFessais.getEresult());
                System.out.println("  getEduration()=" + MyFessais.getEduration());
                System.out.println("  getEtest()=" + MyFessais.getEtest());
                System.out.println("  getEinternal()=" + MyFessais.getEinternal());
                System.out.println("  getEm3num()=" + MyFessais.getEm3num());
                System.out.println("  getEgid()=" + MyFessais.getEgid());
            }

// Essai suppression
            System.out.println("Suppression : " + MyFessais1);
            MyFessaisDAO.delete(MyFessais1.getEnumabs());
            System.out.println("Rang�e(s) affect�e(s)=" + MyFessaisDAO.getNbAffectedRow());

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
