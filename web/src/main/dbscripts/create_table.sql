/*
 * Script for creating table for postgres. Change it to suit your database
*/
CREATE TABLE user_profile
(
  id integer NOT NULL,
  first_name character varying,
  last_name character varying,
  "password" character varying,
  login_id character varying NOT NULL,
  CONSTRAINT user_profile_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);