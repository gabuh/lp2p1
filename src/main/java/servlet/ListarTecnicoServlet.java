package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Tecnico;
import org.hibernate.Session;
import util.HibernateUtil;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/ListarTecnico")
public class ListarTecnicoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Tecnico> tecnicos = session.createQuery("from Tecnico", Tecnico.class).list();

        req.setAttribute("tecnicos", tecnicos);
        RequestDispatcher dispatcher = req.getRequestDispatcher("listarTecnico.jsp");
        dispatcher.forward(req, resp);
    }
}
