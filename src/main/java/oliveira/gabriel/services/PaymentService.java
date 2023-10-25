package oliveira.gabriel.services;

import oliveira.gabriel.entities.Client;

public abstract class PaymentService {

    public static void paymet(Client clientPayer, Client clientReciver, Double value) {
        clientPayer.debit(value);
        clientReciver.credit(value);
    }
}
