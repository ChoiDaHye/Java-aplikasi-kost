package server;

import interfaces.bayar;
import interfaces.bulan;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import interfaces.interfaces;
import interfaces.kamar;
import interfaces.lantai;
import interfaces.penghuni;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class implementasi extends UnicastRemoteObject implements interfaces {

    public java.sql.Connection con;
    PreparedStatement ps;

    public implementasi() throws RemoteException {
        try {
            this.con = new koneksi().getCon();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public String tambah_phn(String d1, String d2, String d3, String d4, String d5) throws RemoteException {
        try {
            ps = con.prepareStatement("INSERT INTO penghuni(nik, nama, kontak, alamat, kode_kamar) values (?, ?, ?, ?, ?)");
            ps.setString(1, d1);
            ps.setString(2, d2);
            ps.setString(3, d3);
            ps.setString(4, d4);
            ps.setString(5, d5);
            ps.executeUpdate();
            return "Data ditambahkan!";
        } catch (Exception e) {
            return "Terjadi kesalahan!\n" + e;
        }
    }

    @Override
    public List<penghuni> getphn(int d1) throws RemoteException {
        List<penghuni> list = new ArrayList<penghuni>();
        try {
            Statement st = con.createStatement();
            String a = "SELECT * FROM penghuni WHERE kode_kamar = " + d1;
            ResultSet rs = st.executeQuery(a);
            while (rs.next()) {
                // Retrieve by column name 
                int id = rs.getInt("id");
                String nik = rs.getString("nik");
                String nama = rs.getString("nama");
                String kontak = rs.getString("kontak");

                String alamat = rs.getString("alamat");
                int kode_kamar = rs.getInt("kode_kamar");

                // Setting the values 
                penghuni phn = new penghuni();
                phn.setId_phn(id);
                phn.setNik(nik);
                phn.setNama(nama);
                phn.setKontak(kontak);
                phn.setAlamat(alamat);
                phn.setKd_kamar(kode_kamar);

                list.add(phn);
            }
            rs.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan!\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return list;
    }

    @Override
    public String tambah_tgn(int d2, String d3, int d4, int d5, String d6, int d7) throws RemoteException {
        try {
            ps = con.prepareStatement("UPDATE transaksi_det SET tgl_bayar = '" + d3 + "', tagihan = '" + d4 + "', status = '" + d5 + "' WHERE id_trans = (SELECT id_trans FROM transaksi WHERE bulan = '" + d6 + "' && tahun = '" + d7 +"') AND id_phn = '" + d2 + "'");
            ps.executeUpdate();
            return "Data diperbaharui!";
        } catch (Exception e) {
            return "Terjadi kesalahan!\n" + e;
        }
    }

    @Override
    public List<bayar> getbyr(int d, String g, int f) throws RemoteException {
        List<bayar> list = new ArrayList<bayar>();
        try {
            Statement st = con.createStatement();
            
            if(d == 0) {
                String a = "SELECT b.kode_kamar, b.nama, a.tgl_bayar, a.tagihan FROM transaksi_det a, penghuni b WHERE a.status = '0' AND a.id_phn = b.id AND a.id_trans = (SELECT id_trans FROM transaksi WHERE bulan = '" + g + "' && tahun = '" + f +"')";
                ResultSet rs = st.executeQuery(a);
                while (rs.next()) {
                    // Retrieve by column name 
                    int kd = rs.getInt("kode_kamar");
                    String nm = rs.getString("nama");
                    String tglbyr = rs.getString("tgl_bayar");
                    int tagihan = rs.getInt("tagihan");

                    // Setting the values 
                    bayar byr = new bayar();
                    byr.setKamar(kd);
                    byr.setNama(nm);
                    byr.setTgl_bayar(tglbyr);
                    byr.setTagihan(tagihan);
                    
                    list.add(byr);
                }
                
                rs.close();
            } else if(d == 1){
                String a = "SELECT b.kode_kamar, b.nama, a.tgl_bayar, a.tagihan FROM transaksi_det a, penghuni b WHERE a.status = '1' AND a.id_phn = b.id AND a.id_trans = (SELECT id_trans FROM transaksi WHERE bulan = '" + g + "' && tahun = '" + f +"')";
                ResultSet rs = st.executeQuery(a);
                while (rs.next()) {
                    // Retrieve by column name 
                    int kd = rs.getInt("kode_kamar");
                    String nm = rs.getString("nama");
                    String tglbyr = rs.getString("tgl_bayar");
                    int tagihan = rs.getInt("tagihan");

                    // Setting the values 
                    bayar byr = new bayar();
                    byr.setKamar(kd);
                    byr.setNama(nm);
                    byr.setTgl_bayar(tglbyr);
                    byr.setTagihan(tagihan);
                    
                    list.add(byr);
                }
                
                rs.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan!\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return list;
    }

    @Override
    public List<penghuni> getphn2() throws RemoteException {
        List<penghuni> list = new ArrayList<penghuni>();
        
        try {
            Statement st = con.createStatement();
            String a = "SELECT * FROM penghuni";
            ResultSet rs = st.executeQuery(a);
            while (rs.next()) {
                // Retrieve by column name 
                int id = rs.getInt("id");
                String nik = rs.getString("nik");
                String nama = rs.getString("nama");
                String kontak = rs.getString("kontak");

                String alamat = rs.getString("alamat");
                int kode_kamar = rs.getInt("kode_kamar");

                // Setting the values 
                penghuni phn = new penghuni();
                phn.setId_phn(id);
                phn.setNik(nik);
                phn.setNama(nama);
                phn.setKontak(kontak);
                phn.setAlamat(alamat);
                phn.setKd_kamar(kode_kamar);

                list.add(phn);
            }
            rs.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan!\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return list;
    }

    @Override
    public List<kamar> getkmr1() throws RemoteException {
        List<kamar> list = new ArrayList<kamar>();
        
        try {
            Statement st = con.createStatement();
            String a = "SELECT * FROM kamar WHERE kode_kamar NOT IN(SELECT kode_kamar FROM penghuni)";
            ResultSet rs = st.executeQuery(a);
            while (rs.next()) {
                int kode = rs.getInt("kode_kamar");
                int lantai = rs.getInt("lantai");
                
                kamar kr = new kamar();
                kr.setKode_kamar(kode);
                kr.setLantai(lantai);

                list.add(kr);
            }
            rs.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan!\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return list;        
    }

    @Override
    public String pindah_phn(int awal, int baru) throws RemoteException {
        try {
            ps = con.prepareStatement("UPDATE penghuni SET kode_kamar = "+ baru +" WHERE id = " + awal);
            ps.executeUpdate();
            
            return "Perubahan berhasil";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan!\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return "Gagal";
    }

    @Override
    public String akhiri(int id) throws RemoteException {
         try {
            ps = con.prepareStatement("DELETE FROM penghuni WHERE id = '"+ id + "'");
             ps.executeUpdate();
         return "Data dihapus!";
        } catch (Exception e) {
            return "Terjadi kesalahan!\n" + e;
        }
    }

    @Override
    public List<bulan> getbln1(int d) throws RemoteException {
        List<bulan> list = new ArrayList<bulan>();
        
        try {
            Statement st = con.createStatement();
            String a = "SELECT a.id_trans, a.bulan, a.tahun FROM transaksi a, transaksi_det b WHERE a.id_trans = b.id_trans AND b.status = 0 AND b.id_phn = '"+ d +"'";
            ResultSet rs = st.executeQuery(a);
            while (rs.next()) {
                int id = rs.getInt("id_trans");
                String bulan = rs.getString("bulan") + " " + rs.getString("tahun").substring(0, 4);
                
                bulan bl = new bulan();
                bl.setId_bln(id);
                bl.setNama_bln(bulan);

                list.add(bl);
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan!\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return list;
    }

    @Override
    public List<bulan> getbln2() throws RemoteException {
        List<bulan> list = new ArrayList<bulan>();
        
        try {
            Statement st = con.createStatement();
            String a = "SELECT * FROM transaksi";
            ResultSet rs = st.executeQuery(a);
            while (rs.next()) {
                int id = rs.getInt("id_trans");
                String bulan = rs.getString("bulan") + " " + rs.getString("tahun").substring(0, 4);
                
                bulan bl = new bulan();
                bl.setId_bln(id);
                bl.setNama_bln(bulan);

                list.add(bl);
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan!\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return list;
    }

    @Override
    public List<lantai> getkmr2() throws RemoteException {
        List<lantai> list = new ArrayList<lantai>();
        
        try {
            Statement st = con.createStatement();
            String a = "SELECT DISTINCT lantai FROM kamar";
            ResultSet rs = st.executeQuery(a);
            while (rs.next()) {
                int lantai = rs.getInt("lantai");
                
                lantai kr = new lantai();
                kr.setLantai(lantai);

                list.add(kr);
            }
            rs.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan!\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return list;
    }

    @Override
    public List<kamar> getkmr3(int c, int d) throws RemoteException {
        List<kamar> list = new ArrayList<kamar>();
        
        try {
            Statement st = con.createStatement();
            //String a = "SELECT * FROM kamar WHERE kode_kamar NOT IN(SELECT kode_kamar FROM penghuni)";            
            if(d == 0) {
                String a = "SELECT * FROM kamar WHERE lantai = '" + c + "' AND kode_kamar NOT IN(SELECT kode_kamar FROM penghuni)";
                ResultSet rs = st.executeQuery(a);
                while (rs.next()) {
                    int kode = rs.getInt("kode_kamar");
                    int lantai = rs.getInt("lantai");

                    kamar kr = new kamar();
                    kr.setKode_kamar(kode);
                    kr.setLantai(lantai);

                    list.add(kr);
                }
                rs.close();
            } else if(d == 1) {
                String a = "SELECT * FROM kamar WHERE lantai = '" + c + "' AND kode_kamar IN(SELECT kode_kamar FROM penghuni)";
                ResultSet rs = st.executeQuery(a);
                while (rs.next()) {
                    int kode = rs.getInt("kode_kamar");
                    int lantai = rs.getInt("lantai");

                    kamar kr = new kamar();
                    kr.setKode_kamar(kode);
                    kr.setLantai(lantai);

                    list.add(kr);
                }
                rs.close();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan!\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return list;  
    }
}
