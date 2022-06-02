-- AUTHORITY_ROLE
INSERT INTO
    tbl_authority_role(role, level)
VALUES
    ('ANONYMOUS', 1),
    ('CUSTOMER', 2),
    ('TESTER', 3),
    ('DEVELOPER', 4),
    ('ADMIN', 5 )
;

-- CUSTOMER
INSERT INTO
    tbl_customer(email, username, first_name, last_name)
VALUES
    ('grocery_admin_ms+customer@admin.com', 'Admin', 'Admin', null),
    ('grocery_dev_ms+customer@admin.com', '_d3v!', 'Developer', null)
;

-- CUSTOMER IDENTITY
INSERT INTO
    tbl_customer_identity(uuid, pwd)
VALUES
    ((SELECT c.uuid FROM tbl_customer c WHERE c.email = 'grocery_admin_ms+customer@admin.com'), 'EWRx6eyDZPVcKZMjuBL0hYeQ5wgh6dsG'),
    ((SELECT c.uuid FROM tbl_customer c WHERE c.email = 'grocery_dev_ms+customer@admin.com'), 'D3v1234!')
;

-- CUSTOMER IDENTITY ROLES
INSERT INTO
    tbl_customer_authority_roles(customer_uuid, role_uuid)
VALUES
    (
        CUSTOMER_UUID('grocery_admin_ms+customer@admin.com'),
        (SELECT r.uuid FROM tbl_authority_role r WHERE r.role = 'ADMIN')
    ),
    (
        CUSTOMER_UUID('grocery_dev_ms+customer@admin.com'),
        (SELECT r.uuid FROM tbl_authority_role r WHERE r.role = 'DEVELOPER')
    ),
    (
        CUSTOMER_UUID('grocery_dev_ms+customer@admin.com'),
        (SELECT r.uuid FROM tbl_authority_role r WHERE r.role = 'CUSTOMER')
    )
;