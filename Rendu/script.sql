drop table if exists GROUPS;
drop table if exists LISTUSRGROUP;
drop table if exists MESSAGES;
drop table if exists USERGROUPS;
drop table if exists USERS;
drop table if exists USERTOGROUP;
drop table if exists USERTOUSER;

create table users (
   numu         SERIAL,
   login        TEXT,
   mdp          TEXT,
   nom          TEXT,
   email        TEXT,
   description  TEXT,
   photo        TEXT,
   constraint pk_users primary key (numu)
);

create table messages (
   idmsg      SERIAL,
   dateenv    timestamp DEFAULT statement_timestamp(),
   contenu    TEXT,
   constraint pk_messages primary key (idmsg)
);

create table groups (
   idgrp    SERIAL,
   libelle  TEXT,
   constraint pk_groups primary key (idgrp)
);

create table listusrgroup (
   uparent INT  ,
   ufils   INT  ,
   idgrp   INT  ,
   constraint pk_listusrgroup primary key (uparent, idgrp, ufils)
);

create table usergroups (
    numu  INT  ,
    idgrp  INT  ,
    constraint pk_usergroups primary key (idgrp, numu)
);

create table usertogroup (
   uexp  INT  ,
   idgrpdest  INT  ,
   idmsg  INT  ,
   constraint pk_usertogroup primary key (idgrpdest, idmsg, uexp)
);

create table usertouser (
   uexp  INT  ,
   idmsg  INT  ,
   udest  INT  ,
   constraint pk_usertouser primary key (uexp, idmsg, udest)
);


alter table listusrgroup
   add constraint fk_ls_usr_parent foreign key (uparent) references users (numu) on delete restrict on update cascade,
   add constraint fk_ls_usr_fils foreign key (ufils) references users (numu) on delete restrict on update cascade,
   add constraint fk_ls_groups foreign key (idgrp) references groups (idgrp) on delete restrict on update cascade;

alter table usergroups
   add constraint fk_usrgrp_grp foreign key (idgrp) references groups (idgrp) on delete restrict on update cascade,
   add constraint fk_usrgrp_usr foreign key (numu) references users (numu) on delete restrict on update cascade;

alter table usertogroup
   add constraint fk_usrtogrp_grp foreign key (idgrpdest) references groups (idgrp) on delete restrict on update cascade,
   add constraint FK_USRTOGRP_MSG foreign key (idmsg) references messages (idmsg) on delete restrict on update cascade,
   add constraint FK_USRTOGRP_USR foreign key (uexp) references users (numu) on delete restrict on update cascade;

alter table usertouser
   add constraint fk_usrtousr_exp     foreign key (uexp)  references users (NUMU) on delete restrict on update cascade,
   add constraint fk_usrtousr_msg  foreign key (idmsg) references messages (idmsg) on delete restrict on update cascade,
   add constraint fk_usrtousr_dest     foreign key (udest) references users (numu) on delete restrict on update cascade;


INSERT INTO groups VALUES (1, 'Autre');
INSERT INTO groups VALUES (2, 'Amis');
INSERT INTO groups VALUES (3, 'Famille');
INSERT INTO groups VALUES (4, 'Pro');



INSERT INTO users VALUES (2, 'alexandre', '202cb962ac59075b964b07152d234b70', 'Alexandre VASTRA', 'test2@user.com', 'Etudiant DA2I', 'default.png');
INSERT INTO users VALUES (3, 'philippe', '202cb962ac59075b964b07152d234b70', 'Philippe MATHIEU', 'test3@user.com', 'Responsable Formation DA2I', 'pm.png');
INSERT INTO users VALUES (1, 'zakaria', '202cb962ac59075b964b07152d234b70', 'Zakaria ZEMMIRI', 'test@user.com', 'Etudiant DA2I', 'kot.png');


INSERT INTO listusrgroup VALUES (1, 2, 2);
INSERT INTO listusrgroup VALUES (1, 3, 4);



INSERT INTO messages VALUES (1, '2016-02-16 21:57:04.727154', 'Salut !');
INSERT INTO messages VALUES (2, '2016-02-16 21:58:32.916189', 'Bonjour !');
INSERT INTO messages VALUES (3, '2016-02-16 22:15:09.827469', 'Bonjour depuis Windows phone :) ');
INSERT INTO messages VALUES (4, '2016-02-16 22:27:45.615108', '<button type="button" id="img_t.png" class="btn btn-default" onclick="showImg(this)" ><img src="../img/avatars/t.png"/></button>');
INSERT INTO messages VALUES (5, '2016-02-16 22:52:17.161221', 'hello !!!');


INSERT INTO usergroups VALUES (1, 1);
INSERT INTO usergroups VALUES (1, 2);
INSERT INTO usergroups VALUES (1, 3);
INSERT INTO usergroups VALUES (1, 4);
INSERT INTO usergroups VALUES (2, 1);
INSERT INTO usergroups VALUES (2, 2);
INSERT INTO usergroups VALUES (2, 3);
INSERT INTO usergroups VALUES (2, 4);
INSERT INTO usergroups VALUES (3, 1);
INSERT INTO usergroups VALUES (3, 2);
INSERT INTO usergroups VALUES (3, 3);
INSERT INTO usergroups VALUES (3, 4);



INSERT INTO usertouser VALUES (1, 1, 2);
INSERT INTO usertouser VALUES (1, 2, 3);
INSERT INTO usertouser VALUES (3, 3, 1);
INSERT INTO usertouser VALUES (1, 4, 3);
INSERT INTO usertouser VALUES (2, 5, 1);
