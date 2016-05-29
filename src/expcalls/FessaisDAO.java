package expcalls;

import java.io.IOException;
import java.sql.*;
import liba2pi.ApplicationProperties;
import liba2pi.DBManager;
import liba2pi.DBServer;
import liba2pi.DBServerException;
import agency.PaternDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe qui d�crit les m�thodes pour acc�der aux tables fessais ou f99essais au
 * travers de JDBC.
 *
 * @author Thierry Baribaud.
 * @version Mai 2015.
 */
public class FessaisDAO extends PaternDAO {

    public enum EtatTicket {

        /**
         * Les tickets en cours sont stock�s dans la table fessais.
         */
        EN_COURS("fessais"),
        /**
         * Les tickets archiv�s sont stock�s dans la table f99essais.
         */
        ARCHIVE("f99essais");

        private String MyTable = "";

        //Constructeur
        EtatTicket(String MyTable) {
            this.MyTable = MyTable;
        }

        /**
         * @return le nom de la table.
         */
        @Override
        public String toString() {
            return MyTable;
        }
    }

    private String MyTable = EtatTicket.EN_COURS.name();

    /**
     * Requ�te SQL pour r�cup�rer la premi�re transmission.
     */
    private String FirstTransmissionStatement;

    /**
     * Requ�te SQL pr�par�e pour r�cup�rer la premi�re transmission.
     */
    private PreparedStatement FirstTransmissionPreparedStatement;

    /**
     * ResultSet pour r�cup�rer la premi�re transmission.
     */
    private ResultSet FirstTransmissionResultSet;

    
    /**
     * Constructeur de la classe FessaisDAO.
     *
     * @param MyConnection connexion � la base de donn�es courante.
     * @param enumabs identifiant de l'essai,
     * @param ecnum identifiant de l'appel,
     * @param MyEtatTicket indique si l'on travaille sur les tickets en cours ou
     * archiv�s.
     * @throws ClassNotFoundException en cas de classse non trouv�e.
     * @throws java.sql.SQLException en cas d'erreur SQL.
     */
    public FessaisDAO(Connection MyConnection, int enumabs, int ecnum, EtatTicket MyEtatTicket)
            throws ClassNotFoundException, SQLException {

        StringBuffer Stmt;

        MyTable = MyEtatTicket.toString();

        this.MyConnection = MyConnection;

        Stmt = new StringBuffer("select enumabs, ecnum, eptr, eunum, edate, etime,"
                + " emessage, etnum, eonum, eresult, eduration, etest, einternal,"
                + " em3num, egid"
                + " from " + MyTable
                + " where enumabs > 0");
        if (enumabs > 0) {
            Stmt.append(" and enumabs = ").append(enumabs);
        }
        if (ecnum > 0) {
            Stmt.append(" and ecnum = ").append(ecnum);
        }
        Stmt.append(" order by enumabs;");
//        System.out.println(Stmt);
        setReadStatement(Stmt.toString());
        setReadPreparedStatement();
        setReadResultSet();

        setUpdateStatement("update " + MyTable
                + " set ecnum=?, eptr=?, eunum=?, edate=?, etime=?,"
                + " emessage=?, etnum=?, eonum=?, eresult=?,"
                + " eduration=?, etest=?, einternal=?, em3num=?, egid=?"
                + " where enumabs=?;");
        setUpdatePreparedStatement();

        setInsertStatement("insert into " + MyTable
                + " (ecnum, eptr, eunum, edate, etime, cdate2, ctime2,"
                + " emessage, etnum, eonum, eresult, eduration,"
                + " etest, einternal, em3num, egid)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
        setInsertPreparedStatement();

        setDeleteStatement("delete from " + MyTable + " where enumabs=?;");
        setDeletePreparedStatement();
        
        if (ecnum > 0) {
            setFirstTransmissionStatement("select min(enumabs) enumabs from " + MyTable
                + " where ecnum = " + ecnum + ";");
            setFirstTransmissionPreparedStatement();
            setFirstTransmissionResultSet();
        }
    }

    /**
     * Selectionne un essai.
     *
     * @return l'essai s�lectionn�.
     */
    @Override
    public Fessais select() {
        Fessais MyFessais = null;

        try {
            if (ReadResultSet.next()) {
                MyFessais = new Fessais();
                MyFessais.setEnumabs(ReadResultSet.getInt("enumabs"));
                MyFessais.setEcnum(ReadResultSet.getInt("ecnum"));
                MyFessais.setEptr(ReadResultSet.getInt("eptr"));
                MyFessais.setEunum(ReadResultSet.getInt("eunum"));
                MyFessais.setEdate(ReadResultSet.getTimestamp("edate"));
                MyFessais.setEtime(ReadResultSet.getString("etime"));
                MyFessais.setEmessage(ReadResultSet.getString("emessage"));
                MyFessais.setEtnum(ReadResultSet.getInt("etnum"));
                MyFessais.setEonum(ReadResultSet.getInt("eonum"));
                MyFessais.setEresult(ReadResultSet.getInt("eresult"));
                MyFessais.setEduration(ReadResultSet.getInt("eduration"));
                MyFessais.setEtest(ReadResultSet.getInt("etest"));
                MyFessais.setEinternal(ReadResultSet.getInt("einternal"));
                MyFessais.setEm3num(ReadResultSet.getInt("em3num"));
                MyFessais.setEgid(ReadResultSet.getInt("egid"));
            } else {
                System.out.println("Lecture de " + MyTable + " termin�e");
            }
        } catch (SQLException MyException) {
            System.out.println("Erreur en lecture de " + MyTable + " "
                    + MyException.getMessage());
        }
        return MyFessais;
    }

    /**
     * Met � jour un essai.
     *
     * @param MyFessais essai � mettre � jour.
     */
    public void update(Fessais MyFessais) {
        try {
            UpdatePreparedStatement.setInt(1, MyFessais.getEcnum());
            UpdatePreparedStatement.setInt(2, MyFessais.getEptr());
            UpdatePreparedStatement.setInt(3, MyFessais.getEunum());
            UpdatePreparedStatement.setTimestamp(4, MyFessais.getEdate());
            UpdatePreparedStatement.setString(5, MyFessais.getEtime());
            UpdatePreparedStatement.setString(6, MyFessais.getEmessage());
            UpdatePreparedStatement.setInt(7, MyFessais.getEtnum());
            UpdatePreparedStatement.setInt(8, MyFessais.getEonum());
            UpdatePreparedStatement.setInt(9, MyFessais.getEresult());
            UpdatePreparedStatement.setInt(10, MyFessais.getEduration());
            UpdatePreparedStatement.setInt(11, MyFessais.getEtest());
            UpdatePreparedStatement.setInt(12, MyFessais.getEinternal());
            UpdatePreparedStatement.setInt(13, MyFessais.getEm3num());
            UpdatePreparedStatement.setInt(14, MyFessais.getEgid());
            UpdatePreparedStatement.setInt(15, MyFessais.getEnumabs());
            setNbAffectedRow(UpdatePreparedStatement.executeUpdate());
            if (getNbAffectedRow() == 0) {
                System.out.println("Impossible de mettre � jour " + MyTable);
            }
        } catch (SQLException MyException) {
            System.out.println("Erreur lors de la mise � jour de " + MyTable
                    + " " + MyException.getMessage());
        }
    }

    /**
     * Supprime d�finitivement un essai.
     *
     * @param enumabs identiant de l'essai � supprimer.
     */
    @Override
    public void delete(int enumabs) {
        try {
            DeletePreparedStatement.setInt(1, enumabs);
            setNbAffectedRow(DeletePreparedStatement.executeUpdate());
            if (getNbAffectedRow() == 0) {
                System.out.println("Impossible de d�truire un essai dans " + MyTable);
            }
        } catch (SQLException MyException) {
            System.out.println("Erreur lors de la suppression d'un essai dans "
                    + MyTable + " " + MyException.getMessage());
        }
    }

    /**
     * Ajoute un essai dans la table fessais ou f99essais.
     *
     * @param MyFessais essai � ajouter � la table fessais ou f99essais.
     */
    public void insert(Fessais MyFessais) {
        ResultSet MyKeyResultSet = null;

        try {
            System.out.println("enumabs=" + MyFessais.getEnumabs());
            InsertPreparedStatement.setInt(1, MyFessais.getEcnum());
            InsertPreparedStatement.setInt(2, MyFessais.getEptr());
            InsertPreparedStatement.setInt(3, MyFessais.getEunum());
            InsertPreparedStatement.setTimestamp(4, MyFessais.getEdate());
            InsertPreparedStatement.setString(5, MyFessais.getEtime());
            InsertPreparedStatement.setString(6, MyFessais.getEmessage());
            InsertPreparedStatement.setInt(7, MyFessais.getEtnum());
            InsertPreparedStatement.setInt(8, MyFessais.getEonum());
            InsertPreparedStatement.setInt(9, MyFessais.getEresult());
            InsertPreparedStatement.setInt(10, MyFessais.getEduration());
            InsertPreparedStatement.setInt(11, MyFessais.getEtest());
            InsertPreparedStatement.setInt(12, MyFessais.getEinternal());
            InsertPreparedStatement.setInt(13, MyFessais.getEm3num());
            InsertPreparedStatement.setInt(14, MyFessais.getEgid());
            setNbAffectedRow(InsertPreparedStatement.executeUpdate());
            if (getNbAffectedRow() == 0) {
                System.out.println("Impossible d'ajouter un essai dans " + MyTable);
            } else {
                MyKeyResultSet = InsertPreparedStatement.getGeneratedKeys();
                if (MyKeyResultSet.next()) {
                    MyFessais.setEnumabs((int) MyKeyResultSet.getInt(1));
                }
            }
            MyKeyResultSet.close();
        } catch (SQLException MyException) {
            System.out.println("Erreur lors de l'insertion d'un essai dans "
                    + MyTable + " " + MyException.getMessage());
        }
    }

    /**
     * @return the FirstTransmissionStatement
     */
    public String getFirstTransmissionStatement() {
        return FirstTransmissionStatement;
    }

    /**
     * @param FirstTransmissionStatement the FirstTransmissionStatement to set
     */
    public void setFirstTransmissionStatement(String FirstTransmissionStatement) {
        this.FirstTransmissionStatement = FirstTransmissionStatement;
    }

    /**
     * @return the FirstTransmissionPreparedStatement
     */
    public PreparedStatement getFirstTransmissionPreparedStatement() {
        return FirstTransmissionPreparedStatement;
    }

    /**
     * Pr�pare la requ�te SQL pour rechercher la premi�re transmission.
     * @throws java.sql.SQLException en cas d'erreur SQL.
     */
    public void setFirstTransmissionPreparedStatement() throws SQLException {
        FirstTransmissionPreparedStatement = MyConnection.prepareStatement(getFirstTransmissionStatement());
    }

    /**
     * @return the FirstTransmissionResultSet
     */
    public ResultSet getFirstTransmissionResultSet() {
        return FirstTransmissionResultSet;
    }

    /**
     * Ex�cute la requ�te SQL pour rechercher la premi�re transmission.
     * @throws java.sql.SQLException en cas d'erreur SQL.
     */
    public void setFirstTransmissionResultSet() throws SQLException {
        FirstTransmissionResultSet = FirstTransmissionPreparedStatement.executeQuery();
    }

    /**
     * R�cup�re l'essai correspondant � la premi�re transmission
     */
    public int getFirstTransmission() {
        int enumabs = 0;
        
        try {
            if (FirstTransmissionResultSet.next()) {
                enumabs = FirstTransmissionResultSet.getInt("enumabs");
            }
        } catch (SQLException MyException) {
            System.out.println("Erreur en lecture de " + MyTable + " "
                    + MyException.getMessage());
        }

        return (enumabs);
    }

    /**
     * M�thode qui ferme toutes les ressources de bases de donn�es.
     *
     * @throws java.sql.SQLException en cas d'erreur SQL.
     */
    @Override
    public void close() throws SQLException {

        super.close();
        FirstTransmissionPreparedStatement.close();
    }

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
            MyFessaisDAO = new FessaisDAO(MyDBManager.getConnection(), 0, 0, EtatTicket.EN_COURS);
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
            MyFessaisDAO = new FessaisDAO(MyDBManager.getConnection(), 0, MyFessais1.getEcnum(), EtatTicket.EN_COURS);
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

    @Override
    public void update(Object MyObject) {
        throw new UnsupportedOperationException("Non support� actuellement"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert(Object MyObject) {
        throw new UnsupportedOperationException("Non support� actuellement"); //To change body of generated methods, choose Tools | Templates.
    }
}