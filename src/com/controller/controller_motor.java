/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;
import com.view.form_motor;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public interface controller_motor {
    public void Simpan (form_motor motor) throws SQLException;
    public void Ubah(form_motor motor) throws SQLException;
    public void Hapus (form_motor motor) throws SQLException;
    public void KlikTable(form_motor motor) throws SQLException;
}
