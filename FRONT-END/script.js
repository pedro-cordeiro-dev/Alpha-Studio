console.log("Script carregado");

document.addEventListener("DOMContentLoaded", () => {
  console.log("DOM totalmente carregado");

  const adminLoginOverlay = document.getElementById("adminLoginOverlay");
  const adminPanel = document.getElementById("adminPanel");
  const adminLoginForm = document.getElementById("adminLoginForm");
  const adminLogoutBtn = document.getElementById("adminLogoutBtn");
  const loginError = document.getElementById("loginError");
  const agendamentosList = document.getElementById("agendamentosList");
  const filterDate = document.getElementById("filterDate");
  const refreshBtn = document.getElementById("refreshBtn");

  const btnAgendamento = document.getElementById("btnAgendamento");
  const btnHeroAgendamento = document.getElementById("btnHeroAgendamento");
  const btnFooterAgendamento = document.getElementById("btnFooterAgendamento");
  const agendamentoForm = document.getElementById("agendamentoForm");
  const blurOverlay = document.getElementById("blurOverlay");
  const closeAgendamento = document.getElementById("closeAgendamento");

  const tipoVeiculo = document.getElementById("tipoVeiculo");
  const servico = document.getElementById("servico");
  const valorServico = document.getElementById("valorServico");

  const formAgendamento = document.getElementById("formAgendamento");
  const confirmacaoContainer = document.getElementById("confirmacaoContainer");
  const fecharConfirmacao = document.getElementById("fecharConfirmacao");

  const precosList = document.getElementById("precosList");
  const tipoVeiculoPreco = document.getElementById("tipoVeiculoPreco");
  const servicoPreco = document.getElementById("servicoPreco");
  const valorPreco = document.getElementById("valorPreco");
  const btnSalvarPreco = document.getElementById("btnSalvarPreco");
  const API_URL = "http://localhost:8080/api/admin";

  const loginForm = document.getElementById("adminLoginForm");
  const loginOverlay = document.getElementById("adminLoginOverlay");
  const logoutBtn = document.getElementById("adminLogoutBtn");

  loginForm.addEventListener("submit", async (e) => {
    e.preventDefault();

    const email = document.getElementById("adminEmail").value.trim();
    const senha = document.getElementById("adminSenha").value.trim();

    try {
      const response = await fetch(`${API_URL}/login`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ email, senha }),
      });

      if (response.ok) {
        const result = await response.text();

        if (result.includes("sucesso")) {
          loginOverlay.style.display = "none";
          adminPanel.style.display = "block";
          loginError.textContent = "";
        } else {
          loginError.textContent = result;
        }
      } else if (response.status === 401) {
        loginError.textContent = "E-mail ou senha incorretos.";
      } else {
        loginError.textContent = "Erro ao autenticar. Tente novamente.";
      }
    } catch (error) {
      console.error("Erro na requisição:", error);
      loginError.textContent = "Erro de conexão com o servidor.";
    }
  });

// Logout
  logoutBtn.addEventListener("click", () => {
    adminPanel.style.display = "none";
    loginOverlay.style.display = "flex";
  });

  function openAgendamentoForm() {
    agendamentoForm.style.display = "flex";
    agendamentoForm.style.flexDirection = "column";
    blurOverlay.style.display = "block";
    document.body.style.overflow = "hidden";
  }

  function closeAgendamentoForm() {
    agendamentoForm.style.display = "none";
    blurOverlay.style.display = "none";
    document.body.style.overflow = "auto";
  }

  function formatarData(data) {
    if (!data) return "";
    const [ano, mes, dia] = data.split("-");
    return `${dia}/${mes}/${ano}`;
  }

  function getServicoNome(codigo) {
    const servicos = {
      basica: "Básica",
      intermediaria: "Intermediária",
      premium: "Premium",
    };
    return servicos[codigo] || codigo;
  }

  function carregarAgendamentos() {
    if (!agendamentosList) return;

    fetch("http://localhost:8080/api/agendamentos")
        .then((response) => response.json())
        .then((agendamentos) => {
          const dataFiltro = filterDate ? filterDate.value : null;
          let agendamentosFiltrados = agendamentos;

          if (dataFiltro) {
            agendamentosFiltrados = agendamentos.filter(
                (ag) => ag.data === dataFiltro
            );
          }

          agendamentosFiltrados.sort((a, b) => {
            const dataA = new Date(`${a.data}T${a.hora}`);
            const dataB = new Date(`${b.data}T${b.hora}`);
            return dataA - dataB;
          });

          agendamentosList.innerHTML = "";

          if (agendamentosFiltrados.length === 0) {
            agendamentosList.innerHTML =
                '<div class="no-agendamentos">Nenhum agendamento encontrado</div>';
            return;
          }

          agendamentosFiltrados.forEach((ag) => {
            const agendamentoItem = document.createElement("div");
            agendamentoItem.className = "agendamento-item";

            agendamentoItem.innerHTML = `
            <div>${formatarData(ag.data)}</div>
            <div>${ag.hora}</div>
            <div>${ag.nome || "Não informado"}</div>
            <div>${ag.marca} ${ag.modelo}</div>
            <div>${getServicoNome(ag.servico)}</div>
            <div>R$ ${ag.valor.toFixed(2)}</div>
          `;

            agendamentosList.appendChild(agendamentoItem);
          });
        })
        .catch((error) => {
          console.error("Erro ao carregar agendamentos:", error);
        });
  }

  async function calcularValor() {
    if (!tipoVeiculo || !servico || !valorServico) return 0;

    const tipo = tipoVeiculo.value;
    const servicoSelecionado = servico.value;

    if (!tipo || !servicoSelecionado) {
      valorServico.textContent = "Valor: R$ 0,00";
      return 0;
    }

    try {
      const response = await fetch(
          `http://localhost:8080/api/precos?tipoVeiculo=${tipo}&servico=${servicoSelecionado}`
      );
      if (!response.ok) throw new Error("Erro ao buscar preço");
      const valor = await response.json();
      valorServico.textContent = `Valor: R$ ${Number(valor).toFixed(2)}`;
      return Number(valor);
    } catch (error) {
      console.error("Erro ao buscar preço:", error);
      valorServico.textContent = "Valor: R$ 0,00";
      return 0;
    }
  }

  function carregarPrecos() {
    if (!precosList) return;

    fetch("http://localhost:8080/api/precos/todos")
        .then((response) => response.json())
        .then((precos) => {
          precosList.innerHTML = "";

          if (precos.length === 0) {
            precosList.innerHTML =
                '<div class="no-agendamentos">Nenhum preço cadastrado</div>';
            return;
          }

          precos.forEach((p) => {
            const precoItem = document.createElement("div");
            precoItem.className = "agendamento-item";

            precoItem.innerHTML = `
            <div>${p.tipoVeiculo}</div>
            <div>${getServicoNome(p.servico)}</div>
            <div>R$ ${Number(p.valor).toFixed(2)}</div>
          `;

            precosList.appendChild(precoItem);
          });
        })
        .catch((error) => {
          console.error("Erro ao carregar preços:", error);
        });
  }

  if (btnSalvarPreco) {
    btnSalvarPreco.addEventListener("click", () => {
      const tipo = tipoVeiculoPreco.value;
      const serv = servicoPreco.value;
      const valor = parseFloat(valorPreco.value);

      if (!tipo || !serv || isNaN(valor)) {
        alert("Preencha todos os campos corretamente.");
        return;
      }

      const preco = {
        tipoVeiculo: tipo,
        servico: serv,
        valor: valor,
      };

      fetch("http://localhost:8080/api/precos", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(preco),
      })
          .then((response) => {
            if (!response.ok) throw new Error("Erro ao salvar preço");
            return response.json();
          })
          .then(() => {
            carregarPrecos();
            tipoVeiculoPreco.value = "";
            servicoPreco.value = "";
            valorPreco.value = "";
          })
          .catch((error) => {
            console.error("Erro ao salvar preço:", error);
          });
    });
  }

  const footerLinks = document.querySelector(".footer-links");
  if (footerLinks && !document.getElementById("btnAdminLogin")) {
    const btnAdminLogin = document.createElement("a");
    btnAdminLogin.href = "#";
    btnAdminLogin.id = "btnAdminLogin";
    btnAdminLogin.textContent = "Área Admin";
    footerLinks.appendChild(btnAdminLogin);

    btnAdminLogin.addEventListener("click", (e) => {
      e.preventDefault();
      adminLoginOverlay.style.display = "flex";
    });
  }

  if (adminLoginOverlay) {
    adminLoginOverlay.addEventListener("click", (e) => {
      if (e.target === adminLoginOverlay) {
        adminLoginOverlay.style.display = "none";
        loginError.textContent = "";
      }
    });
  }

  if (adminLoginForm) {
    adminLoginForm.addEventListener("submit", (e) => {
      e.preventDefault();

      const email = document.getElementById("adminEmail")?.value;
      const senha = document.getElementById("adminSenha")?.value;

      if (email === ADMIN_CREDENTIALS.email && senha === ADMIN_CREDENTIALS.password) {
        adminLoginOverlay.style.display = "none";
        adminPanel.style.display = "block";
        loginError.textContent = "";
        carregarAgendamentos();
        carregarPrecos();
      } else {
        loginError.textContent = "Credenciais inválidas!";
      }
    });
  }

  if (adminLogoutBtn) {
    adminLogoutBtn.addEventListener("click", () => {
      adminPanel.style.display = "none";
      adminLoginForm.reset();
    });
  }

  if (refreshBtn) refreshBtn.addEventListener("click", carregarAgendamentos);
  if (filterDate) filterDate.addEventListener("change", carregarAgendamentos);

  [btnAgendamento, btnHeroAgendamento, btnFooterAgendamento].forEach((btn) => {
    if (btn) {
      btn.addEventListener("click", (e) => {
        e.preventDefault();
        openAgendamentoForm();
      });
    }
  });

  if (closeAgendamento) {
    closeAgendamento.addEventListener("click", closeAgendamentoForm);
  }

  if (blurOverlay) {
    blurOverlay.addEventListener("click", (e) => {
      if (e.target === blurOverlay) closeAgendamentoForm();
    });
  }

  document.addEventListener("keydown", (e) => {
    if (e.key === "Escape" && agendamentoForm?.style.display === "flex") {
      closeAgendamentoForm();
    }
  });

  if (tipoVeiculo) tipoVeiculo.addEventListener("change", async () => await calcularValor());
  if (servico) servico.addEventListener("change", async () => await calcularValor());

  if (formAgendamento) {
    formAgendamento.addEventListener("submit", async (e) => {
      e.preventDefault();

      const valor = await calcularValor();
      if (valor <= 0) {
        alert("Selecione o tipo de veículo e serviço para continuar");
        return;
      }

      const agendamento = {
        nome: document.getElementById("nomeCliente")?.value,
        telefone: document.getElementById("telefoneCliente")?.value,
        marca: document.getElementById("marca")?.value,
        modelo: document.getElementById("modelo")?.value,
        cor: document.getElementById("cor")?.value,
        tipoVeiculo: tipoVeiculo.value,
        servico: servico.value,
        data: document.getElementById("dataAgendamento")?.value,
        hora: document.getElementById("horaAgendamento")?.value,
        valor: valor,
      };

      fetch("http://localhost:8080/api/agendamentos", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(agendamento),
      })
          .then((response) => response.json())
          .then((data) => {
            if (data.message) {
              confirmacaoContainer.style.display = "block";
              formAgendamento.style.display = "none";

              const linkWhatsapp = document.getElementById("btnEnviarWhatsapp");
              if (linkWhatsapp) {
                const nome = agendamento.nome || "";
                const telefone = agendamento.telefone || "";
                const marca = agendamento.marca || "";
                const modelo = agendamento.modelo || "";
                const cor = agendamento.cor || "";
                const tipo = tipoVeiculo.value;
                const tipoNome = tipo.charAt(0).toUpperCase() + tipo.slice(1);
                const servicoNome = getServicoNome(servico.value);
                const dataAg = agendamento.data || "";
                const horaAg = agendamento.hora || "";
                const valorFormatado = valor.toFixed(2).replace(".", ",");

                const msg = `Olá, me chamo ${nome} e acabei de agendar um serviço no Alpha Studio:\n\n🚗 *Veículo*: ${marca} ${modelo} (${tipoNome} - ${cor})\n🛠️ *Serviço*: ${servicoNome}\n📅 *Data*: ${formatarData(dataAg)} às ${horaAg}\n💰 *Valor*: R$ ${valorFormatado}\n📞 *Contato*: ${telefone}`;
                const url = `https://wa.me/adicioneSeuNumeroAqui?text=${encodeURIComponent(
                    msg
                )}`;

                linkWhatsapp.href = url;
                linkWhatsapp.style.display = "inline-block";
              }
            } else {
              alert(data.error || "Erro ao salvar agendamento");
            }
          })
          .catch((error) => {
            console.error("Erro ao enviar agendamento:", error);
            alert("Erro ao enviar agendamento");
          });
    });
  }

  if (fecharConfirmacao) {
    fecharConfirmacao.addEventListener("click", () => {
      confirmacaoContainer.style.display = "none";
      formAgendamento.reset();
      formAgendamento.style.display = "flex";
      closeAgendamentoForm();
    });
  }

  calcularValor();
});

const API_URL = "http://localhost:8080/api/precos";

function carregarPrecos() {
  fetch(`${API_URL}/todos`)
      .then(res => res.json())
      .then(data => {
        const tabela = document.getElementById('tabelaPrecos');
        tabela.innerHTML = '';

        data.forEach(preco => {
          const row = document.createElement('tr');

          row.innerHTML = `
  <td data-label="ID">${preco.id}</td>
  <td data-label="Tipo Veículo">${preco.tipoVeiculo}</td>
  <td data-label="Serviço">${preco.servico}</td>
  <td data-label="Valor (R$)">
    <input type="number" id="input-${preco.id}" value="${preco.valor}" step="0.01">
  </td>
  <td data-label="Ações">
    <button onclick="atualizarPreco(${preco.id})">Salvar</button>
  </td>
`;


          tabela.appendChild(row);
        });
      });
}

function atualizarPreco(id) {
  const input = document.getElementById(`input-${id}`);
  const novoValor = parseFloat(input.value);

  if (isNaN(novoValor) || novoValor < 0) {
    alert("Digite um valor válido.");
    return;
  }

  fetch(`${API_URL}/${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      id: id,
      valor: novoValor
    })
  })
      .then(res => {
        if (!res.ok) {
          throw new Error("Erro ao atualizar preço.");
        }
        return res.json();
      })
      .then(() => {
        alert("Preço atualizado com sucesso!");
        carregarPrecos();
      })
      .catch(err => {
        console.error(err);
        alert("Erro ao atualizar preço.");
      });
}

document.addEventListener("DOMContentLoaded", carregarPrecos);

document.addEventListener("DOMContentLoaded", () => {
  const API_URL = "http://localhost:8080/api/precos/todos";

  const tbody = document.getElementById("precos-tabela");


  const veiculos = ["hatch", "sedan", "suv", "pickup"];
  const servicos = ["basica", "intermediaria", "premium"];


  const tabela = {
    basica: { hatch: "--", sedan: "--", suv: "--", pickup: "--" },
    intermediaria: { hatch: "--", sedan: "--", suv: "--", pickup: "--" },
    premium: { hatch: "--", sedan: "--", suv: "--", pickup: "--" },
  };

  fetch(API_URL)
      .then(res => res.json())
      .then(data => {
        data.forEach(item => {
          const tipoVeiculo = item.tipoVeiculo.toLowerCase();
          const servico = item.servico.toLowerCase();
          const valor = Number(item.valor).toFixed(2).replace(".", ",");

          if (tabela[servico] && tabela[servico][tipoVeiculo] !== undefined) {
            tabela[servico][tipoVeiculo] = `R$ ${valor}`;
          }
        });

        tbody.innerHTML = servicos.map(serv => {
          return `
          <tr>
            <td>${serv.charAt(0).toUpperCase() + serv.slice(1)}</td>
            ${veiculos.map(vei => `<td>${tabela[serv][vei]}</td>`).join('')}
          </tr>
        `;
        }).join('');
      })
      .catch(err => {
        console.error("Erro ao carregar preços:", err);
      });
});
