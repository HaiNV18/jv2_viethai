CREATE TABLE accounts (
    id BIGINT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    gender VARCHAR(1) DEFAULT 'M',
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    role INT NOT NULL,
    id_country INT NOT NULL
);

INSERT INTO accounts (id, username, password, firstname, lastname, email, phone, role, id_country) VALUES
(1, 'admin1', '1234', 'Which', 'John', 'admin1@gmail.com', '0123456789', 1, 1),
(2, 'admin2', '1234', 'Donald', 'Trump', 'admin2@gmail.com', '0888333222', 1, 2),
(3, 'admin3', '1234', 'Baron', 'Trump', 'admin3@gmail.com', '0888333221', 1, 3),
(4, 'user1', '1234', 'Baron', 'Trump', 'user1@gmail.com', '0888333225', 2, 4),
(5, 'user2', '1234', 'Baron', 'Trump', 'user2@gmail.com', '0888333275', 2, 5),
(6, 'user3', '1234', 'Baron', 'Trump', 'user3@gmail.com', '0888333232', 2, 5),
(7, 'user4', '1234', 'Baron', 'Trump', 'user4@gmail.com', '0888333241', 2, 5),
(8, 'user5', '1234', 'Baron', 'Trump', 'user5@gmail.com', '0888334425', 2, 8),
(9, 'user6', '1234', 'Baron', 'Trump', 'user6@gmail.com', '0888333445', 2, 9)
;

INSERT INTO accounts (id, username, password, firstname, lastname, gender, email, phone, role, id_country) VALUES
(10, 'user7', '1234', 'Baron', 'Trump', 'F', 'user7@gmail.com', '0888333225', 2, 10),
(11, 'user8', '1234', 'Baron', 'Trump', 'F', 'user8@gmail.com', '0888333275', 2, 11),
(12, 'user9', '1234', 'Baron', 'Trump', 'F', 'user9@gmail.com', '0888333232', 2, 12),
(13, 'user10', '1234', 'Baron', 'Trump', 'F', 'user10@gmail.com', '0888333241', 2, 7)
;
