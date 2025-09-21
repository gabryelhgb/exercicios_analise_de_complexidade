import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MaquinaDeTuringTest {

    private static MaquinaDeTuring mtL1;
    private static MaquinaDeTuring mtL2;

    @BeforeAll
    public static void setup() {
        mtL1 = MaquinaDeTuring.criarMaquinaLinguagem1();
        mtL2 = MaquinaDeTuring.criarMaquinaLinguagem2();
    }

    // ===================== Testes Linguagem 1 =====================
    @Test
    public void testLinguagem1Aceita() {
        assertTrue(mtL1.executar("ababa"), "ababa deveria ser ACEITA");
        assertTrue(mtL1.executar("baaab"), "baaab deveria ser ACEITA");
        assertTrue(mtL1.executar("baaaaaaaaaaab"), "baaaaaaaaaaab deveria ser ACEITA");
        assertTrue(mtL1.executar("bba"), "bba deveria ser ACEITA");
        assertTrue(mtL1.executar("abbb"), "abbb deveria ser ACEITA");
        assertTrue(mtL1.executar("aabbbb"), "aabbbb deveria ser ACEITA");
        assertTrue(mtL1.executar("aaaaaaa"), "aaaaaaa deveria ser ACEITA");
        assertTrue(mtL1.executar("bbbbbbbbbbbbb"), "bbbbbbbbbbbbb deveria ser ACEITA");
    }

    @Test
    public void testLinguagem1Rejeita() {
        assertFalse(mtL1.executar("bbbaaa"), "bbbaaa deveria ser REJEITADA");
        assertFalse(mtL1.executar("babababa"), "babababa deveria ser REJEITADA");
        assertFalse(mtL1.executar("abba"), "abba deveria ser REJEITADA");
    }

    // ===================== Testes Linguagem 2 =====================
    @Test
    public void testLinguagem2Aceita() {
        assertTrue(mtL2.executar("ac"), "ac deveria ser ACEITA");
        assertTrue(mtL2.executar("bd"), "bd deveria ser ACEITA");
        assertTrue(mtL2.executar("abcd"), "abcd deveria ser ACEITA");
        assertTrue(mtL2.executar("aabccd"), "aabccd deveria ser ACEITA");
        assertTrue(mtL2.executar("abbcdd"), "abbcdd deveria ser ACEITA");
        assertTrue(mtL2.executar("aabbccdd"), "aabbccdd deveria ser ACEITA");
    }

    @Test
    public void testLinguagem2Rejeita() {
        assertFalse(mtL2.executar("abc"), "abc deveria ser REJEITADA");
        assertFalse(mtL2.executar("aabcd"), "aabcd deveria ser REJEITADA");
        assertFalse(mtL2.executar("abcdd"), "abcdd deveria ser REJEITADA");
        assertFalse(mtL2.executar("abbcd"), "abbcd deveria ser REJEITADA");
        assertFalse(mtL2.executar("abd"), "abd deveria ser REJEITADA");
    }
}
