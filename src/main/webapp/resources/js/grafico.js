document.addEventListener("DOMContentLoaded", function () {
    // Variável para controlar o gráfico
    let meuGrafico;

    // Inicializa o gráfico
    atualizarGrafico();

    // Configura o botão de nova conta
    document.getElementById('btnNovaConta').addEventListener('click', function() {
        const formContainer = document.getElementById('formContainer');
        formContainer.style.display = formContainer.style.display === 'none' ? 'block' : 'none';
    });

    // Configura o botão de confirmação
    document.getElementById('btnConfirmar').addEventListener('click', function() {
        const nome = document.getElementById('novoNome').value;
        const banco = document.getElementById('novoBanco').value || '---';
        const tipo = document.getElementById('novoTipo').value;
        const saldo = document.getElementById('novoSaldo').value;

        // Validação simples
        if (!nome || !saldo) {
            alert('Preencha pelo menos Nome e Saldo!');
            return;
        }

        // Adiciona à tabela
        const novaLinha = document.createElement('tr');
        novaLinha.innerHTML = `
            <td>${nome}</td>
            <td>${banco}</td>
            <td>${tipo}</td>
            <td>R$ ${formatarSaldo(saldo)}</td>
        `;
        document.querySelector('#tabela-contas tbody').appendChild(novaLinha);

        // Atualiza o gráfico
        atualizarGrafico();

        // Limpa o formulário
        document.getElementById('novoNome').value = '';
        document.getElementById('novoBanco').value = '';
        document.getElementById('novoSaldo').value = '';
        document.getElementById('formContainer').style.display = 'none';
    });

    // Função para formatar saldo (1.000,00)
    function formatarSaldo(valor) {
        return valor.replace('.', ','); // Simples - para casos como "1000.00"
    }

    // Função principal para atualizar o gráfico
    function atualizarGrafico() {
        const tabela = document.getElementById("tabela-contas").getElementsByTagName("tbody")[0];
        const linhas = tabela.getElementsByTagName("tr");

        const labels = [];
        const valores = [];

        for (let linha of linhas) {
            const nomeConta = linha.cells[0].textContent.trim();
            const saldoTexto = linha.cells[3].textContent.trim()
                .replace("R$", "")
                .replace(/\./g, "")
                .replace(",", ".");
            const saldo = parseFloat(saldoTexto);

            if (!isNaN(saldo)) {
                labels.push(nomeConta);
                valores.push(saldo);
            }
        }

        const ctx = document.getElementById('graficoPizza').getContext('2d');
        
        // Destrói o gráfico anterior se existir
        if (meuGrafico) {
            meuGrafico.destroy();
        }
        
        meuGrafico = new Chart(ctx, {
            type: 'pie',
            data: {
                labels: labels,
                datasets: [{
                    label: 'Saldo por Conta',
                    data: valores,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.6)',
                        'rgba(54, 162, 235, 0.6)',
                        'rgba(255, 206, 86, 0.6)',
                        'rgba(75, 192, 192, 0.6)',
                        'rgba(153, 102, 255, 0.6)'
                    ],
                    borderColor: '#fff',
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        position: 'bottom',
                        labels: {
                            padding: 15,
                            boxWidth: 15,
                            font: {
                                size: 12
                            }
                        }
                    }
                },
                layout: {
                    padding: {
                        top: 10,
                        bottom: 20,
                        left: 10,
                        right: 10
                    }
                }
            }
        });
    } // Fecha a função atualizarGrafico()
}); // Fecha o DOMContentLoaded