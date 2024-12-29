//package MyTaskManager;

import java.util.Scanner;

//Luis Martins / 20240616 / LEI
//David Power / 20240614 / LEI
public class trabalhoFinal {

    // Lê caractere.
    static char lerChar() {
        @SuppressWarnings("resource")
        Scanner teclado = new Scanner(System.in);

        char caractere = teclado.next().charAt(0);

        return caractere;
    }

    static int lerInt(){
        @SuppressWarnings("resource")
        Scanner teclado = new Scanner(System.in);
        
        while (!teclado.hasNextInt())
            teclado.nextLine();

        int inteiro = teclado.nextInt();

        return inteiro;
    }

    static String lerString(){
        @SuppressWarnings("resource")
        Scanner teclado = new Scanner(System.in);

        String str = teclado.nextLine();

        return str;
    }

    // variáveis de teste
    static int inicializarTarefas(String[] tarefa, boolean[] temPrazo, boolean[] foiFeita, int[][] data) {
        tarefa[0] = "Ir ao Dentista"; temPrazo[0] = true; foiFeita[0] = false; data[0][0] = 17; data[0][1] = 12; data[0][2] = 2024;
        tarefa[1] = "Teste de Programação"; temPrazo[1] = true; foiFeita[1] = true; data[1][0] = 14; data[1][1] = 12; data[1][2] = 2024;
        tarefa[2] = "Corrigir Testes"; temPrazo[2] = false; foiFeita[2] = false;
        tarefa[3] = "Frequência de Programação"; temPrazo[3] = true; foiFeita[3] = false; data[3][0] = 13; data[3][1] = 1; data[3][2] = 2024;
        tarefa[4] = "Presentes!!!"; temPrazo[4] = true; foiFeita[4] = false; data[4][0] = 25; data[4][1] = 12; data[4][2] = 2024;

        return 5;
    }

    // Submenu Editar
    static int menuEditar(String[] tarefa, boolean[] foiFeita, boolean[] ultimaFeita, int[][] data, int nTarefas, boolean[]temPrazo) {
        char opcao;
        do {
            System.out.println("SubMenu Editar:");
            System.out.println("(A) Adicionar tarefa");
            System.out.println("(t) Adicionar tarefa na posição n");
            System.out.println("(p) Apagar tarefa na Posição n");
            System.out.println("(f) Apagar feitas");
            System.out.println("(J) Juntar tarefas");
            System.out.println("(E) Editar tarefa");
            System.out.println("(V) Voltar");
            System.out.print("Introduza uma opção: ");
            opcao = lerChar();

            switch (opcao) {
                case 'A':
                    nTarefas = adicionarTarefa(tarefa, nTarefas);
                    break;
                case 't':
                    nTarefas = addTarefaPosN(tarefa, foiFeita, data, nTarefas);
                    break;
                case 'p':
                    nTarefas = apagarPosN(tarefa, foiFeita, data, nTarefas);
                    break;
                case 'f':
                    nTarefas = apagarTarefasFeitas(tarefa, foiFeita, data, nTarefas);
                    break;
                case 'J':
                    nTarefas = juntarTarefas(tarefa, foiFeita, data, nTarefas);
                    break;
                case 'E':
                    editarTarefa( nTarefas,  tarefa,  data, temPrazo);
                    break;
                case 'V':
                    break;
                default:
                    System.out.println("Opção inválida!");
                    System.out.println();

            }
        } while (opcao != 'V');
        System.out.println();
        return nTarefas;
    }

    static int adicionarTarefa(String[] tarefa, int nTarefas) {
        System.out.println();
        System.out.println("Adicionar uma nova tarefa: ");
        String tarefaAdicionar = lerString();

        if (nTarefas < tarefa.length) {
            tarefa[nTarefas] = tarefaAdicionar;
            nTarefas = nTarefas + 1;
            System.out.println();
            System.out.println("A tarefa foi adicionada com sucesso!");
        }

        System.out.println();

        return nTarefas;
    }

    static int addTarefaPosN(String[] tarefa, boolean[] foiFeita, int[][] data, int nTarefas) {
        System.out.println();
        System.out.print("Adicionar uma nova tarefa: ");
        String tarefaNova = lerString();
        System.out.println();

        System.out.print("Qual é a posição onde quer adicionar a tarefa: ");
        int n = lerInt();

        if (n >= 1 && n <= nTarefas) {
            // Deslocamento (shift) das tarefas existentes para a direita.
            for (int i = tarefa.length - 1; i > n - 1; i--) {
                tarefa[i] = tarefa[i - 1];
                foiFeita[i] = foiFeita[i - 1];
                data[i][0] = data[i - 1][0];
                data[i][1] = data[i - 1][1];
                data[i][2] = data[i - 1][2];
            }

            // Introdução da nova tarefa.
            tarefa[n - 1] = tarefaNova;
            foiFeita[n - 1] = false;
            data[n - 1][0] = 0;
            data[n - 1][1] = 0;
            data[n - 1][2] = 0;

            // Incrementar contador de tarefas preenchidas.
            System.out.println();
            System.out.println("Tarefa adicionada com sucesso!");
            nTarefas++;
        } else
            System.out.println("Posição inválida!");

        System.out.println();

        return nTarefas;
    }

    static int apagarPosN(String[] tarefa, boolean[] foiFeita, int[][] data, int nTarefas) {
        System.out.println();
        System.out.print("Qual é a posição da tarefa que deseja remover: ");
        int n = lerInt();

        if (n >= 1 && n <= nTarefas) {
            // Deslocamento (shift) das tarefas existentes para a esquerda.
            for (int i = n - 1; i < nTarefas - 1; i++) {
                tarefa[i] = tarefa[i + 1];
                foiFeita[i] = foiFeita[i + 1];
                data[i][0] = data[i + 1][0];
                data[i][1] = data[i + 1][1];
                data[i][2] = data[i + 1][2];
            }
			
            // Remover a última tarefa em cada array.
            tarefa[nTarefas - 1] = null;
            foiFeita[nTarefas - 1] = false;
            data[nTarefas - 1][0] = 0;
            data[nTarefas - 1][1] = 0;
            data[nTarefas - 1][2] = 0;

            // Decrementar contador de tarefas preenchidas.
            System.out.println();
            System.out.println("A tarefa foi apagada com sucesso!");
            nTarefas--;
        } else
            System.out.println("Posição inválida!");

        System.out.println();

        return nTarefas;
    }

    static int apagarTarefasFeitas(String[] tarefa, boolean[] foiFeita, int[][] data, int nTarefas) {
		boolean encontrou = false;

        if (nTarefas > 0) {
            for (int i = 0; i < nTarefas; i++) {
				// Verificar se a tarefa está marcada como feita.
                if (foiFeita[i] == true) {
					// Deslocamento (shift) das tarefas existentes para a esquerda.
                    for (int j = i; j < nTarefas; j++) {
                        tarefa[j] = tarefa[j + 1];
                        foiFeita[j] = foiFeita[j + 1];
                        data[j][0] = data[j + 1][0];
                        data[j][1] = data[j + 1][1];
                        data[j][2] = data[j + 1][2];
                    }
					
					// Remover a última tarefa em cada array.
					tarefa[nTarefas - 1] = null;
					foiFeita[nTarefas - 1] = false;
					data[nTarefas - 1][0] = 0;
					data[nTarefas - 1][1] = 0;
					data[nTarefas - 1][2] = 0;

                    System.out.println();
                    System.out.println("As tarefas foram apagadas com sucesso!");
                    encontrou = true;
					// Decrementar contador de tarefas preenchidas.
					nTarefas--;

                    i--;
				}
            }
        }
        if(!encontrou){
            System.out.println();
            System.out.println("Não existe mais tarefas feitas para eliminar!");
        }

        System.out.println();

        return nTarefas;
    }

    static int juntarTarefas(String[] tarefa, boolean[] foiFeita, int[][] data, int nTarefas) {
        System.out.println();
        System.out.print("Qual é o número da primeira tarefa que quer juntar: ");
        int tarefaOriginal = lerInt();

        System.out.print("Qual é o número da segunda tarefa que quer juntar: ");
        int tarefaAgregada = lerInt();

        if (tarefaAgregada >= 1 && tarefaAgregada <= nTarefas) {
			// Junta tarefas.
			tarefa[tarefaOriginal - 1] = tarefa[tarefaOriginal - 1] + ", "  + tarefa[tarefaAgregada - 1];
			foiFeita[tarefaOriginal - 1] = foiFeita[tarefaOriginal - 1] || foiFeita[tarefaAgregada - 1];

			// Ano da tarefa Agregada é posterior ao da tarefa Original.
			if(data[tarefaAgregada][2] > data[tarefaOriginal][2]) { // 2024 > 2025
                data[tarefaOriginal][0] = data[tarefaAgregada][0]; // Dia
				data[tarefaOriginal][1] = data[tarefaAgregada][1]; // Mês
				data[tarefaOriginal][2] = data[tarefaAgregada][2]; // Ano
            }
			// Ano da tarefa Original é posterior ao da tarefa Agregada.
            else if (data[tarefaAgregada][2] < data[tarefaOriginal][2]) { // 2025 < 2024
				data[tarefaAgregada][0] = data[tarefaOriginal][0]; // Dia
				data[tarefaAgregada][1] = data[tarefaOriginal][1]; // Mês
				data[tarefaAgregada][2] = data[tarefaOriginal][2]; // Ano
			}
			// Caso contrário, os anos são iguais, logo, temos que prosseguir com mês.
			else { // 2024 == 2024
				if (data[tarefaAgregada][1] > data[tarefaOriginal][1]){
					data[tarefaOriginal][0] = data[tarefaAgregada][0]; // Dia
					data[tarefaOriginal][1] = data[tarefaAgregada][1]; // Mês
					// Já sabemos que os anos são iguais, pelo que não é necessário fazer nova atribuição.
                }
                else if (data[tarefaAgregada][1] < data[tarefaOriginal][1]) {
					data[tarefaAgregada][0] = data[tarefaOriginal][0]; // Dia
					data[tarefaAgregada][1] = data[tarefaOriginal][1]; // Mês
					// Já sabemos que os anos são iguais, pelo que não é necessário fazer nova atribuição.
				}
				// Caso contrário, os meses são iguais, logo, temos que prosseguir com dia.
				else {
                    if (data[tarefaAgregada][0] > data[tarefaOriginal][0]){
                        data[tarefaOriginal][0] = data[tarefaAgregada][0]; // Dia
						// Já sabemos que os meses são iguais, pelo que não é necessário fazer nova atribuição.
                        // Já sabemos que os anos são iguais, pelo que não é necessário fazer nova atribuição.
                    }
                    else if (data[tarefaAgregada][0] < data[tarefaOriginal][0]) {
                        data[tarefaAgregada][0] = data[tarefaOriginal][0]; // Dia
						// Já sabemos que os meses são iguais, pelo que não é necessário fazer nova atribuição.
                        // Já sabemos que os anos são iguais, pelo que não é necessário fazer nova atribuição.
                    }
				}
			}
                
            // Deslocamento (shift) das tarefas existentes para a esquerda - remove tarefa agregada.
            for (int i = tarefaAgregada - 1; i < nTarefas - 1; i++) {
                tarefa[i] = tarefa[i + 1];
                foiFeita[i] = foiFeita[i + 1];
                data[i][0] = data[i + 1][0];
                data[i][1] = data[i + 1][1];
                data[i][2] = data[i + 1][2];
            }

            // Remover a última tarefa do array.
            tarefa[nTarefas - 1] = null;
            foiFeita[nTarefas - 1] = false;
            data[nTarefas - 1][0] = 0;
            data[nTarefas - 1][1] = 0;
            data[nTarefas - 1][2] = 0;

            System.out.println();
            System.out.println("As tarefas foram juntas com sucesso!");

            // Decrementar contador de tarefas preenchidas.
            nTarefas--;
        } else
            System.out.println("Posição inválida!");

        System.out.println();

        return nTarefas;
    }

    static void editarTarefa(int nTarefas, String[] tarefa, int[][] data,boolean[] temPrazo) {
        char opcao;

        do {
            System.out.println();
            System.out.println("(E) Editar Texto");
            System.out.println("(R) Remover/adicionar data");
            System.out.println("(V) Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = lerChar();

            switch (opcao) {
                case 'E':
                    editarTexto(nTarefas, tarefa);
                    break;
                case 'R':
                    removerOuAdicionarData(tarefa, data, nTarefas);
                    break;
                case 'V':
                    break;
                default:
                    System.out.println("Opcao invalida");
                    System.out.println();
            }
        } while (opcao != 'V');
        System.out.println();
    }

    // Submenus editar tarefa
    static void editarTexto(int nTarefas, String[] tarefa) {
        System.out.println();
        System.out.println("Qual a posição da tarefa, de 1 a " + nTarefas + "?");
        int posTarefa = lerInt();

		if (posTarefa >= 1 && posTarefa <= nTarefas) {
			System.out.println("Qual o novo texto");
        	String novoTexto = lerString();
            tarefa[posTarefa - 1] = novoTexto;

            System.out.println();
            System.out.println("O novo texto foi inserido com sucesso!");
		}
        else{
            System.out.println();
            System.out.println("Posição inválida!");

        }

        System.out.println();
    }

    // Remover/adicionar data – Permite: remover a data limite de uma tarefa ou adicionar uma data, caso ela não exista.
    static void removerOuAdicionarData(String[] tarefa, int[][] data, int nTarefas) {
        System.out.println();
        System.out.println("Qual é o número da tarefa que pretendo mudar a data: ");
        int posTarefa = lerInt();

		if (posTarefa >= 1 && posTarefa <= nTarefas) {
			// Se pelo menos uma das componentes da data (Dia, Mês ou Ano) for 0 consideramos que a data não existe
			// e pedimos ao utilizador que introduza uma nova data.
			if (data[posTarefa - 1][0] == 0 || data[posTarefa - 1][1] == 0 || data[posTarefa - 1][2] == 0) {
				System.out.println("Introduza a nova data:");
				System.out.print("Dia: ");
				int dia = lerInt();
				System.out.print("Mês: ");
				int mes = lerInt();
				System.out.print("Ano: ");
				int ano = lerInt();
				
				// Gravar a nova data.
                data[posTarefa - 1][0] = dia;
                data[posTarefa - 1][1] = mes;
                data[posTarefa - 1][2] = ano;
			}
			else {
				// Todas as componentes da data são diferentes de 0, logo, considera-se que a data existe
				// e prosseguimos com a sua remoção.
				data[posTarefa - 1][0] = 0;
				data[posTarefa - 1][1] = 0;
				data[posTarefa - 1][2] = 0;
			}

            System.out.println();
            System.out.println("A data foi mudada com sucesso!");
		}
		else
			System.out.println("Posição inválida!");
    }

    // Submenu Marcar
    static void menuMarcar(String[] tarefa, boolean[] foiFeita, boolean[] ultimaFeita, int[][] data, int nTarefas) {
        char opcao;
        do {
            System.out.println("SubMenu Marcar:");
            System.out.println("(f) Marcar como feita por número");
            System.out.println("(t) Marcar como feita por texto");
            System.out.println("(D) Desmarcar última feita");
            System.out.println("(n) Marcar todas no dia d");
            System.out.println("(V) Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = lerChar();

            switch (opcao) {
                case 'f':
                    marcarFeitaPorNumero(foiFeita, ultimaFeita, nTarefas);
                    break;
                case 't':
                    marcarFeitaPorTexto(tarefa, foiFeita, ultimaFeita, nTarefas);
                    break;
                case 'D':
                    desmarcarUltimaFeita(foiFeita, ultimaFeita);
                    break;
                case 'n':
                    marcarTodasDiaD(foiFeita, ultimaFeita, data);
                    break;
                case 'V':
                    break;
                default:
                    System.out.println();
                    System.out.println("Opção Inválida!");
                    System.out.println();

            }
        } while (opcao != 'V');
        System.out.println();
    }

    static void marcarFeitaPorNumero(boolean[] foiFeita, boolean[] ultimaFeita, int nTarefas) {
        System.err.println();
        System.out.print("Introduza o número da tarefa que deseja marcar como feita: ");
        int tarefaFeita = lerInt();

        if (tarefaFeita >= 0 && tarefaFeita <= nTarefas) {
            foiFeita[tarefaFeita - 1] = true;
            System.out.println();
            System.out.println("Tarefa marcada como feita!");

            // Limpar eventuais sinalizações anteriores de tarefas marcadas como feitas.
            for (int j = 0; j < ultimaFeita.length; j++) {
                ultimaFeita[j] = false;
            }
            ultimaFeita[tarefaFeita] = true;
        }
        else{
            System.out.println();
            System.out.println("Tarefa inválida!");
        }
        System.out.println();

    }

    static void marcarFeitaPorTexto(String[] tarefa, boolean[] foiFeita, boolean[] ultimaFeita, int nTarefas) {
        System.out.println();
        System.out.print("Introduza o nome da tarefa que quer marcar como feita: ");
        String nomeTarefa = lerString();
        boolean tarefaEncontrada = false;
        System.out.println();

        for (int i = 0; i < nTarefas; i++) {
            if (tarefa[i].equalsIgnoreCase(nomeTarefa)) {
                foiFeita[i] = true;
                tarefaEncontrada = true;

                // Limpar eventuais sinalizações anteriores de tarefas marcadas como feitas.
                for (int j = 0; j < ultimaFeita.length; j++) {
                    ultimaFeita[j] = false;
                }
                ultimaFeita[i] = true;

                System.out.println("Tarefa marcada como feita!");
                break; // Terminar o varrimento do ciclo for para aumentar a eficiência.
            }
        }
        if (!tarefaEncontrada)
            System.out.println("Tarefa inválida!");
        System.out.println();
    }

	// O utilizador deve marcar como por fazer a última tarefa que tiver sido marcada como feita.
    static void desmarcarUltimaFeita(boolean[] foiFeita, boolean[] ultimaFeita) {
        System.out.println();
        for (int i = 0; i < ultimaFeita.length; i++) {
            if (ultimaFeita[i] == true) {// Equivalente a if(ultimaFeita[i]), ou seja, if(true == true), ou seja if(true).
            
                foiFeita[i] = false;
                ultimaFeita[i] = false;
                System.out.println("A(s) última(s) tarefa(s) marcada(s) foi(ram) desmarcada(s)!");
            }
        }
        System.out.println();
    }

	// Todas as tarefas a realizar no dia d são marcadas como realizadas.
    static void marcarTodasDiaD(boolean[] foiFeita, boolean[] ultimaFeita, int[][] data) {
        System.out.println();
        System.out.println("Introduza a data cujas tarefas quer marcar:");
        System.out.print("Dia: ");
        int dia = lerInt();
        System.out.print("Mês: ");
        int mes = lerInt();
        System.out.print("Ano: ");
        int ano = lerInt();

        boolean houveAlteracoes = false;

        // Backup do array inicial de últimas tarefas marcadas para evitar alterações
        // indirectas.
        boolean[] backupUltimaFeita = new boolean[ultimaFeita.length];
        for (int i = 0; i < ultimaFeita.length; i++) {
            backupUltimaFeita[i] = ultimaFeita[i]; // Salvaguardar o elemento.
            ultimaFeita[i] = false; // Limpar o array de últimas tarefas marcadas.
        }

        // Pesquisa pela data indicada.
        for (int i = 0; i < data.length; i++) {
            // Se a data indicada for encontrada então marcamos a tarefa como feita e
            // assinalamos no array ultimaFeita que essa tarefa é uma das últimas que foi marcada.
            if (data[i][0] == dia && data[i][1] == mes && data[i][2] == ano) {
                foiFeita[i] = true; // Marcar tarefa como feita.
                ultimaFeita[i] = true; // Sinalizar tarefa como uma das últimas feitas.
                houveAlteracoes = true; // Marca flag que indica que houve alterações.
                System.out.println();
                System.out.println("A(s) tarefa(s) foi(ram) marcada(s) feita(s)!");
            }
        }

        // Se a data indicada não for encontrada não existem alterações.
        // Logo, é necessário repor o backup.
        if (!houveAlteracoes) {
            System.out.println("Não foi encontrada a data indicada!");

            // Restaurar o array inicial de últimas tarefas marcadas.
            for (int i = 0; i < ultimaFeita.length; i++) {
                ultimaFeita[i] = backupUltimaFeita[i]; // Restaurar o elemento.
            }
        }
        System.out.println();
    }

    // Submenu Visualizar
    static void menuVisualizar(String[] tarefa, boolean[] temPrazo, boolean[] foiFeita, int[][] data, int nTarefas) {
        char opcao;
        do {
            System.out.println("SubMenu Visualizar: ");
            System.out.println("(t) Visualizar todas");
            System.out.println("(d) Visualizar dia d");
            System.out.println("(a) Visualizar até dia d");
            System.out.println("(p) Visualizar por fazer");
            System.out.println("(f) Visualizar feitas");
            System.out.println("(l) Visualizar por palavra p");
            System.out.println("(V) Voltar");
            System.out.print("Introduza um opção: ");
            opcao = lerChar();

            switch (opcao) {
                case 't':
                    visualizarTodas(tarefa, foiFeita, data, nTarefas);
                    break;
                case 'd':
                    visualizarDiaD(data, tarefa, foiFeita);
                    break;
                case 'a':
                    visualizarAteDiaD(data, tarefa, foiFeita, nTarefas);
                    break;
                case 'p':
                    visualizarPorFazer(foiFeita, data, tarefa, nTarefas);
                    break;
                case 'f':
                    visualizarFeitas(foiFeita, data, tarefa, nTarefas);
                    break;
                case 'l':
                    visualizarPorPalavra(tarefa, data, foiFeita, nTarefas);
                    break;
                case 'V':
                    break;
                default:
                    System.out.println();
                    System.out.println("Opção inválida!");
                    System.out.println();

            }
        } while (opcao != 'V');
        System.out.println();

        return;
    }

    static void visualizarTodas(String[] tarefa, boolean[] foiFeita, int[][] data, int nTarefas) {
        System.out.println();
		// Cabeçalho.
        System.out.printf("%4s%1s%-60s%s%-10s%2s%5s%n", "", "", "Tarefa", "", "Data", "", "Feita");

        for (int i = 0; i < nTarefas; i++) {
			// Coluna do n.º e da descrição da tarefa.
            System.out.printf("%3d:%1s%-60s%s", i + 1, "", tarefa[i], "");

            if (data[i][0] > 0 && data[i][1] > 0 && data[i][2] > 0)
                System.out.printf("%2d%s%2d%s%4d%2s", data[i][0], "/", data[i][1], "/", data[i][2], "");
            else
                System.out.printf("%10s%s", "", "");

			// Coluna de sinalização de tarefa realizada.
            System.out.printf("%3s%n", foiFeita[i] ? "X" : "");
        }
        System.out.println();
    }

    static void visualizarDiaD(int[][] data, String[] tarefa, boolean[] foiFeita) {
        System.out.println();
        System.out.println("Visualizar as tarefas na data:");
        System.out.println("Dia: ");
        int dia = lerInt();
        System.out.println("Mês: ");
        int mes = lerInt();
        System.out.println("Ano: ");
        int ano = lerInt();
        boolean encontrou = false;
        System.out.println();

        // Ciclo preliminar para verificar se existe ou não.
        // Desta forma evitaremos a impressão do cabeçalho abaixo mesmo quando não
        // existe.
        for (int i = 0; i < data.length; i++) {
            if (dia == data[i][0] && mes == data[i][1] && ano == data[i][2]) {
                encontrou = true;
                break;
            }
        }

        if (encontrou) {
            System.out.printf("%4s%1s%-60s%s%-10s%2s%5s%n", "", "", "Tarefa", "", "Data", "", "Feita");

            for (int i = 0; i < data.length; i++) {
                // Verificar se a data introduzida consta no array de datas.
                if (dia == data[i][0] && mes == data[i][1] && ano == data[i][2]) {
                    System.out.printf("%3d:%1s%-60s%s", i + 1, "", tarefa[i], "");

                    // Verificar se existe uma data válida na posição corrente.
                    if (data[i][0] > 0 && data[i][1] > 0 && data[i][2] > 0)
                        System.out.printf("%2d%s%2d%s%4d%2s", data[i][0], "/", data[i][1], "/", data[i][2], "");
                    else
                        System.out.printf("%10s%s", "", "");

                    System.out.printf("%3s%n", foiFeita[i] ? "X" : "");
                }
            }
        } 
        else
            System.out.println("Não há tarefas nesse dia!");
        
        System.out.println();
    }

    static void visualizarAteDiaD(int[][] data, String[] tarefa, boolean[] foiFeita, int nTarefas) {
        System.out.println();
        System.out.println("Visualizar as tarefas na data:");
        System.out.print("Dia: ");
        int dia = lerInt();
        System.out.print("Mês: ");
        int mes = lerInt();
        System.out.print("Ano: ");
        int ano = lerInt();
        boolean encontrou = false;

        // Ciclo preliminar para verificar se existe ou não.
        // Desta forma evitaremos a impressão do cabeçalho abaixo mesmo quando não
        // existe.
        for (int i = 0; i < data.length; i++) {
            if (dia == data[i][0] && mes == data[i][1] && ano == data[i][2]) {
                encontrou = true;
                break;
            }
        }

        if (encontrou) {
            System.out.printf("%4s%1s%-60s%s%-10s%2s%5s%n", "", "", "Tarefa", "", "Data", "", "Feita");

            for (int j = 0, i = 5; j < data.length && j <= i; j++) {
                if (dia >= data[j][0] && mes >= data[j][1] && ano == data[j][2]) {
					// Coluna do n.º e da descrição da tarefa.
            		System.out.printf("%3d:%1s%-60s%s", j + 1, "", tarefa[j], "");

                    if (data[j][0] > 0 && data[j][1] > 0 && data[j][2] > 0)
                        System.out.printf("%2d%s%2d%s%4d%2s", data[j][0], "/", data[j][1], "/", data[j][2], "");
                    else
                        System.out.printf("%10s%s", "", "");

                        System.out.printf("%3s%n", foiFeita[j] ? "X" : "");
                }
            }
        }
        else{
            System.out.println();
            System.out.println("Não existe nehuma até esse dia!");
        }
        System.out.println();
    }

    static void visualizarPorFazer(boolean[] foiFeita, int[][] data, String[] tarefa, int nTarefas) {
        System.out.println();
        boolean encontrou = false;

        // Ciclo preliminar para verificar se existe ou não.
        // Desta forma evitaremos a impressão do cabeçalho abaixo mesmo quando não
        // existe.
        for (int i = 0; i < nTarefas; i++) {
            if (foiFeita[i] == false) {
                encontrou = true;
                break;
            }
        }

        if (encontrou) {
            System.out.printf("%4s%1s%-60s%s%-10s%2s%5s%n", "", "", "Tarefa", "", "Data", "", "Feita");
            
            for (int i = 0; i < nTarefas; i++) {
                if (foiFeita[i] == false) {
                        // Coluna do n.º e da descrição da tarefa.
            		    System.out.printf("%3d:%1s%-60s%s", i + 1, "", tarefa[i], "");

                        if (data[i][0] > 0 && data[i][1] > 0 && data[i][2] > 0)
                            System.out.printf("%2d%s%2d%s%4d%2s", data[i][0], "/", data[i][1], "/", data[i][2], "");
                        else
                            System.out.printf("%10s%s", "", "");

                        System.out.printf("%3s%n", foiFeita[i] ? "X" : "");
                }
            }
        } else
            System.out.println("Não existem tarefas por fazer!");

        System.out.println();
    }

    static void visualizarFeitas(boolean[] foiFeita, int[][] data, String[] tarefa, int nTarefas) {
        System.out.println();
        boolean encontrou = false;

        // Ciclo preliminar para verificar se existe ou não.
        // Desta forma evitaremos a impressão do cabeçalho abaixo mesmo quando não
        // existe.
        for (int i = 0; i < nTarefas; i++) {
            if (foiFeita[i] == true) {
                encontrou = true;
                break;
            }
        }
        if (encontrou) {
            System.out.printf("%4s%1s%-60s%s%-10s%2s%5s%n", "", "", "Tarefa", "", "Data", "", "Feita");
			
            for (int i = 0; i < nTarefas; i++) {
                if (foiFeita[i] == true) {
                        // Coluna do n.º e da descrição da tarefa.
            		    System.out.printf("%3d:%1s%-60s%s", i + 1, "", tarefa[i], "");

                        if (data[i][0] > 0 && data[i][1] > 0 && data[i][2] > 0)
                            System.out.printf("%2d%s%2d%s%4d%2s", data[i][0], "/", data[i][1], "/", data[i][2], "");
                        else
                            System.out.printf("%10s%s", "", "");

                        System.out.printf("%3s%n", foiFeita[i] ? "X" : "");
                    
                }

            }
        } else
            System.out.println("Não existes tarefas feitas!");

        System.out.println();
    }

    static void visualizarPorPalavra(String[] tarefa, int[][] data, boolean[] foiFeita, int nTarefas) {
        System.out.println();
        System.out.print("Escreva uma palavra: ");
        String palavra = lerString();
        boolean encontrou = false;
        System.out.println();

        // Ciclo preliminar para verificar se existe ou não.
        // Desta forma evitaremos a impressão do cabeçalho abaixo mesmo quando não
        // existe.
        for (int i = 0; i < nTarefas; i++) {
            String[] dividePalavras = tarefa[i].split(" ");

            for (int j = 0; j < dividePalavras.length; j++) {
                if (palavra.equalsIgnoreCase(dividePalavras[j])) {
                    encontrou = true;
                    break;
                }
            }
        }

        if (encontrou) {
            System.out.printf("%4s%1s%-60s%s%-10s%2s%5s%n", "", "", "Tarefa", "", "Data", "", "Feita");

            for (int i = 0; i < nTarefas; i++) {
                String[] dividePalavras = tarefa[i].split(" ");

                for (int j = 0; j < dividePalavras.length; j++) {
                    if (palavra.equalsIgnoreCase(dividePalavras[j])) {
                        // Coluna do n.º e da descrição da tarefa.
            		    System.out.printf("%3d:%1s%-60s%s", i + 1, "", tarefa[i], "");

                        if (data[i][0] > 0 && data[i][1] > 0 && data[i][2] > 0)
                            System.out.printf("%2d%s%2d%s%4d%2s", data[i][0], "/", data[i][1], "/", data[i][2], "");
                        else
                            System.out.printf("%10s%s", "", "");

                        System.out.printf("%3s%n", foiFeita[i] ? "X" : "");

                        break; // Para evitar situações em que a palavra exista mais do que uma vez na tarefa.
                    }
                }
            }
        } else
            System.out.println("Palavra não encontrada!");

        System.out.println();
    }

    // Menu Principal.
    static void menuPrincipal(String[] tarefa, boolean[] temPrazo, boolean[] foiFeita, boolean[] ultimaFeita, int[][] data, int nTarefas) {
        int opcao = 0;
        do {
            System.out.println("Menu Principal:");
            System.out.println("(V) Visualizar");
            System.out.println("(M) Marcar");
            System.out.println("(E) Editar");
            System.out.println("(S) Sair");
            System.out.print("Ecolha uma opção: ");
            opcao = lerChar();

            switch (opcao) {
                case 'V':
                    System.out.println();
                    menuVisualizar(tarefa, temPrazo, foiFeita, data, nTarefas);
                    break;
                case 'M':
                    System.out.println();
                    menuMarcar(tarefa, foiFeita, ultimaFeita, data, nTarefas);
                    break;
                case 'E':
                    System.out.println();
                    nTarefas = menuEditar(tarefa, foiFeita, ultimaFeita, data, nTarefas, temPrazo);
                    break;
                case 'S':
                    System.out.println();
                    System.out.println("Adeus!");
                    break;
                default:
                    System.out.println();
                    System.out.println("Opção inválida!");
                    System.out.println();
            }
        } while (opcao != 'S');
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        int tamMax = 100;
        String[] tarefa = new String[tamMax];
        boolean[] temPrazo = new boolean[tamMax];
        boolean[] foiFeita = new boolean[tamMax];
        boolean[] ultimaFeita = new boolean[tamMax];
        int[][] data = new int[tamMax][3];
        int nTarefas = 0;

        nTarefas = inicializarTarefas(tarefa, temPrazo, foiFeita, data);

        menuPrincipal(tarefa, temPrazo, foiFeita, ultimaFeita, data, nTarefas);

        teclado.close();
    }
}