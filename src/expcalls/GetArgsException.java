package expcalls;

/**
 * Classe qui d�finit une exception lanc�e en cas d'erreur lors de
 * l'analyse des arguments de ligne de commande.
 *
 * @version Juin 2016
 * @author Thierry Baribaud
 */
public class GetArgsException extends Exception {

    /**
     * Creates a new instance of <code>GetArgsException</code> without detail
     * message.
     */
    public GetArgsException() {
    }

    /**
     * Constructs an instance of <code>GetArgsException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public GetArgsException(String msg) {
        super(msg);
    }
}
