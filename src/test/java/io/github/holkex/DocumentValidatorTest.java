package io.github.holkex;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DocumentValidatorTest {

    @Test
    void shouldValidateValidCpf() {
        assertTrue(DocumentValidator.isCpfValid("52998224725"));
        assertTrue(DocumentValidator.isCpfValid("529.982.247-25"));
        assertTrue(DocumentValidator.isCpfValid("12345678909"));
    }

    @Test
    void shouldRejectInvalidCpf() {
        assertFalse(DocumentValidator.isCpfValid("11111111111"));
        assertFalse(DocumentValidator.isCpfValid("12345678900"));
        assertFalse(DocumentValidator.isCpfValid(null));
        assertFalse(DocumentValidator.isCpfValid("123"));
    }

    @Test
    void shouldValidateValidCnpj() {
        assertTrue(DocumentValidator.isCnpjValid("11222333000181"));
        assertTrue(DocumentValidator.isCnpjValid("11.222.333/0001-81"));
    }

    @Test
    void shouldRejectInvalidCnpj() {
        assertFalse(DocumentValidator.isCnpjValid("00000000000000"));
        assertFalse(DocumentValidator.isCnpjValid("11222333000100"));
        assertFalse(DocumentValidator.isCnpjValid(null));
        assertFalse(DocumentValidator.isCnpjValid("123"));
    }

    @Test
    void shouldFormatCpf() {
        assertEquals("529.982.247-25", DocumentValidator.formatCpf("52998224725"));
    }

    @Test
    void shouldFormatCnpj() {
        assertEquals("11.222.333/0001-81", DocumentValidator.formatCnpj("11222333000181"));
    }

    @Test
    void shouldValidateAlphanumericCnpj() {
        // Exemplo baseado na lógica ASCII-48 (A=17, 1, B=18, 2, C=19, 3, D=20, 4, E=21, 5, F=22, 6) -> Dígitos 68
        assertTrue(DocumentValidator.isCnpjValid("A1B2C3D4E5F668"));
        assertTrue(DocumentValidator.isCnpjValid("A1.B2C.3D4/E5F6-68"));
    }
}
