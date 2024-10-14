## **Descrição da(s) Tela(s)**

### Tela de Núcleos de Conhecimento
| Nome do Atributo | Preench. Obrigatório | Preench. Automático | Tipo | Máscara | Observações | Regra de Interface |
|:--------------|:----------------:|:--------------:|:--------------|:----------------|:--------------|:----------------|
| Nome do Núcleo | x | Sim | Alfanumérico | | [RN36](#RN) | |
| Disciplinas | x | Sim | Seleção múltipla | | Nome, Código | |
| Docentes Associados | x | Sim | Seleção múltipla | | Ícone de usuário, Nome, Status, Data do Ingresso no Núcleo/Data da manifestação de interesse no núcleo | |
| Facilitador | x | Não | Seleção Única | | Docentes que pertencem aos cursos da Unidade Acadêmica - [RN37](#RN)  | |
| Criar | | | Botão | | | Visível apenas se o usuário for Vice-diretor |
| Deletar | | | Botão | | | Visível apenas se o usuário for Vice-diretor  |
| Visualizar| | | Botão| |Abre um modal com detalhes do núcleo |	Visível para todos os usuários |
| Descrição | x | Sim | Alfanumérico |  | Exibido no modal da ação de visualizar  | |
| Editar | | | Botão | | Exibido no modal da ação de visualizar | Visível apenas se o usuário for Vice-diretor |
* Tipos: Inteiro, Numérico, Alfanumérico, Data, Hora, Botão, Seleção única, Seleção múltipla, Lista
## **Fluxo Principal**
### FP01 - < Listar Os Núcleos de Conhecimento >

| ID | Passo | Fluxo | Regra de Negócio | Tela |
|:--------------|:----------------|:--------------|:----------------|:--------------|
| 1 | O Usuário seleciona a opção de "Núcleos de Conhecimento"| O usuário, a partir da Tela Inicial, clica na opção para acessar a lista de Núcleos de Conhecimento. | | [Tela Inicial](https://github.com/FabricaDeSoftwareINF/SOCC/blob/dev/documentacao/requisitos/caso-de-uso-visualizar-home.md)  |
| 2 | O sistema mostra uma sidebar com opções e uma lista com os núcleos de conhecimento existentes | A lista exibe informações como nome do núcleo, lista de docentes associados, facilitador e lista de disciplinas associadas. | | [Tela de Núcleos de Conhecimento](#tela-de-núcleos-de-conhecimento) |
### FA01 - < Criar Núcleo de Conhecimento (Somente Vice-Diretor) >

| ID | Passo | Fluxo | Regra de Negócio | Tela |
|:--------------|:----------------|:--------------|:----------------|:--------------|
| 1 | O Vice-Diretor acessa o sistema e seleciona a opção de "Núcleos de Conhecimento"|  | | [Tela Inicial](https://github.com/FabricaDeSoftwareINF/SOCC/blob/dev/documentacao/requisitos/caso-de-uso-visualizar-home.md) |
| 2 | O sistema mostra a tela de núcleo de conhecimento e suas opções |  | |[Tela de Núcleos de Conhecimento](#tela-de-núcleos-de-conhecimento) |
| 3 | O Vice-Diretor seleciona a opção de criar um novo núcleo |  |Somente o Vice-Diretor pode visualizar e selecionar essa opção. | [Tela de Núcleos de Conhecimento](#tela-de-núcleos-de-conhecimento) |
| 4 | O sistema exibe um modal com um formulário de criação do novo núcleo | | |  |
| 5 | O Vice-Diretor preenche as informações necessárias (nome do núcleo, descrição do núcleo, docentes associados, facilitador e disciplinas ) | O sistema valida os campos preenchidos, garantindo que todos os requisitos mínimos sejam atendidos. | [RN36](#RN) |  |
| 6 | O Vice-Diretor confirma a criação do novo núcleo | O sistema salva as informações e cria o novo núcleo na base de dados | [RN37](#RN) |  |
| 7 | O sistema exibe a confirmação da criação do núcleo | Uma mensagem de sucesso é exibida, confirmando que o núcleo foi criado com sucesso. | |  |
| 8 | O Vice-Diretor retorna à lista de núcleos de conhecimento | O sistema redireciona o Vice-Diretor para a Tela de Núcleos de Conhecimento | | [Tela de Núcleos de Conhecimento](#tela-de-núcleos-de-conhecimento) |

### FA02 - < Visualizar Núcleo de Conhecimento >

| ID | Passo | Fluxo | Regra de Negócio | Tela |
|:--------------|:----------------|:--------------|:----------------|:--------------|
| 1 | O usuário acessa o sistema e seleciona a opção de "Núcleos de Conhecimento"|  | | [Tela Inicial](https://github.com/FabricaDeSoftwareINF/SOCC/blob/dev/documentacao/requisitos/caso-de-uso-visualizar-home.md) |
| 2 | O sistema mostra a tela de núcleo de conhecimento e suas opções | | |[Tela de Núcleos de Conhecimento](#tela-de-núcleos-de-conhecimento) |
| 3 | O usuário seleciona a opção visualizar em algum núcleo de conhecimento | | |[Tela de Núcleos de Conhecimento](#tela-de-núcleos-de-conhecimento) |
| 4 | O sistema exibe o formulário com os detalhes do núcleo de conhecimento  (nome, descrição, lista de docentes, facilitador e lista de disciplinas associadas)|  | Somente o vice-diretor pode visualizar o  botão de editar  | [Tela de Núcleos de Conhecimento](#tela-de-núcleos-de-conhecimento) |

