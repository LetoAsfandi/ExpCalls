package expcalls;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import utils.GetArgsException;
import utils.ValidServers;

/**
 * Cette classe sert �v�rifier et � r�cup�rer les arguments pass�s en ligne de
 * commande �un programme.
 *
 * @author Thierry Baribaud.
 * @version 0.53
 */
public class GetArgs {

    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * sourceServer : prod pour le serveur de production, dev pour le serveur de
     * d�veloppement. Valeur par d�faut : dev.
     */
    private String sourceServer = "dev";

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
     * begDate : date de d�but de l'export � 0h.
     */
    private Timestamp begDate = new Timestamp((new java.util.Date().getTime()) - 1000 * 60 * 60 * 24);

    /**
     * endDate : date de fin de l'export � 0h.
     */
    private Timestamp endDate = new Timestamp(new java.util.Date().getTime());

    /**
     * nbJour : nombre de jours � compter de la date courante
     */
    private int nbJour;

    /**
     * suffix : suffixe optionnel � rajouter au nom du fichier
     */
    private String suffix = null;

    /**
     * Filtrer les tickets ouverts
     */
    private boolean openedTicket = false;

    /**
     * Filtrer les tickets associ�s � l'intervenant
     */
    private int tnum;
    
    /**
     * Filtrer les tickets associ�s � une agence
     */
    private int a6num;
    
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
     * @return sourceServer : la valeur pour le serveur source.
     */
    public String getSourceServer() {
        return (sourceServer);
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
     * @return begDate : date de d�but de l'export � 0h.
     */
    public Timestamp getBegDate() {
        return (begDate);
    }

    /**
     * @return endDate : date de fin de l'export � 0h.
     */
    public Timestamp getEndDate() {
        return (endDate);
    }

    /**
     * Retourne s'il y a ou non filtrage des tickets ouverts
     *
     * @return s'il y a ou non filtrage des tickets ouverts
     */
    public boolean isOpenedTicket() {
        return openedTicket;
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
     * @param sourceServer : d�finit le serveur source.
     */
    public void setSourceServer(String sourceServer) {
        this.sourceServer = sourceServer;
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
     * @param begDate : date de d�but de l'export � 0h.
     */
    public void setBegDate(Timestamp begDate) {
        this.begDate = begDate;
    }

    /**
     * @param endDate : date de fin de l'export � 0h.
     */
    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    /**
     * D�finit l'�tat de filtrage des tickets ouverts
     *
     * @param openedTicket �tat de filtrage des tickets ouverts
     */
    public void setOpenedTicket(boolean openedTicket) {
        this.openedTicket = openedTicket;
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
     * @param args arguments de la ligne de commande.
     * @throws GetArgsException erreur sur les param�tres.
     */
    public GetArgs(String args[]) throws GetArgsException {

        int i;
        int n;
        int ip1;
        Date MyDate;
        String currentParam;
        String nextParam;

        // Demande une analyse d'une date valide
        dateFormat.setLenient(false);
        n = args.length;

        System.out.println("nargs=" + n);
//    for(i=0; i<n; i++) System.out.println("args["+i+"]="+Args[i]);
        i = 0;
        while (i < n) {
//            System.out.println("args[" + i + "]=" + Args[i]);
            currentParam = args[i];
            ip1 = i + 1;
            nextParam = (ip1 < n) ? args[ip1] : null;
            if (currentParam.equals("-dbserver")) {
                if (ip1 < n) {
                    if (ValidServers.isAValidServer(nextParam)) {
                        sourceServer = nextParam;
                    } else {
                        throw new GetArgsException("Mauvaise source de donn�es : " + nextParam);
                    }
                    i = ip1;
                } else {
                    throw new GetArgsException("Base de donn�es non d�finie");
                }
            } else if (currentParam.equals("-u")) {
                if (ip1 < n) {
                    try {
                        unum = Integer.parseInt(nextParam);
                        i = ip1;
                    } catch (Exception MyException) {
                        throw new GetArgsException("La r�f�rence client doit �tre num�rique : " + nextParam);
                    }
                } else {
                    throw new GetArgsException("R�f�rence client non d�finie");
                }
            } else if (currentParam.equals("-b")) {
                if (ip1 < n) {
                    try {
                        MyDate = (Date) dateFormat.parse(nextParam);
                        begDate = new Timestamp(MyDate.getTime());
                        i = ip1;
                    } catch (Exception MyException) {
                        throw new GetArgsException("La date de d�but doit �tre valide jj/mm/aaaa : " + nextParam);
                    }
                } else {
                    throw new GetArgsException("Date de d�but non d�finie");
                }
            } else if (currentParam.equals("-e")) {
                if (ip1 < n) {
                    try {
                        MyDate = (Date) dateFormat.parse(nextParam);
                        endDate = new Timestamp(MyDate.getTime());
                        i = ip1;
                    } catch (Exception MyException) {
                        throw new GetArgsException("La date de fin doit �tre valide jj/mm/aaaa : " + nextParam);
                    }
                } else {
                    throw new GetArgsException("Date de fin non d�finie");
                }
            } else if (currentParam.equals("-n")) {
                if (ip1 < n) {
                    try {
                        this.setNbJour(Integer.parseInt(nextParam));
                        i = ip1;
                    } catch (Exception MyException) {
                        throw new GetArgsException("Le nombre de jour(s) doit �tre num�rique : " + nextParam);
                    }
                } else {
                    throw new GetArgsException("Nombre de jour(s) non d�fini");
                }
            } else if (currentParam.equals("-o")) {
                if (ip1 < n) {
                    filename = nextParam;
                    i = ip1;
                } else {
                    throw new GetArgsException("Nom de fichier non d�fini");
                }
            } else if (currentParam.equals("-p")) {
                if (ip1 < n) {
                    directory = nextParam;
                    i = ip1;
                } else {
                    throw new GetArgsException("R�pertoire non d�fini");
                }
            } else if (currentParam.equals("-s")) {
                if (ip1 < n) {
                    suffix = nextParam;
                    i = ip1;
                } else {
                    throw new GetArgsException("Suffixe non d�fini");
                }
            } else if (currentParam.equals("-openedTicket")) {
                openedTicket = true;
            } else if (currentParam.equals("-provider")) {
                if (ip1 < n) {
                    try {
                        tnum = Integer.parseInt(nextParam);
                        i = ip1;
                    } catch (Exception MyException) {
                        throw new GetArgsException("La r�f�rence � l'intervenant doit �tre num�rique : " + nextParam);
                    }
                } else {
                    throw new GetArgsException("R�f�rence intervenant non d�finie");
                }
            } else if (currentParam.equals("-agencyId")) {
                if (ip1 < n) {
                    try {
                        a6num = Integer.parseInt(nextParam);
                        i = ip1;
                    } catch (Exception MyException) {
                        throw new GetArgsException("La r�f�rence � l'agence doit �tre num�rique : " + nextParam);
                    }
                } else {
                    throw new GetArgsException("R�f�rence agence non d�finie");
                }
            } else if (currentParam.equals("-d")) {
                debugMode = true;
            } else if (currentParam.equals("-t")) {
                testMode = true;
            } else {
                usage();
                throw new GetArgsException("Mauvais argument : " + currentParam);
            }
            i++;
        }
        if (begDate.after(endDate)) {
            throw new GetArgsException("La date de d�but " + dateFormat.format(begDate)
                    + " doit �tre ant�rieure � la date de fin " + dateFormat.format(endDate));
        }
    }

    /**
     * Affiche le mode d'utilisation du programme.
     */
    public static void usage() {
        System.out.println("Usage : java ExpCalls -dbserver dbserver -u unum "
                + " [[-b d�but] [-f fin]|[-n nbJour]] [-o fichier.xml]"
                + " [-p r�pertoire] [-s suffixe] [-ticketOpened]"
                + " [-provider tnum]"
                + " [-agencyId a6num]"
                + " [-d] [-t]");
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
                + "dbServer=" + sourceServer
                + ", unum=" + unum
                + ", d�but=" + dateFormat.format(begDate)
                + ", fin=" + dateFormat.format(endDate)
                + ", fichier=" + filename
                + ", r�pertoire=" + directory
                + ", nbJour=" + nbJour
                + ", suffixe=" + suffix
                + ", ticketOuvert=" + openedTicket
                + ", provider=" + tnum
                + ", agencyId=" + a6num
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
     * @param nbJour d�finit le nombre de jours � compter de la date courante La
     * date de d�but et la date de fin sont d�finit en cons�quence.
     */
    public final void setNbJour(int nbJour) {
        Calendar calendar;

        this.nbJour = nbJour;

        // R�cup�re la date du jour
        calendar = new GregorianCalendar();

        // Elimine les heures, minutes, secondes et millisecondes.
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        setEndDate(new Timestamp(calendar.getTimeInMillis()));

        // Calcule la date de d�but d'extraction
        calendar.add(Calendar.DAY_OF_YEAR, -nbJour);
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

    /**
     * @return retourne la r�f�rence de l'intervenant � filtrer
     */
    public int getTnum() {
        return tnum;
    }

    /**
     * @param tnum d�finit la r�f�rence de l'intervenant � filter
     */
    public void setTnum(int tnum) {
        this.tnum = tnum;
    }

    /**
     * @return retourne la r�f�rence de l'agence � filtrer
     */
    public int getA6num() {
        return a6num;
    }

    /**
     * @param a6num d�finit la r�f�rence de l'agence � filter
     */
    public void setA6num(int a6num) {
        this.a6num = a6num;
    }
}
