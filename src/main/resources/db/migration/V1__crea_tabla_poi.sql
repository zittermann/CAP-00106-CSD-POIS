CREATE TABLE POI (
   id BIGINT AUTO_INCREMENT NOT NULL,
   latitud DECIMAL NOT NULL,
   longitud DECIMAL NOT NULL,
   nombre VARCHAR(100) NOT NULL,
   descripcion VARCHAR(255) NULL default NULL,
   PRIMARY KEY (id)
);

CREATE TABLE LOCATION (
                     id BIGINT AUTO_INCREMENT NOT NULL,
                     latitude DECIMAL NOT NULL,
                     longitude DECIMAL NOT NULL,
                     name VARCHAR(100) NOT NULL,
                     label VARCHAR(255) NULL default NULL,
                     PRIMARY KEY (id)
);