<#include "masters/mainMenu.ftl">
<@menu title="Main page">
<div class="row">
    <div class="btn-group" role="group" aria-label="...">
        <button onclick="window.location.href='/admin/group/add'" type="button" class="btn btn-default">Новая группа</button>
    </div>
</div>
<div class="row">
    <div class="panel panel-default">
        <div class="panel-heading">Список групп</div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th>#</th>
                <th>Имя группы</th>
            </tr>
            </thead>
            <tbody>
            <#assign x = 1>
            <#list groups as group>
            <#--TODO пофиксить жирный текст-->
            <tr onclick="window.location.href='/admin/group/info/${group.id}'">
                <th scope="row">${x}</th>
                <th style="font-weight:300">${group.name}</th>
            </tr>
                <#assign x++>
            </#list>
            </tbody>
        </table>
    </div>
</div>
</@menu>

