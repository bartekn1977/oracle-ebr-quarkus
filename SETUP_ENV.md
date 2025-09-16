# Setup POC database

## Docker/Podmab free dataabase

### DOCKER
https://dev.to/francotel/easily-run-oracle-database-in-docker-898
```
docker login container-registry.oracle.com
docker pull container-registry.oracle.com/database/express:latest
docker pull container-registry.oracle.com/database/free:latest

```
alternative
```
docker run -d -p 1521:1521 -e ORACLE_PASSWORD=<your password> gvenzl/oracle-free
docker run -d -p 1521:1521 -e ORACLE_PASSWORD=SomePass1234! -v oracle-volume:/opt/oracle/oradata gvenzl/oracle-free
```

### PODMAN
https://container-registry.oracle.com/ords/f?p=113:4:13046393937477:::4:P4_REPOSITORY,AI_REPOSITORY,AI_REPOSITORY_NAME,P4_REPOSITORY_NAME,P4_EULA_ID,P4_BUSINESS_AREA_ID:1863,1863,Oracle%20Database%20Free,Oracle%20Database%20Free,1,0&cs=373YXnRrRRdHJPPjQ9bJ_1BMzDb7XxETcA0FXFRjB_vSjO8pnDnhQhdyuraGUyVLB4PM1_I8kRZotEeqox8lwgw
```
podman run -d --name <oracle-db> container-registry.oracle.com/database/free:latest
```


### remove in necessary PDB
```
alter pluggable database testpdb close IMMEDIATE;
drop pluggable database testpdb including DATAFILES;
```

### add PDB
```
create pluggable database testpdb admin user pdbadmin identified by <password>;
alter pluggable database testpdb open;
alter pluggable database testpdb save state;
```

### add Schema
```
alter session set container = testpdb;
create bigfile tablespace app_tbs;
create user app_schema quota unlimited on app_tbs DEFAULT TABLESPACE app_tbs;
ALTER USER app_schema ENABLE EDITIONS;
grant create session, create table, create sequence, create view, create procedure, create trigger, CREATE MATERIALIZED VIEW, CREATE ANY EDITION, DROP ANY EDITION to app_schema;
```

### add app user, proxy for schema
```
create bigfile tablespace users;
create user app_service identified by <password> quota unlimited on users;
ALTER USER app_schema GRANT CONNECT THROUGH app_service;
```

### create versions and grant them
```
-- V1
CREATE EDITION V1;
GRANT USE ON EDITION V1 TO app_schema;

-- V2 based on V1
CREATE EDITION V2 AS CHILD OF V1;
GRANT USE ON EDITION V2 TO app_schema;
```

### optionally add service/services
```
exec DBMS_SERVICE.CREATE_SERVICE('testpdb_service','testpdb_service');
exec DBMS_SERVICE.START_SERVICE('testpdb_service');

-- V1 only service
BEGIN
    DBMS_SERVICE.CREATE_SERVICE(
    service_name => 'testpdb_service_v1',
    network_name => 'testpdb_service_v1',
    edition      => 'V1');
End;
/
exec DBMS_SERVICE.START_SERVICE('testpdb_service_v1');

-- V2 only service
BEGIN
    DBMS_SERVICE.CREATE_SERVICE(
    service_name => 'testpdb_service_v2',
    network_name => 'testpdb_service_v2',
    edition      => 'V2');
End;
/
exec DBMS_SERVICE.START_SERVICE('testpdb_service_v2');


SELECT name, edition FROM dba_services;
exec DBMS_SERVICE.STOP_SERVICE('testpdb_service_v2');
exec DBMS_SERVICE.DELETE_SERVICE('testpdb_service_v2');

```

### test connections
```
CONN app_service[app_schema]/<password>@//localhost:1521/testpdb_service
```