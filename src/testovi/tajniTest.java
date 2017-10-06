/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testovi;

import funkcionalnosti.Funkcionalnosti;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import util.Util;

/**
 *
 * @author stefan
 */
public class tajniTest {
    
     public static double test(Funkcionalnosti f){
         
 
        double poena = 0;
        
        Date trenutnoVreme;
        
        int idGradiliste1 = f.unesiGradiliste("Gradiliste 1", Date.valueOf("2017-12-14"));
        int idGradiliste2 = f.unesiGradiliste("Gradiliste 1", Date.valueOf("2017-12-13"));
        int idObjekat11 = f.unesiObjekat("Stambena zgrada 11", idGradiliste1);
        int idObjekat12 = f.unesiObjekat("Stambena zgrada 12", idGradiliste1);
        int idObjekat21 = f.unesiObjekat("Stambena zgrada 21", idGradiliste2);
        
        int idSprat1 = f.unesiSprat(1, idObjekat11);
        int idSprat0 = f.unesiSprat(0, idObjekat11);
        
        f.unesiSprat(0, idObjekat12);
        int cs1 =  f.unesiSprat(1, idObjekat12);
         f.unesiSprat(2, idObjekat12);
        int cs0 =f.unesiSprat(0, idObjekat21);
        int cs2 = f.unesiSprat(1, idObjekat21);
        int cs3 = f.unesiSprat(2, idObjekat21);
        
        if(idSprat1 == -1 && f.obrisiSprat(cs1) == 1 && f.obrisiSprat(cs2) == 1 && f.obrisiSprat(cs3) == 0 && f.obrisiSprat(cs0) == 1){
            poena += 0.25;
        }
        
        int zaposleni1 = f.unesiZaposlenog("Milos", "Milosevic", "2503989720031", "M", "70-11032274-01", "milos@google.com", "069/1245301");
        int zaposleni2 = f.unesiZaposlenog("Jovan", "Jovanovic", "2403989720031", "M", "70-11032274-02", "jovan@google.com", "069/1245302");
  
        int zaposleni3 = f.unesiZaposlenog("Marko", "Markovic", "2402989720031", "M", "70-11032274-03", "marko@google.com", "+38161-127-53-01");
        int zaposleni4 = f.unesiZaposlenog("Katarina", "Vasic", "1204990720031", "Z", "70-11032274-04", "katarina@google.com", "+38169-127-53-02");
        int zaposleni5 = f.unesiZaposlenog("Milos", "Milosevic", "2503989720431", "M", "71-11032274-01", "milos@google.com", "+38169-127-53-01");
       
        int idMagacin1 = f.unesiMagacin(zaposleni1 , new BigDecimal(100.00), idGradiliste1);

        int idMagacin2 = f.unesiMagacin(zaposleni3 , new BigDecimal(1000.00), idGradiliste2);
        
        f.zaposleniRadiUMagacinu(zaposleni1, idMagacin2);
        
        f.isplatiPlateZaposlenimaUMagacinu(idMagacin2);
        
        if(f.unesiMagacin(zaposleni4 , new BigDecimal(1000.00), idGradiliste2) == -1 && f.dohvatiSveZaposlene().size() == 5 && f.dohvatiUkupanIsplacenIznosZaZaposlenog(zaposleni1).compareTo(new BigDecimal(0.000)) == 0 && f.dohvatiUkupanIsplacenIznosZaZaposlenog(zaposleni3).compareTo(new BigDecimal(1000.000)) == 0 ){
            poena += 0.75;
        }
        
        f.izmeniSefaZaMagacin(idMagacin2, zaposleni1);
        
        f.isplatiPlateZaposlenimaUMagacinu(idMagacin2);
        
        if(f.dohvatiUkupanIsplacenIznosZaZaposlenog(zaposleni1).compareTo(new BigDecimal(0.000)) == 0 && f.dohvatiUkupanIsplacenIznosZaZaposlenog(zaposleni3).compareTo(new BigDecimal(2000.000)) == 0){
            poena += 0.25;
        }
        
        f.izmeniSefaZaMagacin(idMagacin2, zaposleni3);
        
         
        f.zaposleniRadiUMagacinu(zaposleni2, idMagacin1);
        f.zaposleniRadiUMagacinu(zaposleni2, idMagacin2);
        f.zaposleniRadiUMagacinu(zaposleni4, idMagacin2);
        f.zaposleniRadiUMagacinu(zaposleni4, idMagacin1);
  
       
        
        f.isplatiPlateZaposlenimaUSvimMagacinima();
        
        f.zaposleniNeRadiUMagacinu(zaposleni1);
        f.izmeniSefaZaMagacin(idMagacin2, zaposleni1);
        f.zaposleniNeRadiUMagacinu(zaposleni4);
        f.zaposleniRadiUMagacinu(zaposleni4, idMagacin1);
        f.izmeniSefaZaMagacin(idMagacin1, zaposleni4);
        
        f.isplatiPlateZaposlenimaUSvimMagacinima();
        
        if(f.dohvatiUkupanIsplacenIznosZaZaposlenog(zaposleni1).compareTo(new BigDecimal(1100.000)) == 0 && f.dohvatiUkupanIsplacenIznosZaZaposlenog(zaposleni2).compareTo(new BigDecimal(200.000)) == 0 && f.dohvatiUkupanIsplacenIznosZaZaposlenog(zaposleni3).compareTo(new BigDecimal(3000.000)) == 0 && f.dohvatiUkupanIsplacenIznosZaZaposlenog(zaposleni4).compareTo(new BigDecimal(1100.000)) == 0){
            poena += 0.5;
        } //moze 4000 ako ostaje da radi         
        
        int idHTZ = f.unesiTipRobe("HTZ");
        int idAlat = f.unesiTipRobe("alat");
        int idMaterijal = f.unesiTipRobe("materijal");
        
        int idNUD = f.unesiNormuUgradnogDela("Ugradni deo 1", new BigDecimal(50), new BigDecimal(100));
        
        //List<Integer> materijali = new ArrayList<>();
        int count = 0;
        int idRoba1;
        int idRoba = idRoba1=  f.unesiRobu("Pesak", "0001", idMaterijal);
        if(idRoba != -1) count++;
        f.unesiRobuUMagacinPoKolicini(idRoba, idMagacin1, new BigDecimal(100));
        f.unesiRobuUMagacinPoKolicini(idRoba, idMagacin2, new BigDecimal(100));
       
        
        idRoba = f.unesiRobu("Cigla", "0001", idMaterijal);
        if(idRoba == -1 &&
        f.unesiRobuUMagacinPoBrojuJedinica(idRoba, idMagacin2, 3000) == -1 &&
        f.unesiPotrebanMaterijalPoBrojuJedinica(idRoba, idNUD, 500) == -1 ) count++;
        
        int nr1k = idRoba = f.unesiRobu("Cement", "0003", idMaterijal);
        if(idRoba != -1) count++;
        f.unesiRobuUMagacinPoKolicini(idRoba, idMagacin1, new BigDecimal(200));
        f.unesiPotrebanMaterijalPoKolicini(idRoba, idNUD, new BigDecimal(200));
        
        int nr1j = idRoba = f.unesiRobu("Keramicka plocica", "0004", idMaterijal);
        if(idRoba != -1) count++;
        f.unesiRobuUMagacinPoBrojuJedinica(idRoba, idMagacin2, 300);
        f.unesiPotrebanMaterijalPoBrojuJedinica(idRoba, idNUD, 300);
        
        idRoba = f.unesiRobu("Crep", "0005", idMaterijal);
        if(idRoba != -1) count++;
        f.unesiRobuUMagacinPoBrojuJedinica(idRoba, idMagacin1, 400);
       
        
        idRoba = f.unesiRobu("Armatura", "0006", idMaterijal);
        if(idRoba != -1) count++;
        f.unesiRobuUMagacinPoBrojuJedinica(idRoba, idMagacin2, 500);
        
        
        List<Integer> alat = new ArrayList<>();
        
        idRoba = f.unesiRobu("Busilica", "0007", idAlat);
        f.unesiRobuUMagacinPoBrojuJedinica(idRoba, idMagacin2, 10);
        alat.add(idRoba);
        
        idRoba = f.unesiRobu("Cekic", "0008", idAlat);
        f.unesiRobuUMagacinPoBrojuJedinica(idRoba, idMagacin2, 10);
        alat.add(idRoba);
        
        idRoba = f.unesiRobu("Elektricni odvijac", "0009", idAlat);
        f.unesiRobuUMagacinPoBrojuJedinica(idRoba, idMagacin2, 10);
        alat.add(idRoba);
        
        idRoba = f.unesiRobu("Kruzna testera", "0010", idAlat);
        f.unesiRobuUMagacinPoBrojuJedinica(idRoba, idMagacin2, 10);
        alat.add(idRoba);
        
        if( f.unesiRobuUMagacinPoKolicini(idRoba,idMagacin2, new BigDecimal(500)) == -1 &&
                f.pogledajBrojJedinicaRobeUMagacinu(idRoba, idMagacin2) == 10 &&
                    f.pogledajKolicinuRobeUMagacinu(idRoba, idMagacin2).compareTo(new BigDecimal(-1)) == 0 && 
                         count == 6){
            poena += 0.25;
        }
        
        
        List<Integer> htzOprema = new ArrayList<>();
        
        idRoba = f.unesiRobu("Rukavice", "0011", idHTZ);
        f.unesiRobuUMagacinPoBrojuJedinica(idRoba, idMagacin1, 50);
        htzOprema.add(idRoba);
        
        idRoba = f.unesiRobu("Naocare", "0012", idHTZ);
        f.unesiRobuUMagacinPoBrojuJedinica(idRoba, idMagacin1, 50);
        htzOprema.add(idRoba);
        
        idRoba = f.unesiRobu("Cipele", "0013", idHTZ);
        f.unesiRobuUMagacinPoBrojuJedinica(idRoba, idMagacin1, 50);
        htzOprema.add(idRoba);
        
        idRoba = f.unesiRobu("Stitnik za kolena", "0014", idHTZ);
        f.unesiRobuUMagacinPoBrojuJedinica(idRoba, idMagacin1, 50);
        htzOprema.add(idRoba);
        
        idRoba = f.unesiRobu("Kaciga", "0015", idHTZ);
        f.unesiRobuUMagacinPoBrojuJedinica(idRoba, idMagacin1, 50);
        htzOprema.add(idRoba);
        
        int unos =  f.unesiRobuUMagacinPoBrojuJedinica(idRoba, idMagacin2, 50);
        htzOprema.add(idRoba);
        
        
        f.uzmiRobuIzMagacinaPoBrojuJedinica(idRoba, idMagacin1, 50); 
        f.uzmiRobuIzMagacinaPoKolicini(idRoba1, idMagacin1, new BigDecimal(100));
        
        int size = f.dohvatiSvuRobu().size();
        if( 
          size ==  14 &&
            f.pogledajKolicinuRobeUMagacinu(idRoba, idMagacin1).compareTo(new BigDecimal(-1)) == 0 &&
                f.pogledajKolicinuRobeUMagacinu(idRoba1, idMagacin2).compareTo(new BigDecimal(100)) == 0 &&
                        f.pogledajBrojJedinicaRobeUMagacinu(idRoba1, idMagacin1) == -1 &&
                                f.pogledajBrojJedinicaRobeUMagacinu(idRoba, idMagacin2) == 50
                                        ){
            poena += 0.25;
        }
                               
        f.uzmiRobuIzMagacinaPoBrojuJedinica(idRoba, idMagacin2, 50); 
        f.uzmiRobuIzMagacinaPoKolicini(idRoba1, idMagacin2, new BigDecimal(100));
        f.obrisiRobu(idRoba);
        
        idRoba = f.unesiRobu("Cigla", "0018", idMaterijal);
        int noviUnos = f.unesiRobuUMagacinPoBrojuJedinica(idRoba, idMagacin2, 2);
        int treciUnos = f.unesiRobuUMagacinPoBrojuJedinica(idRoba, idMagacin2, 1);
        f.unesiRobuUMagacinPoBrojuJedinica(idRoba, idMagacin2, 1);
        f.unesiRobuUMagacinPoBrojuJedinica(idRoba, idMagacin2, 1);
        
        f.uzmiRobuIzMagacinaPoBrojuJedinica(idRoba, idMagacin2, 3);

        
        int flag = f.uzmiRobuIzMagacinaPoBrojuJedinica(idRoba, idMagacin2, 4);
        f.uzmiRobuIzMagacinaPoBrojuJedinica(idRoba, idMagacin2, 4);
        f.uzmiRobuIzMagacinaPoBrojuJedinica(idRoba, idMagacin2, 3);
        f.obrisiRobu(idRoba);
        
        if(
           unos != noviUnos &&
                /*noviUnos == treciUnos &&*/
                    flag == 2 &&
                        f.dohvatiSvuRobu().size() == 13 &&
                            f.pogledajBrojJedinicaRobeUMagacinu(idRoba, idMagacin2) == -1
                ){
            poena += 0.5;
        }
         
        f.zaposleniNeRadiUMagacinu(zaposleni1);
        f.zaposleniNeRadiUMagacinu(zaposleni2);

        f.zaposleniNeRadiUMagacinu(zaposleni4);
       
        
        
        trenutnoVreme = Date.valueOf("2017-12-13");
        Date pocetakPosla1 = trenutnoVreme;
        int idPosao1 = f.unesiPosao(idNUD, cs0, trenutnoVreme);
        
        f.obrisiPotrebanMaterijal(nr1k, idNUD);
        
       
        
        if(idPosao1 == -1){
            poena += 1;
        }
        
        nr1k = f.unesiRobu("Cementara", "0030", idMaterijal);
        f.unesiRobuUMagacinPoKolicini(nr1k, idMagacin2, new BigDecimal(200));
        f.unesiPotrebanMaterijalPoKolicini(nr1k, idNUD, new BigDecimal(200));
        idPosao1 = f.unesiPosao(idNUD, cs0, trenutnoVreme);
        
        if(f.pogledajKolicinuRobeUMagacinu(nr1k, idMagacin2).compareTo(new BigDecimal(-1)) == 0 &&
                f.pogledajBrojJedinicaRobeUMagacinu(nr1j, idMagacin2) == -1)
        {
            poena += 0.25;
        }
        
        int idZ2P1 = f.zaposleniRadiNaPoslu(zaposleni1, idPosao1, Date.valueOf("2017-12-14"));
        Date pocetakRadaZ2P1 = trenutnoVreme;
        trenutnoVreme = Date.valueOf("2016-12-15");
        int idZ3P1 = f.zaposleniRadiNaPoslu(zaposleni2, idPosao1, Date.valueOf("2017-12-16"));
        Date pocetakRadaZ3P1 = trenutnoVreme;
        
        if( f.izmeniDatumPocetkaRadaZaposlenogNaPoslu(idZ2P1, Date.valueOf("2017-12-12")) == 1
                && f.izmeniDatumPocetkaRadaZaposlenogNaPoslu(idZ2P1, Date.valueOf("2017-12-13")) == 0 
        ){
            poena += 0.25;
        }
        
        if( 
            f.zavrsiPosao(idPosao1, Date.valueOf("2017-12-18")) == 0 &&
                 f.zaposleniJeZavrsioSaRadomNaPoslu(idZ3P1,Date.valueOf("2017-12-13")) == 1 &&
                          f.zaposleniJeZavrsioSaRadomNaPoslu(idZ3P1,Date.valueOf("2017-12-15")) == 1 && 
                               f.zaposleniJeZavrsioSaRadomNaPoslu(idZ3P1,Date.valueOf("2017-12-16")) == 1){
            poena += 0.5;
        }  
        
        if(
          f.zaposleniRadiUMagacinu(zaposleni1, idMagacin1) != -1 &&
            f.zaposleniRadiUMagacinu(zaposleni2,idMagacin1 ) != -1 &&
               f.zaposleniRadiNaPoslu(zaposleni2, idPosao1, Date.valueOf("2017-12-17")) == -1){
                poena += 0.5;
        }
        
        if(f.dohvatiProsecnuOcenuZaZaposlenog(zaposleni1).compareTo(new BigDecimal(10)) == 0 &&
               f.dohvatiProsecnuOcenuZaZaposlenog(zaposleni2).compareTo(new BigDecimal(10)) == 0 &&
                    f.zaposleniDobijaOcenu(idZ3P1, 9) == idZ3P1 &&
                        f.dohvatiProsecnuOcenuZaZaposlenog(zaposleni2).compareTo(new BigDecimal(9)) == 0 &&
                            f.izmeniOcenuZaZaposlenogNaPoslu(idZ3P1, 8) == 0 &&
                                f.izmeniOcenuZaZaposlenogNaPoslu(54, 8) == 1 &&
                                    f.dohvatiProsecnuOcenuZaZaposlenog(zaposleni2).compareTo(new BigDecimal(8)) == 0 &&
                                        f.obrisiOcenuZaposlenom(idZ3P1) == 0 &&
                                            f.dohvatiProsecnuOcenuZaZaposlenog(zaposleni2).compareTo(new BigDecimal(10)) == 0
                ){
            poena += 1;
        }
        
        if(f.obrisiOcenuZaposlenom(idZ2P1) == 1){
            poena += 0.5;
        }
 
        f.zaposleniDobijaOcenu(idZ2P1, 7);
        f.zaposleniDobijaOcenu(idZ3P1, 8);
        

        
        
      
        f.zaposleniJeZavrsioSaRadomNaPoslu(idZ2P1, Date.valueOf("2017-12-18"));
        f.izmeniDatumKrajaRadaZaposlenogNaPoslu(idZ3P1,Date.valueOf("2017-12-20"));
        
        if(f.zavrsiPosao(idPosao1, Date.valueOf("2017-12-18")) == 1 &&
                f.zavrsiPosao(idPosao1, Date.valueOf("2017-12-19")) == 1 && 
                    f.zavrsiPosao(idPosao1, Date.valueOf("2017-12-20")) == 1     
                ){
            poena += 0.5;
        }
        
        if(f.pogledajKolicinuRobeUMagacinu(nr1k, idMagacin1).compareTo(new BigDecimal(-1)) == 0 &&
                f.pogledajBrojJedinicaRobeUMagacinu(nr1j, idMagacin2) == -1)
        {
            poena += 0.5;
        }
        
        f.obrisiPotrebanMaterijal(nr1k, idNUD);
        f.unesiPotrebanMaterijalPoKolicini(nr1k, idNUD, new BigDecimal(301));
        
        if(f.unesiPosao(idNUD, cs1, Date.valueOf("2017-12-22") ) == -1 &&
                f.izmeniDatumKrajaRadaZaposlenogNaPoslu(zaposleni1, Date.valueOf("2016-12-19")) == 1 &&
                    f.izmeniDatumKrajaRadaZaposlenogNaPoslu(zaposleni2, Date.valueOf("2016-12-19")) == 1 &&
                      f.izmeniDatumPocetkaRadaZaposlenogNaPoslu(zaposleni1, Date.valueOf("2016-12-15")) == 1 &&
                        f.izmeniDatumPocetkaRadaZaposlenogNaPoslu(zaposleni2, Date.valueOf("2016-12-15")) == 1 && 
                            f.izmeniDatumPocetkaZaPosao(idPosao1, Date.valueOf("2016-12-10")) == 1
                ){
            poena += 0.5;
        }
        
        
        List<Integer> zaduzenjaOpreme = new ArrayList<>();

        for(int i = 0; i < 50; i ++){
            trenutnoVreme = Date.valueOf("2016-06-10");
            int idZO = f.zaposleniZaduzujeOpremu(zaposleni1, idMagacin1, htzOprema.get(0), trenutnoVreme, "...");
            zaduzenjaOpreme.add(idZO);

            trenutnoVreme = Date.valueOf("2016-06-10");
            idZO = f.zaposleniZaduzujeOpremu(zaposleni1, idMagacin1, htzOprema.get(1), trenutnoVreme, "...");
            zaduzenjaOpreme.add(idZO);

            trenutnoVreme = Date.valueOf("2016-07-10");
            idZO = f.zaposleniZaduzujeOpremu(zaposleni1, idMagacin1, htzOprema.get(2), trenutnoVreme, "...");
            zaduzenjaOpreme.add(idZO);

            trenutnoVreme = Date.valueOf("2016-07-15");
            idZO = f.zaposleniZaduzujeOpremu(zaposleni1, idMagacin1, htzOprema.get(3), trenutnoVreme, "...");
            zaduzenjaOpreme.add(idZO);

            trenutnoVreme = Date.valueOf("2016-07-15");
            idZO = f.zaposleniZaduzujeOpremu(zaposleni1, idMagacin1, htzOprema.get(4), trenutnoVreme, "...");
            //zaduzenjaOpreme.add(idZO);
        }
        
        if(f.dohvatiBrojTrenutnoZaduzeneOpremeZaZaposlenog(zaposleni1) == 200 &&
            f.zaposleniZaduzujeOpremu(zaposleni1, idMagacin1, htzOprema.get(3), trenutnoVreme, "...") == -1 &&
            f.pogledajBrojJedinicaRobeUMagacinu(htzOprema.get(0), idMagacin1) == -1 &&
               f.pogledajBrojJedinicaRobeUMagacinu(htzOprema.get(1), idMagacin1) == -1 && 
                    f.pogledajBrojJedinicaRobeUMagacinu(htzOprema.get(2), idMagacin1) == -1 &&
                        f.pogledajBrojJedinicaRobeUMagacinu(htzOprema.get(3), idMagacin1) == -1 &&
                            f.pogledajBrojJedinicaRobeUMagacinu(htzOprema.get(4), idMagacin1) == -1 
           ){
            poena += 1;
        }
        
        if(
           f.zaposleniRazduzujeOpremu(zaduzenjaOpreme.get(zaduzenjaOpreme.size() - 1),Date.valueOf("2016-07-14")) == 1 && 
                   f.zaposleniRazduzujeOpremu(zaduzenjaOpreme.get(zaduzenjaOpreme.size() - 1), Date.valueOf("2016-07-15")) == 0  &&
                        f.dohvatiBrojTrenutnoZaduzeneOpremeZaZaposlenog(zaposleni1) == 199 &&
                            f.pogledajBrojJedinicaRobeUMagacinu(htzOprema.get(3), idMagacin1) == 1
                       
            ){
            poena += 0.5;
        }
        
        if( f.unesiTipRobe("HTz") == -1 &&
                f.unesiTipRobe("Alat") == -1 &&
                    f.unesiTipRobe("mAterijal") == -1 &&
                        f.unesiTipRobe("asdf") == -1 ){
            poena += 0.25;
        }
        
        
        return poena*10;
    }
}
