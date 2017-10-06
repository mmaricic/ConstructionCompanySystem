/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import funkcionalnosti.Funkcionalnosti;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import DB.DB;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stefan
 */
public class mm140457 extends Funkcionalnosti{

    @Override
    public int unesiGradiliste(String naziv, Date datumOsnivanja) {
        try {
            String sql = "Insert into Gradiliste(Naziv, DatumOsnivanja) values(?, ?)";
            PreparedStatement ps = DB.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, naziv);
            ps.setDate(2, datumOsnivanja);
            int res = ps.executeUpdate();
            
            if(res > 0){
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                return Integer.parseInt(rs.getString(1));
            }
            return -1;
            
        } catch (SQLException ex) {
           //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
            
    }

    @Override
    public int obrisiGradiliste(int idGradiliste) {
        try {
            String sql = "DELETE FROM Gradiliste WHERE IdGr = "+idGradiliste;
            Statement s = DB.connection.createStatement();
            int res = s.executeUpdate(sql);
            
            if(res > 0)
                return 0;
            return 1;
            
        } catch (SQLException ex) {
           //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
    }

    @Override
    public List<Integer> dohvatiSvaGradilista() {
        try {
            String sql = "Select IdGr from Gradiliste";
            Statement s = DB.connection.createStatement();
            ResultSet rs = s.executeQuery(sql);
            List<Integer> list = null;
            
              if(rs.next()){
                list = new ArrayList<>();
            
            do{
                list.add(rs.getInt(1));
            }while(rs.next());
              }
            
            return list;
            
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }

    @Override
    public int unesiObjekat(String naziv, int idGradiliste) {
        try {
            String sql = "Insert into Objekat(Naziv,IdGr) values(?,?)";
            PreparedStatement ps = DB.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, naziv);
            ps.setInt(2, idGradiliste);
            int res = ps.executeUpdate();
            
            if(res > 0){
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                return Integer.parseInt(rs.getString(1));
            }
            return -1;
            
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        
    }

    @Override
    public int obrisiObjekat(int idObjekat) {
         try {
            String sql = "DELETE FROM Objekat WHERE IdObj = "+idObjekat;
            Statement s = DB.connection.createStatement();
            int res = s.executeUpdate(sql);
            
            if(res > 0)
                return 0;
            return 1;
            
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
    }

    @Override
    public int unesiSprat(int brSprata, int idObjekat) {
       try {
            String sql = "Insert into Sprat(RedniBroj,IdObj) values(?,?)";
            PreparedStatement ps = DB.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, brSprata);
            ps.setInt(2, idObjekat);
            int res = ps.executeUpdate();
            
            if(res > 0){
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                return Integer.parseInt(rs.getString(1));
            }
            return -1;
            
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public int obrisiSprat(int idSprat) {
          try {
            String sql = "DELETE FROM Sprat WHERE IdSpr = "+idSprat;
            Statement s = DB.connection.createStatement();
            int res = s.executeUpdate(sql);
            
            if(res > 0)
                return 0;
            return 1;
            
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
    }

    @Override
    public int unesiZaposlenog(String ime, String prezime, String jmbg, String pol, String ziroRacun, String email, String brojTelefona) {
        try {
            String sql = "Insert into Zaposleni(Ime, Prezime, JMBG, Pol, ZiroRacun, Email, BrTelefona) values(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = DB.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, ime);
            ps.setString(2, prezime);
            ps.setString(3, jmbg);
            ps.setString(4, pol);
            ps.setString(5, ziroRacun);
            ps.setString(6, email);
            ps.setString(7, brojTelefona);
        
            int res = ps.executeUpdate();
            
            if(res > 0){
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                return Integer.parseInt(rs.getString(1));
            }
            return -1;
            
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public int obrisiZaposlenog(int idZaposleni) {
        try {
            String sql = "DELETE FROM Zaposleni WHERE IdZap = "+idZaposleni;
            Statement s = DB.connection.createStatement();
            int res = s.executeUpdate(sql);
            
            if(res > 0)
                return 0;
            return 1;
            
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
    }

    @Override
    public BigDecimal dohvatiUkupanIsplacenIznosZaZaposlenog(int idZaposleni) {
        try {
            String sql = "Select Zarada from Zaposleni where IdZap = "+idZaposleni;
            Statement s = DB.connection.createStatement();
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()){
                return rs.getBigDecimal(1);
            }
             return BigDecimal.valueOf(-1);
             
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return BigDecimal.valueOf(-1);
        }
    }

    @Override
    public BigDecimal dohvatiProsecnuOcenuZaZaposlenog(int idZaposleni) {
 try {
            String sql = "Select Prosek from Zaposleni where IdZap = "+idZaposleni;
            Statement s = DB.connection.createStatement();
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()){
                return rs.getBigDecimal(1);
            }
             return BigDecimal.valueOf(-1);
             
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return BigDecimal.valueOf(-1);
        }    }

    @Override
    public int dohvatiBrojTrenutnoZaduzeneOpremeZaZaposlenog(int idZaposleni) {
        try {
            String sql = "Select ZaduzenoOpreme from Zaposleni where IdZap = "+idZaposleni;
            Statement s = DB.connection.createStatement();
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()){
                return rs.getInt(1);
            }
             return -1;
             
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public List<Integer> dohvatiSveZaposlene() {
        try {
            String sql = "Select IdZap from Zaposleni";
            Statement s = DB.connection.createStatement();
            ResultSet rs = s.executeQuery(sql);
            List<Integer> list = null;
            
            if(rs.next()){
                list = new ArrayList<>();
            
            do{
                list.add(rs.getInt(1));
            }while(rs.next());
            }
            
            return list;
            
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }    }

    @Override
    public int unesiMagacin(int idSef, BigDecimal plata, int idGradiliste) {
       boolean magins = false;
       int IdMag = 0;
        try {   
           
            String sql = "Insert into Magacin(Plata, IdGr) values(?, ?)";
            PreparedStatement ps = DB.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setBigDecimal(1, plata);
            ps.setInt(2, idGradiliste);
            int res = ps.executeUpdate();
            
            if(res > 0){
                magins = true;
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                IdMag = Integer.parseInt(rs.getString(1));
                sql = "insert into Je_Zaposlen(IdZap, JeSef, IdMag) values (?, 1, ?)";
                ps = DB.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setInt(1, idSef);
                ps.setInt(2, IdMag);
                res = ps.executeUpdate();
                if(res > 0){
                    return IdMag;
                }
                
            }
            return -1;
            
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            if(magins == true){
                try {
                    String sql = "DELETE FROM Magacin WHERE IdMag = "+IdMag;
                    Statement s = DB.connection.createStatement();
                    s.executeUpdate(sql);
                } catch (SQLException ex1) {
                    Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
            return -1;
        }
    }

    @Override
    public int obrisiMagacin(int idMagacin) {
        try{
            String sql = "DELETE FROM Magacin WHERE IdMag = "+idMagacin;
            Statement s = DB.connection.createStatement();
            int res = s.executeUpdate(sql);
            
            if(res > 0)
                return 0;
            return 1;
            
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }    }

    @Override
    public int izmeniSefaZaMagacin(int idMagacin, int idSefNovo) {
        boolean insnew = false;
        boolean delold = false;
         try {   
             String sql = "Select IdZap from Je_Zaposlen where JeSef = 1 and IdMag = "+idMagacin;
             Statement stm = DB.connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next()){
                if(rs.getInt(1) == idSefNovo)
                    return 0;
            }
            sql = "insert into Je_Zaposlen(IdZap, JeSef, IdMag) values (?, 0, ?)";
            PreparedStatement ps = DB.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idSefNovo);
            ps.setInt(2, idMagacin);
            int res = ps.executeUpdate();
                if(res > 0){
                    insnew = true;
                    sql = "delete from Je_Zaposlen where IdMag = "+idMagacin+" and JeSef = 1";
                    Statement s = DB.connection.createStatement();
                    s.executeUpdate(sql);
                    delold = true;
                    sql = "update Je_Zaposlen set JeSef = 1 where IdZap = "+idSefNovo;
                    s.executeUpdate(sql);
                    return 0;
                }
                 return 1;
            } catch (SQLException ex) {
                if(delold == true)
                    System.out.println("u kurcu si");
                if(insnew == true){
                     try {
                    String sql = "DELETE FROM Je_Zaposlen WHERE IdZap = "+idSefNovo;
                    Statement s = DB.connection.createStatement();
                    s.executeUpdate(sql);
                } catch (SQLException ex1) {
                    Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex1);
                }
                }
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
    }

    @Override
    public int izmeniPlatuZaMagacin(int idMagacin, BigDecimal plataNovo) {
        try {
            String sql = "update Magacin set Plata = "+plataNovo+" where IdMag = "+idMagacin;
            Statement s = DB.connection.createStatement();
            int res = s.executeUpdate(sql);
            if(res > 0)
                return 0;
            return 1;
        } catch (SQLException ex) {
            
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
    }

    @Override
    public int isplatiPlateZaposlenimaUSvimMagacinima() {
        try {
            String sql = "Select IdMag from Magacin";
            Statement s = DB.connection.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                int idMag = rs.getInt(1);
                sql = "{call SPIsplatiMagacinPlatu("+idMag+")}";
                CallableStatement cs =  DB.connection.prepareCall(sql);
                cs.execute();
            }
            return 0;
        } catch (SQLException ex) {
           
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
             return 1;
        }
    }

    @Override
    public int isplatiPlateZaposlenimaUMagacinu(int idMagacin) {
        
        try {
            String sql = "{call SPIsplatiMagacinPlatu("+idMagacin+")}";
            CallableStatement cs =  DB.connection.prepareCall(sql);
            cs.execute();
            return 0;
        } catch (SQLException ex) {
             //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
           
        }
    }

    @Override
    public int unesiRobuUMagacinPoKolicini(int idRoba, int idMagacin, BigDecimal kolicina) {
        try { 
            String sql = "Select IdUmag, JedMere, Kol_Jed from U_Magacinu where IdMag = "+idMagacin+" and IdR = "+idRoba;
            Statement stm = DB.connection.createStatement();
            ResultSet rstest = stm.executeQuery(sql);
            if(rstest.next()){
                 char[] cbuf = new char[1];
                 rstest.getCharacterStream("JedMere").read(cbuf);
                char type = cbuf[0];
                if(type != 'K')
                    return -1;
                int id = rstest.getInt(1);
                BigDecimal ammount = rstest.getBigDecimal(3).add(kolicina);
                sql = "Update U_Magacinu set Kol_Jed = "+ammount+" where IdUmag = "+id;
                stm.executeUpdate(sql);
                return id;
            }
            sql = "Insert into U_Magacinu(IdR, IdMag, Kol_Jed, JedMere) values(?, ?, ?, 'K')";
            PreparedStatement ps = DB.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idRoba);
            ps.setInt(2, idMagacin);
            ps.setBigDecimal(3, kolicina);
            int res = ps.executeUpdate();
            
            if(res > 0){
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                return Integer.parseInt(rs.getString(1));
            }
            return -1;
            
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } catch (IOException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public int unesiRobuUMagacinPoBrojuJedinica(int idRoba, int idMagacin, int brojJedinica) {
        try {
            String sql = "Select IdUmag, JedMere, Kol_Jed from U_Magacinu where IdMag = "+idMagacin+" and IdR = "+idRoba;
            Statement stm = DB.connection.createStatement();
            ResultSet rstest = stm.executeQuery(sql);
            if(rstest.next()){
                char[] cbuf = new char[1];
                 rstest.getCharacterStream("JedMere").read(cbuf);
                char type = cbuf[0];
                if(type != 'J')
                    return -1;
                int id = rstest.getInt(1);
                BigDecimal ammount = rstest.getBigDecimal(3).add(new BigDecimal(brojJedinica));
                sql = "Update U_Magacinu set Kol_Jed = "+ammount+" where IdUmag = "+id;
                stm.executeUpdate(sql);
                return id;
            }
            sql = "Insert into U_Magacinu(IdR, IdMag, Kol_Jed, JedMere) values(?, ?, ?, 'J')";
            PreparedStatement ps = DB.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idRoba);
            ps.setInt(2, idMagacin);
            ps.setBigDecimal(3, BigDecimal.valueOf(brojJedinica));
            int res = ps.executeUpdate();
            
            if(res > 0){
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                return Integer.parseInt(rs.getString(1));
            }
            return -1;
            
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } catch (IOException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public BigDecimal uzmiRobuIzMagacinaPoKolicini(int idRoba, int idMagacin, BigDecimal kolicina) {
          try {
            String sql = "{? = call validnaJedinicaMere("+idMagacin+","+idRoba+",'K')}";
            CallableStatement cs =  DB.connection.prepareCall(sql);
            cs.registerOutParameter(1, java.sql.Types.INTEGER);
            cs.execute();
            int res = cs.getInt(1);
            if(res == -1)
                return BigDecimal.valueOf(res);
            
            sql = "{? = call SPSmanjiRobu(?, ?, ?)}";
            cs =  DB.connection.prepareCall(sql);
            cs.setInt(2, idMagacin);
            cs.setInt(3, idRoba);
            cs.setBigDecimal(4, kolicina);
            cs.registerOutParameter(1, java.sql.Types.DECIMAL);
            cs.execute();
            BigDecimal out = cs.getBigDecimal(1);
            return out;
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return BigDecimal.valueOf(-1);
        }
    }

    @Override
    public int uzmiRobuIzMagacinaPoBrojuJedinica(int idRoba, int idMagacin, int brojJedinca) {
          try {
            String sql = "{? = call validnaJedinicaMere("+idMagacin+","+idRoba+",'J')}";
            CallableStatement cs =  DB.connection.prepareCall(sql);
            cs.registerOutParameter(1, java.sql.Types.INTEGER);
            cs.execute();
            int res = cs.getInt(1);
            if(res == -1)
                return res;
            
            sql = "{? = call SPSmanjiRobu(?, ?, ?)}";
            cs =  DB.connection.prepareCall(sql);
            cs.setInt(2, idMagacin);
            cs.setInt(3, idRoba);
            cs.setInt(4, brojJedinca);
            cs.registerOutParameter(1, java.sql.Types.INTEGER);
            cs.execute();
            int out = cs.getInt(1);
            return out;
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
           return -1;
        }
    }

    @Override
    public BigDecimal pogledajKolicinuRobeUMagacinu(int idRoba, int idMagacin) {
        try {
            String sql = "Select Kol_Jed from U_Magacinu where IdR = ? and IdMag = ? and JedMere = 'K'";
            PreparedStatement ps = DB.connection.prepareStatement(sql);
            ps.setInt(1, idRoba);
            ps.setInt(2, idMagacin);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getBigDecimal(1);
            }
             return BigDecimal.valueOf(-1);
             
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return BigDecimal.valueOf(-1);
        }
    }

    @Override
    public int pogledajBrojJedinicaRobeUMagacinu(int idRoba, int idMagacin) {
          try {
            String sql = "Select Kol_Jed from U_Magacinu where IdR = ? and IdMag = ? and JedMere = 'J'";
            PreparedStatement ps = DB.connection.prepareStatement(sql);
            ps.setInt(1, idRoba);
            ps.setInt(2, idMagacin);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int res = rs.getBigDecimal(1).intValue();
                return res;
            }
             return -1;
             
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public int unesiTipRobe(String naziv) {
 try {
            //if((naziv.toLowerCase().equals("alat")) ||(naziv.toLowerCase().equals("materijal")) || (naziv.toLowerCase().equals("htz"))){
            String sql = "Insert into Tip(Naziv) values(?)";
            PreparedStatement ps = DB.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, naziv);
            int res = ps.executeUpdate();
            
            if(res > 0){
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                return Integer.parseInt(rs.getString(1));
            }
           // }
            return -1;
            
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }    }

    @Override
    public int obrisiTipRobe(int idTipRobe) {
        try {
            String sql = "DELETE FROM Tip WHERE IdTip = "+idTipRobe;
            Statement s = DB.connection.createStatement();
            int res = s.executeUpdate(sql);
            
            if(res > 0)
                return 0;
            return 1;
            
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }    }

    @Override
    public int unesiRobu(String naziv, String kod, int idTipRobe) {
        try {
            String sql = "Insert into Roba(Naziv, Kod, IdTip) values(?, ?, ?)";
            PreparedStatement ps = DB.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, naziv);
            ps.setString(2, kod);
            ps.setInt(3, idTipRobe);
            int res = ps.executeUpdate();
            
            if(res > 0){
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                return Integer.parseInt(rs.getString(1));
            }
            return -1;
            
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }    }

    @Override
    public int obrisiRobu(int idRoba) {
        try {
            String sql = "DELETE FROM Roba WHERE IdR = "+idRoba;
            Statement s = DB.connection.createStatement();
            int res = s.executeUpdate(sql);
            
            if(res > 0)
                return 0;
            return 1;
            
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }    }

    @Override
    public List<Integer> dohvatiSvuRobu() {
        try {
            String sql = "Select IdR from Roba";
            Statement s = DB.connection.createStatement();
            ResultSet rs = s.executeQuery(sql);
            List<Integer> list = null;
            
              if(rs.next()){
                list = new ArrayList<>();
            
                do{
                    list.add(rs.getInt(1));
                }while(rs.next());
              }
            
            return list;
            
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }    }

    @Override
    public int zaposleniRadiUMagacinu(int idZaposleni, int idMagacin) {
        try {
            String sql = "Insert into Je_Zaposlen(IdZap, IdMag, JeSef) values(?, ?, 0)";
            PreparedStatement ps = DB.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idZaposleni);
            ps.setInt(2, idMagacin);
            int res = ps.executeUpdate();
            
            if(res > 0)
                return idZaposleni;
            
            return -1;
            
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }    }

    @Override
    public int zaposleniNeRadiUMagacinu(int idZaposleni) {
         try {
            String sql = "DELETE FROM Je_Zaposlen WHERE IdZap = "+idZaposleni;
            Statement s = DB.connection.createStatement();
            int res = s.executeUpdate(sql);
            
            if(res > 0)
                return 0;
            return 1;
            
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
    }

    @Override
    public int zaposleniZaduzujeOpremu(int idZaposlenogKojiZaduzuje, int idMagacin, int idRoba, Date datumZaduzenja, String napomena) {
        try {
            String sql = "Insert into Zaduzenje_Opreme(IdZap, IdMag, IdR, DatumZaduzenja, Napomena) values(?, ?, ?, ?, ?)";
            PreparedStatement ps = DB.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idZaposlenogKojiZaduzuje);
            ps.setInt(2, idMagacin);
            ps.setInt(3, idRoba);
            ps.setDate(4, datumZaduzenja);
            ps.setString(5, napomena);

            int res = ps.executeUpdate();
            
            if(res > 0){
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                return Integer.parseInt(rs.getString(1));
            }
            return -1;
            
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public int zaposleniRazduzujeOpremu(int idZaduzenjaOpreme, Date datumRazduzenja) {
        try {
            String sql = "update Zaduzenje_Opreme set DatumRazduzenja = '"+datumRazduzenja+"' where IdZaduz = "+idZaduzenjaOpreme;
            Statement s = DB.connection.createStatement();
            int res = s.executeUpdate(sql);
            if(res > 0)
                return 0;
            return 1;
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
            
        }    }

    @Override
    public int unesiNormuUgradnogDela(String naziv, BigDecimal cenaIzrade, BigDecimal jedinicnaPlataRadnika) {
        try {
            String sql = "Insert into Norma(Naziv, CenaIzrade, PlataRadnika) values(?, ?, ?)";
            PreparedStatement ps = DB.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, naziv);
            ps.setBigDecimal(2, cenaIzrade);
            ps.setBigDecimal(3, jedinicnaPlataRadnika);
            int res = ps.executeUpdate();
            
            if(res > 0){
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                return Integer.parseInt(rs.getString(1));
            }
            return -1;
            
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }    }

    @Override
    public int obrisiNormuUgradnogDela(int idNormaUgradnogDela) {
        try {
            String sql = "DELETE FROM Norma WHERE IdNor = "+idNormaUgradnogDela;
            Statement s = DB.connection.createStatement();
            int res = s.executeUpdate(sql);
            
            if(res > 0)
                return 0;
            return 1;
            
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }}

    @Override
    public BigDecimal dohvatiJedinicnuPlatuRadnikaNormeUgradnogDela(int idNR) {
        try {
            String sql = "Select PlataRadnika from Norma where IdNor = "+idNR;
            Statement s = DB.connection.createStatement();
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()){
                return rs.getBigDecimal(1);
            }
             return BigDecimal.valueOf(-1);
             
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return BigDecimal.valueOf(-1);
        }    }

    @Override
    public int unesiPotrebanMaterijalPoBrojuJedinica(int idRobaKojaJePotrosniMaterijal, int idNormaUgradnogDela, int brojJedinica) {
         try {
            String sql = "Insert into Potrebna_Roba(IdR, IdNor, Kol_Jed, JedMere) values(?, ?, ?, 'J')";
            PreparedStatement ps = DB.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idRobaKojaJePotrosniMaterijal);
            ps.setInt(2, idNormaUgradnogDela);
            ps.setBigDecimal(3, BigDecimal.valueOf(brojJedinica));
            int res = ps.executeUpdate();
            
            if(res > 0){
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                return Integer.parseInt(rs.getString(1));
            }
            return -1;
            
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public int unesiPotrebanMaterijalPoKolicini(int idRobaKojaJePotrosniMaterijal, int idNormaUgradnogDela, BigDecimal kolicina) {
        try {
            String sql = "Insert into Potrebna_Roba(IdR, IdNor, Kol_Jed, JedMere) values(?, ?, ?, 'K')";
            PreparedStatement ps = DB.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idRobaKojaJePotrosniMaterijal);
            ps.setInt(2, idNormaUgradnogDela);
            ps.setBigDecimal(3, kolicina);
            int res = ps.executeUpdate();
            
            if(res > 0){
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                return Integer.parseInt(rs.getString(1));
            }
            return -1;
            
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public int obrisiPotrebanMaterijal(int idRobaKojaJePotrosniMaterijal, int idNormaUgradnogDela) {
         try {
            String sql = "DELETE FROM Potrebna_Roba WHERE IdR = ? and IdNor = ?";
            PreparedStatement ps = DB.connection.prepareStatement(sql);
            ps.setInt(1, idRobaKojaJePotrosniMaterijal);
            ps.setInt(2, idNormaUgradnogDela);
            int res = ps.executeUpdate();
            
            if(res > 0)
                return 0;
            return 1;
            
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
    }

    @Override
    public int unesiPosao(int idNormaUgradnogDela, int idSprat, Date datumPocetka) {
        try {
            String sql = "Insert into Posao(DatumPocetka, IdNor, IdSpr) values(?, ?, ?)";
            PreparedStatement ps = DB.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setDate(1, datumPocetka);
            ps.setInt(2, idNormaUgradnogDela);
            ps.setInt(3, idSprat);
            int res = ps.executeUpdate();
            
            if(res > 0){
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                return Integer.parseInt(rs.getString(1));
            }
            return -1;
            
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }}

    @Override
    public int obrisiPosao(int idPosao) {
        try {
            String sql = "DELETE FROM Posao WHERE IdPos = "+idPosao;
            Statement s = DB.connection.createStatement();
            int res = s.executeUpdate(sql);
            
            if(res > 0)
                return 0;
            return 1;
            
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
    }

    @Override
    public int izmeniDatumPocetkaZaPosao(int idPosao, Date datumPocetka) {
         try {
            String sql = "update Posao set DatumPocetka = '"+datumPocetka+"' where IdPos = "+idPosao;
            Statement s = DB.connection.createStatement();
            int res = s.executeUpdate(sql);
            if(res > 0)
                return 0;
            return 1;
        } catch (SQLException ex) {
            
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
    }

    @Override
    public int zavrsiPosao(int idPosao, Date datumKraja) {
          try {
            String sql = "update Posao set DatumKraja = '"+datumKraja+"' where IdPos = "+idPosao;
            Statement s = DB.connection.createStatement();
            int res = s.executeUpdate(sql);
            if(res > 0)
                return 0;
            return 1;
        } catch (SQLException ex) {
           
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
             return 1;
        }
    }

    @Override
    public int zaposleniRadiNaPoslu(int idZaposleni, int idPosao, Date datumPocetka) {
          try {
            String sql = "Insert into Radi_Posao(IdZap, IdPos, DatumPocetka) values(?, ?, ?)";
            PreparedStatement ps = DB.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idZaposleni);
            ps.setInt(2, idPosao);
            ps.setDate(3, datumPocetka);
            int res = ps.executeUpdate();
            
            if(res > 0){
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                return Integer.parseInt(rs.getString(1));
            }
            return -1;
            
        } catch (SQLException ex) {
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public int zaposleniJeZavrsioSaRadomNaPoslu(int idZaposleniNaPoslu, Date datumKraja) {
          try {
            String sql = "update Radi_Posao set DatumKraja = '"+datumKraja+"' where IdRadiPos = "+idZaposleniNaPoslu;
            Statement s = DB.connection.createStatement();
            int res = s.executeUpdate(sql);
            if(res > 0)
                return 0;
            return 1;
        } catch (SQLException ex) {
            
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
    }

    @Override
    public int izmeniDatumPocetkaRadaZaposlenogNaPoslu(int idZaposleniNaPoslu, Date datumPocetkaNovo) {
         try {
            String sql = "update Radi_Posao set DatumPocetka = '"+datumPocetkaNovo+"' where IdRadiPos = "+idZaposleniNaPoslu;
            Statement s = DB.connection.createStatement();
            int res = s.executeUpdate(sql);
            if(res > 0)
                return 0;
            return 1;
        } catch (SQLException ex) {
            
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
    }

    @Override
    public int izmeniDatumKrajaRadaZaposlenogNaPoslu(int idZaposleniNaPoslu, Date datumKrajaNovo) {
         try {
            String sql = "update Radi_Posao set DatumKraja = '"+datumKrajaNovo+"' where IdRadiPos = "+idZaposleniNaPoslu;
            Statement s = DB.connection.createStatement();
            int res = s.executeUpdate(sql);
            if(res > 0)
                return 0;
            return 1;
        } catch (SQLException ex) {
            
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
    }

    @Override
    public int zaposleniDobijaOcenu(int idZaposleniNaPoslu, int ocena) {
       try {
           String sql = "Select Ocena from Radi_Posao where Ocena is null and IdRadiPos = "+idZaposleniNaPoslu;
            Statement s = DB.connection.createStatement();
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()){
            sql = "update Radi_Posao set Ocena = "+ocena+" where IdRadiPos = "+idZaposleniNaPoslu;
            int res = s.executeUpdate(sql);
            if(res > 0)
                return idZaposleniNaPoslu;
            }
            return -1;
        } catch (SQLException ex) {
            
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public int obrisiOcenuZaposlenom(int idZaposleniNaPoslu) {
         try {
            String sql = "update Radi_Posao set Ocena = NULL where IdRadiPos = "+idZaposleniNaPoslu/*+"and Ocena is not NULL"*/;
            Statement s = DB.connection.createStatement();
            int res = s.executeUpdate(sql);
            if(res > 0)
                return 0;
            return 1;
        } catch (SQLException ex) {
            
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
    }

    @Override
    public int izmeniOcenuZaZaposlenogNaPoslu(int idZaposleniNaPoslu, int ocenaNovo) {
         try {
            String sql = "update Radi_Posao set Ocena = "+ocenaNovo+" where IdRadiPos = "+idZaposleniNaPoslu;
            Statement s = DB.connection.createStatement();
            int res = s.executeUpdate(sql);
            if(res > 0)
                return 0;
            return 1;
        } catch (SQLException ex) {
            
            //Logger.getLogger(mm140457.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
    }
    
}
