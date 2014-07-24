delete from ss_user;
insert into ss_user (login_name, name, password, salt, roles, create_date, email, enterprise_id,subject) 
	values('admin','Admin','691b14d79bf0fa2215f155235df5e670b64394cc','7efbd59d9741d34f','admin','2012-06-04 01:00:00','jiayingzhe@163.com','1','');
insert into ss_user (login_name, name, password, salt, roles, create_date, email, enterprise_id,subject) 
	values('user','Calvin','2488aa0c31c624687bd9928e0a5d29e7d1ed520b','6d65d24122c30500','user','2012-06-04 02:00:00','xx@x.com','1','');

insert into `ss_enterprise` (`login_name`, `password`, `email`, `salt`, `enterprise_name`, `org_attribute`, `address`, `scale`, `business_cover`, `business_type`, `contacts`, `id_num`, `mobile`, `roles`, `register_date`) values('yzjia','e46ae30ade680b52338a1b54ed4b85ae28e093da','yzjia3201@gmail.com','05dc4c428afe64a1','CaseWOW','政府机构','北京朝阳区酒仙桥中路24号院4号楼','50','global','行业协会','yzjia','1234567890','15011248258','enterprice,user','2014-07-22 18:05:40');


select * from ss_user;

select * from ss_enterprise;