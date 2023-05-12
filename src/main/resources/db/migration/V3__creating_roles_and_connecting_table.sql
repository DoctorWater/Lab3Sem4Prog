ALTER TABLE employee
ADD password VARCHAR(100) DEFAULT '123' NOT NULL AFTER date_of_birth;