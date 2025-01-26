DELETE  FROM project_worker; DELETE FROM project; DELETE FROM worker; DELETE FROM client;

INSERT INTO worker (ID, NAME, BIRTHDAY, LEVEL, SALARY)
VALUES
    (1, 'Neil deGrasse Tyson', '1958-10-05', 'Senior', 75000),
    (2, 'Marie Curie', '1967-11-07', 'Senior', 85000),
    (3, 'J. Robert Oppenheimer', '1904-04-22', 'Middle', 55000),
    (4, 'Carl Sagan', '1934-11-09', 'Senior', 90000),
    (5, 'George Boole', '1915-11-02', 'Middle', 52000),
    (6, 'Alan Turing', '1912-06-23', 'Middle', 60000),
    (7, 'Ada Lovelace', '1915-12-10', 'Junior', 30000),
    (8, 'Richard Feynman', '1918-05-11', 'Senior', 95000),
    (9, 'Tim Berners-Lee', '1955-06-08', 'Senior', 70000),
    (10, 'Grace Hopper', '1906-12-09', 'Trainee', 900),
    (11, 'Stephen Hawking', '1967-11-07', 'Senior', 80000),
    (12, 'Claude Shannon', '1916-04-30', 'Middle', 62000),
    (13, 'Barbara Liskov', '1939-11-07', 'Junior', 25000),
    (14, 'Donald Knuth', '1938-01-10', 'Senior', 95000),
    (15, 'Katherine Johnson', '1918-08-26', 'Trainee', 1200);

INSERT INTO client (ID, NAME)
VALUES
    (314159, 'Harvard University'),
    (27315, 'NASA'),
    (65536, 'Google'),
    (299792, 'SpaceX'),
    (4200, 'Oxford University'),
    (90210, 'Stanford University'),
    (112358, 'Cambridge University'),
    (101010, 'Apple Inc.'),
    (808, 'Tesla, Inc.'),
    (271828, 'MIT');

INSERT INTO project (ID, CLIENT_ID, NAME, START_DATE, FINISH_DATE)
VALUES
    (22816, 314159, 'Pi Number Calculation', '1938-01-10', '1938-02-10'),
    (31415, 65536, 'Manhattan Project', '1942-06-10', '1945-05-08'),
    (17345, 299792, 'Turing Machine Development', '1936-05-01', '1939-01-01'),
    (11235, 90210, 'Voyager Space Mission', '1977-09-05', '1986-01-01'),
    (40404, 808, 'Infinity Calculation', '1900-01-01', NULL),
    (27182, 271828, 'Division by Zero Research', '2001-01-01', '2001-12-01'),
    (66666, 27315, 'Doomsday Device Planning', '2029-01-01', '2029-12-31'),
    (77777, 112358, 'Perpetual Motion Machine Design', '1960-01-01', '1964-01-01'),
    (42000, 4200, 'Flat Earth Cartography', '1992-03-01', '1992-04-01'),
    (55555, 101010, 'Cats and Quantum Physics', '2015-02-01', '2023-02-01'),
    (90211, 90210, 'Artificial Intelligence Research', '1955-09-01', '1963-12-01'),
    (12345, 314159, 'Cryptography Evolution', '1970-01-01', '1978-04-01'),
    (29979, 299792, 'Mars Rover Exploration', '2004-01-01', '2011-01-01'),
    (18182, 271828, 'Time Travel Feasibility Study', '1988-01-01', '1996-01-01'),
    (99999, 27315, 'Human Genome Project', '1995-01-01', '2000-01-01');


INSERT INTO project_worker (PROJECT_ID, WORKER_ID)
VALUES

    (22816, 1),
    (22816, 5),
    (22816, 13),


    (31415, 2),
    (31415, 3),
    (31415, 11),


    (17345, 6),
    (17345, 12),


    (11235, 8),
    (11235, 4),
    (11235, 7),
    (11235, 10),


    (40404, 9),
    (40404, 14),


    (27182, 5),
    (27182, 3),


    (66666, 11),
    (66666, 8),
    (66666, 1),

    (77777, 2),
    (77777, 4),
    (77777, 6),


    (42000, 15),
    (42000, 10),


    (55555, 7),
    (55555, 13),
    (55555, 14),


    (90211, 9),
    (90211, 12),
    (90211, 15),


    (12345, 5),
    (12345, 3),
    (12345, 8),

    (29979, 2),
    (29979, 4),
    (29979, 11),


    (18182, 1),
    (18182, 6),
    (18182, 14),


    (99999, 13),
    (99999, 7),
    (99999, 10),
    (99999, 15);
