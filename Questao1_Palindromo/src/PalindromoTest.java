import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PalindromoTest {
    

    @Test
    void test1() {
        Palindromo objeto = new Palindromo("abba");
        boolean retorno = objeto.ehPalindromo();
        assertEquals(true, retorno); 
    }

    @Test
    void test2() {
        Palindromo objeto = new Palindromo("aaaaaaa");
        boolean retorno = objeto.ehPalindromo();
        assertEquals(true, retorno); 
    }

    @Test
    void test3() {
        Palindromo objeto = new Palindromo("ababa");
        boolean retorno = objeto.ehPalindromo();
        assertEquals(true, retorno); 
    }

    @Test
    void test4() {
        Palindromo objeto = new Palindromo("bbbbbbbbbbbbb");
        boolean retorno = objeto.ehPalindromo();
        assertEquals(true, retorno); 
    }


    @Test
    void test5() {
        Palindromo objeto = new Palindromo("baaab");
        boolean retorno = objeto.ehPalindromo();
        assertEquals(true, retorno); 
    }

    @Test
    void test6() {
        Palindromo objeto = new Palindromo("baaaaaaaaaaab");
        boolean retorno = objeto.ehPalindromo();
        assertEquals(true, retorno); 
    }


    @Test
    void test7() {
        Palindromo objeto = new Palindromo("bba");
        boolean retorno = objeto.ehPalindromo();
        assertEquals(false, retorno);
    }


    @Test
    void test8() {
        Palindromo objeto = new Palindromo("abbb");
        boolean retorno = objeto.ehPalindromo();
        assertEquals(false, retorno); 
    }


    @Test
    void test9() {
        Palindromo objeto = new Palindromo("bbbaaa");
        boolean retorno = objeto.ehPalindromo();
        assertEquals(false, retorno); 
    }


    @Test
    void test10() {
        Palindromo objeto = new Palindromo("aabbbb");
        boolean retorno = objeto.ehPalindromo();
        assertEquals(false, retorno);
    }
    

    @Test
    void test11() {
        Palindromo objeto = new Palindromo("babababa");
        boolean retorno = objeto.ehPalindromo();
        assertEquals(false, retorno);
    }

}
