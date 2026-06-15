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

Para que **qualquer pessoa no mundo** possa usar sua biblioteca via Maven, você precisa publicá-la em um repositório central (como o Maven Central via Sonatype OSSRH).

### Requisitos Obrigatórios:
1.  **Chave GPG**: Você deve assinar seus arquivos `.jar`.
2.  **Javadoc e Sources**: O Maven Central exige que você envie o código-fonte e a documentação Javadoc.
3.  **Conta no JIRA da Sonatype**: Você precisa abrir um chamado para validar que você é dono do domínio `io.github.vinicius`.

### Plugins Necessários no `pom.xml`:
Você precisará adicionar estes plugins para gerar os arquivos exigidos:
*   `maven-source-plugin` (gera o -sources.jar)
*   `maven-javadoc-plugin` (gera o -javadoc.jar)
*   `maven-gpg-plugin` (assina os arquivos)

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
