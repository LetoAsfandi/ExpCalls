package expcalls;

import bdd.ClotureAppel;
import bdd.EtatTicket;
import bdd.Factivity;
import bdd.FactivityDAO;
import bdd.Fagency;
import bdd.FagencyDAO;
import bdd.Fcalls;
import bdd.Fcomplmt;
import bdd.FcomplmtDAO;
import bdd.Fessais;
import bdd.FessaisDAO;
import bdd.Fmenuit;
import bdd.FmenuitDAO;
import bdd.Ftoubib;
import bdd.FtoubibDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Classe repr�sentant un ticket d'un client basique. Cela correspond �
 * l'association d'un appel Fcalls et d'un compl�ment d'appel Fcomplmt s'il
 * existe. Les tickets sp�cifiques � un client d�riveront de celui-ci.
 *
 * @author Thierry Baribaud
 * @version 0.45
 */
public class Ticket_0000 {

    /**
     * Connection � la base de donn�es courante.
     */
    private Connection MyConnection;

    /**
     * Etat du ticket.
     */
    private EtatTicket MyEtatTicket;

    /**
     * Partie compos�e de l'appel.
     */
    public Fcalls Fcalls_0000;

    /**
     * Partie compos�e du compl�ment d'appel.
     */
    public Fcomplmt Fcomplmt_0000;

    /**
     * Format de date "dd/mm/aaaa".
     */
    private static final DateFormat MyDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Nom de l'agence.
     */
    private String A6name = null;

    /**
     * Appelation externe de l'agence.
     */
    private String A6extname = null;

    /**
     * Nom abr�g� de l'agence.
     */
    private String A6abbname = null;

    /**
     * Item de menu s�lectionn�.
     */
    private String M6name = null;

    /**
     * Appelation externe de l'tem de menu s�lectionn�.
     */
    private String M6extname = null;

    /**
     * Etat de l'intervention.
     */
    private String EtatIntervention = null;

    /**
     * Prestataire sur la premi�re transmission.
     */
    private String Prestataire1 = null;

    /**
     * Nom du prestataire sur la premi�re transmission.
     */
    private String NomPrestataire1 = null;

    /**
     * Pr�nom du prestataire sur la premi�re transmission.
     */
    private String PrenomPrestataire1 = null;

    /**
     * Date de la premi�re transmission.
     */
    private String DateMissionnement1 = null;

    /**
     * Heure de la premi�re transmission.
     */
    private String HeureMissionnement1 = null;

    /**
     * Num�ro de t�l�phone du prestataire.
     */
    private String NoTelephone1 = null;

    /**
     * Email du prestataire.
     */
    private String Email1 = null;

    /**
     * Activit� du prestataire.
     */
    private String A4name1 = null;

    /**
     * D�lai d'intervention contractuel (exprim� en secondes) du premier
     * technicien contact�
     */
    private String delaiIntervention1 = null;

    /**
     * Prestataire sur la derni�re transmission.
     */
    private String Prestataire2 = null;

    /**
     * Nom du prestataire sur la derni�re transmission.
     */
    private String NomPrestataire2 = null;

    /**
     * Pr�nom du prestataire sur la derni�re transmission.
     */
    private String PrenomPrestataire2 = null;

    /**
     * Date de la derni�re transmission.
     */
    private String DateMissionnement2 = null;

    /**
     * Heure de la derni�re transmission.
     */
    private String HeureMissionnement2 = null;

    /**
     * Num�ro de t�l�phone du prestataire.
     */
    private String NoTelephone2 = null;

    /**
     * Email du prestataire.
     */
    private String Email2 = null;

    /**
     * Activit� du prestataire.
     */
    private String A4name2 = null;

    /**
     * D�lai d'intervention contractuel (exprim� en secondes) du premier
     * technicien contact�
     */
    private String delaiIntervention2 = null;

    /**
     * D�lai d'intervenion contractuel
     */
    private String DelaiIntervention = null;

    /**
     * Cl�ture d'appel associ�e au ticket.
     */
    private ClotureAppel clotureAppel;

    /**
     * Le ticket est-il annul� ou non ?
     */
    private boolean ticketCanceled;
    
    /**
     * Un SMS a-t-il �t� envoy� ?
     */
    private boolean smsSent = false;
    
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
    public Ticket_0000(Connection MyConnection, Fcalls Fcalls_0000,
            Fcomplmt Fcomplmt_0000, EtatTicket MyEtatTicket)
            throws ClassNotFoundException, SQLException {

        int cc6num;
        Fcomplmt MyFcomplmt;
        FcomplmtDAO MyFcomplmtDAO;
        int a6num;
        String MyA6extname;
        String MyA6name;
        int m6num;
        String MyM6name;
        String MyM6extname;
        Fagency MyFagency;
        FagencyDAO MyFagencyDAO;
        Fmenuit MyFmenuit;
        FmenuitDAO MyFmenuitDAO;
        int enumabs1 = 0;
        Fessais fessais;
        FessaisDAO fessaisDAO;
        int tnum = 0;
        Ftoubib MyFtoubib;
        FtoubibDAO MyFtoubibDAO;
        int a4num;
        String A4name;
        Factivity MyFactivity;
        FactivityDAO MyFactivityDAO;
        int delay;

        this.MyConnection = MyConnection;
        this.Fcalls_0000 = Fcalls_0000;
        this.Fcomplmt_0000 = Fcomplmt_0000;
        this.MyEtatTicket = MyEtatTicket;

        // R�cup�ration du compl�ment d'appel
//        System.out.println("  R�cup�ration du compl�ment d'appel");
        cc6num = this.Fcalls_0000.getCc6num();
        MyFcomplmt = null;
        if (cc6num > 0) {
            MyFcomplmtDAO = new FcomplmtDAO(MyConnection);
            MyFcomplmtDAO.filterById(cc6num);
            MyFcomplmtDAO.setSelectPreparedStatement();
            MyFcomplmt = MyFcomplmtDAO.select();
            if (MyFcomplmt != null) {
                this.setFcomplmt_0000(MyFcomplmt);
            }
            MyFcomplmtDAO.closeSelectPreparedStatement();
        }

        // R�cup�ration de l'agence
//        System.out.println("  R�cup�ration de l'agence");
        a6num = this.Fcalls_0000.getCzone();
        MyFagency = null;
        MyA6name = null;
        MyA6extname = null;
        if (a6num > 0) {
            MyFagencyDAO = new FagencyDAO(MyConnection);
            MyFagencyDAO.filterById(a6num);
            MyFagencyDAO.setSelectPreparedStatement();
            MyFagency = MyFagencyDAO.select();
            if (MyFagency != null) {
                MyA6name = MyFagency.getA6name();
                MyA6extname = MyFagency.getA6extname();
            }
            MyFagencyDAO.closeSelectPreparedStatement();
        }
        if (MyA6name != null) {
            this.setA6name(MyA6name);
        }
        if (MyA6extname != null) {
            this.setA6extname(MyA6extname);
        }

        // R�cup�ration de l'item du menu s�lectionn�
//        System.out.println("  R�cup�ration de l'item du menu s�lectionn�");
        m6num = this.Fcalls_0000.getCquery1();
        MyM6name = null;
        MyM6extname = null;
        MyFmenuit = null;
        if (m6num > 0) {
            MyFmenuitDAO = new FmenuitDAO(MyConnection);
            MyFmenuitDAO.filterById(m6num);
            MyFmenuitDAO.setSelectPreparedStatement();
            MyFmenuit = MyFmenuitDAO.select();
            MyM6name = MyFmenuit.getM6name();
            MyM6extname = MyFmenuit.getM6extname();
            MyFmenuitDAO.closeSelectPreparedStatement();
        }
        if (MyM6name != null) {
            this.setM6name(MyM6name);
        }
        if (MyM6extname != null) {
            this.setM6extname(MyM6extname);
        }

        // Recherche la premi�re transmission
//        System.out.println("  R�cup�ration de la premi�re transmission");
        fessaisDAO = new FessaisDAO(MyConnection, MyEtatTicket);
        fessaisDAO.setFirstTransmissionPreparedStatement(this.Fcalls_0000.getCnum());
        fessais = fessaisDAO.getFirstTransmission();
        fessaisDAO.closeFirstTransmissionPreparedStatement();
        if (fessais != null) {
            enumabs1 = fessais.getEnumabs();
            this.setEtatIntervention("Intervention");
            this.setDateMissionnement1(MyDateFormat.format(fessais.getEdate()));
            this.setHeureMissionnement1(fessais.getEtime());
            tnum = fessais.getEtnum();
            if (tnum > 0) {
                MyFtoubibDAO = new FtoubibDAO(MyConnection);
                MyFtoubibDAO.filterById(tnum);
                MyFtoubibDAO.setSelectPreparedStatement();
                MyFtoubib = MyFtoubibDAO.select();
                if (MyFtoubib != null) {
                    this.setNomPrestataire1(MyFtoubib.getTlname());
                    this.setPrenomPrestataire1(MyFtoubib.getTfname());
                    this.setPrestataire1(MyFtoubib.getTlname(), MyFtoubib.getTfname());
                    this.setNoTelephone1(MyFtoubib.getTel());
                    this.setEmail1(MyFtoubib.getTemail());
                    delay=MyFtoubib.getTdelay1() * 60;
                    if (delay>0) this.setDelaiIntervention1(CharDur(delay));
                    a4num = MyFtoubib.getTa4num();
                    if (a4num > 0) {
                        MyFactivityDAO = new FactivityDAO(MyConnection);
                        MyFactivityDAO.filterById(a4num);
                        MyFactivityDAO.setSelectPreparedStatement();
                        MyFactivity = MyFactivityDAO.select();
                        if (MyFactivity != null) {
                            A4name = MyFactivity.getA4name();
                            if (A4name != null) {
                                this.setA4name1(A4name);
                            }
                        }
                        MyFactivityDAO.closeSelectPreparedStatement();
                    }
                }
                MyFtoubibDAO.closeSelectPreparedStatement();
            }
        } else {
            this.setEtatIntervention("Message");
        }

        // Recherche la derni�re transmission
//        System.out.println("  R�cup�ration de la derni�re transmission");
        fessaisDAO.setLastTransmissionPreparedStatement(this.Fcalls_0000.getCnum());
        fessais = fessaisDAO.getLastTransmission();
        fessaisDAO.closeLastTransmissionPreparedStatement();
        if (fessais != null) {
            if (enumabs1 != fessais.getEnumabs()) {
                this.setEtatIntervention("Intervention");
                this.setDateMissionnement2(MyDateFormat.format(fessais.getEdate()));
                this.setHeureMissionnement2(fessais.getEtime());
                tnum = fessais.getEtnum();
                if (tnum > 0) {
                    MyFtoubibDAO = new FtoubibDAO(MyConnection);
                    MyFtoubibDAO.filterById(tnum);
                    MyFtoubibDAO.setSelectPreparedStatement();
                    MyFtoubib = MyFtoubibDAO.select();
                    if (MyFtoubib != null) {
                        this.setNomPrestataire2(MyFtoubib.getTlname());
                        this.setPrenomPrestataire2(MyFtoubib.getTfname());
                        this.setPrestataire2(MyFtoubib.getTlname(), MyFtoubib.getTfname());
                        this.setNoTelephone2(MyFtoubib.getTel());
                        this.setEmail2(MyFtoubib.getTemail());
                        delay=MyFtoubib.getTdelay1() * 60;
                        if (delay>0) this.setDelaiIntervention2(CharDur(delay));
                        a4num = MyFtoubib.getTa4num();
                        if (a4num > 0) {
                            MyFactivityDAO = new FactivityDAO(MyConnection);
                            MyFactivityDAO.filterById(a4num);
                            MyFactivityDAO.setSelectPreparedStatement();
                            MyFactivity = MyFactivityDAO.select();
                            if (MyFactivity != null) {
                                A4name = MyFactivity.getA4name();
                                if (A4name != null) {
                                    this.setA4name2(A4name);
                                }
                            }
                            MyFactivityDAO.closeSelectPreparedStatement();
                        }
                    }
                    MyFtoubibDAO.closeSelectPreparedStatement();
                }
            }
        }

        // Le ticket est-il annul� ?
        fessaisDAO = new FessaisDAO(MyConnection, MyEtatTicket);
        fessaisDAO.prepareTicketCanceledStatement(this.Fcalls_0000.getCnum());
        fessais = fessaisDAO.getTicketCanceled();
        this.ticketCanceled = (fessais != null);
        fessaisDAO.closeTicketCanceledPreparedStatement();
        
        // R�cup�ration de la cl�ture d'appel
//        System.out.println("  R�cup�ration de la cl�ture d'appel");
        this.clotureAppel = new ClotureAppel(MyConnection,
                this.Fcalls_0000.getCnum(), this.Fcalls_0000.getCdate(),
                DateMissionnement1, HeureMissionnement1,
                DateMissionnement2, HeureMissionnement2,
                MyEtatTicket);
//        System.out.println("    Une cl�ture d'appel trouv�e : =" + this.clotureAppel);
        
        // UN SMS a-t-il �t� envoy� durant le traitement du ticket
        fessaisDAO = new FessaisDAO(MyConnection, MyEtatTicket);
        fessaisDAO.setLastTrialPreparedStatement(this.Fcalls_0000.getCnum(), 99);
//        fessaisDAO.setTrialPreparedStatement(this.Fcalls_0000.getCnum(), 99);
        fessais = fessaisDAO.getLastTrial();
        this.smsSent = (fessais != null);
//        fessaisDAO.closeTrialPreparedStatement();
        fessaisDAO.closeLastTrialPreparedStatement();
    }

    /**
     * Contructeur secondaire de la classe Ticket sans le compl�ment d'appel.
     *
     * @param MyConnection connexion � la base de donn�es courante.
     * @param Fcalls_0000 appel,
     * @param MyEtatTicket etat du ticket.
     * @throws java.lang.ClassNotFoundException en cas de classe non trouv�e.
     * @throws java.sql.SQLException en cas d'erreur SQL.
     */
    public Ticket_0000(Connection MyConnection, Fcalls Fcalls_0000,
            EtatTicket MyEtatTicket)
            throws ClassNotFoundException, SQLException {
//        this.Fcalls_0000 = Fcalls_0000;
//        this.Fcomplmt_0000 = null;
        this(MyConnection, Fcalls_0000, null, MyEtatTicket);
    }

    /**
     * Retourne la connexion � la base de donn�es.
     *
     * @return MyConnection � la base de donn�es.
     */
    public Connection getConnection() {
        return (MyConnection);
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

    /**
     * @return A6name nom de l'agence.
     */
    public String getA6name() {
        return A6name;
    }

    /**
     * @param A6name d�finit le nom de l'agence.
     */
    public void setA6name(String A6name) {
        this.A6name = A6name;
    }

    /**
     * @return A6extname appellation externe de l'agence.
     */
    public String getA6extname() {
        return A6extname;
    }

    /**
     * @param A6extname d�finit l'appellation externe de l'agence.
     */
    public void setA6extname(String A6extname) {
        this.A6extname = A6extname;
    }

    /**
     * @return A6abbname nom abr�g� de l'agence.
     */
    public String getA6abbname() {
        return A6abbname;
    }

    /**
     * @param A6abbname d�finit le nom abr�g� de l'agence.
     */
    public void setA6abbname(String A6abbname) {
        this.A6abbname = A6abbname;
    }

    /**
     * @return M6name nom de l'item de menu s�lectionn�.
     */
    public String getM6name() {
        return M6name;
    }

    /**
     * @param M6name d�finit le nom de l'item de menu.
     */
    public void setM6name(String M6name) {
        this.M6name = M6name;
    }

    /**
     * @return M6extname appellation externe de l'item de menu s�lectionn�.
     */
    public String getM6extname() {
        return M6extname;
    }

    /**
     * @param M6extname d�finit l'appellation externe de l'item de menu.
     */
    public void setM6extname(String M6extname) {
        this.M6extname = M6extname;
    }

    /**
     * @return EtatIntervention l'�tat de l'intevention
     */
    public String getEtatIntervention() {
        return EtatIntervention;
    }

    /**
     * @param EtatIntervention d�finit l'�tat de l'intervention.
     */
    public void setEtatIntervention(String EtatIntervention) {
        this.EtatIntervention = EtatIntervention;
    }

    /**
     * @return Prestataire1 le nom complet du prestataire.
     */
    public String getPrestataire1() {
        return Prestataire1;
    }

    /**
     * @param Prestataire1 d�finit le nom complet du prestataire.
     */
    public void setPrestataire1(String Prestataire1) {
        this.Prestataire1 = Prestataire1;
    }

    /**
     * @return NomPrestataire1 le nom du prestataire.
     */
    public String getNomPrestataire1() {
        return NomPrestataire1;
    }

    /**
     * @param NomPrestataire1 d�finit le nom du prestataire.
     */
    public void setNomPrestataire1(String NomPrestataire1) {
        this.NomPrestataire1 = NomPrestataire1;
    }

    /**
     * @return PrenomPrestataire1 le pr�nom du prestataire.
     */
    public String getPrenomPrestataire1() {
        return PrenomPrestataire1;
    }

    /**
     * @param PrenomPrestataire1 d�finit le pr�nom du prestataire.
     */
    public void setPrenomPrestataire1(String PrenomPrestataire1) {
        this.PrenomPrestataire1 = PrenomPrestataire1;
    }

    /**
     * @param Lastname nom du prestataire,
     * @param Firstname pr�nom du prestataire,
     */
    public void setPrestataire1(String Lastname, String Firstname) {
        StringBuffer MyName = null;

        if (Lastname != null) {
            MyName = new StringBuffer(Lastname);
        }
        if (Firstname != null) {
            if (MyName != null) {
                MyName.append(" ").append(Firstname);
            } else {
                MyName = new StringBuffer(Firstname);
            }
        }
        if (MyName != null) {
            this.setPrestataire1(MyName.toString());
        }
    }

    /**
     * @return DateMissionnement1 la premi�re date de missionnement.
     */
    public String getDateMissionnement1() {
        return DateMissionnement1;
    }

    /**
     * @param DateMissionnement1 d�finit la premi�re date de missionnement.
     */
    public void setDateMissionnement1(String DateMissionnement1) {
        this.DateMissionnement1 = DateMissionnement1;
    }

    /**
     * @return HeureMissionnement1 la premi�re heure de missionnement.
     */
    public String getHeureMissionnement1() {
        return HeureMissionnement1;
    }

    /**
     * @param HeureMissionnement1 d�finit la premi�re heure de missionnement.
     */
    public void setHeureMissionnement1(String HeureMissionnement1) {
        this.HeureMissionnement1 = HeureMissionnement1;
    }

    /**
     * @return NoTelephone1 le num�ro de t�l�phone du prestataire.
     */
    public String getNoTelephone1() {
        return NoTelephone1;
    }

    /**
     * @param NoTelephone1 d�finit le num�ro de t�l�phone du prestataire.
     */
    public void setNoTelephone1(String NoTelephone1) {
        this.NoTelephone1 = NoTelephone1;
    }

    /**
     * @return Email1 l'email du prestataire.
     */
    public String getEmail1() {
        return Email1;
    }

    /**
     * @param Email1 d�finit l'email du prestataire.
     */
    public void setEmail1(String Email1) {
        this.Email1 = Email1;
    }

    /**
     * @return A4name1 l'activit� du prestataire.
     */
    public String getA4name1() {
        return A4name1;
    }

    /**
     * @param A4name1 d�finit l'activit� du prestataire.
     */
    public void setA4name1(String A4name1) {
        this.A4name1 = A4name1;
    }

    /**
     * @return delaiIntervention1 retourne le d�lai d'intervention du premier
     * intervenant contact�.
     */
    public String getDelaiIntervention1() {
        return (delaiIntervention1);
    }

    /**
     * @param delaiIntervention1 d�finit le d�lai d'intervention du premier
     * intervenant contact�.
     */
    public void setDelaiIntervention1(String delaiIntervention1) {
        this.delaiIntervention1 = delaiIntervention1;
    }

    /**
     * @param duree d�finit le d�lai d'intervention du premier intervenant
     * contact� � partir d'une dur�e en secondes.
     */
    public void setDelaiIntervention1(int duree) {
        setDelaiIntervention1(CharDur(duree));
    }

    /**
     * @return Prestataire2 le nom complet du prestataire.
     */
    public String getPrestataire2() {
        return Prestataire2;
    }

    /**
     * @param Prestataire2 d�finit le nom complet du prestataire.
     */
    public void setPrestataire2(String Prestataire2) {
        this.Prestataire2 = Prestataire2;
    }

    /**
     * @return NomPrestataire2 le nom du prestataire.
     */
    public String getNomPrestataire2() {
        return NomPrestataire2;
    }

    /**
     * @param NomPrestataire2 d�finit le nom du prestataire.
     */
    public void setNomPrestataire2(String NomPrestataire2) {
        this.NomPrestataire2 = NomPrestataire2;
    }

    /**
     * @return PrenomPrestataire2 le pr�nom du prestataire.
     */
    public String getPrenomPrestataire2() {
        return PrenomPrestataire2;
    }

    /**
     * @param PrenomPrestataire2 d�finit le pr�nom du prestataire.
     */
    public void setPrenomPrestataire2(String PrenomPrestataire2) {
        this.PrenomPrestataire2 = PrenomPrestataire2;
    }

    /**
     * @param Lastname nom du prestataire,
     * @param Firstname pr�nom du prestataire,
     */
    public void setPrestataire2(String Lastname, String Firstname) {
        StringBuffer MyName = null;

        if (Lastname != null) {
            MyName = new StringBuffer(Lastname);
        }
        if (Firstname != null) {
            if (MyName != null) {
                MyName.append(" ").append(Firstname);
            } else {
                MyName = new StringBuffer(Firstname);
            }
        }
        if (MyName != null) {
            this.setPrestataire2(MyName.toString());
        }
    }

    /**
     * @return DateMissionnement2 la derni�re date de missionnement.
     */
    public String getDateMissionnement2() {
        return DateMissionnement2;
    }

    /**
     * @param DateMissionnement2 d�finit la derni�re date de missionnement.
     */
    public void setDateMissionnement2(String DateMissionnement2) {
        this.DateMissionnement2 = DateMissionnement2;
    }

    /**
     * @return HeureMissionnement2 la derni�re heure de missionnement.
     */
    public String getHeureMissionnement2() {
        return HeureMissionnement2;
    }

    /**
     * @param HeureMissionnement2 d�finit la derni�re heure de missionnement.
     */
    public void setHeureMissionnement2(String HeureMissionnement2) {
        this.HeureMissionnement2 = HeureMissionnement2;
    }

    /**
     * @return NoTelephone2 le num�ro de t�l�phone du prestataire.
     */
    public String getNoTelephone2() {
        return NoTelephone2;
    }

    /**
     * @param NoTelephone2 d�finit le num�ro de t�l�phone du prestataire.
     */
    public void setNoTelephone2(String NoTelephone2) {
        this.NoTelephone2 = NoTelephone2;
    }

    /**
     * @return Email2 l'email du prestataire.
     */
    public String getEmail2() {
        return Email2;
    }

    /**
     * @param Email2 d�finit l'email du prestataire.
     */
    public void setEmail2(String Email2) {
        this.Email2 = Email2;
    }

    /**
     * @return A4name2 l'activit� du prestataire.
     */
    public String getA4name2() {
        return A4name2;
    }

    /**
     * @param A4name2 d�finit l'activit� du prestataire.
     */
    public void setA4name2(String A4name2) {
        this.A4name2 = A4name2;
    }

    /**
     * @return delaiIntervention2 retourne le d�lai d'intervention du second
     * intervenant contact�.
     */
    public String getDelaiIntervention2() {
        return (delaiIntervention2);
    }

    /**
     * @param delaiIntervention2 d�finit le d�lai d'intervention du second
     * intervenant contact�.
     */
    public void setDelaiIntervention2(String delaiIntervention2) {
        this.delaiIntervention2 = delaiIntervention2;
    }

    /**
     * @param duree d�finit le d�lai d'intervention du second intervenant
     * contact� � partir d'une dur�e en secondes.
     */
    public void setDelaiIntervention2(int duree) {
        setDelaiIntervention2(CharDur(duree));
    }

    /**
     * @return MyEtatTicket l'�tat du ticket.
     */
    public EtatTicket getMyEtatTicket() {
        return MyEtatTicket;
    }

    /**
     * @param MyEtatTicket d�finit l'�tat du ticket.
     */
    public void setMyEtatTicket(EtatTicket MyEtatTicket) {
        this.MyEtatTicket = MyEtatTicket;
    }

    /**
     * @return DelaiIntervention retourne le d�lai d'intervention contractuel.
     */
    public String getDelaiIntervention() {
        return (DelaiIntervention);
    }

    /**
     * @param DelaiIntervention d�finit le d�lai d'intervention contractuel.
     */
    public void setDelaiIntervention(String DelaiIntervention) {
        this.DelaiIntervention = DelaiIntervention;
    }

    /**
     * @param duree d�finit le d�lai d'intervention contractuel � partir d'une
     * dur�e en secondes.
     */
    public void setDelaiIntervention(int duree) {
        setDelaiIntervention(CharDur(duree));
    }

    /**
     * Traduit un code d'origine de l'appel en libell�. Repris de tra_origine()
     * dans libutil.4gl.)
     *
     * @param codeOrigine code de l'origine de l'appel.
     * @return LibelleOrigine libell� associ�.
     */
    public String traOrigine(int codeOrigine) {
        String LibelleOrigine;

        switch (codeOrigine) {
            case 1:
                LibelleOrigine = "T�l�phone";
                break;
            case 2:
                LibelleOrigine = "Mail";
                break;
            case 3:
                LibelleOrigine = "fax";
                break;
            default:
                LibelleOrigine = null;
                break;
        }
        return (LibelleOrigine);
    }

    /**
     * Traduit un code d'urgence de l'appel en libell�. Repris de tra_urgence()
     * dans libutil.4gl.)
     *
     * @param codeUrgence code de l'urgence de l'appel.
     * @return LibelleUrgence libell� associ�.
     */
    public String traUrgence(int codeUrgence) {
        String LibelleUrgence;

        switch (codeUrgence) {
            case 0:
                LibelleUrgence = "NON";
                break;
            case 1:
                LibelleUrgence = "OUI";
                break;
            case 2:
                LibelleUrgence = "NON";
                break;
            default:
                LibelleUrgence = "Ind�termin�";
                break;
        }
        return (LibelleUrgence);
    }

    /**
     * Traduit une dur�e exprim�e en secondes au format xxhxxmxxs. Repris de
     * chardur.4gl.
     *
     * @param duree en secondes � traduire.
     * @return DureeAuFormat dur�e au format.
     */
    public String CharDur(int duree) {
        StringBuffer DureeAuFormat;
        int heure;
        int minute;
        int seconde;

        DureeAuFormat = null;

//        System.out.println("duree="+duree);
        heure = duree / 3600;
        if (heure > 0) {
            DureeAuFormat = new StringBuffer(heure + "h");
        }

        minute = duree / 60 - heure * 60;
        if (minute > 0) {
            if (DureeAuFormat == null) {
                DureeAuFormat = new StringBuffer(minute + "m");
            } else {
                DureeAuFormat.append(minute).append("m");
            }
        }

        seconde = duree - 60 * (minute + 60 * heure);
        if (seconde > 0) {
            if (DureeAuFormat == null) {
                DureeAuFormat = new StringBuffer(seconde + "s");
            } else {
                DureeAuFormat.append(seconde).append("s");
            }
        }

        return (DureeAuFormat.toString());
    }

    /**
     * @return la cl�ture d'appel associ�e au ticket.
     */
    public ClotureAppel getClotureAppel() {
        return clotureAppel;
    }

    /**
     * @param clotureAppel d�finit la cl�ture d'appel associ�e au ticket.
     */
    public void setClotureAppel(ClotureAppel clotureAppel) {
        this.clotureAppel = clotureAppel;
    }

    /**
     * @return si le ticket est annul� ou non
     */
    public boolean isTicketCanceled() {
        return ticketCanceled;
    }

    /**
     * @param ticketCanceled d�finit si le ticket est annul� ou non
     */
    public void setTicketCanceled(boolean ticketCanceled) {
        this.ticketCanceled = ticketCanceled;
    }

    /**
     * @return si un SMS a �t� envoy� ou non
     */
    public boolean isSmsSent() {
        return smsSent;
    }

    /**
     * @param smsSent d�finit si un SMS a �t� envoy� ou non
     */
    public void setSmsSent(boolean smsSent) {
        this.smsSent = smsSent;
    }
}
