<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Perfil Atleta | Athlete Lab</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/perfil_atleta_extra_style.css">
</head>

<body>

<div class="form-card">

    <h2>Configurações de Atleta</h2>

    <form class="perfil-form"
          action="${pageContext.request.contextPath}/editar-perfil-atleta"
          method="post"
          enctype="multipart/form-data">

        <!-- ================= DADOS DA CONTA ================= -->

        <h3>Dados da Conta</h3>

        <div class="input-group">
            <label>Foto de Perfil</label>
            <input type="file"
                   name="foto"
                   accept="image/*">
        </div>

        <div class="input-group">
            <label>Nome Completo</label>
            <input type="text"
                   name="nome"
                   value="${perfil.nome}"
                   required>
        </div>

        <div class="row">

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

        <!-- ================= PERFIL DO ATLETA ================= -->

        <h3>Perfil do Atleta</h3>

        <div class="input-group">
            <label>Modalidade</label>
            <input type="text"
                   name="modalidade"
                   value="${perfil.perfilAtleta.modalidade}"
                   placeholder="Ex: Basquete, Natação..."
                   required>
        </div>

        <div class="row">

            <div class="input-group">
                <label>Altura (m)</label>
                <input type="number"
                       step="0.01"
                       name="altura"
                       value="${perfil.perfilAtleta.altura}"
                       placeholder="1.75">
            </div>

            <div class="input-group">
                <label>Peso (kg)</label>
                <input type="number"
                       step="0.1"
                       name="peso"
                       value="${perfil.perfilAtleta.peso}"
                       placeholder="70.5">
            </div>

        </div>

        <div class="input-group">
            <label>Nível de Experiência</label>

            <select name="nivelExperiencia">

                <option value="Iniciante"
                    ${perfil.perfilAtleta.nivelExperiencia == 'Iniciante' ? 'selected' : ''}>
                    Iniciante
                </option>

                <option value="Intermediário"
                    ${perfil.perfilAtleta.nivelExperiencia == 'Intermediário' ? 'selected' : ''}>
                    Intermediário
                </option>

                <option value="Avançado"
                    ${perfil.perfilAtleta.nivelExperiencia == 'Avançado' ? 'selected' : ''}>
                    Avançado
                </option>

            </select>

        </div>

        <div class="input-group">
            <label>Objetivo</label>

            <input type="text"
                   name="objetivo"
                   value="${perfil.perfilAtleta.objetivo}"
                   placeholder="Ex: Ganhar massa, Maratona...">
        </div>

        <div class="input-group">
            <label>Dias Disponíveis (Ex: Seg, Qua, Sex)</label>

            <input type="text"
                   name="diasSemana"
                   value="${perfil.perfilAtleta.diasSemana}"
                   placeholder="Seg, Ter, Qua">
        </div>

        <div class="row">

            <div class="input-group">

                <label>Ambiente de Treino</label>

                <select name="ambiente">

                    <option value="Academia"
                        ${perfil.perfilAtleta.ambiente == 'Academia' ? 'selected' : ''}>
                        Academia
                    </option>

                    <option value="Ar Livre"
                        ${perfil.perfilAtleta.ambiente == 'Ar Livre' ? 'selected' : ''}>
                        Ar Livre
                    </option>

                    <option value="Casa"
                        ${perfil.perfilAtleta.ambiente == 'Casa' ? 'selected' : ''}>
                        Casa
                    </option>

                    <option value="Quadra"
                        ${perfil.perfilAtleta.ambiente == 'Quadra' ? 'selected' : ''}>
                        Quadra
                    </option>

                    <option value="Piscina"
                        ${perfil.perfilAtleta.ambiente == 'Piscina' ? 'selected' : ''}>
                        Piscina
                    </option>

                </select>

            </div>

            <div class="input-group">

                <label>Sexo</label>

                <select name="sexo">

                    <option value="Masculino"
                        ${perfil.perfilAtleta.sexo == 'Masculino' ? 'selected' : ''}>
                        Masculino
                    </option>

                    <option value="Feminino"
                        ${perfil.perfilAtleta.sexo == 'Feminino' ? 'selected' : ''}>
                        Feminino
                    </option>

                    <option value="Outro"
                        ${perfil.perfilAtleta.sexo == 'Outro' ? 'selected' : ''}>
                        Outro
                    </option>

                </select>

            </div>

        </div>

        <div class="input-group">

            <label>Observações / Restrições</label>

            <textarea name="restricaoFisica"
                      rows="4"
                      placeholder="Informe alguma restrição física ou observação importante...">${perfil.perfilAtleta.restricaoFisica}</textarea>

        </div>

        <div class="buttons">

            <button type="submit" class="btn-save">
                GUARDAR ALTERAÇÕES
            </button>

            <a href="${pageContext.request.contextPath}/perfil-atleta"
               class="btn-cancel">
                Cancelar e Voltar
            </a>

        </div>

    </form>

</div>

</body>

</html>