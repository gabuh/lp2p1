<%@ page import="enums.Nacionalidades" %><%--
  Created by IntelliJ IDEA.
  User: Gabriel
  Date: 11/14/2023
  Time: 11:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar" role="navigation" aria-label="main navigation">
  <div class="navbar-brand">

    <a class="navbar-item $navbar-item" href="">

      <img src="${pageContext.request.contextPath}/assets/logo.svg" width="112" height="28" alt="logo">
    </a>

    <a role="button" class="navbar-burger burger" aria-label="menu" aria-expanded="false" data-target="navbarBasicExample">
      <span aria-hidden="true"></span>
      <span aria-hidden="true"></span>
      <span aria-hidden="true"></span>
    </a>
  </div>
  <div id="navbarBasicExample" class="navbar-menu">
    <div class="navbar-start">

      <a class="navbar-item" href="home">
        Home
      </a>
      <%
        if(session.getAttribute("username") != null && !session.getAttribute("username").equals("admin")){
      %>
      <a class="navbar-item" href="time">
        Acessar meu time
      </a>
      <%
        }
      %>



      <%
        if(session.getAttribute("username") != null && session.getAttribute("username").equals("admin")){
      %>
      <a class="navbar-item" href="campeonato">
        Campeonatos
      </a>
      <a class="navbar-item" href="partida">
        Partidas
      </a>
      <a class="navbar-item" href="tecnico">
        Tecnicos
      </a>

      <a class="navbar-item" href="time">
        Times
      </a>

      <%
        }
      %>


      <div class="navbar-item has-dropdown is-hoverable">
        <a class="navbar-link">
          More
        </a>
        <div class="navbar-dropdown">
          <a class="navbar-item">
            About
          </a>
          <%
            if (session.getAttribute("username") != null){
          %>
          <hr class="navbar-divider">
          <a class="navbar-item">
            Logout
          </a>
          <%
            }
          %>
        </div>
      </div>
    </div>

    <div class="navbar-end">
      <div class="navbar-item">

        <%
          if (session.getAttribute("username") != null){
        %>
        <a class="button is-black is-rounded">
          <%=  ( (String) session.getAttribute("username") ).substring(0,1)
          %>
        </a>


        <%
          } else {
        %>

        <div class="buttons">
          <a class="button is-black js-modal-trigger" data-target="registerButton">
            <strong>Sign up</strong>
          </a>
          <a class="button is-light js-modal-trigger" data-target="loginButton">
            Log in
          </a>
        </div>

        <%
          }
        %>


      </div>
    </div>

  </div>
</nav>

<%
  if (session.getAttribute("username") == null){
%>


<div id="loginButton" class="modal">
  <div class="modal-background"></div>
  <div class="modal-card">
    <header class="modal-card-head">
      <p class="modal-card-title">Faça o seu login !</p>
      <button class="delete" aria-label="close"></button>
    </header>


    <section class="modal-card-body">

      <form action="login" method="post">

        <div class="field login">
          <label class="label">Username</label>
          <p class="control has-icons-left has-icons-right">
            <input class="input" type="text" placeholder="Username" name="usernameLogin">
            <span class="icon is-small is-left">
              <i class="ion-person"></i>
            </span>
          </p>
        </div>

        <div class="field login">
          <label class="label">Password</label>
          <p class="control has-icons-left has-icons-right">
            <input class="input" type="password" placeholder="Password" name="passwordLogin">
            <span class="icon is-small is-left">
              <i class="ion-key"></i>
            </span>
          </p>
        </div>

        <div class="field">
          <p class="control">
            <button id="submitLogin" class="button is-black" disabled>
              Login
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


<div id="registerButton" class="modal">
  <div class="modal-background"></div>
  <div class="modal-card">
    <header class="modal-card-head">
      <p class="modal-card-title">Faça o seu Cadastro!</p>
      <button class="delete" aria-label="close"></button>
    </header>


    <section class="modal-card-body">

      <form action="register" method="post">


        <div class="field register">
          <label class="label">Name</label>
          <div class="control has-icons-left has-icons-right">
            <input class="input" type="text" placeholder="Alfredo de souza" name="nome">
            <span class="icon is-small is-left">
              <i class="ion-card"></i>
            </span>
          </div>
        </div>

        <div class="field register" id="usernameField">
          <label class="label">Username</label>
          <div class="control has-icons-left has-icons-right" id="usernameControl">
            <input class="input" type="text"  placeholder="Text" name="username">
            <span class="icon is-small is-left">
              <i class="ion-person"></i>
            </span>
          </div>
        </div>


        <div class="field register">
          <label class="label">Email</label>
          <p class="control has-icons-left has-icons-right">
            <input class="input" type="email" placeholder="Email" name="email">
            <span class="icon is-left">
              <i class="ion-email"></i>
            </span>
          </p>
        </div>



        <div class="field register">
          <label class="label">Data de Nascimento</label>
          <p class="control has-icons-left has-icons-right">
            <input class="input"  type="date" name="dataNascimento" >
            <span class="icon is-left">
              <i class="ion-calendar"></i>
            </span>
          </p>
        </div>


        <div class="field register options">
          <label class="label">Nacionalidade</label>
          <div class="control">
            <div class="select">
              <select name="nacionalidade">
                <option> </option>

            <%
              for ( Nacionalidades n: Nacionalidades.values()) {
            %>
                <option>
                  <%= n.getDescricao() %>
                </option>
              <%
                }
              %>
              </select>
            </div>
          </div>
        </div>


        <div class="field register">
          <label class="label">Senha</label>
          <p class="control has-icons-left has-icons-right">
            <input class="input" type="password" placeholder="Password" id="firstRgPassword" name="senha">
            <span class="icon is-left">
              <i class="ion-key"></i>
            </span>
          </p>
        </div>

        <div class="field register">
          <label class="label">Repita a senha</label>
          <p class="control has-icons-left has-icons-right">
            <input class="input" type="password" placeholder="Password" id="secondRgPassword" name="senha">
            <span class="icon is-left">
              <i class="ion-key"></i>
            </span>
          </p>
        </div>


        <div class="field">
          <p class="control">
            <button id="submitRegister" disabled class="button is-black" >
              Cadastrar
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


<script src="${pageContext.request.contextPath}/script/navbar.js"></script>
<%
  }
%>

<%
  if (session.getAttribute("modalMsg") != null && ((boolean)session.getAttribute("modalMsg"))){
%>
<div class="modal is-active">
    <%
    session.setAttribute("modalMsg", false);
      }else{
    %>
  <div class="modal">
    <%
      }
    %>
    <div class="modal-background"></div>
    <div class="modal-content">
      <div class="box content is-large">
        <%
          if (session.getAttribute("modalMsgContent") != null){
        %>
        <p><%=session.getAttribute("modalMsgContent")%></p>
        <%
          session.setAttribute("modalMsgContent",null);
        }else{
        %>
        <p>${pageScope.modalmsg}</p>
        <%
          }
        %>
      </div>
    </div>
    <button class="modal-close is-large" aria-label="close"></button>
  </div>

