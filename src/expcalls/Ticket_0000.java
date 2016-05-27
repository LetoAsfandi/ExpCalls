/**
 * Classe repr�sentant un ticket. Cela correspond � l'association d'un appel
 * Fcalls et d'un compl�ment d'appel Fcomplmt. Il s'agit du ticket basique. Les
 * tickets sp�cifiques � un client d�riveront de celui-ci.
 *
 * @version Mai 2016.
 * @author Thierry Baribaud.
 */
package expcalls;

public class Ticket_0000 {

    /**
     * Partie compos�e de l'appel.
     */
    private Fcalls Fcalls_0000;

    /**
     * Partie compos�e du compl�ment d'appel.
     */
    private Fcomplmt Fcomplmt_0000;

    /**
     * Contructeur de la classe Ticket complet.
     *
     * @param Fcalls_0000 appel,
     * @param Fcomplmt_0000 compl�ment d'appel.
     */
    public Ticket_0000(Fcalls Fcalls_0000, Fcomplmt Fcomplmt_0000) {
        this.Fcalls_0000 = Fcalls_0000;
        this.Fcomplmt_0000 = Fcomplmt_0000;
    }

    /**
     * Contructeur secondaire de la classe Ticket sans le compl�ment d'appel.
     *
     * @param Fcalls_0000 appel,
     */
    public Ticket_0000(Fcalls Fcalls_0000) {
        this.Fcalls_0000 = Fcalls_0000;
        this.Fcomplmt_0000 = null;
    }

    /**
     * Retourne l'appel.
     *
     * @return L'appel Fcalls_0000.
     */
    public Fcalls getFcalls_0000() {
        return Fcalls_0000;
    }

    /**
     * Retourne le compl�ment d'appel.
     *
     * @return Le compl�ment d'appel Fcomplmt_0000.
     */
    public Fcomplmt getFcomplmt_0000() {
        return Fcomplmt_0000;
    }

    /**
     * D�finit l'appel.
     *
     * @param Fcalls_0000 D�finit l'appel.
     */
    public void setFcalls_0000(Fcalls Fcalls_0000) {
        this.Fcalls_0000 = Fcalls_0000;
    }

    /**
     * D�finit le compl�ment d'appel.
     *
     * @param Fcomplmt_0000 D�finit le compl�ment d'appel.
     */
    public void setFcomplmt_0000(Fcomplmt Fcomplmt_0000) {
        this.Fcomplmt_0000 = Fcomplmt_0000;
    }

    /**
     * Retourne le contenu du ticket.
     *
     * @return le contenu du ticket.
     */
    @Override
    public String toString() {
        return (Fcalls_0000 + " " + Fcomplmt_0000);
    }
}
