-- User user/pass
INSERT INTO users (username, password, enabled)
  values ('user','user',1);
  
INSERT INTO users (username, password, enabled)
  values ('admin','admin',1);
  
INSERT INTO users (username, password, enabled)
  values ('operator','operator',1);

INSERT INTO user_roles (username, AUTHORITY)
  values ('user', 'ROLE_USER');
  
INSERT INTO user_roles (username, AUTHORITY)
  values ('admin', 'ROLE_ADMIN');
 
INSERT INTO user_roles (username, AUTHORITY)
  values ('operator', 'ROLE_OPERATOR');
  
INSERT INTO vehicle_info(vehicle_id, vehicle_name, vehicle_type, vehicle_model, vehicle_manufacturer)
	values(1, 'Vehicle1','Two-Wheeler','180cc','TVS Apache');
	
INSERT INTO vehicle_info(vehicle_id, vehicle_name, vehicle_type, vehicle_model, vehicle_manufacturer)
	values(2, 'Vehicle2','Three-Wheeler','800cc','Bajaj Auto');
	
INSERT INTO vehicle_info(vehicle_id, vehicle_name, vehicle_type, vehicle_model, vehicle_manufacturer)
	values(3, 'Vehicle3','Four-Wheeler','1000cc','Maruti Suzuki');