package com.bit.companion.model.order;

import java.sql.SQLException;
import java.util.List;

import com.bit.companion.common.Pagination;
import com.bit.companion.common.Pagination_P;
import com.bit.companion.controller.order.OrderPagenation;
import com.bit.companion.model.entity.order.OrderQuestionVo;

public interface QuestionDao {
	//insert
	public void registReply(OrderQuestionVo orderQuestionVo) throws SQLException;
	//list view
	public List<OrderQuestionVo> replyList(Pagination_P pagination_p) throws SQLException;
	//review count (paging)
	public int replyListAllCount(Pagination_P pagination_p) throws SQLException;
	//review count (paging)
	public OrderQuestionVo replyDetail(int question_id) throws SQLException;
}
