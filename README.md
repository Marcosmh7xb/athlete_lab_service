- WEB-INF é separar páginas públicas e internas.

---

-  Criação do caminho/link para conexão começar dentro de webapp EX: ./css/arquivo.css ou WEB - INF/arquivo.jsp.

---
    ports: ## Mapeamento da porta do PC (3307) para a porta do MySQL no container (3306), permitindo acesso externo ao banco.
      - "3307:3306"
----
    ports: ## Mapeamento da porta do PC para a porta do container; sem isso o acesso via navegador não funciona.
      - "8080:8080"