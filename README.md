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
