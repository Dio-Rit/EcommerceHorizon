/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.DAOCliente;
import Entidade.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yNot
 */
public class AcaoCliente extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AcaoCliente</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AcaoCliente at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        // processRequest(request, response);

        String param = request.getParameter("param");

        if (param.equals("EdCliente")) {
            String id = request.getParameter("id");

            Cliente usu = new DAOCliente().consultarId(Integer.parseInt(id));

            request.setAttribute("objCliente", usu);
            System.out.println(usu.getId());

            encaminharPagina("/DAOCliente/AtualizaCliente.jsp", request, response);

        } else if (param.equals("ExcluirCliente")) {

            DAOCliente b = new DAOCliente();
            b.excluir(Integer.parseInt(request.getParameter("id")));
            response.sendRedirect("/EcommerceHorizon/DAOCliente/ListarCliente.jsp");

        } else if (param.equals("ListarCliente")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String nome = request.getParameter("nome");
            String cpf = request.getParameter("cpf");
            String data_nsci = request.getParameter("data_nsci");
            String email = request.getParameter("email");
            String descricao = request.getParameter("descricao");
            String status = request.getParameter("x");

            Cliente tl = new Cliente();
            tl.setId(id);
            tl.setNome(nome);
            tl.setCpf(cpf);
            tl.setDataNsci(data_nsci);
            tl.setEmail(email);
            tl.setDecricao(descricao);
            tl.setX(status);

            response.sendRedirect("/EcommerceHorizon/DAOCliente/ListarCliente.jsp");

        }
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
        // processRequest(request, response);

        String param = request.getParameter("param");
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String data_nsci = request.getParameter("data_nsci");
        String email = request.getParameter("email");
        String descricao = request.getParameter("descricao");
        
        System.out.println(nome);

        if (param.equals("SalvarCliente")) {
            
            System.out.println(email);

            Cliente u = new Cliente();
            u.setNome(nome);
            u.setCpf(cpf);
            u.setDataNsci(data_nsci);
            u.setEmail(email);
            u.setDecricao(descricao);
            u.setX("A");

            DAOCliente c = new DAOCliente();
            c.salvar(u);
            response.sendRedirect("/EcommerceHorizon/DAOCliente/ListarCliente.jsp");

        } else if (param.equals("EditarCliente")) {

            Cliente u = new Cliente();

            u.setId(Integer.parseInt(request.getParameter("id")));
            u.setNome(request.getParameter("nome"));
            u.setCpf(request.getParameter("cpf"));
            u.setDataNsci(request.getParameter("data_nsci"));
            u.setEmail(request.getParameter("email"));
            u.setDecricao(request.getParameter("descricao"));
            u.setX("A");

            DAOCliente a = new DAOCliente();
            a.atualizar(u);
            response.sendRedirect("/EcommerceHorizon/DAOCliente/ListarCliente.jsp");

        }
    }

    private void encaminharPagina(String pagina, HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher rd = request.getRequestDispatcher(pagina);
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println("Erro ao encaminhar: " + e);
        }
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

}
