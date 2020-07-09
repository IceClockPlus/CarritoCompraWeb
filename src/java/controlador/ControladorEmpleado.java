/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Empleado;
import modelo.EmpleadoDAO;

/**
 *
 * @author docencia
 */
public class ControladorEmpleado extends HttpServlet {
    
    private EmpleadoDAO emplDAO;
    private int idEdit;
    
    @Override
    public void init(){
        emplDAO = new EmpleadoDAO();
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
                this.listarEmpleados(request,response);
                break;
            case "Agregar":
                this.agregarEmpleado(request, response);
                break;
            case "Editar":
                this.editarEmpleado(request, response);
                break;
            case "Actualizar":
                this.actualizarEmpleado(request, response);
                break;
            case "Eliminar":
                this.eliminarEmpleado(request, response);
            default:
                this.listarEmpleados(request, response);
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
        return "Short description ControladorEmpleado";
    }// </editor-fold>
    
    /**
     * Llama a la función listar de la clase EmpleadoDAO, el cual obtiene una lista
     * de todos los empleados registrados
     * 
     * @param request
     * @param response
     * @throws SQLException si existe algún error con la conexion a la base de datos
     * @throws IOException
     * @throws ServletException 
     */
    private void listarEmpleados(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        List<Empleado> empleados = emplDAO.listar();
        request.setAttribute("empleados", empleados);
        request.getRequestDispatcher("Empleado.jsp").forward(request, response);
        
    }
    
    /**
     * Llama a la función agregar de la clase EmpleadoDAO, el cual permite agregar un nuevo 
     * empleado a la base de datos
     * 
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     * @throws ServletException 
     */
    private void agregarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        
        //Obtener todos los valores ingresados en el formulario
        String rut = request.getParameter("txtRut");
        String nom = request.getParameter("txtNombres");
        String tel = request.getParameter("txtTel");
        String est = request.getParameter("txtEstado");
        String user = request.getParameter("txtUser");
        
        //Crecion de objeto Empleado y asignacion de valores a sus variables
        Empleado empl = new Empleado();
        empl.setRut(rut);
        empl.setNom(nom);
        empl.setTel(tel);
        empl.setEstado(est);
        empl.setUser(user);
        
        //Ejecución de la funcion para agregar el empleado a la base de datos
        this.emplDAO.agregar(empl);

        this.listarEmpleados(request, response);
    }
    
    private void editarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        idEdit = Integer.parseInt(request.getParameter("id"));
        Empleado emp = emplDAO.listarId(idEdit);
    
        //Se rellena los campos del formulario con los datos obtenidos de la busqueda anterior
        request.setAttribute("empleado", emp);
        this.listarEmpleados(request, response);
    }
    
    private void actualizarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        String rut = request.getParameter("txtRut");
        String nom = request.getParameter("txtNombres");
        String tel = request.getParameter("txtTel");
        String estado = request.getParameter("txtEstado");
        String user = request.getParameter("txtUser");
        
        Empleado empl = new Empleado();
        empl.setId(idEdit);
        empl.setRut(rut);
        empl.setNom(nom);
        empl.setTel(tel);
        empl.setEstado(estado);
        empl.setUser(user);
        
        emplDAO.actualizar(empl);
        
        this.listarEmpleados(request, response);
        
    }
    
    private void eliminarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        int idEmpleado = Integer.parseInt(request.getParameter("id"));
        emplDAO.delete(idEmpleado);
        this.listarEmpleados(request, response);

    }

}
