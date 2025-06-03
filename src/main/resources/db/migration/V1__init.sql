-- Таблица: clients
CREATE TABLE clients (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL CHECK(length(name) <= 100),
    rating INTEGER CHECK (rating BETWEEN 0 AND 5000),
    email TEXT CHECK(length(email) <= 100),
    birth_date TEXT NOT NULL,
    registered_at TEXT NOT NULL
);

-- Таблица: categories
CREATE TABLE categories (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL UNIQUE CHECK(length(name) <= 50),
    description TEXT CHECK(length(description) <= 255),
    service_count INTEGER NOT NULL,
    popularity_score INTEGER
);

-- Таблица: procedures
CREATE TABLE procedures (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL CHECK(length(name) <= 150),
    procedure_type TEXT NOT NULL CHECK(length(procedure_type) <= 50),
    duration_minutes INTEGER NOT NULL CHECK(duration_minutes BETWEEN 0 AND 1440),
    price INTEGER NOT NULL,
    description TEXT CHECK(length(description) <= 1000)
);

-- Таблица связи: procedure_category (many-to-many)
CREATE TABLE procedure_category (
    procedure_id INTEGER NOT NULL,
    category_id INTEGER NOT NULL,
    PRIMARY KEY (procedure_id, category_id),
    FOREIGN KEY (procedure_id) REFERENCES procedures(id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE
);

-- Таблица: stylists
CREATE TABLE stylists (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL UNIQUE CHECK(length(name) <= 100),
    address TEXT CHECK(length(address) <= 255),
    contact_email TEXT NOT NULL CHECK(length(contact_email) <= 100),
    hire_date TEXT NOT NULL,
    salary INTEGER
);

-- Таблица: visits
CREATE TABLE visits (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    client_id INTEGER NOT NULL,
    procedure_id INTEGER NOT NULL,
    notes TEXT CHECK(length(notes) <= 255),
    visit_date_time TEXT NOT NULL,
    FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE CASCADE,
    FOREIGN KEY (procedure_id) REFERENCES procedures(id) ON DELETE CASCADE
);

-- Таблица: feedbacks
CREATE TABLE feedbacks (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    procedureId INTEGER NOT NULL,
    client_id INTEGER NOT NULL,
    rating INTEGER NOT NULL CHECK(rating BETWEEN 1 AND 10),
    comment TEXT CHECK(length(comment) <= 500),
    created_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TEXT,
    FOREIGN KEY (procedureId) REFERENCES procedures(id) ON DELETE CASCADE,
    FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE CASCADE
);
