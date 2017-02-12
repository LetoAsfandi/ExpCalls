package expcalls;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Cette classe sert �v�rifier et � r�cup�rer les arguments pass�s en ligne de
 * commande �un programme.
 *
 * @author Thierry Baribaud.
 * @version 0.28
 */
public class GetArgs {

    private static final DateFormat MyDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * SourceServer : prod pour le serveur de production, dev pour le serveur de
     * d�veloppement. Valeur par d�faut : dev.
     */
    private String SourceServer = "dev";

    /**
     * Unum : R�f�rence du client. Valeur par d�faut : doit �tre sp�cifi� en
     * ligne de commande.
     */
    private int unum = 0;

    /**
     * filename : fichier qui recevra les r�sultats du chargement. Valeur par
     * d�faut : tickets_0000.xml.
     */
    private String filename = "tickets_0000.xml";

    /**
     * Directory : r�pertoire vers lequel exporter le fichier des r�sultats. Par
     * d�faut c'est le r�pertoire du programme.
     */
    private String directory = ".";

    /**
     * BegDate : date de d�but de l'export � 0h.
     */
    private Timestamp BegDate = new Timestamp((new java.util.Date().getTime()) - 1000 * 60 * 60 * 24);

    /**
     * EndDate : date de fin de l'export � 0h.
     */
    private Timestamp EndDate = new Timestamp(new java.util.Date().getTime());

    /**
     * nbJour : nombre de jours � compter de la date courante
     */
    private int nbJour;
    
    /**
     * suffix : suffixe optionnel � rajouter au nom du fichier
     */
    private String suffix = null;
    
    /**
     * debugMode : fonctionnement du programme en mode debug (true/false).
     * Valeur par d�faut : false.
     */
    private boolean debugMode = false;

    /**
     * testMode : fonctionnement du programme en mode test (true/false). Valeur
     * par d�faut : false.
     */
    private boolean testMode = false;

    /**
     * @return SourceServer : la valeur pour le serveur source.
     */
    public String getSourceServer() {
        return (SourceServer);
    }

    /**
     * @return filename : le nom du fichier o� envoyer les r�sultats.
     */
    public String getFilename() {
        return (filename);
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
     * @return debugMode : le mode de fonctionnement debug.
     */
    public boolean getDebugMode() {
        return (debugMode);
    }

    /**
     * @return testMode : le mode de fonctionnement test.
     */
    public boolean getTestMode() {
        return (testMode);
    }

    /**
     * @param SourceServer : d�finit le serveur source.
     */
    public void setSourceServer(String SourceServer) {
        this.SourceServer = SourceServer;
    }

    /**
     * @param filename : d�finit le fichier o� envoyer les r�sultats.
     */
    public void setFilename(String filename) {
        this.filename = filename;
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
     * @param debugMode : fonctionnement du programme en mode debug
     * (true/false).
     */
    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    /**
     * @param testMode : fonctionnement du programme en mode test (true/false).
     */
    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }

    /**
     * <p>
     * Les arguments en ligne de commande permettent de changer le mode de
     * fonctionnement.</p><ul>
     * <li>-dbserver : r�f�rence � la base de donn�e, par d�faut fait r�f�rence
     * � la base de d�veloppement, cf. fichier de paramètres
     * <i>myDatabases.prop</i> (optionnel)</li>
     * <li>-u unum : identifiant du service d'urgence (obligatoire).</li>
     * <li>-b d�but : date de d�but de l'extraction � 0h, hier par d�faut,
     * format DD/MM/AAAA (optionnel).</li>
     * <li>-b fin : date de fin de l'extraction � 0h, aujourd'hui par d�faut,
     * format DD/MM/AAAA (optionnel).</li>
     * <li>-o fichier : fichier vers lequel exporter les donn�es des appels, nom
     * par d�faut <i>calls_0000.xml</i>(optionnel).</li>
     * <li>-n nbJour : nombre de jour(s) � compter de la date courante.</li>
     * <li>-s suffixe : suffixe optionnel � ajouter au nom du fichier.</li>
     * <li>-d : le programme fonctionne en mode d�bug le rendant plus verbeux,
     * d�sactiv� par d�faut (optionnel).</li>
     * <li>-t : le programme fonctionne en mode de test, les transactions en
     * base de donn�es ne sont pas ex�cut�es, d�sactiv� par d�faut
     * (optionnel).</li>
     * </ul>
     *
     * @param Args arguments de la ligne de commande.
     * @throws expcalls.GetArgsException erreur sur les param�tres.
     */
    public GetArgs(String Args[]) throws GetArgsException {

        int i;
        int n;
        int ip1;
        Date MyDate;

        // Demande une analyse d'une date valide
        MyDateFormat.setLenient(false);
        n = Args.length;

        System.out.println("nargs=" + n);
//    for(i=0; i<n; i++) System.out.println("args["+i+"]="+Args[i]);
        i = 0;
        while (i < n) {
//            System.out.println("args[" + i + "]=" + Args[i]);
            ip1 = i + 1;
            if (Args[i].equals("-dbserver")) {
                if (ip1 < n) {
                    if (Args[ip1].equals("dev") || Args[ip1].equals("prod") || Args[ip1].equals("mysql")) {
                        setSourceServer(Args[ip1]);
                    } else {
                        throw new GetArgsException("Mauvaise source de donn�es : " + Args[ip1]);
                    }
                    i = ip1;
                } else {
                    throw new GetArgsException("Base de donn�es non d�finie");
                }
            } else if (Args[i].equals("-u")) {
                if (ip1 < n) {
                    try {
                        setUnum(Integer.parseInt(Args[ip1]));
                        i = ip1;
                    } catch (Exception MyException) {
                        throw new GetArgsException("La r�f�rence client doit �tre num�rique : " + Args[ip1]);
                    }
                } else {
                    throw new GetArgsException("R�f�rence client non d�finie");
                }
            } else if (Args[i].equals("-b")) {
                if (ip1 < n) {
                    try {
                        MyDate = (Date) MyDateFormat.parse(Args[ip1]);
                        setBegDate(new Timestamp(MyDate.getTime()));
                        i = ip1;
                    } catch (Exception MyException) {
                        throw new GetArgsException("La date de d�but doit �tre valide jj/mm/aaaa : " + Args[ip1]);
                    }
                } else {
                    throw new GetArgsException("Date de d�but non d�finie");
                }
            } else if (Args[i].equals("-e")) {
                if (ip1 < n) {
                    try {
                        MyDate = (Date) MyDateFormat.parse(Args[ip1]);
                        setEndDate(new Timestamp(MyDate.getTime()));
                        i = ip1;
                    } catch (Exception MyException) {
                        throw new GetArgsException("La date de fin doit �tre valide jj/mm/aaaa : " + Args[ip1]);
                    }
                } else {
                    throw new GetArgsException("Date de fin non d�finie");
                }
            } else if (Args[i].equals("-n")) {
                if (ip1 < n) {
                    try {
                        setNbJour(Integer.parseInt(Args[ip1]));
                        i = ip1;
                    } catch (Exception MyException) {
                        throw new GetArgsException("Le nombre de jour(s) doit �tre num�rique : " + Args[ip1]);
                    }
                } else {
                    throw new GetArgsException("Nombre de jour(s) non d�fini");
                }
            } else if (Args[i].equals("-o")) {
                if (ip1 < n) {
                    setFilename(Args[ip1]);
                    i = ip1;
                } else {
                    throw new GetArgsException("Nom de fichier non d�fini");
                }
            } else if (Args[i].equals("-p")) {
                if (ip1 < n) {
                    setDirectory(Args[ip1]);
                    i = ip1;
                } else {
                    throw new GetArgsException("R�pertoire non d�fini");
                }
            } else if (Args[i].equals("-s")) {
                if (ip1 < n) {
                    setSuffix(Args[ip1]);
                    i = ip1;
                } else {
                    throw new GetArgsException("Suffixe non d�fini");
                }
            } else if (Args[i].equals("-d")) {
                setDebugMode(true);
            } else if (Args[i].equals("-t")) {
                setTestMode(true);
            } else {
                throw new GetArgsException("Mauvais argument : " + Args[i]);
            }
            i++;
        }
        if (getBegDate().after(getEndDate())) {
            throw new GetArgsException("La date de d�but " + MyDateFormat.format(getBegDate())
                    + " doit �tre ant�rieure � la date de fin " + MyDateFormat.format(getEndDate()));
        }
    }

    /**
     * Affiche le mode d'utilisation du programme.
     */
    public static void usage() {
        System.out.println("Usage : java ExpCalls -dbserver prod -u unum "
                + " [[-b d�but] [-f fin]|[-n nbJour]] [-o fichier.xml]"
                + " [-p r�pertoire] [-s suffix] [-d] [-t]");
    }

    /**
     * @return le r�pertoire o� exporter le fichier des r�sultats
     */
    public String getDirectory() {
        return directory;
    }

    /**
     * @param directory d�finit le r�pertoire o� exporter le fichier des
     * r�sultats
     */
    public void setDirectory(String directory) {
        this.directory = directory;
    }

    /**
     * Affiche le contenu de GetArgs.
     *
     * @return le contenu de GetArgs.
     */
    @Override
    public String toString() {
        return "GetArgs:{"
                + "dbServer=" + SourceServer
                + ", unum=" + unum
                + ", d�but=" + MyDateFormat.format(BegDate)
                + ", fin=" + MyDateFormat.format(EndDate)
                + ", fichier=" + filename
                + ", r�pertoire=" + directory
                + ", nbJour=" + nbJour
                + ", suffixe=" + suffix
                + ", debugMode=" + debugMode
                + ", testMode=" + testMode
                + "}";
    }

    /**
     * @return le nombre de jours � compter de la date courante
     */
    public int getNbJour() {
        return nbJour;
    }

    /**
     * @param nbJour d�finit le nombre de jours � compter de la date courante
     * La date de d�but et la date de fin sont d�finit en cons�quence.
     */
    public void setNbJour(int nbJour) {
        Calendar calendar;
        
        this.nbJour = nbJour;

        // R�cup�re la date du jour
        calendar = new GregorianCalendar();
        
        // Elimine les heures, minutes, secondes et millisecondes.
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        setEndDate(new Timestamp(calendar.getTimeInMillis()));
        
        // Calcule la date de d�but d'extraction
        calendar.add(Calendar.DAY_OF_YEAR, - nbJour);
        setBegDate(new Timestamp(calendar.getTimeInMillis()));
    }

    /**
     * @return le suffixe � ajouter au nom du fichier
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * @param suffix d�finit le suffixe � ajouter au nom du fichier
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
