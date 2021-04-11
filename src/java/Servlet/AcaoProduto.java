/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.DAOProduto;
import Entidade.Produto;
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
public class AcaoProduto extends HttpServlet {

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
            out.println("<title>Servlet AcaoProduto</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AcaoProduto at " + request.getContextPath() + "</h1>");
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
        ///  processRequest(request, response);

        String param = request.getParameter("param");

        if (param.equals("EdProduto")) {
            String id = request.getParameter("id");

            Produto usu = new DAOProduto().consultarId(Integer.parseInt(id));

            request.setAttribute("objProduto", usu);
            System.out.println(usu.getId());

            encaminharPagina("/DAOProduto/AtualizaProduto.jsp", request, response);

        } else if (param.equals("ExcluirProduto")) {

            DAOProduto b = new DAOProduto();
            b.excluir(Integer.parseInt(request.getParameter("id")));
            response.sendRedirect("/EcommerceHorizon/DAOProduto/ListarProduto.jsp");

        } else if (param.equals("ListarProduto")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String nome = request.getParameter("nome");
            int quantidade = Integer.parseInt(request.getParameter("quantidade"));
            double preco = Double.parseDouble(request.getParameter("preco"));
            String descricao = request.getParameter("descricao");
            String status = request.getParameter("x");

            Produto tl = new Produto();
            tl.setId(id);
            tl.setNome(nome);
            tl.setQuantidade(quantidade);
            tl.setPreco(preco);
            tl.setDescricao(descricao);
            tl.setX(status);

            response.sendRedirect("/EcommerceHorizon/DAOProduto/ListarProduto.jsp");

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
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        double preco = Double.parseDouble(request.getParameter("preco"));
        String descricao = request.getParameter("descricao");

        if (param.equals("SalvarProduto")) {

            Produto u = new Produto();
            u.setNome(nome);
            u.setQuantidade(quantidade);
            u.setPreco(preco);
            u.setDescricao(descricao);
            u.setX("A");

            DAOProduto c = new DAOProduto();
            c.salvar(u);
            response.sendRedirect("/EcommerceHorizon/DAOProduto/ListarProduto.jsp");

        } else if (param.equals("EditarProduto")) {

            Produto u = new Produto();

            u.setId(Integer.parseInt(request.getParameter("id")));
            u.setNome(request.getParameter("nome"));
            u.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
            u.setPreco(Double.parseDouble(request.getParameter("preco")));
            u.setDescricao("descricao");
            u.setX("A");

            DAOProduto a = new DAOProduto();
            a.atualizar(u);
            response.sendRedirect("/EcommerceHorizon/DAOProduto/ListarProduto.jsp");

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
