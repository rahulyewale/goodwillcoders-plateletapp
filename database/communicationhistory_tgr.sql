-- Trigger: communicationhistory_tgr on communication_history

-- DROP TRIGGER communicationhistory_tgr ON communication_history;

CREATE TRIGGER communicationhistory_tgr
  AFTER INSERT OR UPDATE
  ON communication_history
  FOR EACH ROW
  EXECUTE PROCEDURE Update_uniquerequesttxn_fun();
