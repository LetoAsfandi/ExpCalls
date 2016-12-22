package expcalls;

import bdd.EtatTicket;

/**
 * Interface pour les objets de type ExpCalls_xxxx.
 * 
 * @author Thierry Baribaud
 * @version 0.27
 */
public interface ExpCallsInterface {

    /**
     *
     * @param MyExpcallsParams param�tres d'ex�cution
     * @param MyXMLDocument document XML
     * @param MyEtatTicket �tat du ticket
     */
    abstract void processTickets(ExpCallsParams MyExpcallsParams,
            Calls_0000_XMLDocument MyXMLDocument, EtatTicket MyEtatTicket);
}
