Ator Coordenador de curso

 Quem é
 - O Coordenador de Curso é um ator do SOCC, ele possui acesso a funcionalidades que lhe permitem executar tarefas como a organização da oferta de disciplinas e a gestão da matriz do seu curso.

O que faz no sistema
- Gera a matriz de oferta do curso 
- Resolve anomalias e conflitos na matriz de oferta do curso  
- Corrige a carga horária dos componentes curriculares

O que espera do sistema
- Receber recomendação de resolução de anomalias
- Geração automática da matriz de oferta do curso  

Responsabilidades
- Gestão da Matriz de demanda do curso
- Aprovação de planos de ensino
- Cadastro de componentes curriculares
- Correção de anomalias na distribuição de turmas

Desejos
- Notificação de choque de horários na alocação de turmas
- Proteção dos dados
- Interface intuitiva e fácil de usar (usabilidade)
- Acessar o sistema a partir de qualquer navegador (acessibilidade)
- Disponível sempre que eu for acessá-lo (disponibilidade)

Histórias de usuários (HU)

Identificador
Nome
HU01
Como Coordenador de Curso, eu quero logar no sistema para ter acesso aos recursos e funcionalidades relacionadas ao Processo de Oferta de Componentes Curriculares (POCC) de turmas.
HU02
Como Coordenador de Curso, eu quero que o sistema me permita aprovar Planos de Ensino, inserir sugestões de correção em seções do Plano de Ensino e submetê-las ao Docente para correção.
HU03
Como Coordenador de Curso, eu quero que o sistema permita que eu filtre a visualização do histórico de disciplinas ministradas por: Período de Oferta, nome do Docente, nome do CC, código do CC, código da turma, curso e matriz curricular.
HU04
Como Coordenador de Curso, eu quero que o sistema permita visualizar o histórico de CC ministradas a cada semestre letivo para auxiliar na tomada de decisões e na resolução de anomalias e conflitos.
HU05
Como Coordenador de Curso, eu quero ter acesso a uma interface intuitiva e fácil de usar.
HU06
Como Coordenador de Curso, eu quero acessar o sistema a partir de qualquer navegador.
HU07
Como Coordenador de Curso, eu quero que o sistema esteja disponível sempre que eu for acessá-lo.
HU08
Como Coordenador de Curso, eu quero que o sistema me permita visualizar as informações relevantes para meu trabalho de forma prática.
HU16
Como Coordenador de Curso, eu quero que o sistema me permita acessar e editar a matriz de demanda do curso (MDC), incluindo alterações nas turmas e em sua atribuição aos Docentes, para que eu possa consolidar a matriz de demanda de turmas que será encaminhada para o SIG da UFG (SIGAA).
HU17
Como Coordenador de Curso, eu quero cadastrar no sistema os CC(s) da(s) matriz(es) curricular(es) do meu curso para que o sistema possua as informações necessárias quando for criar uma MDC.
HU19
Como Coordenador de Curso, eu quero ter acesso a um histórico de calendários já criado por semestre.
HU20
Como Coordenador de Curso, eu quero que o sistema emita relatórios que apresente um histórico de preferências por semestre por docente.














Requisitos de Sistema (RS)

Requisitos funcionais (RF)

Identificador
Nome
RS1
O sistema deve permitir que Coordenadores de curso façam login para ter acesso aos recursos e funcionalidades relacionados ao processo de oferta de Componentes Curriculares (CCs) e turmas, conforme necessário.
RS7
O sistema deve permitir que os Coordenadores de Curso aprovem Planos de Ensino, insiram sugestões de correção em seções do Plano de Ensino e submetam essas sugestões ao Docente para correção.
RS10
O sistema deve permitir que o Coordenador de Curso acesse e edite a Matriz de Demanda do Curso (MDC), incluindo alterações nas turmas e em sua atribuição aos Docentes, para consolidar a matriz de demanda de turmas que será encaminhada para o Sistema Integrado de Gestão Acadêmica (SIGAA) da UFG.
RS14
O sistema deve permitir que o Coordenador cadastre os Componentes Curriculares (CCs) da(s) matriz(es) curricular(es) do seu curso, de modo que o sistema possua as informações necessárias quando for criar uma Matriz de Demanda do Curso (MDC).
RS28
O sistema deve permitir que  Coordenadores,   filtrem a visualização do histórico de disciplinas ministradas com base em diferentes critérios, incluindo Período de Oferta, nome do Docente, nome do Componente Curricular (CC), código do CC, código da turma, curso e matriz curricular.
RS33
O sistema deve calcular e exibir a Carga Horária Total Disponível na Unidade (CHTD), a Carga Horária Total Demandada por Curso (CHTD-Curso) e a Carga Horária Total Demandada pela Unidade Acadêmica (CHTD-UA) a partir das informações da matriz de oferta cadastrada e/ou consolidada.
RS35
O sistema deve permitir que  Coordenadores,  visualizem o histórico de Componentes Curriculares (CCs) ministrados a cada semestre letivo, facilitando a tomada de decisões e a resolução de anomalias e conflitos.
RS40
O sistema deve alertar o usuário (coordenador de curso/vice-diretor) quando este tentar alocar turmas de um mesmo semestre/curso com choque de horários


Requisitos não funcionais (RNF)

Identificador
Nome
RS21
O sistema deve oferecer uma interface intuitiva e fácil de usar, seguindo as heurísticas de Nielsen, tendo seu layout organizado de maneira lógica e intuitiva para facilitar a navegação e a localização das funcionalidades.
RS22
O sistema deve ser acessível a partir de qualquer navegador web e dispositivo, ou seja, a interface do sistema deve se adaptar de forma responsiva e consistente em diferentes tamanhos de tela(desktops, laptops, tablets e dispositivos móveis) e seus recursos e funcionalidades devem ser totalmente operacionais nos navegadores mais populares (Google Chrome, Mozilla Firefox, Microsoft Edge, Safari, entre outros) para garantir a compatibilidade multiplataforma.
RS23
O sistema deve estar disponível de forma contínua e estável, sem interrupções. Para isso, o sistema deve ser hospedado em uma infraestrutura confiável e escalável, capaz de suportar uma carga de usuários adequada sem comprometer a disponibilidade, com redundância, monitoramento constante, entrega contínua de atualizações e recuperação automática em caso de falhas.



Regras de negócio(RN)

Identificador
Nome
RN02
Após a composição inicial das turmas, cada coordenação de curso deve validar a alocação proposta. Se uma alocação não for validada, a coordenação deve ajustá-la em conformidade com os requisitos e disponibilidades específicos antes de sua finalização.
RN08
Toda oferta de turma deve estar alinhada com a matriz curricular vigente do curso. Se uma oferta desviar da matriz, ela deve ser corrigida antes de sua aprovação final.
RN17
Sempre que um coordenador de curso registrar a oferta de turmas no sistema, este deve validar automaticamente a conformidade com a matriz curricular e os horários estipulados. Caso haja discrepâncias, o sistema deve alertar o coordenador para correções. 
RN20
Sempre que uma nova turma for cadastrada no sistema, este deve verificar se a oferta está de acordo com a matriz curricular definida no Regulamento Geral dos Cursos de Graduação. Se não estiver conforme, o sistema deve rejeitar a oferta e notificar o coordenador do curso.
RN44
Um componente curricular (CC) de uma matriz de demanda deve conter todas as informações/atributos necessários desta matriz.
RN45
Uma Matriz de Oferta (MOC ou MOS) deve incluir, no mínimo, uma turma para cada componente curricular (CC) presente na Matriz de Demanda (MDC ou MDS) correspondente. Essa exigência aplica-se apenas aos CCs que fazem parte do fluxo das matrizes curriculares em vigor para o curso ao qual o CC pertence.





Relação entre UH e RS
HU01 - RS1
HU02 - RS7
HU03 - RS28
HU04 - RS35
HU05 - RS21
HU06 - RS22
HU07 - RS23
HU08 - não há um requisito bem definido informando sobre o que seria as “informações relevantes para meu trabalho de forma prática” relacionadas ao coordenador de curso.
HU16 - RS10
HU17 - RS14
HU19 - não consta nos requisitos sobre o armazenamento de antigos calendários ou de um histórico de calendários, muito menos do coordenador de curso tendo acesso a funcionalidade.
HU20 - não consta nos requisitos sobre esse histórico de preferências, além de também não constar como seria a análise desses dados por semestre


Itens já contemplados nos casos de uso
Realizar login - HU01 - RS1
Instanciar Matriz de Demanda de Curso - HU16 - RS10
 


Itens que precisam ser atendidos nos casos de uso
HU02 - RS7   CU  relacionados à análise dos Planos de Ensino, com opções como, aprovar, dar feedback, enviar de volta para correção
HU03 - RS28  CU relacionados a um histórico de disciplinas ministradas, tendo elas as seguintes características: período de oferta, nome do docente, nome do CC, código do CC, código da turma, curso e matriz curricular.
HU04 - RS35  CU relacionados a um histórico de CCs ministrados, separados por cada semestre.
HU17 - RS14 CU relacionado ao cadastro de CCs do seu curso
HU19  CU relacionado ao acesso a um histórico de calendários
HU20 CU relacionado a visualizar a preferência semestral de cada docente

