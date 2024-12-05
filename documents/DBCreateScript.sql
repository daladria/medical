CREATE TABLE application_parameters (
	"parameter_name" TEXT NOT NULL,
	"parameter_value" TEXT,
	"date_time" timestamp without time zone,
	CONSTRAINT applicationparameters_pk PRIMARY KEY ("parameter_name")
);

CREATE TABLE public.logs
(
    user_name text,
    command text ,
    params text ,
    datetime timestamp without time zone
);

CREATE TABLE public.messages
(
    channel text  NOT NULL,
    name text NOT NULL,
    lang text NOT NULL,
    value text,
    datetime timestamp without time zone,
    CONSTRAINT messages_pk PRIMARY KEY (channel, name, lang)
);


CREATE TABLE documents
(
    name text,
    lang text,
    value text,
    title text,
    view_order int,
    date_time timestamp without time zone,
    CONSTRAINT messages_pk PRIMARY KEY (name, lang)
);	

CREATE TABLE "otp_codes" (
	"id" TEXT NOT NULL,
	"otp" TEXT,
	retry_count int default 0,
	"date_time" timestamp without time zone,
	CONSTRAINT otpcodes_pk PRIMARY KEY ("id")
);	

CREATE TABLE users
(
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    email text,
    mobile text,
    full_name text default '',
    password text default '',
    lang text default 'tr',
    is_agreement_confirm boolean default false,
    is_contact_confirm boolean default false,
    model text default '',
    brand text default '',
    ip_number text default '',
    device_token text default '', 			
    token uuid ,
    token_expire_date timestamp without time zone,
    refresh_token uuid,
    refresh_token_expire_date timestamp without time zone,
    is_deleted boolean NOT NULL DEFAULT false,
    create_date timestamp without time zone NOT NULL DEFAULT now(),
    last_login_date timestamp without time zone
);

CREATE TABLE banners
(   id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),	
    name text,
    lang text,
    title text,
    short_comment text,
    comment text,
    start_date timestamp without time zone DEFAULT now(),
    end_date timestamp without time zone DEFAULT now(),
    view_order int,
    image text,
    link text,
    is_deleted boolean DEFAULT false
);

CREATE TABLE notifications
(
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id uuid,
    lang text default 'tr',
    title text  default '',
    description text  default '',
    icon text  default '',
    is_clicked boolean  default false,
    is_read boolean  default false,
    link text  default 'tr',
    create_date timestamp without time zone DEFAULT now()
);

CREATE TABLE announcements
(
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    lang text default 'tr',
    title text  default '',
    description text  default '',
    icon text  default '',
    link text  default '',
    is_deleted boolean default false,
    create_date timestamp without time zone DEFAULT now()
);

insert into announcements (lang,title,description,icon) values ('tr','tr1 title','tr 1 description', 'an1.png');
insert into announcements (lang,title,description,icon) values ('tr','tr2 title','tr 2 description', 'an2.gif');
insert into announcements (lang,title,description,icon) values ('en','en1 title','en 1 description', 'an1.png');
insert into announcements (lang,title,description,icon) values ('en','en2 title','en 2 description', 'an2.gif');



CREATE TABLE campaigns
(
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    name text,
    lang text,
    title text,
    short_comment text,
    comment text,
    start_date timestamp without time zone DEFAULT now(),
    end_date timestamp without time zone DEFAULT now(),
    view_order int,
    image text,
    link text,
    is_deleted boolean DEFAULT false
);


CREATE TABLE places
(
   id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
   user_id uuid, 	   
   title text default '',	
   address text default '',
   address_direction text default '',
   building_no text default '',
   floor text default '',
   apartment_no text default '',
   country text default '',
   city text default '',
   district text default '',
   town text default '',
   street text default '',
   postal_code text default '',
   latitude text default '',
   longitude text default '',	
   create_date timestamp without time zone DEFAULT now()
);

CREATE TABLE categories
(
    parent_id uuid,
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    title_tr text,
    title_en text,
    icon_active text,
    icon_passive text,
    view_order integer,
    is_enabled boolean DEFAULT true,
    CONSTRAINT categories_pkey PRIMARY KEY (id)
);

CREATE TABLE brands
(
   id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
   title text Not NULL,
   phone text default '',
   icon text,
   is_enabled boolean DEFAULT true,
   update_date timestamp without time zone DEFAULT now()
);

insert into brands (title,phone,icon) values ('Vestel','111','icon');
insert into brands (title,phone,icon) values ('Beko','111','icon');
insert into brands (title,phone,icon) values ('Tesco','111','icon');
insert into brands (title,phone,icon) values ('Bosch','111','icon');
insert into brands (title,phone,icon) values ('Arçelik','111','icon');


CREATE TABLE models
(  category_id uuid,
   brand_id uuid,
   id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
   title text Not NULL,
   user_guide text,
   is_enabled boolean DEFAULT true,
   update_date timestamp without time zone DEFAULT now()
);

insert into models (brand_id,title) values ('8921ac16-3ad2-4712-bade-14108c5c1e99','3');
insert into models (brand_id,title) values ('8921ac16-3ad2-4712-bade-14108c5c1e99','1');
insert into models (brand_id,title) values ('8921ac16-3ad2-4712-bade-14108c5c1e99','2');

insert into models (brand_id,title) values ('b7ca9cba-2f24-42ff-9063-eabf2a62e94d','y');
insert into models (brand_id,title) values ('b7ca9cba-2f24-42ff-9063-eabf2a62e94d','z');
insert into models (brand_id,title) values ('b7ca9cba-2f24-42ff-9063-eabf2a62e94d','x');

insert into models (brand_id,title) values ('0efc665c-d500-45dd-b9f2-73a472d2e296','a');
insert into models (brand_id,title) values ('0efc665c-d500-45dd-b9f2-73a472d2e296','c');
insert into models (brand_id,title) values ('0efc665c-d500-45dd-b9f2-73a472d2e296','b');

insert into models (brand_id,title) values ('79936399-be4a-4c3f-a98b-e60f7f685498','26');
insert into models (brand_id,title) values ('79936399-be4a-4c3f-a98b-e60f7f685498','54');
insert into models (brand_id,title) values ('79936399-be4a-4c3f-a98b-e60f7f685498','12');

insert into models (brand_id,title) values ('fe529241-1603-43da-8fab-c647c2229455','adana');
insert into models (brand_id,title) values ('fe529241-1603-43da-8fab-c647c2229455','yozgat');
insert into models (brand_id,title) values ('fe529241-1603-43da-8fab-c647c2229455','samsun');



create table user_devices 
(  
   id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
   user_id uuid,
   brand_id uuid,
   category_id uuid,
   model_id uuid,
   place_id uuid,
   model_image text default '',
   guarantee_finish_date text,
   room_name text,
   notes text,
   maintenance_start_date timestamp without time zone DEFAULT null,
   maintenance_interval integer,
   guarantee_docs text default '',
   invoice_docs text default '',
   service_docs text default '',
   device_docs text default '',	
   update_date timestamp without time zone DEFAULT now()
);

create table service_fault_types 
(  
   id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
   parent_id uuid default null,
   title_en text default '',
   desc_en text default '',
   type_en text default '',	
   title_tr text default '',   
   desc_tr text default '',
   type_tr text default '',	
   is_endpoint boolean default true,   
   view_order integer default 0,
   icon text default '',	 	  
   categories text default '',	 	  
   is_enabled boolean default true,  
   update_date timestamp without time zone DEFAULT now()
);


(  
   id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
   user_id uuid,	
   device_id uuid default null,
   fault_type_id uuid, 
   is_open boolean default true,  
   request_date timestamp without time zone DEFAULT now()
);