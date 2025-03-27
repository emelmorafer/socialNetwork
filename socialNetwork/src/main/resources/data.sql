-- data.sql

CREATE TABLE IF NOT EXISTS Useer(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255),
    date_birth DATETIME,
    e_mail VARCHAR(255),
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Post(
    id INT AUTO_INCREMENT PRIMARY KEY,
    content VARCHAR(255),
    like_number INT,
    user_id INT NOT NULL,
    publication_date DATETIME,
    FOREIGN KEY (user_id) REFERENCES Useer(id) ON DELETE CASCADE
);


INSERT INTO Useer (name,last_name,date_birth,e_mail,username,password)
SELECT
'Emel',
'Mora',
'1986-03-09 20:40:00',
'emelmora@gmail.com',
'emelmora',
'123456*'
WHERE NOT EXISTS (SELECT 1 FROM Useer WHERE username = 'emelmora');


INSERT INTO Useer (name,last_name,date_birth,e_mail,username,password)
SELECT
'Carlos',
'Medina',
'1986-08-10 20:40:00',
'medina@gmail.com',
'carmedina',
'abcde*'
WHERE NOT EXISTS (SELECT 1 FROM Useer WHERE username = 'carmedina');



INSERT INTO Post (content,like_number,user_id,publication_date)
SELECT
'contenido escrito por emelmora 1',
0, (SELECT id FROM Useer WHERE username = 'emelmora'),
'2025-03-09 20:40:00'
WHERE NOT EXISTS (SELECT 1 FROM Post WHERE content = 'contenido escrito por emelmora 1');


INSERT INTO Post (content,like_number,user_id,publication_date)
SELECT
'contenido escrito por emelmora 2',
0, (SELECT id FROM Useer WHERE username = 'emelmora'),
'2025-03-10 20:40:00'
WHERE NOT EXISTS (SELECT 1 FROM Post WHERE content = 'contenido escrito por emelmora 2');


INSERT INTO Post (content,like_number,user_id,publication_date)
SELECT
'contenido escrito por emelmora 3',
0, (SELECT id FROM Useer WHERE username = 'emelmora'),
'2025-03-10 20:40:00'
WHERE NOT EXISTS (SELECT 1 FROM Post WHERE content = 'contenido escrito por emelmora 3');


INSERT INTO Post (content,like_number,user_id,publication_date)
SELECT
'contenido escrito por emelmora 4',
0, (SELECT id FROM Useer WHERE username = 'emelmora'),
'2025-03-10 20:40:00'
WHERE NOT EXISTS (SELECT 1 FROM Post WHERE content = 'contenido escrito por emelmora 4');


INSERT INTO Post (content,like_number,user_id,publication_date)
SELECT
'contenido escrito por carmedina 1',
0, (SELECT id FROM Useer WHERE username = 'carmedina'),
'2025-02-11 07:40:00'
WHERE NOT EXISTS (SELECT 1 FROM Post WHERE content = 'contenido escrito por carmedina 1');


INSERT INTO Post (content,like_number,user_id,publication_date)
SELECT
'contenido escrito por carmedina 2',
0, (SELECT id FROM Useer WHERE username = 'carmedina'),
'2025-02-07 13:40:00'
WHERE NOT EXISTS (SELECT 1 FROM Post WHERE content = 'contenido escrito por carmedina 2');


INSERT INTO Post (content,like_number,user_id,publication_date)
SELECT
'contenido escrito por carmedina 3',
0, (SELECT id FROM Useer WHERE username = 'carmedina'),
'2025-02-07 13:40:00'
WHERE NOT EXISTS (SELECT 1 FROM Post WHERE content = 'contenido escrito por carmedina 3');