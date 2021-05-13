
/* 
--create and set up user
set search_path to bankofplummer;
show search_path
create user appuser with password '4ppp4ssDontGoChasingWaterfalls';
grant all privileges on all tables in schema bankofplummer to appuser;
grant usage on schema bankofplummer to appuser;
grant create on schema bankofplummer to appuser;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA bankofplummer TO appuser;
GRANT all privileges ON ALL TABLES IN SCHEMA bankofplummer TO appuser;
*/



--RUN AS ROOT USER
set search_path to bankofplummer;


--load UUID module
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

drop table if exists account_access;
drop table if exists transaction_history;
drop table if exists accounts;
drop table if exists users;
drop sequence if exists account_num_seq;

--select * from users;
--drop table if exists users;
create table users (
user_ID UUID not null,
firstname varchar(40) not null,
lastname varchar(40) not null,
streetaddress varchar(40),
zipcode char(5),
email varchar(40),
username varchar(20) unique not null,
password varchar(40) not null,
active bool not null,
primary key (user_ID)
);

CREATE SEQUENCE account_num_seq
  INCREMENT 1
  MINVALUE 1100001
  MAXVALUE 9999999
  START 1100001
  CACHE 1;

--select * from accounts;
--drop table if exists accounts;
create table accounts (
account_ID UUID not null,
account_num int unique not null default nextval('account_num_seq'),
account_type varchar(20) not null,
account_desc varchar(50) not null,
balance decimal(30,2) not null,
active bool not null,
primary key (account_ID)
);

--select * from transaction_history;
--drop table if exists transaction_history;
create table transaction_history (
transaction_ID UUID not null,
account_ID UUID not null references accounts on delete restrict,
user_ID UUID not null references users on delete restrict,
transaction_type varchar(20) not null,
amount numeric not null,
transaction_date timestamp not null,
primary key (transaction_ID),
foreign key (account_ID) references accounts (account_ID),
foreign key (user_ID) references users (user_ID)
);

--select * from account_access
--drop table if exists account_access;
create table account_access (
account_access_ID UUID not null,
user_ID UUID not null references users on delete restrict,
account_ID UUID not null references accounts on delete restrict,
primary key (account_access_ID),
foreign key (account_ID) references accounts (account_ID),
foreign key (user_ID) references users (user_ID)
);

--select * from pg_catalog.pg_constraint

drop function if exists populatedata();
CREATE FUNCTION populatedata() RETURNS integer as $$
--<< outerblock >>
declare
	kyle_user_ID UUID := uuid_generate_v1mc();
	kyle_account_access_ID UUID := uuid_generate_v1mc();
	kyle_account_ID UUID := uuid_generate_v1mc();
	tom_user_ID UUID := uuid_generate_v1mc();
	tom_account_access_ID UUID := uuid_generate_v1mc();
	tom_account_ID UUID := uuid_generate_v1mc();
	shared_account_access_ID UUID := uuid_generate_v1mc();
	
  
begin
	raise notice 'kyle_user_ID is %', kyle_user_ID;
	raise notice 'kyle_account_access_ID is %', kyle_account_access_ID;
	raise notice 'kyle_account_ID is %', kyle_account_ID;
	raise notice 'tom_user_ID is %', tom_user_ID;
	raise notice 'tom_account_access_ID is %', tom_account_access_ID;
	raise notice 'tom_account_ID is %', tom_account_ID;
	raise notice 'shared_account_access_ID is %', shared_account_access_ID;

	--select * from users
	insert into users values (kyle_user_ID,  'Kyle', 'Plummer', '925 Ford rd.', '12121', 'kyle.plummer@revature.net', 'kplummer', 'password', true);
	insert into users values (tom_user_ID, 'Tom', 'Smith', '1 Way St.', '12180', 'tom.smith@gmail.com', 'tsmith', 'password', true);
	
	--select * from accounts
	insert into accounts (account_id, account_type, account_desc, balance, active) values (kyle_account_ID, 'checking', 'Kyle''s Checking Account', 0.00, true);
	insert into accounts (account_id, account_type, account_desc, balance, active) values (tom_account_ID, 'savings', 'Tom''s Savings Account', 1250.99, true);

	--select * from account_access 
	insert into account_access values (kyle_account_access_ID, kyle_user_ID, kyle_account_ID);
	insert into account_access values (tom_account_access_ID, tom_user_ID, tom_account_ID);
	insert into account_access values (shared_account_access_ID, tom_user_ID, kyle_account_ID);

	--select * from transaction_history
	insert into transaction_history values (uuid_generate_v1mc(), tom_account_ID, tom_user_ID, 'deposit', 1000.00, NOW());
	insert into transaction_history values (uuid_generate_v1mc(), tom_account_ID, tom_user_ID, 'deposit', 200.00, NOW());
	insert into transaction_history values (uuid_generate_v1mc(), tom_account_ID, tom_user_ID, 'deposit', 50.00, NOW());
	insert into transaction_history values (uuid_generate_v1mc(), tom_account_ID, tom_user_ID, 'deposit', 0.99, NOW());

	--test values
	insert into users values ('4aed96ae-b328-11eb-b538-fba1f9307d97', 'testfirstname', 'testlastname', 'testaddress', '00000', 'test@email.com', 'testuser', 'testpass', true);
	insert into accounts values ('4aed98e8-b328-11eb-b53a-83cc36f981e7', 1000001, 'test', 'test account', 0, true);
	insert into account_access values ('4aedaa68-b328-11eb-b53b-378bdb7dc0d7', '4aed96ae-b328-11eb-b538-fba1f9307d97', '4aed98e8-b328-11eb-b53a-83cc36f981e7');
	insert into transaction_history values ('4af080d0-b328-11eb-b53e-fb637c84cdc0', '4aed98e8-b328-11eb-b53a-83cc36f981e7', '4aed96ae-b328-11eb-b538-fba1f9307d97', 'test', 0, '2000-01-01 12:12:12');
	insert into transaction_history values ('5af080d0-b328-11eb-b53e-fb637c84cdc1', '4aed98e8-b328-11eb-b53a-83cc36f981e7', '4aed96ae-b328-11eb-b538-fba1f9307d97', 'test', 0, '2000-01-01 12:12:12');
	
	RETURN 1;
END;
$$ LANGUAGE plpgsql;

select populatedata();




drop function if exists authenticate(usrid uuid, pass varchar(40));
CREATE FUNCTION authenticate(usrid uuid, pass varchar(40)) RETURNS boolean as $$
--<< outerblock >>
declare
	auth boolean := false;
	truepass varchar(40) := (select password from users where user_id = usrid);
  
begin
	--select password into truepass from user where username = usr;
	if pass = truepass then auth := true;
	end if;
	
	RETURN auth;
END;
$$ LANGUAGE plpgsql;


--permissions
grant all privileges on all tables in schema bankofplummer to appuser;
grant usage on schema bankofplummer to appuser;
grant create on schema bankofplummer to appuser;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA bankofplummer TO appuser;
GRANT all privileges ON ALL TABLES IN SCHEMA bankofplummer TO appuser;
GRANT all privileges ON ALL SEQUENCES IN SCHEMA bankofplummer TO appuser;


