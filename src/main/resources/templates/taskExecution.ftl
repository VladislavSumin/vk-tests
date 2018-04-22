<#include "masters/mainMenu.ftl">
<@menu title="Main page">
<div class="panel panel-default" style="margin-top:45px">
    <div class="panel-heading">
        <h3 class="panel-title">Вопрос</h3>
    </div>
    <div class="panel-body">

    <#if error??>
    <div class="alert alert-danger" role="alert">${error}</div>
    </#if>

        <form method="post">
            <input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden">
            <input name="question_id" value="${question.id}" type="hidden">
            <label>${question.question}</label>
            <#assign x = 1>
            <#list question.answers as answer>
            <div class="form-group">
            <#--нужно править-->
            <input type="hidden" name="answer_${x}_id" value="${answer.id}">
                <label>
                    <input type="checkbox" name="answer_${x}_is_right">
                    ${answer.answer}
                </label>
                <#assign x++>
            </#list>
            <button type="submit" class="btn btn-default">Ответить</button>
        </form>
    </div>
</div>
</@menu>

