CREATE TABLE countries (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

INSERT INTO countries (name) VALUES
                                     ('USA'),
                                     ('France'),
                                     ('Brazil'),
                                     ('Italy'),
                                     ('Canada'),
                                     ('VN'),
                                     ('Laos'),
                                     ('Thailand'),
                                     ('Cambodia')
;

UPDATE countries
SET name = 'China'
WHERE id > 5
;
DELETE FROM countries WHERE id=6;


;
-- SELECT * FROM COUNTRIES;

-- SELECT A.ID, USERNAME, FIRSTNAME, LASTNAME, NAME
-- FROM ACCOUNTS A
--          JOIN COUNTRIES C
--               ON C.ID = A.ID_COUNTRY
-- WHERE A.GENDER = 'M';

-- SELECT COUNT(DISTINCT NAME) AS COUNTRY_NAME
-- FROM ACCOUNTS A
--          JOIN COUNTRIES C
--               ON C.ID = A.ID_COUNTRY
-- WHERE GENDER = 'M';

-- SELECT A.ID, USERNAME, FIRSTNAME, LASTNAME, NAME
-- FROM ACCOUNTS A
--          INNER JOIN COUNTRIES C
--                     ON C.ID = A.ID_COUNTRY;