-- Sequence: donor_id_seq

-- DROP SEQUENCE donor_id_seq;

CREATE SEQUENCE donor_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE donor_id_seq
  OWNER TO postgres;
  
-- Sequence: donation_history_id_seq

-- DROP SEQUENCE donation_history_id_seq;

CREATE SEQUENCE donation_history_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE donation_history_id_seq
  OWNER TO postgres;

-- Sequence: donor_contact_id_seq

-- DROP SEQUENCE donor_contact_id_seq;

CREATE SEQUENCE donor_contact_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE donor_contact_id_seq
  OWNER TO postgres;

-- Table: donor_address_details

-- Sequence: donor_address_id_seq

-- DROP SEQUENCE donor_address_id_seq;

CREATE SEQUENCE donor_address_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE donor_address_id_seq
  OWNER TO postgres;

  -- Sequence: donation_center_id_seq

-- DROP SEQUENCE donation_center_id_seq;

CREATE SEQUENCE donation_center_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 5
  CACHE 1;
ALTER TABLE donation_center_id_seq
  OWNER TO postgres;

  
  -- Sequence: communication_history_seq

-- DROP SEQUENCE communication_history_seq;

CREATE SEQUENCE communication_history_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 5
  CACHE 1;
ALTER TABLE communication_history_seq
  OWNER TO postgres;
 
-- Sequence: unique_request_txn_seq
-- DROP SEQUENCE unique_request_txn_seq;

CREATE SEQUENCE unique_request_txn_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 5
  CACHE 1;
ALTER TABLE unique_request_txn_seq
  OWNER TO postgres;