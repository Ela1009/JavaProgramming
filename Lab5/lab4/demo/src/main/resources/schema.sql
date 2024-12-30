CREATE TABLE IF NOT EXISTS book (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    author VARCHAR(255),
    isbn VARCHAR(13),
    price DOUBLE,
    genre VARCHAR(100),
    published_year INT
);
