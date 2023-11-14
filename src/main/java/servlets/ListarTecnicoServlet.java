package servlets;

import dao.impl.TecnicoDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Tecnico;

import java.io.IOException;
import java.util.List;

@WebServlet("/ListarTecnico")
public class ListarTecnicoServlet extends HttpServlet {

    private TecnicoDao tecnicoDao;

    @Override
    public void destroy() {
        super.destroy();
        tecnicoDao = new TecnicoDao();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Tecnico> tecnicos = tecnicoDao.findAll();

        req.setAttribute("tecnicos", tecnicos);
        RequestDispatcher dispatcher = req.getRequestDispatcher("listarTecnico.jsp");
        dispatcher.forward(req, resp);
    }


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        tecnicoDao.close();
    }
}
