-- Function: Update_uniquerequesttxn_fun()

-- DROP FUNCTION Update_uniquerequesttxn_fun();

CREATE OR REPLACE FUNCTION Update_uniquerequesttxn_fun()
  RETURNS trigger AS
$BODY$
DECLARE 
vn_DonorCount        	INTEGER:=0;
vn_ConfirmedCount      	INTEGER:=0;
vn_ReservedCount        INTEGER:=0;
vn_RejectedCount        INTEGER:=0;

BEGIN
     
	SELECT INTO vn_DonorCount count(donor_id)
	FROM   communication_history
	WHERE  request_id = NEW.request_id
	AND status='DONATED';

	SELECT INTO vn_ConfirmedCount count(donor_id)
	FROM   communication_history
	WHERE  request_id = NEW.request_id
	AND status='CONFIRMED';

	SELECT INTO vn_ReservedCount count(donor_id)
	FROM   communication_history
	WHERE  request_id = NEW.request_id
	AND status='RESERVED';

	SELECT INTO vn_RejectedCount count(donor_id)
	FROM   communication_history
	WHERE  request_id = NEW.request_id
	AND status='REJECTED';

	
	 IF (TG_OP = 'INSERT') OR (TG_OP = 'UPDATE') THEN
	
                UPDATE unique_request_txn 
				set confirmed_count =vn_ConfirmedCount,
				donated_count=vn_DonorCount,
				reject_count=vn_RejectedCount,
				reserve_count=vn_ReservedCount
				where request_id = NEW.request_id;

				RETURN NULL ;
						   
    END IF;				
	      
		Return null;
		
	EXCEPTION
		WHEN OTHERS THEN
		RAISE NOTICE  'Update_uniquerequesttxn_fun % %', SQLERRM, SQLSTATE;
		--RETURN x;
END;
 $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION Update_uniquerequesttxn_fun()
  OWNER TO postgres;
