CREATE TABLE IF NOT EXISTS users
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(64) NOT NULL,
    lastName VARCHAR(64) NOT NULL,
    age      TINYINT     NOT NULL
);

DROP TABLE IF EXISTS users;

SELECT id,
       name,
       lastName,
       age
FROM users;

TRUNCATE users;

DELETE
FROM users
WHERE id = 1;

