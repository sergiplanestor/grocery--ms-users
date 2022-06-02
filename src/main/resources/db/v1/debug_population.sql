-- CUSTOMER
INSERT INTO
    tbl_customer(email, username, first_name, last_name)
VALUES
    ('test@xyz.com', 'Testy', 'Tester', 'Dev'),
    ('splanes@protonmail.com', 'CoolDaddy', 'Sergi', 'Planes')
;

-- CUSTOMER IDENTITY
INSERT INTO
    tbl_customer_identity(uuid, pwd)
VALUES
    ((SELECT c.uuid FROM tbl_customer c WHERE c.email = 'test@xyz.com'), 'Test1234!'),
    ((SELECT c.uuid FROM tbl_customer c WHERE c.email = 'splanes@protonmail.com'), '96ed7h83')
;

-- CUSTOMER IDENTITY ROLES
INSERT INTO
    tbl_customer_authority_roles(customer_uuid, role_uuid)
VALUES
    (
        (SELECT UUID_BY_EMAIL('test@xyz.com', 'tbl_customer')),
        (SELECT r.uuid FROM tbl_authority_role r WHERE r.role = 'CUSTOMER')
    ),
    (
        UUID_BY_EMAIL('splanes@protonmail.com', 'tbl_customer'),
        (SELECT r.uuid FROM tbl_authority_role r WHERE r.role = 'CUSTOMER')
    ),