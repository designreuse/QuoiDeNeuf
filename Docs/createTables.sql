drop table if exists GROUPS;
drop table if exists LISTUSRGROUP;
drop table if exists MESSAGES;
drop table if exists USERGROUPS;
drop table if exists USERS;
drop table if exists USERTOGROUP;
drop table if exists USERTOUSER;

create table USERS (
   NUMU         SERIAL,
   LOGIN        TEXT,
   MDP          TEXT,
   NOM          TEXT,
   EMAIL        TEXT,
   DESCRIPTION  TEXT,
   PHOTO        TEXT,
   constraint   PK_USERS primary key (NUMU)
);

create table MESSAGES (
   IDMSG      SERIAL,
   DATEENV    timestamp DEFAULT statement_timestamp(),
   CONTENU    TEXT,
   constraint PK_MESSAGES primary key (IDMSG)
);

create table GROUPS (
   IDGRP    SERIAL,
   LIBELLE  TEXT,
   constraint PK_GROUPS primary key (IDGRP)
);

create table LISTUSRGROUP (
   UPARENT    INT  ,
   UFILS  INT  ,
   IDGRP  INT  ,
   constraint PK_LISTUSRGROUP primary key (UPARENT, IDGRP, UFILS)
);

create table USERGROUPS (
    NUMU  INT  ,
    IDGRP  INT  ,
    constraint PK_USERGROUPS primary key (IDGRP, NUMU)
);

create table USERTOGROUP (
   UEXP  INT  ,
   IDGRPDEST  INT  ,
   IDMSG  INT  ,
   constraint PK_USERTOGROUP primary key (IDGRPDEST, IDMSG, UEXP)
);

create table USERTOUSER (
   UEXP  INT  ,
   IDMSG  INT  ,
   UDEST  INT  ,
   constraint PK_USERTOUSER primary key (UEXP, IDMSG, UDEST)
);


alter table LISTUSRGROUP
   add constraint FK_LS_USR_PARENT foreign key (UPARENT) references USERS (NUMU) on delete restrict on update cascade,
   add constraint FK_LS_USR_FILS foreign key (UFILS) references USERS (NUMU) on delete restrict on update cascade,
   add constraint FK_LS_GROUPS foreign key (IDGRP) references GROUPS (IDGRP) on delete restrict on update cascade;

alter table USERGROUPS
   add constraint FK_USRGRP_GRP foreign key (IDGRP) references GROUPS (IDGRP) on delete restrict on update cascade,
   add constraint FK_USRGRP_USR foreign key (NUMU) references USERS (NUMU) on delete restrict on update cascade;

alter table USERTOGROUP
   add constraint FK_USRTOGRP_GRP foreign key (IDGRPDEST) references GROUPS (IDGRP) on delete restrict on update cascade,
   add constraint FK_USRTOGRP_MSG foreign key (IDMSG) references MESSAGES (IDMSG) on delete restrict on update cascade,
   add constraint FK_USRTOGRP_USR foreign key (UEXP) references USERS (NUMU) on delete restrict on update cascade;

alter table USERTOUSER
   add constraint FK_USRTOUSR_EXP     foreign key (UEXP)  references USERS (NUMU) on delete restrict on update cascade,
   add constraint FK_USRTOUSR_MSG  foreign key (IDMSG) references MESSAGES (IDMSG) on delete restrict on update cascade,
   add constraint FK_USRTOUSR_DEST     foreign key (UDEST) references USERS (NUMU) on delete restrict on update cascade;
