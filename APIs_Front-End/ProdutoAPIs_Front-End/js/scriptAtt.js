// Link API
const API_URL = "http://localhost:8080/produtos";

// Função para buscar por ID (GET)
async function buscarProdutoID() {
    try {
        // Pega o id da URL
        const params = new URLSearchParams(window.location.search);
        const id = params.get('id');  // Obtém o 'id' da URL, ex: attProduto.html?id=1

        if (!id) {
            alert("ID do produto não encontrado na URL");
            return;
        }

        const response = await fetch(API_URL + `/buscar/${id}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
              },
        });

        if (!response.ok) {
          throw new Error(`Erro HTTP: ${response.status}`);
        }
    
        const produto = await response.json();

        // Preenchendo campos
        document.getElementById('produtoId').value = produto.idProduto;
        document.getElementById('nome').value = produto.nome
        document.getElementById('valor').value = produto.valor
        document.getElementById('saldo').value = produto.saldo
        document.getElementById('saldoMinimo').value = produto.saldoMinimo

    } catch (error) {
        console.error("Erro ao buscar o produto:", error);
        alert("Erro ao buscar o produto:", error);
    }
}

// Função para atualizar produto (PUT)
async function atualizarProduto(event) {
    event.preventDefault();
  
    const id = document.getElementById("produtoId").value;
    const produtoAtualizado = {
      nome: document.getElementById("nome").value,
      valor: parseFloat(document.getElementById("valor").value),
      saldo: parseInt(document.getElementById("saldo").value),
      saldoMinimo: parseInt(document.getElementById("saldoMinimo").value),
    };
  
    try {
      const response = await fetch(`${API_URL}/atualizar/${id}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(produtoAtualizado),
      });
  
      if (!response.ok) {
        throw new Error(`Erro HTTP: ${response.status}`);
      }
  
      alert(`Produto ID ${id} atualizado com sucesso.`);

      window.history.back(); // Redireciona o usuário para a pagina anterior
    } catch (error) {
      console.error("Erro ao atualizar produto:", error);
      alert("Erro ao atualizar produto. Verifique o ID e tente novamente.");
    }
  }

  // DOM carregado
document.addEventListener("DOMContentLoaded", () => {
  buscarProdutoID(); // Carregando dados atuais do produto

  const formAtualizar = document.getElementById("form-atualizar");
  if (formAtualizar) {
    formAtualizar.addEventListener("submit", atualizarProduto);
  }
});