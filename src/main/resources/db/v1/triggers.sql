delimiter $$
CREATE OR REPLACE TRIGGER trigger___tbl_customer___uuid_autogenerate BEFORE INSERT ON tbl_customer
    FOR EACH ROW BEGIN
        SET NEW.uuid = UPPER(SYS_GUID());
        IF
            NEW.email IS NOT NULL THEN SET NEW.email = TRIM(NEW.email);
        END IF;
        IF
            NEW.first_name IS NOT NULL THEN SET NEW.first_name = TRIM(NEW.first_name);
        END IF;
        IF
            NEW.last_name IS NOT NULL THEN SET NEW.last_name = TRIM(NEW.last_name);
        END IF;
        IF
            NEW.username IS NOT NULL THEN SET NEW.username = TRIM(NEW.username);
        ELSEIF
            NEW.first_name IS NOT NULL THEN SET NEW.username = TRIM(NEW.first_name);
        END IF;
    END; $$

CREATE OR REPLACE TRIGGER trigger___tbl_authority_role___uuid_autogenerate BEFORE INSERT ON tbl_authority_role
    FOR EACH ROW BEGIN
        SET NEW.uuid = UPPER(SYS_GUID());
    END; $$

CREATE OR REPLACE TRIGGER trigger___tbl_customer_identity___encrypt BEFORE INSERT ON tbl_customer_identity
    FOR EACH ROW BEGIN
        SET NEW.pwd = PWD_ENCRYPT(NEW.pwd);
    END; $$
delimiter ;