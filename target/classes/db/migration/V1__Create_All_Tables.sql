CREATE TABLE public.users
(
    id   INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE public.files
(
    id      INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name    VARCHAR(50),
    content TEXT
);

CREATE TABLE public.events
(
    id        INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    activity  VARCHAR(10),
    dateEvent DATE,
    user_id   INT NOT NULL,
    file_id   INT,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (file_id) REFERENCES files (id)
);

