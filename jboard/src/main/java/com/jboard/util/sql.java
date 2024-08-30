package com.jboard.util;

public class sql {

	public static final String select_terms = "select * from terms";
	
	public static final String select_count_user = "SELECT COUNT(*) FROM `user`";
	public static final String where_uid = "where `uid`=?"; // 따로 써서 where절 빼는 거임
	public static final String where_nick = "where `nick`=?";
	public static final String where_email = "where `email`=?";
	public static final String where_hp = "where `hp`=?";
	
	public static final String select_user = "select * from `user` where `uid`=? and `pass`=SHA2(?, 256)";
	public static final String insert_user = "insert into user set"
												+"`uid`=?,"
												+"`pass`=SHA2(?, 256)," // DB 관리자도 몰라야 함 (암호화 해야 함)
												+"`name`=?,"
												+"`nick`=?,"
												+"`email`=?,"
												+"`hp`=?,"
												+"`zip`=?,"
												+"`addr1`=?,"
												+"`addr2`=?,"
												+"`regip`=?,"
												+"`regDate`=NOW()";
	
	// article
	public static final String select_count_total = "SELECT COUNT(*) FROM `article`";
	public static final String select_article = "SELECT * FROM `article` as a "
												+ "left JOIN `file` AS b ON a.`no` = b.`ano` "
												+ "WHERE `no`=?";
	public static final String select_articles = "SELECT ROW_NUMBER() OVER(ORDER BY `no` DESC), a.*, b.nick FROM `article` AS a "
												+"JOIN `user` AS b ON a.writer = b.uid "
												+"order by `no` desc "
												+"limit ?, 10";
	
	
	public static final String select_max_no = "select MAX(`no`) from `article`";
	public static final String insert_article = "insert into article set "
													+"`title`=?,"
													+"`content`=?,"
													+"`file`=?,"
													+"`writer`=?,"
													+"`regip`=?,"
													+"`rdate`=NOW()";
	// comment
	public static final String select_comment = "SELECT a.*, b.nick FROM `comment` AS a "
													+ "JOIN `user` AS b ON a.writer = b.uid "
													+ "WHERE `no`=?";
	public static final String select_comments = "SELECT a.*, b.nick FROM `comment` AS a "
													+ "JOIN `user` AS b ON a.writer = b.uid "
													+ "WHERE `parent`=?"
													+ "order by no";
													
	public static final String insert_comment = "insert into `comment`set "
													+"`parent`=?,"
													+"`content`=?,"
													+"`writer`=?,"
													+"`regip`=?,"
													+"`rdate`=NOW()";
	
	public static final String update_comment = "update `comment` set `content`=? where `no`=?";
	public static final String delete_comment = "delete from `comment` where `no`=?";
	
	// file
	public static final String select_file = "select * from `file` where `fno`=?";
	public static final String insert_file = "insert into file set "
													+"`ano`=?,"
													+"`oName`=?,"
													+"`sName`=?,"
													+"`rdate`=NOW()";
	public static final String update_file_download_count = "update `file` set `download` = `download` + 1 where `fno`=?";
}

























