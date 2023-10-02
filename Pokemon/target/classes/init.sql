DROP TABLE IF EXISTS Pokemon;
CREATE TABLE IF NOT EXISTS Pokemon (
                                       id INT NOT NULL,
                                       num VARCHAR NOT NULL,
                                       name VARCHAR(20) NOT NULL,
                                       height VARCHAR(10) NOT NULL,
                                       weight VARCHAR(10) NOT NULL
);
INSERT INTO Pokemon (id, num, name, height, weight) VALUES(999, '999', 'Joe', '1.7 m', '6.9 kg');