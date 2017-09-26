-- phpMyAdmin SQL Dump
-- version 4.0.10.14
-- http://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Generation Time: Jan 14, 2017 at 03:44 AM
-- Server version: 5.5.52-cll-lve
-- PHP Version: 5.6.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `android_webapp`
--

-- --------------------------------------------------------

--
-- Table structure for table `chat`
--

CREATE TABLE IF NOT EXISTS `chat` (
  `from` varchar(100) NOT NULL,
  `to` varchar(100) NOT NULL,
  `message` varchar(600) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `chat`
--

INSERT INTO `chat` (`from`, `to`, `message`) VALUES
('9740076782', '9740076782', 'bjj'),
('7829447344', '9740076782', 'hi'),
('9740076782', '9740076782', 'znjs'),
('9833862970', '7829447344', 'hi'),
('7829447344', '9833862970', 'hello'),
('7829447344', '9833862970', 'fhrug'),
('9833862970', '7829447344', 'dyufufufiu77i'),
('7829447344', '9833862970', 'uftiududuud'),
('7829447344', '9833862970', 'fufuududu'),
('9833862970', '7829447344', '456fudufufuf'),
('7829447344', '9833862970', 'gryutt'),
('9833862970', '7829447344', '45jfjfjfjf'),
('9833862970', '7829447344', ''),
('9833862970', '7829447344', ''),
('9833862970', '7829447344', ''),
('9833862970', '7829447344', ''),
('9833862970', '7829447344', ''),
('9833862970', '7829447344', ''),
('9833862970', '7829447344', ''),
('9833862970', '7829447344', ''),
('9833862970', '7829447344', ''),
('9833862970', '7829447344', ''),
('9833862970', '7829447344', ''),
('9833862970', '7829447344', ''),
('9833862970', '7829447344', ''),
('9833862970', '7829447344', ''),
('9833862970', '7829447344', ''),
('9833862970', '7829447344', ''),
('9833862970', '7829447344', ''),
('9833862970', '7829447344', ''),
('9833862970', '7829447344', ''),
('7829447344', '9740076782', ''),
('7829447344', '9740076782', ''),
('7829447344', '9740076782', ''),
('7829447344', '9740076782', ''),
('7829447344', '9740076782', ''),
('9740076782', '9740076782', 'sms'),
('9740076782', '7829447344', 'hi ...'),
('7829447344', '9740076782', 'hello'),
('9740076782', '7829447344', 'one to one is working or not'),
('9740076782', '9740076782', 'hdjs'),
('7829447344', '9740076782', 'working but if i tap on notification its not directly entering to chat'),
('9740076782', '7829447344', 'Ok we set to go to chat option'),
('7829447344', '9740076782', 'ya please do that to go that individual chat'),
('9740076782', '7829447344', 'and sir for group invite only if the user already install app then only it added to group'),
('7829447344', '9740076782', 'can we send that group link through sms'),
('9740076782', '7829447344', 'no sir it is embedded in app we cannot send link of that group'),
('7829447344', '9740076782', 'but how individual invite will go'),
('9740076782', '7829447344', 'that invite will send SMS to users'),
('7829447344', '9740076782', 'now its not going'),
('7829447344', '9740076782', 'configure that and ll check tomorrow'),
('9740076782', '7829447344', 'ok\nbecause it will simply use SMS '),
('7829447344', '9740076782', 'orry we cannot bcoz we have other work'),
('9740076782', '7829447344', 'ok'),
('7829447344', '9740076782', 'tushar firse ye wo bol raha tha'),
('9740076782', '7829447344', 'kya?'),
('7829447344', '9740076782', 'mai usko bola hamara kaam hai'),
('7829447344', '9740076782', 'after 3 months karenge karke'),
('9740076782', '7829447344', 'itna hua hai utna upload kar karenge...'),
('7829447344', '9740076782', 'ha '),
('9740076782', '7829447344', 'ok'),
('7829447344', '9740076782', 'hamara bhi dusara kaam hai na'),
('7829447344', '9740076782', 'uska hi kare to hamara kab hoga'),
('9740076782', '7829447344', ' haan vo bhi hai'),
('7829447344', '9740076782', 'usko bola mai'),
('7829447344', '9740076782', 'after somedays karke'),
('9740076782', '7829447344', 'thik hai chalta hai to'),
('7829447344', '9740076782', 'hmm'),
('9740076782', '7829447344', 'Ok sir bye ....'),
('7829447344', '9740076782', 'bye'),
('9740076782', '9740076782', 'hajags'),
('9740076782', '9740076782', 'fsvh'),
('9740076782', '9740076782', 'sgsvk'),
('9740076782', '9740076782', 'windows'),
('9740076782', '7829447344', 'ckfksu'),
('9740076782', '7829447344', 'ckgsog'),
('9740076782', '9740076782', 'chg'),
('9740076782', '9740076782', 'Ã§'),
('9740076782', '8746055420', ' bxnxjf'),
('9740076782', '8746055420', 'jdjd'),
('8746055420', '9740076782', 'hello'),
('9740076782', '8746055420', 'xjcickf'),
('9740076782', '8746055420', 'zhdufi'),
('9740076782', '8746055420', 'sndgo'),
('9740076782', '8746055420', 'udfhdhdj'),
('9740076782', '8746055420', 'sjfkfjd'),
('8746055420', '9740076782', 'fgxch'),
('9740076782', '8746055420', 'suehshshdhs'),
('9740076782', '8746055420', 'xjxjxjx'),
('9740076782', '8746055420', 'as kfjdhdjak'),
('9740076782', '8746055420', 'bshahs'),
('8746055420', '9740076782', 'xhlÃ§cdvvcucu'),
('9740076782', '8746055420', 'fdjsjs'),
('9740076782', '9740076782', 'xkclvkvl'),
('9740076782', '9740076782', 'jxjzjxjd\nf\nddnf\nf\nf\nf\nf\nf\nf\n'),
('9740076782', '9740076782', 'hzlsjdjdjxjsksjxnshsjdksjsksndndhdjdjdkdjdjekdkfkfjf'),
('9740076782', '8746055420', ',jzhzjxhxhzjxz.c.d.x.x.c.x.d.d.dd'),
('9740076782', '8746055420', 'zbdxhjznznsnxnzbzbxbxjxnsbxjdjdnxnd dnxjxnd d djx dxnd d'),
('9740076782', '8746055420', 'dbdb'),
('8746055420', '9740076782', 'my mrssges'),
('8746055420', '9740076782', 'chat with me'),
('8746055420', '9740076782', 'chat'),
('8746055420', '9740076782', 'hello'),
('8746055420', '9740076782', 'hi'),
('7829447344', '9740076782', 'jsjtejyeo7ro7'),
('7829447344', '9740076782', 'dthfghys'),
('8746055420', '9740076782', 'hsjd'),
('9740076782', '9740076782', 'cifcju'),
('9740076782', '9740076782', 'church'),
('9740076782', '9740076782', 'cmxjcjc'),
('9740076782', '8746055420', 'dzfxxgxyx'),
('9740076782', '8746055420', ' xnflg'),
('9740076782', '8746055420', 'xhfusjf'),
('9740076782', '8746055420', 'fif'),
('8746055420', '9740076782', '  zbsns'),
('9740076782', '8746055420', 'dfiifkf'),
('9740076782', '9740076782', 'xjxjxkc'),
('9740076782', '9740076782', 'xjxjc'),
('9740076782', '9740076782', 'xnxnxnx'),
('9740076782', '9740076782', 'xhdjx'),
('9740076782', '9740076782', 'xnxkc'),
('9740076782', '9740076782', 'zhxjfkg'),
('9740076782', '9740076782', 'fjfjdjfj'),
('9740076782', '9740076782', 'cjckckv'),
('9740076782', '9740076782', 'hxjsis'),
('9740076782', '9740076782', 'ndmfm'),
('9740076782', '9740076782', 'djfkf'),
('9740076782', '9740076782', 'jddlf'),
('9740076782', '9740076782', 'mfkfk'),
('9740076782', '9740076782', 'xvc,vhj'),
('9740076782', '9740076782', '"'),
('8746055420', '9740076782', 'cpgyf'),
('9740076782', '8746055420', 'zdjfjd'),
('8746055420', '9740076782', 'zhgkc'),
('9740076782', '8746055420', 'bxjchd'),
('9740076782', '8746055420', 'vkfkd'),
('9740076782', '8746055420', 'czjzg'),
('9740076782', '8746055420', 'first'),
('9740076782', '8746055420', 'second'),
('9740076782', '9740076782', 'xjxjx'),
('8746055420', '9740076782', 'hdkjd'),
('9740076782', '8746055420', 'xgjzz'),
('8746055420', '9740076782', 'nccc'),
('8746055420', '9740076782', 'mckxkc'),
('9740076782', '8746055420', 'fjdhdj'),
('9740076782', '8746055420', 'dhdhdbd'),
('8746055420', '9740076782', 'vccvgz'),
('9740076782', '8746055420', 'fjxjx'),
('9740076782', '8746055420', 'vxjxjz'),
('9740076782', '8746055420', 'xxdf'),
('9740076782', '8746055420', 'dhjdjd'),
('9740076782', '8746055420', 'evrbht'),
('9740076782', '8746055420', 'vsjsjdi'),
('9740076782', '8746055420', 'ghdjdkd'),
('9740076782', '8746055420', 'vsjjx'),
('8746055420', '9740076782', 'ckcjcc'),
('9740076782', '8746055420', 'hdhdhd'),
('9740076782', '8746055420', 'xcvfdjxjx'),
('9740076782', '8746055420', 'hdhdjjd'),
('9740076782', '8746055420', 'xjdjd'),
('8746055420', '9740076782', 'vjv'),
('9740076782', '8746055420', 'djdjd'),
('9740076782', '8746055420', 'dgdhs'),
('9740076782', '8746055420', 'hdhdjd'),
('9740076782', '8746055420', 'tshsjs'),
('8746055420', '9740076782', 'jfud'),
('9740076782', '8746055420', 'hdkdhzjd'),
('9740076782', '8746055420', 'fussudusus'),
('9740076782', '8746055420', 'sydjf'),
('9740076782', '8746055420', 'shxjdjd'),
('8746055420', '9740076782', 'jcjxjx'),
('9740076782', '8746055420', 'bdnshs'),
('8746055420', '9740076782', 'datA'),
('9740076782', '9740076782', 'cjc'),
('9740076782', '8746055420', 'gkx'),
('9740076782', '8746055420', 'fjfjcjvob'),
('9740076782', '8746055420', 'jdkfkf'),
('8746055420', '9740076782', 'vjjfk'),
('9740076782', '8746055420', 'dhdg'),
('9972969885', '7204234729', 'hello'),
('7204234729', '9972969885', 'hi'),
('9972969885', '7204234729', 'otodosoysttstfvufgggry ttyfydtsyf'),
('7204234729', '9972969885', 'udufgughu hgdif'),
('9740076782', '9740076782', 'fjddhh'),
('9740076782', '9740076782', 'fhjccgi'),
('9740076782', '9740076782', 'ccvk'),
('9740076782', '9740076782', 'vxvb'),
('9740076782', '9740076782', 'tg,.'),
('9740076782', '9740076782', 'test'),
('9740076782', '9740076782', 'hdhdhd'),
('9740076782', '9740076782', 'hsssjs'),
('9740076782', '9740076782', 'chsh'),
('9740076782', '9740076782', 'gshs'),
('9740076782', '9740076782', 'to gfc'),
('9740076782', '9740076782', 'gffj'),
('9972969885', '7204234729', 'fttyf'),
('9740076782', '9740076782', 'test'),
('9833862970', '9740516136', 'trugf'),
('8746055420', '9740076782', 'fnfd'),
('8746055420', '9740076782', 'hi'),
('8746055420', '9740076782', 'dhh'),
('9972969885', '7204234729', 'gdyhft'),
('7204234729', '9972969885', 'gbvhj'),
('9972969885', '7204234729', 'gdgyydcg cdthgd'),
('9740076782', '9740076782', 'bshzjzjx\ndhdjd\nxhd\ndxhdjd\ndhdjxhxmd\ndhd\ndjd\ndh'),
('9740516136', '9833862970', ''),
('9740516136', '9833862970', ''),
('9740516136', '9833862970', ''),
('9740516136', '9833862970', ''),
('9740516136', '9833862970', 'hi'),
('9740516136', '9833862970', ''),
('9740516136', '9833862970', 'hi'),
('9740516136', '9833862970', ''),
('9740516136', '9833862970', ''),
('9740516136', '9833862970', ''),
('9740516136', '9833862970', ''),
('9740516136', '9833862970', ''),
('9740516136', '9833862970', ''),
('9740076782', '9740076782', 'test'),
('9740076782', '9740076782', 'test'),
('9740076782', '9740076782', 'djekjeje'),
('9740076782', '9740076782', 'hdhdhs'),
('9740076782', '9740076782', 'jxjd'),
('9740076782', '9740076782', 'hjx'),
('9740076782', '9740076782', 'what are you doing?'),
('9740076782', '9740076782', 'mdjdnf\nfjf.fjf\nfjfjfjfmfjf.ffkf\nf\ndf'),
('9740076782', '9740076782', 'f hi usme sh jhk'),
('9740076782', '9740076782', 'gjd'),
('9740076782', '9740076782', 'fugi'),
('9740076782', '9740076782', 'fgjg'),
('9740076782', '9740076782', 'messages'),
('9740076782', '9740076782', 'test'),
('9740076782', '9740076782', 'check messagr'),
('9740076782', '9740076782', 'test'),
('9740076782', '9740076782', 'hdud'),
('9740076782', '9740076782', 'test'),
('9740076782', '9740076782', 'windows'),
('9740076782', '9740076782', 'xp hello hi'),
('9740076782', '9740076782', 'fighting'),
('9740076782', '9740076782', 'hshdud'),
('9740076782', '9740076782', 'check'),
('9740076782', '9740076782', 'what '),
('9740076782', '9740076782', 'data'),
('9740076782', '9740076782', 'hi there is a few days of the new York city and then we can get a lot of a bit more information that the new year to the other than that I am not the new year to see if I can get a lot');

-- --------------------------------------------------------

--
-- Table structure for table `gmember`
--

CREATE TABLE IF NOT EXISTS `gmember` (
  `gname` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `number` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gmember`
--

INSERT INTO `gmember` (`gname`, `name`, `number`) VALUES
('Startup', 'goks', '7829447344'),
('Startup', 'tushar', '9833862970'),
('hello', 'Gokul M', '7829447344'),
('goks', 'Gokul M', '7829447344'),
('Lawyers', 'goks', '7829447344'),
('Lawyers', 'tushar', '9833862970'),
('Startup', 'mujahid', '9740076782'),
('Startup', 'akshay', '8746055420'),
('Students', 'akshay', '8746055420'),
('Students', 'mujahid', '9740076782'),
('test', 'Mujahid', '9740076782'),
('test', 'akshay', '8746055420'),
('test2', 'Mujahid', '9740076782'),
('test2', 'akshay', '8746055420'),
('Enterpreneurs', 'mujahid', '9740076782'),
('Students', 'goks', '7829447344'),
('Doctors', 'mujahid', '9740076782'),
('mjgroup', 'Mujahid', '9740076782'),
('mjgroup', 'mujahid', '9740076782'),
('Doctors', 'tushar', '9833862970'),
('Students', 'Shivani', '7204234729'),
('Enterpreneurs', 'tushar', '9833862970'),
('Students', 'tushar', '9833862970'),
('', 'tushar', '9833862970'),
('my name', 'Mujahid', '9740076782');

-- --------------------------------------------------------

--
-- Table structure for table `group2`
--

CREATE TABLE IF NOT EXISTS `group2` (
  `gname` varchar(100) NOT NULL,
  `by` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `group2`
--

INSERT INTO `group2` (`gname`, `by`) VALUES
('Startup', 'admin'),
('Enterpreneurs', 'admin'),
('Lawyers', 'admin'),
('Doctors', 'admin'),
('Students', 'admin'),
('test2', '9740076782'),
('hello', '7829447344'),
('test', '9740076782'),
('goks', '7829447344'),
('my name', '9740076782'),
('hum', '9833862970'),
('boys', '9833862970'),
('mjgroup', '9740076782');

-- --------------------------------------------------------

--
-- Table structure for table `register`
--

CREATE TABLE IF NOT EXISTS `register` (
  `name` varchar(200) NOT NULL,
  `mobile` varchar(130) NOT NULL,
  `token` varchar(200) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `register`
--

INSERT INTO `register` (`name`, `mobile`, `token`) VALUES
('mujahid', '9740076782', 'frQN0BBtaX0:APA91bGPmarEo-E4QTy6PgpHRqfzV3ow5M5cmxSplGE-hXQzWoxxVy8p0WTOzbiGSNWiHqirFqfhwIqwn2hf0bHqVpIbsc66tlqomuJ_xrf-2dZefEGrMsKNBNUBHzRCx7mb7J5nK-Yx'),
('akshay', '8746055420', 'faZjCi6nn7o:APA91bHMPY0a3FsgKFwnFSOMPiBK1dwh0r4bvkAQL5pEy8CfdWx7bf9lO0bYpQNBNTt9aPWZCMeJsGifRD6fCIB9QTfr9FK8HxmQIHcVhG-UJSSzRMql3-E8Ttt3fYUzmlVSOHUOAWJc'),
('goks', '7829447344', 'cOcailX9dic:APA91bFnf2gQLJ2xBaFqsva4aQqaMdOFaQ6ww097d8c-tfBNqxALBZ7WS0t9T2NGB11-vC7HN8wO9TC8pjXwTO-SWVB3uDsmZd-hMCQZRAlamlz88nYwMEUgyNeugYjlIFIasGJzRuDv'),
('', '', ''),
('gokul', '9972969885', 'foX2g-TQ8os:APA91bFlxgw0O0I-vJwmSrv44B1ah0He0meHtMpIhhBkbYzerUKcpfZLwPZ_iiI9WYjIQPutGGdsGcABaeCu4YDYX7QAGwpXQSPLZsPzkwSWsiX_soVct0NOxk3sTEVvXN2GL75yuni8'),
('tushar', '9833862970', 'fKAwEe_To-g:APA91bECsd22nXTAz-nUFR7w-E5GAtW9-LzeiS7eJTltNf5f1J52kTmRQSoFyB69jBeqMoY8KyVKhwzibUPNLGR9H8vPUGqpCReSH4oe29FcBmc2vt9lRXRYihyv08c8BeoIPN4Q_xZI'),
('Ravi', '9740516136', 'fNcNdWa1Jt4:APA91bHNq-IKI4CgUwci-p71tSR-6lFAQE-xErhls3TOSyBJz2WJ95f6-pgz1tvyk-TtPobH7-yXCqu4jqE6VMhd--cpx3fh8bM5n2tBSi73KQFCiwr35tCCKDwJuDNcDGl4NZg-zkJN'),
('Shivani', '7204234729', 'dKt5wb_U7iQ:APA91bH4M1b6hc9Euj0UNluGfBQ9QBv-Xlav-jitCB8L6-ao0KNbozW659N2PhvmOHyb3C4ZH4iDjWO09FuQJHOGCjaJg5GWUZtArHI4jQCSaP1mjkC0jRsTb059Wdccuv4egJowyGqs'),
('Yyoyo', '7204483571', 'eA1YhWUOzjY:APA91bEKUTw5vrXKq5Y4bh0jqQKTA9WuyXZcu_IGsvMndyjsNIfEtInNjkiabxqXirJK-SjQl1Jw6tLp5J3yZEYT0L1kjNl3pbcOHkXasBZoedOq2PmVcmlyDPlkLxF-skzyTMDL3P3A');

-- --------------------------------------------------------

--
-- Table structure for table `tmpdata`
--

CREATE TABLE IF NOT EXISTS `tmpdata` (
  `data` varchar(500) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tmpdata`
--

INSERT INTO `tmpdata` (`data`) VALUES
('9740076782'),
('8746055420'),
('+919833862970'),
('9591642746'),
(''),
('9740076782'),
('9740076782'),
('+917204234729'),
('8951782317'),
('+919740516136'),
('+919740516136');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
