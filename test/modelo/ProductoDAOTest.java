/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import static org.junit.Assert.*;

/**
 *
 * @author aramossa
 */
public class ProductoDAOTest {
    
    public ProductoDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of buscar method, of class ProductoDAO.
     */
    @Test
    public void testBuscar() {
        System.out.println("buscar");
        //Arrange
        /*Id de producto a buscar*/
        int id = 7;
        /*Creación de instancia ProductoDAO*/
        ProductoDAO instance = new ProductoDAO();
        //Act
        /*Se realiza la busqueda del producto por la ID*/
        Producto result = instance.buscar(id);
        
        //Assert
        assertEquals("producto 1", result.getNombres());
    }

    /**
     * Test of actualizarStock method, of class ProductoDAO.
     */
    @Test
    public void testActualizarStock() {
        System.out.println("actualizarStock");
        //Arrange
        int id = 10;
        int stock = 30;
        ProductoDAO instance = new ProductoDAO();
        
        //Act
        boolean result = instance.actualizarStock(id, stock);
        
        //Assert
        
        boolean expResult = true;
        assertEquals(expResult, result);
        if(result==false){
            fail("El stock del producto no fue actualizado");
        }        
    }
    
    /**
     * Test del metodo actualizarStock con un stock negativo. 
     * Se espera que no sea posible actualizar el stock de un producto con un 
     * valor negativo
     */
    @Test
    public void testActualizarStockNegativo(){
        //Arrange
        int id = 11;
        int stock = -5;
        ProductoDAO instance = new ProductoDAO();
        
        //Act
        boolean resultado = instance.actualizarStock(id, stock);
        boolean resEsperado = false;
        
        //Assert 
        assertEquals(resEsperado,resultado);
        
    }

    /**
     * Test of listarId method, of class ProductoDAO.
     */
    @Test
    public void testListarId() {
        System.out.println("listarId");
        //Arrange
        int id = 8;
        ProductoDAO instance = new ProductoDAO();
        //Act
        Producto result = instance.listarId(id);
        //Assert
        assertEquals("Producto 2", result.getNombres());
        
        if(result.equals(null)){
            fail("No se pudo realizar la busqueda.");

        }
    }

    /**
     * Test of listar method, of class ProductoDAO.
     */
    @Test
    public void testListar() {
        System.out.println("listar");
        //Arrange
        ProductoDAO instance = new ProductoDAO();
        //Act
        List result = instance.listar();
        
        //Assert
        /*Verificar que la lista no está vacia*/
        assertFalse(result.isEmpty());
        
        if(result.isEmpty()){
            fail("La función entrego una lista vacia.");

        }
    }

    /**
     * Test of agregar method, of class ProductoDAO.
     */
    @Test
    public void testAgregar() {
        System.out.println("agregar");
        //Arrange
        /*Definición de Producto*/
        Producto p = new Producto();
        p.setNombres("Producto Test");
        p.setDescripcion("Descripcion de Test");
        p.setPrecio(30000);
        p.setStock(20);
        /*Creacion de instancia ProductoDAO*/
        ProductoDAO instance = new ProductoDAO();
        
        //Act
        /*Ejecucion de funcion para agregar producto*/
        boolean result = instance.agregar(p);
        //Assert   
        boolean valorEsperado = true;
        assertEquals(valorEsperado, result);
        
        // TODO review the generated test code and remove the default call to fail.
        if(result == false){
            fail("Producto no fue agregado");
        }
    }

    /**
     * Test of actualizar method, of class ProductoDAO.
     */
    @Test
    public void testActualizar() {
        System.out.println("actualizar");
        //Arrange
        Producto p = new Producto();
        p.setId(12);
        p.setNombres("Producto 6 version 2");
        /*Nueva descripcion*/
        p.setDescripcion("Este es la nueva version del producto 6");
        /*Nuevo Precio*/
        p.setPrecio(29000);
        p.setStock(2);
        
        /*Instancia ProductoDAO*/
        ProductoDAO instance = new ProductoDAO();
        //Act
        boolean result = instance.actualizar(p);
       
        //Assert
        boolean expResult = true;
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if(result==false){
           fail("El producto no fue actualizado.");

        }
    }

    /**
     * Test al metodo actualizar en un producto inexistente
     */
    @Test
    public void testActualizarProdInexistente()
    {
        System.out.println("actualizar producto inexistente");
        //Arrange
        boolean valorEsperado =false;
        Producto p = new Producto();
        p.setId(1300);
        p.setDescripcion("Producto que no existe");
        p.setPrecio(12000);
        
        ProductoDAO instance = new ProductoDAO();
        //Act
        boolean resultado = instance.actualizar(p);
       
        //Assert
        assertEquals(valorEsperado,resultado);
        
    }    
    
    /**
     * Test of delete method, of class ProductoDAO.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        //Arrange
        
        Producto prod = new Producto();
        prod.setNombres("Producto Del");
        prod.setDescripcion("Producto a eliminar");
        prod.setPrecio(3000);
        prod.setStock(3);
        
        
        ProductoDAO instance = new ProductoDAO();
        //Act
        /*Agregar producto a eliminar*/
        instance.agregar(prod);
        
        List list = instance.listar();
        
        /*Cantidad de producto antes de la eliminacion*/
        int cantidadAntes = list.size();
        System.out.println("Cantidad de productos antes de la eliminacion: "+cantidadAntes);
        
        /*Se recupera el producto creado desde la lista, el cual se encuentra en la
        ultima posicion de esta*/
        Producto p = (Producto)list.get(list.size()-1);
        
        /*Se realiza la eliminacion del producto*/
        instance.delete(p.getId());
        
        /*Se vuelve a llamar a listar para obtener el conteo de productos*/
        int resultado = instance.listar().size();
        System.out.println("Cantidad de productos tras la eliminacion: "+resultado);
        
        assertNotSame(cantidadAntes, resultado);
    }
    
    /**
     * Test de metodo delete de un producto inexistente
     */
    @Test
    public void deleteInexistente(){
        System.out.println("delete producto inexistente");
        //Arrange
        /*Id de un producto que no existe*/
        int id = 1000;
        
        ProductoDAO instance = new ProductoDAO();
       
        /*Se espera que la cantidad de productos sea igual tras la elmininacion*/
        int resultadoEsperado = instance.listar().size();
        
        //Act
        /*Se ejecuta la eliminacion del producto inexistente*/
        instance.delete(id);
        
        
        //Assert
        /*Se vuelve a contar la cantidad de productos tras la elmininacion*/
        int resultado = instance.listar().size();
        /*Se espera que las cantidades de productos no hayan cambiado*/
        assertSame(resultadoEsperado,resultado);
        
    }
    
}
