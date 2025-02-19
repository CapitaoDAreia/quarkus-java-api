INSERT INTO address (id, street, number, complement, neighborhood, city, state, country, zipCode)
VALUES (
           UUID_TO_BIN(UUID()),
           'Negra Arroyo Lane',
           '308',
           'Search for a pizza in the roof',
           '',
           'Albuquerque',
           'New Mexico',
           'United States',
           '87104'
       );

INSERT INTO customer (id, name, surName, email, cpf, address_id)
VALUES (
           UUID_TO_BIN(UUID()),
           'Walter',
           'White',
           'walter.white@lospolloshermanos.com',
           '12345678900',
           (SELECT id FROM address WHERE street = 'Negra Arroyo Lane' AND number = '308')
       );

INSERT INTO customer (id, name, surName, email, cpf, address_id)
VALUES (
           UUID_TO_BIN(UUID()),
           'Skyler',
           'White',
           'skyler.white@benekefabricators.com',
           '12345678999',
           (SELECT id FROM address WHERE street = 'Negra Arroyo Lane' AND number = '308')
       );

-- Inserindo a conta para o cliente Walter White
INSERT INTO account (id, accountNumber, agency, isActive, customer_id)
VALUES (
           UUID_TO_BIN(UUID()),
           '123456',
           '0001',
           true,
           (SELECT id FROM customer WHERE name = 'Walter' AND surName = 'White')
       );

INSERT INTO card (id, number, expirationDate, cvv, isActive, isBlocked, country, account_id)
VALUES (
           UUID_TO_BIN(UUID()),
           '1234 5678 1234 5678',
           '2025-12-01',
           '123',
           true,
           false,
           'United States',
           (SELECT id FROM account WHERE accountNumber = '123456' AND agency = '0001')
       );
