drop table if exists ss_enterprise;
drop table if exists ss_subject;
drop table if exists ss_user;
drop table if exists ss_questions;

create table ss_subject (
	id bigint auto_increment,
	name varchar(128) not null,
	description varchar(255),
	enterprise_id bigint not null,
    primary key (id)
) ENGINE=MyISAM;

create table ss_user (
	id bigint auto_increment,
	login_name varchar(64) not null unique,
	name varchar(64) not null,
	password varchar(255) not null,
	email varchar(64) not null,
	salt varchar(64) not null,
	roles varchar(255) not null,
	create_date timestamp not null default 0,
	enterprise_id bigint not null,
	subject varchar(255) not null,
	primary key (id)
) ENGINE=MyISAM;

create table ss_enterprise (
	id bigint auto_increment,
	login_name varchar(64) not null unique,
	password varchar(255) not null,
	email varchar(64) not null,
	salt varchar(64) not null,
	enterprise_name varchar(255) not null unique,
	org_attribute varchar(32) not null,
	address varchar(255) not null,
	scale varchar(64) not null,
	business_cover varchar(32) not null,
	business_type varchar(64) not null,
	contacts varchar(32) not null,
	id_num varchar(32) not null,
	mobile varchar(32) not null,
	roles varchar(255) not null,
	register_date timestamp not null default 0,
	primary key (id)
) ENGINE=MyISAM;

CREATE TABLE IF NOT EXISTS `ss_questions` (
  `id` bigint auto_increment,
  `qtype` int(3) NOT NULL,
  `question` text NOT NULL,
  `options` text NOT NULL,
  `optionsnum` tinyint(11) NOT NULL,
  `answer` text NOT NULL,
  `describe` text NOT NULL,
  `createtime` timestamp not null default 0,
  `level` int(1) NOT NULL,
  enterprise_id bigint not null,
  subject_id bigint not null,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM;

select * from ss_enterprise;

CREATE TABLE IF NOT EXISTS `x2_examhistory` (
  `ehid` int(11) NOT NULL AUTO_INCREMENT,
  `ehexamid` int(11) NOT NULL,
  `ehexam` varchar(240) NOT NULL,
  `ehtype` int(11) NOT NULL,
  `ehbasicid` int(11) NOT NULL,
  `ehquestion` longtext NOT NULL,
  `ehsetting` text NOT NULL,
  `ehscorelist` text NOT NULL,
  `ehuseranswer` text NOT NULL,
  `ehtime` int(11) NOT NULL,
  `ehscore` decimal(10,2) NOT NULL,
  `ehuserid` int(11) NOT NULL,
  `ehusername` varchar(120) NOT NULL,
  `ehstarttime` int(11) NOT NULL,
  `ehstatus` int(1) NOT NULL DEFAULT '1',
  `ehdecide` int(1) NOT NULL,
  PRIMARY KEY (`ehid`),
  KEY `ehtype` (`ehtype`,`ehbasicid`,`ehtime`,`ehuserid`),
  KEY `ehdecide` (`ehdecide`),
  KEY `ehexamid` (`ehexamid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `x2_exams`
--

CREATE TABLE IF NOT EXISTS `x2_exams` (
  `examid` int(11) NOT NULL AUTO_INCREMENT,
  `examsubject` tinyint(4) NOT NULL,
  `exam` varchar(120) NOT NULL,
  `examsetting` text NOT NULL,
  `examquestions` text NOT NULL,
  `examscore` text NOT NULL,
  `examstatus` int(1) NOT NULL DEFAULT '0',
  `examtype` int(11) NOT NULL,
  `examauthorid` int(11) NOT NULL,
  `examauthor` varchar(120) CHARACTER SET utf8 COLLATE utf8_estonian_ci NOT NULL,
  `examtime` int(11) NOT NULL,
  `examkeyword` varchar(240) NOT NULL,
  `examdecide` int(1) NOT NULL,
  PRIMARY KEY (`examid`),
  KEY `examstatus` (`examstatus`),
  KEY `examtype` (`examtype`,`examauthorid`),
  KEY `examtime` (`examtime`),
  KEY `examsubject` (`examsubject`),
  KEY `examdecide` (`examdecide`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- 转存表中的数据 `x2_exams`
--

INSERT INTO `x2_exams` (`examid`, `examsubject`, `exam`, `examsetting`, `examquestions`, `examscore`, `examstatus`, `examtype`, `examauthorid`, `examauthor`, `examtime`, `examkeyword`, `examdecide`) VALUES
(1, 1, '近代史课测试', 'a:5:{s:8:"examtime";i:90;s:7:"comfrom";s:0:"";s:5:"score";i:100;s:9:"passscore";i:60;s:8:"questype";a:5:{i:1;a:6:{s:6:"number";i:20;s:5:"score";i:1;s:8:"describe";s:0:"";s:10:"easynumber";i:30;s:12:"middlenumber";s:1:"0";s:10:"hardnumber";s:1:"0";}i:2;a:6:{s:6:"number";i:15;s:5:"score";i:2;s:8:"describe";s:0:"";s:10:"easynumber";i:15;s:12:"middlenumber";s:1:"0";s:10:"hardnumber";s:1:"0";}i:3;a:6:{s:6:"number";i:20;s:5:"score";i:2;s:8:"describe";s:0:"";s:10:"easynumber";i:20;s:12:"middlenumber";s:1:"0";s:10:"hardnumber";s:1:"0";}i:4;a:6:{s:6:"number";i:1;s:5:"score";i:10;s:8:"describe";s:1:"0";s:10:"easynumber";s:1:"0";s:12:"middlenumber";s:1:"0";s:10:"hardnumber";i:1;}i:5;a:6:{s:6:"number";s:1:"0";s:5:"score";s:1:"0";s:8:"describe";s:1:"0";s:10:"easynumber";s:1:"0";s:12:"middlenumber";s:1:"0";s:10:"hardnumber";s:1:"0";}}}', 'N;', '', 0, 1, 2, 'redrangon', 1372249127, '', 0),
(2, 1, '手动组卷示例', 'a:5:{s:8:"examtime";i:90;s:7:"comfrom";s:0:"";s:5:"score";i:100;s:9:"passscore";i:60;s:8:"questype";a:5:{i:1;a:3:{s:6:"number";i:20;s:5:"score";i:2;s:8:"describe";s:0:"";}i:2;a:3:{s:6:"number";i:10;s:5:"score";i:2;s:8:"describe";s:0:"";}i:3;a:3:{s:6:"number";i:15;s:5:"score";i:2;s:8:"describe";s:0:"";}i:4;a:3:{s:6:"number";i:1;s:5:"score";i:10;s:8:"describe";s:0:"";}i:5;a:3:{s:6:"number";s:1:"0";s:5:"score";s:1:"0";s:8:"describe";s:1:"0";}}}', 'a:5:{i:1;a:2:{s:9:"questions";s:77:",228,227,226,225,224,220,221,222,223,216,215,212,211,210,190,191,192,193,194,";s:13:"rowsquestions";s:3:",1,";}i:2;a:2:{s:9:"questions";s:41:",405,404,403,399,396,401,359,361,357,378,";s:13:"rowsquestions";s:0:"";}i:3;a:2:{s:9:"questions";s:61:",715,714,713,712,711,696,697,699,700,702,690,689,688,692,686,";s:13:"rowsquestions";s:0:"";}i:4;a:2:{s:9:"questions";s:5:",771,";s:13:"rowsquestions";s:0:"";}i:5;a:2:{s:9:"questions";s:0:"";s:13:"rowsquestions";s:0:"";}}', '', 0, 2, 2, 'redrangon', 1372249681, '', 0);

-- --------------------------------------------------------

--
-- 表的结构 `x2_examsession`
--

CREATE TABLE IF NOT EXISTS `x2_examsession` (
  `examsessionid` varchar(32) NOT NULL,
  `examsession` varchar(240) NOT NULL,
  `examsessionsetting` text NOT NULL,
  `examsessionsign` text NOT NULL,
  `examsessionbasic` tinyint(4) NOT NULL,
  `examsessiontype` int(1) NOT NULL,
  `examsessionkey` varchar(32) NOT NULL,
  `examsessionquestion` longtext NOT NULL,
  `examsessionuseranswer` text NOT NULL,
  `examsessionstarttime` int(11) NOT NULL,
  `examsessiontime` int(11) NOT NULL,
  `examsessionstatus` int(1) NOT NULL,
  `examsessionscore` decimal(10,1) NOT NULL,
  `examsessionscorelist` text NOT NULL,
  `examsessionissave` int(1) NOT NULL,
  PRIMARY KEY (`examsessionid`),
  KEY `examsessionstarttime` (`examsessionstarttime`),
  KEY `examsessionstatus` (`examsessionstatus`),
  KEY `examsessiontype` (`examsessiontype`),
  KEY `examsessionkey` (`examsessionkey`),
  KEY `examsubject` (`examsessionbasic`),
  KEY `examsessionissave` (`examsessionissave`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `x2_favor`
--

CREATE TABLE IF NOT EXISTS `x2_favor` (
  `favorid` int(11) NOT NULL AUTO_INCREMENT,
  `favoruserid` int(11) NOT NULL,
  `favorsubjectid` int(11) NOT NULL,
  `favorquestionid` int(11) NOT NULL,
  `favortime` int(11) NOT NULL,
  PRIMARY KEY (`favorid`),
  KEY `favoruserid` (`favoruserid`,`favorquestionid`,`favortime`),
  KEY `favorsubjectid` (`favorsubjectid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

-
-- --------------------------------------------------------

--
-- 表的结构 `x2_examhistory`
--

CREATE TABLE IF NOT EXISTS `x2_examhistory` (
  `ehid` int(11) NOT NULL AUTO_INCREMENT,
  `ehexamid` int(11) NOT NULL,
  `ehexam` varchar(240) NOT NULL,
  `ehtype` int(11) NOT NULL,
  `ehbasicid` int(11) NOT NULL,
  `ehquestion` longtext NOT NULL,
  `ehsetting` text NOT NULL,
  `ehscorelist` text NOT NULL,
  `ehuseranswer` text NOT NULL,
  `ehtime` int(11) NOT NULL,
  `ehscore` decimal(10,2) NOT NULL,
  `ehuserid` int(11) NOT NULL,
  `ehusername` varchar(120) NOT NULL,
  `ehstarttime` int(11) NOT NULL,
  `ehstatus` int(1) NOT NULL DEFAULT '1',
  `ehdecide` int(1) NOT NULL,
  PRIMARY KEY (`ehid`),
  KEY `ehtype` (`ehtype`,`ehbasicid`,`ehtime`,`ehuserid`),
  KEY `ehdecide` (`ehdecide`),
  KEY `ehexamid` (`ehexamid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `x2_exams`
--

CREATE TABLE IF NOT EXISTS `x2_exams` (
  `examid` int(11) NOT NULL AUTO_INCREMENT,
  `examsubject` tinyint(4) NOT NULL,
  `exam` varchar(120) NOT NULL,
  `examsetting` text NOT NULL,
  `examquestions` text NOT NULL,
  `examscore` text NOT NULL,
  `examstatus` int(1) NOT NULL DEFAULT '0',
  `examtype` int(11) NOT NULL,
  `examauthorid` int(11) NOT NULL,
  `examauthor` varchar(120) CHARACTER SET utf8 COLLATE utf8_estonian_ci NOT NULL,
  `examtime` int(11) NOT NULL,
  `examkeyword` varchar(240) NOT NULL,
  `examdecide` int(1) NOT NULL,
  PRIMARY KEY (`examid`),
  KEY `examstatus` (`examstatus`),
  KEY `examtype` (`examtype`,`examauthorid`),
  KEY `examtime` (`examtime`),
  KEY `examsubject` (`examsubject`),
  KEY `examdecide` (`examdecide`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- 转存表中的数据 `x2_exams`
--

INSERT INTO `x2_exams` (`examid`, `examsubject`, `exam`, `examsetting`, `examquestions`, `examscore`, `examstatus`, `examtype`, `examauthorid`, `examauthor`, `examtime`, `examkeyword`, `examdecide`) VALUES
(1, 1, '近代史课测试', 'a:5:{s:8:"examtime";i:90;s:7:"comfrom";s:0:"";s:5:"score";i:100;s:9:"passscore";i:60;s:8:"questype";a:5:{i:1;a:6:{s:6:"number";i:20;s:5:"score";i:1;s:8:"describe";s:0:"";s:10:"easynumber";i:30;s:12:"middlenumber";s:1:"0";s:10:"hardnumber";s:1:"0";}i:2;a:6:{s:6:"number";i:15;s:5:"score";i:2;s:8:"describe";s:0:"";s:10:"easynumber";i:15;s:12:"middlenumber";s:1:"0";s:10:"hardnumber";s:1:"0";}i:3;a:6:{s:6:"number";i:20;s:5:"score";i:2;s:8:"describe";s:0:"";s:10:"easynumber";i:20;s:12:"middlenumber";s:1:"0";s:10:"hardnumber";s:1:"0";}i:4;a:6:{s:6:"number";i:1;s:5:"score";i:10;s:8:"describe";s:1:"0";s:10:"easynumber";s:1:"0";s:12:"middlenumber";s:1:"0";s:10:"hardnumber";i:1;}i:5;a:6:{s:6:"number";s:1:"0";s:5:"score";s:1:"0";s:8:"describe";s:1:"0";s:10:"easynumber";s:1:"0";s:12:"middlenumber";s:1:"0";s:10:"hardnumber";s:1:"0";}}}', 'N;', '', 0, 1, 2, 'redrangon', 1372249127, '', 0),
(2, 1, '手动组卷示例', 'a:5:{s:8:"examtime";i:90;s:7:"comfrom";s:0:"";s:5:"score";i:100;s:9:"passscore";i:60;s:8:"questype";a:5:{i:1;a:3:{s:6:"number";i:20;s:5:"score";i:2;s:8:"describe";s:0:"";}i:2;a:3:{s:6:"number";i:10;s:5:"score";i:2;s:8:"describe";s:0:"";}i:3;a:3:{s:6:"number";i:15;s:5:"score";i:2;s:8:"describe";s:0:"";}i:4;a:3:{s:6:"number";i:1;s:5:"score";i:10;s:8:"describe";s:0:"";}i:5;a:3:{s:6:"number";s:1:"0";s:5:"score";s:1:"0";s:8:"describe";s:1:"0";}}}', 'a:5:{i:1;a:2:{s:9:"questions";s:77:",228,227,226,225,224,220,221,222,223,216,215,212,211,210,190,191,192,193,194,";s:13:"rowsquestions";s:3:",1,";}i:2;a:2:{s:9:"questions";s:41:",405,404,403,399,396,401,359,361,357,378,";s:13:"rowsquestions";s:0:"";}i:3;a:2:{s:9:"questions";s:61:",715,714,713,712,711,696,697,699,700,702,690,689,688,692,686,";s:13:"rowsquestions";s:0:"";}i:4;a:2:{s:9:"questions";s:5:",771,";s:13:"rowsquestions";s:0:"";}i:5;a:2:{s:9:"questions";s:0:"";s:13:"rowsquestions";s:0:"";}}', '', 0, 2, 2, 'redrangon', 1372249681, '', 0);

-- --------------------------------------------------------

--
-- 表的结构 `x2_examsession`
--

CREATE TABLE IF NOT EXISTS `x2_examsession` (
  `examsessionid` varchar(32) NOT NULL,
  `examsession` varchar(240) NOT NULL,
  `examsessionsetting` text NOT NULL,
  `examsessionsign` text NOT NULL,
  `examsessionbasic` tinyint(4) NOT NULL,
  `examsessiontype` int(1) NOT NULL,
  `examsessionkey` varchar(32) NOT NULL,
  `examsessionquestion` longtext NOT NULL,
  `examsessionuseranswer` text NOT NULL,
  `examsessionstarttime` int(11) NOT NULL,
  `examsessiontime` int(11) NOT NULL,
  `examsessionstatus` int(1) NOT NULL,
  `examsessionscore` decimal(10,1) NOT NULL,
  `examsessionscorelist` text NOT NULL,
  `examsessionissave` int(1) NOT NULL,
  PRIMARY KEY (`examsessionid`),
  KEY `examsessionstarttime` (`examsessionstarttime`),
  KEY `examsessionstatus` (`examsessionstatus`),
  KEY `examsessiontype` (`examsessiontype`),
  KEY `examsessionkey` (`examsessionkey`),
  KEY `examsubject` (`examsessionbasic`),
  KEY `examsessionissave` (`examsessionissave`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `x2_favor`
--

CREATE TABLE IF NOT EXISTS `x2_favor` (
  `favorid` int(11) NOT NULL AUTO_INCREMENT,
  `favoruserid` int(11) NOT NULL,
  `favorsubjectid` int(11) NOT NULL,
  `favorquestionid` int(11) NOT NULL,
  `favortime` int(11) NOT NULL,
  PRIMARY KEY (`favorid`),
  KEY `favoruserid` (`favoruserid`,`favorquestionid`,`favortime`),
  KEY `favorsubjectid` (`favorsubjectid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

-- --------------------------------------------------------

--
-- 表的结构 `x2_questions`
--

CREATE TABLE IF NOT EXISTS `x2_questions` (
  `questionid` int(11) NOT NULL AUTO_INCREMENT,
  `questiontype` int(3) NOT NULL,
  `question` text NOT NULL,
  `questionuserid` int(11) NOT NULL,
  `questionusername` varchar(120) NOT NULL,
  `questionlastmodifyuser` varchar(120) NOT NULL,
  `questionselect` text NOT NULL,
  `questionselectnumber` tinyint(11) NOT NULL,
  `questionanswer` text NOT NULL,
  `questiondescribe` text NOT NULL,
  `questionknowsid` text NOT NULL,
  `questioncreatetime` int(11) NOT NULL,
  `questionstatus` int(1) NOT NULL DEFAULT '1',
  `questionhtml` text NOT NULL,
  `questionparent` int(11) NOT NULL,
  `questionsequence` int(3) NOT NULL,
  `questionlevel` int(1) NOT NULL,
  PRIMARY KEY (`questionid`),
  KEY `questioncreatetime` (`questioncreatetime`),
  KEY `questiontype` (`questiontype`),
  KEY `questionstatus` (`questionstatus`),
  KEY `questionuserid` (`questionuserid`),
  KEY `questionparent` (`questionparent`,`questionsequence`),
  KEY `questionlevel` (`questionlevel`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8445 ;