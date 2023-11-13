package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Jogador;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.io.IOException;

@WebServlet(value = "/AddJogador")
public class AddJogadorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("cadJogador.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int peso = Integer.parseInt(req.getParameter("peso"));
        int numeroCamisa = Integer.parseInt(req.getParameter("numeroCamisa"));
        int gols = Integer.parseInt(req.getParameter("gols"));
        int assistencias = Integer.parseInt(req.getParameter("assistencias"));

        Jogador jogador = new Jogador(
                req.getParameter("nome"),
                req.getParameter("idade"),
                req.getParameter("nacionalidade"),
                req.getParameter("altura"),
                peso,
                numeroCamisa,
                req.getParameter("posicao"),
                gols,
                assistencias
        );

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();

        session.persist(jogador);

        transaction.commit();

        resp.sendRedirect("ListarJogador");
    }
}
