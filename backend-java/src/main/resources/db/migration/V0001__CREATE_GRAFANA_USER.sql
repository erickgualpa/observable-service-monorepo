CREATE USER 'grafanaReader' IDENTIFIED BY 'grafanaPassword';
GRANT SELECT ON wis.* TO 'grafanaReader';