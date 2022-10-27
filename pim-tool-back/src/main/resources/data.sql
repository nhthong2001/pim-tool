INSERT INTO EMPLOYEE (VISA)
VALUES ('QMV'),
       ('HNH'),
       ('HTV'),
       ('QKP'),
       ('MKN'),
       ('APL'),
       ('XHP'),
       ('TQP'),
       ('NQN'),
       ('PLH'),
       ('HNL'),
       ('TBH'),
       ('TDN'),
       ('HPN'),
       ('HUN'),
       ('BNN'),
       ('PNH'),
       ('VVT');

INSERT INTO GROUPS(GROUP_LEADER_ID)
VALUES (1),
       (2);

INSERT INTO PROJECT (PROJECT_NUMBER, NAME, STATUS, CUSTOMER, START_DATE, GROUP_ID, VERSION)
VALUES (3116, 'Facturation/Encaissements', 'NEW', 'Les Retaites Populaires', '2004-02-25', 1, 0),
       (3118, 'GKBWEB', 'FIN', 'GKB', '2002-10-10', 1, 0),
       (7157, 'MGBAHN-Main2015', 'INP', 'MGB Tourism', '2006-09-24', 1, 0),
       (7174, 'SOMED-SPITEX MAINT', 'NEW', 'SOMED-SPITEX MAINT', '2015-10-05', 2, 0),
       (7199, 'ARCHIMEDE-2015-3.14', 'PLA', 'Les Retaites Populaires', '2005-05-29', 2, 0);

INSERT INTO PROJECT_EMPLOYEE(EMPLOYEE_ID, PROJECT_ID)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 4),
       (2, 5);