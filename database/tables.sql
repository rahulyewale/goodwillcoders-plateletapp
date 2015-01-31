-- Table: donor

-- DROP TABLE donor;

CREATE TABLE donor
(
  donor_id bigint NOT NULL,
  donor_uuid character varying(50),
  first_name character varying(50),
  middle_name character varying(50),
  last_name character varying(50),
  birth_date date,
  gender character varying(10),
  blood_group character varying,
  donation_component_type character varying,
  created_by character varying,
  created_date date,
  modified_by character varying,
  modified_date date,
  CONSTRAINT donor_p_key PRIMARY KEY (donor_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE donor
  OWNER TO postgres;

-- Table: donation_history

-- DROP TABLE donation_history;

CREATE TABLE donation_history
(
  donation_history_id bigint NOT NULL,
  donor_id bigint NOT NULL,
  donation_date date,
  donated_component_type character varying,
  isrejected boolean,
  remarks character varying(255),
  created_by character varying,
  created_date date,
  modified_by character varying,
  modified_date date,
  CONSTRAINT donation_history_id_p_key PRIMARY KEY (donation_history_id),
  CONSTRAINT donation_history_donor_id_fkey FOREIGN KEY (donor_id)
      REFERENCES donor (donor_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE donation_history
  OWNER TO postgres;

 -- Table: donor_address_details

-- DROP TABLE donor_address_details;

CREATE TABLE donor_address_details
(
  donor_address_id bigint NOT NULL,
  address_line_1 character varying,
  address_line_2 character varying,
  city character varying,
  state character varying,
  pin_code character varying,
  created_by character varying NOT NULL,
  created_date date NOT NULL,
  modified_by character varying,
  modified_date date,
  donor_id bigint NOT NULL,
  CONSTRAINT donor_address_details_pkey PRIMARY KEY (donor_address_id),
  CONSTRAINT donor_address_details_donor_id_fkey FOREIGN KEY (donor_id)
      REFERENCES donor (donor_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE donor_address_details
  OWNER TO postgres;

-- Table: donor_contact_details

-- DROP TABLE donor_contact_details;

CREATE TABLE donor_contact_details
(
  donor_contact_id bigint NOT NULL,
  contact_number character varying(20),
  contact_number_alternate character varying(20),
  email character varying(50),
  donor_id bigint NOT NULL,
  CONSTRAINT donor_contact_details_pkey PRIMARY KEY (donor_contact_id),
  CONSTRAINT donor_contact_details_donor_id_fkey FOREIGN KEY (donor_id)
      REFERENCES donor (donor_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE donor_contact_details
  OWNER TO postgres;

-- Table: donation_center

-- DROP TABLE donation_center;

CREATE TABLE donation_center
(
  donation_center_id bigint NOT NULL,
  donation_center_name character varying(30),
  address_line_1 character varying,
  address_line_2 character varying,
  city character varying,
  state character varying,
  pin_code character varying,
  created_by character varying NOT NULL,
  created_date date NOT NULL,
  modified_by character varying,
  modified_date date,
  CONSTRAINT donation_center_pkey PRIMARY KEY (donation_center_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE donation_center
  OWNER TO postgres;

  -- Table: donation_center

-- DROP TABLE donation_center;

CREATE TABLE communication_history
(
  communication_history_id bigint NOT NULL,
  request_id character varying,
  donor_id bigint NOT NULL,
  mobile_number character varying,
  donation_center_id bigint NOT NULL,
  requested_date date,
  sms_sent_date date,
  status character varying,
  created_by character varying NOT NULL,
  created_date date NOT NULL,
  CONSTRAINT communication_history_id PRIMARY KEY (communication_history_id),
  CONSTRAINT donation_center_donation_center_id_fkey FOREIGN KEY (donation_center_id)
      REFERENCES donation_center (donation_center_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE communication_history
  OWNER TO postgres;
  
-- Table: unique_request_txn
-- DROP TABLE unique_request_txn;

CREATE TABLE unique_request_txn
(
  unique_request_txn_id bigint NOT NULL,
  request_date date NOT NULL,
  donation_center_id bigint NOT NULL,
  request_id character varying NOT NULL,
  CONSTRAINT unique_request_txn_pkey PRIMARY KEY (unique_request_txn_id)
  
)
WITH (
  OIDS=FALSE
);
ALTER TABLE unique_request_txn
  OWNER TO postgres;