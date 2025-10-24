# Car Manager (Teste)

`Car Manager` é um aplicativo Android nativo desenvolvido como um teste técnico. O aplicativo permite ao usuário gerenciar uma lista de veículos, adicionando novos carros e visualizando os existentes. Os dados são persistidos na nuvem usando o Google Firebase Firestore.

## Tecnologias Utilizadas

Este projeto foi construído com uma abordagem moderna de desenvolvimento Android, utilizando as seguintes tecnologias:

* **Kotlin:** Linguagem de programação principal.
* **Jetpack Compose:** Toolkit de UI declarativo moderno para a construção da interface do usuário.
* **Arquitetura MVVM (Model-View-ViewModel):** Padrão de arquitetura para separar a lógica de negócios da UI.
    * **ViewModel:** Gerencia o estado da UI e interage com o repositório.
    * **ViewState/ViewAction:** Padrão MVI-like para um fluxo de dados unidirecional e gerenciamento de estado previsível.
* **Koin:** Injeção de dependência para gerenciar e fornecer dependências como ViewModels, Repositórios e instâncias do Firebase.
* **Firebase Firestore:** Banco de dados NoSQL em nuvem para persistir os dados dos veículos.
* **Jetpack Navigation (Compose):** Para gerenciar a navegação entre as telas do aplicativo.
* **Kotlin Coroutines & Flow:** Para gerenciamento de operações assíncronas, como chamadas de rede e atualizações de navegação.

## Instruções de Execução

Para executar este projeto, você precisará configurá-lo com seu próprio projeto Firebase.

**Importante:** O arquivo `app/google-services.json` não deve ser incluído no repositório por motivos de segurança. Siga os passos abaixo para gerar o seu.

**1. Clone o Repositório**

```bash
git clone https://github.com/carlosivis/car-manager-test.git
cd car-manager-test
```

**2. Crie um Projeto no Firebase**

1.  Acesse o [Console do Firebase](https://console.firebase.google.com/).
2.  Clique em **"Adicionar projeto"** e siga as instruções para criar um novo projeto.
3.  No painel do seu projeto, adicione um aplicativo Android.
    *   Use `dev.carlosivis.carmanager` como o nome do pacote Android.

**3. Adicione o `google-services.json`**

1.  Após adicionar o aplicativo Android, o Firebase fornecerá um arquivo `google-services.json` para download.
2.  Baixe este arquivo e coloque-o no diretório `app/` do seu projeto Android.

**4. Configure o Firestore**

1.  No menu do Firebase, vá para a seção **"Cloud Firestore"**.
2.  Clique em **"Criar banco de dados"**.
3.  Selecione **"Iniciar no modo de teste"**. Isso permite acesso aberto para facilitar o desenvolvimento inicial.
4.  Escolha uma localização para o seu banco de dados.

**5. Execute o Aplicativo**

1.  Abra o projeto no Android Studio.
2.  Aguarde o Android Studio sincronizar o projeto com os arquivos Gradle.
3.  Execute o aplicativo em um emulador ou em um dispositivo físico.
