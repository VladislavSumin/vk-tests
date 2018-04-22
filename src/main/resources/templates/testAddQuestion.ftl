<#include "masters/mainMenu.ftl">
<@menu title="Main page">
<div class="panel panel-default" style="margin-top:45px">
    <div class="panel-heading">
        <h3 class="panel-title">Добавление вопроса к тесту ${test.name}</h3>
    </div>
    <div class="panel-body">

    <#if error??>
    <div class="alert alert-danger" role="alert">${error}</div>
    </#if>

        <form method="post">
            <input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden">
            <input name="test_id" value="${test.id}" type="hidden">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Вопрос" name="question">
            </div>
            <div class="form-group">
            <#--нужно править-->
                <input type="checkbox" name="answer_1_is_right">
                <input type="text" class="form-control" placeholder="Ответ 1"
                       name="answer_1">
            </div>
            <div class="form-group">
            <#--нужно править-->
                <input type="checkbox" name="answer_2_is_right">
                <input type="text" class="form-control" placeholder="Ответ 2"
                       name="answer_2">
            </div>
            <div class="form-group">
            <#--нужно править-->
                <input type="checkbox" name="answer_3_is_right">
                <input type="text" class="form-control" placeholder="Ответ 3"
                       name="answer_3">
            </div>
            <div class="form-group">
            <#--нужно править-->
                <input type="checkbox" name="answer_4_is_right">
                <input type="text" class="form-control" placeholder="Ответ 4"
                       name="answer_4">
            </div>
            <button type="submit" class="btn btn-default">Добавить</button>
        </form>
    </div>
</div>
</@menu>

