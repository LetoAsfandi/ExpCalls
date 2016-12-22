package expcalls;

import bdd.Fcalls;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Fcalls_0000 is a class that describes an active calls. It extends Fcalls
 * class and implements methods to generate CSV and XML records.
 *
 * @author Thierry Baribaud.
 * @version 0.27
 */
public class Fcalls_0000 extends Fcalls {

    private static final DateFormat MyDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Constructeur principal de la classe
     * @param MyCalls appel veant de la base de donn�es.
     */
    public Fcalls_0000(Fcalls MyCalls) {
        setCnum(MyCalls.getCnum());
        setCname(MyCalls.getCname());
        setCtel(MyCalls.getCtel());
        setCaddress(MyCalls.getCaddress());
        setCaddress2(MyCalls.getCaddress2());
        setCaccess(MyCalls.getCaccess());
        setCposcode(MyCalls.getCposcode());
        setCity(MyCalls.getCity());
        setCsympt(MyCalls.getCsympt());
        setCnumber4(MyCalls.getCnumber4());
        setCdate(MyCalls.getCdate());
        setCtime(MyCalls.getCtime());
        setCdate2(MyCalls.getCdate2());
        setCtime2(MyCalls.getCtime2());
    }

    /**
     *
     * @return la ligne de titre du fichier CSV
     */
    public final static String CSV_Title() {
        return ("ID;Nom;T�l�phone;Adresse;Compl�ment;Acc�s;CP;Ville"
                + "Raison d'appel;Digicode;Saisi le;�;Modifi� le;�");
    }

    /**
     *
     * @return une ligne de donn�es dans le fichier CSV
     */
    public String toCSV() {
        return (getCnum() + ";" + getCname() + ";" + getCtel() + ";"
                + getCaddress() + ";" + getCaddress2() + ";" + getCaccess() + ";"
                + getCposcode() + ";" + getCity() + ";" + getCsympt() + ";"
                + getCnumber4() + ";"
                + MyDateFormat.format(getCdate()) + ";" + getCtime() + ";"
                + MyDateFormat.format(getCdate2()) + ";" + getCtime2() + ";");
    }

    /**
     * Convertion au format XML
     */
    public void toXML() {
    }
}
