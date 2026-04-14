CREATE TABLE countries (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255)
);

INSERT INTO countries (id, name) VALUES
                                     (1, 'USA'),
                                     (2, 'France'),
                                     (3, 'Brazil'),
                                     (4, 'Italy'),
                                     (5, 'Canada'),
                                     (6, 'VN'),
                                     (7, 'Laos'),
                                     (8, 'Thailand'),
                                     (9, 'Cambodia')
;

UPDATE countries
SET name = 'China'
WHERE id > 5
;
DELETE FROM countries WHERE id=6;