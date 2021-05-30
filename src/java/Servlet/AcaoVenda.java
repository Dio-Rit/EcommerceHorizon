/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Apoio.Formatacao;
import DAO.DAOCliente;
import DAO.DAOProduto;
import DAO.VendaDAO;
import DAO.VendaProdutoDAO;
import Entidade.Cliente;
import Entidade.Produto;
import Entidade.Venda;
import Entidade.VendaProduto;
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
public class AcaoVenda extends HttpServlet {

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
            out.println("<title>Servlet AcaoVenda</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AcaoVenda at " + request.getContextPath() + "</h1>");
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

        String param = request.getParameter("param");
        System.out.println(param);
        String id = request.getParameter("id");

        if (param.equals("SelecionaCliente")) {

            Cliente usu = new DAOCliente().consultarId(Integer.parseInt(id));

            request.setAttribute("objCliente", usu);

            encaminharPagina("CadastroVenda1.jsp", request, response);

        } else if (param.equals("SelecionaProduto")) {

            Produto usu = new DAOProduto().consultarId(Integer.parseInt(id));

            request.setAttribute("objProduto", usu);

            encaminharPagina("CadastroProdutosVenda1.jsp", request, response);
  
        } else if (param.equals("ExcluirVenda")) {
            VendaDAO b = new VendaDAO();
            VendaProdutoDAO s = new VendaProdutoDAO();
            s.excluir1(Integer.parseInt(request.getParameter("id")));
            b.excluir(Integer.parseInt(request.getParameter("id")));
            response.sendRedirect("ListarVenda.jsp");
            
            

            
            
            
        } else if (param.equals("ExcluirVenda1")) {

            VendaDAO b = new VendaDAO();
            Venda x = b.PegaUltimoID();
            b.excluir(x.getId());
            response.sendRedirect("ListarVenda.jsp");

            
                
            
            
            
        } else if (param.equals("ExcluirProdutoVenda")) {

            VendaProdutoDAO a = new VendaProdutoDAO();
            a.excluir(Integer.parseInt(id));
            response.sendRedirect("CadastroProdutosVenda.jsp");
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

        String param = request.getParameter("param");
        String id = request.getParameter("id");

        if (param.equals("SalvarVenda")) {

            Venda v = new Venda();
            v.setData(Formatacao.getDataAtual());
            v.setClienteId(Integer.parseInt(id));
            v.setValorTotal(0);
            v.setX("A");

            VendaDAO x = new VendaDAO();
            x.salvar(v);
            Venda venda = new VendaDAO().consultarId((x.obterLancamentoId()));

            request.setAttribute("objCliente", venda);
            encaminharPagina("CadastroProdutosVenda.jsp", request, response);

        } else if (param.equals("SalvarProduto")) {

            DAOProduto p = new DAOProduto();
            VendaDAO k = new VendaDAO();

            String produto = request.getParameter("Produto_id");
            String quantidade = request.getParameter("Quantidade");

            Venda y = k.PegaUltimoID();
            Produto z = p.consultarId(Integer.parseInt(produto));

            VendaProduto vp = new VendaProduto();

            vp.setPreco(z.getPreco());
            vp.setProdutoId(Integer.parseInt(produto));
            vp.setQuantidade(Integer.parseInt(quantidade));
            vp.setVendaId(y.getId());
            vp.setVendaClienteId(y.getClienteId());

            VendaProdutoDAO f = new VendaProdutoDAO();
            f.salvar(vp);

            request.setAttribute("objVenda", y);
            encaminharPagina("CadastroProdutosVenda.jsp", request, response);

        } else if (param.equals("FinalizarVenda")) {

            String total = request.getParameter("ValorTotal");
            String venda = request.getParameter("Venda");
            System.out.println("Valor Total= " + total);
            System.out.println("Venda do finaliza = " + venda);

            VendaDAO v = new VendaDAO();

            v.uptadeTotal(Integer.parseInt(venda), Double.parseDouble(total));

            response.sendRedirect("ListarVenda.jsp");

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
