package com.example.recuapss.model;

import com.example.recuapss.utils.MySQLConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoPersona {
    BeanPersona persona = new BeanPersona();
    Connection conn;
    PreparedStatement ps;
    CallableStatement cstm;
    ResultSet rs;
    public List<BeanPersona> findAll(){
        List<BeanPersona> personas = new ArrayList<>();
        BeanPersona persona = null;
        try{
            conn = new MySQLConnection().getConnection();
            String query = ("SELECT * FROM view_person_ex");
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
                persona = new BeanPersona();
                persona.setId(rs.getInt("id"));
                persona.setNombre(rs.getString("nombre"));
                persona.setApellidos(rs.getString("apellidos"));
                persona.setBirthday(rs.getString("birthday"));
                persona.setEmail(rs.getString("email"));
                persona.setPhone(rs.getString("phone"));
                persona.setUsername(rs.getString("username"));
                persona.setPassword(rs.getString("password"));
                personas.add(persona);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoPersona.class.getName())
                    .log(Level.SEVERE, "ERROR findAll", e);
        }finally {
            closeConnections();
        }
        return personas;
    }

    public boolean add(BeanPersona persona){
        try{
            conn = new MySQLConnection().getConnection();
            String query = ("call in_persona_ex (?,?,?,?,?,?,?)");
            cstm = conn.prepareCall(query);
            cstm.setString(1, persona.getNombre());
            cstm.setString(2, persona.getApellidos());
            cstm.setString(3, persona.getBirthday());
            cstm.setString(4, persona.getEmail());
            cstm.setString(5, persona.getPassword());
            cstm.setString(6, persona.getUsername());
            cstm.setString(7, persona.getPassword());
            return cstm.execute();
        }catch (SQLException e){
            Logger.getLogger(DaoPersona.class.getName())
                    .log(Level.SEVERE, "ERROR save", e);
            return false;
        }finally {
            closeConnections();
        }
    }
    public BeanPersona findOne(int id){
        try{
            conn = new MySQLConnection().getConnection();
            String query = "select * from persona WHERE id =?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()){
                BeanPersona persona = new BeanPersona();
                persona.setId(rs.getInt("id"));
                persona.setNombre(rs.getString("nombre"));
                persona.setApellidos(rs.getString("apellidos"));
                persona.setBirthday(rs.getString("birthday"));
                return persona;
            }
        }catch (SQLException e){
            Logger.getLogger(DaoPersona.class.getName())
                    .log(Level.SEVERE, "ERROR save", e);
        }finally {
            closeConnections();
        }
        return null;
    }
    public boolean update(BeanPersona persona){
        try{
            conn = new MySQLConnection().getConnection();
            String query = "UPDATE persona SET nombre = ?,"+
                    "apellidos=?, curp=?, birthday=? WHERE id =?";
            ps = conn.prepareStatement(query);
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getApellidos());
            ps.setString(4, persona.getBirthday());
            ps.setInt(5, persona.getId());
            return ps.executeUpdate() == 1;
        }catch (SQLException e){
            Logger.getLogger(DaoPersona.class.getName())
                    .log(Level.SEVERE, "Error update", e);
            return false;
        }finally {
            closeConnections();
        }
    }
    public boolean delete(int id){
        try {
            conn = new MySQLConnection().getConnection();
            String query = "DELETE FROM persona WHERE id = ? ;";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        }catch (SQLException e){
            Logger.getLogger(DaoPersona.class.getName())
                    .log(Level.SEVERE,"Error DELETE user",e);
            return false;
        }finally {
            closeConnections();
        }
    }
    public void closeConnections() {
        try {
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (cstm != null) {
                cstm.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
        }
    }
}
