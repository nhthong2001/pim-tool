INSERT INTO EMPLOYEE (VISA)
VALUES ('QMV'), ('HNH');

INSERT INTO GROUPS(GROUP_LEADER_ID)
VALUES (1), (2);

INSERT INTO PROJECT (PROJECT_NUMBER, NAME, STATUS, CUSTOMER, START_DATE, GROUP_ID)
VALUES (3116,'Facturation/Encaissements', 'NEW', 'Les Retaites Populaires', '2004-02-25', 1),
       (3118,'GKBWEB', 'FIN', 'GKB', '2002-10-10', 1),
       (7157,'MGBAHN-Main2015', 'INP', 'MGB Tourism', '2006-09-24', 1),
       (7174,'SOMED-SPITEX MAINT', 'NEW', 'SOMED-SPITEX MAINT', '2015-10-05', 2),
       (7199,'ARCHIMEDE-2015-3.14', 'PLA', 'Les Retaites Populaires', '2005-05-29', 2);

INSERT INTO PROJECT_EMPLOYEE(EMPLOYEE_ID, PROJECT_ID)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 4),
       (2, 5);

--         ('HTV', 'Project Leader'),
--        ('QKP', 'Project Leader'),
--        ('MKN', 'Project Leader'),
--        ('APL', 'Project Leader'),
--        ('XHP', 'Project Leader'),
--        ('TQP', 'Developer'),
--         ('HNH', 'Quality Agent'),
--        ('NQN', 'Developer'),
--        ('PLH', 'Quality Agent'),
--        ('HNL', 'Developer'),
--        ('TBH', 'Quality Agent'),
--        ('TDN', 'Developer'),
--        ('HPN', 'Developer'),
--        ('HUN', 'Quality Agent'),
--        ('BNN', 'Developer'),
--        ('PNH', 'Quality Agent'),
--        ('QMV', 'Quality Agent'),
--        ('VVT', 'Developer');
--



-- INSERT INTO USER (USERNAME)
-- VALUES
--     ('USER1'),
--     ('USER2'),
--     ('USER3');

-- INSERT INTO TASK(NAME, DEADLINE, PROJECT_ID, USER_ID)
-- VALUES ('EFV_TASK_1', '2020-03-05', 1, 1),
--        ('EFV_TASK_2', '2020-03-10', 1, null),
--        ('EFV_TASK_3', '2020-03-15', 1, null);