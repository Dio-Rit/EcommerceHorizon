/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.DAOFornecedor;
import Entidade.Fornecedor;
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
public class AcaoFornecedor extends HttpServlet {

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
            out.println("<title>Servlet AcaoFornecedor</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AcaoFornecedor at " + request.getContextPath() + "</h1>");
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

        if (param.equals("EdFornecedor")) {
            String id = request.getParameter("id");

            Fornecedor usu = new DAOFornecedor().consultarId(Integer.parseInt(id));

            request.setAttribute("objFornecedor", usu);
            System.out.println(usu.getId());

            encaminharPagina("/DAOFornecedor/AtualizaFornecedor.jsp", request, response);

        } else if (param.equals("ExcluirFornecedor")) {

            DAOFornecedor b = new DAOFornecedor();
            b.excluir(Integer.parseInt(request.getParameter("id")));
            response.sendRedirect("/EcommerceHorizon/DAOFornecedor/ListarFornecedor.jsp");

        } else if (param.equals("ListarFornecedor")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String nome = request.getParameter("nome");
            String cnpj = request.getParameter("cnpj");
            String telefone = request.getParameter("telefone");
            String email = request.getParameter("email");
            String status = request.getParameter("x");

            Fornecedor tl = new Fornecedor();
            tl.setId(id);
            tl.setNome(nome);
            tl.setCnpj(cnpj);
            tl.setTelefone(telefone);
            tl.setEmail(email);
            tl.setX(status);

            response.sendRedirect("/EcommerceHorizon/DAOFornecedor/ListarFornecedor.jsp");

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
        //  processRequest(request, response);

        String param = request.getParameter("param");
        String nome = request.getParameter("nome");
        String cnpj = request.getParameter("cnpj");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");

        if (param.equals("SalvarFornecedor")) {

            Fornecedor u = new Fornecedor();
            u.setNome(nome);
            u.setCnpj(cnpj);
            u.setTelefone(telefone);
            u.setEmail(email);
            u.setX("A");

            DAOFornecedor c = new DAOFornecedor();
            c.salvar(u);
            response.sendRedirect("/EcommerceHorizon/DAOFornecedor/ListarFornecedor.jsp");

        } else if (param.equals("EditarFornecedor")) {

            Fornecedor u = new Fornecedor();

            u.setId(Integer.parseInt(request.getParameter("id")));
            u.setNome(request.getParameter("nome"));
            u.setCnpj(request.getParameter("cnpj"));
            u.setTelefone(request.getParameter("telefone"));
            u.setEmail(request.getParameter("email")); 
            u.setX("A");

            DAOFornecedor a = new DAOFornecedor();
            a.atualizar(u);
            response.sendRedirect("/EcommerceHorizon/DAOFornecedor/ListarFornecedor.jsp");

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
