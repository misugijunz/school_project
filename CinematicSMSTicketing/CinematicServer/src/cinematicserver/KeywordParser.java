/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cinematicserver;

import java.util.ArrayList;
import java.util.HashMap;



/**
 *
 * @author Oscar Kurniawan
 */
public class KeywordParser {
    private ArrayList<String> kodeBioskop;
    private ArrayList<String> kodeFilm;
    private ArrayList<String> kodeVocerAktif;

    private HashMap<String, Integer> petaSaldo;
    private HashMap<String, String> infoFilm;

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
        kodeBioskop = new ArrayList<String>();
        kodeBioskop.add("GI");
        kodeBioskop.add("MOI");

        kodeFilm = new ArrayList<String>();
        kodeFilm.add("NM");
        kodeFilm.add("2012");
        kodeFilm.add("CC");
        kodeFilm.add("KCB");

        kodeVocerAktif = new ArrayList<String>();
        kodeVocerAktif.add("20103103");
        kodeVocerAktif.add("20103003");
        kodeVocerAktif.add("011011");

        petaSaldo = new HashMap<String, Integer>();

        petaSaldo.put("20103103", 150000);
        petaSaldo.put("20103003", 74000);

        infoFilm = new HashMap<String, String>();

        infoFilm.put("GI", "GI 02/12/2009. Harga tiket: Rp.25.000" +
            "Studio1 NewMoon(NM) 13:00(A) 15:00(B) 17:00(C)" +
            "Studio2 Christmas Carol(CC) 13:00(A) 17:00(B)" +
            "Studio3 2012(2012) 13:00(A) 16:00(B) 17:00(C)" +
            "Studio4 Ketika Cinta Bertasbih(KCB) 15:00(A) 17:00(B)" +
            "Next: PESAN<spasi>KODE_FILM<spasi> JAM_TAYANG<spasi>JLH_TIKET kirim ke 1234. Cth: REG SALDO 011011");
    }

    public String getResponse(String input) {
        String [] kata_terpisah = input.split(" ");
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
                return infoFilm.get(temp);
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
                    int cur_saldo = petaSaldo.get(Id);
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

    public boolean isError() {
        return terjadiGalat;
    }

}
