CREATE SQL SECURITY INVOKER VIEW view_full_customer AS
    SELECT
        c.uuid,
        c.email,
        c.first_name,
        c.last_name,
        c.username,
        (PWD_DECRYPT(c.uuid)) as plain_password,
        r.role as highest_role,
        i.date_creation as current_pwd_creation_date,
        i.date_last_login as pwd_last_update_date,
        c.date_creation as customer_creation_date,
        c.date_last_login as customer_last_login_date,
    FROM tbl_customer_authority_roles cr
        RIGHT JOIN tbl_customer c ON cr.customer_uuid = c.uuid
        LEFT JOIN tbl_authority_role r ON cr.role_uuid = r.uuid AND
;