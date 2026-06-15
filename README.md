# br-document-validator

Biblioteca Java para validação e formatação de CPF e CNPJ.

## 🚀 Como usar

Adicione o repositório do JitPack ao seu `pom.xml`:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Adicione a dependência:

```xml
<dependency>
    <groupId>com.github.Holkex</groupId>
    <artifactId>br-document-validator</artifactId>
    <version>v1.0.0</version>
</dependency>
```

## 🛠️ Exemplo de código

```java
import io.github.vinicius.DocumentValidator;

public class Main {
    public static void main(String[] args) {
        // Validação
        boolean isCpfValid = DocumentValidator.isCpfValid("52998224725");
        
        // Formatação
        String formattedCpf = DocumentValidator.formatCpf("52998224725"); // 529.982.247-25
        
        // Identificação
        String type = DocumentValidator.whatsDocumentIs("52998224725"); // "CPF"
    }
}
```

## 📄 Licença
Distribuído sob a licença MIT. Veja `pom.xml` para mais informações.
