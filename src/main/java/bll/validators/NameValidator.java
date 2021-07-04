package bll.validators;

import model.Client;

/**
 * Clasa folosita pentru a verifica daca numele si prenumele contin cifre
 *
 */

public class NameValidator implements Validator<Client> {

    @Override
    public void validate(Client client) {
        char[] nume = client.getName().toCharArray();
        for(char ch: nume)
        {
            if(Character.isDigit(ch))
            {
                throw new IllegalArgumentException("Illegal name, it has numbers!");
            }
        }
    }
}