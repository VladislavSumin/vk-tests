<#include "masters/mainMenu.ftl">
<@menu title="Main page">
<#--<div class="row">-->
    <#--<div class="btn-group" role="group" aria-label="...">-->
        <#--<button onclick="window.location.href='/admin/group/add'" type="button" class="btn btn-default">Новая группа-->
        <#--</button>-->
    <#--</div>-->
<#--</div>-->
<div class="row">
    <div class="panel panel-default">
        <div class="panel-heading">Участники группы</div>

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

