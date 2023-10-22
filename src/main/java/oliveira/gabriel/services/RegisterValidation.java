package oliveira.gabriel.services;

import oliveira.gabriel.entities.Client;

public class RegisterValidation {

    public static Boolean isExist(Client clientCompared) {
        Client client = Client.find("SELECT m FROM Client m WHERE m.documentId = ?1 OR m.email = ?2",
                clientCompared.getDocumentId(),clientCompared.getEmail()).firstResult();
        if (client != null) {
            System.out.println(client);
            return true;
        }
        return false;
    }
}
