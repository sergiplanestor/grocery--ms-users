-- ·················································································································· --
-- CUSTOMER
-- ·················································································································· --
CREATE OR REPLACE TABLE tbl_customer(
    _id INT NOT NULL AUTO_INCREMENT,
    uuid VARCHAR(32) NOT NULL,
    email VARCHAR(100) NOT NULL,
    first_name VARCHAR(50) CHARACTER SET utf8 NOT NULL,
    last_name VARCHAR(50) CHARACTER SET utf8 DEFAULT NULL,
    username VARCHAR(50) CHARACTER SET utf8 DEFAULT NULL,
    date_creation DATE NOT NULL DEFAULT CURRENT_DATE,
    date_last_login TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (_id),
    UNIQUE U_tbl_customer_uuid (uuid),
    UNIQUE U_tbl_customer_email (email),
    CONSTRAINT CHCK_tbl_customer_email CHECK (email LIKE '%___@___%.__%'),
    CONSTRAINT CHCK_tbl_customer_first_name CHECK (LENGTH(TRIM(first_name)) > 3)
);

-- ·················································································································· --
-- CUSTOMER IDENTITY
-- ·················································································································· --
CREATE OR REPLACE TABLE tbl_customer_identity(
    _id INT NOT NULL AUTO_INCREMENT,
    uuid VARCHAR(32) NOT NULL,
    pwd MEDIUMTEXT NOT NULL,
    date_creation DATE NOT NULL DEFAULT CURRENT_DATE,
    date_last_update DATE NOT NULL DEFAULT CURRENT_DATE,

    PRIMARY KEY (_id),
    UNIQUE U_tbl_customer_identity_uuid (uuid),
    CONSTRAINT FK_tbl_customer___tbl_customer_identity FOREIGN KEY (uuid) REFERENCES tbl_customer(uuid)
);

CREATE OR REPLACE TABLE tbl_authority_role(
    _id INT NOT NULL AUTO_INCREMENT,
    uuid VARCHAR(32) NOT NULL,
    role VARCHAR(15) NOT NULL,
    level INT NOT NULL,

    PRIMARY KEY (_id),
    UNIQUE U_tbl_authority_role_uuid (uuid)
);

CREATE OR REPLACE TABLE tbl_customer_authority_roles(
    customer_uuid VARCHAR(32) NOT NULL,
    role_uuid VARCHAR(32) NOT NULL,

    PRIMARY KEY (customer_uuid, role_uuid),
    CONSTRAINT FK_tbl_customer___tbl_customer_authority_roles FOREIGN KEY (customer_uuid) REFERENCES tbl_customer(uuid),
    CONSTRAINT FK_tbl_authority_role___tbl_customer_authority_roles FOREIGN KEY (role_uuid) REFERENCES tbl_authority_role(uuid)
);