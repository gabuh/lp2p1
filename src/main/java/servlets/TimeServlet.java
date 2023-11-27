package servlets;


import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;

@WebServlet("/time")
@MultipartConfig
public class TimeServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String[] paramValues = req.getParameterValues(paramName);
            System.out.println("Parameter: " + paramName + ", Value: " + Arrays.toString(paramValues));
        }

            String nome = req.getParameter("nome");
            String[] jogadorIds = req.getParameterValues("jogador_id");

        if (jogadorIds != null) {
            for (String id : jogadorIds) {
                System.out.println("Jogador id : " + id);
            }
        } else {
            System.out.println("No jogador_ids received");
        }

        System.out.println(nome!=null?"nome "+nome:"Nome doesn't exist");

    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
