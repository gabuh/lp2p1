<%@ page import="model.Campeonato" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: Gabriel
  Date: 11/26/2023
  Time: 12:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="WEB-INF/jsp/header.jsp"/>
<body>
<jsp:include page="WEB-INF/jsp/navbar.jsp"/>


<section class="hero is-primary is-medium">

    <div class="hero-body">
        <div class="container has-text-centered">


            <h1 class="title" >${campeonato.nome}</h1>
            <h3 class="subtitle">Campeonato</h3>
            <%
                if (session.getAttribute("username") != null && session.getAttribute("username").equals("admin")){
            %>
            <div class="container is-flex">
                <button class="button is-dark js-modal-trigger" data-target="criarTime">Criar Time</button>
            </div>

            <div id="criarTime" class="modal has-text-left">
                <div class="modal-background"></div>
                <div class="modal-card">
                    <header class="modal-card-head">
                        <p class="modal-card-title">Criando Time</p>
                        <button class="delete" aria-label="close"></button>
                    </header>


                    <section class="modal-card-body">

                        <form id="timeForm">

                            <div class="field">
                                <label class="label">Nome</label>
                                <p class="control has-icons-left has-icons-right">
                                    <input class="input" type="text" placeholder="Username" name="nome">
                                    <span class="icon is-small is-left">
                                        <i class="ion-person"></i>
                                    </span>
                                </p>
                            </div>


                            <div class="field options">
                                <label class="label">Tecnico</label>
                                <div class="control">
                                    <div class="select">
                                        <select name="jogadores">
                                            <option> </option>
                                            <c:forEach var="tecnico" items="${tecnicos}">
                                                <option> ${tecnico.nome} - ${tecnico.nacionalidade}}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>


                            <div class="field options">
                                <label class="label">Jogadores</label>
                                <div class="control">
                                    <div class="select">
                                        <select name="jogadores"   id="jogadorSelect">
                                            <option> </option>
                                            <option> nome - jogador.altura - jogador.peso - ogador.nacionalidad  - 887</option>
                                            <c:forEach var="jogador" items="${jogadores}">
                                            <option> ${jogador.nome} - ${jogador.altura} - ${jogador.peso} - ${jogador.nacionalidade} - ${jogador.id}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <p class="button" id="addJogador"> Add</p>

                            <div class="table-container">
                                <table class="table is-fullwidth" id="jogadoresTable">
                                    <thead>
                                    <tr>
                                        <th>Nome</th>
                                        <th>Altura</th>
                                        <th>Peso</th>
                                        <th>Nacionalidade</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <!-- Existing rows or dynamically added rows will go here -->
                                    </tbody>
                                </table>
                            </div>


                            <div class="field">
                                <p class="control">
                                    <button id="submitBtn" type="button" class="button is-black" >
                                        Criar
                                    </button>
                                </p>
                            </div>

                        </form>
                    </section>

                    <footer class="modal-card-foot">
                        <button class="button">Cancel</button>
                    </footer>
                </div>
            </div>


           <script src="${pageContext.request.contextPath}/script/campeonato.js"></script>
            <%
                }
            %>
            <table class="table is-fullwidth">
                <tr>
                    <th>Nome</th>
                    <th>Empates</th>
                    <th>Vitorias</th>
                    <th>Derrotas</th>
                </tr>
            
            
            <c:forEach var="time" items="${campeonato.times}">
                <tr>
                    <td>${time.nome}</td>
                    <td>${time.empates}</td>
                    <td>${time.vitorias}</td>
                    <td>${time.derrotas}</td>
                </tr>
            </c:forEach>
            
            </table>

        </div>
    </div>
    
  
</section>



<jsp:include page="WEB-INF/jsp/footer.jsp"/>
</body>
</html>
