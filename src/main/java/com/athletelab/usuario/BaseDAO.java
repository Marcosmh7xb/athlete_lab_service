package com.athletelab.usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.athletelab.configBD.ConnectionDataBase;

public abstract class BaseDAO {
    // Método herdado por todos os DAOs para obter a conexão de forma limpa
    protected Connection obterConexao() throws Exception {
        return ConnectionDataBase.getConnection();
    }

    // Método centralizado para fechar os recursos do banco com segurança
    protected void fecharRecursos(ResultSet rs, PreparedStatement ps, Connection conn) {
        try { if (rs != null) rs.close(); } catch (Exception e) {}
        try { if (ps != null) ps.close(); } catch (Exception e) {}
        try { if (conn != null) conn.close(); } catch (Exception e) {}
    }

}
