CREATE TABLE IF NOT EXISTS TB_CITY (
    ID INTEGER NOT NULL PRIMARY KEY,
    NAME VARCHAR(254) NOT NULL,
    POPULATION INTEGER NOT NULL
);

INSERT INTO TB_CITY 
    (ID, NAME, POPULATION)
VALUES
    (1, 'São Paulo', 100),
    (2, 'Porto Alegre', 50),
    (3, 'Florianópolis', 65),
    (4, 'Curitiba', 35),
    (5, 'Rio de Janeiro', 80),
    (6, 'Porto Belo', 10),
    (7, 'Londrina', 15),
    (8, 'Piracicaba', 25),
    (9, 'Dois Irmãos', 78),
    (10, 'Passo Fundo', 38);