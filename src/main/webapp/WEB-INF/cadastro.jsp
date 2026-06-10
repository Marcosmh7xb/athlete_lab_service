<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro</title>
    <link rel="stylesheet" href="css/cadastro_style.css">
    <!-- FONTES -->
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Be+Vietnam+Pro:wght@800&display=swap" rel="stylesheet">
</head>
<body>

<img src="banco_imagens/imagens/Athlete logo white.png" class="logo">
<div class="container">

    <a href="login?tipoUsuario=${tipoUsuario}" class="voltar"> ← Voltar </a>
    <h2> Cadastro de ${tipoUsuario} </h2>
    <p class="subtitulo"> Preencha seus dados para criar sua conta e começar a acompanhar seu desempenho. </p>

    <!-- FORM FUNCIONAL -->
    <form action="cadastro" method="post">
        <!-- mantém o tipo de usuário -->
        <input type="hidden" name="tipoUsuario" value="${sessionScope.tipoUsuario}">

        <div class="form">
            <div class="campo">
                <label>Nome Completo</label>
                <input type="text" name="nome" placeholder="Seu nome completo" required >
            </div>

            <div class="campo">
                <label>E-mail ou Telefone</label>
                <input type="email" name="email" placeholder="atleta@exemplo.com" required >
            </div>

            <div class="grid">

                <div class="campo"> <label>Data de Nascimento</label>
                    <input type="date" name="dataNascimento" required >
                </div>

                <div class="campo">
                    <label>Cidade/UF</label>
                    <input type="text" name="cidadeUF" placeholder="Ex: São Paulo/SP" required >
                </div>

                <div class="campo">
                    <label>Senha</label>
                    <input type="password" name="senha" required >
                </div>

                <div class="campo">
                    <label>Telefone</label>
                    <input type="text" name="telefone" placeholder="(11) 99999-9999" required >
                </div>

            </div>

           <div class="termos">
             <label class="lembrar">
               <input type="checkbox" required>
               <span class="checkmark"></span>
               Eu concordo com os <b>Termos de Uso</b> e a <b>Política de Privacidade</b>
             </label>
           </div>

            <div class="linha"></div>

            <div class="continuar">
                <button type="submit"> Continuar </button>
            </div>

            <div class="login">
                <p>Já tem uma conta?</p>

                <a href="login?tipoUsuario=${tipoUsuario}" class="login-btn"> Faça login </a>
            </div>
        </div>
    </form>
</div>

<footer> © 2026 AthleteLab. Todos os direitos reservados | Termo de Uso | Privacidade </footer>

</body>
</html>