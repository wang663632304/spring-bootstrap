DROP DATABASE IF EXISTS <application-name-lowercase>;

CREATE DATABASE <application-name-lowercase>
  WITH OWNER = <db-username>
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'en_US.UTF-8'
       LC_CTYPE = 'en_US.UTF-8'
       TEMPLATE=template0
       CONNECTION LIMIT = -1;