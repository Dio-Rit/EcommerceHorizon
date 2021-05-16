/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.DAOUsuario;
import Entidade.Usuario;
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
public class AcaoUsuario extends HttpServlet {

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
            out.println("<title>Servlet AcaoUsuario</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AcaoUsuario at " + request.getContextPath() + "</h1>");
            out.println("Ação Realizada com sucesso");
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
        //processRequest(request, response);

        String param = request.getParameter("param");

        if (param.equals("EdUsuario")) {
            String id = request.getParameter("id");

            Usuario usu = new DAOUsuario().consultarId(Integer.parseInt(id));

            request.setAttribute("objUsuario", usu);
            System.out.println(usu.getId());

            encaminharPagina("/DAOUsuario/AtualizaUsuario.jsp", request, response);

        } else if (param.equals("ExcluirUsuario")) {

            DAOUsuario b = new DAOUsuario();
            b.excluir(Integer.parseInt(request.getParameter("id")));
            response.sendRedirect("/EcommerceHorizon/DAOUsuario/ListarUsuarios.jsp");

        } else if (param.equals("ListarUsuario")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String nome = request.getParameter("nome");
            String login = request.getParameter("login");
            String senha = request.getParameter("senha");
            String status = request.getParameter("x");

            Usuario tl = new Usuario();
            tl.setId(id);
            tl.setNome(nome);
            tl.setLogin(login);
            tl.setSenha(senha);
            tl.setX(status);

            response.sendRedirect("/EcommerceHorizon/DAOUsuario/ListarUsuarios.jsp");

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
        String Nome = request.getParameter("Nome");
        String Login = request.getParameter("Login");
        String Senha = request.getParameter("Senha");

        if (param.equals("SalvarUsuario")) {

            Usuario u = new Usuario();
            u.setNome(Nome);
            u.setLogin(Login);
            u.setSenha(Senha);
            u.setX("A");

            DAOUsuario c = new DAOUsuario();
            c.salvar(u);
            response.sendRedirect("/EcommerceHorizon/DAOUsuario/ListarUsuarios.jsp");

        } else if (param.equals("EditarUsuario")) {

            Usuario u = new Usuario();

            u.setId(Integer.parseInt(request.getParameter("id")));
            u.setNome(request.getParameter("Nome"));
            u.setLogin(request.getParameter("Login"));
            u.setSenha(request.getParameter("Senha"));
            u.setX("A");

            DAOUsuario a = new DAOUsuario();
            a.atualizar(u);
            response.sendRedirect("/EcommerceHorizon/DAOUsuario/ListarUsuarios.jsp");

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
