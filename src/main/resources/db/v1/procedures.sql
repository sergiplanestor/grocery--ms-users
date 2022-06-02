delimiter $$
CREATE OR REPLACE FUNCTION CUSTOMER_UUID(email VARCHAR(100)) RETURNS VARCHAR(32)
    BEGIN
        DECLARE uuid_found VARCHAR(32);
        SELECT t.uuid INTO uuid_found FROM tbl_customer t WHERE t.email = email;
        RETURN uuid_found;
    END; $$
-- delimiter ;
-- delimiter $$
CREATE OR REPLACE FUNCTION ROLE_UUID(role_name VARCHAR(15)) RETURNS VARCHAR(32)
    BEGIN
        DECLARE uuid_found VARCHAR(32);
        SELECT t.uuid INTO uuid_found FROM tbl_authority_role t WHERE t.role = role_name;
        RETURN uuid_found;
    END; $$
-- delimiter ;
-- delimiter $$
CREATE OR REPLACE FUNCTION PWD_ENCRYPT(pwd MEDIUMTEXT) RETURNS MEDIUMTEXT
    BEGIN
        RETURN TO_BASE64(AES_ENCRYPT(pwd, SHA2('Yarx229A63qkwwNEAwGSbf4cpFknWbrh', 512)));
    END; $$

CREATE OR REPLACE FUNCTION PWD_DECRYPT(uuid MEDIUMTEXT) RETURNS CHAR
    BEGIN
        DECLARE pwd_encrypted MEDIUMTEXT;
        SELECT a.pwd INTO pwd_encrypted FROM tbl_customer_identity a WHERE a.uuid = uuid;
        RETURN CAST(AES_DECRYPT(FROM_BASE64(pwd_encrypted), SHA2('Yarx229A63qkwwNEAwGSbf4cpFknWbrh', 512)) AS CHAR);
    END; $$
delimiter ;