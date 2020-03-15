package com.wy.dataobject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PanelContentDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PanelContentDOExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPanelIdIsNull() {
            addCriterion("panel_id is null");
            return (Criteria) this;
        }

        public Criteria andPanelIdIsNotNull() {
            addCriterion("panel_id is not null");
            return (Criteria) this;
        }

        public Criteria andPanelIdEqualTo(Integer value) {
            addCriterion("panel_id =", value, "panelId");
            return (Criteria) this;
        }

        public Criteria andPanelIdNotEqualTo(Integer value) {
            addCriterion("panel_id <>", value, "panelId");
            return (Criteria) this;
        }

        public Criteria andPanelIdGreaterThan(Integer value) {
            addCriterion("panel_id >", value, "panelId");
            return (Criteria) this;
        }

        public Criteria andPanelIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("panel_id >=", value, "panelId");
            return (Criteria) this;
        }

        public Criteria andPanelIdLessThan(Integer value) {
            addCriterion("panel_id <", value, "panelId");
            return (Criteria) this;
        }

        public Criteria andPanelIdLessThanOrEqualTo(Integer value) {
            addCriterion("panel_id <=", value, "panelId");
            return (Criteria) this;
        }

        public Criteria andPanelIdIn(List<Integer> values) {
            addCriterion("panel_id in", values, "panelId");
            return (Criteria) this;
        }

        public Criteria andPanelIdNotIn(List<Integer> values) {
            addCriterion("panel_id not in", values, "panelId");
            return (Criteria) this;
        }

        public Criteria andPanelIdBetween(Integer value1, Integer value2) {
            addCriterion("panel_id between", value1, value2, "panelId");
            return (Criteria) this;
        }

        public Criteria andPanelIdNotBetween(Integer value1, Integer value2) {
            addCriterion("panel_id not between", value1, value2, "panelId");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(Long value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(Long value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(Long value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(Long value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(Long value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(Long value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<Long> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<Long> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(Long value1, Long value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(Long value1, Long value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andSortOrderIsNull() {
            addCriterion("sort_order is null");
            return (Criteria) this;
        }

        public Criteria andSortOrderIsNotNull() {
            addCriterion("sort_order is not null");
            return (Criteria) this;
        }

        public Criteria andSortOrderEqualTo(Integer value) {
            addCriterion("sort_order =", value, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderNotEqualTo(Integer value) {
            addCriterion("sort_order <>", value, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderGreaterThan(Integer value) {
            addCriterion("sort_order >", value, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort_order >=", value, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderLessThan(Integer value) {
            addCriterion("sort_order <", value, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderLessThanOrEqualTo(Integer value) {
            addCriterion("sort_order <=", value, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderIn(List<Integer> values) {
            addCriterion("sort_order in", values, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderNotIn(List<Integer> values) {
            addCriterion("sort_order not in", values, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderBetween(Integer value1, Integer value2) {
            addCriterion("sort_order between", value1, value2, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("sort_order not between", value1, value2, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andFullUrlIsNull() {
            addCriterion("full_url is null");
            return (Criteria) this;
        }

        public Criteria andFullUrlIsNotNull() {
            addCriterion("full_url is not null");
            return (Criteria) this;
        }

        public Criteria andFullUrlEqualTo(String value) {
            addCriterion("full_url =", value, "fullUrl");
            return (Criteria) this;
        }

        public Criteria andFullUrlNotEqualTo(String value) {
            addCriterion("full_url <>", value, "fullUrl");
            return (Criteria) this;
        }

        public Criteria andFullUrlGreaterThan(String value) {
            addCriterion("full_url >", value, "fullUrl");
            return (Criteria) this;
        }

        public Criteria andFullUrlGreaterThanOrEqualTo(String value) {
            addCriterion("full_url >=", value, "fullUrl");
            return (Criteria) this;
        }

        public Criteria andFullUrlLessThan(String value) {
            addCriterion("full_url <", value, "fullUrl");
            return (Criteria) this;
        }

        public Criteria andFullUrlLessThanOrEqualTo(String value) {
            addCriterion("full_url <=", value, "fullUrl");
            return (Criteria) this;
        }

        public Criteria andFullUrlLike(String value) {
            addCriterion("full_url like", value, "fullUrl");
            return (Criteria) this;
        }

        public Criteria andFullUrlNotLike(String value) {
            addCriterion("full_url not like", value, "fullUrl");
            return (Criteria) this;
        }

        public Criteria andFullUrlIn(List<String> values) {
            addCriterion("full_url in", values, "fullUrl");
            return (Criteria) this;
        }

        public Criteria andFullUrlNotIn(List<String> values) {
            addCriterion("full_url not in", values, "fullUrl");
            return (Criteria) this;
        }

        public Criteria andFullUrlBetween(String value1, String value2) {
            addCriterion("full_url between", value1, value2, "fullUrl");
            return (Criteria) this;
        }

        public Criteria andFullUrlNotBetween(String value1, String value2) {
            addCriterion("full_url not between", value1, value2, "fullUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlIsNull() {
            addCriterion("pic_url is null");
            return (Criteria) this;
        }

        public Criteria andPicUrlIsNotNull() {
            addCriterion("pic_url is not null");
            return (Criteria) this;
        }

        public Criteria andPicUrlEqualTo(String value) {
            addCriterion("pic_url =", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlNotEqualTo(String value) {
            addCriterion("pic_url <>", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlGreaterThan(String value) {
            addCriterion("pic_url >", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlGreaterThanOrEqualTo(String value) {
            addCriterion("pic_url >=", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlLessThan(String value) {
            addCriterion("pic_url <", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlLessThanOrEqualTo(String value) {
            addCriterion("pic_url <=", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlLike(String value) {
            addCriterion("pic_url like", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlNotLike(String value) {
            addCriterion("pic_url not like", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlIn(List<String> values) {
            addCriterion("pic_url in", values, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlNotIn(List<String> values) {
            addCriterion("pic_url not in", values, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlBetween(String value1, String value2) {
            addCriterion("pic_url between", value1, value2, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlNotBetween(String value1, String value2) {
            addCriterion("pic_url not between", value1, value2, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrl2IsNull() {
            addCriterion("pic_url2 is null");
            return (Criteria) this;
        }

        public Criteria andPicUrl2IsNotNull() {
            addCriterion("pic_url2 is not null");
            return (Criteria) this;
        }

        public Criteria andPicUrl2EqualTo(String value) {
            addCriterion("pic_url2 =", value, "picUrl2");
            return (Criteria) this;
        }

        public Criteria andPicUrl2NotEqualTo(String value) {
            addCriterion("pic_url2 <>", value, "picUrl2");
            return (Criteria) this;
        }

        public Criteria andPicUrl2GreaterThan(String value) {
            addCriterion("pic_url2 >", value, "picUrl2");
            return (Criteria) this;
        }

        public Criteria andPicUrl2GreaterThanOrEqualTo(String value) {
            addCriterion("pic_url2 >=", value, "picUrl2");
            return (Criteria) this;
        }

        public Criteria andPicUrl2LessThan(String value) {
            addCriterion("pic_url2 <", value, "picUrl2");
            return (Criteria) this;
        }

        public Criteria andPicUrl2LessThanOrEqualTo(String value) {
            addCriterion("pic_url2 <=", value, "picUrl2");
            return (Criteria) this;
        }

        public Criteria andPicUrl2Like(String value) {
            addCriterion("pic_url2 like", value, "picUrl2");
            return (Criteria) this;
        }

        public Criteria andPicUrl2NotLike(String value) {
            addCriterion("pic_url2 not like", value, "picUrl2");
            return (Criteria) this;
        }

        public Criteria andPicUrl2In(List<String> values) {
            addCriterion("pic_url2 in", values, "picUrl2");
            return (Criteria) this;
        }

        public Criteria andPicUrl2NotIn(List<String> values) {
            addCriterion("pic_url2 not in", values, "picUrl2");
            return (Criteria) this;
        }

        public Criteria andPicUrl2Between(String value1, String value2) {
            addCriterion("pic_url2 between", value1, value2, "picUrl2");
            return (Criteria) this;
        }

        public Criteria andPicUrl2NotBetween(String value1, String value2) {
            addCriterion("pic_url2 not between", value1, value2, "picUrl2");
            return (Criteria) this;
        }

        public Criteria andPicUrl3IsNull() {
            addCriterion("pic_url3 is null");
            return (Criteria) this;
        }

        public Criteria andPicUrl3IsNotNull() {
            addCriterion("pic_url3 is not null");
            return (Criteria) this;
        }

        public Criteria andPicUrl3EqualTo(String value) {
            addCriterion("pic_url3 =", value, "picUrl3");
            return (Criteria) this;
        }

        public Criteria andPicUrl3NotEqualTo(String value) {
            addCriterion("pic_url3 <>", value, "picUrl3");
            return (Criteria) this;
        }

        public Criteria andPicUrl3GreaterThan(String value) {
            addCriterion("pic_url3 >", value, "picUrl3");
            return (Criteria) this;
        }

        public Criteria andPicUrl3GreaterThanOrEqualTo(String value) {
            addCriterion("pic_url3 >=", value, "picUrl3");
            return (Criteria) this;
        }

        public Criteria andPicUrl3LessThan(String value) {
            addCriterion("pic_url3 <", value, "picUrl3");
            return (Criteria) this;
        }

        public Criteria andPicUrl3LessThanOrEqualTo(String value) {
            addCriterion("pic_url3 <=", value, "picUrl3");
            return (Criteria) this;
        }

        public Criteria andPicUrl3Like(String value) {
            addCriterion("pic_url3 like", value, "picUrl3");
            return (Criteria) this;
        }

        public Criteria andPicUrl3NotLike(String value) {
            addCriterion("pic_url3 not like", value, "picUrl3");
            return (Criteria) this;
        }

        public Criteria andPicUrl3In(List<String> values) {
            addCriterion("pic_url3 in", values, "picUrl3");
            return (Criteria) this;
        }

        public Criteria andPicUrl3NotIn(List<String> values) {
            addCriterion("pic_url3 not in", values, "picUrl3");
            return (Criteria) this;
        }

        public Criteria andPicUrl3Between(String value1, String value2) {
            addCriterion("pic_url3 between", value1, value2, "picUrl3");
            return (Criteria) this;
        }

        public Criteria andPicUrl3NotBetween(String value1, String value2) {
            addCriterion("pic_url3 not between", value1, value2, "picUrl3");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}