package expcalls;

import bdd.EtatTicket;
import bdd.Fcalls;
import bdd.Fcomplmt;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Classe repr�sentant un ticket pour les clients de la famille du client 609
 *
 * @version Juillet 2016
 * @author Thierry Baribaud
 */
public class Ticket_0609 extends Ticket_0000 {

    /**
     * Indication de site trouv� en base de donn�es.
     */
    private String SiteEnBase;

    /**
     * Indication de site sous contrat.
     */
    private String SiteSousContrat;

    /**
     * Contructeur principal de la classe Ticket.
     *
     * @param MyConnection connexion � la base de donn�es courante.
     * @param Fcalls_0000 appel,
     * @param Fcomplmt_0000 compl�ment d'appel.
     * @param MyEtatTicket etat du ticket.
     * @throws java.lang.ClassNotFoundException en cas de classe non trouv�e.
     * @throws java.sql.SQLException en cas d'erreur SQL.
     */
    public Ticket_0609(Connection MyConnection, Fcalls Fcalls_0000, Fcomplmt Fcomplmt_0000, EtatTicket MyEtatTicket) throws ClassNotFoundException, SQLException {

        super(MyConnection, Fcalls_0000, Fcomplmt_0000, MyEtatTicket);

        int delai;

        // Site trouv� en base de donn�es ou non.
        // System.out.println("  cage="+Fcalls_0000.getCage());
        setSiteEnBase(this.Fcalls_0000.getCage());

        // D�lai d'intervention exprim� en minutes
        // System.out.println("  cdelay1="+Fcalls_0000.getCdelay1());
        delai = 0;
        if ((delai = this.Fcalls_0000.getCdelay1()) > 0) {
            setDelaiIntervention(delai * 60);
        }

        // Site sous contrat ou non.
        // System.out.println("  cnumber8="+Fcalls_0000.getCnumber8());
        setSiteSousContrat(this.Fcalls_0000.getCnumber8(), 0);

    }

    /**
     * Contructeur secondaire de la classe Ticket.
     *
     * @param MyConnection connexion � la base de donn�es courante.
     * @param Fcalls_0000 appel,
     * @param MyEtatTicket etat du ticket.
     * @throws java.lang.ClassNotFoundException en cas de classe non trouv�e.
     * @throws java.sql.SQLException en cas d'erreur SQL.
     */
    public Ticket_0609(Connection MyConnection, Fcalls Fcalls_0000, EtatTicket MyEtatTicket) throws ClassNotFoundException, SQLException {
        this(MyConnection, Fcalls_0000, null, MyEtatTicket);
    }

    /**
     * @return SiteEnBase Indication de site trouv� en base de donn�es.
     */
    public String getSiteEnBase() {
        return SiteEnBase;
    }

    /**
     * @param SiteEnBase d�finit l'indication de site trouv� en base de donn�es.
     */
    public void setSiteEnBase(String SiteEnBase) {
        this.SiteEnBase = SiteEnBase;
    }

    /**
     * @param codeSiteEnBase d�finit si le site a �t� trouv� ou non en base de
     * donn�es.
     */
    public void setSiteEnBase(int codeSiteEnBase) {
        if (codeSiteEnBase == 2) {
            setSiteEnBase("NON");
        } else {
            setSiteEnBase("OUI");
        }
    }

    /**
     * @return SiteSousContrat Indication sur site sous contrat ou non.
     */
    public String getSiteSousContrat() {
        return SiteSousContrat;
    }

    /**
     * @param SiteSousContrat d�finit si le site est sous contrat.
     */
    public void setSiteSousContrat(String SiteSousContrat) {
        this.SiteSousContrat = SiteSousContrat;
    }

    /**
     * @param SiteSousContrat d�finit si le site est sous contrat
     * @param inutile pour avoir une signature diff�rente.
     */
    public void setSiteSousContrat(String SiteSousContrat, int inutile) {
        if (SiteSousContrat == null) {
            setSiteSousContrat("OUI");
        } else {
            if (SiteSousContrat.equals("NON")) {
                setSiteSousContrat("NON");
            } else {
                setSiteSousContrat("OUI");
            }
        }
    }

}
