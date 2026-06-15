# Publicando e Testando sua Biblioteca Maven

Este guia explica como disponibilizar sua biblioteca `br-document-validator` para uso em outros projetos.

## 1. Instalação Local (Para Testes Imediatos)

Antes de publicar na internet, você deve "instalar" a biblioteca na sua máquina. Isso a coloca no seu repositório local (geralmente em `~/.m2/repository`).

Execute o comando na raiz do projeto:
```bash
mvn clean install
```
Se o comando terminar com `BUILD SUCCESS`, qualquer outro projeto na sua máquina poderá usar a biblioteca.

---

## 2. Testando em um Projeto Externo

Para garantir que a biblioteca funciona como esperado:

1.  Crie uma nova pasta fora deste projeto.
2.  Crie um novo arquivo `pom.xml` simples com a dependência:
    ```xml
    <dependency>
        <groupId>io.github.vinicius</groupId>
        <artifactId>br-document-validator</artifactId>
        <version>1.0.0</version>
    </dependency>
    ```
3.  Crie uma classe Java e tente importar `io.github.vinicius.DocumentValidator`.

---

## 3. Publicação Oficial (Maven Central / OSSRH)

Publicar no Maven Central torna sua biblioteca "oficial". É o que permite que alguém a use apenas adicionando a `<dependency>` sem precisar de um `<repository>` extra.

### Passo 1: Conta no Sonatype (OSSRH)
1.  Acesse o [Sonatype JIRA](https://issues.sonatype.org/) ou o novo portal [Central Portal](https://central.sonatype.com/).
2.  Crie uma conta e abra um chamado (Ticket) de "New Project".
3.  Você terá que provar que é dono do `io.github.holkex`. Geralmente eles pedem para você criar um repositório vazio com um nome específico no seu GitHub.

### Passo 2: Assinatura GPG (Obrigatório)
O Maven Central exige que todos os arquivos sejam assinados digitalmente.
1.  Instale o [GnuPG](https://gnupg.org/download/).
2.  Gere uma chave: `gpg --gen-key`.
3.  Distribua sua chave pública para um servidor de chaves: `gpg --keyserver keyserver.ubuntu.com --send-keys SUA_CHAVE`.

### Passo 3: Configurar o `settings.xml` do seu PC
Você não coloca sua senha do Sonatype no `pom.xml`. Você coloca no arquivo global do Maven (`~/.m2/settings.xml`):
```xml
<settings>
  <servers>
    <server>
      <id>ossrh</id>
      <username>seu_usuario_sonatype</username>
      <password>sua_senha_ou_token</password>
    </server>
  </servers>
</settings>
```

### Passo 4: Plugins Necessários no `pom.xml`
Para o Maven Central aceitar, seu projeto **precisa** enviar o código-fonte e o Javadoc. Eu já preparei a estrutura básica, mas você precisará ativar estes plugins para o deploy:
*   `maven-source-plugin`: Gera o JAR com o código-fonte.
*   `maven-javadoc-plugin`: Gera o JAR com a documentação.
*   `maven-gpg-plugin`: Faz a assinatura digital.
*   `nexus-staging-maven-plugin`: Envia tudo para o servidor da Sonatype.

### Passo 5: O Comando de Deploy
Quando tudo estiver configurado:
```bash
mvn clean deploy -P release
```
Isso enviará sua biblioteca para um "Staging Repository". Você entra no site da Sonatype, confere se está tudo certo, e clica em **"Release"**. Em algumas horas, ela estará no search.maven.org.

---

## 4. Alternativa Fácil: JitPack.io

O JitPack é a forma mais rápida de transformar seu repositório do GitHub em uma biblioteca Maven sem precisar de burocracia.

### Passo a Passo para o JitPack:

1.  **Suba seu código no GitHub**:
    *   Crie um repositório chamado `br-document-validator`.
    *   Envie este projeto para lá.

2.  **Crie uma "Release"**:
    *   No seu repositório no GitHub, clique em **"Releases"** (no lado direito).
    *   Clique em **"Draft a new release"**.
    *   No campo "Tag version", coloque `v1.0.0`.
    *   Dê um título e clique em **"Publish release"**.

3.  **Acesse o JitPack**:
    *   Vá em [https://jitpack.io/](https://jitpack.io/).
    *   Cole a URL do seu repositório (ex: `https://github.com/vinicius/br-document-validator`).
    *   Clique em **"Look up"**.
    *   Você verá a versão `v1.0.0` na lista. Clique em **"Get it"**.

4.  **Como seus usuários devem usar**:
    Eles precisarão adicionar o repositório do JitPack no `pom.xml` deles:
    ```xml
    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
    ```

    E a dependência (note que o `groupId` muda levemente para incluir o JitPack):
    ```xml
    <dependency>
        <groupId>com.github.vinicius</groupId>
        <artifactId>br-document-validator</artifactId>
        <version>v1.0.0</version>
    </dependency>
    ```
