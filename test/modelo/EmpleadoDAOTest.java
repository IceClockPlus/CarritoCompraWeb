/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import junit.framework.Assert;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;

/**
 *
 * @author Alexis
 */
public class EmpleadoDAOTest {
    
    public EmpleadoDAOTest() {
        
    }
    
    
    @BeforeClass
    public static void setUpClass() throws SQLException {  
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }

    @After
    public void tearDown() throws SQLException {
        
    }

    /**
     * Test of validar method, of class EmpleadoDAO.
     */
    @Test
    public void testValidar() {
        System.out.println("validar");
       
        //Arrange
        String Rut = "333-3";
        String user = "pepe";
        EmpleadoDAO instance = new EmpleadoDAO();
        //Act
        Empleado result = instance.validar(Rut, user);
        //Assert
        assertEquals(Rut, result.getRut());
        // TODO review the generated test code and remove the default call to fail.
       
 
    }

    
    /**
     * Test of agregar method, of class EmpleadoDAO.
     */
    @Test
    public void testAgregar() {
        System.out.println("agregar");
        Empleado em = new Empleado(7,"444-4","Carlos Sandoval","489151","1","CarlSa0");
        EmpleadoDAO instance = new EmpleadoDAO();
        boolean expResult = true;
        boolean result = instance.agregar(em);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if(result == false){
            fail("Empleado no agregado.");
        }
        
    }
    @Test
    public void testAgregarExistente(){
        System.out.println("Agregar empleado existente");
        Empleado em = new Empleado(2,"123-4","Roman Riquelme","489151","1","CarlSa0");
        EmpleadoDAO instance = new EmpleadoDAO();
        boolean expResult = false;
        boolean result = instance.agregar(em);
        assertEquals(expResult, result);
        if(result==true){
            fail("Empleado ya existente agregado");
        }

    }
    

    /**
     * Test of listarId method, of class EmpleadoDAO.
     */
    @Test
    public void testListarId() {
        System.out.println("listarId");
        int id = 5;
        EmpleadoDAO instance = new EmpleadoDAO();
        String expResult = "333-3";
        Empleado result = instance.listarId(id);
        assertEquals(expResult, result.getRut());
    }
    
    @Test
    public void testListarIdInexistente() {
        System.out.println("listarId");
        //Arrange
        int id = 100;
        EmpleadoDAO instance = new EmpleadoDAO();
        String expResult = null;
        //Act
        Empleado result = instance.listarId(id);
        //Assert
        assertEquals(expResult, result.getRut());
    }
    
    

    /**
     * Test of delete method, of class EmpleadoDAO.
     */
    @Test
    public void testActualizar() {
        System.out.println("actualizar");
        Empleado em = new Empleado(2,"123-4","Carlos Hernandez","941594812","1","Jo46");
        EmpleadoDAO instance = new EmpleadoDAO();
        boolean expResult = true;
        boolean result = instance.actualizar(em);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    @Test
    public void testActualizarInexistente(){
        System.out.println("actualizar empleado inexistente");
        Empleado em = new Empleado(1000,"999-9","Denisse Bravo","48944949","1","Denn94");
        EmpleadoDAO instance = new EmpleadoDAO();
        boolean expResult = false;
        boolean result = instance.actualizar(em);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of actualizar method, of class EmpleadoDAO.
     */
    @Test
    public void testdeleteEmpleado(){
        System.out.println("Eliminar empleado");
        
        //Arrange
        boolean expected = true;
        Empleado emp = new Empleado();
        emp.setNom("Alex Mason");
        emp.setRut("44444444-4");
        emp.setTel("945286454");
        emp.setEstado("1");
        emp.setUser("AlMason");
          
        EmpleadoDAO instance = new EmpleadoDAO();
        instance.agregar(emp);
        
        //Obtener objeto empleado de lista, el cual se encuentra al final de esta
        List<Empleado> empleados = instance.listar();
        Empleado e =(Empleado)empleados.get(empleados.size()-1);
        
        //Act
        boolean result = instance.delete(e.getId());
        
        //Assert
        assertEquals(expected, result);
    }
    
    /**
     * Test sobre el metodo delete de la clase EmpleadoDAO con una id 
     * de un empleado inexistente
     */
    @Test
    public void testDeleteInexistente(){
        System.out.println("Eliminar empleado inexistente");
        //Arrange
        int id = 1000;
        boolean expected = false;
        EmpleadoDAO instance = new EmpleadoDAO();
        
        //Arrange
        boolean result = instance.delete(id);

        
        //Assert 
        assertEquals(expected,result);
    }

    
    
    
    
}
