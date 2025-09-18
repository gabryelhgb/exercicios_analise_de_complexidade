import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MaquinaTuringPalindromoTest {

    @Test
    void test1() {
        MaquinaTuringPalindromo objeto = new MaquinaTuringPalindromo("abba");
        boolean retorno = objeto.VerificaTuring();
        assertEquals(true, retorno); 
    }

    @Test
    void test2() {
        MaquinaTuringPalindromo objeto = new MaquinaTuringPalindromo("aaaaaaa");
        boolean retorno = objeto.VerificaTuring();
        assertEquals(true, retorno); 
    }

    @Test
    void test3() {
        MaquinaTuringPalindromo objeto = new MaquinaTuringPalindromo("ababa");
        boolean retorno = objeto.VerificaTuring();
        assertEquals(true, retorno); 
    }

    @Test
    void test4() {
        MaquinaTuringPalindromo objeto = new MaquinaTuringPalindromo("bbbbbbbbbbbbb");
        boolean retorno = objeto.VerificaTuring();
        assertEquals(true, retorno); 
    }


    @Test
    void test5() {
        MaquinaTuringPalindromo objeto = new MaquinaTuringPalindromo("baaab");
        boolean retorno = objeto.VerificaTuring();
        assertEquals(true, retorno); 
    }

    @Test
    void test6() {
        MaquinaTuringPalindromo objeto = new MaquinaTuringPalindromo("baaaaaaaaaaab");
        boolean retorno = objeto.VerificaTuring();
        assertEquals(true, retorno); 
    }


    @Test
    void test7() {
        MaquinaTuringPalindromo objeto = new MaquinaTuringPalindromo("bba");
        boolean retorno = objeto.VerificaTuring();
        assertEquals(false, retorno);
    }


    @Test
    void test8() {
        MaquinaTuringPalindromo objeto = new MaquinaTuringPalindromo("abbb");
        boolean retorno = objeto.VerificaTuring();
        assertEquals(false, retorno); 
    }


    @Test
    void test9() {
        MaquinaTuringPalindromo objeto = new MaquinaTuringPalindromo("bbbaaa");
        boolean retorno = objeto.VerificaTuring();
        assertEquals(false, retorno); 
    }


    @Test
    void test10() {
        MaquinaTuringPalindromo objeto = new MaquinaTuringPalindromo("aabbbb");
        boolean retorno = objeto.VerificaTuring();
        assertEquals(false, retorno);
    }
    

    @Test
    void test11() {
        MaquinaTuringPalindromo objeto = new MaquinaTuringPalindromo("babababa");
        boolean retorno = objeto.VerificaTuring();
        assertEquals(false, retorno);
    }

}
