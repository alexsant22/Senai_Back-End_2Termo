// Link API
const API_URL = "http://localhost:8080/produtos";

// Saída (somente na tela principal)
let saida = document.getElementById("listaProdutos");

// Função de adicionar (POST)
async function salvarProduto(event) {
  event.preventDefault();

  const produto = {
    nome: document.getElementById("nome").value,
    valor: parseFloat(document.getElementById("valor").value),
    saldo: parseInt(document.getElementById("saldo").value),
    saldoMinimo: parseInt(document.getElementById("saldoMinimo").value),
  };

  try {
    const response = await fetch(API_URL + "/adicionar", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(produto),
    });

    if (!response.ok) {
      throw new Error(`Erro HTTP: ${response.status}`);
    }

    alert(`Produto "${produto.nome}" adicionado com sucesso.`);
    buscarProdutos();

    // Limpar os campos do formulário após adicionar o produto
    document.getElementById("nome").value = '';
    document.getElementById("valor").value = '';
    document.getElementById("saldo").value = '';
    document.getElementById("saldoMinimo").value = '';

  } catch (error) {
    console.error("Erro ao cadastrar produto:", error);
    alert("Erro ao cadastrar produto. Por favor, tente novamente.");
  }
}

// Função de buscar (GET)
async function buscarProdutos() {
  try {
    saida.innerHTML = "<li>Carregando...</li>";

    const response = await fetch(API_URL + "/buscar", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    });

    if (!response.ok) {
      throw new Error(`Erro HTTP: ${response.status}`);
    }

    const produtos = await response.json();
    saida.innerHTML = "";

    if (produtos.length === 0) {
      saida.innerHTML = "<li>Nenhum produto adicionado</li>";
      return;
    }

    produtos.forEach((produto) => {
      const li = document.createElement("li");
      li.innerHTML = `
        ID: ${produto.idProduto} |
        Produto: ${produto.nome} |
        Valor: R$ ${produto.valor.toFixed(2)} |
        Saldo: ${produto.saldo} |
        Saldo Mínimo: ${produto.saldoMinimo}
      `;

      let btnDeletar = document.createElement("button");
      btnDeletar.textContent = "Deletar";
      btnDeletar.style.marginLeft = "10px";
      btnDeletar.onclick = () => {
        deletarProduto(produto.idProduto);
      };
      li.appendChild(btnDeletar);
      
      let btnAtt = document.createElement("button");
      btnAtt.textContent = "Atualizar";
      btnAtt.style.marginLeft = "10px";
      btnAtt.onclick = () => {
        window.location.href = `attProduto.html?id=${produto.idProduto}`;
      };
      li.appendChild(btnAtt);

      saida.appendChild(li);
    });
  } catch (error) {
    console.error("Erro ao buscar produtos:", error);
    saida.innerHTML = "<li>Erro ao carregar produtos</li>";
  }
}

// Função para deletar (DELETE)
async function deletarProduto(id) {
  try {
    const response = await fetch(`${API_URL}/deletar/${id}`, {
      method: "DELETE",
    });

    if (!response.ok) {
      throw new Error(`Erro HTTP: ${response.status}`);
    }

    buscarProdutos();
  } catch (error) {
    console.error("Erro ao deletar produto:", error);
  }
}

// DOM carregado
document.addEventListener("DOMContentLoaded", () => {
  const formCadastro = document.getElementById("form-submit");
  if (formCadastro) {
    formCadastro.addEventListener("submit", salvarProduto);
  }

  const listarBtn = document.getElementById("listarProdutosBtn");
  if (listarBtn) {
    listarBtn.addEventListener("click", buscarProdutos);
  }
});
