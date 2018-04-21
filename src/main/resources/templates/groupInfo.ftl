<#include "masters/mainMenu.ftl">
<@menu title="Main page">
<div class="row">
    <div class="btn-group" role="group" aria-label="...">
        <button onclick="window.location.href='/admin/user/add?group_id=${group.id}'" type="button"
                class="btn btn-default">Добавить пользователя
        </button>
    </div>
<#--TODO тут чет все криво-->
    <form class="btn-group" action="/admin/user/generate_password" method="post">
        <input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden">
        <input type="hidden" name="group_id" value="${group.id}">
        <button type="submit" class="btn btn-default">Сгенерировать пароли</button>
    </form>

</div>
<div class="row">
    <div class="panel panel-default">
        <div class="panel-heading">Участники группы ${group.name}</div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th>#</th>
                <th>Фамилия</th>
                <th>Имя</th>
                <th>Отчество</th>
            </tr>
            </thead>
            <tbody>
            <#assign x = 1>
            <#list users as user>
            <#--TODO пофиксить жирный текст-->
            <tr>
                <th scope="row">${x}</th>
                <th style="font-weight:300">${user.lastName}</th>
                <th style="font-weight:300">${user.firstName}</th>
                <th style="font-weight:300">${user.soname}</th>
            </tr>
                <#assign x++>
            </#list>
            </tbody>
        </table>
    </div>
</div>
</@menu>

