/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_;

import Controller_.koneksi_db;
import Model_.M_buku;
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
            PreparedStatement ps = conn.prepareStatement("INSERT INTO pinjam_buku VALUES (?,?,?,?,(mulai_pinjam + INTERVAL 14 DAY),?,?)");
            ps.setInt(1, pinjem.getId_pinjam());
            ps.setInt(2, pinjem.getId_member());
            ps.setString(3, pinjem.getId_buku());
            ps.setString(4, pinjem.getMulai_pinjam());
            ps.setString(5, pinjem.getSudah_diambil());
            ps.setString(6, pinjem.getDikonfirmasikah());
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
            PreparedStatement detail_buku_dipinjam = conn.prepareStatement("SELECT *, DATEDIFF(akhir_pinjam,CURRENT_DATE() ) AS pinjam_day_remaining FROM buku INNER JOIN pinjam_buku USING (id_buku) WHERE id_member=?");
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
            PreparedStatement detail_buku_dipinjam = conn.prepareStatement("SELECT *, DATEDIFF(akhir_pinjam,CURRENT_DATE() ) AS pinjam_day_remaining FROM buku INNER JOIN pinjam_buku USING (id_buku)");

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
}
