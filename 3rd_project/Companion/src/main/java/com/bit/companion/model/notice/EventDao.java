package com.bit.companion.model.notice;

import java.sql.SQLException;
import java.util.List;

import com.bit.companion.common.Pagination;
import com.bit.companion.model.entity.notice.ArticleVo;

public interface EventDao {

	int selectTotal() throws SQLException;

	List<ArticleVo> selectAll(Pagination pagination) throws SQLException;

	ArticleVo selectOne(int article_id) throws SQLException;

	void updateCount(int article_id) throws SQLException;

}
