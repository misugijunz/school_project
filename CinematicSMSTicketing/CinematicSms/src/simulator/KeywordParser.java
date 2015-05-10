/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package simulator;

import java.util.Vector;
import java.util.Hashtable;



/**
 *
 * @author Oscar Kurniawan
 */
public class KeywordParser {
    private Vector kodeBioskop;
    private Vector kodeFilm;
    private Vector kodeVocerAktif;

    private Hashtable petaSaldo;
    private Hashtable infoFilm;

    private String Id;

    private boolean terjadiGalat = false;

    private final String ERROR_REG = "Maaf, format yang anda ketik salah. " +
            "Format yang benar : REG <spasi> ID_MEMBER. Cth: REG 011011.";
    private final String ERROR_INFO = "Maaf, format yang anda ketik salah. " +
            "Format yang benar : INFO <spasi> KODE_MALL. Cth: INFO GI";
    private final String ERROR_PESAN = "Maaf, format yang anda ketik salah. " +
            "Format yang benar : PESAN <spasi> KODE_FILM <spasi> JAM_TAYANG <spasi> JLH_TIKET. Cth: PESAN NM A 2";
    private final String ERROR_SALDO = "Maaf, format yang anda ketik salah. " +
            "Format yang benar : REG <spasi> SALDO <spasi> ID_MEMBER. Cth: REG SALDO 011011";


    public KeywordParser(String Id) {

        this.Id = Id;

        init();

    }

    private void init() {
        kodeBioskop = new Vector();
        kodeBioskop.addElement("GI");
        kodeBioskop.addElement("MOI");

        kodeFilm = new Vector();
        kodeFilm.addElement("NM");
        kodeFilm.addElement("2012");
        kodeFilm.addElement("CC");
        kodeFilm.addElement("KCB");

        kodeVocerAktif = new Vector();
        kodeVocerAktif.addElement("20103103");
        kodeVocerAktif.addElement("20103003");
        kodeVocerAktif.addElement("011011");

        petaSaldo = new Hashtable();

        petaSaldo.put("20103103", new Integer(150000));
        petaSaldo.put("20103003", new Integer(74000));

        infoFilm = new Hashtable();

        infoFilm.put("GI", "GI 02/12/2009. Harga tiket: Rp.25.000" +
            "Studio1 NewMoon(NM) 13:00(A) 15:00(B) 17:00(C)" +
            "Studio2 Christmas Carol(CC) 13:00(A) 17:00(B)" +
            "Studio3 2012(2012) 13:00(A) 16:00(B) 17:00(C)" +
            "Studio4 Ketika Cinta Bertasbih(KCB) 15:00(A) 17:00(B)" +
            "Next: PESAN<spasi>KODE_FILM<spasi> JAM_TAYANG<spasi>JLH_TIKET kirim ke 1234. Cth: PESAN NM A 2");
    }

    public String getResponse(String input) {
        String [] kata_terpisah = split(input," ");
        String temp;


        temp = kata_terpisah[0].toLowerCase();

        if (temp.startsWith("r")) {
            if (temp.equals("reg")) {
                temp = kata_terpisah[1].toLowerCase();

                if (kata_terpisah.length == 2) {
                    if (!kodeVocerAktif.contains(temp)) {
                        terjadiGalat = true;

                        return ERROR_REG;
                    }

                    return "ID_MEMBER anda sudah dapat digunakan. " +
                            "Next : INFO <spasi> KODE_MALL kirim ke 1234. Cth: INFO GI";
                }
                else if (kata_terpisah.length == 3) {
                    if (! temp.equals("saldo")) {
                        terjadiGalat = true;

                        return ERROR_SALDO;
                    }
                    else {
                        temp = kata_terpisah[2].toLowerCase();

                        if (!kodeVocerAktif.contains(temp)) {
                            terjadiGalat = true;

                            return ERROR_SALDO;
                        }
                        return "Sisa voucher anda adalah Rp. " + petaSaldo.get(temp) + ",-";
                    }
                }

            }
            else {
                terjadiGalat = true;

                return ERROR_REG;
            }
        }
        else if (temp.startsWith("i")) {
            if (! temp.equals("info")) {
                terjadiGalat = true;
                return ERROR_INFO;
            }
            else {
                temp = kata_terpisah[1].toLowerCase();

                if (!kodeBioskop.contains(temp)) {
                    terjadiGalat = true;
                    return ERROR_INFO;
                }
                return (String)infoFilm.get(temp);
            }
        }
        else if (temp.startsWith("p")) {
            if (! temp.equals("pesan")) {
                terjadiGalat = true;
                return ERROR_PESAN;
            }
            else {
                temp = kata_terpisah[1].toLowerCase();

                if (!kodeFilm.contains(temp)) {
                    terjadiGalat = true;
                    return ERROR_PESAN;
                }
                else {
                    int jumlah_tiket;

                    try {
                        jumlah_tiket = Integer.parseInt(kata_terpisah[3]);
                    }catch(Exception e){
                        terjadiGalat = true;
                        return ERROR_PESAN;
                    }
                    int cur_saldo = ((Integer)petaSaldo.get(Id)).intValue();
                    if (25000*jumlah_tiket > cur_saldo){
                        terjadiGalat = true;

                        return "Saldo anda tidak mencukupi, silahkan lakukan pengisian ulang. Saldo anda saat ini adalah Rp. " + cur_saldo + ",-";
                    }
                    else
                        return "Terimakasih. Anda dengan ID" + Id + " telah memesan " +
                                "tiket pada bioskop Grand Indonesia dengan film " +
                                "New Moon pada jam 13:00 sebanyak 2 tiket. " +
                                "Bangku yang anda peroleh  adalah G12 G11. " +
                                "Kode E-tiket anda adalah GI13245. Silahkan " +
                                "tunjukkan sms ini pada petugas counter " +
                                "sebagai bukti pemesanan";
                }
            }
        }

        return "";
    }

    public String[] split(String input, final String separator) {
        Vector temp = new Vector();
        int index;

        index = input.indexOf(separator);

        while (index >= 0) {
            temp.addElement(input.substring(0, index));
            input = input.substring(index + separator.length());
            index = input.indexOf(separator);
            //input.s
        }

        temp.addElement(input);

        String []result = new String[temp.size()];

        for (int i = 0; i < temp.size(); i++) {
            result[i] = (String)temp.elementAt(i);
            System.out.println(result[i]);
        }

        return result;
    }

    public boolean isError() {
        return terjadiGalat;
    }

}
