package expcalls;

import bdd.Furgent;
import bdd.FurgentDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;

/**
 * Classe servant � stocker les param�tres pour exporter les appels.
 *
 * @version Juillet 2016
 * @author Thierry Baribaud
 */
public class ExpCallsParams {

    /**
     * Connexion � la base de donn�es courante.
     */
    private Connection MyConnection;

    /**
     * Identifiant du client.
     */
    private int unum;

    /**
     * Nom du client.
     */
    private String Uname;

    /**
     * Nom abr�g� du client.
     */
    private String Uabbname;

    /**
     * Date de d�but de l'export � 0h.
     */
    private Timestamp BegDate;

    /**
     * Date de fin de l'export � 0h.
     */
    private Timestamp EndDate;

    /**
     * Nom du fichier de sortie au format XML.
     */
    private String XMLFilename = DetermineXMLFilename(0);

    /**
     * Nom du fichier contenant le sch�ma XML.
     */
    private String XSDFilename = DetermineXSDFilename(0);

    /**
     * Nom du fichier de sortie au format Excel.
     */
    private String ExcelFilename = DetermineExcelFilename(0);

    public ExpCallsParams(Connection MyConnection, GetArgs MyArgs) throws ClassNotFoundException, SQLException {
        FurgentDAO MyFurgentDAO;
        Furgent MyFurgent;

        setMyConnection(MyConnection);

        setUnum(MyArgs.getUnum());
        MyFurgentDAO = new FurgentDAO(MyConnection);
        MyFurgentDAO.filterById(unum);
        MyFurgentDAO.setSelectPreparedStatement();
        MyFurgent = MyFurgentDAO.select();
        if (MyFurgent != null) {
            setUname(MyFurgent.getUname());
            setUabbname(MyFurgent.getUabbname());
        } else {
            setUname("Inconnu");
            setUabbname("INCONNU");
        }
        MyFurgentDAO.closeSelectPreparedStatement();

        setBegDate(MyArgs.getBegDate());
        setEndDate(MyArgs.getEndDate());
        setXMLFilename(DetermineXMLFilename(unum));
        setXSDFilename(DetermineXSDFilename(unum));
        setExcelFilename(DetermineExcelFilename(unum));
    }

    /**
     * @return XMLFilename le nom du fichier de sortie au format XML.
     */
    public String getXMLFilename() {
        return XMLFilename;
    }

    /**
     * @param unum d�finit la r�f�rence client.
     */
    public void setUnum(int unum) {
        this.unum = unum;
    }

    /**
     * @param BegDate d�finit la date de d�but de l'export � 0h.
     */
    public void setBegDate(Timestamp BegDate) {
        this.BegDate = BegDate;
    }

    /**
     * @param EndDate d�finit la date de fin de l'export � 0h.
     */
    public void setEndDate(Timestamp EndDate) {
        this.EndDate = EndDate;
    }

    /**
     * @return Unum l'identifiant du client.
     */
    public int getUnum() {
        return (unum);
    }

    /**
     * @return BegDate la date de d�but de l'export � 0h.
     */
    public Timestamp getBegDate() {
        return (BegDate);
    }

    /**
     * @return EndDate la date de fin de l'export � 0h.
     */
    public Timestamp getEndDate() {
        return (EndDate);
    }

    /**
     * M�thode qui d�termine la racine du nom d'un fichier par rapport �
     * l'identifiant du client.
     *
     * @param unum identifiant du client.
     * @return Filename la racine du nom du fichier.
     */
    private String DefaultFilename(int unum) {
        DecimalFormat MyFormatter = new DecimalFormat("0000");

        return ("tickets_" + MyFormatter.format(unum));
    }

    /**
     * M�thode qui d�termine le nom du fichier de sortie au format XML.
     *
     * @param unum identifiant du client.
     * @return Filename la racine du nom du fichier.
     */
    private String DetermineXMLFilename(int unum) {
        return (DefaultFilename(unum) + ".xml");
    }

    /**
     * M�thode qui d�termine le nom du fichier contenant le sch�ma XML.
     *
     * @param unum identifiant du client.
     * @return Filename la racine du nom du fichier.
     */
    private String DetermineXSDFilename(int unum) {
        String Filename = "tickets_0000";

        switch (unum) {
            case 105:
                Filename = "tickets_0105";
                break;
            case 125:
                Filename = "tickets_0105";
                break;
            case 341:
                Filename = "tickets_0341";
                break;
            case 513:
                Filename = "tickets_0513";
                break;
            case 515:
                Filename = "tickets_0341";
                break;
            case 552:
                Filename = "tickets_0552";
                break;
            case 555:
                Filename = "tickets_0555";
                break;
            case 557:
                Filename = "tickets_0541";
                break;
            case 567:
                Filename = "tickets_0567";
                break;
            case 572:
                Filename = "tickets_0572";
                break;
            case 573:
                Filename = "tickets_0573";
                break;
            case 582:
                Filename = "tickets_0582";
                break;
            case 592:
                Filename = "tickets_0105";
                break;
            case 600:
                Filename = "tickets_0600";
                break;
            case 602:
                Filename = "tickets_0541";
                break;
            case 603:
                Filename = "tickets_0609";
                break;
            case 604:
                Filename = "tickets_0609";
                break;
            case 605:
                Filename = "tickets_0609";
                break;
            case 606:
                Filename = "tickets_0609";
                break;
            case 607:
                Filename = "tickets_0609";
                break;
            case 608:
                Filename = "tickets_0609";
                break;
            case 609:
                Filename = "tickets_0609";
                break;
            case 610:
                Filename = "tickets_0609";
                break;
            case 611:
                Filename = "tickets_0609";
                break;
            case 612:
                Filename = "tickets_0609";
                break;
            case 613:
                Filename = "tickets_0609";
                break;
            case 614:
                Filename = "tickets_0609";
                break;
            case 615:
                Filename = "tickets_0609";
                break;
            case 616:
                Filename = "tickets_0609";
                break;
            case 617:
                Filename = "tickets_0609";
                break;
            case 620:
                Filename = "tickets_0609";
                break;
            case 626:
                Filename = "tickets_0609";
                break;
            case 627:
                Filename = "tickets_0609";
                break;
            case 629:
                Filename = "tickets_0609";
                break;
            case 630:
                Filename = "tickets_0609";
                break;
            case 632:
                Filename = "tickets_0609";
                break;
            case 634:
                Filename = "tickets_0572";
                break;
            case 635:
                Filename = "tickets_0635";
                break;
        }
        return (Filename + ".xsd");
    }

    /**
     * M�thode qui d�termine le nom du fichier de sortie au format Excel.
     *
     * @param unum identifiant du client.
     * @return Filename la racine du nom du fichier.
     */
    private String DetermineExcelFilename(int unum) {
        return (DefaultFilename(unum) + ".xlsx");
    }

    /**
     * @param XMLFilename d�finit le nom du fichier de sortie au format XML.
     */
    public void setXMLFilename(String XMLFilename) {
        this.XMLFilename = XMLFilename;
    }

    /**
     * @return XSDFilename le nom du fichier contenant le sch�ma XML.
     */
    public String getXSDFilename() {
        return XSDFilename;
    }

    /**
     * @param XSDFilename d�finit le nom du fichier contenant le sch�ma XML.
     */
    public void setXSDFilename(String XSDFilename) {
        this.XSDFilename = XSDFilename;
    }

    /**
     * @return ExcelFilename le nom du fichier de sortie au format Excel.
     */
    public String getExcelFilename() {
        return ExcelFilename;
    }

    /**
     * @param ExcelFilename d�finit le nom du fichier de sortie au format Excel.
     */
    public void setExcelFilename(String ExcelFilename) {
        this.ExcelFilename = ExcelFilename;
    }

    /**
     * @return MyConnection connection � la base de donn�es locale.
     */
    public Connection getMyConnection() {
        return MyConnection;
    }

    /**
     * @param MyConnection d�finit la connection � la base de donn�es locale.
     */
    public void setMyConnection(Connection MyConnection) {
        this.MyConnection = MyConnection;
    }

    /**
     * @return Uname le nom du client.
     */
    public String getUname() {
        return Uname;
    }

    /**
     * @param Uname d�finit le nom du client.
     */
    public void setUname(String Uname) {
        this.Uname = Uname;
    }

    /**
     * @return Uabbname le nom abr�g� du client.
     */
    public String getUabbname() {
        return Uabbname;
    }

    /**
     * @param Uabbname d�finit le nom abr�g� du client.
     */
    public void setUabbname(String Uabbname) {
        this.Uabbname = Uabbname;
    }

}
