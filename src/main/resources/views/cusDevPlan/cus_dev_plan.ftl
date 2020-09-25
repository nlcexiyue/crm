<!DOCTYPE html>
<html>
<head>
    <title>客户开发计划管理</title>
	<#include "common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form">

            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="customerName"
                           class="layui-input
					searchVal" placeholder="客户名"/>
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="createMan" class="layui-input
					searchVal" placeholder="创建人"/>
                </div>
                <div class="layui-input-inline">
                    <select name="devResult" id="devResult">
                        <option value="">开发状态</option>
                        <option value="0">未开发</option>
                        <option value="1">开发中</option>
                        <option value="2">开发成功</option>
                        <option value="3">开发失败</option>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <a class="layui-btn search_btn" data-type="reload"><i
                            class="layui-icon">&#xe615;</i> 搜索</a>
                </div>


            </div>

    <table id="saleChanceList" class="layui-table" lay-filter="saleChances"></table>


    <script type="text/html" id="toolbarDemo">
	</script>


    <!--操作-->
    <script id="op" type="text/html">
        {{# if (d.devResult=== 0 || d.devResult==1) { }}
        <a href="javascript:;" class="layui-btn layui-btn-warm layui-btn-xs" lay-event="dev">开发</a>
        {{# } else { }}
        <a href="javascript:;" class="layui-btn layui-btn-normal layui-btn-xs" lay-event="info">详情</a>
        {{# } }}
    </script>

</form>
<script type="text/javascript" src="${ctx}/js/cusDevPlan/cus.dev.plan.js"></script>
<script>
    layui.use(['form','table'],function () {
        var form = layui.form,
                table = layui.table;
        table.render({
            elem:'#saleChanceList',
            height:312,
            url:'/cus_dev_plan/list',
            page:true,
            where:{}
            cols:[[
                {field:'id',title:'ID',width:80,fixed:'left'},
                {field:'saleChanceId',title:'订单机会',width:200},
                {field:'planItem',title:'计划标题',width:200},
                {field:'planDate',title:'计划时间',width:200},
                {field:'exeAffect',title:'计划影响',width:200},
                {field:'createDate',title:'创建时间',width:200},
                {field:'updateDate',title:'更新时间',width:200},
                {field:'isValid',title:'有效状态',width:200}]],
            parseData:function (data) {
                return {
                    'data':data.data,
                    'code':data.code
                }
            },
            response:{
                statusCode:0
            }




        })



    })
</script>
</body>
</html>