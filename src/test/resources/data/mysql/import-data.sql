delete from ss_user;
insert into ss_user (id, login_name, name, password, salt, roles, create_date, email, enterprise_id,subject) 
	values(1,'admin','Admin','691b14d79bf0fa2215f155235df5e670b64394cc','7efbd59d9741d34f','admin','2012-06-04 01:00:00','jiayingzhe@163.com','1','');
insert into ss_user (id, login_name, name, password, salt, roles, create_date, email, enterprise_id,subject) 
	values(2,'user','Calvin','2488aa0c31c624687bd9928e0a5d29e7d1ed520b','6d65d24122c30500','user','2012-06-04 02:00:00','xx@x.com','1','');

insert into ss_enterprise (id, login_name, password, salt, enterprise_name, roles, )
	
select * from ss_user;

select * from ss_enterprise;