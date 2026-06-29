<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="pt-PT">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/perfil_treinador_extra_style.css">
    <title>Editar Perfil | Athlete Lab</title>
</head>

<body>

<div class="form-card">

    <h2>Configurações de Perfil</h2>

    <form action="${pageContext.request.contextPath}/editar-perfil"
          method="post"
          enctype="multipart/form-data">

        <h3>Dados da Conta</h3>

        <div class="input-group">
            <label>Foto de Perfil</label>
            <input type="file" name="foto" accept="image/*">
        </div>

        <div class="input-group">
            <label>Nome Completo</label>
            <input type="text"
                   name="nome"
                   value="${perfil.nome}"
                   required>
        </div>

        <div class="input-group">
            <label>E-mail</label>
            <input type="email"
                   name="email"
                   value="${perfil.email}"
                   required>
        </div>

        <div class="form-row">

            <div class="input-group">
                <label>Telefone</label>
                <input type="text"
                       name="telefone"
                       value="${perfil.telefone}">
            </div>

            <div class="input-group">
                <label>Cidade / UF</label>
                <input type="text"
                       name="cidadeUF"
                       value="${perfil.cidadeUF}">
            </div>

        </div>

        <div class="input-group">
            <label>Data de Nascimento</label>
            <input type="date"
                   name="dataNascimento"
                   value="${perfil.dataNascimento}">
        </div>

        <h3>Perfil Profissional</h3>

        <div class="input-group">
            <label>Modalidade</label>
            <input type="text"
                   name="modalidade"
                   value="${perfil.perfilTreinador.modalidade}"
                   placeholder="Ex: Musculação, Crossfit..."
                   required>
        </div>

        <div class="form-row">

            <div class="input-group">

                <label>Nível de Experiência</label>

                <select name="nivelExperiencia">

                    <option value="Iniciante"
                        ${perfil.perfilTreinador.nivelExperiencia == 'Iniciante' ? 'selected' : ''}>
                        Iniciante
                    </option>

                    <option value="Intermediário"
                        ${perfil.perfilTreinador.nivelExperiencia == 'Intermediário' ? 'selected' : ''}>
                        Intermediário
                    </option>

                    <option value="Avançado"
                        ${perfil.perfilTreinador.nivelExperiencia == 'Avançado' ? 'selected' : ''}>
                        Avançado / Especialista
                    </option>

                </select>

            </div>

            <div class="input-group">

                <label>Sexo Atendido</label>

                <select name="sexo">

                    <option value="Masculino"
                        ${perfil.perfilTreinador.sexo == 'Masculino' ? 'selected' : ''}>
                        Masculino
                    </option>

                    <option value="Feminino"
                        ${perfil.perfilTreinador.sexo == 'Feminino' ? 'selected' : ''}>
                        Feminino
                    </option>

                    <option value="Ambos"
                        ${perfil.perfilTreinador.sexo == 'Ambos' ? 'selected' : ''}>
                        Ambos
                    </option>

                </select>

            </div>

        </div>

        <div class="form-row">

            <div class="input-group">

                <label>Objetivo Profissional</label>

                <input type="text"
                       name="objetivo"
                       value="${perfil.perfilTreinador.objetivo}"
                       placeholder="Ex: Hipertrofia, Emagrecimento...">

            </div>

            <div class="input-group">

                <label>Ambiente de Treino</label>

                <input type="text"
                       name="ambiente"
                       value="${perfil.perfilTreinador.ambiente}"
                       placeholder="Ex: Academia, Online, Ar Livre...">

            </div>

        </div>

        <div class="input-group">

            <label>Observações / Restrições</label>

            <textarea
                    name="restricaoFisica"
                    rows="4"
                    placeholder="Notas sobre restrições físicas ou avisos...">${perfil.perfilTreinador.restricaoFisica}</textarea>

        </div>

        <button type="submit" class="btn-save">
            GUARDAR TODAS AS ALTERAÇÕES
        </button>

        <a href="${pageContext.request.contextPath}/perfil-treinador"
           class="btn-cancel">
            Cancelar e Voltar
        </a>

    </form>

</div>

</body>
</html>