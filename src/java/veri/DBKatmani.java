/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package veri;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Arrays;

public class DBKatmani {
    
    private Connection conn;
    String dburl = "jdbc:mysql://localhost:3306/universite?useTimezone=true&serverTimezone=Europe/Istanbul";
    String user = "root";
    String pass = "Veritabani123";
    
    public Connection baglan(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println(dburl + user + pass);
            conn = DriverManager.getConnection(dburl, user, pass);
            System.out.println("baglanti basarili");
        } catch(Exception e) {
            System.out.println("baglantida sorun var");
        }
        return conn;
    }
    
    public String getOgrenciGiris(String email) {
        String girisBilgi="";
       
        if (conn == null) {
            System.out.println("Veritabanı bağlı değil, bağlanıyorum");
            baglan();
        }
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT sifre FROM ogrenci WHERE email ='"+email+"'");
            if (rs.next()) {
                girisBilgi=rs.getString("sifre");  
            }
        } catch(Exception e) {
           System.out.println("Veritabanı bağlantısında sorun var: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println(girisBilgi);

        return girisBilgi;
    }
    
    public String getOgrenciIsim(String email) {
        String isim="";
       
        if (conn == null) {
            System.out.println("Veritabanı bağlı değil, bağlanıyorum");
            baglan();
        }

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT ogrenciAdi FROM ogrenci WHERE email ='"+email+"'");
            if (rs.next()) {
                isim=rs.getString("ogrenciAdi");             
            }
        } catch(Exception e) {
           System.out.println("Veritabanı bağlantısında sorun var: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println(isim);

        return isim;
    }
    
    public String getOgretmenGiris(String email) {
        String girisBilgi="";
       
        if (conn == null) {
            System.out.println("Veritabanı bağlı değil, bağlanıyorum");
            baglan();
        }
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT sifre FROM ogretmen WHERE email ='"+email+"'");
            if (rs.next()) {
                girisBilgi=rs.getString("sifre");  
            }
        } catch(Exception e) {
           System.out.println("Veritabanı bağlantısında sorun var: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println(girisBilgi);

        return girisBilgi;
    }
    
    public String getOgretmenIsim(String email) {
        String isim="";
       
        if (conn == null) {
            System.out.println("Veritabanı bağlı değil, bağlanıyorum");
            baglan();
        }

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT ogretmenAdi FROM ogretmen WHERE email ='"+email+"'");
            if (rs.next()) {
                isim=rs.getString("ogretmenAdi");             
            }
        } catch(Exception e) {
           System.out.println("Veritabanı bağlantısında sorun var: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println(isim);

        return isim;
    }
    
    public String[] getOgrenciProfil(String email) {
        
        if (conn == null) {
            System.out.println("Veritabanı bağlı değil, bağlanıyorum");
            baglan();
        }

        try {
            Statement stmt = conn.createStatement();
            System.out.println(email);
            ResultSet rs = stmt.executeQuery("{ CALL OgrenciBilgiler('"+email+"') }");

            String[] bilgiler = new String[8]; 

            rs.beforeFirst();

            while (rs.next()) {
                bilgiler[0] = rs.getString(1); // numara
                bilgiler[1] = rs.getString(2); // isim
                bilgiler[2] = rs.getString(3); // fakülte
                bilgiler[3] = rs.getString(4); // bölüm
                bilgiler[4] = rs.getString(5); // emial
                bilgiler[5] = rs.getString(6); // numara
                bilgiler[6] = rs.getString(7); // adres
                bilgiler[7] = rs.getString(8); // doenem
            }

            return bilgiler;
            
        } catch(Exception e) {
            System.out.println("Veritabanı bağlantısında sorun var: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    public String[] getOgretmenProfil(String email) {
        
        if (conn == null) {
            System.out.println("Veritabanı bağlı değil, bağlanıyorum");
            baglan();
        }

        try {
            Statement stmt = conn.createStatement();
            System.out.println(email);
            ResultSet rs = stmt.executeQuery("{ CALL OgretmenBilgiler('"+email+"') }");

            String[] bilgiler = new String[7]; 

            rs.beforeFirst();

            while (rs.next()) {
                bilgiler[0] = rs.getString(1); // unvan
                bilgiler[1] = rs.getString(2); // isim
                bilgiler[2] = rs.getString(3); // fakülte
                bilgiler[3] = rs.getString(4); // bölüm
                bilgiler[4] = rs.getString(5); // emial
                bilgiler[5] = rs.getString(6); // numara
                bilgiler[6] = rs.getString(7); // adres
            }

            return bilgiler;
            
        } catch(Exception e) {
            System.out.println("Veritabanı bağlantısında sorun var: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    public String[] getDonem(String email) {
        
        if (conn == null) {
            System.out.println("Veritabanı bağlı değil, bağlanıyorum");
            baglan();
        }

        try {
            Statement stmt = conn.createStatement();
            System.out.println(email);
            ResultSet rs = stmt.executeQuery("{ CALL OgrenciDonemListele('"+email+"') }");
            // Veritabanındaki kayıt sayısını bulmak için önce sonuca bir kez taşınmalıyız.
            rs.last();
            int rowCount = rs.getRow();
            
            // Matrisi oluşturuyoruz
            String[] donemler = new String[rowCount];

            // ResultSet başa alınır
            rs.beforeFirst();
            
            int rowIndex = 0;
            while (rs.next()) {
                donemler[rowIndex] = rs.getString(1); // donem adi
                rowIndex++;
            }       
            return donemler;
            
        } catch(Exception e) {
            System.out.println("Veritabanı bağlantısında sorun var: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    public String[][] getAlinanDersler(String email, String donem) {
        
        if (conn == null) {
            System.out.println("Veritabanı bağlı değil, bağlanıyorum");
            baglan();
        }

        try {
            String query = "{ CALL OgrenciDersBilgileri(?, ?) }";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, donem);
            ResultSet rs = pstmt.executeQuery();

            // Veritabanındaki kayıt sayısını bulmak için önce sonuca bir kez taşınmalıyız.
            rs.last();
            int rowCount = rs.getRow();
            
            // Matrisi oluşturuyoruz
            String[][] dersler = new String[rowCount][5];

            // ResultSet başa alınır
            rs.beforeFirst();
            
            int rowIndex = 0;
            while (rs.next()) {
                dersler[rowIndex][0] = rs.getString(1); // ders kodu
                dersler[rowIndex][1] = rs.getString(2); // ders adi
                dersler[rowIndex][2] = rs.getString(3); // ders hocasi
                dersler[rowIndex][3] = rs.getString(4); // kredi
                dersler[rowIndex][4] = rs.getString(5); // akts
                rowIndex++;
            }
             for (int i = 0; i < dersler.length; i++) {
            for (int j = 0; j < dersler[i].length; j++) {
                System.out.print(dersler[i][j] + " ");
            }
            System.out.println(); // Yeni satıra geç
        }
            return dersler;
            
        } catch(Exception e) {
            System.out.println("Veritabanı bağlantısında sorun var: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    public String getSonDonem() {
        
        if (conn == null) {
            System.out.println("Veritabanı bağlı değil, bağlanıyorum");
            baglan();
        }

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("{ CALL SonDonem() }");
            String sonDonem="";
            while (rs.next()) {
                sonDonem = rs.getString(1);               
            }
            System.out.println(sonDonem);
            return sonDonem;
            
        } catch(Exception e) {
            System.out.println("Veritabanı bağlantısında sorun var: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    public String[][] getDersNotlari(String email, String donem) {
        
        if (conn == null) {
            System.out.println("Veritabanı bağlı değil, bağlanıyorum");
            baglan();
        }

        try {
            String query = "{ CALL DersSinavNotlar(?, ?) }";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, donem);
            ResultSet rs = pstmt.executeQuery();

            // Veritabanındaki kayıt sayısını bulmak için önce sonuca bir kez taşınmalıyız.
            rs.last();
            int rowCount = rs.getRow();
            
            // Matrisi oluşturuyoruz
            String[][] dersNot = new String[rowCount][7];

            // ResultSet başa alınır
            rs.beforeFirst();
            
            int rowIndex = 0;
            while (rs.next()) {
                dersNot[rowIndex][0] = rs.getString(1); // ders kodu
                dersNot[rowIndex][1] = rs.getString(2); // vize
                dersNot[rowIndex][2] = rs.getString(3); // final
                dersNot[rowIndex][3] = rs.getString(4); // but
                dersNot[rowIndex][4] = rs.getString(5); // ortalama
                dersNot[rowIndex][5] = rs.getString(6); // haf not
                dersNot[rowIndex][6] = rs.getString(7); // durum
                rowIndex++;
            }
             for (int i = 0; i < dersNot.length; i++) {
            for (int j = 0; j < dersNot[i].length; j++) {
                System.out.print(dersNot[i][j] + " ");
            }
            System.out.println(); // Yeni satıra geç
        }
            return dersNot;
            
        } catch(Exception e) {
            System.out.println("Veritabanı bağlantısında sorun var: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    public String[][] getOgretmenDersler(String email, String donem) {
        
        if (conn == null) {
            System.out.println("Veritabanı bağlı değil, bağlanıyorum");
            baglan();
        }

        try {
            String query = "{ CALL OgretmenGirilenDersler(?, ?) }";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, donem);
            ResultSet rs = pstmt.executeQuery();

            // Veritabanındaki kayıt sayısını bulmak için önce sonuca bir kez taşınmalıyız.
            rs.last();
            int rowCount = rs.getRow();
            
            // Matrisi oluşturuyoruz
            String[][] dersler = new String[rowCount][3];

            // ResultSet başa alınır
            rs.beforeFirst();
            
            int rowIndex = 0;
            while (rs.next()) {
                dersler[rowIndex][0] = rs.getString(1); // ders kodu
                dersler[rowIndex][1] = rs.getString(2); // ders adi
                dersler[rowIndex][2] = rs.getString(3); // ogrenci sayisi
                rowIndex++;
            }
             for (int i = 0; i < dersler.length; i++) {
            for (int j = 0; j < dersler[i].length; j++) {
                System.out.print(dersler[i][j] + " ");
            }
            System.out.println(); // Yeni satıra geç
        }
            return dersler;
            
        } catch(Exception e) {
            System.out.println("Veritabanı bağlantısında sorun var: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    public String[] getOgretmenDonem(String email) {
        
        if (conn == null) {
            System.out.println("Veritabanı bağlı değil, bağlanıyorum");
            baglan();
        }

        try {
            Statement stmt = conn.createStatement();
            System.out.println(email);
            ResultSet rs = stmt.executeQuery("{ CALL OgretmenDonemListele('"+email+"') }");
            // Veritabanındaki kayıt sayısını bulmak için önce sonuca bir kez taşınmalıyız.
            rs.last();
            int rowCount = rs.getRow();
            
            // Matrisi oluşturuyoruz
            String[] donemler = new String[rowCount];

            // ResultSet başa alınır
            rs.beforeFirst();
            
            int rowIndex = 0;
            while (rs.next()) {
                donemler[rowIndex] = rs.getString(1); // donem adi
                rowIndex++;
            }       
            return donemler;
            
        } catch(Exception e) {
            System.out.println("Veritabanı bağlantısında sorun var: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    public String[][] getOgretmenDersİsim(String email, String donem) {
        
        if (conn == null) {
            System.out.println("Veritabanı bağlı değil, bağlanıyorum");
            baglan();
        }

        try {
            String query = "{ CALL OgretmenDersAdlari(?, ?) }";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, donem);
            ResultSet rs = pstmt.executeQuery();

            // Veritabanındaki kayıt sayısını bulmak için önce sonuca bir kez taşınmalıyız.
            rs.last();
            int rowCount = rs.getRow();
            
            // Matrisi oluşturuyoruz
            String[][] dersler = new String[rowCount][2];

            // ResultSet başa alınır
            rs.beforeFirst();
            
            int rowIndex = 0;
            while (rs.next()) {
                dersler[rowIndex][0] = rs.getString(1); // ders kodu
                dersler[rowIndex][1] = rs.getString(2); // ders adi
                rowIndex++;
            }
             for (int i = 0; i < dersler.length; i++) {
            for (int j = 0; j < dersler[i].length; j++) {
                System.out.print(dersler[i][j] + " ");
            }
            System.out.println(); // Yeni satıra geç
        }
            return dersler;
            
        } catch(Exception e) {
            System.out.println("Veritabanı bağlantısında sorun var: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    public String[][] getOgretmenSinavNotlari(String email, String ders) {
        
        if (conn == null) {
            System.out.println("Veritabanı bağlı değil, bağlanıyorum");
            baglan();
        }

        try {
            String query = "{ CALL OgretmenSinavNotlari(?, ?) }";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, ders);
            ResultSet rs = pstmt.executeQuery();

            // Veritabanındaki kayıt sayısını bulmak için önce sonuca bir kez taşınmalıyız.
            rs.last();
            int rowCount = rs.getRow();
            
            // Matrisi oluşturuyoruz
            String[][] dersNotlar = new String[rowCount][8];

            // ResultSet başa alınır
            rs.beforeFirst();
            
            int rowIndex = 0;
            while (rs.next()) {
                dersNotlar[rowIndex][0] = rs.getString(1); // ogrenci no
                dersNotlar[rowIndex][1] = rs.getString(2); // ogrenci adi
                dersNotlar[rowIndex][2] = rs.getString(3); // vize
                dersNotlar[rowIndex][3] = rs.getString(4); // final
                dersNotlar[rowIndex][4] = rs.getString(5); // but
                dersNotlar[rowIndex][5] = rs.getString(6); // ortalama
                dersNotlar[rowIndex][6] = rs.getString(7); // harfnotu
                dersNotlar[rowIndex][7] = rs.getString(8); // durum
                rowIndex++;
            }
             for (int i = 0; i < dersNotlar.length; i++) {
            for (int j = 0; j < dersNotlar[i].length; j++) {
                System.out.print(dersNotlar[i][j] + " ");
            }
            System.out.println(); // Yeni satıra geç
        }
            return dersNotlar;
            
        } catch(Exception e) {
            System.out.println("Veritabanı bağlantısında sorun var: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    public String getIlkDers(String email, String donem) {
        
        if (conn == null) {
            System.out.println("Veritabanı bağlı değil, bağlanıyorum");
            baglan();
        }

        try {
            String query = "{ CALL IlkDers(?, ?) }";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, donem);
            ResultSet rs = pstmt.executeQuery();
            String sonDonem="";
            while (rs.next()) {
                sonDonem = rs.getString(1);               
            }
            System.out.println(sonDonem);
            return sonDonem;
            
        } catch(Exception e) {
            System.out.println("Veritabanı bağlantısında sorun var: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    public String sinavKaydet(String[][] data, String dersAdi) {
        if (conn == null) {
            System.out.println("Veritabanı bağlı değil, bağlanıyorum");
            baglan();
        }

        String updateQuery = "CALL SinavKaydet(?, ?, ?, ?, ?)";
        
        for (String[] row : data) {
            String ogrenciNo = row[0];
            String vizeStr = row[2];
            String finalStr = row[3];
            String butStr = row[4];
            
            Integer vizeNot = null;
            Integer finalNot = null;
            Integer butNot = null;

            try {
                try {
                    vizeNot = Integer.parseInt(vizeStr);
                } catch (NumberFormatException e) {
                    // Sayıya dönüştürülemeyen bir değer olduğunda vizeNot null olarak bırakılır
                }

                try {
                    finalNot = Integer.parseInt(finalStr);
                } catch (NumberFormatException e) {
                    // Sayıya dönüştürülemeyen bir değer olduğunda finalNot null olarak bırakılır
                }

                try {
                    butNot = Integer.parseInt(butStr);
                } catch (NumberFormatException e) {
                    // Sayıya dönüştürülemeyen bir değer olduğunda butNot null olarak bırakılır
                }

                try (PreparedStatement preparedStatement = conn.prepareStatement(updateQuery)) {
                    preparedStatement.setString(1, ogrenciNo);
                    preparedStatement.setString(2, dersAdi);
                    preparedStatement.setObject(3, vizeNot);
                    preparedStatement.setObject(4, finalNot);
                    preparedStatement.setObject(5, butNot);
                    preparedStatement.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (NumberFormatException e) {
                System.out.println("Sayısal olmayan veri atlandı: " + Arrays.toString(row));
            }
        }

        return null;
    }
    
    public String sonuclandir (String[][] data, String dersAdi) {
        
        if (conn == null) {
            System.out.println("Veritabanı bağlı değil, bağlanıyorum");
            baglan();
        }
        
        try {
            
            for (String[] row : data) {
                String ogrenciNo = row[0];

                ortalamaHesapla(ogrenciNo, dersAdi);
                harfNotHesapla(ogrenciNo, dersAdi);
                durumHesapla(ogrenciNo, dersAdi);
                katsayiHesapla(ogrenciNo, dersAdi);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public String ortalamaHesapla(String ogrenciNo, String dersAdi) {
        
        if (conn == null) {
            System.out.println("Veritabanı bağlı değil, bağlanıyorum");
            baglan();
        }

        try {
            String query = "{ CALL OrtalamaHesapla(?, ?) }";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, ogrenciNo);
            pstmt.setString(2, dersAdi);
            ResultSet rs = pstmt.executeQuery();
            
        } catch(Exception e) {
            System.out.println("Veritabanı bağlantısında sorun var: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    public String harfNotHesapla(String ogrenciNo, String dersAdi) {
        
        if (conn == null) {
            System.out.println("Veritabanı bağlı değil, bağlanıyorum");
            baglan();
        }

        try {
            String query = "{ CALL HarfNotHesapla(?, ?) }";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, ogrenciNo);
            pstmt.setString(2, dersAdi);
            ResultSet rs = pstmt.executeQuery();
            
        } catch(Exception e) {
            System.out.println("Veritabanı bağlantısında sorun var: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    public String durumHesapla(String ogrenciNo, String dersAdi) {
        
        if (conn == null) {
            System.out.println("Veritabanı bağlı değil, bağlanıyorum");
            baglan();
        }

        try {
            String query = "{ CALL DurumHesaplama(?, ?) }";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, ogrenciNo);
            pstmt.setString(2, dersAdi);
            ResultSet rs = pstmt.executeQuery();
            
        } catch(Exception e) {
            System.out.println("Veritabanı bağlantısında sorun var: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    public String katsayiHesapla(String ogrenciNo, String dersAdi) {
        
        if (conn == null) {
            System.out.println("Veritabanı bağlı değil, bağlanıyorum");
            baglan();
        }

        try {
            String query = "{ CALL KatsayiHesapla(?, ?) }";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, ogrenciNo);
            pstmt.setString(2, dersAdi);
            ResultSet rs = pstmt.executeQuery();
            
        } catch(Exception e) {
            System.out.println("Veritabanı bağlantısında sorun var: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    public String[][] getTranskript(String email) {
        
        if (conn == null) {
            System.out.println("Veritabanı bağlı değil, bağlanıyorum");
            baglan();
        }
        
        try {
            String query = "{ CALL Transkript(?) }";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            // Veritabanındaki kayıt sayısını bulmak için önce sonuca bir kez taşınmalıyız.
            rs.last();
            int rowCount = rs.getRow();
            
            // Matrisi oluşturuyoruz
            String[][] transkript = new String[rowCount][7];

            // ResultSet başa alınır
            rs.beforeFirst();
            
            int rowIndex = 0;
            while (rs.next()) {
                transkript[rowIndex][0] = rs.getString(1); // donemID
                transkript[rowIndex][1] = rs.getString(2); // ders kodu
                transkript[rowIndex][2] = rs.getString(3); // ders adi
                transkript[rowIndex][3] = rs.getString(4); // kredi
                transkript[rowIndex][4] = rs.getString(5); // akts
                transkript[rowIndex][5] = rs.getString(6); // katsayi
                transkript[rowIndex][6] = rs.getString(7); // harfnotu
                rowIndex++;
            }
             for (int i = 0; i < transkript.length; i++) {
            for (int j = 0; j < transkript[i].length; j++) {
                System.out.print(transkript[i][j] + " ");
            }
            System.out.println(); // Yeni satıra geç
        }
            return transkript;
            
        } catch(Exception e) {
            System.out.println("Veritabanı bağlantısında sorun var: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
        DBKatmani dbk = new DBKatmani();
        dbk.getTranskript("ahmetcan.ilyas.tsal@gmail.com");
    }
}
