package servlets;

import dao.impl.JogadorDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Jogador;

import java.io.IOException;
import java.util.List;

@WebServlet("/ListarJogador")
public class ListarJogadorServlet extends HttpServlet {

    private JogadorDao jogadorDao;

    @Override
    public void init() throws ServletException {
        super.init();
        jogadorDao = new JogadorDao();
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Jogador> jogadores = jogadorDao.findAll();
        req.setAttribute("jogadores", jogadores);
        RequestDispatcher dispatcher = req.getRequestDispatcher("listarJogador.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
        jogadorDao.close();
    }


}
