CREATE EXTENSION "uuid-ossp";

CREATE TABLE Authors (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE Posts (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    publish_date DATE NOT NULL DEFAULT CURRENT_DATE,
    author_id uuid REFERENCES Authors(id) ON DELETE CASCADE
);
