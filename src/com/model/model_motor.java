/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.controller.controller_motor;
import com.koneksi.koneksi;
import com.view.form_motor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
public class model_motor implements controller_motor {
    String jk;

    @Override
    public void Simpan(form_motor motor) throws SQLException {
        if (motor.rbLaki.isSelected()) {
            jk = "Laki-laki";
        }else{
            jk = "Perempuan";
        }
        try {
            Connection con = koneksi.getcon();
            String sql = "Insert Into siswa Values (?,?,?,?)";
            PreparedStatement prepare = con.prepareStatement (sql);
            prepare.setString(1, motor.txtnama.getText());
            prepare.setString(2, motor.txtAlamat.getText());
            prepare.setString(3, jk);
            prepare.setString(4, (String) motor.cbJeniskendaraan.getSelectedItem());
            prepare.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil diSimpan");
            prepare.close();
        } catch (Exception e){
            System.out.println(e);
        } finally {
            Tampil(motor);
            motor.setLebarKolom();
        }
    }

    public void baru(form_motor motor) throws SQLException {
        motor.txtnama.setText("");
        motor.txtAlamat.setText("");
        motor.rbLaki.setSelected(true);
        motor.cbJeniskendaraan.setSelectedIndex(0);

    }

    public void Tampil(form_motor motor) throws SQLException {
      motor.tblmodel.getDataVector().removeAllElements();
        motor.tblmodel.fireTableDataChanged();
        
        try {
            Connection con = koneksi.getcon();
            Statement stt = con.createStatement();
            String sql = "SELECT * FROM siswa ORDER BY Nama ASC";
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()) {                
                Object[] ob = new Object[8];
                ob[0] = rs.getString(1);
                ob[1] = rs.getString(2);
                ob[2] = rs.getString(3);
                ob[3] = rs.getString(4);
                motor.tblmodel.addRow(ob);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @Override
    public void Hapus(form_motor motor) throws SQLException {
try {
            Connection con = koneksi.getcon();
        String sql = "DELETE FROM siswa WHERE Nama=?";
        PreparedStatement prepare = con.prepareStatement(sql);
        prepare.setString(1, motor.txtnama.getText());
        prepare.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data Berhasil Di Hapus");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Tampil(motor);
            motor.setLebarKolom();
            baru(motor);
        }    
}

    @Override
    public void Ubah(form_motor motor) throws SQLException {
     if (motor.rbLaki.isSelected()) {
            jk = "Laki-Laki";
        } else {
            jk = "Perempuan";
        }
        try {
            Connection con = koneksi.getcon();
            String sql = "UPDATE siswa SET nama=?, jenis_Kelamin=?, "
                    +"jenis_kendaraan=? WHERE Nama=?";
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.setString(4, motor.txtnama.getText());
            prepare.setString(1, motor.txtAlamat.getText());
            prepare.setString(2, jk);
            prepare.setString(3, (String)motor.cbJeniskendaraan.getSelectedItem());
            prepare.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Di Ubah");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Tampil(motor);
            motor.setLebarKolom();
            baru(motor);
        }  
    }


    @Override
    public void KlikTable(form_motor motor) throws SQLException {
      try {
      int pilih = motor.tabel.getSelectedRow();
            if (pilih == -1) {
                return;
            }
            motor.txtnama.setText(motor.tblmodel.getValueAt(pilih, 0).toString());
            motor.txtAlamat.setText(motor.tblmodel.getValueAt(pilih, 1).toString());
            motor.cbJeniskendaraan.setSelectedItem(motor.tblmodel.getValueAt(pilih, 3).toString());
            jk = String.valueOf(motor.tblmodel.getValueAt(pilih, 2));
        }catch (Exception e) {
            
        }
        //memberi nilai jk pada radio button
        if (motor.rbLaki.getText().equals(jk)) {
            motor.rbLaki.setSelected(true);
        } else {
            motor.rbPerempuan.setSelected(true);
        }
    }    

    }