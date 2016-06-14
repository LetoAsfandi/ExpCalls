package expcalls;

import java.sql.Connection;
import java.sql.Timestamp;

/**
 * Classe servant � stocker les param�tres pour exporter les appels.
 *
 * @version Juin 2016
 * @author Thierry Baribaud
 */
public class ExpCallsParams {

    /**
     * MyConnection : Connection � la base de donn�es locale.
     */
    private Connection MyConnection;

    /**
     * Unum : R�f�rence du client.
     */
    private int unum;

    /**
     * BegDate : date de d�but de l'export � 0h.
     */
    private Timestamp BegDate;

    /**
     * EndDate : date de fin de l'export � 0h.
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

    public ExpCallsParams(Connection MyConnection, GetArgs MyArgs) {
        setMyConnection(MyConnection);
        setUnum(MyArgs.getUnum());
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
     * @param unum : d�finit la r�f�rence client.
     */
    public void setUnum(int unum) {
        this.unum = unum;
    }

    /**
     * @param BegDate : date de d�but de l'export � 0h.
     */
    public void setBegDate(Timestamp BegDate) {
        this.BegDate = BegDate;
    }

    /**
     * @param EndDate : date de fin de l'export � 0h.
     */
    public void setEndDate(Timestamp EndDate) {
        this.EndDate = EndDate;
    }

    /**
     * @return Unum : la r�f�rence du client.
     */
    public int getUnum() {
        return (unum);
    }

    /**
     * @return BegDate : date de d�but de l'export � 0h.
     */
    public Timestamp getBegDate() {
        return (BegDate);
    }

    /**
     * @return EndDate : date de fin de l'export � 0h.
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
        String Filename = "tickets_0000";

        switch (unum) {
            case 572:
                Filename = "tickets_0572";
                break;
        }
        return (Filename);
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
        return (DefaultFilename(unum) + ".xsd");
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

}
