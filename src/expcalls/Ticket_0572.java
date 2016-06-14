package expcalls;

import bdd.EtatTicket;
import bdd.Fcalls;
import bdd.Fcomplmt;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Classe repr�sentant un ticket pour les clients de la famille du client 572
 *
 * @version Juin 2016
 * @author Thierry Baribaud
 */
public class Ticket_0572 extends Ticket_0000 {

    /**
     * Degr� d'urgence de la demande d'intervention.
     */
    private String DegreDUrgence;
    
    /**
     * Indication de d�g�ts des eaux OUI/NON..
     */
    private String DegatsDesEaux;

    /**
     * Indication de site trouv� en base de donn�es.
     */
    private String SiteEnBase;
    
    /**
     * Type de demande.
     */
    private String TypeDeDemande;
    
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
    public Ticket_0572(Connection MyConnection, Fcalls Fcalls_0000, Fcomplmt Fcomplmt_0000, EtatTicket MyEtatTicket) throws ClassNotFoundException, SQLException {

        super(MyConnection, Fcalls_0000, Fcomplmt_0000, MyEtatTicket);

        // Degr� d'urgence cf. tra_nat_urg_0572() dans libspc0572.4gl.
//        System.out.println("  cdelay1="+Fcalls_0000.getCdelay1());
        switch (this.Fcalls_0000.getCdelay1()) {
            case 1:
                setDegreDUrgence("Immediate");
                break;
            case 2:
                setDegreDUrgence("Courante");
                break;
            default:
                setDegreDUrgence(null);
                break;
        }

        // Indication de d�g�ts des eaux cf. tra_degat_eau_0572() dans libspc0572.4gl.
        if ("1".equals(this.Fcalls_0000.getCsector1())) {
            setDegatsDesEaux("OUI");
        }
        else {
            setDegatsDesEaux("NON");
        }

        // Indication de site trouv� en base de donn�es cf. librep0572.4gl en dur.
        if ("SIEGE".equals(getA6abbname())) {
            setSiteEnBase("NON");
        }
        else {
            setSiteEnBase("OUI");
        }

        // Indication du type de demande cf. tra_typinter_0572() dans libspc0572.4gl.
        switch (this.Fcomplmt_0000.getC6int2()) {
            case 1: 
                setTypeDeDemande("DIC");
                break;
            case 2: 
                setTypeDeDemande("DI");
                break;
            default:
                setTypeDeDemande("AUTRE");
                break;
        }

        // tra_type_interv_0572
//        switch (this.Fcomplmt_0000.getC6int2()) {
//            case 0: 
//                setTypeDeDemande("Message");
//                break;
//            case 1: 
//                setTypeDeDemande("Imm�diate");
//                break;
//            case 2: 
//                setTypeDeDemande("Courante");
//                break;
//            case 3: 
//                setTypeDeDemande("Demande de renseignement");
//                break;
//            case 4: 
//                setTypeDeDemande("Site non couvert en HO");
//                break;
//            default:
//                setTypeDeDemande("Inconnu");
//                break;
//        }
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
    public Ticket_0572(Connection MyConnection, Fcalls Fcalls_0000, EtatTicket MyEtatTicket) throws ClassNotFoundException, SQLException {
        this(MyConnection, Fcalls_0000, null, MyEtatTicket);
    }

    /**
     * @return DegreDUrgence le degr� d'urgence de la demande d'intervention.
     */
    public String getDegreDUrgence() {
        return DegreDUrgence;
    }

    /**
     * @param DegreDUrgence d�finit le degr� d'urgence de la demande
     * d'intervention.
     */
    public void setDegreDUrgence(String DegreDUrgence) {
        this.DegreDUrgence = DegreDUrgence;
    }

    /**
     * @return DegatsDesEaux indication de d�g�ts des eaux OUI/NON.
     */
    public String getDegatsDesEaux() {
        return DegatsDesEaux;
    }

    /**
     * @param DegatsDesEaux d�finit l'indication de d�g�ts des eaux OUI/NON.
     */
    public void setDegatsDesEaux(String DegatsDesEaux) {
        this.DegatsDesEaux = DegatsDesEaux;
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
     * @return TypeDeDemande le type de demande.
     */
    public String getTypeDeDemande() {
        return TypeDeDemande;
    }

    /**
     * @param TypeDeDemande d�finit le type de demande.
     */
    public void setTypeDeDemande(String TypeDeDemande) {
        this.TypeDeDemande = TypeDeDemande;
    }

}
