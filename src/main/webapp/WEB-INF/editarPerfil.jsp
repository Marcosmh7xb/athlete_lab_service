<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-PT">
<head>
    <meta charset="UTF-8">
    <title>Editar Perfil | Athlete Lab</title>
    <style>
        body { background-color: #000; color: white; font-family: sans-serif; display: flex; justify-content: center; align-items: center; min-height: 100vh; margin: 0; padding: 40px 0; }
        .form-card { background: #181818; padding: 40px; border-radius: 15px; width: 550px; border: 1px solid #333; box-shadow: 0 10px 30px rgba(0,0,0,0.5); }
        h2 { color: red; margin-top: 0; text-align: center; text-transform: uppercase; letter-spacing: 1px; }
        h3 { color: #fff; border-bottom: 1px solid red; padding-bottom: 5px; margin-top: 30px; font-size: 1rem; text-transform: uppercase; }
        .input-group { margin-bottom: 15px; }
        label { display: block; color: #888; margin-bottom: 5px; font-size: 0.85rem; font-weight: bold; }
        input, select, textarea { width: 100%; padding: 12px; background: #222; border: 1px solid #444; color: white; border-radius: 5px; box-sizing: border-box; font-family: sans-serif; }
        input:focus, select:focus, textarea:focus { border-color: red; outline: none; }
        .btn-save { background: red; color: white; border: none; width: 100%; padding: 15px; border-radius: 5px; cursor: pointer; font-weight: bold; margin-top: 25px; font-size: 1rem; transition: 0.3s; }
        .btn-save:hover { background: #cc0000; }
        .btn-cancel { display: block; text-align: center; color: #666; text-decoration: none; margin-top: 15px; font-size: 0.9rem; }
    </style>
</head>
<body>

<div class="form-card">
    <h2>Configurações de Perfil</h2>

    <form action="${pageContext.request.contextPath}/editar-perfil" method="post" enctype="multipart/form-data">

        <h3>Dados da Conta</h3>
        <div class="input-group">
            <label>Foto de Perfil</label>
            <input type="file" name="foto" accept="image/*">
        </div>
        <div class="input-group">
            <label>Nome Completo</label>
            <input type="text" name="nome" value="${perfil.nome}" required>
        </div>
        <div class="input-group">
            <label>E-mail</label>
            <input type="email" name="email" value="${perfil.email}" required>
        </div>
        <div class="input-group">
            <label>Telefone</label>
            <input type="text" name="telefone" value="${perfil.telefone}">
        </div>
        <div class="input-group">
            <label>Cidade / UF</label>
            <input type="text" name="cidadeUF" value="${perfil.cidadeUF}">
        </div>
        <div class="input-group">
            <label>Data de Nascimento</label>
            <input type="date" name="dataNascimento" value="${perfil.dataNascimento}">
        </div>

        <h3>Perfil Profissional</h3>

        <div class="input-group">
            <label>Modalidade</label>
            <input type="text" name="modalidade" value="${perfil.perfilTreinador.modalidade}" placeholder="Ex: Musculação, Crossfit..." required>
        </div>

        <div class="input-group">
            <label>Nível de Experiência</label>
            <select name="nivelExperiencia">
                <option value="Iniciante" ${perfil.perfilTreinador.nivelExperiencia == 'Iniciante' ? 'selected' : ''}>Iniciante</option>
                <option value="Intermediário" ${perfil.perfilTreinador.nivelExperiencia == 'Intermediário' ? 'selected' : ''}>Intermediário</option>
                <option value="Avançado" ${perfil.perfilTreinador.nivelExperiencia == 'Avançado' ? 'selected' : ''}>Avançado / Especialista</option>
            </select>
        </div>

        <div class="input-group">
            <label>Objetivo Profissional</label>
            <input type="text" name="objetivo" value="${perfil.perfilTreinador.objetivo}" placeholder="Ex: Hipertrofia, Perda de Peso...">
        </div>

        <div class="input-group">
            <label>Ambiente de Treino</label>
            <input type="text" name="ambiente" value="${perfil.perfilTreinador.ambiente}" placeholder="Ex: Ginásio, Online, Ar Livre...">
        </div>

        <div class="input-group">
            <label>Sexo Atendido</label>
            <select name="sexo">
                <option value="Masculino" ${perfil.perfilTreinador.sexo == 'Masculino' ? 'selected' : ''}>Masculino</option>
                <option value="Feminino" ${perfil.perfilTreinador.sexo == 'Feminino' ? 'selected' : ''}>Feminino</option>
                <option value="Ambos" ${perfil.perfilTreinador.sexo == 'Ambos' ? 'selected' : ''}>Ambos</option>
            </select>
        </div>

        <div class="input-group">
            <label>Observações / Restrições (Opcional)</label>
            <textarea name="restricaoFisica" rows="3" placeholder="Notas sobre restrições físicas ou avisos...">${perfil.perfilTreinador.restricaoFisica}</textarea>
        </div>

        <button type="submit" class="btn-save">GUARDAR TODAS AS ALTERAÇÕES</button>
        <a href="${pageContext.request.contextPath}/perfil-treinador" class="btn-cancel">Cancelar e Voltar</a>

    </form>
</div>

</body>
</html>