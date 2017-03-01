package expcalls;

import bdd.ClotureAppel;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import utils.XMLDocument;
import org.w3c.dom.Comment;
import org.w3c.dom.Element;

/**
 * Classe pour g�n�rer un fichier au format XML d�crivant des tickets des
 * clients basiques.
 *
 * @author Thierry Baribaud
 * @version 0.29
 */
public class Calls_0000_XMLDocument extends XMLDocument {

    /**
     * Initialise le document XML - constructeur principal.
     *
     * @param RootName nom de la racine du document XML.
     * @param XsdFile nom du fichier contenant le sch�ma XML.
     * @param MyComment commentaire sur le contenu du fichier.
     */
    public Calls_0000_XMLDocument(String RootName, String XsdFile, String MyComment) {
        super(RootName, XsdFile, MyComment);
    }

    /**
     * Initialise le document XML - constructeur secondaire.
     *
     * @param RootName nom de la racine du document XML.
     * @param XsdFile nom du fichier contenant le sch�ma XML.
     */
    public Calls_0000_XMLDocument(String RootName, String XsdFile) {
        this(RootName, XsdFile, null);
    }

    /**
     * Ajoute un ticket au document XML. Le ticket est d�crit de mani�re non
     * structur�e - flat schema -
     *
     * @param ticket_0000 ticket � transcrire en XML.
     */
    public void AddToXMLDocument(Ticket_0000 ticket_0000) {

        Comment comment;
        Element element;
        String aString;
        int anInt;
        Timestamp aTimestamp;
        ClotureAppel clotureAppel;

        Element Ticket;

        DateFormat MyDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        aString = "Ticket ref=" + ticket_0000.Fcalls_0000.getCnum();
        if (aString != null) {
            comment = MyDocument.createComment(aString);
            MyElements.appendChild(comment);
        }

        Ticket = MyDocument.createElement("ticket");
        MyElements.appendChild(Ticket);

        // Date de saisie
        element = MyDocument.createElement("DateDeSaisie");
        Ticket.appendChild(element);
        aTimestamp = ticket_0000.Fcalls_0000.getCdate();
        if (aTimestamp != null) {
            element.appendChild(MyDocument.createTextNode(MyDateFormat.format(aTimestamp)));
        }

        // Heure de saisie
        element = MyDocument.createElement("HeureDeSaisie");
        Ticket.appendChild(element);
        aString = ticket_0000.Fcalls_0000.getCtime();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Num�ro de ticket
        element = MyDocument.createElement("NumeroDeDossier");
        Ticket.appendChild(element);
        anInt = ticket_0000.Fcalls_0000.getCseqno();
        if (anInt > 0) {
            element.appendChild(MyDocument.createTextNode(String.valueOf(anInt)));
//                    + "/" + String.valueOf(ticket_0000.Fcalls_0000.getCnum())));
        }

        // Agence
        element = MyDocument.createElement("ProgrammeAgence");
        Ticket.appendChild(element);
        if ((aString = ticket_0000.getA6extname()) == null) aString = ticket_0000.getA6name();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Adresse compl�te
        element = MyDocument.createElement("Adresse");
        Ticket.appendChild(element);
        aString = ticket_0000.Fcalls_0000.getCaddress();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }
        element = MyDocument.createElement("Complement");
        Ticket.appendChild(element);
        aString = ticket_0000.Fcalls_0000.getCaddress2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }
        element = MyDocument.createElement("CP");
        Ticket.appendChild(element);
        aString = ticket_0000.Fcalls_0000.getCposcode();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }
        element = MyDocument.createElement("Ville");
        Ticket.appendChild(element);
        aString = ticket_0000.Fcalls_0000.getCity();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // B�timent
        element = MyDocument.createElement("Batiment");
        Ticket.appendChild(element);
        aString = ticket_0000.Fcalls_0000.getCnumber4();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }
        // Escalier
        element = MyDocument.createElement("Escalier");
        Ticket.appendChild(element);
        aString = ticket_0000.Fcalls_0000.getCnumber5();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Nom de l'appelant
        element = MyDocument.createElement("Nom");
        Ticket.appendChild(element);
        aString = ticket_0000.Fcalls_0000.getCname();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // T�l�phone de l'appelant
        element = MyDocument.createElement("TelAppelant");
        Ticket.appendChild(element);
        aString = ticket_0000.Fcalls_0000.getCtel();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Raison d'appel
        element = MyDocument.createElement("RaisonAppel");
        Ticket.appendChild(element);
        if ((aString = ticket_0000.getM6extname()) == null) aString = ticket_0000.getM6name();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Demande d'intervention
        element = MyDocument.createElement("DemandeIntervention");
        Ticket.appendChild(element);
        aString = ticket_0000.Fcalls_0000.getCsympt();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Etat de l'intervention
        element = MyDocument.createElement("EtatIntervention");
        Ticket.appendChild(element);
        aString = ticket_0000.getEtatIntervention();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Prestataire1
        element = MyDocument.createElement("Prestataire1");
        Ticket.appendChild(element);
        aString = ticket_0000.getPrestataire1();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // DateMissionnement1
        element = MyDocument.createElement("DateMissionnement1");
        Ticket.appendChild(element);
        aString = ticket_0000.getDateMissionnement1();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // HeureMissionnement1
        element = MyDocument.createElement("HeureMissionnement1");
        Ticket.appendChild(element);
        aString = ticket_0000.getHeureMissionnement1();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // NoTelephone1
        element = MyDocument.createElement("NoTelephone1");
        Ticket.appendChild(element);
        aString = ticket_0000.getNoTelephone1();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Prestataire2
        element = MyDocument.createElement("Prestataire2");
        Ticket.appendChild(element);
        aString = ticket_0000.getPrestataire2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // DateMissionnement2
        element = MyDocument.createElement("DateMissionnement2");
        Ticket.appendChild(element);
        aString = ticket_0000.getDateMissionnement2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // HeureMissionnement2
        element = MyDocument.createElement("HeureMissionnement2");
        Ticket.appendChild(element);
        aString = ticket_0000.getHeureMissionnement2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // NoTelephone2
        element = MyDocument.createElement("NoTelephone2");
        Ticket.appendChild(element);
        aString = ticket_0000.getNoTelephone2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Cloture de l'appel
        clotureAppel = ticket_0000.getClotureAppel();
        anInt = ticket_0000.Fcalls_0000.getCnote();
        aString = (anInt == 1) ? "Appel cl�tur�" : "Appel non cl�tur�";
        element = MyDocument.createElement("ResultatIntervention");
        Ticket.appendChild(element);
        element.appendChild(MyDocument.createTextNode(aString));

        // Rapport d'intervention
        element = MyDocument.createElement("RapportIntervention");
        Ticket.appendChild(element);
        aString = clotureAppel.getRapport();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Le technicien est-il encore sur site ?
        element = MyDocument.createElement("TechnicienSurSite");
        Ticket.appendChild(element);
        aString = clotureAppel.getOnSite();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }
    }
}
