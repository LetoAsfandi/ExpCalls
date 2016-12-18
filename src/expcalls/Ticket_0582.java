package expcalls;

import bdd.EtatTicket;
import bdd.Fcalls;
import bdd.Fcomplmt;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Classe repr�sentant un ticket pour les clients de la famille du client 582
 *
 * @author Thierry Baribaud
 * @version 0.26
 */
public class Ticket_0582 extends Ticket_0000 {

    /**
     * Indication de site trouv� en base de donn�es.
     */
    private String SiteEnBase;

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
    public Ticket_0582(Connection MyConnection, Fcalls Fcalls_0000, Fcomplmt Fcomplmt_0000, EtatTicket MyEtatTicket) throws ClassNotFoundException, SQLException {

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
    public Ticket_0582(Connection MyConnection, Fcalls Fcalls_0000, EtatTicket MyEtatTicket) throws ClassNotFoundException, SQLException {
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
}
