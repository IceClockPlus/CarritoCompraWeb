package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;

    public Empleado validar(String Rut, String user) {
        Empleado em = new Empleado();
        String sql = "select * from empleado where User=? and Rut=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, Rut);
            rs = ps.executeQuery();
            while (rs.next()) {
                em.setId(rs.getInt("IdEmpleado"));
                em.setUser(rs.getString("User"));
                em.setRut(rs.getString("Rut"));
                em.setNom(rs.getString("Nombres"));
            }
        } catch (SQLException e) {
            e.getStackTrace();
            em=null;
        }
        return em;
    }
    
    //Operaciones CRUD
    
    public List listar(){
        String sql="select * from empleado";
        List<Empleado>lista=new ArrayList<>();
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {
                Empleado em=new Empleado();
                em.setId(rs.getInt(1));
                em.setRut(rs.getString(2));
                em.setNom(rs.getString(3));
                em.setTel(rs.getString(4));
                em.setEstado(rs.getString(5));
                em.setUser(rs.getString(6));
                lista.add(em);
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return lista;
    }
    public boolean agregar(Empleado em){ 
        String sql="insert into empleado(Rut, Nombres, Telefono,Estado,User)values(?,?,?,?,?)";
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, em.getRut());
            ps.setString(2, em.getNom());
            ps.setString(3, em.getTel());
            ps.setString(4, em.getEstado());
            ps.setString(5, em.getUser());
            if( ps.executeUpdate()>0 ){
                return true;
            }
            
        } catch (SQLException e) {
            e.getSQLState();
            return false;
        }
        return false;
        
    }
    public Empleado listarId(int id){
        Empleado emp=new Empleado();
        String sql="select * from empleado where IdEmpleado="+id;
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {
                emp.setRut(rs.getString(2));
                emp.setNom(rs.getString(3));
                emp.setTel(rs.getString(4));
                emp.setEstado(rs.getString(5));
                emp.setUser(rs.getString(6));
            }
        } catch (SQLException e) {
            e.getStackTrace();
            return null;
        }
        return emp;
    }
    public boolean actualizar(Empleado em){
        String sql="update empleado set Rut=?, Nombres=?, Telefono=?,Estado=?,User=? where IdEmpleado=?";
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, em.getRut());
            ps.setString(2, em.getNom());
            ps.setString(3, em.getTel());
            ps.setString(4, em.getEstado());
            ps.setString(5, em.getUser());
            ps.setInt(6, em.getId());
            if(ps.executeUpdate()>0){
                return true;
            }
            
        } catch (SQLException e) {
            e.getStackTrace();
            return false;
        }
        System.out.println("El producto no se agrego");

        return false;
    }
    public boolean delete(int id){
        String sql="delete from empleado where IdEmpleado=?";
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            if(ps.executeUpdate()>0){
                return true;
            }
        } catch (SQLException e) {
            e.getStackTrace();
            return false;
        }
        return false;
        
    }
    
}
