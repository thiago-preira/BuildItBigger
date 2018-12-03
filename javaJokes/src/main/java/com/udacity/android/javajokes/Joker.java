package com.udacity.android.javajokes;

import java.util.Random;

public class Joker {
    Random random;
    private String[] jokes;

    public Joker() {
        random = new Random();
        jokes = new String[3];
        jokes[0] = "A professora chega para o Joãozinho e diz:\n" +
                "\n" +
                " - Joãozinho qual é o tempo da frase: Eu procuro um homem fiel?\n" +
                "\n" +
                " E então Joãozinho responde\n" +
                "\n" +
                "- É tempo perdido!\n" +
                "Piadas: http://www.piadas.com.br/";
        jokes[1] = "O sujeito bate à porta de uma casa e, assim que um homem abre, ele diz:\n" +
                "\n" +
                "-O senhor poderia contribuir com o Lar dos Idosos?\n" +
                "\n" +
                "-Claro! Espere um pouco, que vou buscar minha sogra!\n" +
                "Piadas: http://www.piadas.com.br/";

        jokes[2] = "Na aula de química o professor pergunta:\n" +
                "- Quais as principais reações do álcool?\n" +
                "\n" +
                "O aluno responde:\n" +
                "- Chorar pela ex, achar que esta rico, ficar valente e pegar mulher feia ...\n" +
                "\n" +
                "Professor: \n" +
                "- Tirou 10!\n" +
                "Piadas: http://www.piadas.com.br/";
    }

    public String getJoke(){
        return jokes[random.nextInt(jokes.length)];
    }
}
