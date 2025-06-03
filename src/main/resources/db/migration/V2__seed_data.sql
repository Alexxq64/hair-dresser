-- ==== Clients ====
INSERT INTO clients (name, rating, email, birth_date, registered_at)
VALUES
('Анна Иванова', 1200, 'anna@example.com', '1995-03-10', '2024-01-15'),
('Сергей Смирнов', 800, 'sergey@example.com', '1990-08-22', '2024-02-01');

-- ==== Categories ====
INSERT INTO categories (name, description, service_count, popularity_score)
VALUES
('Стрижки', 'Все виды стрижек для мужчин и женщин', 10, 95),
('Окрашивание', 'Покраска волос, мелирование, балаяж', 6, 88);

-- ==== Procedures ====
INSERT INTO procedures (name, procedure_type, duration_minutes, price, description)
VALUES
('Мужская стрижка', 'Стрижка', 30, 1500, 'Классическая мужская стрижка машинкой и ножницами.'),
('Женская стрижка', 'Стрижка', 45, 2000, 'Модельная стрижка для женщин.'),
('Окрашивание в один тон', 'Окрашивание', 90, 4000, 'Окрашивание волос в один цвет без аммиака.');

-- ==== Procedure-Category связи ====
INSERT INTO procedure_category (procedure_id, category_id)
VALUES
(1, 1),
(2, 1),
(3, 2);

-- ==== Stylists ====
INSERT INTO stylists (name, address, contact_email, hire_date, salary)
VALUES
('Мария Петрова', 'ул. Ленина, 12', 'maria@salon.ru', '2022-06-01T10:00:00', 60000),
('Алексей Козлов', 'пр. Мира, 45', 'aleksey@salon.ru', '2021-04-10T09:30:00', 70000);

-- ==== Visits ====
INSERT INTO visits (client_id, procedure_id, notes, visit_date_time)
VALUES
(1, 1, 'Обычная стрижка без пожеланий', '2024-05-10T14:00:00'),
(2, 3, 'Просьба использовать безаммиачный краситель', '2024-05-11T16:30:00');

-- ==== Feedbacks ====
INSERT INTO feedbacks (procedureId, client_id, rating, comment, created_at, updated_at)
VALUES
(1, 1, 9, 'Очень понравилась стрижка, мастер был вежливый.', '2024-05-10T15:00:00', NULL),
(3, 2, 8, 'Цвет вышел насыщенным, как и хотела.', '2024-05-11T18:00:00', NULL);
