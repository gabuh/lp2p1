package servlets;

import dao.impl.TecnicoDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Tecnico;

import java.io.IOException;

@WebServlet("/AddTecnico")
public class AddTecnicoServlet extends HttpServlet {

    private TecnicoDao tecnicoDao;


    @Override
    public void init() throws ServletException {
        super.init();
        tecnicoDao = new TecnicoDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("cadTecnico.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Tecnico tecnico = new Tecnico(
//            req.getParameter("nome"),
//            req.getParameter("idade"),
//            req.getParameter("nacionalidade"),
//            req.getParameter("email"),
//            req.getParameter("senha")
//        );
//
//
//
//        tecnicoDao.persist(tecnico);
//
//
//        resp.sendRedirect("ListarTecnico");
    }


    @Override
    public void destroy() {
        super.destroy();
        tecnicoDao.close();
    }


}
