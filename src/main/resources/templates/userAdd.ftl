<#include "masters/mainMenu.ftl">
<@menu title="Main page">
<div class="panel panel-default" style="margin-top:45px">
    <div class="panel-heading">
        <h3 class="panel-title">Добавление пользователя в группу ${group.name}</h3>
    </div>
    <div class="panel-body">

    <#if error??>
    <div class="alert alert-danger" role="alert">${error}</div>
    </#if>

        <form method="post">
            <input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden">
            <input name="group_id" value="${group.id}" type="hidden">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Фамилия"
                       name="secondName">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Имя"
                       name="firstName">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Отчество"
                       name="soname">
            </div>
            <button type="submit" class="btn btn-default">Добавить</button>
        </form>
    </div>
</div>
</@menu>

