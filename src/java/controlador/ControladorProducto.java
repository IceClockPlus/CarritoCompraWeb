/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Producto;
import modelo.ProductoDAO;

/**
 *
 * @author Alexis
 */
public class ControladorProducto extends HttpServlet {

     private ProductoDAO prodDAO;
     private int idProdMod;
    
    public void init(){
        prodDAO = new ProductoDAO();
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        try{
            switch(accion){
            case "Listar":
                this.listarProductos(request,response);
                break;
            case "Agregar":       
                this.agregarProducto(request, response);                    
                break;
            case "Editar":
                this.editarProducto(request, response);
                break;
            case "Actualizar":
                this.actualizarProducto(request, response);
                break;
            case "Eliminar":
                this.eliminarProducto(request, response);
                break;
        }
        }catch(IOException | SQLException | ServletException ex){
            ex.getStackTrace();
        }
        


    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    /**
     * Funcion que permite agregar un producto a la base de datos, por medio de 
     * la función agregar de la clase ProductoDAO
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     * @throws IllegalArgumentException 
     */
    private void agregarProducto(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, SQLException, IllegalArgumentException{
        
        //Recolecta los datos obtenidos del formulario y asignarlo a las variables
        String nom = request.getParameter("txtNombre");
        String des = request.getParameter("txtDescrip");
        double precio = Double.parseDouble(request.getParameter("txtPrecio"));
        int stock = Integer.parseInt(request.getParameter("txtStock"));
        
        try{
            //Creacion de instancia de clase Producto y asignar a sus variables los valores anteriores
            Producto prod = new Producto();
            prod.setNombres(nom);
            prod.setDescripcion(des);
            prod.setPrecio(precio);
            prod.setStock(stock);
        
            //Se ejecuta la función agregar de la instancia de la clase ProductoDAO
            prodDAO.agregar(prod);
        }catch(IllegalArgumentException ex){
            ex.getStackTrace();
        }
            this.listarProductos(request, response);
    }
    
    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, SQLException, IllegalArgumentException{
        
        try{
            String nombre = request.getParameter("txtNombre");
            String descripcion = request.getParameter("txtDescrip");
            int precio = Integer.parseInt(request.getParameter("txtPrecio"));
            int stock = Integer.parseInt(request.getParameter("txtStock"));

            Producto p = new Producto();
            p.setId(idProdMod);
            p.setNombres(nombre);
            p.setDescripcion(descripcion);
            p.setPrecio(precio);
            p.setStock(stock);
        
            prodDAO.actualizar(p);
        }catch(IllegalArgumentException iae){
            iae.getStackTrace();
        }
        this.listarProductos(request, response);

    }
    
    /**
     * Función que permite obtener los datos de un producto con una Id en especifico, los
     * cuales son rellenados en los campos del formulario
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws SQLException 
     */
    private void editarProducto(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, SQLException{
        
        //Se asigna a la variable id ProdMod que es necesaria para realizar la actualizacion del producto
        idProdMod = Integer.parseInt(request.getParameter("id"));
        
        //Se realiza la busqueda del producto con la id asignada a idProdMod
        Producto p = prodDAO.listarId(idProdMod);
        //Se rellena los campos del formulario con los datos obtenidos de la busqueda anterior
        request.setAttribute("producto", p);
        this.listarProductos(request, response);

    }
    
    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException,SQLException{
        int idProd = Integer.parseInt(request.getParameter("id"));
        prodDAO.delete(idProd);
        this.listarProductos(request, response);
    }
    
    
    private void listarProductos(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException,SQLException{
        
        List lista = prodDAO.listar();
        request.setAttribute("productos", lista);
        request.getRequestDispatcher("Producto.jsp").forward(request, response);
    }
}
