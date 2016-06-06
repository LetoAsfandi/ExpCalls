package expcalls;

import bdd.Ftoubib;
import bdd.FtoubibDAO;
import java.io.IOException;
import java.sql.SQLException;
import utils.ApplicationProperties;
import utils.DBManager;
import utils.DBServer;
import utils.DBServerException;

/**
 * Programme pour tester la classe FtoubibDAO.
 *
 * @author Thierry Baribaud
 * @version Juin 2016
 */
public class tstFtoubibDAO {

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
        FtoubibDAO MyFtoubibDAO;
        Ftoubib MyFtoubib1;
        Ftoubib MyFtoubib;
        long i;
        int tnum = 0;

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
            MyFtoubibDAO = new FtoubibDAO(MyDBManager.getConnection(), tnum, MyArgs.getUnum());
            MyFtoubib1 = new Ftoubib();
            MyFtoubib1.setTunum(MyArgs.getUnum());
            MyFtoubib1.setTlname("polo");
            MyFtoubib1.setTfname("marco");
            MyFtoubib1.setTabbname("MARCOPOLO");
            MyFtoubib1.setTel("01.01.01.01.01");
            MyFtoubib1.setTel2("02.02.02.02.02");
            MyFtoubib1.setTelper("03.03.03.03.03");
            MyFtoubib1.setTel4("04.04.04.04.04");
            MyFtoubib1.setTel5("05.05.05.05.05");
            MyFtoubib1.setTel6("06.06.06.06.06");
            MyFtoubib1.setTelfax("01.01.01.01.11");
            MyFtoubib1.setTa6num(1);
            MyFtoubib1.setTemail("marco.polo@gmail.com");
            MyFtoubib1.setTaddress("je fais mon");
            MyFtoubib1.setTaddress2("tour du monde");
            MyFtoubib1.setTcomment("ne pas d�ranger");
            System.out.println("Ftoubib(avant insertion)=" + MyFtoubib1);
            MyFtoubibDAO.insert(MyFtoubib1);
            System.out.println("Ftoubib(apr�s insertion)=" + MyFtoubib1);
            System.out.println("Rang�e(s) affect�e(s)=" + MyFtoubibDAO.getNbAffectedRow());

// Essai mise � jour
            MyFtoubib1.setTemail(MyFtoubib1.getTemail() + ",utopia@free.fr");
            MyFtoubibDAO.update(MyFtoubib1);
            System.out.println("Ftoubib(apr�s mise-�-jour)=" + MyFtoubib1);
            System.out.println("Rang�e(s) affect�e(s)=" + MyFtoubibDAO.getNbAffectedRow());
            MyFtoubibDAO.close();

// Essai lecture
            MyFtoubibDAO = new FtoubibDAO(MyDBManager.getConnection(), tnum, MyArgs.getUnum());
            i = 0;
            while ((MyFtoubib = MyFtoubibDAO.select()) != null) {
                i++;
                System.out.println("Ftoubib(" + i + ")=" + MyFtoubib);
                System.out.println("  getTnum()=" + MyFtoubib.getTnum());
                System.out.println("  getTunum()=" + MyFtoubib.getTunum());
                System.out.println("  getTa6num()=" + MyFtoubib.getTa6num());
                System.out.println("  getTlname()=" + MyFtoubib.getTlname());
                System.out.println("  getTfname()=" + MyFtoubib.getTfname());
                System.out.println("  getTabbname()=" + MyFtoubib.getTabbname());
                System.out.println("  getTel()=" + MyFtoubib.getTel());
                System.out.println("  getTel2()=" + MyFtoubib.getTel2());
                System.out.println("  getTelper()=" + MyFtoubib.getTelper());
                System.out.println("  getTel4()=" + MyFtoubib.getTel4());
                System.out.println("  getTel5()=" + MyFtoubib.getTel5());
                System.out.println("  getTel6()=" + MyFtoubib.getTel6());
                System.out.println("  getTelfax()=" + MyFtoubib.getTelfax());
                System.out.println("  getTemail()=" + MyFtoubib.getTemail());
                System.out.println("  getTaddress()=" + MyFtoubib.getTaddress());
                System.out.println("  getTaddress2()=" + MyFtoubib.getTaddress2());
                System.out.println("  getTcomment()=" + MyFtoubib.getTcomment());
            }

// Essai suppression
            System.out.println("Suppression de : " + MyFtoubib1);
            MyFtoubibDAO.delete(MyFtoubib1.getTnum());
            System.out.println("Rang�e(s) affect�e(s)=" + MyFtoubibDAO.getNbAffectedRow());

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
