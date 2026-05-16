<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Editar Perfil Atleta | Athlete Lab</title>
    <style>
        body { background-color: #000; color: white; font-family: sans-serif; display: flex; justify-content: center; align-items: center; min-height: 100vh; margin: 0; padding: 40px 0; }
        .form-card { background: #181818; padding: 40px; border-radius: 15px; width: 550px; border: 1px solid #333; box-shadow: 0 10px 30px rgba(0,0,0,0.5); }
        h2 { color: red; margin-top: 0; text-align: center; text-transform: uppercase; letter-spacing: 1px; }
        h3 { color: #fff; border-bottom: 1px solid red; padding-bottom: 5px; margin-top: 30px; font-size: 1rem; text-transform: uppercase; }
        .input-group { margin-bottom: 15px; }
        label { display: block; color: #888; margin-bottom: 5px; font-size: 0.85rem; font-weight: bold; }
        input, select, textarea { width: 100%; padding: 12px; background: #222; border: 1px solid #444; color: white; border-radius: 5px; box-sizing: border-box; font-family: sans-serif; }
        input:focus, select:focus, textarea:focus { border-color: red; outline: none; }
        .row { display: flex; gap: 15px; }
        .row .input-group { flex: 1; }
        .btn-save { background: red; color: white; border: none; width: 100%; padding: 15px; border-radius: 5px; cursor: pointer; font-weight: bold; margin-top: 25px; font-size: 1rem; transition: 0.3s; }
        .btn-save:hover { background: #cc0000; }
        .btn-cancel { display: block; text-align: center; color: #666; text-decoration: none; margin-top: 15px; font-size: 0.9rem; }
    </style>
</head>
<body>

<div class="form-card">
    <h2>Configurações de Atleta</h2>

    <form action="${pageContext.request.contextPath}/editar-perfil-atleta" method="post" enctype="multipart/form-data">

        <h3>Dados da Conta</h3>
        <div class="input-group">
            <label>Foto de Perfil</label>
            <input type="file" name="foto" accept="image/*">
        </div>
        <div class="input-group">
            <label>Nome Completo</label>
            <input type="text" name="nome" value="${perfil.nome}" required>
        </div>
        <div class="row">
            <div class="input-group">
                <label>Telefone</label>
                <input type="text" name="telefone" value="${perfil.telefone}">
            </div>
            <div class="input-group">
                <label>Cidade / UF</label>
                <input type="text" name="cidadeUF" value="${perfil.cidadeUF}">
            </div>
        </div>
        <div class="input-group">
            <label>Data de Nascimento</label>
            <input type="date" name="dataNascimento" value="${perfil.dataNascimento}">
        </div>

        <h3>Perfil do Atleta</h3>

        <div class="input-group">
            <label>Modalidade</label>
            <input type="text" name="modalidade" value="${perfil.perfilAtleta.modalidade}" placeholder="Ex: Basquete, Natação..." required>
        </div>

        <div class="row">
            <div class="input-group">
                <label>Altura (m)</label>
                <input type="number" step="0.01" name="altura" value="${perfil.perfilAtleta.altura}" placeholder="1.75">
            </div>
            <div class="input-group">
                <label>Peso (kg)</label>
                <input type="number" step="0.1" name="peso" value="${perfil.perfilAtleta.peso}" placeholder="70.5">
            </div>
        </div>

        <div class="input-group">
            <label>Nível de Experiência</label>
            <select name="nivelExperiencia">
                <option value="Iniciante" ${perfil.perfilAtleta.nivelExperiencia == 'Iniciante' ? 'selected' : ''}>Iniciante</option>
                <option value="Intermediário" ${perfil.perfilAtleta.nivelExperiencia == 'Intermediário' ? 'selected' : ''}>Intermediário</option>
                <option value="Avançado" ${perfil.perfilAtleta.nivelExperiencia == 'Avançado' ? 'selected' : ''}>Avançado</option>
            </select>
        </div>

        <div class="input-group">
            <label>Objetivo</label>
            <input type="text" name="objetivo" value="${perfil.perfilAtleta.objetivo}" placeholder="Ex: Ganhar massa, Maratona...">
        </div>

        <div class="input-group">
            <label>Dias Disponíveis (Ex: Seg, Qua, Sex)</label>
            <input type="text" name="diasSemana" value="${perfil.perfilAtleta.diasSemana}">
        </div>

        <div class="input-group">
            <label>Observações / Restrições</label>
            <textarea name="restricaoFisica" rows="3">${perfil.perfilAtleta.restricaoFisica}</textarea>
        </div>

        <button type="submit" class="btn-save">GUARDAR ALTERAÇÕES</button>
        <a href="${pageContext.request.contextPath}/perfil-atleta" class="btn-cancel">Cancelar e Voltar</a>

    </form>
</div>

</body>
</html>