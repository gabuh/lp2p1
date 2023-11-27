<%@ page import="dao.impl.CampeonatoDao" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Campeonato" %>
<html lang="br">
<jsp:include page="WEB-INF/jsp/header.jsp"/>
<body>
<jsp:include page="WEB-INF/jsp/navbar.jsp"/>


<div class="hero is-fullheight has-background-link-light">
    <div class="hero-body">


        <div class="container">
                
            <div class="columns has-text-centered has-text-weight-bold is-desktop">
                <div class="column content is-large">
                    <h3>
                        Dentro do mundo do esporte tem lugar para todos.
                    </h3>
                </div>

                <div class="column">

                    <div class="container content has-text-right">
                        <h5>Clique sobre o icone para saber sobre cada Campeonato !</h5>
                    </div>



                    <%
                        CampeonatoDao campeonatoDao = new CampeonatoDao();
                        List<Campeonato> campeonatos = campeonatoDao.findAll();
                        Campeonato campeonato;
                        for (int i=0; i < campeonatos.size(); i++){
                            if (i % 2 == 0){
                    %>



                    <div class="columns is-mobile">

                        <%
                            for (int j=i; j<=(i+1); j++){
                            if ((campeonato = campeonatos.get(j)) != null){
                        %>

                        <div class="column campeonato" id="<%=campeonato.getId()%>">
                            <div class="box has-background-info content">
                                <figure class="image">
                                    <img class="is-rounded" src="${pageContext.request.contextPath}/assets/trophy.png" alt="trophy">
                                </figure><p> <%=campeonato.getNome()%> </p>
                            </div>
                        </div>

                        <%
                                }
                            }
                        %>

                    </div>


                    <%
                            }
                        }
                        campeonatoDao.close();
                    %>


                </div>

            </div> <!-- columns -->

        </div>


    </div>
</div>


<jsp:include page="WEB-INF/jsp/footer.jsp"/>
</body>
</html>
