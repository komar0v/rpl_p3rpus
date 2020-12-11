/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_;

import Controller_.koneksi_db;
import Model_.M_buku;
import Model_.M_detailBuku_Dipinjam;
import Model_.M_pinjamBuku;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class x_Peminjaman {

    public void member_pinjamBuku(M_pinjamBuku pinjem) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = koneksi_db.initializeDatabase();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO pinjam_buku VALUES (?,?,?,?,(mulai_pinjam + INTERVAL 14 DAY),?,?,?)");
            ps.setInt(1, pinjem.getId_pinjam());
            ps.setInt(2, pinjem.getId_member());
            ps.setString(3, pinjem.getId_buku());
            ps.setString(4, pinjem.getMulai_pinjam());
            ps.setString(5, pinjem.getSudah_diambil());
            ps.setString(6, pinjem.getDikonfirmasikah());
            ps.setString(7, pinjem.getBatalkah());
            ps.executeUpdate();
            conn.close();

        } catch (Exception ex) {
            Logger.getLogger(x_Peminjaman.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<M_pinjamBuku> getBuku_yang_dipinjam(int id_member) {
        ArrayList<M_pinjamBuku> detail_buku_yang_dipinjam = new ArrayList<M_pinjamBuku>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = koneksi_db.initializeDatabase();
            PreparedStatement detail_buku_dipinjam = conn.prepareStatement("SELECT *, DATEDIFF(akhir_pinjam,CURRENT_DATE() ) AS pinjam_day_remaining FROM buku INNER JOIN pinjam_buku USING (id_buku) WHERE id_member=? AND batalkah='tidak'");
            detail_buku_dipinjam.setInt(1, id_member);

            ResultSet rs4 = detail_buku_dipinjam.executeQuery();
            while (rs4.next()) {
                M_pinjamBuku buku_dipinjam = new M_pinjamBuku();
                buku_dipinjam.setId_pinjam(rs4.getInt("id_pinjam"));
                buku_dipinjam.setId_buku(rs4.getString("id_buku"));
                buku_dipinjam.setJudul_buku(rs4.getString("judul_buku"));
                buku_dipinjam.setAkhir_pinjam(rs4.getString("akhir_pinjam"));
                buku_dipinjam.setSudah_diambil(rs4.getString("diambilkah"));
                buku_dipinjam.setDikonfirmasikah(rs4.getString("dikonfirmasikah"));
                buku_dipinjam.setPinjam_day_remaining(rs4.getString("pinjam_day_remaining"));
                detail_buku_yang_dipinjam.add(buku_dipinjam);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(x_Peminjaman.class.getName()).log(Level.SEVERE, null, ex);
        }
        return detail_buku_yang_dipinjam;
    }
    
    public static List<M_pinjamBuku> getBuku_yang_mauDiperpanjang(int id_member) {
        ArrayList<M_pinjamBuku> detail_buku_yang_dipinjam = new ArrayList<M_pinjamBuku>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = koneksi_db.initializeDatabase();
            PreparedStatement detail_buku_dipinjam = conn.prepareStatement("SELECT *, DATEDIFF(akhir_pinjam,CURRENT_DATE() ) AS pinjam_day_remaining FROM buku INNER JOIN pinjam_buku USING (id_buku) WHERE id_member=? AND diambilkah='sudah'");
            detail_buku_dipinjam.setInt(1, id_member);

            ResultSet rs4 = detail_buku_dipinjam.executeQuery();
            while (rs4.next()) {
                M_pinjamBuku buku_dipinjam = new M_pinjamBuku();
                buku_dipinjam.setId_pinjam(rs4.getInt("id_pinjam"));
                buku_dipinjam.setId_buku(rs4.getString("id_buku"));
                buku_dipinjam.setJudul_buku(rs4.getString("judul_buku"));
                buku_dipinjam.setAkhir_pinjam(rs4.getString("akhir_pinjam"));
                buku_dipinjam.setSudah_diambil(rs4.getString("diambilkah"));
                buku_dipinjam.setDikonfirmasikah(rs4.getString("dikonfirmasikah"));
                buku_dipinjam.setPinjam_day_remaining(rs4.getString("pinjam_day_remaining"));
                detail_buku_yang_dipinjam.add(buku_dipinjam);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(x_Peminjaman.class.getName()).log(Level.SEVERE, null, ex);
        }
        return detail_buku_yang_dipinjam;
    }
    
    public static List<M_pinjamBuku> getSemuaPeminjaman() {
        ArrayList<M_pinjamBuku> detail_buku_yang_dipinjam = new ArrayList<M_pinjamBuku>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = koneksi_db.initializeDatabase();
            PreparedStatement detail_buku_dipinjam = conn.prepareStatement("SELECT judul_buku, nama_member, id_pinjam, mulai_pinjam, akhir_pinjam FROM pinjam_buku JOIN buku ON pinjam_buku.id_buku=buku.id_buku JOIN member ON pinjam_buku.id_member=member.id_member WHERE batalkah='tidak' AND dikonfirmasikah='belum'");

            ResultSet rs4 = detail_buku_dipinjam.executeQuery();
            while (rs4.next()) {
                M_pinjamBuku buku_dipinjam = new M_pinjamBuku();
                buku_dipinjam.setId_pinjam(rs4.getInt("id_pinjam"));
                buku_dipinjam.setNama_member(rs4.getString("nama_member"));
                buku_dipinjam.setJudul_buku(rs4.getString("judul_buku"));
                buku_dipinjam.setMulai_pinjam(rs4.getString("mulai_pinjam"));
                detail_buku_yang_dipinjam.add(buku_dipinjam);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(x_Peminjaman.class.getName()).log(Level.SEVERE, null, ex);
        }
        return detail_buku_yang_dipinjam;
    }
    
    public static List<M_pinjamBuku> getSemuaPeminjaman_yangsudah_dikonfirmasi() {
        ArrayList<M_pinjamBuku> detail_buku_yang_dipinjam = new ArrayList<M_pinjamBuku>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = koneksi_db.initializeDatabase();
            PreparedStatement detail_buku_dipinjam = conn.prepareStatement("SELECT judul_buku, nama_member, id_pinjam, mulai_pinjam, akhir_pinjam FROM pinjam_buku JOIN buku ON pinjam_buku.id_buku=buku.id_buku JOIN member ON pinjam_buku.id_member=member.id_member WHERE dikonfirmasikah='sudah' AND diambilkah='belum'");

            ResultSet rs4 = detail_buku_dipinjam.executeQuery();
            while (rs4.next()) {
                M_pinjamBuku buku_dipinjam = new M_pinjamBuku();
                buku_dipinjam.setId_pinjam(rs4.getInt("id_pinjam"));
                buku_dipinjam.setNama_member(rs4.getString("nama_member"));
                buku_dipinjam.setJudul_buku(rs4.getString("judul_buku"));
                buku_dipinjam.setMulai_pinjam(rs4.getString("mulai_pinjam"));
                detail_buku_yang_dipinjam.add(buku_dipinjam);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(x_Peminjaman.class.getName()).log(Level.SEVERE, null, ex);
        }
        return detail_buku_yang_dipinjam;
    }
    
    public void admin_konfirmasiAmbil_SUDAH_DIAMBIL(int id_pinjam){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = koneksi_db.initializeDatabase();
            PreparedStatement ps = conn.prepareStatement("UPDATE pinjam_buku SET diambilkah='sudah' WHERE id_pinjam=?");
            ps.setInt(1, id_pinjam);
            ps.executeUpdate();
            conn.close();

        } catch (Exception ex) {
            Logger.getLogger(x_Peminjaman.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void admin_konfirmasiAmbil_BATAL_DIAMBIL(int id_pinjam){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = koneksi_db.initializeDatabase();
            PreparedStatement ps = conn.prepareStatement("UPDATE pinjam_buku SET diambilkah='batal' WHERE id_pinjam=?");
            ps.setInt(1, id_pinjam);
            ps.executeUpdate();
            conn.close();

        } catch (Exception ex) {
            Logger.getLogger(x_Peminjaman.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void admin_konfirmasiPinjam(int id_pinjam){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = koneksi_db.initializeDatabase();
            PreparedStatement ps = conn.prepareStatement("UPDATE pinjam_buku SET dikonfirmasikah='sudah', batalkah='tidak' WHERE id_pinjam=?");
            ps.setInt(1, id_pinjam);
            ps.executeUpdate();
            conn.close();

        } catch (Exception ex) {
            Logger.getLogger(x_Peminjaman.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void admin_konfirmasiPembatalanPinjam(int id_pinjam){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = koneksi_db.initializeDatabase();
            PreparedStatement ps = conn.prepareStatement("UPDATE pinjam_buku SET batalkah='ya' WHERE batalkah='onProccess' AND id_pinjam=?");
            ps.setInt(1, id_pinjam);
            ps.executeUpdate();
            conn.close();

        } catch (Exception ex) {
            Logger.getLogger(x_Peminjaman.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static List<M_pinjamBuku> admin_getSemuaPermintaanPembatalan() {
        ArrayList<M_pinjamBuku> detail_buku_yang_dipinjam = new ArrayList<M_pinjamBuku>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = koneksi_db.initializeDatabase();
            PreparedStatement detail_buku_dipinjam = conn.prepareStatement("SELECT judul_buku, nama_member, id_pinjam, mulai_pinjam, akhir_pinjam FROM pinjam_buku JOIN buku ON pinjam_buku.id_buku=buku.id_buku JOIN member ON pinjam_buku.id_member=member.id_member WHERE batalkah='onProccess' ORDER BY mulai_pinjam DESC");

            ResultSet rs4 = detail_buku_dipinjam.executeQuery();
            while (rs4.next()) {
                M_pinjamBuku buku_dipinjam = new M_pinjamBuku();
                buku_dipinjam.setId_pinjam(rs4.getInt("id_pinjam"));
                buku_dipinjam.setNama_member(rs4.getString("nama_member"));
                buku_dipinjam.setJudul_buku(rs4.getString("judul_buku"));
                buku_dipinjam.setMulai_pinjam(rs4.getString("mulai_pinjam"));
                buku_dipinjam.setAkhir_pinjam(rs4.getString("akhir_pinjam"));
                detail_buku_yang_dipinjam.add(buku_dipinjam);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(x_Peminjaman.class.getName()).log(Level.SEVERE, null, ex);
        }
        return detail_buku_yang_dipinjam;
    }
    
    
    public static List<M_pinjamBuku> getHistoryPeminjaman() {
        ArrayList<M_pinjamBuku> detail_buku_yang_dipinjam = new ArrayList<M_pinjamBuku>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = koneksi_db.initializeDatabase();
            PreparedStatement detail_buku_dipinjam = conn.prepareStatement("SELECT judul_buku, nama_member, id_pinjam, mulai_pinjam, akhir_pinjam FROM pinjam_buku JOIN buku ON pinjam_buku.id_buku=buku.id_buku JOIN member ON pinjam_buku.id_member=member.id_member WHERE diambilkah='sudah' ORDER BY mulai_pinjam DESC");

            ResultSet rs4 = detail_buku_dipinjam.executeQuery();
            while (rs4.next()) {
                M_pinjamBuku buku_dipinjam = new M_pinjamBuku();
                buku_dipinjam.setId_pinjam(rs4.getInt("id_pinjam"));
                buku_dipinjam.setNama_member(rs4.getString("nama_member"));
                buku_dipinjam.setJudul_buku(rs4.getString("judul_buku"));
                buku_dipinjam.setMulai_pinjam(rs4.getString("mulai_pinjam"));
                buku_dipinjam.setAkhir_pinjam(rs4.getString("akhir_pinjam"));
                detail_buku_yang_dipinjam.add(buku_dipinjam);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(x_Peminjaman.class.getName()).log(Level.SEVERE, null, ex);
        }
        return detail_buku_yang_dipinjam;
    }
    
    public String getJumlahBuku_dipinjamMember(int id_member) {
        int jumlah_buku_dipinjam = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = koneksi_db.initializeDatabase();
            PreparedStatement psJml_bukuDipinjam = conn.prepareStatement("SELECT COUNT(*) AS jumlah_buku_dipinjam FROM pinjam_buku WHERE id_member=? AND diambilkah='sudah'");
            psJml_bukuDipinjam.setInt(1, id_member);
            ResultSet rs4 = psJml_bukuDipinjam.executeQuery();

            while (rs4.next()) {
                jumlah_buku_dipinjam = rs4.getInt("jumlah_buku_dipinjam");
            }

        } catch (Exception ex) {
            Logger.getLogger(x_Member.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Integer.toString(jumlah_buku_dipinjam);
    }
    
    public static List<M_pinjamBuku> member_getBuku_yang_mauDIBATALKAN(int id_member) {
        ArrayList<M_pinjamBuku> detail_buku_yang_dipinjam = new ArrayList<M_pinjamBuku>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = koneksi_db.initializeDatabase();
            PreparedStatement detail_buku_dipinjam = conn.prepareStatement("SELECT dikonfirmasikah, batalkah, judul_buku, nama_member, id_pinjam, mulai_pinjam, akhir_pinjam FROM pinjam_buku JOIN buku ON pinjam_buku.id_buku=buku.id_buku JOIN member ON pinjam_buku.id_member=member.id_member WHERE dikonfirmasikah='belum' AND pinjam_buku.id_member=?");
            detail_buku_dipinjam.setInt(1, id_member);

            ResultSet rs4 = detail_buku_dipinjam.executeQuery();
            while (rs4.next()) {
                M_pinjamBuku buku_dipinjam = new M_pinjamBuku();
                buku_dipinjam.setId_pinjam(rs4.getInt("id_pinjam"));
                buku_dipinjam.setJudul_buku(rs4.getString("judul_buku"));
                buku_dipinjam.setAkhir_pinjam(rs4.getString("akhir_pinjam"));
                buku_dipinjam.setDikonfirmasikah(rs4.getString("dikonfirmasikah"));
                buku_dipinjam.setBatalkah(rs4.getString("batalkah"));
                detail_buku_yang_dipinjam.add(buku_dipinjam);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(x_Peminjaman.class.getName()).log(Level.SEVERE, null, ex);
        }
        return detail_buku_yang_dipinjam;
    }
    
    public void member_mengajukanPembatalan(int idPinjam_){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = koneksi_db.initializeDatabase();
            PreparedStatement ps = conn.prepareStatement("UPDATE pinjam_buku SET batalkah='onProccess' WHERE id_pinjam=?");
            ps.setInt(1, idPinjam_);
            ps.executeUpdate();
            conn.close();

        } catch (Exception ex) {
            Logger.getLogger(x_Peminjaman.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void member_perpanjangPinjam(int idPinjam_){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = koneksi_db.initializeDatabase();
            PreparedStatement ps = conn.prepareStatement("UPDATE pinjam_buku SET akhir_pinjam=(akhir_pinjam + INTERVAL 7 DAY) WHERE id_pinjam=?");
            ps.setInt(1, idPinjam_);
            ps.executeUpdate();
            conn.close();

        } catch (Exception ex) {
            Logger.getLogger(x_Peminjaman.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static List<M_detailBuku_Dipinjam> getDetailBuku_yangDipinjam(int id_peminjaman) {
        ArrayList<M_detailBuku_Dipinjam> detail_buku_yang_dipinjam = new ArrayList<M_detailBuku_Dipinjam>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = koneksi_db.initializeDatabase();
            PreparedStatement detail_buku_dipinjam = conn.prepareStatement("SELECT *, DATEDIFF(akhir_pinjam,CURRENT_DATE() ) AS pinjam_day_remaining FROM buku INNER JOIN pinjam_buku USING (id_buku) WHERE id_pinjam=?");
            detail_buku_dipinjam.setInt(1, id_peminjaman);

            ResultSet rs4 = detail_buku_dipinjam.executeQuery();
            while (rs4.next()) {
                M_detailBuku_Dipinjam buku_dipinjam = new M_detailBuku_Dipinjam();
                buku_dipinjam.setId_pinjam(rs4.getInt("id_pinjam"));
                
                buku_dipinjam.setJudul_buku(rs4.getString("judul_buku"));
                buku_dipinjam.setIsbn_buku(rs4.getString("isbn_buku"));
                buku_dipinjam.setPenerbit_buku(rs4.getString("penerbit_buku"));
                buku_dipinjam.setPengarang_buku(rs4.getString("pengarang_buku"));
                buku_dipinjam.setKategori_buku(rs4.getString("kategori_buku"));
                buku_dipinjam.setTahunterbit_buku(rs4.getString("tahunterbit_buku"));
                
                buku_dipinjam.setMulai_pinjam(rs4.getString("mulai_pinjam"));
                buku_dipinjam.setAkhir_pinjam(rs4.getString("akhir_pinjam"));
                buku_dipinjam.setPinjam_day_remaining(rs4.getString("pinjam_day_remaining"));
                buku_dipinjam.setDikonfirmasikah(rs4.getString("dikonfirmasikah"));
                buku_dipinjam.setSudah_diambil(rs4.getString("diambilkah"));
                detail_buku_yang_dipinjam.add(buku_dipinjam);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(x_Peminjaman.class.getName()).log(Level.SEVERE, null, ex);
        }
        return detail_buku_yang_dipinjam;
    }
    
    
}
