<#include "masters/mainMenu.ftl">
<@menu title="Main page">
<div class="panel panel-default" style="margin-top:45px">
    <div class="panel-heading">
        <h3 class="panel-title">Добавление группы</h3>
    </div>
    <div class="panel-body">

    <#if error??>
    <div class="alert alert-danger" role="alert">${error}</div>
    </#if>

        <form method="post">
            <input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden">
            <div class="form-group">
                <input type="text" class="form-control" id="username" placeholder="Имя группы"
                       name="name">
            </div>
            <button type="submit" class="btn btn-default">Создать</button>
        </form>
    </div>
</div>
</@menu>

