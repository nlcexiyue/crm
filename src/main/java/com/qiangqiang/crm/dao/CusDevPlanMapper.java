package com.qiangqiang.crm.dao;


import com.qiangqiang.base.BaseMapper;
import com.qiangqiang.crm.query.CusDevPlanQuery;
import com.qiangqiang.crm.vo.CusDevPlan;

import java.util.List;

public interface CusDevPlanMapper extends BaseMapper<CusDevPlan,Integer> {

    List<CusDevPlan> selectBySearch(CusDevPlanQuery cusDevPlanQuery);

}