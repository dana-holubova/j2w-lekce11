CREATE TABLE kniha
(
    id         IDENTITY PRIMARY KEY,
    nazev      VARCHAR(255) NOT NULL,
    autor      VARCHAR(255) NOT NULL,
    rok_vydani SMALLINT,
    isbn       CHAR(13) CHECK REGEXP_LIKE(isbn, '[0-9]{13}|[0-9]{9}[0-9X]', ''),
    stornovano BOOLEAN      NOT NULL DEFAULT FALSE,
    version    INTEGER      NOT NULL
);
