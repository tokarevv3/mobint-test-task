# mobint-test-task

### База данных
Работоспособность приложения проверялась на 3-х таблицах базы данных:
- client
- company
- company_admin

SQL-код для создания и наполнения таблиц:
```
CREATE TABLE company (
                         id SERIAL PRIMARY KEY,
                         number_mask VARCHAR(30) NOT NULL
);

INSERT INTO company (number_mask) VALUES
                                      ('+7 (***) ***-**-**'),
                                      ('+375 (**) ***-**-**');

CREATE TABLE company_admin (
                               id SERIAL PRIMARY KEY,
                               login VARCHAR(16) NOT NULL,
                               password VARCHAR(32) NOT NULL,
                               company_id BIGINT NOT NULL,
                               CONSTRAINT fk_company
                                   FOREIGN KEY (company_id)
                                       REFERENCES company(id)
                                       ON DELETE CASCADE
);

INSERT INTO company_admin (login, password, company_id) VALUES
                                                            ('admin_rus', '{noop}pass123', 1),
                                                            ('admin_bel', '{noop}admin456', 2);

CREATE TABLE client (
                        id SERIAL PRIMARY KEY,
                        full_name VARCHAR(255) NOT NULL,
                        masked_number VARCHAR(30) NOT NULL
);

INSERT INTO client (full_name, masked_number) VALUES
                                                  ('Алиса Иванова Петровна', '+7 (123) 445-22-33'),
                                                  ('Боб Бобов Бобич', '+7 (987) 654-11-22'),
                                                  ('Чарли Сидоров Джексонович', '+375 (29) 123-45-67'),
                                                  ('Диана Новик Анатольевна', '+375 (33) 987-65-43');
```
