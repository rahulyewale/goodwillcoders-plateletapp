--adding columns ( confirmed_count,donated_count,reject_count,reserve_count) and default to 0
ALTER TABLE unique_request_txn ADD COLUMN confirmed_count integer;
ALTER TABLE unique_request_txn ALTER COLUMN confirmed_count SET DEFAULT 0;

ALTER TABLE unique_request_txn ADD COLUMN donated_count integer;
ALTER TABLE unique_request_txn ALTER COLUMN donated_count SET DEFAULT 0;

ALTER TABLE unique_request_txn ADD COLUMN reject_count integer;
ALTER TABLE unique_request_txn ALTER COLUMN reject_count SET DEFAULT 0;

ALTER TABLE unique_request_txn ADD COLUMN reserve_count integer;
ALTER TABLE unique_request_txn ALTER COLUMN reserve_count SET DEFAULT 0;

--Update all previous values for columns ( confirmed_count,donated_count,reject_count,reserve_count) to 0
update unique_request_txn set confirmed_count=0,donated_count=0,reject_count=0,reserve_count=0