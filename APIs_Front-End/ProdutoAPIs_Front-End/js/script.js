//Link API
const API_URL = "http://localhost:8080/produtos";

// Saida
let saida = document.getElementById("listaProdutos");

// Função de adicionar (POST)
async function salvarProduto(event) {
  event.preventDefault();

  // Entradas do Produto
  const produto = {
    nome: document.getElementById("nome").value,
    valor: parseFloat(document.getElementById("valor").value),
    saldo: parseInt(document.getElementById("saldo").value),
    saldoMinimo: parseInt(document.getElementById("saldoMinimo").value),
  };

  console.log(produto);

  // Fazendo jsnon do produto
  try {
    const response = await fetch(API_URL + "/adicionar", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        // Inclua outros headers necessários como Authorization
      },
      body: JSON.stringify(produto),
    });
    // Validando se o produto pode ser adicionado
    if (!response.ok) {
      throw new Error(`Erro HTTP: ${response.status}`);
    }
    alert(`Produto "${produto.nome}" adicionado com sucesso.`);
    buscarProdutos(); // Atualiza a lista
  } catch (error) {
    console.error("Erro ao cadastrar produto:", error);
    alert("Erro ao cadastrar produto. Por favor, tente novamente.");
  }
}

// Função de Buscar todos (GET)
async function buscarProdutos() {
  try {
    // Mostrar carregando
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
    console.log(produtos);

    // Limpar lista
    saida.innerHTML = "";

    if (produtos.length === 0) {
      saida.innerHTML = "<li>Nenhum produto adicionado</li>";
      return;
    }

    // Adicionando a lista os produtos
    produtos.forEach((produto) => {
      const li = document.createElement("li");

      li.innerHTML = `
                <strong>${produto.nome}</strong> | 
                Valor: R$ ${produto.valor.toFixed(2)} | 
                Saldo: ${produto.saldo} | 
                Saldo Mínimo: ${produto.saldoMinimo}
            `;

      saida.appendChild(li);
    });
  } catch (error) {
    console.error("Erro ao buscar produtos:", error);
    saida.innerHTML = "<li>Erro ao carregar produtos</li>";
  }
}

// DOM
document.addEventListener("DOMContentLoaded", () => {
  // Botão adicionar
  let fomulario = document.getElementById("form-submit");
  fomulario.addEventListener("submit", salvarProduto);

  // Botão listar todos
  let listaProdutosBtn = document.getElementById("listarProdutosBtn");
  listaProdutosBtn.addEventListener("click", buscarProdutos);
});
