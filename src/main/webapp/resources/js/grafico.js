document.addEventListener("DOMContentLoaded", function() {
    let meuGrafico;

    // Toggle do formulário
    document.getElementById('btnNovaConta').addEventListener('click', function() {
        const formContainer = document.getElementById('formContainer');
        formContainer.style.display = formContainer.style.display === 'none' ? 'block' : 'none';
    });

    // Inicializa o gráfico
    atualizarGrafico();

    function atualizarGrafico() {
        const tabela = document.getElementById("tabela-contas");
        const linhas = tabela.getElementsByTagName("tbody")[0].getElementsByTagName("tr");

        const dados = {
            labels: [],
            valores: []
        };

        for (let linha of linhas) {
            const nome = linha.cells[0].textContent;
            const saldo = parseFloat(linha.cells[3].textContent
                .replace('R$', '')
                .replace('.', '')
                .replace(',', '.')
                .trim());

            dados.labels.push(nome);
            dados.valores.push(saldo);
        }

        const ctx = document.getElementById('graficoPizza').getContext('2d');

        if (meuGrafico) {
            meuGrafico.destroy();
        }

        meuGrafico = new Chart(ctx, {
            type: 'pie',
            data: {
                labels: dados.labels,
                datasets: [{
                    data: dados.valores,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.8)',
                        'rgba(54, 162, 235, 0.8)',
                        'rgba(255, 206, 86, 0.8)',
                        'rgba(75, 192, 192, 0.8)',
                        'rgba(153, 102, 255, 0.8)'
                    ]
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false
            }
        });
    }
});