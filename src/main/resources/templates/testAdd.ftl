<#include "masters/mainMenu.ftl">
<@menu title="Main page">
<div class="panel panel-default" style="margin-top:45px">
    <div class="panel-heading">
        <h3 class="panel-title">Добавление теста</h3>
    </div>
    <div class="panel-body">

    <#if error??>
    <div class="alert alert-danger" role="alert">${error}</div>
    </#if>

        <form method="post">
            <input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Название" name="name">
            </div>
            <div class="form-group">
                <input type="number" class="form-control" placeholder="Время на выполнение (в минутах)"
                       name="time_limit">
            </div>
            <button type="submit" class="btn btn-default">Добавить</button>
        </form>
    </div>
</div>
</@menu>

